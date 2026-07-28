CREATE TABLE Customers (
                           CustomerID NUMBER PRIMARY key,
                           Name VARCHAR2(100),
                           DOB Date,
                           Balance NUMBER,
                           LastModified DATE
);
CREATE TABLE Accounts (
                          AccountID NUMBER PRIMARY KEY,
                          CustomerID NUMBER,
                          AccountType VARCHAR2(20),
                          Balance NUMBER,
                          LastModified DATE,
                          FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);
CREATE table Transactions (
                              TransactionID number PRIMARY key,
                              AccountID number,
                              TransactionDate Date,
                              Amount number,
                              TransactionType VARCHAR2(10),
                              FOREIGN key (AccountID) REFERENCES Accounts(AccountID)
);
CREATE table Loans (
                       LoanID number PRIMARY KEY,
                       CustomerID NUMBER,
                       LoanAmount NUMBER,
                       InterestRate NUMBER,
                       StartDate DATE,
                       EndDate DATE,
                       FOREIGN key (CustomerID) REFERENCES Customers(CustomerID)
);
CREATE TABLE Employees (
                           EmployeeID NUMBER PRIMARY KEY,
                           Name VARCHAR2(100),
                           Position VARCHAR2(50),
                           Salary NUMBER,
                           Department VARCHAR2(50),
                           HireDate DATE
);
-- INSERT SAMPLE DATA
INSERT INTO Customers VALUES (1, 'John Doe', TO_DATE('1955-05-15', 'YYYY-MM-DD'), 1000, SYSDATE);
INSERT INTO Customers VALUES (2, 'Jane Smith', TO_DATE('1990-07-20', 'YYYY-MM-DD'), 15000, SYSDATE);
INSERT INTO Customers VALUES (3, 'Bob Wilson', TO_DATE('1958-03-10', 'YYYY-MM-DD'), 500, SYSDATE);
INSERT INTO Customers VALUES (4, 'Alice Brown', TO_DATE('1995-11-25', 'YYYY-MM-DD'), 12000, SYSDATE);

INSERT INTO Accounts VALUES (1, 1, 'Savings', 1000, SYSDATE);
INSERT INTO Accounts VALUES (2, 2, 'Savings', 15000, SYSDATE);
INSERT INTO Accounts VALUES (3, 3, 'Checking', 500, SYSDATE);
INSERT INTO Accounts VALUES (4, 4, 'Savings', 12000, SYSDATE);

INSERT INTO Transactions VALUES (1, 1, SYSDATE, 200, 'Deposit');
INSERT INTO Transactions VALUES (2, 2, SYSDATE, 300, 'Withdrawal');

INSERT INTO Loans VALUES (1, 1, 5000, 5, SYSDATE, ADD_MONTHS(SYSDATE, 60));
INSERT INTO Loans VALUES (2, 2, 10000, 7, SYSDATE, ADD_MONTHS(SYSDATE, 25));
INSERT INTO Loans VALUES (3, 3, 3000, 6, SYSDATE, SYSDATE + 20);

INSERT INTO Employees VALUES (1, 'Alice Johnson', 'Manager', 70000, 'HR', TO_DATE('2015-06-15', 'YYYY-MM-DD'));
INSERT INTO Employees VALUES (2, 'Bob Brown', 'Developer', 60000, 'IT', TO_DATE('2017-03-20', 'YYYY-MM-DD'));
INSERT INTO Employees VALUES (3, 'Charlie Davis', 'Analyst', 55000, 'IT', TO_DATE('2019-08-10', 'YYYY-MM-DD'));

COMMIT;

ALTER TABLE Customers add IsVIP VARCHAR2(5) default 'FALSE';

