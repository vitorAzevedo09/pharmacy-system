CREATE TABLE orders (
    id BIGINT PRIMARY KEY,
    customer_id BIGINT REFERENCES customers(id),
    order_date TIMESTAMPTZ,
    total_amount DECIMAL(19, 2),
    status VARCHAR(255),
    confirmation_date TIMESTAMPTZ,
    cancellation_date TIMESTAMPTZ,
    delivery_date TIMESTAMPTZ
);
