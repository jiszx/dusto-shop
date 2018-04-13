create or replace type str_split is table of varchar2 (4000);
create or replace function splitstr(p_string in varchar2, p_delimiter in varchar2)
   return str_split 
   pipelined
as
   v_length   number := length(p_string);
   v_start    number := 1;
   v_index    number;
begin
   while(v_start <= v_length)
   loop
       v_index := instr(p_string, p_delimiter, v_start);

       if v_index = 0
       then
           pipe row(substr(p_string, v_start));
           v_start := v_length + 1;
       else
           pipe row(substr(p_string, v_start, v_index - v_start));
           v_start := v_index + 1;
       end if;
   end loop;

   return;
end splitstr;