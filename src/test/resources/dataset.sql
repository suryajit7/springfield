DROP TABLE IF EXISTS user;
CREATE TABLE user AS SELECT * FROM CSVREAD('classpath:test-data/user_visa.csv');