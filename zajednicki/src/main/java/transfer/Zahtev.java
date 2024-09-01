package transfer;

import java.io.Serializable;

/**
 * Predstavlja zahtev poslat od klijenta serveru.
 *
 * @author dulait
 */
public class Zahtev implements Serializable {

    private static final long serialVersionUID = 1L;

    private Object parametar;
    private int operacija;

    /**
     * Podrazumevajući konstruktor.
     */
    public Zahtev() {
    }

    /**
     * Konstruktor koji kreira Zahtev sa određenim parametrom i kodom operacije.
     *
     * @param parametar parametar ili podaci povezani sa zahtevom
     * @param operacija kod operacije koji označava tip operacije koju treba
     * izvršiti
     */
    public Zahtev(Object parametar, int operacija) {
        this.parametar = parametar;
        this.operacija = operacija;
    }

    /**
     * Vraća kod operacije ovog zahteva.
     *
     * @return kod operacije
     */
    public int getOperacija() {
        return operacija;
    }

    /**
     * Postavlja kod operacije za ovaj zahtev.
     *
     * @param operacija kod operacije koji treba postaviti
     */
    public void setOperacija(int operacija) {
        this.operacija = operacija;
    }

    /**
     * Vraća parametar ili podatke povezane sa ovim zahtevom.
     *
     * @return parametar ili podaci
     */
    public Object getParametar() {
        return parametar;
    }

    /**
     * Postavlja parametar ili podatke za ovaj zahtev.
     *
     * @param parametar parametar ili podaci koji treba postaviti
     */
    public void setParametar(Object parametar) {
        this.parametar = parametar;
    }
}
