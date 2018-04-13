create or replace package crm_ar_balance_pkg is
  procedure mian(p_result out varchar2);
  procedure ar_period(p_period in varchar2, p_result out varchar2);
  procedure update_merch(p_merch_cust_id in number, p_result out varchar2);
  function vlidate_ar(p_merch_cust_id in number, p_period in varchar2)
    return varchar2;
end crm_ar_balance_pkg;


create or replace package body crm_ar_balance_pkg is

  /*=================================
  name：main
  type；procedure
  description:应收账款期末数据结算
  author:add by hhnz-chenkai 2016-08-29
  ===================================*/
  procedure mian(p_result out varchar2) is
  
    --获取应收账款数据
    cursor ar_balances is
      SELECT max(cmab.period) keep(dense_rank first order by cmab.period desc) period,
             cmab.merch_cust_id,
             cmab.organization_id
        FROM c_merch_ar_balance cmab
       where 1 = 1
       group by cmab.merch_cust_id, cmab.organization_id;
    sysday   number;
    ytd      number := 0;
    dAmt     number := 0;
    cAmt     number := 0;
    ptdamt   number := 0;
    v_flag   varchar2(4);
  begin
    --判断当前时间是否为月初第一天
    begin
      select extract(day from sysdate) into sysday from dual;
      if sysday != 1 then
        p_result := '应收账款更新在每月第一天凌晨执行';
        return;
      end if;
    end;
    
    begin
      for ar in ar_balances loop
        --验证数据
        v_flag := 'E';
        v_flag := vlidate_ar(ar.merch_cust_id, to_char(sysdate, 'yyyy-MM'));
        if v_flag != 'E' then
          continue;
        end if;
        --计算本期增加
        begin
          SELECT nvl(sum(oi.total_price + oi.total_tax), 0)
            into dAmt
            FROM om_invoice oi, c_merch_cust_base cmcb
           where 1 = 1
             and oi.customer_id = cmcb.sap_customer_id
             and cmcb.id = ar.merch_cust_id
             and to_char(to_date(oi.period, 'yyyy-mm'), 'yyyy-mm') =
                 ar.period;
        exception
          when others then
            dAmt := 0;
        end;
      
        --计算本期减少
        begin
          SELECT nvl(sum(cmcal.d_amt), 0) / 100
            into cAmt
            FROM c_merch_cust_account_log cmcal
           where cmcal.type = '1'
             and cmcal.period = ar.period
             and cmcal.merch_cust_id = ar.merch_cust_id
             and cmcal.states = 'S';
        exception
          when others then
            cAmt := 0;
        end;
        begin
          SELECT cmab.ytd
            into ytd
            FROM c_merch_ar_balance cmab
           where cmab.merch_cust_id = ar.merch_cust_id
             and cmab.period = ar.period;
        exception
          when others then
            ytd := 0;
        end;
        ptdamt := ytd + dAmt - cAmt;
        --更新数据
        update c_merch_ar_balance
           set d_amt = dAmt, c_amt = cAmt, ptd = ptdamt
         where id = ar.merch_cust_id
           and period = ar.period;
      
        insert into c_merch_ar_balance
          (id,
           organization_id,
           period,
           ytd,
           d_amt,
           c_amt,
           ptd,
           merch_cust_id)
        values
          (SEQ_MERCH_AR_ID.nextval,
           ar.organization_id,
           to_char(sysdate,'yyyy-mm'),
           ptdamt,
           0,
           0,
           0,
           ar.merch_cust_id);
      end loop;
      commit;
    exception
      when others then
        p_result := '获取数据出错';
        return;
    end;
  
    --更新数据
    begin
      insert into c_merch_ar_balance
        (id,
         organization_id,
         period,
         ytd,
         d_amt,
         c_amt,
         ptd,
         merch_cust_id)
        (SELECT SEQ_MERCH_AR_ID.nextval,
                cmcb.organization_id,
                to_char(sysdate, 'YYYY-MM'),
                0,
                0,
                0,
                0,
                cmcb.id
           FROM c_merch_cust_base cmcb
          where 1 = 1
            and cmcb.states = '3'
            and cmcb.cust_type not in ('2', '6', '7', '70', '8')
            and not exists
          (SELECT 1
                   FROM c_merch_ar_balance a
                  where 1 = 1
                    and a.merch_cust_id = cmcb.id
                    and a.organization_id = cmcb.organization_id));
      commit;
    end;
    p_result := '应收账款月结成功';
  end;

  /*=================================
  name：vlidate_ar
  type；function
  description:验证数据
  author:add by hhnz-chenkai 2017-02-27
  ===================================*/
  function vlidate_ar(p_merch_cust_id in number, p_period in varchar2)
    return varchar2 is
    v_flag   varchar2(4) := 'S';
    --v_states varchar2(4);
   -- v_ptd    number := 0;
  begin
    --验证当前月数据是否已经生成
    begin
      SELECT nvl('S', 'E')
        into v_flag
        FROM c_merch_ar_balance cmab
       where cmab.merch_cust_id = p_merch_cust_id
         and cmab.period = to_char(sysdate, 'yyyy-MM');
    exception
      when no_data_found then
        v_flag := 'E';
      when others then
        v_flag := 'N';
    end;
    
    /*--盘点客户状态，
    SELECT cmcb.states
      into v_states
      FROM c_merch_cust_base cmcb
     where cmcb.id = p_merch_cust_id;
  
    if v_states = '4' then
      --休眠客户判断当前最新月应收账款数据期末余额是否为0
      SELECT cmab.ytd + nvl(cmab.d_amt, 0) - nvl(cmab.c_amt, 0)
        into v_ptd
        FROM c_merch_ar_balance cmab
       where cmab.merch_cust_id = p_merch_cust_id
         and cmab.period = p_period;
      
      --判断休眠客户订单是否还有对应的应收账款数据没有生成
      
      SELECT * FROM  om_order_headers_all ooha
      where ooha.merch_cust_id= p_merch_cust_id
      and ooha.states in 
    end if;*/
    return v_flag;
  end;

  /*=================================
  name：cost_period
  type；procedure
  description:手工触发结算应收账款并生成数据
  author:add by hhnz-chenkai 2016-08-29
  ===================================*/

  procedure ar_period(p_period in varchar2, p_result out varchar2) is
    --获取应收账款数据
    cursor ar_balances is
      SELECT *
        FROM c_merch_ar_balance ccb
       where 1 = 1
         and ccb.period =
             to_char(add_months(to_date(p_period, 'yyyy-mm'), -1),
                     'yyyy-mm');
    ptdamt number := 0;
    dAmt   number := 0;
    cAmt   number := 0;
    flag   number := 0;
    period varchar2(10) := to_char(add_months(to_date(p_period, 'yyyy-mm'),
                                              -1),
                                   'yyyy-mm');
  begin
  
    --判断传入期间的数据是否已经生成
    begin
      SELECT nvl(count(1), 0)
        into flag
        FROM c_merch_ar_balance cmab
       where cmab.period = p_period;
    
      if flag = 1 then
        /*dbms_output.put_line('当前时间为月底最后一天');
        else*/
        p_result := '当前期间' || p_period || '已经生成数据';
        return;
      end if;
    end;
    begin
      for ar in ar_balances loop
        --计算本期增加
        begin
          SELECT nvl(sum(oi.total_price + oi.total_tax), 0)
            into dAmt
            FROM om_invoice oi, c_merch_cust_base cmcb
           where 1 = 1
             and oi.customer_id = cmcb.sap_customer_id
             and cmcb.id = ar.merch_cust_id
             and to_char(to_date(oi.period, 'yyyy-mm'), 'yyyy-mm') =
                 ar.period;
        end;
      
        --计算本期减少
        begin
          SELECT nvl(sum(cmcal.d_amt), 0) / 100
            into cAmt
            FROM c_merch_cust_account_log cmcal
           where cmcal.type = '1'
             and cmcal.period = ar.period
             and cmcal.merch_cust_id = ar.merch_cust_id
             and cmcal.states = 'S';
        end;
      
        ptdamt := ar.ytd + dAmt - cAmt;
        /*dbms_output.put_line(ptdamt);*/
        update c_merch_ar_balance
           set ptd = ptdamt, d_amt = dAmt, c_amt = cAmt
         where id = ar.id;
      
        --插入下一期间数据
        insert into c_merch_ar_balance
          (id,
           organization_id,
           period,
           ytd,
           d_amt,
           c_amt,
           ptd,
           merch_cust_id)
        values
          (SEQ_MERCH_AR_ID.nextval,
           ar.organization_id,
           p_period,
           ptdamt,
           0,
           0,
           0,
           ar.merch_cust_id);
      end loop;
      commit;
    exception
      when others then
        p_result := '获取数据出错';
        return;
    end;
    --获取新生成省区信息
    begin
      insert into c_merch_ar_balance
        (id,
         organization_id,
         period,
         ytd,
         d_amt,
         c_amt,
         ptd,
         merch_cust_id)
        (SELECT SEQ_MERCH_AR_ID.nextval,
                cmcb.organization_id,
                to_char(sysdate, 'YYYY-MM'),
                0,
                0,
                0,
                0,
                cmcb.id
           FROM c_merch_cust_base cmcb
          where 1 = 1
            and cmcb.states = '3'
            and cmcb.cust_type not in ('2', '6', '7','70','8')
            and not exists
          (SELECT 1
                   FROM c_merch_ar_balance a
                  where 1 = 1
                    and a.merch_cust_id = cmcb.id
                    and a.organization_id = cmcb.organization_id));
      commit;
    end;
    p_result := '应收账款月结成功';
  end;

  /*=================================
  name：update_merch
  type；procedure
  description:手工触发更新客户的应收账款数据
  author:add by hhnz-chenkai 2016-08-29
  ===================================*/
  procedure update_merch(p_merch_cust_id in number, p_result out varchar2) is
    --获取应收账款数据
    cursor ar_balances is
      SELECT *
        FROM c_merch_ar_balance cmab
       where 1 = 1
         and cmab.merch_cust_id = p_merch_cust_id
       order by cmab.period;
    dAmt   number := 0;
    cAmt   number := 0;
    ptdamt number := 0;
  begin
    begin
      for ar in ar_balances loop
        --计算本期增加
        begin
          SELECT nvl(sum(oi.total_price + oi.total_tax), 0)
            into dAmt
            FROM om_invoice oi, c_merch_cust_base cmcb
           where 1 = 1
             and oi.customer_id = cmcb.sap_customer_id
             and cmcb.id = ar.merch_cust_id
             and to_char(to_date(oi.period, 'yyyy-mm'), 'yyyy-mm') =
                 ar.period;
        end;
      
        --计算本期减少
        begin
          SELECT nvl(sum(cmcal.d_amt), 0) / 100
            into cAmt
            FROM c_merch_cust_account_log cmcal
           where cmcal.type = '1'
             and cmcal.period = ar.period
             and cmcal.merch_cust_id = ar.merch_cust_id
             and cmcal.states = 'S';
        end;
      
        ptdamt := ar.ytd + dAmt - cAmt;
      
        --更新期末
        update c_merch_ar_balance
           set ptd = ptdamt, d_amt = dAmt, c_amt = cAmt
         where id = ar.id;
      
        --更新下一个月期末数据
        update c_merch_ar_balance a
           set a.ytd = ptdamt
         where a.merch_cust_id = ar.merch_cust_id
           and a.organization_id = ar.organization_id
           and a.period =
               to_char(add_months(to_date(ar.period, 'yyyy-mm'), 1),
                       'yyyy-mm');
        commit;
      end loop;
    exception
      when others then
        p_result := '更新客户应收账款数据失败';
    end;
    p_result := '更新客户应收账款数据成功';
  end;
end crm_ar_balance_pkg;
