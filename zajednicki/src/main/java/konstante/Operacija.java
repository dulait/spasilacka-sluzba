package konstante;

/**
 * This class contains a set of constants used to define various operations
 * within the system. Each constant represents a specific operation or status
 * code that can be used in controllers and other parts of the application to
 * manage different types of actions or responses.
 * <p>
 * The constants include operation codes for creating, updating, deleting, and
 * loading different entities, as well as status codes for success and failure.
 * </p>
 *
 * @author dulait
 */
public class Operacija {

    /**
     * Status code indicating a failure.
     */
    public static final int NEUSPEH = 0;

    /**
     * Status code indicating success.
     */
    public static final int USPEH = 1;

    /**
     * Operation code for registering a coordinator.
     */
    public static final int PRIJAVI_KOORDINATORA = 2;

    /**
     * Operation code for creating a lifeguard.
     */
    public static final int KREIRAJ_SPASIOCA = 3;

    /**
     * Operation code for creating a shift.
     */
    public static final int KREIRAJ_SMENU = 4;

    /**
     * Operation code for creating an engagement.
     */
    public static final int KREIRAJ_ANGAZOVANJE = 5;

    /**
     * Operation code for creating a schedule.
     */
    public static final int KREIRAJ_RASPORED = 6;

    /**
     * Operation code for creating a report.
     */
    public static final int KREIRAJ_IZVESTAJ = 7;

    /**
     * Operation code for loading a coordinator.
     */
    public static final int UCITAJ_KOORDINATORA = 8;

    /**
     * Operation code for loading a lifeguard.
     */
    public static final int UCITAJ_SPASIOCA = 9;

    /**
     * Operation code for loading a shift.
     */
    public static final int UCITAJ_SMENU = 10;

    /**
     * Operation code for loading an engagement.
     */
    public static final int UCITAJ_ANGAZOVANJE = 11;

    /**
     * Operation code for loading a schedule.
     */
    public static final int UCITAJ_RASPORED = 12;

    /**
     * Operation code for loading a report.
     */
    public static final int UCITAJ_IZVESTAJ = 13;

    /**
     * Operation code for updating a lifeguard.
     */
    public static final int AZURIRAJ_SPASIOCA = 14;

    /**
     * Operation code for updating a shift.
     */
    public static final int AZURIRAJ_SMENU = 15;

    /**
     * Operation code for updating a report.
     */
    public static final int AZURIRAJ_IZVESTAJ = 16;

    /**
     * Operation code for deleting an engagement.
     */
    public static final int OBRISI_ANGAZOVANJE = 17;

    /**
     * Operation code for deleting a schedule.
     */
    public static final int OBRISI_RASPORED = 18;

    /**
     * Operation code for searching lifeguards.
     */
    public static final int PRETRAZI_SPASIOCE = 19;

    /**
     * Operation code for loading a list of lifeguards.
     */
    public static final int UCITAJ_LISTU_SPASIOCA = 20;

    /**
     * Operation code for loading a list of shifts.
     */
    public static final int UCITAJ_LISTU_SMENA = 21;

    /**
     * Operation code for loading a list of schedules.
     */
    public static final int UCITAJ_LISTU_RASPOREDA = 22;

    /**
     * Operation code for loading a list of engagements.
     */
    public static final int UCITAJ_LISTU_ANGAZOVANJA = 23;

    /**
     * Operation code for loading a list of reports.
     */
    public static final int UCITAJ_LISTU_IZVESTAJA = 24;

    /**
     * Operation code for loading a list of coordinators.
     */
    public static final int UCITAJ_LISTU_KOORDINATORA = 25;

    /**
     * Operation code for closing a connection.
     */
    public static final int ZATVORI_KONEKCIJU = 26;

    /**
     * Operation code for closing the server.
     */
    public static final int ZATVORI_SERVER = 27;

}
