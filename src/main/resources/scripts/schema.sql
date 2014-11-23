drop table contact;

CREATE TABLE contact (
  id int  GENERATED BY DEFAULT AS IDENTITY (START WITH 1 INCREMENT BY 1)  NOT  NULL primary key,
  name varchar(225) DEFAULT NULL,
  lname varchar(225) DEFAULT NULL,
  street_number int DEFAULT NULL,
  street_name varchar(225) DEFAULT NULL,
  city varchar(225) DEFAULT NULL,
  state varchar(225) DEFAULT NULL,
  postal_code varchar(10) DEFAULT NULL,
  country varchar(225) DEFAULT NULL,
  phone varchar(225) DEFAULT NULL,
  email varchar(225) DEFAULT NULL,
  added_date DATETIME DEFAULT NULL,
  last_contact_date DATETIME DEFAULT NULL
);