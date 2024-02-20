package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Spasilac extends OpstiDomenskiObjekat {

    private int id;
    private String ime;
    private String prezime;
    private String jmbg;

    public Spasilac() {
    }

    public Spasilac(int id) {
        this.id = id;
    }

    public Spasilac(int spasilacId, String ime, String prezime, String jmbg) {
        this.id = spasilacId;
        this.ime = ime;
        this.prezime = prezime;
        this.jmbg = jmbg;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    @Override
    public String getNazivTabele() {
        return "spasilac";
    }

    @Override
    public String getParametre() {
        return String.format("%d, '%s', '%s', '%s'", id, ime, prezime, jmbg);
    }

    @Override
    public String getNaziveParametara() {
        return "id, ime, prezime, jmbg";
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
    public List<OpstiDomenskiObjekat> konvertujRSUListu(ResultSet rs) {
        List<OpstiDomenskiObjekat> spasioci = new ArrayList<>();
        try {
            while (rs.next()) {
                int rsId = rs.getInt("id");
                String rsIme = rs.getString("ime");
                String rsPrezime = rs.getString("prezime");
                String rsJmbg = rs.getString("jmbg");

                spasioci.add(new Spasilac(rsId, rsIme, rsPrezime, rsJmbg));
            }
        } catch (SQLException e) {
            System.out.println("Greska u Spasilac::konvertujRSUListu\n" + e.getMessage());
        }
        return spasioci;
    }

    @Override
    public String getSelectUpit() {
        return "SELECT * FROM " + getNazivTabele();
    }

    @Override
    public String getSelectUpitPoParametru() {
        return "SELECT * FROM " + getNazivTabele() + " WHERE id = " + getId() + " OR jmbg = '" + getJmbg() + "'";
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
        return String.format("ime = '%s', prezime = '%s', jmbg = '%s'",
                ime, prezime, jmbg);
    }

    @Override
    public String getDeleteUpit() {
        return "DELETE FROM " + getNazivTabele() + " WHERE " + getNazivPrimarnogKljuca() + " = " + getVrednostPrimarnogKljuca();
    }
}
