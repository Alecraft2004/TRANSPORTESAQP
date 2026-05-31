
package Modelo;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TecnicoTabla extends AbstractTableModel {

    private List<Tecnico> listaTecnicos;
    private final String[] columnas = {"Código", "Nombres", "Apellidos", "Dirección", "Sexo", "Correo", "Celular", "Especialidad", "Tiempo Servicio", "Editar", "Eliminar"};

    public TecnicoTabla(List<Tecnico> listaTecnicos) {
        this.listaTecnicos = listaTecnicos;
    }

    @Override
    public int getRowCount() {
        return listaTecnicos.size();
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
        Tecnico tecnico = listaTecnicos.get(rowIndex);
        switch (columnIndex) {
            case 0: return tecnico.getCodigo();
            case 1: return tecnico.getNombres();
            case 2: return tecnico.getApellidos();
            case 3: return tecnico.getDireccion();
            case 4: return tecnico.getSexo();
            case 5: return tecnico.getCorreo();
            case 6: return tecnico.getCelular();
            case 7: return tecnico.getEspecialidad();
            case 8: return tecnico.getTiempoServicio();
            case 9: return "Editar";
            case 10: return "Eliminar";
            default: return null;
        }
    }

    public Tecnico getTecnicoAt(int rowIndex) {
        return listaTecnicos.get(rowIndex);
    }

    public void setTecnicos(List<Tecnico> listaTecnicos) {
        this.listaTecnicos = listaTecnicos;
        fireTableDataChanged();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 9 || columnIndex == 10;
    }
}
