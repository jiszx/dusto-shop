create or replace function combination_price(p_id         in number,
                                             p_orgid in varchar2)
  return number is
  price number :=0;
  cursor lines is
    SELECT cmpl.*
      FROM crm_material_package_lines cmpl
     where cmpl.header_id = p_id;

  amt number := 0;
begin
  for rec in lines loop
    dbms_output.put_line(rec.material_id);
    begin
      SELECT nvl(tmp.price, 100000) * nvl(tmb.attribute6, 1)
        into amt
        FROM (SELECT *
                FROM t_material_price
               where edate + 0.99999 > sysdate
               order by bdate desc) tmp,
             t_material_base tmb
       where tmp.organization_id = p_orgid
         and tmp.material_id = rec.material_id
         and tmp.material_id= tmb.sap_id
         and rownum = 1;
    exception
      when others then
        return 0;
    end;
    price := price + amt;
  end loop;
  dbms_output.put_line(price);
  return price;
end combination_price;
