CREATE TABLE workspace(
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    UNIQUE (name)
)