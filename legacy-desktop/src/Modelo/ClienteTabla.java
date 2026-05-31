
package Modelo;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ClienteTabla extends AbstractTableModel {

    private List<Cliente> listaClientes;
    private final String[] columnas = {"Nombres", "Apellidos", "Dirección", "Sexo", "Correo", "Celular", "Editar", "Eliminar"};

    public ClienteTabla(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    @Override
    public int getRowCount() {
        return listaClientes.size();
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
        Cliente cliente = listaClientes.get(rowIndex);
        switch (columnIndex) {
            case 0: return cliente.getNombres();
            case 1: return cliente.getApellidos();
            case 2: return cliente.getDireccion();
            case 3: return cliente.getSexo();
            case 4: return cliente.getCorreo();
            case 5: return cliente.getCelular();
            case 6: return "Editar";
            case 7: return "Eliminar";
            default: return null;
        }
    }

    public Cliente getClienteAt(int rowIndex) {
        return listaClientes.get(rowIndex);
    }

    public void setClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
        fireTableDataChanged();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 6 || columnIndex == 7;
    }
}
