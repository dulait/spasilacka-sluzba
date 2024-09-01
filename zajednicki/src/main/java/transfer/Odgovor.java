package transfer;

import java.io.Serializable;

/**
 * Predstavlja odgovor poslat od servera klijentu.
 *
 * @author dulait
 */
public class Odgovor implements Serializable {

    private static final long serialVersionUID = 1L; // Dodato za kompatibilnost sa serijalizacijom

    private Object odgovor;
    private int uspeh;
    private String poruka;

    /**
     * Podrazumevajući konstruktor za kreiranje praznog {@code Odgovor} objekta.
     */
    public Odgovor() {
    }

    /**
     * Konstruktor koji kreira {@code Odgovor} sa određenim podacima odgovora,
     * statusom uspeha i porukom.
     *
     * @param odgovor podaci ili rezultat operacije
     * @param uspeh ceo broj koji označava status uspeha operacije
     * @param poruka poruka povezana sa odgovorom
     */
    public Odgovor(Object odgovor, int uspeh, String poruka) {
        this.odgovor = odgovor;
        this.uspeh = uspeh;
        this.poruka = poruka;
    }

    /**
     * Vraća podatke ili rezultat operacije.
     *
     * @return podaci odgovora
     */
    public Object getOdgovor() {
        return odgovor;
    }

    /**
     * Postavlja podatke ili rezultat operacije.
     *
     * @param odgovor podaci odgovora koje treba postaviti
     */
    public void setOdgovor(Object odgovor) {
        this.odgovor = odgovor;
    }

    /**
     * Vraća poruku povezanu sa odgovorom.
     *
     * @return poruka
     */
    public String getPoruka() {
        return poruka;
    }

    /**
     * Postavlja poruku povezanu sa odgovorom.
     *
     * @param poruka poruka koju treba postaviti
     */
    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    /**
     * Vraća status uspeha operacije.
     *
     * @return status uspeha.
     */
    public int getUspeh() {
        return uspeh;
    }

    /**
     * Postavlja status uspeha operacije.
     *
     * @param uspeh status uspeha koji treba postaviti.
     */
    public void setUspeh(int uspeh) {
        this.uspeh = uspeh;
    }
}
