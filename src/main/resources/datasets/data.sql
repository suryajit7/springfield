DROP TABLE IF EXISTS users;
CREATE TABLE users AS SELECT * FROM CSVREAD("classpath:user_visa.csv");