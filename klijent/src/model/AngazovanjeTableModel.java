package model;

import domen.Angazovanje;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class AngazovanjeTableModel extends AbstractTableModel {

    private final List<Angazovanje> angazovanja;
    private final String[] kolone = {"Spasilac", "Smena", "Raspored"};

    public AngazovanjeTableModel(List<Angazovanje> angazovanja) {
        this.angazovanja = angazovanja;
    }

    @Override
    public int getRowCount() {
        return angazovanja.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Angazovanje angazovanje = angazovanja.get(rowIndex);
        switch (columnIndex) {
            case 0 -> {
                return angazovanje.getSpasilac();
            }
            case 1 -> {
                return angazovanje.getSmena();
            }
            case 2 -> {
                return angazovanje.getRaspored();
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
