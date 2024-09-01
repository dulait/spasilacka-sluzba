package db;

import config.Config;
import domen.OpstiDomenskiObjekat;

import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Klasa koja pruža operacije za komunikaciju sa bazom.
 *
 * @author dulait
 */
public class DbBroker {

    private static DbBroker instanca;
    private Connection konekcija;

    /**
     * Privatni konstruktor kako bi se sprečila direktna instanciranja.
     */
    private DbBroker() {
    }

    /**
     * Vraća singleton instancu {@code DbBroker}. Ako instanca ne postoji, ona
     * se kreira i inicijalizuje.
     *
     * @return singleton instanca {@code DbBroker}
     */
    public static DbBroker getInstanca() {
        if (instanca == null) {
            instanca = new DbBroker();
        }
        return instanca;
    }

    /**
     * Otvara konekciju ka bazi koristeći podešavanja iz klase {@code Config}.
     */
    public void otvoriKonekciju() {
        try {
            konekcija = DriverManager.getConnection(
                    Config.getDatabaseUrl(),
                    Config.getUsername(),
                    Config.getPassword()
            );
            konekcija.setAutoCommit(false);
        } catch (SQLException ex) {
            System.err.println("Neuspešno povezivanje sa bazom : " + ex.getMessage());
        }
    }

    /**
     * Zatvara trenutnu konekciju ka bazi .
     */
    public void zatvoriKonekciju() {
        try {
            if (konekcija != null && !konekcija.isClosed()) {
                konekcija.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbBroker.class.getName()).log(Level.SEVERE, "Greška prilikom zatvaranja konekcije sa bazom podataka", ex);
        }
    }

    /**
     * Komituje trenutnu transakciju.
     */
    public void commit() {
        try {
            if (konekcija != null && !konekcija.isClosed()) {
                konekcija.commit();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbBroker.class.getName()).log(Level.SEVERE, "Greška prilikom komitovanja transakcije", ex);
        }
    }

    /**
     * Rollbackuje trenutnu transakciju.
     */
    public void rollback() {
        try {
            if (konekcija != null && !konekcija.isClosed()) {
                konekcija.rollback();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbBroker.class.getName()).log(Level.SEVERE, "Greška prilikom rollbackovanja transakcije", ex);
        }
    }

    /**
     * Vraća listu domenskih bjekata.
     *
     * @param o tip domenskog objekta koji se koristi za generisanje SELECT
     * upita
     * @return lista domenskih objekata preuzetih iz baze, ili {@code null} ako
     * dođe do greške
     */
    public synchronized List<OpstiDomenskiObjekat> getAllOpstiDomenskiObjekats(OpstiDomenskiObjekat o) {
        try {
            String upit = o.getSelectUpit();
            List<OpstiDomenskiObjekat> lista;
            try (Statement s = konekcija.createStatement()) {
                ResultSet rs = s.executeQuery(upit);
                lista = o.konvertujRSUListu(rs);
            }
            return lista;
        } catch (SQLException ex) {
            System.err.println("Greška prilikom preuzimanja domenskog objekata iz tabele: " + o.getNazivTabele());
        }
        return null;
    }

    /**
     * Vraća jedan domenski objekat iz baze na osnovu parametara.
     *
     * @param o domenski objekat koji se koristi za generisanje SELECT upita po
     * parametru
     * @return domenski objekat vraćen iz baze , ili {@code null} ako nije
     * pronađen objekat ili dođe do greške
     */
    public synchronized OpstiDomenskiObjekat getOpstiDomenskiObjekatPoParametru(OpstiDomenskiObjekat o) {
        try {
            String upit = o.getSelectUpitPoParametru();
            List<OpstiDomenskiObjekat> lista;
            try (Statement s = konekcija.createStatement()) {
                ResultSet rs = s.executeQuery(upit);
                lista = o.konvertujRSUListu(rs);
            }
            return lista.isEmpty() ? null : lista.get(0);
        } catch (SQLException ex) {
            System.err.println("Greška prilikom preuzimanja domenskog objekta iz tabele: " + o.getNazivTabele());
        }
        return null;
    }

    /**
     * Ubacuje domenski objekat u bazu.
     *
     * @param o domenski objekat koji treba umetnuti u bazu
     * @return {@code true} ako je umetanje uspešno, {@code false} inače
     */
    public synchronized boolean saveOpstiDomenskiObjekat(OpstiDomenskiObjekat o) {
        try {
            String upit = o.getInsertUpit();
            try (Statement s = konekcija.createStatement()) {
                s.executeUpdate(upit);
            }
            return true;
        } catch (SQLException ex) {
            System.err.println("Greška prilikom čuvanja novog domenskog objekta u tabeli: " + o.getNazivTabele() + " Greška: " + ex.getMessage());
        }
        return false;
    }

    /**
     * Ažurira postojeći domenski objekat u bazi .
     *
     * @param o domenski objekat koji treba ažurirati u bazi
     * @return {@code true} ako je ažuriranje uspešno, {@code false} inače
     */
    public synchronized boolean updateOpstiDomenskiObjekat(OpstiDomenskiObjekat o) {
        try {
            String upit = o.getUpdateUpit();
            try (Statement s = konekcija.createStatement()) {
                s.executeUpdate(upit);
            }
            return true;
        } catch (SQLException ex) {
            System.err.println("Greška prilikom ažuriranja objekta u tabeli: " + o.getNazivTabele() + " Greška: " + ex.getMessage());
        }
        return false;
    }

    /**
     * Briše domenski objekat iz baze .
     *
     * @param o domenski objekat koji treba obrisati iz baze
     * @return {@code true} ako je brisanje uspešno, {@code false} inače
     */
    public synchronized boolean deleteOpstiDomenskiObjekat(OpstiDomenskiObjekat o) {
        try {
            String upit = o.getDeleteUpit();
            try (Statement s = konekcija.createStatement()) {
                s.executeUpdate(upit);
            }
            return true;
        } catch (SQLException ex) {
            System.err.println("Greška prilikom brisanja domenskog objekta iz tabele: " + o.getNazivTabele() + " Greška: " + ex.getMessage());
        }
        return false;
    }
}
