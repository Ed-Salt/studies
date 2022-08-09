#1
select *
from Staff
where position='Assistant'
or position='Supervisor';

#2
select propertyNo as 'property number', 
concat(street, ', ', city, ', ', postcode) as 'address', 
propType as 'type', 
rooms as 'number of rooms',
rent
from PropertyForRent
where branchNo='B003';

#3
select *
from PrivateOwner
where address like '%Glasgow%';

#4
select *
from Client c, Viewing v
where v.clientNo=c.clientNo
group by v.clientNo having count(v.clientNo) > 1;

#5
select propertyNo,
street,
city,
postcode,
propType,
rooms,
concat('£', format((rent*12), 2)) as 'yearly rent',
ownerNo,
staffNo,
branchNo
from PropertyForRent;

#6
select *
from Viewing
where not comment is null
order by viewDate desc, clientNo;

#7
select min(rooms) as 'min',
round(avg(rooms), 1) as 'avg',
max(rooms) as 'max'
from PropertyForRent;

#8
select min(DOB) as 'oldest',
max(DOB) as 'youngest'
from Staff;

#9
select rooms,
count(*) as 'no properties',
format(avg(rent), 2) as 'avg rent'
from PropertyForRent
where rooms <= 5
group by rooms;

#10
select branchNo,
ownerNo,
count(ownerNo) as 'no properties'
from PropertyForRent
group by branchNo, ownerNo
having count(ownerNo) > 1;
