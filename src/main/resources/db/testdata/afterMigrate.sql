DELETE FROM order_items;
DELETE FROM products;
DELETE FROM orders;
DELETE FROM users;
DELETE FROM customers;

-- Inserting medicine products
INSERT INTO products (id,name, manufacturer, description, price, quantity_in_stock, expiration_date)
VALUES
    (1,'Painkiller', 'MediPharm', 'Effective pain relief for various conditions', 9.99, 100, '2023-12-31'),
    (2,'Antibiotic', 'HealthCare Ltd.', 'Broad-spectrum antibiotic for bacterial infections', 19.99, 50, '2023-12-31'),
    (3,'Allergy Medication', 'Wellness Pharma', 'Relieves allergy symptoms such as sneezing and itching', 14.99, 75, '2023-12-31');

-- Inserting users
INSERT INTO "users" (id, username, password, dtype)
VALUES
    (1, 'john_doe', 'password123', 'customer'),
    (2, 'jane_smith', 'securePwd456', 'user');

-- Inserting customers
INSERT INTO customers (id, username, password, first_name, last_name, email)
VALUES
    (1, 'john_doe', 'password123', 'John', 'Doe', 'john.doe@example.com'),
    (2, 'jane_smith', 'securePwd456', 'Jane', 'Smith', 'jane.smith@example.com');

-- Inserting an order
INSERT INTO orders (id, customer_id, order_date, total_amount, status, confirmation_date, cancellation_date, delivery_date)
VALUES
    (1,1, '2023-11-10 12:00:00'::TIMESTAMPTZ, 150.99, 'CREATED', NULL, NULL, NULL),
    (2,2, '2023-11-11 14:30:00'::TIMESTAMPTZ, 99.50, 'DELIVERIED', '2023-11-12 10:00:00'::TIMESTAMPTZ, NULL, '2023-11-15 12:00:00'::TIMESTAMPTZ);

-- Inserting order items for the orders
INSERT INTO order_items (order_item_id,order_id, product_id, quantity, price_per_unit)
VALUES
    (1,1, 1, 2, 25.99),  -- Assuming product_id 1 exists
    (2,1, 2, 1, 19.99),  -- Assuming product_id 2 exists
    (3,2, 3, 3, 14.99);  -- Assuming product_id 3 exists
