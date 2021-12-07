import { useEffect, useState } from "react";
import WorkspaceForm from "../WorkspaceForm/WorkspaceForm";

import styles from "./WorkspacesContainer.module.css";

function WorkspacesContainer({ selectedWorkspace, setSelectedWorkspace }) {
  const [workspaces, setWorkspaces] = useState([]);
  const [showWorkspaceForm, setShowWorkspaceForm] = useState(false);

  async function fetchWorkspaces() {
    const response = await fetch("http://localhost:8080/api/workspaces");
    const workspaces = await response.json();
    console.log(workspaces);
    setWorkspaces(workspaces);
  }

  useEffect(() => {
    fetchWorkspaces();
  }, []);

  useEffect(() => {
    if (selectedWorkspace == null && workspaces.length) {
      setSelectedWorkspace(workspaces[0].id);
      console.log(workspaces[0].id);
    }
  });

  return (
    <div className={styles.workspacesContainer}>
      <p className={styles.workspacesContainerHeader}>Workspaces</p>
      <ul className={styles.workspacesList}>
        {workspaces.map((workspace) => (
          <button
            className={
              workspace.id === selectedWorkspace
                ? styles.workspaceSelected
                : styles.workspace
            }
            key={workspace.id}
            onClick={() => {
              setSelectedWorkspace(workspace.id);
            }}
          >
            {workspace.name}
          </button>
        ))}
      </ul>

      {showWorkspaceForm ? (
        <WorkspaceForm
          setShowWorkspaceForm={setShowWorkspaceForm}
          fetchWorkspaces={fetchWorkspaces}
        />
      ) : (
        <button
          className={styles.addWorkspaceButton}
          onClick={() => setShowWorkspaceForm(true)}
        >
          + Add Workspace
        </button>
      )}
    </div>
  );
}

export default WorkspacesContainer;
