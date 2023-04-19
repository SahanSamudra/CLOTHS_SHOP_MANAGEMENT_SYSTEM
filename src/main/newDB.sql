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
                     size VARCHAR(10),
                     supplier VARCHAR(25),
                      CONSTRAINT PRIMARY KEY (Iid)

                      );
                      SHOW TABLES;
                                        DESCRIBE Item;