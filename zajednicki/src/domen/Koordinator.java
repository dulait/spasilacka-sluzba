/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author drask
 */
public class Koordinator extends OpstiDomenskiObjekat {

    private int id;
    private String korisnickoIme;
    private String lozinka;
    private String ime;
    private String prezime;

    public Koordinator() {

    }

    public Koordinator(int id) {
        this.id = id;
    }

    public Koordinator(int id, String korisnickoIme, String lozinka, String ime, String prezime) {
        this.id = id;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.ime = ime;
        this.prezime = prezime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public String getNazivTabele() {
        return "koordinator";
    }

    @Override
    public String getParametre() {
        return String.format("%d, '%s', '%s', '%s', '%s'", id, korisnickoIme, lozinka, ime, prezime);
    }

    @Override
    public String getNaziveParametara() {
        return "id, korisnickoIme, lozinka, ime, prezime";
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
        ArrayList<OpstiDomenskiObjekat> koordinatori = new ArrayList<>();
        try {
            while (rs.next()) {
                int rsId = rs.getInt("id");
                String rsKorisnickoIme = rs.getString("korisnickoIme");
                String rsLozinka = rs.getString("lozinka");
                String rsIme = rs.getString("ime");
                String rePrezime = rs.getString("prezime");

                koordinatori.add(new Koordinator(rsId, rsKorisnickoIme, rsLozinka, rsIme, rePrezime));
            }
        } catch (SQLException e) {
            System.out.println("Greska u Koordinator::konvertujRSUListu\n" + e.getMessage());
        }
        return koordinatori;
    }

    @Override
    public String getSelectUpit() {
        return "SELECT * FROM " + getNazivTabele();
    }

    @Override
    public String getSelectUpitPoParametru() {
        return "SELECT * FROM " + getNazivTabele() + " WHERE id = " + getVrednostPrimarnogKljuca();
    }

    @Override
    public String getInsertUpit() {
        return null;
    }

    @Override
    public String getUpdateParametre() {
        return null;
    }

    @Override
    public String getDeleteUpit() {
        return null;
    }

    @Override
    public String getUpdateUpit() {
        return String.format("id = %d, korisnickoIme = '%s', lozinka = '%s', ime = '%s', prezime = '%s'",
                id, korisnickoIme, lozinka, ime, prezime);
    }

}
