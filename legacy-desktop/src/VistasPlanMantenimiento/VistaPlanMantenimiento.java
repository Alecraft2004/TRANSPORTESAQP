package VistasPlanMantenimiento;

import Controladores.ControladorPlanMantenimiento;
import Modelo.PlanMantenimiento;
import Modelo.PlanMantenimientoTabla;
import Modelo.DAOVehiculo;
import Modelo.DAOTecnico;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import Modelo.Tecnico;
import Modelo.Vehiculo;
import VistasMain.VistaMenuPrincipal;

public class VistaPlanMantenimiento extends javax.swing.JFrame {
    
    private ControladorPlanMantenimiento controladorPlan;
    private PlanMantenimientoTabla modeloTabla;
    private DAOVehiculo vehiculoDAO;
    private DAOTecnico TecnicoDAO;
    private VistaMenuPrincipal menuPrincipal;
    
    // Constructor que acepta el menú principal
    public VistaPlanMantenimiento(VistaMenuPrincipal menuPrincipal) {
        this.menuPrincipal = menuPrincipal;
        controladorPlan = new ControladorPlanMantenimiento(); // Inicializar el controlador aquí
        initComponents();
        cargarPlanes(); // Cargar datos al iniciar desde el menú

        // Configurar las acciones de los botones
        AgregarBoton.addActionListener(this::abrirFormularioNuevo);
        BuscarBoton.addActionListener(this::buscarPlan);
        jButton1.addActionListener(e -> regresarAlMenuPrincipal());
    }

    // Método para regresar al menú principal
    private void regresarAlMenuPrincipal() {
        menuPrincipal.setVisible(true);
        this.dispose();
    }

    private void cargarPlanes() {
        List<PlanMantenimiento> planes = controladorPlan.obtenerTodosLosPlanes();
        modeloTabla = new PlanMantenimientoTabla(planes);
        jTable1.setModel(modeloTabla);
        configurarAccionesTabla();
    }

    private void abrirFormularioNuevo(ActionEvent evt) {
        abrirFormulario(null);  // Llama al formulario en modo "nuevo" sin un plan específico
    }

