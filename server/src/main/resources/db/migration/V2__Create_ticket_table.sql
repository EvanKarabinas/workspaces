CREATE TABLE tickets(
    id SERIAL PRIMARY KEY,
    workspace_id INT,
    name TEXT NOT NULL,
    type VARCHAR(255),
    CONSTRAINT fk_workspace
          FOREIGN KEY(workspace_id)
    	  REFERENCES workspaces(id)
)