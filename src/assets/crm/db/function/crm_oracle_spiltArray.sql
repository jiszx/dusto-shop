create or replace type array_split as object(
v1 VARCHAR2(300), v2 VARCHAR2(300)
);
create or replace type typ_array_target as table of array_split;

CREATE OR REPLACE FUNCTION splitstrArray(p_source_data IN sys_refcursor,p_limit_size  in pls_integer)
  RETURN typ_array_target
  PIPELINED is

  type typ_source_data is table of om_policy_headers%rowtype  index by pls_integer;
  
  r_source_data typ_source_data;
  
  r_target_data  array_split := array_split(null, null);
  
  cursor datas(p_string varchar2) is
    SELECT * FROM table(splitstr(p_string, ','));
BEGIN
  loop
     fetch p_source_data  bulk collect 
        into r_source_data;  
      exit when r_source_data.count = 0;  
      for i in 1 .. r_source_data.count loop
         --dbms_output.put_line(r_source_data(i).id);
         --dbms_output.put_line(r_source_data(i).attribute1);
         for rec in datas(p_string => r_source_data(i).attribute1) loop
            r_target_data.v1 := r_source_data(i).id;
            r_target_data.v2 := rec.column_value;
            pipe row(r_target_data);
         end loop;
      end loop;
  end loop;
  close p_source_data;
  RETURN;
END splitstrArray;
