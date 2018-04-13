create or replace view v_process as select arp.name_ as def_name, ahp.*
from act_hi_procinst ahp
join act_re_procdef arp on arp.id_ = ahp.proc_def_id_;

create or replace view v_task as select arp.name_ as def_name,ahp.start_user_id_ as start_user,aht.*
from act_hi_taskinst aht
join act_hi_procinst ahp on ahp.proc_inst_id_ = aht.proc_inst_id_
join act_re_procdef arp on arp.id_ = aht.proc_def_id_;
commit;