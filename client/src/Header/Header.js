import styles from "./Header.module.css";

function Header() {
  return (
    <div className={styles.headerContainer}>
      <p className={styles.headerLogo}>📓Workspaces/</p>
      <p className={styles.headerText}>
        A very VERY basic, Kanban style, project tracking tool.
      </p>
    </div>
  );
}

export default Header;
