CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    phone_number VARCHAR(255) UNIQUE,
    enabled BOOLEAN NOT NULL DEFAULT TRUE,
    dtype VARCHAR(50) DEFAULT 'user'
);

CREATE TABLE customers (
    id serial PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users(id),
    company VARCHAR(255)
);


