import { useState } from "react";
import WorkspacesContainer from "../WorkspacesContainer/WorkspacesContainer";
import TicketsContainer from "../TicketsContainer/TicketsContainer";
import styles from "./Dashboard.module.css";

function Dashboard() {
  const [selectedWorkspace, setSelectedWorkspace] = useState(null);

  return (
    <div className={styles.dashboardContainer}>
      <p className={styles.dashboardLabel}>Dashboard</p>
      <div className={styles.dashboardBody}>
        <WorkspacesContainer
          selectedWorkspace={selectedWorkspace}
          setSelectedWorkspace={setSelectedWorkspace}
        />
        <TicketsContainer
          selectedWorkspace={selectedWorkspace}
          setSelectedWorkspace={setSelectedWorkspace}
        />
      </div>
    </div>
  );
}

export default Dashboard;
