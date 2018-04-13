create or replace procedure account(p_period in varchar2,
                                    p_result out varchar2) is

  --获取现有激活客户
  cursor customer is
    SELECT * FROM c_merch_cust_base cmcb where cmcb.states = '3';

  --获取客户的资金账户
  cursor cust_account(p_merch_cust_id varchar2, s_period varchar2,p_organization_id in varchar2) is
    SELECT *
      FROM c_merch_cust_balances cmcb
     where 1 = 1
       and cmcb.period = s_period
       and cmcb.organization_id= p_organization_id
       and cmcb.merch_cust_id = p_merch_cust_id;
  amt       number := 0;
  maxperiod varchar2(20);
begin
  --判断当前期间是否是现有数据的最大期间
  begin
    SELECT max(period) into maxperiod FROM c_merch_cust_balances;
    if p_period = maxperiod then
      p_result := p_period || '数据已经生成。';
      return;
    end if;
  end;
  begin
    for cust in customer loop
      for account in cust_account(p_merch_cust_id => cust.id,
                                  s_period        => to_char(add_months(to_date(p_period,
                                                                                'YYYY-MM'),
                                                                        -1),
                                                             'YYYY-MM'),
                                  p_organization_id =>cust.organization_id) loop
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
           cust.id,
           account.account_type,
           amt,
           0,
           0,
           0,
           p_period);
      end loop;
    end loop;
    commit;
    begin
       update c_merch_cust_balances cmcb
         set cmcb.c_amt =
             nvl((SELECT sum(nvl(cmcal.c_amt, 0))
                FROM c_merch_cust_account_log cmcal
               where cmcal.merch_cust_id = cmcb.merch_cust_id
                 and cmcal.organization_id = cmcb.organization_id
                 and cmcal.period = cmcb.period),0),
             cmcb.d_amt =
             nvl((SELECT sum(nvl(cmcal.d_amt, 0))
                FROM c_merch_cust_account_log cmcal
               where cmcal.merch_cust_id = cmcb.merch_cust_id
                 and cmcal.organization_id = cmcb.organization_id
                 and cmcal.period = cmcb.period),0)
       where cmcb.period = p_period;
    exception
      when others then
        p_result := '生成数据成功，更新失败';
        return;
    end;
    commit;
  exception
    when others then
      p_result := '生成数据出错';
      return;
  end;
  p_result := '生成数据成功';
end ;
