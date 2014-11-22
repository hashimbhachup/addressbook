drop table addressbook;
CREATE TABLE addressbook (
  id numeric primary key,
  name varchar(225) DEFAULT NULL,
  lname varchar(225) DEFAULT NULL,
  address varchar(225) DEFAULT NULL,
  phone varchar(225) DEFAULT NULL,
  email varchar(225) DEFAULT NULL,
  lastcontactdate DATETIME DEFAULT NULL
);