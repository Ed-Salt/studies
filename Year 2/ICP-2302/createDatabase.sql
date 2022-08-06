CREATE TABLE accounts (
accountID varchar(6),
accountUser varchar(10),
accountPass varchar(50),
roleID varchar (1),
PRIMARY KEY (accountID),
FOREIGN KEY (roleID) REFERENCES roles(roleID));

CREATE TABLE roles (
roleID varchar(1),
roleName varchar(50),
PRIMARY KEY (roleID));

CREATE TABLE schools (
schoolID varchar(6),
schoolName varchar(50),
schoolHead varchar(50),
PRIMARY KEY (schoolID));

CREATE TABLE titles (
titleID varchar(1),
titleName varchar(10),
PRIMARY KEY (titleID));

CREATE TABLE visitorDetails (
visitorID varchar(6),
visitorName varchar(50),
titleID varchar(1),
schoolID varchar(6),
PRIMARY KEY (visitorID),
FOREIGN KEY (titleID) REFERENCES titles(titleID),
FOREIGN KEY (schoolID) REFERENCES schools(schoolID));

CREATE TABLE visitRecord (
hostID varchar(6),
visitorID varchar(6),
arrivalDate date,
departureDate date,
schoolID varchar(6),
approved bool,
PRIMARY KEY (hostID, visitorID, arrivalDate),
FOREIGN KEY (hostID) REFERENCES accounts(accountID),
FOREIGN KEY (visitorID) REFERENCES visitorDetails(visitorID),
FOREIGN KEY (schoolID) REFERENCES schools(schoolID));