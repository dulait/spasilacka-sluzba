package model;

import domen.Koordinator;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Model tabele za prikaz liste Koordinatora.
 *
 * @author dulait
 */
public class KoordinatorTableModel extends AbstractTableModel {

    private final List<Koordinator> koordinatori;
    private final String[] kolone = {"Ime", "Prezime"};

    /**
     * Konstruktor koji inicijalizacije model listom Koordinatora.
     *
     * @param koordinatori lista Koordinatora za prikaz u tabeli
     */
    public KoordinatorTableModel(List<Koordinator> koordinatori) {
        this.koordinatori = koordinatori;
    }

    /**
     * Vraća broj redova u tabeli.
     *
     * @return broj redova
     */
    @Override
    public int getRowCount() {
        return koordinatori.size();
    }

    /**
     * Vraća broj kolona u tabeli.
     *
     * @return broj kolona
     */
    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    /**
     * Vraća vrednost u određenoj ćeliji tabele.
     *
     * @param rowIndex indeks reda
     * @param columnIndex indeks kolone
     * @return vrednost ćelije ili {@code null} ako je indeks van opsega
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Koordinator koordinator = koordinatori.get(rowIndex);
        return switch (columnIndex) {
            case 0 ->
                koordinator.getIme();
            case 1 ->
                koordinator.getPrezime();
            default ->
                null;
        };
    }

    /**
     * Vraća naziv kolone za dati indeks.
     *
     * @param column indeks kolone
     * @return naziv kolone
     */
    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
}
