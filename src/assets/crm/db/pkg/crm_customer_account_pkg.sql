create or replace package crm_customer_account_pkg is

  procedure account_period(p_result out varchar2);
  function validate_balances(p_merch_cust_id in number,
                             p_account_type  in varchar2) return varchar2;
  function validate_merch(p_merch_cust_id in number,
                          p_period        in varchar2,
                          p_account_type  in varchar2) return varchar2;
  /* procedure getcashamt(p_merch_cust_id in number,
                       ytd             out number,
                       d_amt           out number,
                       c_amt           out number,
                       ptd             out number);
  function getcustamt(p_merch_cust_id in number,
                      p_account_type  in varchar2,
                      p_period        in varchar2) return number;
  
  function insertdata(p_organization_id in number,
                      p_merch_cust_id   in number,
                      p_account_type    in varchar2,
                      p_ytd             in number,
                      p_d_amt           in number,
                      p_c_amt           in number,
                      p_ptd             in number) return number;*/
end crm_customer_account_pkg;


create or replace package body crm_customer_account_pkg is
  /*========================
  name :account_period
  type: procedure
  descirption:计算客户期末余额并生成下月期初
  author:add by hhnz-chenkai 2016-08-22
  =========================*/
  procedure account_period(p_result out varchar2) is
  
    --获取客户的资金账户
    cursor cust_account is
      SELECT *
        FROM c_merch_cust_balances cmcb
       where 1 = 1
         and cmcb.period = to_char(sysdate - 1, 'yyyy-MM');
    sysday   number;
    amt      number := 0;
    v_flag   varchar2(4);
    v_m_flag varchar2(4);
  begin
    --判断当前时间是否为月初第一天
    begin
    
      select extract(day from sysdate) into sysday from dual;
      if sysday != 1 then
        --每月月初凌晨执行
        p_result := '账户更新在每月第一天凌晨执行';
        return;
      end if;
    end;
    begin
      for account in cust_account loop
      
        v_flag := 'E';
        v_flag := validate_balances(account.merch_cust_id,
                                    account.account_type);
        if v_flag != 'E' then
          continue;
        end if;
        v_m_flag := 'E';
        v_m_flag := validate_merch(account.merch_cust_id,
                                   account.period,
                                   account.account_type);
        if v_m_flag != 'S' then
          continue;
        end if;
        --计算期末余额
        amt := nvl(account.ytd, 0) + nvl(account.d_amt, 0) -
               nvl(account.c_amt, 0);
        --更新期末数据
        begin
          update c_merch_cust_balances cmcb
             set cmcb.ptd = amt
           where cmcb.id = account.id;
        end;
      
        insert into c_merch_cust_balances
          (id,
           organization_id,
           merch_cust_id,
           account_type,
           ytd,
           d_amt,
           c_amt,
           ptd,
           period)
        values
          (seq_merch_balances_id.nextval,
           account.organization_id,
           account.merch_cust_id,
           account.account_type,
           amt,
           0,
           0,
           0,
           to_char(add_months(sysdate, 1), 'yyyy-mm'));
      end loop;
      commit;
      p_result := '客户账户期间数据更新成功';
    exception
      when others then
        p_result := '更新客户账户数据出错';
    end;
  end account_period;

  /*========================
  name :validate_balances
  type: function
  descirption:验证客户数据
  author:add by hhnz-chenkai 2016-08-22
  return :S:已经生成账户数据
          E:没有生成账户数据
          N：查询数据错误
  =========================*/
  function validate_balances(p_merch_cust_id in number,
                             p_account_type  in varchar2) return varchar2 is
    v_flag varchar2(4) := 'S';
  begin
    begin
      SELECT nvl('S', 'E')
        into v_flag
        FROM c_merch_cust_balances cmcb
       where cmcb.merch_cust_id = p_merch_cust_id
         and cmcb.account_type = p_account_type
         and cmcb.period = to_char(sysdate, 'yyyy-MM');
    exception
      when no_data_found then
        v_flag := 'E';
      when others then
        v_flag := 'E';
    end;
    return v_flag;
  end;
 
  /*========================
  name :validate_merch
  type: function
  descirption:验证客户数据
  author:add by hhnz-chenkai 2016-08-22
  return :S:需要创建下一个月账户
          E:不需要创建下一个月账户
  =========================*/
  function validate_merch(p_merch_cust_id in number,
                          p_period        in varchar2,
                          p_account_type  in varchar2) return varchar2 is
    v_flag varchar2(4);
    v_ytd  number;
  begin
    begin
      SELECT nvl('S', 'E')
        into v_flag
        FROM c_merch_cust_base cmcb
       where cmcb.id = p_merch_cust_id
         and cmcb.states = '3';
    exception
      when no_data_found then
        v_flag := 'E';
      when others then
        v_flag := 'S';
    end;
    if v_flag = 'E' then
      --如果客户状态不是激活状态，判断客户是否当前账户余额是否为0
      SELECT cmcb.ytd + nvl(cmcb.d_amt, 0) - nvl(cmcb.c_amt, 0)
        into v_ytd
        FROM c_merch_cust_balances cmcb
       where cmcb.merch_cust_id = p_merch_cust_id
         and cmcb.period = p_period
         and cmcb.account_type = p_account_type;
      if v_ytd != 0 then
        --失效客户下存在期末余额
        return 'S';
      else
        return 'E';
      end if;
    end if;
  end;
end crm_customer_account_pkg;
