package model;

import domen.Smena;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Klasa {@code SmenaTableModel} predstavlja model tabele za prikaz liste
 * {@code Smena} instanci.
 *
 * @author dulait
 */
public class SmenaTableModel extends AbstractTableModel {

    private final List<Smena> smene;
    private final String[] kolone = {"Pocetak smene", "Kraj smene"};

    /**
     * Konstruktor za {@code SmenaTableModel} sa datom listom {@code Smena}
     * instanci.
     *
     * @param smene lista {@code Smena} instanci koja se prikazuje u tabeli
     */
    public SmenaTableModel(List<Smena> smene) {
        this.smene = smene;
    }

    /**
     * Vraća broj redova u tabeli.
     *
     * @return broj redova u tabeli
     */
    @Override
    public int getRowCount() {
        return smene.size();
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
        Smena smena = smene.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return smena.getPocetak();
            case 1:
                return smena.getKraj();
            default:
                return null;
        }
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
