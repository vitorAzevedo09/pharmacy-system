CREATE TABLE users (
    id BIGINT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

-- Altering the users table to include dtype column
ALTER TABLE users
ADD COLUMN dtype VARCHAR(50) DEFAULT 'user';
