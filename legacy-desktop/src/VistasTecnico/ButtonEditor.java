package VistasTecnico;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonEditor extends DefaultCellEditor {
    private JButton button;
    private String label;
    private boolean isPushed;
    private int row;
    private JTable table;

    public ButtonEditor(JTable table, String label) {
        super(new JTextField());
        this.table = table;
        this.label = label;
        button = new JButton(label);
        button.setOpaque(true);
        button.setPreferredSize(new Dimension(150, 80)); // Ancho y altura del botón
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.row = row;
        button.setText(label);
        isPushed = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return label;
    }

    @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }

    @Override
    protected void fireEditingStopped() {
        super.fireEditingStopped();
        if ("Editar".equals(label)) {
            ((VistaTecnico) SwingUtilities.getWindowAncestor(table)).editarTecnico(row);
        } else if ("Eliminar".equals(label)) {
            ((VistaTecnico) SwingUtilities.getWindowAncestor(table)).eliminarTecnico(row);
        }
    }

}
