package model;

import domen.Izvestaj;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Klasa {@code IzvestajTableModel} predstavlja model tabele za prikaz liste
 * {@code Izvestaj} instanci.
 *
 * @author dulait
 */
public class IzvestajTableModel extends AbstractTableModel {

    private final List<Izvestaj> izvestaji;
    private final String[] kolone = {"Angazovanje", "Opis"};

    /**
     * Konstruktor za {@code IzvestajTableModel} sa datom listom
     * {@code Izvestaj} instanci.
     *
     * @param izvestaji lista {@code Izvestaj} instanci koja se prikazuje u
     * tabeli
     */
    public IzvestajTableModel(List<Izvestaj> izvestaji) {
        this.izvestaji = izvestaji;
    }

    /**
     * Vraća broj redova u tabeli.
     *
     * @return broj redova u tabeli
     */
    @Override
    public int getRowCount() {
        return izvestaji.size();
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
        Izvestaj izvestaj = izvestaji.get(rowIndex);
        return switch (columnIndex) {
            case 0 ->
                izvestaj.getAngazovanje();
            case 1 ->
                izvestaj.getOpis();
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
