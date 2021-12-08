import { useState } from "react";
import Select from "react-select";
import styles from "./TicketForm.module.css";

function TicketForm({
  setShowTicketForm,
  selectedWorkspace,
  selectedTicket,
  name,
  type,
  status,
  setTickets,
  method,
}) {
  const [nameInput, setNameInput] = useState(name);
  const [typeInput, setTypeInput] = useState(type);
  const [statusInput, setStatusInput] = useState(status);
  const [toggleDropDown, setToggleDropDown] = useState(false);

  async function fetchTickets() {
    const response = await fetch(
      `http://localhost:8080/api/workspaces/${selectedWorkspace}/tickets`
    );
    const tickets = await response.json();
    console.log(tickets);
    setTickets(tickets);
  }

  async function postTicket(ticket) {
    const response = await fetch(
      `http://localhost:8080/api/workspaces/${selectedWorkspace}/tickets`,
      {
        method: "POST",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
        },
        body: JSON.stringify(ticket),
      }
    );
    return response;
  }

  async function updateTicket(ticket) {
    const response = await fetch(
      `http://localhost:8080/api/workspaces/${selectedWorkspace}/tickets/${selectedTicket.id}`,
      {
        method: "PUT",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
        },
        body: JSON.stringify(ticket),
      }
    );
    return response;
  }

  const handleSubmit = async (event) => {
    event.preventDefault();

    const ticket = { name: nameInput, type: typeInput, status: statusInput };

    let response;
    if (method === "POST") {
      response = await postTicket(ticket);
    } else {
      response = await updateTicket(ticket);
    }

    if (response.ok) {
      setShowTicketForm(false);
      fetchTickets();
    } else {
      const errorMsg = await response.text();
      console.log(errorMsg);
    }
  };

  const handleBackdropClick = async () => {
    console.log("Clicked backdrop!");
    setShowTicketForm(false);
  };

  const handleToggleDropDown = (event) => {
    event.preventDefault();
    setToggleDropDown(!toggleDropDown);
  };
  const handleNameInput = (event) => {
    setNameInput(event.target.value);
  };
  const handleTypeInput = (event) => {
    setTypeInput(event.target.value);
  };

  const handleOption = (event) => {
    event.preventDefault();
    setStatusInput(event.target.value);
    setToggleDropDown(!toggleDropDown);
  };

  return (
    <>
      <div
        onClick={handleBackdropClick}
        className={styles.backdropContainer}
      ></div>
      <div className={styles.formContainer}>
        <form onSubmit={handleSubmit}>
          <input
            type="text"
            name="name"
            placeholder="New ticket"
            value={nameInput}
            onChange={handleNameInput}
            className={styles.nameInput}
          ></input>
          <div className={styles.detailsContainer}>
            <div className={styles.labels}>
              <p className={styles.label}>Type</p>
              <p className={styles.label}>Status</p>
            </div>
            <div className={styles.inputs}>
              <input
                type="text"
                name="type"
                value={typeInput}
                onChange={handleTypeInput}
                className={styles.typeInput}
              ></input>
              <div className={styles.statusContainer}>
                <div className={styles.dropDown}>
                  <div>
                    <button
                      className={styles.optionsButton}
                      onClick={handleToggleDropDown}
                    >
                      {statusInput}
                    </button>
                  </div>
                  {toggleDropDown && (
                    <>
                      <button
                        onClick={handleOption}
                        value="to-do"
                        className={styles.option}
                      >
                        to-do
                      </button>
                      <button
                        onClick={handleOption}
                        value="in-progress"
                        className={styles.option}
                      >
                        in-progress
                      </button>
                      <button
                        onClick={handleOption}
                        value="completed"
                        className={styles.option}
                      >
                        completed
                      </button>
                    </>
                  )}
                </div>
              </div>
            </div>
          </div>
          <button className={styles.submitButton}>Save</button>
        </form>
      </div>
    </>
  );
}

export default TicketForm;
