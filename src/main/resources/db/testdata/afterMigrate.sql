TRUNCATE TABLE 
order_items,
order_status_history,
products,
orders,
users,
customers,
images,
users_roles,
roles,
roles_privileges,
privileges
RESTART IDENTITY;

-- Inserting medicine products
INSERT INTO products (name, manufacturer, description, price, quantity_in_stock, expiration_date)
VALUES
    ('Painkiller', 'MediPharm', 'Effective pain relief for various conditions', 9.99, 100, '2023-12-31'),
    ('Antibiotic', 'HealthCare Ltd.', 'Broad-spectrum antibiotic for bacterial infections', 19.99, 50, '2023-12-31'),
    ('Allergy Medication', 'Wellness Pharma', 'Relieves allergy symptoms such as sneezing and itching', 14.99, 75, '2023-12-31');

-- Inserting users
INSERT INTO users (username, first_name,last_name, email, password, dtype)
VALUES
    ('john_doe','John', 'Doe','fulano@email.com', 'password123', 'customer'),
    ('jane_smith','Jane','Smith','sicrano@email.com', 'securePwd456', 'user'),
    ('beatriz_oliveira','Beatriz Cristine','Oliveira dos Santos','beatrizgostosa@email.com', 'senhazinha', 'customer');

-- Inserting customers
INSERT INTO customers (user_id,company)
VALUES
    (1,'Comercial Buriti'),
    (3,'Coca Cola');

-- Inserting an order
INSERT INTO orders (customer_id, total_amount, status)
VALUES
    (1, 150.99, 'CREATED'),
    (2, 99.50, 'DELIVERED');

-- Inserting order items for the orders
INSERT INTO order_items (order_id, product_id, quantity, price_per_unit)
VALUES
    (1, 1, 2, 25.99),  -- Assuming product_id 1 exists
    (1, 2, 1, 19.99),  -- Assuming product_id 2 exists
    (2, 3, 3, 14.99);  -- Assuming product_id 3 exists

    
-- Fake Order History
INSERT INTO order_status_history (order_id, status, change_date) VALUES
(1, 'CREATED', '2023-01-01T12:00:00Z'::TIMESTAMPTZ),
(1, 'PROCESSING', '2023-01-02T10:00:00Z'::TIMESTAMPTZ),
(1, 'SHIPPED', '2023-01-03T14:30:00Z'::TIMESTAMPTZ);
