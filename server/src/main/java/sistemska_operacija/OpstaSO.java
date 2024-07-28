package sistemska_operacija;

import db.DbBroker;

/**
 * Abstract base class for system operations that defines the template method
 * for executing a system operation.
 * <p>
 * This class encapsulates the process of opening a database connection,
 * executing a specific operation, committing the transaction, and handling
 * exceptions by rolling back and closing the connection if needed.
 * </p>
 * <p>
 * Concrete subclasses must implement the {@code izvrsiSpecificnuOperaciju}
 * method to define the specific operation to be performed.
 * </p>
 *
 * @see DbBroker
 * @see OpstaSO#izvrsiSpecificnuOperaciju()
 *
 * @author dulait
 */
public abstract class OpstaSO {

    /**
     * Executes the system operation in a transaction.
     * <p>
     * This method manages the entire process of executing a system operation,
     * including:
     * <ul>
     * <li>Opening a connection to the database.</li>
     * <li>Calling the {@code izvrsiSpecificnuOperaciju} method to perform the
     * specific operation.</li>
     * <li>Committing the transaction if the operation succeeds.</li>
     * <li>Rolling back the transaction and closing the connection in case of an
     * exception.</li>
     * </ul>
     * </p>
     * <p>
     * If an exception occurs during the operation, the transaction is rolled
     * back and the connection is closed.
     * </p>
     */
    public final void izvrsiSistemskuOperaciju() {
        try {
            DbBroker.getInstanca().otvoriKonekciju();
            izvrsiSpecificnuOperaciju();
            DbBroker.getInstanca().commit();
            DbBroker.getInstanca().zatvoriKonekciju();
        } catch (Exception ex) {
            DbBroker.getInstanca().rollback();
            DbBroker.getInstanca().zatvoriKonekciju();
        }
    }

    /**
     * Executes the specific operation defined by the subclass.
     * <p>
     * This method must be implemented by subclasses to define the actual
     * operation to be performed within the transaction.
     * </p>
     *
     */
    protected abstract void izvrsiSpecificnuOperaciju();
}
