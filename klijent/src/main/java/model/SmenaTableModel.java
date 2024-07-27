package model;

import domen.Smena;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * The {@code SmenaTableModel} class is a table model for displaying a list of
 * {@code Smena} instances.
 * <p>
 * This class extends {@code AbstractTableModel} and provides the data and
 * column names required for displaying {@code Smena} objects in a
 * {@code JTable}.
 * </p>
 *
 * <p>
 * It provides data for two columns: "Pocetak smene" and "Kraj smene", which
 * correspond to the start time and end time of the {@code Smena} object,
 * respectively.
 * </p>
 *
 * @author dulait
 */
public class SmenaTableModel extends AbstractTableModel {

    private final List<Smena> smene;
    private final String[] kolone = {"Pocetak smene", "Kraj smene"};

    /**
     * Constructs a {@code SmenaTableModel} with the specified list of
     * {@code Smena} instances.
     *
     * @param smene the list of {@code Smena} instances to be displayed in the
     * table
     */
    public SmenaTableModel(List<Smena> smene) {
        this.smene = smene;
    }

    /**
     * Returns the number of rows in the table.
     * <p>
     * This method returns the size of the list of {@code Smena} instances.
     * </p>
     *
     * @return the number of rows in the table
     */
    @Override
    public int getRowCount() {
        return smene.size();
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
     * This method retrieves the value from the {@code Smena} instance at the
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
