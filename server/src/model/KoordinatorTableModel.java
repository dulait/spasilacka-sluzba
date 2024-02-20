package model;

import domen.Koordinator;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class KoordinatorTableModel extends AbstractTableModel {

    private final List<Koordinator> koordinatori;
    private final String[] kolone = {"Ime", "Prezime"};

    public KoordinatorTableModel(List<Koordinator> koordinatori) {
        this.koordinatori = koordinatori;
    }

    @Override
    public int getRowCount() {
        return koordinatori.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Koordinator koordinator = koordinatori.get(rowIndex);
        switch (columnIndex) {
            case 0 -> {
                return koordinator.getIme();
            }
            case 1 -> {
                return koordinator.getPrezime();
            }
            default -> {
                return null;
            }
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

}
