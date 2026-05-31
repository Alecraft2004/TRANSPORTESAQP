package VistasVehiculo;

import Controladores.ControladorVehiculo;
import Modelo.Vehiculo;
import Modelo.VehiculoTabla;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import Modelo.DAOVehiculo;
import VistasVehiculo.ButtonRenderer;
import VistasVehiculo.ButtonEditor;
import VistasMain.VistaMenuPrincipal;

public class VistaVehiculo extends javax.swing.JFrame {

    private ControladorVehiculo controladorVehiculo;
    private VehiculoTabla modeloTabla;
    private DAOVehiculo DAOvehiculo = new DAOVehiculo(); 
    private VistaMenuPrincipal menuPrincipal;
    
    public VistaVehiculo(VistaMenuPrincipal menuPrincipal) {
        this.menuPrincipal = menuPrincipal;
        controladorVehiculo = new ControladorVehiculo(); // Asegúrate de inicializar el controlador
        initComponents();
        cargarVehiculos();  // Cargar datos al iniciar desde el menú

        // Configurar el modelo de la tabla
        modeloTabla = new VehiculoTabla(controladorVehiculo.obtenerTodosLosVehiculos());
        jTable1.setModel(modeloTabla);

        // Configurar renderizadores y editores de botones para Editar y Quitar
        configurarBotones();

        // Configurar botón Agregar
        AgregarBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirFormularioVehiculo(null); // Abrir en modo agregar
            }
        });

        // Configurar botón Buscar
        BuscarBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarVehiculo();
            }
        });
    }



    // Método para regresar al menú principal
    private void regresarAlMenuPrincipal() {
        menuPrincipal.setVisible(true);
        this.dispose();
    }


    private void cargarVehiculos() {
        List<Vehiculo> listaVehiculos = controladorVehiculo.obtenerTodosLosVehiculos();
        modeloTabla = new VehiculoTabla(listaVehiculos);
        jTable1.setModel(modeloTabla);
        configurarBotones();

        // Configurar renderizadores y editores de botones para Editar y Quitar
        configurarBotones();

        // Ajustar los anchos de las columnas
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(50); // Código
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(200); // Placa
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(220); // Número de Serie
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(100); // Año
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(100); // Color
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(100); // Puertas
        jTable1.getColumnModel().getColumn(6).setPreferredWidth(100); // Cilindrada
        jTable1.getColumnModel().getColumn(7).setPreferredWidth(100); // Dueño
        jTable1.getColumnModel().getColumn(8).setPreferredWidth(80); // Editar
        jTable1.getColumnModel().getColumn(9).setPreferredWidth(80); // Quitar

        // Hacer que la tabla ajuste automáticamente el ancho de las columnas según el contenido
        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    }


    private void abrirFormularioVehiculo(Vehiculo vehiculo) {
    FormularioVehiculo formulario = new FormularioVehiculo(vehiculo, controladorVehiculo);
    
    formulario.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosed(java.awt.event.WindowEvent e) {
            cargarVehiculos();
        }
    });
    
    formulario.setVisible(true);
}


    private void buscarVehiculo() {
        String criterio = TFBuscar.getText().trim(); // Obtener el criterio del campo de texto

        // Llamar al método en el DAO con el criterio (puede estar vacío)
        List<Vehiculo> vehiculos = DAOvehiculo.buscarVehiculosPorCriterio(criterio);

        if (vehiculos.isEmpty() && !criterio.isEmpty()) {
            // Si la búsqueda no encuentra nada y había criterio, mostrar mensaje
            JOptionPane.showMessageDialog(this, "No se encontraron vehículos que coincidan con el criterio.");
        }

        // Actualizar la tabla con los resultados de la búsqueda (o con todos si el criterio estaba vacío)
        modeloTabla.setVehiculos(vehiculos); // Actualiza el modelo de la tabla con los resultados de búsqueda
        jTable1.setModel(modeloTabla);

        // Configurar botones de nuevo para la nueva lista
        configurarBotones();
    }


    private void configurarBotones() {
        // Configurar renderizadores y editores para los botones "Editar" y "Quitar"
        jTable1.getColumn("Editar").setCellRenderer(new ButtonRenderer("Editar"));
        jTable1.getColumn("Editar").setCellEditor(new ButtonEditor(jTable1, "Editar"));

        jTable1.getColumn("Quitar").setCellRenderer(new ButtonRenderer("Quitar"));
        jTable1.getColumn("Quitar").setCellEditor(new ButtonEditor(jTable1, "Quitar"));

        // Ajustar el ancho mínimo de las columnas para los botones
        jTable1.getColumn("Editar").setMinWidth(100);
        jTable1.getColumn("Editar").setMaxWidth(100);
        jTable1.getColumn("Quitar").setMinWidth(100);
        jTable1.getColumn("Quitar").setMaxWidth(100);
    }


    public void editarVehiculo(int row) {
        if (row >= 0) { // Verifica que la fila seleccionada sea válida
            Vehiculo vehiculo = modeloTabla.getVehiculoAt(row);
            abrirFormularioVehiculo(vehiculo);
            cargarVehiculos(); // Recargar después de editar
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un vehículo para editar.");
        }
    }


    public void eliminarVehiculo(int row) {
        if (row >= 0) { // Verifica que la fila seleccionada sea válida
            Vehiculo vehiculo = modeloTabla.getVehiculoAt(row);
            int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar este vehículo?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                controladorVehiculo.eliminarVehiculo(vehiculo.getCodigo());
                cargarVehiculos(); // Recargar la tabla después de eliminar
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un vehículo para eliminar.");
        }
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        AgregarBoton = new javax.swing.JButton();
        TFBuscar = new javax.swing.JTextField();
        BuscarBoton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        AgregarBoton.setBackground(new java.awt.Color(51, 255, 0));
        AgregarBoton.setFont(new java.awt.Font("sansserif", 1, 15)); // NOI18N
        AgregarBoton.setText("Agregar Nuevo Vehículo");

        TFBuscar.setBackground(new java.awt.Color(0, 0, 0));
        TFBuscar.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        TFBuscar.setForeground(new java.awt.Color(255, 255, 255));

        BuscarBoton.setBackground(new java.awt.Color(0, 0, 204));
        BuscarBoton.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        BuscarBoton.setForeground(new java.awt.Color(255, 255, 255));
        BuscarBoton.setText("Buscar");

        jTable1.setBackground(new java.awt.Color(51, 47, 38));
        jTable1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTable1.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        jTable1.setForeground(new java.awt.Color(255, 255, 255));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9"
            }
        ));
        jTable1.setMaximumSize(new java.awt.Dimension(2147483647, 120));
        jTable1.setMinimumSize(new java.awt.Dimension(180, 120));
        jTable1.setRowHeight(30);
        jScrollPane1.setViewportView(jTable1);

        jButton1.setBackground(new java.awt.Color(255, 0, 255));
        jButton1.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Volver la menú");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(TFBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 628, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(BuscarBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 962, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AgregarBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(338, 338, 338)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(AgregarBoton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TFBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BuscarBoton))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jButton1)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        regresarAlMenuPrincipal();
     }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AgregarBoton;
    private javax.swing.JButton BuscarBoton;
    private javax.swing.JTextField TFBuscar;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
