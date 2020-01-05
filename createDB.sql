CREATE DATABASE scbase;

CREATE TABLE company (company_id INT AUTO_INCREMENT, name VARCHAR(40) NOT NULL UNIQUE, CONSTRAINT company_pk PRIMARY KEY (company_id));

CREATE TABLE employee_skill (skill_id INT AUTO_INCREMENT, skill VARCHAR(25) NOT NULL UNIQUE, CONSTRAINT skill_pk PRIMARY KEY (skill_id));

CREATE TABLE personal_data (personal_data_id INT AUTO_INCREMENT, first_name VARCHAR(20) NOT NULL, last_name VARCHAR(20) NOT NULL, CONSTRAINT personal_data_pk PRIMARY KEY (personal_data_id));

CREATE TABLE  job_title (title_id INT AUTO_INCREMENT, title VARCHAR(20) NOT NULL UNIQUE, CONSTRAINT job_title__pk PRIMARY KEY (title_id));

CREATE TABLE employee (employee_id INT AUTO_INCREMENT, personal_data_id INT, title_id INT, company_id INT, CONSTRAINT employee_pk PRIMARY KEY (employee_id), CONSTRAINT emp_company_fk FOREIGN KEY (company_id) REFERENCES company (company_id), CONSTRAINT `emp_pd_fk` FOREIGN KEY (`personal_data_id`) REFERENCES `personal_data` (`personal_data_id`), CONSTRAINT `emp_jobst_fk` FOREIGN KEY (`title_id`) REFERENCES `job_title` (`title_id`));

CREATE TABLE  employee_to_skill (employee_id INT , skill_id INT,CONSTRAINT emp_skill_pk PRIMARY KEY (employee_id, skill_id), CONSTRAINT skill_fk FOREIGN KEY (skill_id) REFERENCES employee_skill (skill_id), CONSTRAINT emp_skill_fk FOREIGN KEY (employee_id) REFERENCES employee (employee_id));