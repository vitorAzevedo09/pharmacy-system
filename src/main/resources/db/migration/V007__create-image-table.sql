CREATE TABLE images (
    id SERIAL PRIMARY KEY,
    url VARCHAR(255),
    description VARCHAR(255),
    product_id BIGINT NOT NULL
);

ALTER TABLE images
ADD CONSTRAINT fk_product_id FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE;
