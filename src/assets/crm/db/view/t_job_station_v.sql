create or replace view t_job_station_v as select
	cso. name orgname,
	ts.id,(select name from CRM_SALES_ORGANIZATION where id=substr(ts.org_id, 0, 3)) || '-' || ts.name name,ts.org_id,ts.job_type,ts.data_view
from
	t_job_station ts,
	crm_sales_organization cso
where
	ts.org_id = cso. id;

create or replace view t_job_user_v as select
	tjs. name stationname,
	tju.job_id,tju.emp_id, te.id,te.name,te.pid,te.is_salesman,te.card_no,te.card_address,te.contact_address,te.contact_tel,te.create_ts,te.entry_ts,te.quit_ts,te.states,te.login_name,te.passwd,te.exp_date,te.lastupdates,te.pwd_error_count,te.login_ts,te.lastloginid,te.email,te.update_ts,te.role_id,te.levels,te.organization_id
from
	t_job_user tju,
	t_employee te,
	t_job_station tjs
where
	te. id = tju.emp_id (+)
and tju.job_id = tjs. id (+);
commit;