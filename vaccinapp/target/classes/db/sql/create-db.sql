DROP TABLE users IF EXISTS;
DROP TABLE userVaccination IF EXISTS;
DROP TABLE adminVaccination IF EXISTS;
DROP TABLE dailyNews IF EXISTS;
DROP TABLE enquiry IF EXISTS;
DROP TABLE clinics IF EXISTS;


CREATE TABLE users (
  id    INTEGER ,
  fname VARCHAR(30),
  lname VARCHAR(30),
  userType VARCHAR (10),
  gender VARCHAR(20),
  email  VARCHAR(50),
  password VARCHAR(50),
  address VARCHAR(100),
  postal VARCHAR(30),
  dob VARCHAR(20),
  isApproved INTEGER
);

CREATE TABLE userVaccination (
  vaccinId    INTEGER identity,
  fname VARCHAR(30),
  vaccinType VARCHAR(30),
  notes VARCHAR(100),
  vaccinDate  VARCHAR(50)
);

CREATE TABLE adminVaccination (
  vaccinId    INTEGER identity ,
  vaccinType VARCHAR(30),
  notes VARCHAR(100),
  vaccinEffective  INTEGER
);

CREATE TABLE dailyNews (
  newsId    INTEGER identity ,
  news VARCHAR(200),
  postedBy VARCHAR(100),
  postDate  VARCHAR(50)
);

CREATE TABLE enquiry (
  enquiryId    INTEGER identity ,
  enquiry VARCHAR(200),
  name VARCHAR(50),
  email  VARCHAR(50),
  response VARCHAR(200),
  isReplied INTEGER
);

CREATE TABLE clinics (
  clinicId    INTEGER identity ,
  clinicName VARCHAR(200),
  clinicAddress VARCHAR(50),
  clinicEmail  VARCHAR(50),
  clinicContact VARCHAR(200)
);


