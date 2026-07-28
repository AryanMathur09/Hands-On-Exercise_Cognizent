SET SERVERPOINT ON;
CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest AS
BEGIN
    DBMS_OUTPUT.PUT_LINE('=== Processing Monthly Interest');
    --updating all saving acc with 1%interest
    UPDATE Accounts
    SET Balance = Balance + (Balance * 0.01),
        LastMOdified = SYSDATE
    WHERE AccountType = 'Savings';

    -- showing the updated balances
    FOR acc IN (SELECT AccountID, CustomerID, Balance
                FROM Accounts
                WHERE AccountType  = 'Savings') LOOP
        DBMS_OUTPUT.PUT_LINE('Account ID: '|| acc.AccountID || ' | New Balance: $'|| ROUND(acc.Balance, 2));
    END LOOP;
    COMMIT;

    DBMS_OUTPUT.PUT_LINE('=== Monthly INterest Processed===');
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error : '|| SQLERRM);
END ProcessMonthlyInterest;
/

SET SERVEROUTPUT ON;

CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus(
    p_department   IN VARCHAR2,
    p_bonus_percent IN NUMBER
) AS
    v_count NUMBER := 0;
BEGIN
    DBMS_OUTPUT.PUT_LINE('=== Updating Employee Bonus ===');
    DBMS_OUTPUT.PUT_LINE('Department: ' || p_department);
    DBMS_OUTPUT.PUT_LINE('Bonus: ' || p_bonus_percent || '%');

    -- Check if department exists
SELECT COUNT(*) INTO v_count
FROM Employees
WHERE Department = p_department;

IF v_count = 0 THEN
        DBMS_OUTPUT.PUT_LINE('No employees found in ' || p_department);
        RETURN;
END IF;

    -- Update salary with bonus
FOR emp IN (SELECT EmployeeID, Name, Salary
                FROM Employees
                WHERE Department = p_department) LOOP

        DBMS_OUTPUT.PUT_LINE('Employee: ' || emp.Name ||
                           ' | Old Salary: $' || emp.Salary ||
                           ' | New Salary: $' ||
                           ROUND(emp.Salary +
                           (emp.Salary * p_bonus_percent/100), 2));

UPDATE Employees
SET Salary = Salary + (Salary * p_bonus_percent / 100)
WHERE EmployeeID = emp.EmployeeID;
END LOOP;

COMMIT;
DBMS_OUTPUT.PUT_LINE('=== Bonus Update Complete ===');

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END UpdateEmployeeBonus;
/
SET SERVEROUTPUT ON;

CREATE OR REPLACE PROCEDURE TransferFunds(
    p_from_account IN NUMBER,
    p_to_account   IN NUMBER,
    p_amount       IN NUMBER
) AS
    v_from_balance NUMBER;
    v_to_balance   NUMBER;

    -- Custom exception
    e_insufficient_funds EXCEPTION;
    e_invalid_account    EXCEPTION;
BEGIN
    DBMS_OUTPUT.PUT_LINE('=== Fund Transfer Processing ===');
    DBMS_OUTPUT.PUT_LINE('From Account: ' || p_from_account);
    DBMS_OUTPUT.PUT_LINE('To Account  : ' || p_to_account);
    DBMS_OUTPUT.PUT_LINE('Amount      : $' || p_amount);

    -- Get source account balance
BEGIN
SELECT Balance INTO v_from_balance
FROM Accounts
WHERE AccountID = p_from_account;
EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RAISE e_invalid_account;
END;

    -- Get destination account balance
BEGIN
SELECT Balance INTO v_to_balance
FROM Accounts
WHERE AccountID = p_to_account;
EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RAISE e_invalid_account;
END;

    DBMS_OUTPUT.PUT_LINE('Source Balance     : $' || v_from_balance);
    DBMS_OUTPUT.PUT_LINE('Destination Balance: $' || v_to_balance);

    -- Check sufficient funds
    IF v_from_balance < p_amount THEN
        RAISE e_insufficient_funds;
END IF;

    -- Perform transfer
UPDATE Accounts
SET Balance = Balance - p_amount,
    LastModified = SYSDATE
WHERE AccountID = p_from_account;

UPDATE Accounts
SET Balance = Balance + p_amount,
    LastModified = SYSDATE
WHERE AccountID = p_to_account;

COMMIT;

DBMS_OUTPUT.PUT_LINE('Transfer Successful!');
    DBMS_OUTPUT.PUT_LINE('New Source Balance     : $' ||
                        (v_from_balance - p_amount));
    DBMS_OUTPUT.PUT_LINE('New Destination Balance: $' ||
                        (v_to_balance + p_amount));
    DBMS_OUTPUT.PUT_LINE('=== Transfer Complete ===');

EXCEPTION
    WHEN e_insufficient_funds THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: Insufficient funds!');
        DBMS_OUTPUT.PUT_LINE('Available: $' || v_from_balance ||
                           ' | Required: $' || p_amount);
WHEN e_invalid_account THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: Invalid account ID!');
WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END TransferFunds;
/

-- Test with valid transfer
EXEC TransferFunds(1, 2, 500);

-- Test with insufficient funds
EXEC TransferFunds(1, 2, 99999);

-- Test with invalid account
EXEC TransferFunds(99, 2, 100);

-- Execute with IT department and 10% bonus
EXEC UpdateEmployeeBonus('IT', 10);
--Executing the procedure
EXEC ProcessMonthlyInterest;