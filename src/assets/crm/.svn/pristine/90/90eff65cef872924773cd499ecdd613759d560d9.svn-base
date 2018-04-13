create or replace package CRM_CUSTOMER_CONTRACT_PKG is

  /*========================
  name :main
  type: procedure
  descirption:销售政策返利主程序
  author:add by hhnz-chenkai 2016-09-13
  ==========================*/
  procedure main(p_result out varchar2);

end CRM_CUSTOMER_CONTRACT_PKG;

create or replace package body CRM_CUSTOMER_CONTRACT_PKG is

  /*========================
  name :main
  type: procedure
  descirption:客户合同主程序
  author:add by hhnz-chenkai 2016-09-13
  ==========================*/
  procedure main(p_result out varchar2) is
    cursor contract is
      SELECT cmcc.*
        FROM c_merch_cust_contract cmcc
       where cmcc.states = '4';
    account    c_merch_cust_account%ROWTYPE;
    amt        number := 0;
    contractId number;
  begin
    begin
      for rec in contract loop
        if sysdate > to_date(rec.contract_edate, 'yyyy-mm-dd') + 0.00001 then
          --当前时间大于合同的截至日期
          --失效合同
          update c_merch_cust_contract cmcc
             set cmcc.states = '5'
           where cmcc.id = rec.id
             and cmcc.merch_cust_id = rec.merch_cust_id
             and cmcc.organization_id = rec.organization_id;
        
          --修改产品表状态   
          update c_merch_cust_product cmcp
             set cmcp.states = '5'
           where cmcp.id = rec.id
             and cmcp.merch_cust_id = rec.merch_cust_id
             and cmcp.organization_id = rec.organization_id;
          
          --激活合同
          update c_merch_cust_contract cmcc2
             set cmcc2.states = '4'
           where cmcc2.states = '6'
             and cmcc2.merch_cust_id = rec.merch_cust_id
             and cmcc2.organization_id = rec.organization_id;
          commit;
          --获取当前生效合同
          begin
            SELECT t.id
              into contractId
              FROM c_merch_cust_contract t
             where t.states = '4'
               and t.merch_cust_id = rec.merch_cust_id
               and t.organization_id = rec.organization_id;
          exception
            when others then
              contractId := 0;
          end;
          if contractId != 0 then
            --插入新的物料信息
            INSERT INTO C_MERCH_CUST_PRODUCT
              (ID,
               MERCH_CUST_ID,
               MATERIAL_ID,
               ORGANIZATION_ID,
               CONTRACT_ID,
               CONTRACT_LINEID,
               CREATE_TS,
               CREATE_OID,
               STATES,
               H_PRICE)
              (SELECT SEQ_MERCH_PRODUCT_ID.NEXTVAL,
                      CMCC.MERCH_CUST_ID,
                      tmcb.sap_id,
                      CMCC.ORGANIZATION_ID,
                      cmccl.contract_id,
                      cmccl.id,
                      SYSDATE,
                      (SELECT CREATE_OID
                         FROM C_MERCH_CUST_CONTRACT
                        WHERE ID = CMCC.ID),
                      '4',
                      (SELECT a.h_price
                         FROM c_merch_cust_product a
                        where a.merch_cust_id = rec.merch_cust_id
                          and a.organization_id = rec.organization_id
                          and a.material_id = tmcb.sap_id
                          and a.id = rec.id)
                 FROM c_merch_cust_contract_lines cmccl,
                      c_merch_cust_contract       cmcc,
                      t_material_base             tmcb
                where 1 = 1
                  and cmcc.id = cmccl.contract_id
                  and tmcb.active = '1'
                  and (cmccl.agent_id = tmcb.category or
                      cmccl.agent_id = tmcb.brand or
                      cmccl.agent_id = tmcb.series or
                      cmccl.agent_id = tmcb.sap_id or
                      cmccl.agent_id = tmcb.i_package)
                  and cmcc.id = contractId);
             commit;
          end if;
        end if;
      end loop;
      
      --修改账户余额信息
      /*for rec in (select *
                    from c_merch_cust_contract cmcc
                   where cmcc.states = '4') loop
        SELECT cmca.*
          into account
          FROM c_merch_cust_account cmca
         where cmca.merch_cust_id = rec.merch_cust_id
           and cmca.organization_id = rec.organization_id;
        account.contract_id         := rec.id;
        amt                         := nvl(account.contract_credit_amt, 0);
        account.contract_credit_amt := rec.credit_amt;
        account.credit_amt          := account.credit_amt +
                                       (rec.credit_amt - amt);
        update c_merch_cust_account cmca
           set cmca.contract_id         = account.contract_id,
               cmca.contract_credit_amt = account.contract_credit_amt,
               cmca.credit_amt          = account.credit_amt
         where cmca.id = account.id;
      end loop;*/
      commit;
    exception
      when others then
        p_result := '客户合同主程序错误';
    end;
    p_result := '运行完成';
  end main;
end CRM_CUSTOMER_CONTRACT_PKG;
