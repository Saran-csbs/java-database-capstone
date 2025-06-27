## MySQL Database Design

### Table: patients
- id: INT, PK, AUTO_INCREMENT
- name: VARCHAR(100), NOT NULL
- dob: DATE, NOT NULL
- email: VARCHAR(150), UNIQUE, NOT NULL

### Table: doctors
- id: INT, PK, AUTO_INCREMENT
- name: VARCHAR(100), NOT NULL
- speciality: VARCHAR(50)
- email: VARCHAR(150), UNIQUE, NOT NULL

### Table: appointments
- id: INT, PK, AUTO_INCREMENT
- doctor_id: INT, FK → doctors(id)
- patient_id: INT, FK → patients(id)
- appt_time: DATETIME, NOT NULL
- status: ENUM('Scheduled','Completed','Cancelled'), DEFAULT 'Scheduled'

### Table: admin
- id: INT, PK, AUTO_INCREMENT
- username: VARCHAR(50), UNIQUE, NOT NULL
- password_hash: VARCHAR(255), NOT NULL
