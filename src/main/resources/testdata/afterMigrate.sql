SET foreign_key_checks = 0;

DELETE FROM products;

SET foreign_key_checks = 1;

ALTER TABLE products auto_increment = 1;

INSERT INTO products (name, manufacturer, description, price, quantity_in_stock, expiration_date)
VALUES ('Sample Product', 'Sample Manufacturer', 'This is a sample product description.', 49.99, 100, '2023-12-31');
