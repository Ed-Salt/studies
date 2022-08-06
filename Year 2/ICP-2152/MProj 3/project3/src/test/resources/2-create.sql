CREATE TABLE student(
student_Id varchar(6),
student_name varchar(50),
degree_scheme varchar(50),
PRIMARY KEY (student_Id));

CREATE TABLE staff(
staff_Id varchar(6),
staff_name varchar(50),
staff_grade varchar(50),
PRIMARY KEY (staff_Id));

CREATE TABLE module(
module_Id varchar(5), 
module_name varchar(50), 
credits integer(2),
PRIMARY KEY (module_Id));

CREATE TABLE registration(
student_Id varchar(6), 
module_Id varchar(5),
PRIMARY KEY (student_Id, module_Id),
FOREIGN KEY (student_Id) REFERENCES student(student_Id) 
ON UPDATE CASCADE ON DELETE CASCADE,
FOREIGN KEY (module_Id) REFERENCES module(module_Id)
ON UPDATE CASCADE ON DELETE CASCADE);

CREATE TABLE teaches(
staff_Id varchar(6), 
module_Id varchar(5),
PRIMARY KEY (staff_Id, module_Id),
FOREIGN KEY (staff_Id) REFERENCES staff(staff_Id)
ON UPDATE CASCADE ON DELETE CASCADE,
FOREIGN KEY (module_Id) REFERENCES module(module_Id)
ON UPDATE CASCADE ON DELETE CASCADE);
