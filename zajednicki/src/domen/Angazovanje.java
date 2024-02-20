package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Angazovanje extends OpstiDomenskiObjekat {

    private Spasilac spasilac;
    private Smena smena;
    private Raspored raspored;

    public Angazovanje() {
    }

    public Angazovanje(Spasilac spasilac, Smena smena, Raspored raspored) {
        this.spasilac = spasilac;
        this.smena = smena;
        this.raspored = raspored;
    }

    public Spasilac getSpasilac() {
        return spasilac;
    }

    public void setSpasilac(Spasilac spasilac) {
        this.spasilac = spasilac;
    }

    public Smena getSmena() {
        return smena;
    }

    public void setSmena(Smena smena) {
        this.smena = smena;
    }

    public Raspored getRaspored() {
        return raspored;
    }

    public void setRaspored(Raspored raspored) {
        this.raspored = raspored;
    }

    @Override
    public String toString() {
        return spasilac.toString() + ", " + smena.toString() + ", " + raspored.toString();
    }

    @Override
    public String getNazivTabele() {
        return "angazovanje";
    }

    @Override
    public String getParametre() {
        return String.format("%d, %d, %d", spasilac.getId(), smena.getId(), raspored.getId());
    }

    @Override
    public String getNaziveParametara() {
        return "spasilacId, smenaId, rasporedId";
    }

    @Override
    public String getNazivPrimarnogKljuca() {
        return null;
    }

    @Override
    public Integer getVrednostPrimarnogKljuca() {
        return null;
    }

    @Override
    public String getSlozeniPrimarniKljuc() {
        return "spasilacId = " + spasilac.getId() + " AND smenaId = " + smena.getId() + " AND rasporedId = " + raspored.getId();
    }

    @Override
    public ArrayList<OpstiDomenskiObjekat> konvertujRSUListu(ResultSet rs) {
        ArrayList<OpstiDomenskiObjekat> angazovanja = new ArrayList<>();
        try {
            while (rs.next()) {
                Spasilac rsSpasilac = new Spasilac(
                        rs.getInt("spa.id"),
                        rs.getString("spa.ime"),
                        rs.getString("spa.prezime"),
                        rs.getString("spa.jmbg")
                );
                Smena rsSmena = new Smena(
                        rs.getInt("sme.id"),
                        rs.getInt("sme.pocetak"),
                        rs.getInt("sme.kraj")
                );
                Raspored rsRaspored = new Raspored(
                        rs.getInt("ras.id"),
                        rs.getDate("ras.datum").toLocalDate()
                );

                angazovanja.add(new Angazovanje(rsSpasilac, rsSmena, rsRaspored));
            }
        } catch (SQLException e) {
            System.out.println("Greska u Angazovanje::konvertujRSUListu\n" + e.getMessage());
        }
        return angazovanja;
    }

    @Override
    public String getSelectUpit() {
        return "SELECT ang.*, spa.*, sme.*, ras.* "
                + "FROM angazovanje ang "
                + "JOIN spasilac spa ON ang.spasilacId = spa.id "
                + "JOIN smena sme ON ang.smenaId = sme.id "
                + "JOIN raspored ras ON ang.rasporedId = ras.id";
    }

    @Override
    public String getSelectUpitPoParametru() {
        return "SELECT ang.*, spa.*, sme.*, ras.* "
                + "FROM angazovanje ang "
                + "JOIN spasilac spa ON ang.spasilacId = spa.id "
                + "JOIN smena sme ON ang.smenaId = sme.id "
                + "JOIN raspored ras ON ang.rasporedId = ras.id "
                + "WHERE " + getSlozeniPrimarniKljuc();
    }

    @Override
    public String getInsertUpit() {
        return "INSERT INTO " + getNazivTabele() + "(" + getNaziveParametara() + ")" + " VALUES (" + getParametre() + ")";
    }

    @Override
    public String getUpdateUpit() {
        return "UPDATE " + getNazivTabele() + " SET " + getUpdateParametre() + " WHERE " + getSlozeniPrimarniKljuc();
    }

    @Override
    public String getUpdateParametre() {
        return String.format("spasilacId = %d, smenaId = %d, rasporedId = %d",
                getSpasilac().getId(), getSmena().getId(), getRaspored().getId());
    }

    @Override
    public String getDeleteUpit() {
        return "DELETE FROM " + getNazivTabele() + " WHERE " + getSlozeniPrimarniKljuc();
    }

}
