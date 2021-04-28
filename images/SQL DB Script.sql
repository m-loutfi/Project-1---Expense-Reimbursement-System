

---------PROJECT 1 SQL SCRIPT-----------


---List of tables:
-----Reimbursement Table
-----Users Table
-----Reimbursement Status Table
-----Reimbursement Type Table
-----User Roles Table


--------CREATING ALL TABLES--------

----User Roles Table
CREATE TABLE user_roles(
	role_ID SERIAL,
	user_role varchar(50),
	PRIMARY KEY (role_ID)
);

----Users Table
CREATE TABLE Users(
	user_ID SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	user_password varchar(50)NOT NULL,
	first_name varchar(100),
	last_name varchar(100),
	user_email varchar(200)NOT NULL UNIQUE,
	role_ID int,
	PRIMARY KEY (user_ID),
	FOREIGN KEY (role_ID) REFERENCES user_roles(role_ID)
);
----Reimbursement Status Table----
CREATE TABLE reimb_status (
	status_ID serial,
	reimb_status varchar(20),
	PRIMARY KEY (status_ID)
);
---Reimbursement Type Table----
CREATE TABLE reimb_type (
	type_ID Serial,
	reimb_type varchar(20),
	PRIMARY KEY (type_id)
);
--Reimbursement Table
CREATE TABLE Reimbursement (
	reimb_ID Serial,
	amount money,
	time_submitted timestamp,
	time_resolved timestamp,
	description varchar(300),
	receipt bytea,
	author int,
	resolver int,
	status_id int,
	type_id int,
	PRIMARY KEY (reimb_id),
	FOREIGN KEY (author) REFERENCES users(user_id),
	FOREIGN KEY (resolver) REFERENCES users(user_id),
	FOREIGN KEY (status_id) REFERENCES reimb_status(status_id),
	FOREIGN KEY (type_id) REFERENCES reimb_type(type_id)
);

----CRUD Statements----

--INSERT
INSERT INTO reimb_status(reimb_status) VALUES ('Pending');
INSERT INTO reimb_status(reimb_status) VALUES ('Approved');
INSERT INTO reimb_status(reimb_status) VALUES ('Denied');

INSERT INTO reimb_type (reimb_type) VALUES	('Lodging');
INSERT INTO reimb_type (reimb_type) VALUES	('Travel');
INSERT INTO reimb_type (reimb_type) VALUES	('Food');
INSERT INTO reimb_type (reimb_type) VALUES	('Other');

INSERT INTO	user_roles(user_role) VALUES ('Employee');
INSERT INTO	user_roles(user_role) VALUES ('Finance Manager');
INSERT INTO users(username, user_password, first_name, last_name, user_email, role_id) VALUES ('testname', 'testpass', 'Test', 'Test', 'test@test.com', '1');
INSERT INTO users(username, user_password, first_name, last_name, user_email, role_id) VALUES ('testnameFM', 'testpassFM', 'TestFM', 'TestFM', 'test@test.com', '2');


--
----TABLE SELECTS----
--SELECT * FROM reimb_status;
--SELECT * FROM reimb_type;
--SELECT * FROM reimbursement;
--
--SELECT * FROM users;
--SELECT * FROM user_roles;
--INSERT INTO reimbursement(amount, description, author, receipt, status_id, type_id, time_submitted) VALUES (109.99, 'test2', 2, null, 1, 2, current_timestamp);
--
----READ
--SELECT * FROM reimbursement where reimb_id = 7;
--SELECT * FROM reimb_status WHERE status_id = ?;
--SELECT user_role FROM users u JOIN user_roles ur ON u.role_id = ur.role_id WHERE user_id = ?;
--
--
----UPDATE
--UPDATE reimbursement SET status_id = ? WHERE reimb_id = ?;
--UPDATE reimbursement SET resolver = ?, time_resolved = current_timestamp WHERE reimb_id = ?;
--
----DELETE
--DELETE FROM reimbursement WHERE reimb_id = 4;












