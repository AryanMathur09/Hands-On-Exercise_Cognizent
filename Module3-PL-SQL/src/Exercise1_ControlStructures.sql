--Loan Interest discount for customers above 60
set SERVEROUTPUT on;
DECLARE
v_customer_id Customers.CustomerID%TYPE;
v_name        Customers.Name%TYPE;
v_dob         Customers.DOB%TYPE;
v_age         NUMBER;

CURSOR c_customers IS
SELECT CustomerID, Name, DOB
FROM Customers;

BEGIN
    DBMS_OUTPUT.PUT_LINE ('=== Loan Interest Discount Processing ===');

FOR customer IN c_customers LOOP
   v_age := TRUNC(MONTHS_BETWEEN(SYSDATE, customer.DOB) /12);
   DBMS_OUTPUT.PUT_LINE('Customer: ' || customer.Name || ' | Age: ' || v_age);

   IF v_age > 60 THEN
   -- applying 1% discount
update LOANS
SET InterestRate = InterestRate-1
where CustomerID = customer.CustomerID
  And InterestRate > 1;

DBMS_OUTPUT.PUT_LINE('Discount applied to ' || customer.Name || '''s loans!');
ELSE
   DBMS_OUTPUT.PUT_LINE(' Not eligible for discount');
END IF;
END LOOP;
COMMIT;
DBMS_OUTPUT.PUT_LINE('=== Processing Complete ===');
EXCEPTION
      WHEN others THEN
            ROLLBACK;
            DBMS_OUTPUT.PUT_LINE('Error: '|| SQLERRM);
END;
/

set SERVEROUTPUT on;
DECLARE
CURSOR c_customers is
SELECT CustomerID, Name, Balance
FROM CUSTOMERS;
BEGIN
    DBMS_OUTPUT.PUT_LINE('=== VIP Status Processing');

FOR customer in c_customers LOOP
        DBMS_OUTPUT.PUT_LINE('Customer: ' || customer.Name || ' | Balance: $' || customer.Balance);
        IF customer.Balance > 10000 THEN
        --seting vip flag
UPDATE CUSTOMERS
SET IsVIP = 'TRUE'
where CustomerID = customer.CustomerID;
DBMS_OUTPUT.PUT_LINE(' ->' || customer.Name || 'promoted to VIP!');
ELSE
UPDATE CUSTOMERS
SET IsVIP = 'FALSE'
where CustomerID = customer.CustomerID;
DBMS_OUTPUT.PUT_LINE('- >' || customer.Name || 'is a regular customer');
end if;
end loop;
commit;
DBMS_OUTPUT.PUT_LINE('=== VIP processing complete');
EXCEPTION
    when others THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
/
SET SERVEROUTPUT ON;

DECLARE
CURSOR c_loans IS
SELECT l.LoanID,
       l.CustomerID,
       c.Name,
       l.EndDate,
       l.LoanAmount,
       (l.EndDate - SYSDATE) AS days_remaining
FROM Loans l
         JOIN Customers c ON l.CustomerID = c.CustomerID
WHERE l.EndDate BETWEEN SYSDATE AND SYSDATE + 30;

BEGIN
    DBMS_OUTPUT.PUT_LINE('=== Loan Due Reminders ===');
    DBMS_OUTPUT.PUT_LINE('Checking loans due in next 30 days...');
    DBMS_OUTPUT.PUT_LINE('');

FOR loan IN c_loans LOOP
        DBMS_OUTPUT.PUT_LINE('REMINDER for Customer: ' || loan.Name);
        DBMS_OUTPUT.PUT_LINE('  Loan ID     : ' || loan.LoanID);
        DBMS_OUTPUT.PUT_LINE('  Loan Amount : $' || loan.LoanAmount);
        DBMS_OUTPUT.PUT_LINE('  Due Date    : ' ||
                           TO_CHAR(loan.EndDate, 'DD-MON-YYYY'));
        DBMS_OUTPUT.PUT_LINE('  Days Left   : ' ||
                           TRUNC(loan.days_remaining) || ' days');
        DBMS_OUTPUT.PUT_LINE('  ⚠ Please ensure timely payment!');
        DBMS_OUTPUT.PUT_LINE('');
END LOOP;

    DBMS_OUTPUT.PUT_LINE('=== Reminder Processing Complete ===');

EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
/