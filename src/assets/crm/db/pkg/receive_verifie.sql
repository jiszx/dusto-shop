create or replace procedure RECEIVE_VERIFIE(p_org_id in varchar2,
                                            p_result out varchar2) is
  --负数发票信息
  cursor negative_invoices is
    SELECT oi.*, cmcb.id merchid
      FROM om_invoice oi, c_merch_cust_base cmcb
     where cmcb.sap_customer_id = oi.customer_id
       and oi.writeoff_invoice is not null
       and cmcb.organization_id = p_org_id
       and oi.total_price < 0
       and not exists (SELECT 1
              FROM c_merch_receive_verifie cmrv
             where cmrv.invoice_id = oi.id
               and cmrv.invoice_no = oi.invoice_no);

  cursor invoices is
    SELECT oi.*,
           cmcb.id merchid,
           (SELECT nvl(sum(cmrv.verifie_amt), 0)
              FROM c_merch_receive_verifie cmrv
             where cmrv.invoice_id = oi.id
               and cmrv.invoice_no = oi.invoice_no) as verifieAmt,
           (oi.total_price + oi.total_tax)*100 as amt
      FROM om_invoice oi, c_merch_cust_base cmcb
     where cmcb.sap_customer_id = oi.customer_id
       and oi.writeoff_invoice is null
       and cmcb.organization_id = 'T0101'
       and oi.total_price > 0;
  invoice      om_invoice%rowtype;
  unverifieamt number;
  flag  varchar2(4) :='1';
begin
  begin
    for rec in negative_invoices loop
      --获取负数发票对应的正数发票
      begin
        SELECT oi.*
          into invoice
          FROM om_invoice oi
         where oi.invoice_no = rec.writeoff_invoice;
      exception
        when others then
          invoice := null;
          flag :='0';
      end;
      if flag ='1' then
        --添加核销信息
        insert into c_merch_receive_verifie
          (id,
           VERIFIED_ID,
           INVOICE_NO,
           VERIFIE_AMT,
           INVOICE_ID,
           MERCH_CUST_ID,
           ORGANIZATION_ID,
           type)
        values
          (seq_merch_verifie_id.nextval,
           rec.id,
           invoice.invoice_no,
           abs(rec.total_price + rec.total_tax)*100,
           invoice.id,
           rec.merchid,
           p_org_id,
           '2');
        insert into c_merch_receive_verifie
          (id,
           VERIFIED_ID,
           INVOICE_NO,
           VERIFIE_AMT,
           INVOICE_ID,
           MERCH_CUST_ID,
           ORGANIZATION_ID,
           type)
        values
          (seq_merch_verifie_id.nextval,
           invoice.id,
           rec.invoice_no,
           (rec.total_price + rec.total_tax)*100,
           rec.id,
           rec.merchid,
           p_org_id,
           '2');
      end if;
    end loop;
    commit;
    for invoice in invoices loop
      --应收发票金额大于已核销金额，存在未核销金额
      if invoice.amt > invoice.verifieamt then
        --计算未核销金额
        unverifieamt := invoice.amt - invoice.verifieamt;
        if unverifieamt = 0 then
          exit;
        end if;
        --获取对应的上账记录
        for upaccount in (SELECT cmu.*
                            FROM c_merch_upaccount cmu
                           where cmu.merch_cus_id = invoice.merchid
                             and cmu.organization_id = p_org_id
                             and cmu.states = '5') loop
          if unverifieamt = 0 then
            exit;
          end if;
          if unverifieamt <= upaccount.unrecevice_amt then
            --未核销金额小于收款未核销金额
            insert into c_merch_receive_verifie
              (id,
               VERIFIED_ID,
               INVOICE_NO,
               VERIFIE_AMT,
               INVOICE_ID,
               MERCH_CUST_ID,
               ORGANIZATION_ID,
               type)
            values
              (seq_merch_verifie_id.nextval,
               upaccount.id,
               invoice.invoice_no,
               unverifieamt,
               invoice.id,
               invoice.merchid,
               p_org_id,
               '1');
            update c_merch_upaccount cmu
               set cmu.recevice_amt   = cmu.recevice_amt + unverifieamt,
                   cmu.unrecevice_amt = cmu.unrecevice_amt - unverifieamt
             where cmu.id = upaccount.id;
             commit;
            exit;
          else
            --未核销金额大于上账金额
            insert into c_merch_receive_verifie
              (id,
               VERIFIED_ID,
               INVOICE_NO,
               VERIFIE_AMT,
               INVOICE_ID,
               MERCH_CUST_ID,
               ORGANIZATION_ID,
               type)
            values
              (seq_merch_verifie_id.nextval,
               upaccount.id,
               invoice.invoice_no,
               upaccount.unrecevice_amt,
               invoice.id,
               invoice.merchid,
               p_org_id,
               '1');
            update c_merch_upaccount cmu
               set cmu.recevice_amt = cmu.pay_amt, cmu.unrecevice_amt = 0
             where cmu.id = upaccount.id;
            unverifieamt := unverifieamt - upaccount.pay_amt;
            commit;
          end if;
        end loop;
      end if;
    end loop;
    commit;
    p_result := '核销成功';
    return;
  exception
    when others then
      p_result := '核销失败';
      return;
  end;
end RECEIVE_VERIFIE;
