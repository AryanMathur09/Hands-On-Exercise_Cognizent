SET SERVERPOINT ON;
CREATE OR REPLACE FUNCTION CalculateAge(
       p_dob IN DATE
) RETURN NUMBER AS
       v_age NUMBER;
BEGIN
-- calculating age in years
v_age := TRUNC(MONTHS_BETWEEN(SYSDATE, p_dob) / 12);
RETURN v_age;
END CalculateAge;
/
-- calculating for one customer
DECLARE
v_age NUMBER;
BEGIN
v_age:= CalculateAge(TO_DATE('1955-05-15', 'YYYY-MM-DD'));
     DBMS_OUTPUT.PUT_LINE('Random kumar: '|| v_age);

--testing with all customes using fo loop
FOR customer IN(SELECT Name, DOB FROM Customers) LOOP
DBMS_OUTPUT.PUT_LINE('Customer: '||customer.Name|| '| Age: '|| CalculateAge(customer.DOB));
END LOOP;
END;
/

-- Calculating monthlyinstallment
SET SERVEROUTPUT ON;

CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment(
    p_loan_amount   IN NUMBER,
    p_interest_rate IN NUMBER,
    p_years         IN NUMBER
) RETURN NUMBER AS
    v_monthly_rate   NUMBER;
    v_num_payments   NUMBER;
    v_installment    NUMBER;
BEGIN
    -- Convert annual rate to monthly rate
    v_monthly_rate := (p_interest_rate / 100) / 12;

    -- Total number of monthly payments
    v_num_payments := p_years * 12;

    -- EMI Formula: P * r * (1+r)^n / ((1+r)^n - 1)
    v_installment := p_loan_amount *
                     (v_monthly_rate * POWER(1 + v_monthly_rate, v_num_payments)) /
                     (POWER(1 + v_monthly_rate, v_num_payments) - 1);

RETURN ROUND(v_installment, 2);
END CalculateMonthlyInstallment;
/

-- Test the function
DECLARE
v_installment NUMBER;
BEGIN
    DBMS_OUTPUT.PUT_LINE('=== Monthly Installment Calculator ===');

    -- Test Case 1
    v_installment := CalculateMonthlyInstallment(5000, 5, 5);
    DBMS_OUTPUT.PUT_LINE('Loan: $5000 | Rate: 5% | Years: 5');
    DBMS_OUTPUT.PUT_LINE('Monthly Installment: $' || v_installment);

    DBMS_OUTPUT.PUT_LINE('');

    -- Test Case 2
    v_installment := CalculateMonthlyInstallment(10000, 7, 3);
    DBMS_OUTPUT.PUT_LINE('Loan: $10000 | Rate: 7% | Years: 3');
    DBMS_OUTPUT.PUT_LINE('Monthly Installment: $' || v_installment);

    DBMS_OUTPUT.PUT_LINE('');

    -- Test with actual loans from DB
    DBMS_OUTPUT.PUT_LINE('=== Existing Loans Installments ===');
FOR loan IN (SELECT l.LoanID, c.Name, l.LoanAmount,
                        l.InterestRate,
                        TRUNC(MONTHS_BETWEEN(l.EndDate, l.StartDate)/12) AS Years
                 FROM Loans l
                 JOIN Customers c ON l.CustomerID = c.CustomerID) LOOP

        DBMS_OUTPUT.PUT_LINE('Customer: ' || loan.Name ||
                           ' | Loan: $' || loan.LoanAmount ||
                           ' | Monthly EMI: $' ||
                           CalculateMonthlyInstallment(
                               loan.LoanAmount,
                               loan.InterestRate,
                               loan.Years));
END LOOP;
END;
/
