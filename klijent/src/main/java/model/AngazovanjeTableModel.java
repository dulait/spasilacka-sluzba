package model;

import domen.Angazovanje;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Klasa {@code AngazovanjeTableModel} predstavlja model tabele za prikaz liste
 * {@code Angazovanje} instanci.
 *
 * @author dulait
 */
public class AngazovanjeTableModel extends AbstractTableModel {

    private final List<Angazovanje> angazovanja;
    private final String[] kolone = {"Spasilac", "Smena", "Raspored"};

    /**
     * Konstruktor za {@code AngazovanjeTableModel} sa datom listom
     * {@code Angazovanje} instanci.
     *
     * @param angazovanja lista {@code Angazovanje} instanci koja se prikazuje u
     * tabeli
     */
    public AngazovanjeTableModel(List<Angazovanje> angazovanja) {
        this.angazovanja = angazovanja;
    }

    /**
     * Vraća broj redova u tabeli.
     *
     * @return broj redova u tabeli
     */
    @Override
    public int getRowCount() {
        return angazovanja.size();
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
        Angazovanje angazovanje = angazovanja.get(rowIndex);
        return switch (columnIndex) {
            case 0 ->
                angazovanje.getSpasilac();
            case 1 ->
                angazovanje.getSmena();
            case 2 ->
                angazovanje.getRaspored();
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
