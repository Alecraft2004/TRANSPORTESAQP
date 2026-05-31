
package Modelo;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class PlanMantenimientoTabla extends AbstractTableModel {

    private final String[] columnas = {
        "Código", "Técnico", "Vehículo", "Tipo Mantenimiento", 
        "Kilometraje", "Falla", "Fecha", "Servicio Pago", 
        "Ver Técnico", "Ver Vehículo", "Editar", "Eliminar"
    };
    private List<PlanMantenimiento> listaPlanes;

    public PlanMantenimientoTabla(List<PlanMantenimiento> listaPlanes) {
        this.listaPlanes = listaPlanes;
    }

    @Override
    public int getRowCount() {
        return listaPlanes.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnas[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PlanMantenimiento plan = listaPlanes.get(rowIndex);
        switch (columnIndex) {
            case 0: return plan.getCodigo();
            case 1: return plan.getIdTecnico();  // Puedes obtener el nombre completo si ajustas el objeto Técnico.
            case 2: return plan.getIdVehiculo(); // Puedes obtener la placa y dueño si ajustas el objeto Vehículo.
            case 3: return plan.getTipoMantenimiento();
            case 4: return plan.getKilometraje();
            case 5: return plan.getFalla();
            case 6: return plan.getFecha();
            case 7: return plan.getServicioPago();
            case 8: return "Ver Técnico";       // Acción para ver detalles del técnico
            case 9: return "Ver Vehículo";      // Acción para ver detalles del vehículo
            case 10: return "Editar";           // Acción para editar el plan
            case 11: return "Eliminar";         // Acción para eliminar el plan
            default: return null;
        }
    }

    // Método para obtener el PlanMantenimiento en una fila específica
    public PlanMantenimiento getPlanAt(int rowIndex) {
        return listaPlanes.get(rowIndex);
    }

    // Método para actualizar los planes en la tabla
    public void setPlanes(List<PlanMantenimiento> listaPlanes) {
        this.listaPlanes = listaPlanes;
        fireTableDataChanged(); // Notifica a la tabla que los datos cambiaron
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex >= 8;  // Solo las columnas "Ver Técnico", "Ver Vehículo", "Editar", "Eliminar" son editables
    }
}
