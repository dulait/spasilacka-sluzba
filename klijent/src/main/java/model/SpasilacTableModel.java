package model;

import domen.Spasilac;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Klasa {@code SpasilacTableModel} predstavlja model tabele za prikaz liste
 * {@code Spasilac} instanci.
 *
 * @author dulait
 */
public class SpasilacTableModel extends AbstractTableModel {

    private final List<Spasilac> spasioci;
    private final String[] kolone = {"Ime", "Prezime", "JMBG"};

    /**
     * Konstruktor za {@code SpasilacTableModel} sa datom listom
     * {@code Spasilac} instanci.
     *
     * @param spasioci lista {@code Spasilac} instanci koja se prikazuje u
     * tabeli
     */
    public SpasilacTableModel(List<Spasilac> spasioci) {
        this.spasioci = spasioci;
    }

    /**
     * Vraća broj redova u tabeli.
     *
     * @return broj redova u tabeli
     */
    @Override
    public int getRowCount() {
        return spasioci.size();
    }

    /**
     * Vraća broj kolona u tabeli.
     *
     * @return broj kolona u tabeli
     */
    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    /**
     * Vraća vrednost u zadatoj ćeliji.
     *
     * @param rowIndex indeks reda ćelije
     * @param columnIndex indeks kolone ćelije
     * @return vrednost u zadatoj ćeliji, ili {@code null} ako je indeks van
     * granica
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Spasilac spasilac = spasioci.get(rowIndex);
        return switch (columnIndex) {
            case 0 ->
                spasilac.getIme();
            case 1 ->
                spasilac.getPrezime();
            case 2 ->
                spasilac.getJmbg();
            default ->
                null;
        };
    }

    /**
     * Vraća naziv kolone na zadatom indeksu.
     *
     * @param column indeks kolone
     * @return naziv kolone na zadatom indeksu
     */
    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
}
