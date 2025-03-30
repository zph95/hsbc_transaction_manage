drop table if exists transaction;
CREATE TABLE transaction (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    tid VARCHAR(255) NOT NULL UNIQUE,
    type VARCHAR(255) NOT NULL,
    amount DOUBLE NOT NULL,
    currency VARCHAR(255) NOT NULL,
    transaction_time BIGINT NOT NULL,
    target_uid BIGINT,
    link_key VARCHAR(255),
    created_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE UNIQUE INDEX uni_tid ON transaction(tid);
CREATE INDEX idx_user_id ON transaction(user_id);
CREATE INDEX idx_type ON transaction(type);
CREATE INDEX idx_currency ON transaction(currency);
CREATE INDEX idx_transaction_time ON transaction(transaction_time);