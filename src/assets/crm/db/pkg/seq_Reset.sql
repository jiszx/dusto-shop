CREATE OR REPLACE Procedure seq_Reset(v_seqname in varchar2) As
  n    number(10);
  tsql varchar2(100);
Begin
  tsql := 'Select ' || v_seqname || '.nextval From dual';
  Execute Immediate tsql
    into n;

  --如果序列本身是初始状态则不进行数值计算
  if n <> 1 then
    n := - (n - 1);
  end if;

  tsql := 'Alter Sequence ' || v_seqname || ' Increment By ' || n;
  Execute Immediate tsql;
  tsql := 'Select ' || v_seqname || '.nextval From dual';
  Execute Immediate tsql
    into n;
  tsql := 'Alter Sequence ' || v_seqname || ' Increment By 1';
  Execute Immediate tsql;
End seq_Reset;
