import { useState } from "react";
import styles from "./WorkspaceForm.module.css";

function WorkspaceForm({ fetchWorkspaces, setShowWorkspaceForm }) {
  const [nameInput, setNameInput] = useState("");
  const [error, setError] = useState("");

  const handleInput = (event) => {
    setNameInput(event.target.value);
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    if (!nameInput.trim().length) {
      setShowWorkspaceForm(false);
      return;
    }
    const response = await fetch("http://localhost:8080/api/workspaces", {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ name: nameInput }),
    });
    if (response.ok) {
      fetchWorkspaces();
      setShowWorkspaceForm(false);
    } else {
      const errorMsg = await response.text();
      console.log(errorMsg);
      setError(errorMsg);
    }
  };

  return (
    <form onSubmit={handleSubmit} className={styles.formContainer}>
      <label className={styles.labelContainer}>
        Name:
        <input
          autoFocus={true}
          onBlur={handleSubmit}
          type="text"
          name="name"
          value={nameInput}
          onChange={handleInput}
          className={styles.textInput}
        />
      </label>
      {error.length > 1 && <p className={styles.error}>{error}</p>}
    </form>
  );
}

export default WorkspaceForm;
