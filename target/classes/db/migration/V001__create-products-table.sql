CREATE TABLE products (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    manufacturer VARCHAR(255),
    description VARCHAR(255),
    price NUMERIC(19,2) NOT NULL,
    quantity_in_stock INT NOT NULL,
    expiration_date DATE
);
