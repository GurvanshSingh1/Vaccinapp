INSERT INTO users VALUES (1, 'test1', 'test1', 'USER', 'male', 'abc@abc.com','password', '1234city', 'v4a2x9', '01/01/95', 1);
INSERT INTO users VALUES (1, 'Admin', 'Admin', 'ADMIN', 'male', 'admin@admin.com','password', '1234city', 'v4a2x9', '01/01/95', 1);

INSERT INTO userVaccination VALUES (1, 'test1', 'Coronavirus', 'This vaccination is for COVID-19', '01/01/2020');
INSERT INTO userVaccination VALUES (2, 'test2', 'ABC', 'This vaccination is for AAA-19', '01/01/2020');

INSERT INTO adminVaccination VALUES (1, 'Covid-19', 'This vaccination is for Covid-19', 6);
INSERT INTO adminVaccination VALUES (2, 'Covid-20', 'This vaccination is for Covid-20', 5);

INSERT INTO students VALUES('lis@douglascollege.ca', 'Simon Li', '123456');
INSERT INTO students VALUES('wongi5@douglascollege.ca', 'Ivan Wong', '654321');

INSERT INTO courses VALUES('CSIS1175', 'Introduction to Programming 1');
INSERT INTO courses VALUES('CSIS1275', 'Introduction to Programming 2');

INSERT INTO registrations VALUES('wongi5@douglascollege.ca', 'CSIS1275');