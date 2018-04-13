begin
  sys.dbms_job.submit(job => :job,
                      what => 'begin jobs_task_main();  end ;',
                      next_date => to_date('08-03-2017 00:01:00', 'dd-mm-yyyy hh24:mi:ss'),
                      interval => 'trunc(sysdate)+1+0.0168/24');
  commit;
end;
/