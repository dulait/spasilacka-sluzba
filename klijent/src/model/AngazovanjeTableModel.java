package model;

import domen.Angazovanje;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * The {@code AngazovanjeTableModel} class is a table model for displaying a
 * list of {@code Angazovanje} instances.
 * <p>
 * This class extends {@code AbstractTableModel} and provides the data and
 * column names required for displaying {@code Angazovanje} objects in a
 * {@code JTable}.
 * </p>
 *
 * <p>
 * It provides data for three columns: "Spasilac", "Smena", and "Raspored",
 * which correspond to the rescuer, shift, and schedule of the
 * {@code Angazovanje} object, respectively.
 * </p>
 *
 * @author dulait
 */
public class AngazovanjeTableModel extends AbstractTableModel {

    private final List<Angazovanje> angazovanja;
    private final String[] kolone = {"Spasilac", "Smena", "Raspored"};

    /**
     * Constructs an {@code AngazovanjeTableModel} with the specified list of
     * {@code Angazovanje} instances.
     *
     * @param angazovanja the list of {@code Angazovanje} instances to be
     * displayed in the table
     */
    public AngazovanjeTableModel(List<Angazovanje> angazovanja) {
        this.angazovanja = angazovanja;
    }

    /**
     * Returns the number of rows in the table.
     * <p>
     * This method returns the size of the list of {@code Angazovanje}
     * instances.
     * </p>
     *
     * @return the number of rows in the table
     */
    @Override
    public int getRowCount() {
        return angazovanja.size();
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
     * This method retrieves the value from the {@code Angazovanje} instance at
     * the specified row and column index.
     * </p>
     *
     * @param rowIndex the row index of the cell
     * @param columnIndex the column index of the cell
     * @return the value at the specified cell, or {@code null} if the index is
     * out of bounds
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Angazovanje angazovanje = angazovanja.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return angazovanje.getSpasilac();
            case 1:
                return angazovanje.getSmena();
            case 2:
                return angazovanje.getRaspored();
            default:
                return null;
        }
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
