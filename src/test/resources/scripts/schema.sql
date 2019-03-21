CREATE SCHEMA IF NOT EXISTS MA_STUDENT;

CREATE SEQUENCE IF NOT EXISTS CUSTOMER_SEQ
	 MINVALUE 5555555555
	 MAXVALUE 9999999999
	 START WITH 5555555556
	INCREMENT BY 1;

DROP TABLE IF EXISTS MA_STUDENT.CUSTOMERS;

 CREATE TABLE MA_STUDENT.CUSTOMERS 
   (	CUST_NUM DECIMAL(38,0),
   	COMPANY VARCHAR(30), 
	CUST_REP DECIMAL(38,0),
	CREDIT_LIMIT DECIMAL(38,0)
)
