CREATE TABLE workspaces(
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    UNIQUE (name)
)