CREATE SEQUENCE bank_users_id_seq;
CREATE TABLE bank_users (
    user_id INT PRIMARY KEY,
    username VARCHAR2(20) UNIQUE NOT NULL,
    password VARCHAR2(20) NOT NULL,
    firstname VARCHAR2(20) NOT NULL,
    lastname VARCHAR2(20) NOT NULL,
    address VARCHAR(60) NOT NULL,
    usertype VARCHAR2(15) DEFAULT 'user' NOT NULL 
);

CREATE SEQUENCE bank_accounts_id_seq;
CREATE TABLE bank_accounts (
    account_id INT PRIMARY KEY,
    user_id INT REFERENCES bank_users(user_id),
    account_type VARCHAR2(10) NOT NULL,
    account_balance NUMBER(7,2) DEFAULT 0.00
);

CREATE SEQUENCE bank_records_id_seq;
CREATE TABLE bank_records(
    record_id INT PRIMARY KEY,
    user_id INT REFERENCES bank_users(user_id),
    account_id INT REFERENCES bank_accounts(account_id),
    transaction_amount NUMBER(7,2) NOT NULL,
    transaction_type VARCHAR2(10) NOT NULL,
    time INT NOT NULL
);
-- CREATING USERS

INSERT INTO bank_users (user_id, username, password, firstname, lastname, address, usertype)
VALUES (BANK_USERS_ID_SEQ.nextval, 'bubbles', 'password', 'Bubbles', 'Powerpuff', '8838 Helen St.
Chesterfield, VA 23832', 'user' );

INSERT INTO bank_users (user_id, username, password, firstname, lastname, address, usertype)
VALUES (BANK_USERS_ID_SEQ.nextval, 'buttercup', 'password', 'Buttercup', 'Powerpuff', '42 Walnutwood St.
Brookline, MA 02446', 'user' );

INSERT INTO bank_users (user_id, username, password, firstname, lastname, address, usertype)
VALUES (BANK_USERS_ID_SEQ.nextval, 'blossom', 'password', 'Blossom', 'Powerpuff', '83 West University St.
Paducah, KY 42001', 'user' );

INSERT INTO bank_users (user_id, username, password, firstname, lastname, address, usertype)
VALUES (BANK_USERS_ID_SEQ.nextval, 'chemicalmasta', 'password', 'Professor', 'Utonium', '806 Thatcher Ave.
Lynchburg, VA 24502', 'admin' );

INSERT INTO bank_users (user_id, username, password, firstname, lastname, address, usertype)
VALUES (BANK_USERS_ID_SEQ.nextval, 'madmonkeh', 'password', 'Mojo', 'Jojo', '9138 Talbot Street
West Bend, WI 53095', 'user' );

--CREATING BANK ACCOUNT ENTRIES
INSERT INTO bank_accounts (account_id, user_id, account_type, account_balance, account_status)
VALUES (bank_accounts_id_seq.nextval, 21, 'checking', '345.20','active');

INSERT INTO bank_accounts (account_id, user_id, account_type, account_balance, account_status)
VALUES (bank_accounts_id_seq.nextval, 22, 'checking', '450.00','active');

INSERT INTO bank_accounts (account_id, user_id, account_type, account_balance, account_status)
VALUES (bank_accounts_id_seq.nextval, 23, 'checking', '225.50','closed');

INSERT INTO bank_accounts (account_id, user_id, account_type, account_balance, account_status)
VALUES (bank_accounts_id_seq.nextval, 24, 'savings', '10000.50','active');

INSERT INTO bank_accounts (account_id, user_id, account_type, account_balance, account_status)
VALUES (bank_accounts_id_seq.nextval, 25, 'savings', '13450.00','active');

--CREATING TRANSACTION ENTRIES
INSERT INTO bank_records (record_id, user_id, account_id, transaction_amount, transaction_type)