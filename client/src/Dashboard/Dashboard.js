import { useState } from "react";
import WorkspacesContainer from "../WorkspacesContainer/WorkspacesContainer";
import TicketsContainer from "../TicketsContainer/TicketsContainer";
import styles from "./Dashboard.module.css";

function Dashboard() {
  const [tickets, setTickets] = useState([]);

  async function fetchWorkspaces() {
    const response = await fetch("http://localhost:8080/api/workspaces");
    const workspaces = await response.json();
    console.log(workspaces);
  }

  return (
    <div className={styles.dashboardContainer}>
      <p>Dashboard</p>
      <div className={styles.dashboardBody}>
        <WorkspacesContainer />
        <TicketsContainer />
      </div>
    </div>
  );
}

export default Dashboard;
