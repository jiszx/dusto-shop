create or replace package CRM_TRANSFER_ORDER_COLASE is
  procedure order_close(p_order_id in number, p_result out varchar2);
  procedure main(p_order_id in number, p_result out varchar2);
  function validate_is_debit(p_order_id in number) return varchar2;
  function do_close_undebit(p_order_id in number) return varchar2;
  function do_close_deit(p_order_id in number) return varchar2;
  procedure update_merch_amt(p_account_type in varchar2,
                             p_amt          in number,
                             p_order_id     in number);

  procedure update_inv(p_cust_type    in varchar2,
                       p_material_id  in varchar2,
                       p_num          in number,
                       p_factory_code in varchar2);
  procedure close_shipped_order(p_order_id in number ,p_result out varchar2);
  procedure update_shipeed_inv(p_cust_type    in varchar2,
                       p_material_id  in varchar2,
                       p_num          in number,
                       p_factory_code in varchar2);
end CRM_TRANSFER_ORDER_COLASE;


create or replace package body crm_transfer_order_colase is

  procedure main(p_order_id in number, p_result out varchar2) as
    v_order_type varchar2(4);
    v_order_id   number;
  begin
    begin
      SELECT ooha.order_type
        into v_order_type
        FROM om_order_headers_all ooha
       where ooha.id = p_order_id;
    exception
      when others then
        v_order_type := 'E';
    end;
    if v_order_type = 'E' then
      p_result := '获取订单类型失败';
      return;
    end if;
    if v_order_type = '5' then
      --调拨单关闭
      order_close(p_order_id, p_result);
    elsif v_order_type = '7' then
      --调拨单转化订单
      begin
        sELECT to_number(ooha.attribute13)
          into v_order_id
          FROM om_order_headers_all ooha
         where ooha.id = p_order_id;
      end;
      order_close(v_order_id, p_result);
    end if;
  end;
  /*=================================
  name:main
  description；调拨单关闭主程序
  author:hhnz-skevin 2017-02-16
  ===================================*/
  procedure order_close(p_order_id in number, p_result out varchar2) as
    v_states varchar2(4);
    flag     varchar2(4) := 'S';
  begin
    --判断订单:状态为待发货，或者已发货，发货数量为0的调拨单
    begin
      SELECT ooha.states
        into v_states
        FROM om_order_headers_all ooha
       where ooha.id = p_order_id
         and ooha.order_type in('5','8')
         and ooha.states in ('5', '7')
         and exists
       (SELECT 1
                FROM c_merch_cust_base cmcb
               where cmcb.id = ooha.merch_cust_id
                 and ((cmcb.cust_type = '7' and
                     (SELECT nvl(sum(cvwh.change_amt), 0)
                          FROM crm_virtual_warehouse_history cvwh
                         where 1 = 1
                           and cvwh.cust_type like 'Z9%'
                           and cvwh.related_order_id = ooha.id) = 0) or
                     (cmcb.cust_type = '70' and
                     (SELECT nvl(sum(oodh.num), 0)
                          FROM om_order_delivered_history oodh,
                               om_order_spilts            oos,
                               om_order_headers_all       ooh
                         where oodh.sap_order_id = ooh.sap_order_id
                           and ooh.id = ooha.attribute13
                           and ooh.id = oos.header_id
                           and ooh.merch_cust_id = oos.merch_cust_id
                           and oodh.orderitem_sap_no = oos.orderitem_sap_no) = 0)));
    exception
      when others then
        flag := 'E';
    end;
    if flag = 'E' then
      p_result := '只能发货数量为0的待发货或者已发货订单可以关闭';
      return;
    end if;
    if v_states = '1' then
      p_result := '编辑状态调拨单请直接删除';
      return;
    else
      --判断订单是否已经扣款
      flag := validate_is_debit(p_order_id);
      if flag = 'N' then
        p_result := '查询订单扣款日志出错';
        return;
      end if;
      if flag = 'S' then
        --订单已扣款
        p_result := do_close_deit(p_order_id);
      else
        --订单未扣款
        p_result := do_close_undebit(p_order_id);
      end if;
    end if;
    commit;
  end;
  /*=================================
  name:validate_is_debit
  description；验证调拨单是否扣款
  author:hhnz-skevin 2017-02-16
  ===================================*/
  function validate_is_debit(p_order_id in number) return varchar2 is
    p_flag varchar2(4) := 'E';
  begin
    begin
      SELECT nvl('S', 'E')
        into p_flag
        FROM c_merch_cust_account_log cmcal
       where cmcal.order_id = to_char(p_order_id);
    exception
      when no_data_found then
        return 'E';
      when others then
        return 'N';
    end;
    return p_flag;
  end;
  /*=================================
  name:do_close_undebit
  description；未扣款调拨单关闭
  author:hhnz-skevin 2017-02-16
  ===================================*/
  function do_close_undebit(p_order_id in number) return varchar2 as
  begin
    begin
      update om_order_headers_all ooha
         set ooha.states = '10'
       where ooha.id = p_order_id;
    exception
      when others then
        return '关闭订单失败';
    end;
    return '关闭订单成功';
  end;
  /*=================================
  name:do_close_deit
  description；已扣款调拨单关闭
  author:hhnz-skevin 2017-02-16
  ===================================*/
  function do_close_deit(p_order_id in number) return varchar2 as
    cursor line_datas is
      SELECT oos.material_id, oos.num, ooha.rdc_code, cso.sap_id
        FROM om_order_spilts        oos,
             om_order_headers_all   ooha,
             crm_sales_organization cso
       where 1 = 1
         and oos.header_id = ooha.id
         and ooha.organization_id = cso.id
         and oos.header_id = p_order_id;
    v_cash_amt   number; --现金
    v_credit_amt number; --授信
    v_bond_amt   number; --保证金额度
    v_order_amt  number := 0; --订单金额
    v_cust_type  varchar2(4);
  begin
  
    --获取客户资金余额
    SELECT cmca.cash_amt, cmca.credit_amt, cmca.bond_amt
      into v_cash_amt, v_credit_amt, v_bond_amt
      FROM c_merch_cust_account cmca, om_order_headers_all ooha
     where cmca.merch_cust_id = ooha.merch_cust_id
       and ooha.id = p_order_id;
    --获取订单金额
  
    SELECT sum(oos.amt)
      into v_order_amt
      FROM om_order_spilts oos
     where oos.header_id = p_order_id;
    --判断授信额度是否等于保证金金额
    if v_credit_amt = v_bond_amt then
      --授信金额等于保证金金额
      --elsif v_credit_amt < v_contract_credit_amt then
      update_merch_amt('1', v_order_amt, p_order_id);
    elsif v_credit_amt < v_bond_amt then
      --授信金额小于保证金金额
      if v_bond_amt - v_credit_amt < v_order_amt then
        --订单金额大于已用授信金额
        update_merch_amt('3', v_bond_amt - v_credit_amt, p_order_id);
        update_merch_amt('1',
                         v_order_amt - (v_bond_amt - v_credit_amt),
                         p_order_id);
      else
        --订单金额小于已用金额
        update_merch_amt('3', v_order_amt, p_order_id);
      end if;
    end if;
    begin
      SELECT cmcb.cust_type
        into v_cust_type
        FROM c_merch_cust_base cmcb, Om_Order_Headers_All ooha
       where cmcb.id = ooha.merch_cust_id
         and ooha.id = p_order_id;
    end;
    --更新库存
    --if v_cust_type = '7' then
      for rec in line_datas loop
        update_inv(p_cust_type    => rec.rdc_code,
                   p_material_id  => rec.material_id,
                   p_num          => rec.num,
                   p_factory_code => rec.sap_id);
      end loop;
    --end if;
    --更新订单状态
    update om_order_headers_all ooha
       set ooha.states = '10'
     where ooha.id = p_order_id;
    return '关闭订单成功';
  end;

  /*=================================
  name:update_merch_amt
  description；更新客户资金
  author:hhnz-skevin 2017-02-16
  ===================================*/
  procedure update_merch_amt(p_account_type in varchar2,
                             p_amt          in number,
                             p_order_id     in number) is
  
  begin
    dbms_output.put_line(p_amt);
    --插入日志表
    insert into c_merch_cust_account_log
      (ID,
       MERCH_CUST_ID,
       ORGANIZATION_ID,
       TYPE,
       D_AMT,
       C_AMT,
       ACCOUNT_TYPE,
       ORDER_ID,
       PERIOD,
       CREATE_TS,
       CREATER,
       STATES)
      (SELECT seq_merch_account_log_id.nextval,
              ooha.merch_cust_id,
              ooha.organization_id,
              '12',
              p_amt,
              0,
              p_account_type,
              p_order_id,
              to_char(sysdate, 'yyyy-MM'),
              sysdate,
              'admin',
              'S'
         FROM om_order_headers_all ooha
        where ooha.id = p_order_id);
  
    --更新期间表
    update c_merch_cust_balances cmcb
       set cmcb.d_amt = cmcb.d_amt + p_amt
     where cmcb.account_type = p_account_type
       and cmcb.period = to_char(sysdate, 'yyyy-MM')
       and exists (SELECT 1
              FROM om_order_headers_all ooha
             where ooha.merch_cust_id = cmcb.merch_cust_id
               and ooha.organization_id = cmcb.organization_id
               and ooha.id = p_order_id);
  
    --更新账户余额表
    if p_account_type = '1' then
      update c_merch_cust_account cmca
         set cmca.cash_amt = cmca.cash_amt + p_amt
       where 1 = 1
         and exists
       (SELECT 1
                FROM om_order_headers_all ooha
               where cmca.merch_cust_id = ooha.merch_cust_id
                 and cmca.organization_id = ooha.organization_id
                 and ooha.id = p_order_id);
    elsif p_account_type = '3' then
      update c_merch_cust_account cmca
         set cmca.credit_amt = cmca.credit_amt + p_amt
       where 1 = 1
         and exists
       (SELECT 1
                FROM om_order_headers_all ooha
               where cmca.merch_cust_id = ooha.merch_cust_id
                 and cmca.organization_id = ooha.organization_id
                 and ooha.id = p_order_id);
    end if;
  end;
  /*=================================
  name:update_inv
  description；更新库存
  author:hhnz-skevin 2017-02-16
  ===================================*/
  procedure update_inv(p_cust_type    in varchar2,
                       p_material_id  in varchar2,
                       p_num          in number,
                       p_factory_code in varchar2) is
  begin
    update crm_virtual_warehouse cvw
       set cvw.amt        = cvw.amt + p_num,
           cvw.frozen_amt = cvw.frozen_amt - p_num
     where 1 = 1
       and cvw.factory_code = p_factory_code
       and cvw.cust_type = p_cust_type
       and cvw.material_id = p_material_id;
  end;
   /*=================================
  name:close_unsap_order
  description；已发货调拨单关闭订单
  author:hhnz-skevin 2017-02-16
  ===================================*/
   procedure close_shipped_order(p_order_id in number ,p_result out varchar2) as
   cursor line_datas is
      SELECT oos.material_id, oos.num, ooha.rdc_code, cso.sap_id
        FROM om_order_spilts        oos,
             om_order_headers_all   ooha,
             crm_sales_organization cso
       where 1 = 1
         and oos.header_id = ooha.id
         and ooha.organization_id = cso.id
         and oos.header_id = p_order_id;
    v_cash_amt   number; --现金
    v_credit_amt number; --授信
    v_bond_amt   number; --保证金额度
    v_order_amt  number := 0; --订单金额
    v_cust_type  varchar2(4);
  begin
  
    --获取客户资金余额
    SELECT cmca.cash_amt, cmca.credit_amt, cmca.bond_amt
      into v_cash_amt, v_credit_amt, v_bond_amt
      FROM c_merch_cust_account cmca, om_order_headers_all ooha
     where cmca.merch_cust_id = ooha.merch_cust_id
       and ooha.id = p_order_id;
    --获取订单金额
  
    SELECT sum(oos.amt)
      into v_order_amt
      FROM om_order_spilts oos
     where oos.header_id = p_order_id;
    --判断授信额度是否等于保证金金额
    if v_credit_amt = v_bond_amt then
      --授信金额等于保证金金额
      --elsif v_credit_amt < v_contract_credit_amt then
      update_merch_amt('1', v_order_amt, p_order_id);
    elsif v_credit_amt < v_bond_amt then
      --授信金额小于保证金金额
      if v_bond_amt - v_credit_amt < v_order_amt then
        --订单金额大于已用授信金额
        update_merch_amt('3', v_bond_amt - v_credit_amt, p_order_id);
        update_merch_amt('1',
                         v_order_amt - (v_bond_amt - v_credit_amt),
                         p_order_id);
      else
        --订单金额小于已用金额
        update_merch_amt('3', v_order_amt, p_order_id);
      end if;
    end if;
    begin
      SELECT cmcb.cust_type
        into v_cust_type
        FROM c_merch_cust_base cmcb, Om_Order_Headers_All ooha
       where cmcb.id = ooha.merch_cust_id
         and ooha.id = p_order_id;
    end;
    --更新库存
    if v_cust_type = '7' then
      for rec in line_datas loop
        update_shipeed_inv(p_cust_type    => rec.rdc_code,
                   p_material_id  => rec.material_id,
                   p_num          => rec.num,
                   p_factory_code => rec.sap_id);
      end loop;
    end if;
    --更新订单状态
    update om_order_headers_all ooha
       set ooha.states = '10'
     where ooha.id = p_order_id;
    p_result:= '关闭订单成功';
    commit;
  end;
  /*=================================
  name:update_shipeed_inv
  description；更新已发货库存
  author:hhnz-skevin 2017-02-16
  ===================================*/
  procedure update_shipeed_inv(p_cust_type    in varchar2,
                       p_material_id  in varchar2,
                       p_num          in number,
                       p_factory_code in varchar2) is
  begin
    update crm_virtual_warehouse cvw
       set cvw.amt        = cvw.amt + p_num
     where 1 = 1
       and cvw.factory_code = p_factory_code
       and cvw.cust_type = p_cust_type
       and cvw.material_id = p_material_id;
  end;
end CRM_TRANSFER_ORDER_COLASE;
