import { useState } from "react";
import styles from "./WorkspacesContainer.module.css";

function WorkspacesContainer() {
  const [workspaces, setWorkspaces] = useState([{ id: 17, name: "lol" }]);

  async function fetchWorkspaces() {
    const response = await fetch("http://localhost:8080/api/workspaces");
    const workspaces = await response.json();
    console.log(workspaces);
    setWorkspaces(workspaces);
  }

  return (
    <div className={styles.workspacesContainer}>
      <p>Workspaces</p>
      <ul>
        {workspaces.map((workspace) => (
          <li key={workspace.id}>{workspace.name}</li>
        ))}
      </ul>
      <button onClick={fetchWorkspaces}>Fetch Workspaces!</button>
    </div>
  );
}

export default WorkspacesContainer;
