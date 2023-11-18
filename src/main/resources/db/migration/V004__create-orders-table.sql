CREATE TABLE orders (
    id BIGSERIAL PRIMARY KEY,
    customer_id BIGINT REFERENCES customers(id),
    total_amount DECIMAL(19, 2),
    status VARCHAR(10) DEFAULT 'CREATED' NOT NULL
);
