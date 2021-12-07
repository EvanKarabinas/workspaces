import { useEffect, useState } from "react";
import TicketStatusColumn from "../TicketStatusColumn/TicketStatusColumn";
import styles from "./TicketsContainer.module.css";

function TicketsContainer({ selectedWorkspace }) {
  const [tickets, setTickets] = useState([]);

  useEffect(() => {
    console.log(`* Tickets effect *`);
    async function fetchTickets() {
      const response = await fetch(
        `http://localhost:8080/api/workspaces/${selectedWorkspace}/tickets`
      );
      const tickets = await response.json();
      console.log(tickets);
      setTickets(tickets);
    }

    if (selectedWorkspace) {
      console.log(`Fetching tickets for workspace: ${selectedWorkspace}`);
      fetchTickets();
    }
  }, [selectedWorkspace]);

  return (
    <div className={styles.ticketsContainer}>
      <p className={styles.ticketsLabel}>🔖 Tickets</p>
      <div className={styles.columnsContainer}>
        <TicketStatusColumn tickets={tickets} columnStatus={"To Do"} />
        <TicketStatusColumn tickets={tickets} columnStatus={"In Progress"} />
        <TicketStatusColumn tickets={tickets} columnStatus={"Completed"} />
      </div>
    </div>
  );
}

export default TicketsContainer;
