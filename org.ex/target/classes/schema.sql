DROP TABLE IF EXISTS employees;

CREATE TABLE employees(
  id NUMERIC IDENTITY PRIMARY KEY,
  name VARCHAR(512) ,
  email VARCHAR(512) ,
  address VARCHAR(512),
  telephone VARCHAR(512)
);