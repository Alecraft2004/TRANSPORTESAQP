package VistasPlanMantenimiento;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ButtonRenderer extends JButton implements TableCellRenderer {

    public ButtonRenderer(String label) {
        setText(label);
        setOpaque(true); // Cambia a true para que el botón tenga fondo visible
        setPreferredSize(new Dimension(100, 40)); // Tamaño ajustado
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setText(value != null ? value.toString() : ""); // Configura el texto según el valor de la celda
        return this;
    }
}
