INSERT INTO users VALUES (1, 'Admin', 'Admin', 'ADMIN', 'male', 'admin@admin.com','password', '1234city', 'v4a2x9', '01/01/95', 1);
INSERT INTO users VALUES (2, 'Test', 'Test', 'USER', 'male', 'test@test.com','password', 'test city', 'v4a2x9', '01/01/95', 1);
INSERT INTO users VALUES (3, 'Jasmeet', 'Brar', 'USER', 'female', 'jasmeetbrar@gmail.com','password', 'Surrey', 'v3v7y5', '01/01/97', 1);
INSERT INTO users VALUES (4, 'Gurvansh', 'Singh', 'USER', 'male', 'gurvanshsingh@gmail.com','password', 'Burnaby', 'v4a2x9', '01/14/92', 1);
INSERT INTO users VALUES (5, 'Abhisek', 'Chowdhury', 'USER', 'male', 'abhisekchowdhury@gmail.com','password', 'Surrey', 'v4a2x9', '05/01/94', 1);

INSERT INTO userVaccination VALUES (1, 'test', 'Covid-19', 'This vaccination is for COVID-19', '01/01/2020', 12);

INSERT INTO adminVaccination VALUES (1, 'Covid-19', 'This vaccination is for Covid-19', 12);
INSERT INTO adminVaccination VALUES (2, 'Influenza (flu)', 'This vaccine is recommended once a year.',12);
INSERT INTO adminVaccination VALUES (3, 'Tetanus and Diphtheria', 'This vaccine is recommended to be received every 10 years', 120);
INSERT INTO adminVaccination VALUES (4, 'Typhoid', 'The Typh vaccine is recommended for individuals travelling to or working in Asia, Africa, Central and South America, the Middle East or Mediterranean.', 12);
INSERT INTO adminVaccination VALUES (5, 'Measles + Mumps + Rubella', 'The MMR vaccine is recommended for children between 12 and 18 months of age as part of the routine vaccination schedule, and is recommended for adults who have not been vaccinated as children.', 48);
INSERT INTO adminVaccination VALUES (6, 'Smallpox', 'This vaccination is for Smallpox', 24);


INSERT INTO dailyNews VALUES (1, 'Breakthrough in Zika Virus Vaccine', 'Admin', '03/31/2019');
INSERT INTO dailyNews VALUES (2, 'Engineered Virus Might Be Able to Block Coronavirus Infections, Mouse Study Shows.', 'Admin', '04/02/2020');

INSERT INTO enquiry VALUES (1, 'I cannot find Hepatitis Vaccination in here', 'test', 'test@test.com', 'Waiting Response!', 0);
INSERT INTO enquiry VALUES (2, 'Are these all the vaccinations?', 'test', 'test@test.com', 'Waiting Response!', 0);

INSERT INTO clinics VALUES (1, 'Surrey Hospital', 'Surrey', 'surrey@surrey.com', '1234567890');
INSERT INTO clinics VALUES (2, 'Burnaby Hospital', 'Burnaby', 'burnaby@burnaby.com', '0987654321');
