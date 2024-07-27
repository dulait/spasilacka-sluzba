package model;

import domen.Izvestaj;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * The {@code IzvestajTableModel} class is a table model for displaying a list
 * of {@code Izvestaj} instances.
 * <p>
 * This class extends {@code AbstractTableModel} and provides the data and
 * column names required for displaying {@code Izvestaj} objects in a
 * {@code JTable}.
 * </p>
 *
 * <p>
 * It provides data for two columns: "Angazovanje" and "Opis", which correspond
 * to the engagement and description of the {@code Izvestaj} object,
 * respectively.
 * </p>
 *
 * @author dulait
 */
public class IzvestajTableModel extends AbstractTableModel {

    private final List<Izvestaj> izvestaji;
    private final String[] kolone = {"Angazovanje", "Opis"};

    /**
     * Constructs a {@code IzvestajTableModel} with the specified list of
     * {@code Izvestaj} instances.
     *
     * @param izvestaji the list of {@code Izvestaj} instances to be displayed
     * in the table
     */
    public IzvestajTableModel(List<Izvestaj> izvestaji) {
        this.izvestaji = izvestaji;
    }

    /**
     * Returns the number of rows in the table.
     * <p>
     * This method returns the size of the list of {@code Izvestaj} instances.
     * </p>
     *
     * @return the number of rows in the table
     */
    @Override
    public int getRowCount() {
        return izvestaji.size();
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
     * This method retrieves the value from the {@code Izvestaj} instance at the
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
