DROP DATABASE IF EXISTS Shalini_Fashion;
CREATE DATABASE IF NOT EXISTS Shalini_Fashion;
SHOW DATABASES;
USE Shalini_Fashion;

DROP TABLE IF EXISTS Customer;
CREATE TABLE IF NOT EXISTS Customer
(
    cid     VARCHAR(10) NOT NULL,
    name    VARCHAR(45),
    address VARCHAR(75),
    contact VARCHAR(12),
    CONSTRAINT PRIMARY KEY (cid)
    );
SHOW TABLES;
DESCRIBE Customer;


DROP TABLE IF EXISTS Supplier;
CREATE TABLE IF NOT EXISTS Supplier
(
    sid VARCHAR(10) not null,
    snic VARCHAR (45),
    sname VARCHAR(45),
    scontact VARCHAR(12),
    saddress VARCHAR (75),
    scompany VARCHAR (45),
    CONSTRAINT PRIMARY KEY (sid)
    );
SHOW TABLES;
DESCRIBE Supplier;



DROP TABLE IF EXISTS Employee;
                  CREATE TABLE IF NOT EXISTS Employee
                  (
                      eId VARCHAR(10),
                      eName VARCHAR (45),
                      NIC VARCHAR(25),
                      eAddress VARCHAR(12),
                      salary DOUBLE DEFAULT 0.00,
                      eContact VARCHAR (15),
                      CONSTRAINT PRIMARY KEY (eId)
                      );
                  SHOW TABLES;
                  DESCRIBE Employee;

   DROP TABLE IF EXISTS Item;
                     CREATE TABLE IF NOT EXISTS Item
                     (

                     Iid VARCHAR(10),
                     type VARCHAR(25),
                     price DOUBLE,
                     qty INT,
                     size  VARCHAR(10),
                     supplier VARCHAR(25),
                     tcost DOUBLE,
                      CONSTRAINT PRIMARY KEY (Iid)

                      );
                      SHOW TABLES;
                                        DESCRIBE Item;


DROP TABLE IF EXISTS Orders;
CREATE TABLE IF NOT EXISTS Orders(

    oid VARCHAR (10) NOT NULL,
    ocid VARCHAR (10) NOT NULL,
    odate VARCHAR(15),
    otime  VARCHAR(15),
    cost VARCHAR(15),

    CONSTRAINT PRIMARY KEY (oid),
    CONSTRAINT FOREIGN KEY (ocid) REFERENCES Customer (cid) ON DELETE CASCADE ON UPDATE CASCADE
    );
SHOW TABLES;
DESCRIBE Orders;


DROP TABLE IF EXISTS Item_Reports;
CREATE TABLE IF NOT EXISTS Item_Reports(

    itemcode VARCHAR (10) NOT NULL,
    oid VARCHAR (10) NOT NULL,
    unitprice DOUBLE ,
    itemcount INT  DEFAULT 0,
    total DOUBLE DEFAULT 0.00,
    rdate VARCHAR (15),

    CONSTRAINT PRIMARY KEY (itemcode,oid),
    CONSTRAINT FOREIGN KEY (itemcode) REFERENCES Item(Iid) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (oid) REFERENCES Orders (oid) ON DELETE CASCADE ON UPDATE CASCADE
    );
SHOW TABLES;
DESCRIBE Item_Reports;

DROP TABLE IF EXISTS Billing;
CREATE TABLE IF NOT EXISTS Billing
(
    Bicode VARCHAR (15),
    Bid   VARCHAR(10) NOT NULL,
    Bcid VARCHAR (10),
    Bitype VARCHAR (15),
    Bbrandname VARCHAR (15),
    Bmodel VARCHAR (15),
    Bimei VARCHAR (15),
    Bwarrantydue VARCHAR (15),
    Bdescription VARCHAR (25),
    qty INT,
    price DOUBLE,
    Bdate VARCHAR (15),

    CONSTRAINT PRIMARY KEY (Bicode)


    );
SHOW TABLES;
DESCRIBE Billing;

create table returnItem(
    id VARCHAR(5),
    qty INT,
    size_ VARCHAR(2),
    item_id VARCHAR(10),

    CONSTRAINT PRIMARY KEY(id),
    CONSTRAINT FOREIGN KEY(item_id) REFERENCES Item(Iid) ON DELETE CASCADE ON UPDATE CASCADE

);

DROP TABLE IF EXISTS Customer;
CREATE TABLE IF NOT EXISTS Customer
(
    cid     VARCHAR(10) NOT NULL,
    name    VARCHAR(45),
    address VARCHAR(75),
    contact VARCHAR(12),
    CONSTRAINT PRIMARY KEY (cid)
    );
SHOW TABLES;
DESCRIBE Customer;

 alter table returnitem ADD Column date DATE;
CREATE TABLE working_day(id VARCHAR(10) primary key,date DATE);


CREATE TABLE attendence(
	employee_id varchar(10),
	day_id VARCHAR(10),
	time TIME,
	status ENUM('Y','N'),
	CONSTRAINT PRIMARY KEY(employee_id,day_id),
	FOREIGN KEY(employee_id) REFERENCES employee(eId) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY(day_id) REFERENCES working_day(id) ON DELETE CASCADE ON UPDATE CASCADE
);
alter table returnitem add column o_id VARCHAR(10);