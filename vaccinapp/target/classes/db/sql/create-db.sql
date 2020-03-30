DROP TABLE users IF EXISTS;
DROP TABLE registrations IF EXISTS;
DROP TABLE students IF EXISTS;
DROP TABLE courses IF EXISTS;
DROP TABLE userVaccination IF EXISTS;


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
  vaccinId    INTEGER ,
  fname VARCHAR(30),
  vaccinType VARCHAR(30),
  notes VARCHAR(100),
  vaccinDate  VARCHAR(50)
);

CREATE TABLE students (
  email VARCHAR(50) PRIMARY KEY,
  name VARCHAR(50),
  password VARCHAR(20)
);

CREATE TABLE courses (
  code VARCHAR(50) PRIMARY KEY,
  name VARCHAR(200)
);

CREATE TABLE registrations(
  email VARCHAR(50),
  code VARCHAR(50),
  foreign key (email) references students(email),
  foreign key (code) references courses(code)
);
