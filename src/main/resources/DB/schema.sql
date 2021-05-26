CREATE TABLE bank_accounts(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    number VARCHAR(24) NOT NULL,
    bank_client_id BIGINT NOT NULL,
    CONSTRAINT bank_accounts_client_fk
    FOREIGN KEY (bank_client_id) REFERENCES bank_clients(id) ON DELETE CASCADE
);

CREATE TABLE bank_clients(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    full_name VARCHAR,
    phoneNumber VARCHAR);

CREATE TABLE debit_cards(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    number VARCHAR(19) NOT NULL,
    balance BIGINT,
    bank_account_id BIGINT,
    CONSTRAINT bank_debit_cards_account_fk
    FOREIGN KEY (bank_account_id) REFERENCES bank_accounts(id) ON DELETE CASCADE
);

CREATE TABLE accounts_transactions(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    tr_difference BIGINT,
    bank_account_id BIGINT,
    tr_date TIMESTAMP NOT NULL,
    CONSTRAINT bank_transactions_account_fk
    FOREIGN KEY (bank_account_id) REFERENCES bank_accounts(id) ON DELETE CASCADE
);