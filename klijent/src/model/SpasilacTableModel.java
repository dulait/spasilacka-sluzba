package model;

import domen.Spasilac;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class SpasilacTableModel extends AbstractTableModel {

    private final List<Spasilac> spasioci;
    private final String[] kolone = {"Ime", "Prezime", "JMBG"};

    public SpasilacTableModel(List<Spasilac> spasioci) {
        this.spasioci = spasioci;
    }

    @Override
    public int getRowCount() {
        return spasioci.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Spasilac spasilac = spasioci.get(rowIndex);
        switch (columnIndex) {
            case 0 -> {
                return spasilac.getIme();
            }
            case 1 -> {
                return spasilac.getPrezime();
            }
            case 2 -> {
                return spasilac.getJmbg();
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
