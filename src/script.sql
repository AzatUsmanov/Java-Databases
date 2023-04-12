DROP TABLE Tasks;

DROP TABLE Employees;

CREATE TABLE Employees (
	id BIGINT PRIMARY KEY,
	name VARCHAR(25),
	date_of_birth DATE
);

CREATE TABLE Tasks (
	id BIGINT PRIMARY KEY,
	name VARCHAR(25),
	deadline DATE,
	description VARCHAR(200),
	task_type VARCHAR(20) CHECK(task_type in ('new functionality', 'error', 'improvement', 'analytics')),
	human BIGINT,
	FOREIGN KEY(human) REFERENCES Employee(id)
);

SELECT *
FROM Employees;

SELECT *
FROM Tasks;

INSERT INTO Employees (id, name, date_of_birth)
VALUES(1,'John Travolta','2012-10-2');

INSERT INTO Tasks (id, name, deadline, description, task_type, human)
VALUES(1,'task', '2020-10-11', 'some task', 'error', 1);


