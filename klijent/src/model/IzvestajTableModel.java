package model;

import domen.Izvestaj;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class IzvestajTableModel extends AbstractTableModel {

    private final List<Izvestaj> izvestaji;
    private final String[] kolone = {"Angazovajne", "Opis"};

    public IzvestajTableModel(List<Izvestaj> izvestaji) {
        this.izvestaji = izvestaji;
    }

    @Override
    public int getRowCount() {
        return izvestaji.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Izvestaj izvestaj = izvestaji.get(rowIndex);
        switch (columnIndex) {
            case 0 -> {
                return izvestaj.getAngazovanje();
            }
            case 1 -> {
                return izvestaj.getOpis();
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
