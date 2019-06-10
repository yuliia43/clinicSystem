CREATE DATABASE Clinic;
USE Clinic;

CREATE TABLE clinic_staff (
	staff_id int AUTO_INCREMENT  NOT NULL,
	staff_surname nvarchar(30) NOT NULL,
	staff_name nvarchar(30) NOT NULL,
	title nvarchar(10) NOT NULL,
    email nvarchar(50) NOT NULL, 
    `password` nvarchar(30) NOT NULL, 
    PRIMARY KEY(staff_id)
    );
    
ALTER TABLE clinic_staff
ADD CHECK(title = 'doctor' or title = 'nurse');



CREATE TABLE patients_cards (
	patient_card_id int AUTO_INCREMENT NOT NULL,
	patient_surname nvarchar(30) NOT NULL,
	patient_name nvarchar(30) NOT NULL,
    sex char(1) NOT NULL,
    birthday_date date NOT NULL,
    PRIMARY KEY(patient_card_id)
    );
    
ALTER TABLE patients_cards
ADD CHECK(sex = 'm' or sex = 'f');
    
    CREATE TABLE diagnosis(
    diagnosis_id int AUTO_INCREMENT NOT NULL,
	patient_card_id int  NOT NULL,
	doctor_id int NOT NULL,
	diagnosis nvarchar(50) NOT NULL,
    set_date date NOT NULL,
	is_final_diagnosis bit NOT NULL,
    PRIMARY KEY(diagnosis_id),
    FOREIGN KEY(doctor_id) REFERENCES clinic_staff(staff_id),
    FOREIGN KEY(patient_card_id) REFERENCES patients_cards(patient_card_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
    );
    
    
    CREATE TABLE appointed(
	appointed_id int AUTO_INCREMENT NOT NULL,
	diagnosis_id int NOT NULL,
	`type` nvarchar(30) NOT NULL,
	details nvarchar(50) NOT NULL,
    PRIMARY KEY(appointed_id),  
    FOREIGN KEY(diagnosis_id) REFERENCES diagnosis(diagnosis_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

ALTER TABLE appointed
ADD CHECK(`type` = 'medicine' or `type` = 'operation' or `type` = 'procedure');

CREATE TABLE appointing_schedule (
	schedule_id int AUTO_INCREMENT NOT NULL,
	appointed_id int NOT NULL,
	pursuance_time datetime NOT NULL,
	performer_id int NOT NULL,
	is_performed bit NOT NULL,
    PRIMARY KEY(schedule_id),  
    FOREIGN KEY(performer_id) REFERENCES clinic_staff(staff_id),
    FOREIGN KEY(appointed_id) REFERENCES appointed(appointed_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);


INSERT INTO clinic_staff (staff_surname, staff_name, title, email,  `password`)
VALUES('Snow', 'Jon', 'nurse', 'johnsnow@gmail.com',  111111),
('Anna', 'Candall', 'doctor', 'anna@gmail.com',  12345);

INSERT INTO patients_cards (patient_surname, patient_name, birthday_date, sex)
VALUES('Котов', 'Андрей',Date("2010-06-15"),  'm'),
('Smith', 'Jane',Date("1989-12-07"),  'f');

INSERT INTO diagnosis (patient_card_id, doctor_id, diagnosis, is_final_diagnosis,  set_date)
VALUES(1, 2, 'Простуда', 1, Date("2019-06-03")), 
(2, 2, 'flu', 0, Date("2019-05-21")), 
(2, 2, 'bronhit', 0, Date("2019-06-01"));

 INSERT INTO appointed(diagnosis_id,`type`,details)
 VALUES(1, 'medicine', 'Капли для носа'),
 (2, 'medicine', 'AntiFlu'),
(3, 'procedure', 'Lungs ventilation');

 INSERT INTO appointing_schedule (appointed_id,pursuance_time,performer_id,is_performed)
 VALUES (3, TIMESTAMP("2019-06-06", "15:00:00") , 1, 1),
(1, TIMESTAMP("2019-06-13", "07:00:00") , 2, 0),
 (1, TIMESTAMP("2019-06-13", "15:00:00") , 2, 0),
 (1, TIMESTAMP("2019-06-13", "20:00:00") , 2, 0),
(2, TIMESTAMP("2019-06-13",  "09:00:00"), 1, 0),
(2, TIMESTAMP("2019-06-13",  "12:00:00"), 1, 0),
(2, TIMESTAMP("2019-06-13",  "17:00:00"), 1, 0) ;


    
