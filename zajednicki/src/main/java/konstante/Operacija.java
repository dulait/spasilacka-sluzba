package konstante;

/**
 * Ova klasa sadrži skup konstanti koje se koriste za definisanje raznih
 * operacija unutar sistema. Svaka konstanta predstavlja specifičnu operaciju
 * ili kod statusa koji se može koristiti u kontrolerima i drugim delovima
 * aplikacije za upravljanje različitim tipovima akcija ili odgovora.
 * <p>
 * Konstante uključuju kodove operacija za kreiranje, ažuriranje, brisanje i
 * učitavanje različitih entiteta, kao i kodove statusa za uspeh i neuspeh.
 * </p>
 *
 * @author dulait
 */
public class Operacija {

    /**
     * Kod statusa koji označava neuspeh.
     */
    public static final int NEUSPEH = 0;

    /**
     * Kod statusa koji označava uspeh.
     */
    public static final int USPEH = 1;

    /**
     * Kod operacije za registraciju koordinatora.
     */
    public static final int PRIJAVI_KOORDINATORA = 2;

    /**
     * Kod operacije za kreiranje spasioca.
     */
    public static final int KREIRAJ_SPASIOCA = 3;

    /**
     * Kod operacije za kreiranje smene.
     */
    public static final int KREIRAJ_SMENU = 4;

    /**
     * Kod operacije za kreiranje angažovanja.
     */
    public static final int KREIRAJ_ANGAZOVANJE = 5;

    /**
     * Kod operacije za kreiranje rasporeda.
     */
    public static final int KREIRAJ_RASPORED = 6;

    /**
     * Kod operacije za kreiranje izveštaja.
     */
    public static final int KREIRAJ_IZVESTAJ = 7;

    /**
     * Kod operacije za učitavanje koordinatora.
     */
    public static final int UCITAJ_KOORDINATORA = 8;

    /**
     * Kod operacije za učitavanje spasioca.
     */
    public static final int UCITAJ_SPASIOCA = 9;

    /**
     * Kod operacije za učitavanje smene.
     */
    public static final int UCITAJ_SMENU = 10;

    /**
     * Kod operacije za učitavanje angažovanja.
     */
    public static final int UCITAJ_ANGAZOVANJE = 11;

    /**
     * Kod operacije za učitavanje rasporeda.
     */
    public static final int UCITAJ_RASPORED = 12;

    /**
     * Kod operacije za učitavanje izveštaja.
     */
    public static final int UCITAJ_IZVESTAJ = 13;

    /**
     * Kod operacije za ažuriranje spasioca.
     */
    public static final int AZURIRAJ_SPASIOCA = 14;

    /**
     * Kod operacije za ažuriranje smene.
     */
    public static final int AZURIRAJ_SMENU = 15;

    /**
     * Kod operacije za ažuriranje izveštaja.
     */
    public static final int AZURIRAJ_IZVESTAJ = 16;

    /**
     * Kod operacije za brisanje angažovanja.
     */
    public static final int OBRISI_ANGAZOVANJE = 17;

    /**
     * Kod operacije za brisanje rasporeda.
     */
    public static final int OBRISI_RASPORED = 18;

    /**
     * Kod operacije za pretraživanje spasilaca.
     */
    public static final int PRETRAZI_SPASIOCE = 19;

    /**
     * Kod operacije za učitavanje liste spasilaca.
     */
    public static final int UCITAJ_LISTU_SPASIOCA = 20;

    /**
     * Kod operacije za učitavanje liste smena.
     */
    public static final int UCITAJ_LISTU_SMENA = 21;

    /**
     * Kod operacije za učitavanje liste rasporeda.
     */
    public static final int UCITAJ_LISTU_RASPOREDA = 22;

    /**
     * Kod operacije za učitavanje liste angažovanja.
     */
    public static final int UCITAJ_LISTU_ANGAZOVANJA = 23;

    /**
     * Kod operacije za učitavanje liste izveštaja.
     */
    public static final int UCITAJ_LISTU_IZVESTAJA = 24;

    /**
     * Kod operacije za učitavanje liste koordinatora.
     */
    public static final int UCITAJ_LISTU_KOORDINATORA = 25;

    /**
     * Kod operacije za zatvaranje veze.
     */
    public static final int ZATVORI_KONEKCIJU = 26;

    /**
     * Kod operacije za zatvaranje servera.
     */
    public static final int ZATVORI_SERVER = 27;

    /**
     * Kod operacije za izvoz u JSON.
     */
    public static final int EXPORT_TO_JSON = 28;

}
