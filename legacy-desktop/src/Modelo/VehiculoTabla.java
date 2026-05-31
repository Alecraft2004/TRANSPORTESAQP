
package Modelo;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class VehiculoTabla extends AbstractTableModel {

    private List<Vehiculo> listaVehiculos;
    private final String[] columnas = {"Código", "Placa", "Número de Serie", "Año", "Color", "Puertas", "Cilindrada", "Dueño", "Editar", "Quitar"};

    public VehiculoTabla(List<Vehiculo> listaVehiculos) {
        this.listaVehiculos = listaVehiculos;
    }

    @Override
    public int getRowCount() {
        return listaVehiculos.size();
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
        Vehiculo vehiculo = listaVehiculos.get(rowIndex);
        switch (columnIndex) {
            case 0: return vehiculo.getCodigo();
            case 1: return vehiculo.getPlaca();
            case 2: return vehiculo.getNumeroSerie();
            case 3: return vehiculo.getAnhoFabricacion();
            case 4: return vehiculo.getColor();
            case 5: return vehiculo.getCantidadPuertas();
            case 6: return vehiculo.getCilindrada();
            case 7: return vehiculo.getDuenho();
            case 8: return "Editar";
            case 9: return "Quitar";
            default: return null;
        }
    }

    public Vehiculo getVehiculoAt(int rowIndex) {
        return listaVehiculos.get(rowIndex);
    }

    public void setVehiculos(List<Vehiculo> listaVehiculos) {
        this.listaVehiculos = listaVehiculos;
        fireTableDataChanged();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 8 || columnIndex == 9;
    }
}
