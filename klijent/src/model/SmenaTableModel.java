package model;

import domen.Smena;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class SmenaTableModel extends AbstractTableModel {

    private final List<Smena> smene;
    private final String[] kolone = {"Pocetak smene", "Kraj smene"};

    public SmenaTableModel(List<Smena> smene) {
        this.smene = smene;
    }

    @Override
    public int getRowCount() {
        return smene.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Smena smena = smene.get(rowIndex);
        switch (columnIndex) {
            case 0 -> {
                return smena.getPocetak();
            }
            case 1 -> {
                return smena.getKraj();
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
