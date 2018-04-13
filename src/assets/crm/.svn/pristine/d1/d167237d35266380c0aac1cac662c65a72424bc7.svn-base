create or replace package CRM_MERCH_PRODUC_BALANCE_PKG is

  procedure mian(p_result out varchar2);
  function vlidate_balances(p_merch_cust_id   in number,
                            p_material_id     in varchar2,
                            p_organization_id in varchar2,
                            p_ptd             in number) return varchar2;
  procedure update_balances(p_merch_cust_id in number,
                            p_material_id   in varchar2,
                            p_result        out varchar2);
end CRM_MERCH_PRODUC_BALANCE_PKG;


create or replace package body crm_merch_produc_balance_pkg is

  /*=================================
  name：main
  type；procedure
  description:仓储库存期间数据更新
  author:add by hhnz-chenkai 2016-08-29
  ===================================*/
  procedure mian(p_result out varchar2) is
  
    --获取库存账款数据
    cursor balances is
      SELECT max(cmcp.period) keep(dense_rank first order by cmcp.period desc) period,
             cmcp.merch_cust_id,
             cmcp.organization_id,
             cmcp.material_id
        FROM c_merch_cust_product_balances cmcp
       where 1 = 1
       group by cmcp.merch_cust_id, cmcp.organization_id, cmcp.material_id;
    sysday number;
    v_dNum number;
    v_cNum number;
    v_ptd  number;
    v_flag varchar2(4);
  begin
    --判断当前时间是否为月初第一天
    begin
      select extract(day from sysdate) into sysday from dual;
      if sysday != 1 then
        p_result := '应收账款更新在每月第一天凌晨执行';
        return;
      end if;
    end;
    for rec in balances loop
      --更新期间表本期增加，本期减少
      SELECT sum(nvl(cmcpl.d_num, 0)), sum(nvl(cmcpl.c_num, 0))
        into v_dNum, v_cNum
        FROM c_merch_cust_product_log cmcpl
       where cmcpl.merch_cust_id = rec.merch_cust_id
         and cmcpl.material_id = rec.material_id
         and cmcpl.period = rec.period;
    
      update c_merch_cust_product_balances cmcpb
         set cmcpb.d_num = v_dNum, cmcpb.c_num = v_cNum
       where cmcpb.merch_cust_id = rec.merch_cust_id
         and cmcpb.organization_id = rec.organization_id
         and cmcpb.period = rec.period
         and cmcpb.material_id = rec.material_id;
    
      SELECT a.ytd + v_dNum - v_cNum
        into v_ptd
        FROM c_merch_cust_product_balances a
       where a.merch_cust_id = rec.merch_cust_id
         and a.organization_id = rec.organization_id
         and a.period = rec.period
         and a.material_id = rec.material_id;
    
      v_flag := vlidate_balances(rec.merch_cust_id,
                                 rec.material_id,
                                 rec.organization_id,
                                 v_ptd);
      if v_flag = 'E' then
        --当前月已经生成数据
        update c_merch_cust_product_balances cmcpb
           set cmcpb.ytd = v_ptd
         where cmcpb.merch_cust_id = rec.merch_cust_id
           and cmcpb.material_id = rec.material_id
           and cmcpb.organization_id = rec.organization_id
           and cmcpb.period = to_char(sysdate, 'yyyy-MM');
      elsif v_flag = 'S' then
        insert into c_merch_cust_product_balances
          (ID,
           MERCH_CUST_ID,
           PERIOD,
           YTD,
           D_NUM,
           C_NUM,
           PTD,
           ORGANIZATION_ID,
           STATES,
           CONTRACT_ID,
           MATERIAL_ID)
        values
          (seq_merch_inv_balance_id.nextval,
           rec.merch_cust_id,
           to_char(sysdate, 'yyyy-MM'),
           v_ptd,
           0,
           0,
           0,
           rec.organization_id,
           '1',
           null,
           rec.material_id);
      elsif v_flag = 'N' then
        update c_merch_cust_product_balances cmcpb
           set cmcpb.ytd = v_ptd
         where cmcpb.merch_cust_id = rec.merch_cust_id
           and cmcpb.material_id = rec.material_id
           and cmcpb.organization_id = rec.organization_id
           and cmcpb.period = rec.period;
      end if;
      commit;
    end loop;
  end;

  /*=================================
  name：vlidate_balances
  type；function
  description:验证数据
  author:add by hhnz-chenkai 2017-02-27
  ===================================*/
  function vlidate_balances(p_merch_cust_id   in number,
                            p_material_id     in varchar2,
                            p_organization_id in varchar2,
                            p_ptd             in number) return varchar2 is
    --E当前月已经新增，N客户失效，ptd等于0，S当前月不存在，客户有效或者ptd不等于0
    v_flag         varchar2(4);
    v_merch_states varchar2(4);
  begin
    --验证当前月数据是否已经生成
    begin
      SELECT nvl('S', 'E')
        into v_flag
        FROM c_merch_cust_product_balances cmcp
       where cmcp.merch_cust_id = p_merch_cust_id
         and cmcp.material_id = p_material_id
         and cmcp.organization_id = p_organization_id
         and cmcp.period = to_char(sysdate, 'yyyy-MM');
    exception
      when no_data_found then
        v_flag := 'E';
      when others then
        v_flag := 'N';
    end;
    if v_flag = 'S' then
      return 'E';
    end if;
  
    --期间库存数据为0判断客户是否生效
    if p_ptd = 0 then
      begin
        SELECT 'S'
          into v_merch_states
          FROM c_merch_cust_base cmcb
         where cmcb.id = p_merch_cust_id
           and cmcb.organization_id = p_organization_id
           and cmcb.states = '3';
      exception
        when no_data_found then
          v_merch_states := 'E';
        when others then
          v_merch_states := 'S';
      end;
    else
      return 'S';
    end if;
    if v_merch_states = 'S' then
      --客户有效并且当前月不存在
      return 'S';
    else
      --客户失效或者不存在
      return 'N';
    end if;
  end;
  procedure update_balances(p_merch_cust_id in number,
                            p_material_id   in varchar2,
                            p_result        out varchar2) is
    cursor datas is
      SELECT cmcpl.period, sum(cmcpl.d_num) dNum, sum(cmcpl.c_num) cNum
        FROM c_merch_cust_product_log cmcpl
       where 1 = 1
         and (cmcpl.merch_cust_id = p_merch_cust_id or
             cmcpl.attribute1 = p_merch_cust_id)
         and cmcpl.material_id = p_material_id
       group by cmcpl.period
       order by cmcpl.period;
    cursor balances is
      SELECT cmcpb.*
        FROM c_merch_cust_product_balances cmcpb
       where cmcpb.merch_cust_id = p_merch_cust_id
         and cmcpb.material_id = p_material_id
       order by cmcpb.period;
  begin
    for rec in datas loop
      update c_merch_cust_product_balances cmcpb
         set cmcpb.d_num = rec.dNum, cmcpb.c_num = rec.cNum
       where cmcpb.period = rec.period
         and cmcpb.merch_cust_id = p_merch_cust_id
         and cmcpb.material_id = p_material_id;
    end loop;
    commit;
    for balance in balances loop
      update c_merch_cust_product_balances cmcbp
         set cmcbp.ptd = balance.ytd + balance.d_num - balance.c_num
       where cmcbp.id = balance.id
         and cmcbp.merch_cust_id = p_merch_cust_id
         and cmcbp.material_id = p_material_id;
    
      update c_merch_cust_product_balances a
         set a.ytd = balance.ytd + balance.d_num - balance.c_num
       where a.merch_cust_id = p_merch_cust_id
         and a.material_id = p_material_id
         and a.period =
             to_char(add_months(to_date(balance.period, 'yyyy-mm'), 1),
                     'yyyy-mm');
      commit;
    end loop;
    p_result := 'S';
  exception
    when others then
      p_result := 'E';
  end;
end CRM_MERCH_PRODUC_BALANCE_PKG;
