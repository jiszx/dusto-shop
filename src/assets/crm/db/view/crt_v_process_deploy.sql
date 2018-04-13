create view v_process_deploy as
select act_re_procdef.id_ as id,act_re_procdef.deployment_id_ as deploy_id,act_re_procdef.key_ as pkey,act_re_procdef.name_ as name,act_re_procdef.version_ as version,act_re_deployment.name_  as deploy_des
from act_re_procdef
join act_re_deployment on act_re_deployment.id_ = act_re_procdef.deployment_id_