#1
select branchNo, 
sum(rent) as 'monthly total',
sum(rent*12) as 'yearly total'
from PropertyForRent
group by branchNo
having sum(rent*12) >= 7000;

#2
select b.*,
count(s.branchNo) as 'no staff'
from Branch b, Staff s
where b.branchNo = s.branchNo
group by branchNo
having count(s.branchNo) > 1;

#3
select r.clientNo,
fName,
lName,
dateJoined
from Registration r, Client c
where dateJoined > '2007-04-01'
and r.clientNo = c.clientNo;

#4
select .city, .city
from 
	(
	select distinct propertyNo 
    from PropertyForRent 
	) 
    
	inner join 
    
    (
	select distinct propertyNo
    from PropertyForRent 
	) 
where .city = .city;

#5
select s.staffNo, 
fName, 
lName, 
s.branchNo, 
city
from Staff s, PropertyForRent p
where city = 'London'
and s.staffNo = p.staffNo;

#6
(
select p.propertyNo
from PropertyForRent p, Viewing v
where p.propertyNo not in 
	(
    select propertyNo
    from Viewing
    )
)
union
(
select propertyNo
from PropertyForRent
where rent > 600
)
order by propertyNo asc;

#7
select propertyNo,
rent
from PropertyForRent
where rent > 
(
select max(rent)
from PropertyForRent
where branchNo = 'B003'
);

#8
select staffNo,
salary
from Staff
where salary <= 
(
	select avg(salary)*1.5 
	from Staff
)
and salary >= 
(
	select avg(salary)*0.5 
	from Staff
)
order by salary;

#9
select branchNo,
sex
from Staff 
join
(
select sum(salary)
from Staff
group by sex = 'F'
and branchNo = 'B003'
)
order by branchNo, sex;
#rollup

#10
select concat(fName, ' ', lName) as 'name',
staffNo
from Staff
where branchNo = 'B005'
and (
select *
from Client
);
