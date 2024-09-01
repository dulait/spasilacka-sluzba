package domen;

import java.io.Serializable;
import java.sql.*;
import java.util.List;

/**
 * Apstraktna klasa koja predstavlja opšti domensk objekat. Ova klasa pruža
 * ugovor za sve domenske objekte.
 *
 * Ova klasa implementira {@link Serializable} kako bi omogućila serijalizaciju
 * objekata.
 *
 * @author dulait
 */
public abstract class OpstiDomenskiObjekat implements Serializable {

    /**
     * Vraća ime tabele povezane sa ovim domenskim objektom.
     *
     * @return ime tabele kao String.
     */
    public abstract String getNazivTabele();

    /**
     * Vraća parametre za ovaj domenski objekat u formatu koji je potreban za
     * SQL iskaz.
     *
     * @return parametri kao String.
     */
    public abstract String getParametre();

    /**
     * Vraća imena parametara za ovaj domenski objekat.
     *
     * @return imena parametara kao String.
     */
    public abstract String getNaziveParametara();

    /**
     * Vraća ime kolone primarnog ključa za ovaj domenski objekat.
     *
     * @return ime kolone primarnog ključa kao String.
     */
    public abstract String getNazivPrimarnogKljuca();

    /**
     * Vraća vrednost primarnog ključa za ovaj domenski objekat.
     *
     * @return vrednost primarnog ključa kao Integer.
     */
    public abstract Integer getVrednostPrimarnogKljuca();

    /**
     * Vraća složeni primarni ključ za ovaj domenski objekat, ako je
     * primenljivo.
     *
     * @return složeni primarni ključ kao String, ili null ako nije primenljivo.
     */
    public abstract String getSlozeniPrimarniKljuc();

    /**
     * Konvertuje {@link ResultSet} u listu domenskih objekata.
     *
     * @param rs {@link ResultSet} koji se konvertuje.
     * @return {@link List} domenskih objekata.
     */
    public abstract List<OpstiDomenskiObjekat> konvertujRSUListu(ResultSet rs);

    /**
     * Vraća SQL SELECT upit za dobijanje svih zapisa ovog domenskog objekta.
     *
     * @return SQL SELECT upit kao String.
     */
    public abstract String getSelectUpit();

    /**
     * Vraća SQL SELECT upit za dobijanje zapisa ovog domenskog objekta na
     * osnovu specifičnih parametara.
     *
     * @return SQL SELECT upit sa parametrima kao String.
     */
    public abstract String getSelectUpitPoParametru();

    /**
     * Vraća SQL INSERT upit za umetanje zapisa ovog domenskog objekta.
     *
     * @return SQL INSERT upit kao String.
     */
    public abstract String getInsertUpit();

    /**
     * Vraća SQL UPDATE upit za ažuriranje zapisa ovog domenskog objekta.
     *
     * @return SQL UPDATE upit kao String.
     */
    public abstract String getUpdateUpit();

    /**
     * Vraća parametre za ažuriranje zapisa ovog domenskog objekta.
     *
     * @return parametri za ažuriranje kao String.
     */
    public abstract String getUpdateParametre();

    /**
     * Vraća SQL DELETE upit za brisanje zapisa ovog domenskog objekta.
     *
     * @return SQL DELETE upit kao String.
     */
    public abstract String getDeleteUpit();

}
