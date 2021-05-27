/*
    Bank Clients Table.
*/
CREATE TABLE bank_clients(
     id BIGINT PRIMARY KEY AUTO_INCREMENT,
     full_name VARCHAR,
     phone_number VARCHAR,
     passport_id BIGINT NOT NULL
);
/*
    Bank Accounts Table.
    One client may have many accounts.
*/
CREATE TABLE bank_accounts(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    number VARCHAR(24) NOT NULL,
    bank_client_id BIGINT NOT NULL, -- one to many
    balance DECIMAL,
    CONSTRAINT bank_accounts_client_fk
    FOREIGN KEY (bank_client_id) REFERENCES bank_clients(id) ON DELETE CASCADE
);
/*
    Debit Cards Table.
    One account may have many Debit Cards
*/
CREATE TABLE debit_cards(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    bank_account_id BIGINT, -- one to many
    number VARCHAR(19) NOT NULL,
    expiration_date VARCHAR(4) NOT NULL,
    cvv_code VARCHAR(3) NOT NULL,
    CONSTRAINT bank_debit_cards_account_fk
    FOREIGN KEY (bank_account_id) REFERENCES bank_accounts(id) ON DELETE CASCADE
);

/*
    Account's Local Transactions Table.
    One account may have many transactions.
*/
CREATE TABLE account_local_transactions(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    bank_account_id BIGINT NOT NULL, -- one to many
    difference DECIMAL NOT NULL,
    date TIMESTAMP NOT NULL,
    details VARCHAR(255),
    CONSTRAINT bank_transactions_account_fk
    FOREIGN KEY (bank_account_id) REFERENCES bank_accounts(id) ON DELETE CASCADE
);