    private void abrirFormulario(PlanMantenimiento plan) {
        FormularioPlanMantenimiento formulario = new FormularioPlanMantenimiento(plan, controladorPlan);
        formulario.setVisible(true);

        // Recargar la tabla cuando el formulario se cierre
        formulario.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                cargarPlanes();  // Recarga la lista completa de planes
            }
        });
    }

    private void buscarPlan(ActionEvent evt) {
        String criterio = TFBuscar.getText().trim();
        List<PlanMantenimiento> planes;

        if (criterio.isEmpty()) {
            planes = controladorPlan.obtenerTodosLosPlanes();  // Cargar todos los planes si el criterio está vacío
        } else {
            planes = controladorPlan.buscarPlanesPorCriterio(criterio);  // Cargar solo los planes que coinciden con el criterio
        }

        modeloTabla.setPlanes(planes);  // Actualiza la tabla con los resultados de búsqueda
    }

    private void configurarAccionesTabla() {
        jTable1.getColumn("Editar").setCellRenderer(new ButtonRenderer("Editar"));
        jTable1.getColumn("Editar").setCellEditor(new ButtonEditor(jTable1, "Editar", this));

        jTable1.getColumn("Eliminar").setCellRenderer(new ButtonRenderer("Eliminar"));
        jTable1.getColumn("Eliminar").setCellEditor(new ButtonEditor(jTable1, "Eliminar", this));

        jTable1.getColumn("Ver Vehículo").setCellRenderer(new ButtonRenderer("Ver Vehículo"));
        jTable1.getColumn("Ver Vehículo").setCellEditor(new ButtonEditor(jTable1, "Ver Vehículo", this));

        // Agregar la columna "Ver Técnico"
        jTable1.getColumn("Ver Técnico").setCellRenderer(new ButtonRenderer("Ver Técnico"));
        jTable1.getColumn("Ver Técnico").setCellEditor(new ButtonEditor(jTable1, "Ver Técnico", this));
    }


    void editarPlan(int row) {
        PlanMantenimiento plan = modeloTabla.getPlanAt(row);
        abrirFormulario(plan);  // Llama al formulario con el plan seleccionado para editar
    }

    void eliminarPlan(int row) {
        PlanMantenimiento plan = modeloTabla.getPlanAt(row);
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar este plan de mantenimiento?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            controladorPlan.eliminarPlan(plan.getCodigo());
            cargarPlanes(); // Recargar la tabla después de eliminar
        }
    }
    
    void verVehiculo(int row) {
        PlanMantenimiento plan = modeloTabla.getPlanAt(row);
        Vehiculo vehiculo = controladorPlan.obtenerVehiculo(plan.getIdVehiculo()); // Obtener el objeto Vehiculo
        VistaVehiculo vistaVehiculo = new VistaVehiculo(vehiculo); // Pasar el Vehiculo a la vista
        vistaVehiculo.setVisible(true);
    }
    
    void verTecnico(int row) {
        PlanMantenimiento plan = modeloTabla.getPlanAt(row);
        int idTecnico = plan.getIdTecnico();  // Obtén el ID del técnico relacionado con el plan

        // Obtener el técnico a partir del ID (suponiendo que tienes un DAO para técnicos)
        DAOTecnico DAOtecnico = new DAOTecnico();
        Tecnico tecnico = DAOtecnico.obtenerTecnicoPorId(idTecnico);

        // Mostrar los datos en la vista de técnico
        if (tecnico != null) {
            VistaTecnico vistaTecnico = new VistaTecnico(tecnico); // Pasa el objeto técnico al constructor de la vista
            vistaTecnico.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró el técnico especificado.", "Error", JOptionPane.ERROR_MESSAGE);
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

        AgregarBoton.setBackground(new java.awt.Color(0, 255, 0));
        AgregarBoton.setFont(new java.awt.Font("sansserif", 1, 15)); // NOI18N
        AgregarBoton.setText("Agregar Nuevo Plan de Mantenimiento");
        AgregarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgregarBotonActionPerformed(evt);
            }
        });

        TFBuscar.setBackground(new java.awt.Color(0, 0, 0));
        TFBuscar.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        TFBuscar.setForeground(new java.awt.Color(255, 255, 255));
        TFBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFBuscarActionPerformed(evt);
            }
        });

        BuscarBoton.setBackground(new java.awt.Color(0, 0, 255));
        BuscarBoton.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        BuscarBoton.setForeground(new java.awt.Color(255, 255, 255));
        BuscarBoton.setText("Buscar");

        jTable1.setBackground(new java.awt.Color(51, 47, 38));
        jTable1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTable1.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        jTable1.setForeground(new java.awt.Color(255, 255, 255));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9", "Title 10", "Title 11", "Title 12"
            }
        ));
        jTable1.setAlignmentX(0.9F);
        jTable1.setAlignmentY(0.9F);
        jTable1.setGridColor(new java.awt.Color(51, 51, 51));
        jTable1.setInheritsPopupMenu(true);
        jTable1.setOpaque(false);
        jTable1.setRowHeight(30);
        jTable1.setShowGrid(true);
        jTable1.setSurrendersFocusOnKeystroke(true);
        jScrollPane1.setViewportView(jTable1);

        jButton1.setBackground(new java.awt.Color(204, 0, 204));
        jButton1.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Volver al Menú");
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
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1076, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(TFBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 756, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(BuscarBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(AgregarBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(363, 363, 363)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(AgregarBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TFBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BuscarBoton))
                .addGap(38, 38, 38)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(28, 28, 28))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AgregarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarBotonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AgregarBotonActionPerformed

    private void TFBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFBuscarActionPerformed

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
