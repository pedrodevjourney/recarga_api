CREATE TABLE users (
    id UUID PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    CONSTRAINT uk_users_email UNIQUE (email)
);

CREATE INDEX idx_users_email ON users (email);
