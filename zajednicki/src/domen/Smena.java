package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Smena extends OpstiDomenskiObjekat {

    private int id;
    private int pocetak;
    private int kraj;

    public Smena() {
    }

    public Smena(int smenaId) {
        this.id = smenaId;
    }

    public Smena(int smenaId, int pocetak, int kraj) {
        this.id = smenaId;
        this.pocetak = pocetak;
        this.kraj = kraj;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPocetak() {
        return pocetak;
    }

    public void setPocetak(int pocetak) {
        this.pocetak = pocetak;
    }

    public int getKraj() {
        return kraj;
    }

    public void setKraj(int kraj) {
        this.kraj = kraj;
    }

    @Override
    public String toString() {
        return "" + pocetak + ":00h - " + kraj + ":00h";
    }

    @Override
    public String getNazivTabele() {
        return "smena";
    }

    @Override
    public String getParametre() {
        return String.format("%s, %s, %s", id, pocetak, kraj);
    }

    @Override
    public String getNaziveParametara() {
        return "id, pocetak, kraj";
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
        ArrayList<OpstiDomenskiObjekat> smene = new ArrayList<>();
        try {
            while (rs.next()) {
                int rsId = rs.getInt("id");
                int rsPocetak = rs.getInt("pocetak");
                int rsKraj = rs.getInt("kraj");

                smene.add(new Smena(rsId, rsPocetak, rsKraj));
            }
        } catch (SQLException e) {
            System.out.println("Greska u Smena::konvertujRSUListu:\n" + e.getMessage());
        }
        return smene;
    }

    @Override
    public String getSelectUpit() {
        return "SELECT * FROM " + getNazivTabele();
    }

    @Override
    public String getSelectUpitPoParametru() {
        return "SELECT * FROM " + getNazivTabele() + " WHERE id = " + getId() + " OR (pocetak = " + getPocetak() + " AND kraj = " + getKraj() + ")";
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
        return String.format("pocetak = %s, kraj = %s", pocetak, kraj);
    }

    @Override
    public String getDeleteUpit() {
        return "DELETE FROM " + getNazivTabele() + " WHERE " + getNazivPrimarnogKljuca() + " = " + getVrednostPrimarnogKljuca();
    }
}
