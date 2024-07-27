package model;

import domen.Koordinator;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * The {@code KoordinatorTableModel} class is a table model for displaying a
 * list of {@code Koordinator} instances.
 * <p>
 * This class extends {@code AbstractTableModel} and provides the data and
 * column names required for displaying {@code Koordinator} objects in a
 * {@code JTable}.
 * </p>
 *
 * <p>
 * It provides data for two columns: "Ime" and "Prezime", which correspond to
 * the first name and last name of the {@code Koordinator} object, respectively.
 * </p>
 *
 * @author dulait
 */
public class KoordinatorTableModel extends AbstractTableModel {

    private final List<Koordinator> koordinatori;
    private final String[] kolone = {"Ime", "Prezime"};

    /**
     * Constructs a {@code KoordinatorTableModel} with the specified list of
     * {@code Koordinator} instances.
     *
     * @param koordinatori the list of {@code Koordinator} instances to be
     * displayed in the table
     */
    public KoordinatorTableModel(List<Koordinator> koordinatori) {
        this.koordinatori = koordinatori;
    }

    /**
     * Returns the number of rows in the table.
     * <p>
     * This method returns the size of the list of {@code Koordinator}
     * instances.
     * </p>
     *
     * @return the number of rows in the table
     */
    @Override
    public int getRowCount() {
        return koordinatori.size();
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
     * This method retrieves the value from the {@code Koordinator} instance at
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
        Koordinator koordinator = koordinatori.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return koordinator.getIme();
            case 1:
                return koordinator.getPrezime();
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
