create or replace procedure jobs_task_main Authid Current_User is
  cursor tasks is
    SELECT ctm.*
      FROM crm_task_main ctm
     where ctm.task_type = '2'
       and ctm.states = '1';
   log_id number ;
   bdate  date;
   edate  date;
   states varchar2(4);
   p_result varchar2(300);
begin
  /*begin
   Execute Immediate 'drop sequence seq_order_headers_id';
   Execute Immediate 'create sequence seq_order_headers_id minvalue 1 maxvalue 999999 start with 1 increment by 1 cache 20';
  end;*/
  for task in tasks loop
    states :='3';
    bdate :=sysdate;
    begin
    log_id:= SEQ_TASK_LOG_ID.nextval;

    begin
      Execute Immediate 'begin  '||task.TASK_DESCRIPTION||'(:a);  end;' using out p_result ;
      exception when others then
       states :='2';
       p_result :='调用存储过程出错';
    end;
    insert into crm_task_log
      (id, task_main_id, task_name, bdate,edate, states,remark)
    values
      (log_id, task.id, task.TASK_SYSTEM, bdate,sysdate, states,p_result);
    commit;
   end;
  end loop;
end;
