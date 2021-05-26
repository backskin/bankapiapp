CREATE TABLE bank_accounts(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    number VARCHAR(24) NOT NULL,
    balance DECIMAL,
    bank_client_id BIGINT NOT NULL,
    CONSTRAINT bank_accounts_client_fk
    FOREIGN KEY (bank_client_id) REFERENCES bank_clients(id) ON DELETE CASCADE
);

CREATE TABLE bank_clients(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    full_name VARCHAR,
    phone_number VARCHAR,
    passport_id BIGINT NOT NULL);

CREATE TABLE debit_cards(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    number VARCHAR(19) NOT NULL,
    bank_account_id BIGINT,
    expiration_date VARCHAR(4) NOT NULL,
    cvv_code VARCHAR(3) NOT NULL,
    CONSTRAINT bank_debit_cards_account_fk
    FOREIGN KEY (bank_account_id) REFERENCES bank_accounts(id) ON DELETE CASCADE
);

CREATE TABLE account_local_transactions(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    bank_account_id BIGINT NOT NULL,
    difference DECIMAL NOT NULL,
    date TIMESTAMP NOT NULL,
    details VARCHAR(255),
    CONSTRAINT bank_transactions_account_fk
    FOREIGN KEY (bank_account_id) REFERENCES bank_accounts(id) ON DELETE CASCADE
);