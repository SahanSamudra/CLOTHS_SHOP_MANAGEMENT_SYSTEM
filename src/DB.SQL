DROP DATABASE IF EXISTS Shalini_Fashion;
CREATE DATABASE IF NOT EXISTS Shalini_Fashion;
SHOW DATABASES;
USE Shalini_Fashion;

DROP TABLE IF EXISTS user;
CREATE TABLE   User(
    user_id VARCHAR(8) PRIMARY KEY,
    FName VARCHAR (10)NOT NULL DEFAULT 'Unknown',
    LName VARCHAR (10)NOT NULL DEFAULT 'Unknown',
    UserName VARCHAR(15),
    Password VARCHAR (15),
    Email VARCHAR(20)
    );
SHOW TABLES ;
DESCRIBE User;

#--------------------------------------------------------

create table employee(
     employee_id VARCHAR(20) PRIMARY KEY,
     employee_name VARCHAR(50),
     nic VARCHAR(15),
     address VARCHAR(50),
     ranks VARCHAR(20),
     contact_no VARCHAR(12),
     user_id VARCHAR(20),
     CONSTRAINT FOREIGN KEY (user_id) REFERENCES user(user_id)
);

#--------------------------------------------------------
CREATE TABLE Attendance(
Attendance_Id VARCHAR(50) PRIMARY KEY,
Hour TIME,
Employee_Id VARCHAR(50),
FOREIGN KEY(Employee_Id) REFERENCES Employee(Employee_Id)
);

#--------------------------------------------------------------

CREATE TABLE Employee_Attendance(
Employee_Id VARCHAR(20),
Attendance_Id VARCHAR(50),
Salary DOUBLE(7,2),
PRIMARY KEY(employee_Id,Attendance_Id),
FOREIGN KEY (employee_Id) REFERENCES employee(employee_Id),
FOREIGN KEY (Attendance_Id) REFERENCES Attendance(Attendance_Id)
);
#-----------------------------------------------------------------

create table customer(
     customer_id VARCHAR(20) PRIMARY KEY,
     customer_name VARCHAR(30),
     nic VARCHAR(15),
     address VARCHAR(50),
     email VARCHAR(50),
     contact_no VARCHAR(12),
     user_id VARCHAR(20),
     CONSTRAINT FOREIGN KEY (user_id) REFERENCES user(user_id)
);

#---------------------------------------------------------

create table supplier(
    supplier_id VARCHAR(20) PRIMARY KEY,
    supplier_name VARCHAR(30),
    address VARCHAR(50),
    email VARCHAR(50),
    contact_no VARCHAR(12),
    user_id VARCHAR(20),
    CONSTRAINT FOREIGN KEY (user_id) REFERENCES user(user_id)
);

#---------------------------------------------------------
DROP TABLE IF EXISTS SalesRecord;
CREATE TABLE SalesRecord(
    SalesId VARCHAR(9) PRIMARY KEY,
    Description VARCHAR (20),
    SalesType VARCHAR(10)
    );
SHOW TABLES ;
DESCRIBE SalesRecord;

#--------------------------------------------------------------------------------------

DROP TABLE IF EXISTS Orders;
CREATE TABLE Orders(
   OrderId VARCHAR(6) PRIMARY KEY,
   customer_id VARCHAR(20) NOT NULL,
   Order_Date DATE,
   Price DOUBLE DEFAULT 0.00,
   CONSTRAINT FOREIGN KEY ( customer_id) REFERENCES customer( customer_id) ON DELETE CASCADE ON UPDATE CASCADE
   );
SHOW TABLES ;
DESCRIBE Orders;

#--------------------------------------------------------------------------------------

DROP TABLE IF EXISTS Item;
CREATE TABLE Item(
   Item_Code VARCHAR(6) PRIMARY KEY,
   description VARCHAR(50) NOT NULL,
   qtyOnHand INT DEFAULT 0,
   unitPrice DOUBLE DEFAULT 0.00,
   Item_Type VARCHAR(15)
);
SHOW TABLES ;
DESCRIBE  Item;

#------------------------------------------------------------------------------------------

DROP TABLE IF EXISTS Order_Detail;
CREATE TABLE Order_Detail(
   OrderId VARCHAR(6) NOT NULL,
   Item_Code VARCHAR(6) NOT NULL,
   OrderQty INT NOT NULL,
   unitPrice DOUBLE DEFAULT 0.00,
   CONSTRAINT PRIMARY KEY (OrderId , Item_Code),
   CONSTRAINT FOREIGN KEY (OrderId) REFERENCES Orders(OrderId) ON DELETE CASCADE ON UPDATE CASCADE,
   CONSTRAINT FOREIGN KEY (Item_Code) REFERENCES Item(Item_Code) ON DELETE CASCADE ON UPDATE CASCADE
);
SHOW TABLES ;
DESCRIBE Order_Detail;

#------------------------------------------------------------------------------------------


CREATE TABLE ReturnItem(
	return_code VARCHAR(10) PRIMARY KEY,
	description VARCHAR(30),
	qty INT(5),
	OrderId 		VARCHAR(10),
	CONSTRAINT FOREIGN KEY(OrderId) REFERENCES Orders(OrderId)
);


















