package transfer;

import java.io.Serializable;

/**
 * Represents a request sent from a client to the server.
 * <p>
 * The {@code Zahtev} class is used to encapsulate the parameters and the type
 * of operation that a client wants to perform. It is designed to be serialized
 * for transmission over a network.
 * </p>
 *
 * @author dulait
 */
public class Zahtev implements Serializable {

    private static final long serialVersionUID = 1L;

    private Object parametar;
    private int operacija;

    /**
     * Default constructor for creating an empty {@code Zahtev} object.
     */
    public Zahtev() {
    }

    /**
     * Constructs a {@code Zahtev} with the specified parameter and operation
     * code.
     *
     * @param parametar the parameter or data associated with the request
     * @param operacija the operation code indicating the type of operation to
     * perform
     */
    public Zahtev(Object parametar, int operacija) {
        this.parametar = parametar;
        this.operacija = operacija;
    }

    /**
     * Returns the operation code of this request.
     *
     * @return the operation code
     */
    public int getOperacija() {
        return operacija;
    }

    /**
     * Sets the operation code for this request.
     *
     * @param operacija the operation code to set
     */
    public void setOperacija(int operacija) {
        this.operacija = operacija;
    }

    /**
     * Returns the parameter or data associated with this request.
     *
     * @return the parameter or data
     */
    public Object getParametar() {
        return parametar;
    }

    /**
     * Sets the parameter or data for this request.
     *
     * @param parametar the parameter or data to set
     */
    public void setParametar(Object parametar) {
        this.parametar = parametar;
    }

}
