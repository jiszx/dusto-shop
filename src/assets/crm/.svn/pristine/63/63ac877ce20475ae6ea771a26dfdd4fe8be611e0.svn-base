create or replace package CRM_MERCH_CUST_BASE_PKG is

  procedure change_org(p_merch_id    in number,
                       p_old_org_id  in varchar2,
                       p_new_org_id  in varchar2,
                       p_change_type in varchar2,
                       p_station_id  in number,
                       p_salesrep_id in number,
                       p_result      out varchar2);
  function do_change_org(p_merch_id    in number,
                         p_old_org_id  in varchar2,
                         p_new_org_id  in varchar2,
                         p_change_type in varchar2,
                         p_station_id  in number,
                         p_salesrep_id in number) return varchar2;
  procedure change_states(p_merch_cust_id in number,
                          p_states        in varchar2,
                          p_reuslt        out varchar2);

  procedure do_activation_merch(p_merch_cust_id in number,
                                p_reuslt        out varchar2);

  procedure update_merch_balances(p_merch_cust_id in number);
end CRM_MERCH_CUST_BASE_PKG;


create or replace package body crm_merch_cust_base_pkg is
  /*========================
  name :change_org
  type: procedure
  descirption:变更客户销售组织
  author:add by hhnz-chenkai 2017-01-20
  =========================*/
  procedure change_org(p_merch_id    in number,
                       p_old_org_id  in varchar2,
                       p_new_org_id  in varchar2,
                       p_change_type in varchar2,
                       p_station_id  in number,
                       p_salesrep_id in number,
                       p_result      out varchar2) is
    v_flag varchar2(4) := 'E';
  begin
    --判断新销售组织下是否存在同名客户
    begin
      SELECT distinct 'S'
        into v_flag
        FROM c_merch_cust_base cmcb
       where cmcb.organization_id = p_new_org_id
         and exists
       (SELECT 1
                FROM c_merch_cust_base ocmcb
               where ocmcb.name = cmcb.name
                 and ocmcb.business_license = cmcb.business_license
                 and ocmcb.id = p_merch_id
                 and ocmcb.organization_id = p_old_org_id);
    exception
      when no_data_found then
        v_flag := 'E';
      when others then
        p_result := '验证客户数据失败';
        return;
    end;
    --判断仓储服务商或者合作仓储服务商下的零售商是否存在订单
    if v_flag = 'E' then
      p_result := do_change_org(p_merch_id,
                                p_old_org_id,
                                p_new_org_id,
                                p_change_type,
                                p_station_id,
                                p_salesrep_id);
    else
      p_result := '新销售组织下已经存在同名并且税号相同的客户';
    end if;
  end;
  /*===================================
  name:do_change
  description:切换销售组织
  author:hhnz-skevin 2017-02-21
  ===================================*/
  function do_change_org(p_merch_id    in number,
                         p_old_org_id  in varchar2,
                         p_new_org_id  in varchar2,
                         p_change_type in varchar2,
                         p_station_id  in number,
                         p_salesrep_id in number) return varchar2 is
    v_merch_id         number := seq_merch_base_id.nextval;
    v_order_flag       varchar2(4);
    v_account_log_flag varchar2(4);
    v_cash_flag        varchar2(4);
 
    cursor d_data is
      SELECT *
        FROM c_merch_cust_distribution cmcd
       where cmcd.merch_cust_id = p_merch_id;
  begin
  
    --验证原销售组织下是否存在销售订单，
    begin
      SELECT distinct 'S'
        into v_order_flag
        FROM om_order_headers_all ooha
       where 1 = 1
         and ((ooha.merch_cust_id = p_merch_id) or
             (ooha.ship_id = p_merch_id));
    exception
      when no_data_found then
        v_order_flag := 'E';
      when others then
        v_order_flag := 'S';
    end;
    --判断资金日志表中是否存在数据
    begin
      SELECT distinct 'S'
        into v_account_log_flag
        FROM c_merch_cust_account_log cmcal
       where cmcal.merch_cust_id = p_merch_id
         and cmcal.organization_id = p_old_org_id;
    exception
      when no_data_found then
        v_account_log_flag := 'E';
      when others then
        v_account_log_flag := 'S';
    end;
  
    --判断是否存在现金上账记录
    begin
      SELECT distinct 'S'
        into v_cash_flag
        FROM c_merch_upaccount cmu
       where cmu.merch_cus_id = p_merch_id;
    exception
      when no_data_found then
        v_cash_flag := 'E';
      when others then
        v_cash_flag := 'S';
    end;
    
    if v_order_flag = 'E' and v_cash_flag = 'E' and v_account_log_flag = 'E' then
      --客户没有销售订单，账户余额为0
    
      --客户主表
      update c_merch_cust_base cmcb
         set cmcb.organization_id = p_new_org_id,
             cmcb.create_oid      = p_salesrep_id
       where 1 = 1
         and cmcb.id = p_merch_id
         and cmcb.organization_id = p_old_org_id;
      --送达方信息
      update c_merch_cust_base cmcb
         set cmcb.organization_id = p_new_org_id
       where 1 = 1
         and cmcb.pid = p_merch_id
         and cmcb.cust_type = '6'
         and cmcb.organization_id = p_old_org_id;
      --账户期间表
      update c_merch_cust_balances cmcb
         set cmcb.organization_id = p_new_org_id
       where 1 = 1
         and cmcb.merch_cust_id = p_merch_id
         and cmcb.organization_id = p_old_org_id;
    
      --日志表
      update c_merch_cust_account_log cmcal
         set cmcal.organization_id = p_new_org_id
       where 1 = 1
         and cmcal.merch_cust_id = p_merch_id
         and cmcal.organization_id = p_old_org_id;
    
      --账户表
      update c_merch_cust_account cmca
         set cmca.organization_id = p_new_org_id
       where cmca.merch_cust_id = p_merch_id
         and cmca.organization_id = p_old_org_id;
    
      --合同表
      update c_merch_cust_contract cmcc
         set cmcc.organization_id = p_new_org_id
       where cmcc.merch_cust_id = p_merch_id
         and cmcc.organization_id = p_old_org_id;
    
      --产品表
      update c_merch_cust_product cmcp
         set cmcp.organization_id = p_new_org_id
       where cmcp.merch_cust_id = p_merch_id
         and cmcp.organization_id = p_old_org_id;
      --岗位表
      update c_merch_cust_station cmcs
         set cmcs.station_id = p_station_id
       where cmcs.merch_cust_id = p_merch_id;
    
      --调整表
      update c_merch_cust_adjust cmca
         set cmca.organization_id = p_new_org_id
       where 1 = 1
         and cmca.merch_cust_id = p_merch_id
         and cmca.organization_id = p_old_org_id;
    
      --到站信息
      update c_merch_cust_distribution cmcd
         set cmcd.organization_id = p_new_org_id
       where cmcd.merch_cust_id = p_merch_id
         and cmcd.organization_id = p_old_org_id;
    
      --应收账款表
      update c_merch_ar_balance cmab
         set cmab.organization_id = p_new_org_id
       where cmab.merch_cust_id = p_merch_id
         and cmab.organization_id = p_old_org_id;
    else
      --插入客户
      insert into c_merch_cust_base
        (ID,
         NAME,
         ABBR_NAME,
         PROV_ID,
         CITY_ID,
         COUNTY_ID,
         ADDRESS,
         LP_NO,
         LP_NAME,
         ZIP_CODE,
         REG_ADDR,
         TEL,
         CREATE_TS,
         CREATE_OID,
         UPDATE_TS,
         UPDATE_OID,
         STATES,
         SAP_CUSTOMER_ID,
         CHANNEL_ID,
         CUST_TYPE,
         IS_ATTACHMENT,
         BUSINESS_LICENSE,
         CONTACT_NAME,
         CONTACT_TEL,
         ORGANIZATION_ID,
         PID,
         INVOICE_NAME,
         INVOICE_TAX_NUM,
         INVOICE_ADDRESS,
         INVOICE_ACCOUNT,
         INVOICE_TEL,
         INVOICE_BANK_NAME,
         INVOICE_ACCOUNT_NAME,
         OPENING_TYPE,
         OPENING_REASON,
         OPENING_MERCHANT,
         OPENING_CLOSE_TS,
         EXPAND_KA_PLACE,
         EXPAND_BC_PLACE,
         EXPAND_CIRCULATE_PLACE,
         EXPAND_FACTORY_PLACE,
         EXPAND_SCHOOL_PLACE,
         EXPAND_RETAIL_PLACE,
         EXPAND_AREA_PEOPLES,
         EXPAND_AREA_VOLUME,
         EXPAND_BUSINES_VOLUME,
         EXPAND_SPENT_MAMT,
         EXPAND_SPENT_FAMT,
         CONTEXT_SALES_YEAR,
         CONTEXT_BUSINESS_RATIO,
         CONTEXT_INVESTMENT,
         CONTEXT_OPERATE_CAPITAL,
         CONTEXT_KA_NUM,
         CONTEXT_BC_NUM,
         CONTEXT_WHOLESALERS_NUM,
         CONTEXT_RETAIL_NUM,
         CONTEXT_FARMERS_NUM,
         CONTEXT_RESTAURANT_NUM,
         CONTEXT_OTHERS_NUM,
         CONTEXT_SALESMAN_NUM,
         CONTEXT_LOGISTICS_NUM,
         CONTEXT_TRUCK_NUM,
         CONTEXT_LORRY_NUM,
         CONTEXT_DEPOT,
         PROV_NAME,
         CITY_NAME,
         COUNTY_NAME,
         PROCESS_ID,
         IS_INVOICE,
         PLAN_AREA,
         PLAN_BRAND,
         PLAN_CATEGORY,
         MIN_ORDER,
         CODE)
        (SELECT v_merch_id,
                NAME,
                ABBR_NAME,
                PROV_ID,
                CITY_ID,
                COUNTY_ID,
                ADDRESS,
                LP_NO,
                LP_NAME,
                ZIP_CODE,
                REG_ADDR,
                TEL,
                sysdate,
                CREATE_OID,
                null,
                null,
                '1',
                case
                  when cust_type = p_change_type and p_change_type ='7' then
                   'X' || SAP_CUSTOMER_ID
                  else
                   null
                end,
                CHANNEL_ID,
                p_change_type,
                IS_ATTACHMENT,
                BUSINESS_LICENSE,
                CONTACT_NAME,
                CONTACT_TEL,
                p_new_org_id,
                DECODE(p_change_type, '7', NULL, '70', pid),
                INVOICE_NAME,
                INVOICE_TAX_NUM,
                INVOICE_ADDRESS,
                INVOICE_ACCOUNT,
                INVOICE_TEL,
                INVOICE_BANK_NAME,
                INVOICE_ACCOUNT_NAME,
                OPENING_TYPE,
                OPENING_REASON,
                OPENING_MERCHANT,
                OPENING_CLOSE_TS,
                EXPAND_KA_PLACE,
                EXPAND_BC_PLACE,
                EXPAND_CIRCULATE_PLACE,
                EXPAND_FACTORY_PLACE,
                EXPAND_SCHOOL_PLACE,
                EXPAND_RETAIL_PLACE,
                EXPAND_AREA_PEOPLES,
                EXPAND_AREA_VOLUME,
                EXPAND_BUSINES_VOLUME,
                EXPAND_SPENT_MAMT,
                EXPAND_SPENT_FAMT,
                CONTEXT_SALES_YEAR,
                CONTEXT_BUSINESS_RATIO,
                CONTEXT_INVESTMENT,
                CONTEXT_OPERATE_CAPITAL,
                CONTEXT_KA_NUM,
                CONTEXT_BC_NUM,
                CONTEXT_WHOLESALERS_NUM,
                CONTEXT_RETAIL_NUM,
                CONTEXT_FARMERS_NUM,
                CONTEXT_RESTAURANT_NUM,
                CONTEXT_OTHERS_NUM,
                CONTEXT_SALESMAN_NUM,
                CONTEXT_LOGISTICS_NUM,
                CONTEXT_TRUCK_NUM,
                CONTEXT_LORRY_NUM,
                CONTEXT_DEPOT,
                PROV_NAME,
                CITY_NAME,
                COUNTY_NAME,
                NULL,
                IS_INVOICE,
                PLAN_AREA,
                PLAN_BRAND,
                PLAN_CATEGORY,
                MIN_ORDER,
                CODE
           FROM c_merch_cust_base
          where id = p_merch_id
            and organization_id = p_old_org_id);
    
      --插入到站信息
      for rec in d_data loop
        insert into c_merch_cust_distribution
          (ID,
           MERCH_CUST_ID,
           NAME,
           STATES,
           ORGANIZATION_ID,
           LOGISTICS,
           SITE,
           PROV_ID,
           CITY_ID,
           ADDRESS,
           CONTACTER,
           MOBILE,
           CREATE_TS,
           CREATE_OID)
          (SELECT seq_merch_distribution_id.nextval,
                  v_merch_id,
                  rec.name,
                  rec.states,
                  p_new_org_id,
                  rec.logistics,
                  rec.site,
                  rec.prov_id,
                  rec.city_id,
                  rec.address,
                  rec.contacter,
                  rec.mobile,
                  sysdate,
                  rec.create_oid
             FROM dual);
      end loop;
      commit;
      --赋值岗位信息
      insert into c_merch_cust_station
        (ID, MERCH_CUST_ID, STATION_ID, STATES, CREATE_TS)
        (SELECT seq_merch_cust_station_id.nextval,
                v_merch_id,
                p_station_id,
                '1',
                sysdate
           FROM dual);
      commit;
    
    end if;
    return '变更销售主题成功';
  end;
  /*========================
  name :change_states
  type: procedure
  descirption:变更客户状态
  author:add by hhnz-chenkai 2017-01-20
  =========================*/
  procedure change_states(p_merch_cust_id in number,
                          p_states        in varchar2,
                          p_reuslt        out varchar2) as
  
  begin
    if p_states = '3' then
      --激活客户
      do_activation_merch(p_merch_cust_id, p_reuslt);
    elsif p_states = '4' then
      --失效客户
      begin
        update c_merch_cust_base cmcb
           set cmcb.states = p_states
         where cmcb.id = p_merch_cust_id;
      exception
        when others then
          p_reuslt := '休眠客户失败，请联系管理人员';
      end;
      commit;
      p_reuslt := '休眠客户成功';
    end if;
  end;
  /*========================
  name :do_activation_merch
  type: procedure
  descirption:激活客户
  author:add by hhnz-chenkai 2017-01-20
  =========================*/
  procedure do_activation_merch(p_merch_cust_id in number,
                                p_reuslt        out varchar2) is
  
  begin
    begin
      update_merch_balances(p_merch_cust_id);
    exception
      when others then
        p_reuslt := '处理客户账户数据错误，请相关人员';
        return;
    end;
    update c_merch_cust_base cmcb
       set cmcb.states = '3'
     where cmcb.id = p_merch_cust_id;
    p_reuslt := '激活客户成功';
  exception
    when others then
      p_reuslt := '激活客户失败';
  end;

  procedure update_merch_balances(p_merch_cust_id in number) as
    v_flag       varchar2(4);
    amt          number := 0;
    v_max_period varchar2(10);
    v_number     number;
  
    --获取客户最大账户数据（销售组织，账户类型）
    cursor balances_data is
      SELECT max(cmcb.period) keep(dense_rank first order by cmcb.period desc) period,
             cmcb.organization_id,
             cmcb.merch_cust_id,
             cmcb.account_type
        FROM c_merch_cust_balances cmcb
       where cmcb.merch_cust_id = p_merch_cust_id
       group by cmcb.organization_id, cmcb.merch_cust_id, cmcb.account_type;
  begin
    --判断客户当前月是否存在账户数据
    begin
      SELECT distinct 'S'
        into v_flag
        FROM c_merch_cust_balances cmcb
       where cmcb.merch_cust_id = p_merch_cust_id
         and cmcb.period = to_char(sysdate, 'yyyy-mm');
    exception
      when no_data_found then
        v_flag := 'E';
      when others then
        v_flag := 'N';
    end;
    if v_flag = 'E' then
      --客户不存在当月账户数据
    
      for rec in balances_data loop
        v_max_period := '';
        v_number     := 0;
        --获取历史数据最大月份
        SELECT max(cmcb.period) keep(dense_rank first order by cmcb.period desc)
          into v_max_period
          FROM c_merch_cust_balances cmcb
         where cmcb.merch_cust_id = p_merch_cust_id
           and cmcb.account_type = rec.account_type;
      
        --当前时间和月份差
        SELECT trunc(months_between(sysdate,
                                    to_date(v_max_period, 'yyyy-mm')))
          into v_number
          FROM dual;
        if v_number = 0 then
          continue;
        end if;
        --计算期末余额
        select nvl(cmcb.ytd, 0) + nvl(cmcb.d_amt, 0) - nvl(cmcb.c_amt, 0)
          into amt
          from c_merch_cust_balances cmcb
         where cmcb.merch_cust_id = rec.merch_cust_id
           and cmcb.period = rec.period
           and cmcb.account_type = rec.account_type;
        --更新期末数据
        begin
          update c_merch_cust_balances cmcb
             set cmcb.ptd = amt
           where cmcb.merch_cust_id = rec.merch_cust_id
             and cmcb.account_type = rec.account_type
             and cmcb.period = rec.period;
        end;
        for i in 1 .. v_number loop
          --补全期间数据
          if i != v_number then
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
               rec.organization_id,
               rec.merch_cust_id,
               rec.account_type,
               amt,
               0,
               0,
               amt,
               to_char(add_months(to_date(rec.period, 'yyyy-mm'), i),
                       'yyyy-mm'));
          else
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
               rec.organization_id,
               rec.merch_cust_id,
               rec.account_type,
               amt,
               0,
               0,
               0,
               to_char(add_months(to_date(rec.period, 'yyyy-mm'), i),
                       'yyyy-mm'));
          end if;
        end loop;
      end loop;
      commit;
    end if;
  end;
end CRM_MERCH_CUST_BASE_PKG;
