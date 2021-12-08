import styles from "./Ticket.module.css";

function Ticket({ ticket, handleEdit, handleDelete }) {
  const emojiMap = {
    bug: "🐞",
    task: "🔨",
  };

  return (
    <div className={styles.ticketContainer}>
      <p className={styles.ticketTitle}>{ticket.name}</p>
      <div className={styles.ticketDetailsContainer}>
        <span className={styles.ticketDetailSpan}>
          <p className={styles.label}>Type:</p>
          <p className={styles.type}>
            {emojiMap[ticket.type]} {ticket.type}
          </p>
        </span>
        <span className={styles.ticketDetailSpan}>
          <p className={styles.label}>Status:</p>
          <p className={styles.status}>{ticket.status}</p>
        </span>
      </div>
      <div className={styles.buttons}>
        <button
          onClick={() => handleEdit(ticket)}
          className={styles.editButton}
        >
          ⚙︎
        </button>
        <button
          onClick={() => handleDelete(ticket)}
          className={styles.deleteButton}
        >
          🗑
        </button>
      </div>
    </div>
  );
}

export default Ticket;
