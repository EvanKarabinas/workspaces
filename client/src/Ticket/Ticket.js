import styles from "./Ticket.module.css";

function Ticket({ ticket }) {
  return (
    <div className={styles.ticketContainer}>
      <p className={styles.ticketTitle}>{ticket.name}</p>
      <div className={styles.ticketDetailsContainer}>
        <span className={styles.ticketDetailSpan}>
          <p>Type:</p>
          <p>{ticket.type}</p>
        </span>
        <span className={styles.ticketDetailSpan}>
          <p>Status:</p>
          <p>{ticket.status}</p>
        </span>
      </div>
    </div>
  );
}

export default Ticket;
