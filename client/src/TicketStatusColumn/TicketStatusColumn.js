import Ticket from "../Ticket/Ticket";
import styles from "./TicketStatusColumn.module.css";

function TicketStatusColumn({ columnStatus, tickets }) {
  const classMap = {
    "to do": styles.toDoLabel,
    "in progress": styles.inProgressLabel,
    completed: styles.completedLabel,
  };

  const emojiMap = {
    "to do": " ✏️",
    "in progress": " ⏳",
    completed: " ✅",
  };

  return (
    <div className={styles.column}>
      <p className={classMap[columnStatus.toLowerCase()]}>
        {columnStatus}
        {emojiMap[columnStatus.toLowerCase()]}
      </p>
      <ul className={styles.ticketList}>
        {tickets.map((ticket) => {
          if (ticket.status === columnStatus.toLowerCase()) {
            return <Ticket key={ticket.id} ticket={ticket} />;
          }
        })}
      </ul>
    </div>
  );
}

export default TicketStatusColumn;
