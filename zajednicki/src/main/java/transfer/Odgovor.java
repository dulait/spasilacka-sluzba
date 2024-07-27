package transfer;

import java.io.Serializable;

/**
 * Represents a response sent from the server to the client.
 * <p>
 * The {@code Odgovor} class encapsulates the data returned by the server in
 * response to a client's request. It includes information about the success of
 * the operation, the response data, and any relevant message.
 * </p>
 *
 * @author dulait
 */
public class Odgovor implements Serializable {

    private static final long serialVersionUID = 1L; // Added for serialization compatibility

    private Object odgovor;
    private int uspeh;
    private String poruka;

    /**
     * Default constructor for creating an empty {@code Odgovor} object.
     */
    public Odgovor() {
    }

    /**
     * Constructs an {@code Odgovor} with the specified response data, success
     * status, and message.
     *
     * @param odgovor the data or result of the operation
     * @param uspeh an integer indicating the success status of the operation
     * @param poruka a message associated with the response
     */
    public Odgovor(Object odgovor, int uspeh, String poruka) {
        this.odgovor = odgovor;
        this.uspeh = uspeh;
        this.poruka = poruka;
    }

    /**
     * Returns the data or result of the operation.
     *
     * @return the response data
     */
    public Object getOdgovor() {
        return odgovor;
    }

    /**
     * Sets the data or result of the operation.
     *
     * @param odgovor the response data to set
     */
    public void setOdgovor(Object odgovor) {
        this.odgovor = odgovor;
    }

    /**
     * Returns the message associated with the response.
     *
     * @return the message
     */
    public String getPoruka() {
        return poruka;
    }

    /**
     * Sets the message associated with the response.
     *
     * @param poruka the message to set
     */
    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    /**
     * Returns the success status of the operation.
     * <p>
     * This value indicates whether the operation was successful or not.
     * </p>
     *
     * @return the success status (e.g., {@link konstante.Operacija#USPEH} or
     * {@link konstante.Operacija#NEUSPEH})
     */
    public int getUspeh() {
        return uspeh;
    }

    /**
     * Sets the success status of the operation.
     *
     * @param uspeh the success status to set (e.g.,
     * {@link konstante.Operacija#USPEH} or {@link konstante.Operacija#NEUSPEH})
     */
    public void setUspeh(int uspeh) {
        this.uspeh = uspeh;
    }

}
