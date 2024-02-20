package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Izvestaj extends OpstiDomenskiObjekat {

    private Angazovanje angazovanje;
    private String opis;

    public Izvestaj() {
    }

    public Izvestaj(Angazovanje angazovanje, String opis) {
        this.angazovanje = angazovanje;
        this.opis = opis;
    }

    public Angazovanje getAngazovanje() {
        return angazovanje;
    }

    public void setAngazovanje(Angazovanje angazovanje) {
        this.angazovanje = angazovanje;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @Override
    public String getNazivTabele() {
        return "izvestaj";
    }

    @Override
    public String getParametre() {
        return String.format("%d, %d, %d, '%s'",
                angazovanje.getSpasilac().getId(), angazovanje.getSmena().getId(), angazovanje.getRaspored().getId(), opis);
    }

    @Override
    public String getNaziveParametara() {
        return "spasilacId, smenaId, rasporedId, opis";
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
        return "spasilacId = " + angazovanje.getSpasilac().getId() + " AND smenaId = " + angazovanje.getSmena().getId()
                + " AND rasporedId = " + angazovanje.getRaspored().getId();
    }

    @Override
    public ArrayList<OpstiDomenskiObjekat> konvertujRSUListu(ResultSet rs) {
        ArrayList<OpstiDomenskiObjekat> izvestaji = new ArrayList<>();
        try {
            while (rs.next()) {
                String rsOpis = rs.getString("izv.opis");

                int spasilacTableId = rs.getInt("spa.id");
                String spasilacIme = rs.getString("spa.ime");
                String spasilacPrezime = rs.getString("spa.prezime");
                String spasilacJmbg = rs.getString("spa.jmbg");
                Spasilac rsSpasilac = new Spasilac(spasilacTableId, spasilacIme, spasilacPrezime, spasilacJmbg);

                int smenaTableId = rs.getInt("sme.id");
                int smenaPocetak = rs.getInt("sme.pocetak");
                int smenaKraj = rs.getInt("sme.kraj");
                Smena rsSmena = new Smena(smenaTableId, smenaPocetak, smenaKraj);

                int rasporedTableId = rs.getInt("ras.id");
                LocalDate rasporedDatum = rs.getDate("ras.datum").toLocalDate();
                Raspored rsRaspored = new Raspored(rasporedTableId, rasporedDatum);

                Angazovanje rsAangazovanje = new Angazovanje(rsSpasilac, rsSmena, rsRaspored);

                rsAangazovanje.setSpasilac(rsSpasilac);
                rsAangazovanje.setSmena(rsSmena);
                rsAangazovanje.setRaspored(rsRaspored);

                Izvestaj izvestaj = new Izvestaj(rsAangazovanje, rsOpis);
                izvestaji.add(izvestaj);
            }
        } catch (SQLException e) {
            System.out.println("Greska u Izvestaj::konvertujRSUListu\n" + e.getMessage());
        }
        return izvestaji;
    }

    @Override
    public String getSelectUpit() {
        return "SELECT izv.*, ang.*, spa.*, sme.*, ras.* "
                + "FROM izvestaj izv "
                + "JOIN angazovanje ang ON izv.spasilacId = ang.spasilacId AND izv.smenaId = ang.smenaId AND izv.rasporedId = ang.rasporedId "
                + "JOIN spasilac spa ON ang.spasilacId = spa.id "
                + "JOIN smena sme ON ang.smenaId = sme.id "
                + "JOIN raspored ras ON ang.rasporedId = ras.id";
    }

    @Override
    public String getSelectUpitPoParametru() {
        return "SELECT izv.*, ang.*, spa.*, sme.*, ras.* "
                + "FROM izvestaj izv "
                + "JOIN angazovanje ang ON izv.spasilacId = ang.spasilacId AND izv.smenaId = ang.smenaId AND izv.rasporedId = ang.rasporedId "
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
        return String.format("spasilacId = %d, smenaId = %d, rasporedId = %d, opis = '%s'",
                angazovanje.getSpasilac().getId(), angazovanje.getSmena().getId(), angazovanje.getRaspored().getId(), opis);
    }

    @Override
    public String getDeleteUpit() {
        return "DELETE FROM " + getNazivTabele() + " WHERE " + getSlozeniPrimarniKljuc();
    }

}
