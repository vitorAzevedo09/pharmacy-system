CREATE TABLE orders (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users(id),
    total_amount DECIMAL(19, 2),
    status VARCHAR(10) DEFAULT 'CREATED' NOT NULL
);
