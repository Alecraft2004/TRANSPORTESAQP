package VistasPlanMantenimiento;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ButtonEditor extends DefaultCellEditor {
    private JButton button;
    private String label;
    private boolean isPushed;
    private int row;
    private VistaPlanMantenimiento vista; // Referencia directa a VistaPlanMantenimiento

    public ButtonEditor(JTable table, String label, VistaPlanMantenimiento vista) {
        super(new JTextField());
        this.vista = vista;
        this.label = label;
        button = new JButton(label);
        button.setOpaque(true);
        button.setPreferredSize(new Dimension(100, 40));
        button.addActionListener(this::handleAction);
    }

    private void handleAction(ActionEvent e) {
        fireEditingStopped();
        switch (label) {
            case "Editar":
                vista.editarPlan(row);
                break;
            case "Eliminar":
                vista.eliminarPlan(row);
                break;
            case "Ver Vehículo":
                vista.verVehiculo(row);
                break;
            case "Ver Técnico":
                vista.verTecnico(row); // Llama directamente a verTecnico
                break;
        }
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
}
