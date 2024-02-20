package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Raspored extends OpstiDomenskiObjekat implements Comparable<Raspored> {

    private int id;
    private LocalDate datum;
    private List<Angazovanje> angazovanja;

    public Raspored() {
    }

    public Raspored(int id) {
        this.id = id;
    }

    public Raspored(int id, LocalDate datum) {
        this.id = id;
        this.datum = datum;
        angazovanja = new ArrayList<>();
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Angazovanje> getAngazovanja() {
        return angazovanja;
    }

    public void setAngazovanja(List<Angazovanje> angazovanja) {
        this.angazovanja = angazovanja;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy", new Locale.Builder().setLanguage("sr").setScript("Latn").build());
        return formatter.format(datum);
    }

    @Override
    public int compareTo(Raspored o) {
        return this.getDatum().compareTo(o.getDatum());
    }

    @Override
    public String getNazivTabele() {
        return "raspored";
    }

    @Override
    public String getParametre() {
        return String.format("%d, '%s'", id, datum);
    }

    @Override
    public String getNaziveParametara() {
        return "id, datum";
    }

    @Override
    public String getNazivPrimarnogKljuca() {
        return "id";
    }

    @Override
    public Integer getVrednostPrimarnogKljuca() {
        return id;
    }

    @Override
    public String getSlozeniPrimarniKljuc() {
        return null;
    }

    @Override
    public ArrayList<OpstiDomenskiObjekat> konvertujRSUListu(ResultSet rs) {
        ArrayList<OpstiDomenskiObjekat> rasporedi = new ArrayList<>();
        try {
            while (rs.next()) {
                int rsId = rs.getInt("id");
                LocalDate rsDatum = rs.getDate("datum").toLocalDate();
                rasporedi.add(new Raspored(rsId, rsDatum));
            }
        } catch (SQLException e) {
            System.out.println("Greska u Raspored::konvertujRSUListu:\n" + e.getMessage());
        }
        return rasporedi;
    }

    @Override
    public String getSelectUpit() {
        return "SELECT * FROM " + getNazivTabele();
    }

    @Override
    public String getSelectUpitPoParametru() {
        return "SELECT * FROM " + getNazivTabele() + " WHERE id = " + getId() + " OR datum = '" + getDatum() + "'";
    }

    @Override
    public String getInsertUpit() {
        return "INSERT INTO " + getNazivTabele() + "(" + getNaziveParametara() + ")" + " VALUES (" + getParametre() + ")";
    }

    @Override
    public String getUpdateUpit() {
        return "UPDATE " + getNazivTabele() + " SET " + getUpdateParametre() + " WHERE " + getNazivPrimarnogKljuca() + " = " + getVrednostPrimarnogKljuca();
    }

    @Override
    public String getUpdateParametre() {
        return String.format("datum = '%s'", getDatum());
    }

    @Override
    public String getDeleteUpit() {
        return "DELETE FROM " + getNazivTabele() + " WHERE " + getNazivPrimarnogKljuca() + " = " + getVrednostPrimarnogKljuca();
    }

}
