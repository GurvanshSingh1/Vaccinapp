INSERT INTO users VALUES (1, 'test1', 'test1', 'USER', 'male', 'abc@abc.com','password', '1234city', 'v4a2x9', '01/01/95', 1);
INSERT INTO users VALUES (1, 'Admin', 'Admin', 'ADMIN', 'male', 'admin@admin.com','password', '1234city', 'v4a2x9', '01/01/95', 1);

INSERT INTO userVaccination VALUES (1, 'test1', 'Coronavirus', 'This vaccination is for COVID-19', '01/01/2020');
INSERT INTO userVaccination VALUES (2, 'test2', 'ABC', 'This vaccination is for AAA-19', '01/01/2020');

INSERT INTO adminVaccination VALUES (1, 'Covid-19', 'This vaccination is for Covid-19', 6);
INSERT INTO adminVaccination VALUES (2, 'Covid-20', 'This vaccination is for Covid-20', 5);

INSERT INTO dailyNews VALUES (1, 'Today is 31', 'Admin', '03/31/2020');
INSERT INTO dailyNews VALUES (2, 'News 2 here...', 'Admin', '01/01/2020');

INSERT INTO enquiry VALUES (1, 'What is your name?', 'test1', 'abc@abc.com', '', 0);
INSERT INTO enquiry VALUES (2, 'How are you doing?', 'test1', 'abc@abc.com', '', 0);

INSERT INTO clinics VALUES (1, 'Surrey Hospital', 'Surrey', 'surrey@surrey.com', '1234567890');
INSERT INTO clinics VALUES (2, 'Burnaby Hospital', 'Burnaby', 'burnaby@burnaby.com', '0987654321');
