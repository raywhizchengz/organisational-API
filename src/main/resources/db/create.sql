SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS departments (
 id int PRIMARY KEY auto_increment,
 deptName VARCHAR,
 description VARCHAR,
 noOfEmployees INTEGER
);

CREATE TABLE IF NOT EXISTS employees (
 id int PRIMARY KEY auto_increment,
 employeeName VARCHAR,
 employeePosition VARCHAR,
 deptId INTEGER
);

CREATE TABLE IF NOT EXISTS news (
 id int PRIMARY KEY auto_increment,
 topic VARCHAR,
 content VARCHAR,
);

CREATE TABLE IF NOT EXISTS departmentNews (
 id int PRIMARY KEY auto_increment,
 topic VARCHAR,
 content VARCHAR,
 deptId INTEGER
);