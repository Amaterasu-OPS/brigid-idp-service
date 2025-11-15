CREATE TABLE IF NOT EXISTS users
(
    id UUID PRIMARY KEY NOT NULL DEFAULT gen_random_uuid(),
    name VARCHAR,
    family_name VARCHAR,
    birth_date DATE,
    gender VARCHAR,
    email TEXT UNIQUE,
    password TEXT,
    status INT DEFAULT 1,
    created_at TIMESTAMP DEFAULT now(),
    updated_at TIMESTAMP DEFAULT now()
);

CREATE INDEX IF NOT EXISTS idx_user_email ON users using btree(email);
CREATE INDEX IF NOT EXISTS idx_user_status ON users using hash(status);
