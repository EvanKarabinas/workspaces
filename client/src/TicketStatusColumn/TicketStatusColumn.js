import { useEffect, useState } from "react";
import Ticket from "../Ticket/Ticket";
import TicketForm from "../TicketForm/TicketForm";
import styles from "./TicketStatusColumn.module.css";

function TicketStatusColumn({
  columnStatus,
  tickets,
  setTickets,
  selectedWorkspace,
}) {
  const [showTicketForm, setShowTicketForm] = useState(false);
  const [selectedTicket, setSelectedTicket] = useState(null);
  const [method, setMethod] = useState("POST");
  const LabelMap = {
    "to-do": " To Do",
    "in-progress": "In Progress",
    completed: "Completed",
  };
  const classMap = {
    "to-do": styles.toDoLabel,
    "in-progress": styles.inProgressLabel,
    completed: styles.completedLabel,
  };

  const emojiMap = {
    "to-do": " ✏️",
    "in-progress": " ⏳",
    completed: " ✅",
  };

  async function fetchTickets() {
    const response = await fetch(
      `http://localhost:8080/api/workspaces/${selectedWorkspace}/tickets`
    );
    const tickets = await response.json();
    console.log(tickets);
    setTickets(tickets);
  }

  async function deleteTicket(ticket) {
    setSelectedTicket(ticket);
    const response = await fetch(
      `http://localhost:8080/api/workspaces/${selectedWorkspace}/tickets/${ticket.id}`,
      {
        method: "DELETE",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
        },
      }
    );
    return response;
  }

  function handleEdit(ticket) {
    setSelectedTicket(ticket);
    setMethod("PUT");
    setShowTicketForm(true);
  }

  async function handleDelete(ticket) {
    setSelectedTicket(ticket);
    let response = await deleteTicket(ticket);
    if (response.ok) {
      fetchTickets();
    } else {
      const errorMsg = await response.text();
      console.log(errorMsg);
    }
  }

  const handleNewTicketButton = (e) => {
    setMethod("POST");
    setShowTicketForm(true);
  };

  return (
    <div className={styles.column}>
      <p className={classMap[columnStatus]}>
        {LabelMap[columnStatus]}
        {emojiMap[columnStatus]}
      </p>
      <ul className={styles.ticketList}>
        {tickets.map((ticket) => {
          if (ticket.status === columnStatus) {
            return (
              <Ticket
                key={ticket.id}
                ticket={ticket}
                handleEdit={handleEdit}
                handleDelete={handleDelete}
              />
            );
          }
        })}
      </ul>
      <button
        className={styles.newTicketButton}
        onClick={handleNewTicketButton}
      >
        + Add Ticket
      </button>
      {showTicketForm && (
        <TicketForm
          setTickets={setTickets}
          setShowTicketForm={setShowTicketForm}
          selectedWorkspace={selectedWorkspace}
          selectedTicket={selectedTicket}
          name={method === "PUT" ? selectedTicket.name : ""}
          type={method === "PUT" ? selectedTicket.type : ""}
          status={method === "PUT" ? selectedTicket.status : columnStatus}
          method={method}
        />
      )}
    </div>
  );
}

export default TicketStatusColumn;
