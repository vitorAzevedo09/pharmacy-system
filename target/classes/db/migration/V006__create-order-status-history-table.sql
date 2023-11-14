CREATE TABLE order_status_history (
    id SERIAL PRIMARY KEY,
    order_id BIGINT REFERENCES orders(id),
    status VARCHAR(10) NOT NULL,
    change_date TIMESTAMP WITH TIME ZONE NOT NULL
);
