
select branchNo,
sex,
(

(
select sum(salary)
from Staff
where sex = 'F'
and branchNo = 'B003'
)
+
(
select sum(salary)
from Staff
where sex = 'M'
and branchNo = 'B003'
)

) as 'salary total'
from Staff;
