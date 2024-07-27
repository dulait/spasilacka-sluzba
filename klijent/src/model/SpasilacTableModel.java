package model;

import domen.Spasilac;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * The {@code SpasilacTableModel} class is a table model for displaying a list
 * of {@code Spasilac} instances.
 * <p>
 * This class extends {@code AbstractTableModel} and provides the data and
 * column names required for displaying {@code Spasilac} objects in a
 * {@code JTable}.
 * </p>
 *
 * <p>
 * It provides data for three columns: "Ime", "Prezime", and "JMBG", which
 * correspond to the first name, last name, and unique identification number of
 * the {@code Spasilac} object, respectively.
 * </p>
 *
 * @author dulait
 */
public class SpasilacTableModel extends AbstractTableModel {

    private final List<Spasilac> spasioci;
    private final String[] kolone = {"Ime", "Prezime", "JMBG"};

    /**
     * Constructs a {@code SpasilacTableModel} with the specified list of
     * {@code Spasilac} instances.
     *
     * @param spasioci the list of {@code Spasilac} instances to be displayed in
     * the table
     */
    public SpasilacTableModel(List<Spasilac> spasioci) {
        this.spasioci = spasioci;
    }

    /**
     * Returns the number of rows in the table.
     * <p>
     * This method returns the size of the list of {@code Spasilac} instances.
     * </p>
     *
     * @return the number of rows in the table
     */
    @Override
    public int getRowCount() {
        return spasioci.size();
    }

    /**
     * Returns the number of columns in the table.
     * <p>
     * This method returns the length of the {@code kolone} array, which defines
     * the column names.
     * </p>
     *
     * @return the number of columns in the table
     */
    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    /**
     * Returns the value at the specified cell.
     * <p>
     * This method retrieves the value from the {@code Spasilac} instance at the
     * specified row and column index.
     * </p>
     *
     * @param rowIndex the row index of the cell
     * @param columnIndex the column index of the cell
     * @return the value at the specified cell, or {@code null} if the index is
     * out of bounds
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
     * Returns the name of the column at the specified index.
     *
     * @param column the index of the column
     * @return the name of the column at the specified index
     */
    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
}
