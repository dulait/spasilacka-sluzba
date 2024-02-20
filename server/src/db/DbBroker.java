package db;

import config.Config;
import domen.OpstiDomenskiObjekat;
import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbBroker {

    private static DbBroker instanca;
    private Connection konekcija;

    private DbBroker() {
    }

    public static DbBroker getInstanca() {
        if (instanca == null) {
            instanca = new DbBroker();
        }
        return instanca;
    }

    public void otvoriKonekciju() {
        try {
            konekcija = DriverManager.getConnection(Config.getDatabaseUrl(), Config.getUsername(), Config.getPassword());
            konekcija.setAutoCommit(false);
        } catch (SQLException ex) {
            System.err.println("Neuspesno konektovanje sa bazom");
        }
    }

    public void zatvoriKonekciju() {
        try {
            konekcija.close();
        } catch (SQLException ex) {
            Logger.getLogger(DbBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void commit() {
        try {
            konekcija.commit();
        } catch (SQLException ex) {
            Logger.getLogger(DbBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void rollback() {
        try {
            konekcija.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(DbBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public synchronized List<OpstiDomenskiObjekat> getAllOpstiDomenskiObjekats(OpstiDomenskiObjekat o) {
        try {
            String upit = o.getSelectUpit();
            List<OpstiDomenskiObjekat> lista;
            try (Statement s = konekcija.createStatement()) {
                System.out.println(upit);
                ResultSet rs = s.executeQuery(upit);
                lista = o.konvertujRSUListu(rs);
            }

            return lista;
        } catch (SQLException ex) {
            System.err.println("Greska prilikom vracanja opstih domenskih objkekata tabele: " + o.getNazivTabele());
        }
        return null;
    }

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
            System.err.println("Greska prilikom vracanja opstih domenskih objkekata tabele: " + o.getNazivTabele());
        }
        return null;
    }

    public synchronized boolean saveOpstiDomenskiObjekat(OpstiDomenskiObjekat o) {
        try {
            String upit = o.getInsertUpit();
            try (Statement s = konekcija.createStatement()) {
                s.executeUpdate(upit);
            }
            return true;
        } catch (SQLException ex) {
            System.err.println("Greska prilikom cuvanja novog domenskog objekta u tabelu: " + o.getNazivTabele() + "Greska: " + ex.getMessage());
        }
        return false;
    }

    public synchronized boolean updateOpstiDomenskiObjekat(OpstiDomenskiObjekat o) {
        try {
            String upit = o.getUpdateUpit();
            try (Statement s = konekcija.createStatement()) {
                s.executeUpdate(upit);
            }
            return true;
        } catch (SQLException ex) {
            System.out.println("Greska prilikom azuriranja objekta: " + o.getNazivTabele() + " Greska: " + ex.getMessage());
        }
        return false;
    }

    public synchronized boolean deleteOpstiDomenskiObjekat(OpstiDomenskiObjekat o) {
        try {
            String upit = o.getDeleteUpit();
            try (Statement s = konekcija.createStatement()) {
                s.executeUpdate(upit);
            }
            return true;
        } catch (SQLException ex) {
            System.out.println("Greska prilikom brisanja objekta: " + o.getNazivTabele() + " Greska: " + ex.getMessage());
        }
        return false;
    }

}
