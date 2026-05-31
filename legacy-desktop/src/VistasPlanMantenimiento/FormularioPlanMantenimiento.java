package VistasPlanMantenimiento;

import Controladores.ControladorPlanMantenimiento;
import Controladores.ControladorTecnico;
import Controladores.ControladorVehiculo;
import Modelo.PlanMantenimiento;
import Modelo.Tecnico;
import Modelo.Vehiculo;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;
import com.toedter.calendar.JDateChooser;
import java.text.SimpleDateFormat;


public class FormularioPlanMantenimiento extends javax.swing.JFrame {

    private PlanMantenimiento plan;
    private ControladorPlanMantenimiento controlador;
    private ControladorTecnico controladorTecnico;
    private ControladorVehiculo controladorVehiculo;
    private JDateChooser dateChooser;

    public FormularioPlanMantenimiento(PlanMantenimiento plan, ControladorPlanMantenimiento controlador) {
        initComponents();
        this.plan = plan;
        this.controlador = controlador;
        this.controladorTecnico = new ControladorTecnico();
        this.controladorVehiculo = new ControladorVehiculo();
        
        // Inicializa el JDateChooser
        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("yyyy-MM-dd"); // Configura el formato de fecha

        // Añade el JDateChooser al panelFecha
        jPanel1.setLayout(new java.awt.BorderLayout()); // Asegúrate de que el layout sea BorderLayout para que el JDateChooser ocupe todo el espacio
        jPanel1.add(dateChooser);

        // Configura el ComboBox de tipo de mantenimiento con opciones válidas
        CBTipoMantenimiento.setModel(new DefaultComboBoxModel<>(new String[]{
            "Cambio de Aceite", 
            "Repintado", 
            "Reparación interna",
            "Reparación externa",
        }));
        cargarTecnicos();
        cargarVehiculos();

        if (plan != null) {
            cargarDatosPlan();
        }

        GuardarBoton.addActionListener((ActionEvent e) -> guardarPlan());
        CancelarBoton.addActionListener(e -> dispose());
    }

    private void cargarTecnicos() {
        List<Tecnico> tecnicos = controladorTecnico.obtenerTodosLosTecnicos();
        CBTecnico.removeAllItems();
        for (Tecnico tecnico : tecnicos) {
            CBTecnico.addItem(tecnico); // Añade el objeto completo sin convertir a String
        }
    }

    private void cargarVehiculos() {
        List<Vehiculo> vehiculos = controladorVehiculo.obtenerTodosLosVehiculos();
        CBVehiculo.removeAllItems();
        for (Vehiculo vehiculo : vehiculos) {
            CBVehiculo.addItem(vehiculo); // Añade el objeto completo sin convertir a String
        }
    }



    private void cargarDatosPlan() {
        TFCodigo.setText(String.valueOf(plan.getCodigo()));
        TFCodigo.setEditable(false); 
        CBTecnico.setSelectedItem(plan.getIdTecnico());
        CBVehiculo.setSelectedItem(plan.getIdVehiculo());
        CBTipoMantenimiento.setSelectedItem(plan.getTipoMantenimiento());
        TFKilometraje.setText(String.valueOf(plan.getKilometraje()));
        TFFalla.setText(plan.getFalla());
        TFPago.setText(String.valueOf(plan.getServicioPago()));
    }

    private void guardarPlan() {
        try {
            Tecnico tecnicoSeleccionado = (Tecnico) CBTecnico.getSelectedItem();
            Vehiculo vehiculoSeleccionado = (Vehiculo) CBVehiculo.getSelectedItem();

            if (tecnicoSeleccionado == null) {
                JOptionPane.showMessageDialog(this, "Por favor, selecciona un técnico.");
                return;
            }

            if (vehiculoSeleccionado == null) {
                JOptionPane.showMessageDialog(this, "Por favor, selecciona un vehículo.");
                return;
            }

            String tipoMantenimiento = (String) CBTipoMantenimiento.getSelectedItem();
            int kilometraje = Integer.parseInt(TFKilometraje.getText().trim());
            String falla = TFFalla.getText().trim();
            double servicioPago = Double.parseDouble(TFPago.getText().trim());

                        // Obtén la fecha seleccionada
            java.util.Date fechaSeleccionada = dateChooser.getDate();
            if (fechaSeleccionada == null) {
                JOptionPane.showMessageDialog(this, "Por favor, selecciona una fecha.");
                return;
            }

            // Formatear fecha a String
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String fechaFormateada = sdf.format(fechaSeleccionada);


            if (plan == null) {
                plan = new PlanMantenimiento(0, tecnicoSeleccionado.getCodigo(), vehiculoSeleccionado.getCodigo(), tipoMantenimiento, kilometraje, falla, fechaFormateada, servicioPago);
                controlador.agregarPlan(plan);
                JOptionPane.showMessageDialog(this, "Plan de mantenimiento agregado correctamente.");
            } else {
                plan.setIdTecnico(tecnicoSeleccionado.getCodigo());
                plan.setIdVehiculo(vehiculoSeleccionado.getCodigo());
                plan.setTipoMantenimiento(tipoMantenimiento);
                plan.setKilometraje(kilometraje);
                plan.setFalla(falla);
                plan.setFecha(fechaFormateada); // Guardar la fecha seleccionada
                plan.setServicioPago(servicioPago);
                controlador.actualizarPlan(plan);
                JOptionPane.showMessageDialog(this, "Plan de mantenimiento actualizado correctamente.");
            }
            dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error: Asegúrate de que los valores numéricos sean válidos.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar el plan: " + e.getMessage());
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

        LTitulo = new javax.swing.JLabel();
        LCodigo = new javax.swing.JLabel();
        LTecnico = new javax.swing.JLabel();
        LVehiculo = new javax.swing.JLabel();
        LDir = new javax.swing.JLabel();
        LKilometraje = new javax.swing.JLabel();
        LFalla = new javax.swing.JLabel();
        CancelarBoton = new javax.swing.JButton();
        GuardarBoton = new javax.swing.JButton();
        LPago = new javax.swing.JLabel();
        CBTecnico = new javax.swing.JComboBox<>();
        CBVehiculo = new javax.swing.JComboBox<>();
        CBTipoMantenimiento = new javax.swing.JComboBox<>();
        TFCodigo = new javax.swing.JTextField();
        TFKilometraje = new javax.swing.JTextField();
        TFFalla = new javax.swing.JTextField();
        TFPago = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(52, 50, 55));

        LTitulo.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        LTitulo.setText("FORMULARIO PLAN DE MANTENIMIENTO");

        LCodigo.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        LCodigo.setText("Código:");

        LTecnico.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        LTecnico.setText("Técnico:");

        LVehiculo.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        LVehiculo.setText("Vehículo:");

        LDir.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        LDir.setText("Tipo de Mantenimiento:");

        LKilometraje.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        LKilometraje.setText("Kilometraje:");

        LFalla.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        LFalla.setText("Falla:");

        CancelarBoton.setBackground(new java.awt.Color(255, 51, 51));
        CancelarBoton.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        CancelarBoton.setText("Cancelar");

        GuardarBoton.setBackground(new java.awt.Color(51, 255, 0));
        GuardarBoton.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        GuardarBoton.setText("Guardar");

        LPago.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        LPago.setText("Servicio de Pago");

        CBTecnico.setBackground(new java.awt.Color(153, 153, 153));
        CBTecnico.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        CBTecnico.setForeground(new java.awt.Color(255, 255, 255));
        CBTecnico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBTecnicoActionPerformed(evt);
            }
        });

        CBVehiculo.setBackground(new java.awt.Color(153, 153, 153));
        CBVehiculo.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        CBVehiculo.setForeground(new java.awt.Color(255, 255, 255));

        CBTipoMantenimiento.setBackground(new java.awt.Color(153, 153, 153));
        CBTipoMantenimiento.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        CBTipoMantenimiento.setForeground(new java.awt.Color(255, 255, 255));
        CBTipoMantenimiento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        TFCodigo.setBackground(new java.awt.Color(0, 0, 0));
        TFCodigo.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        TFCodigo.setForeground(new java.awt.Color(255, 255, 255));

        TFKilometraje.setBackground(new java.awt.Color(0, 0, 0));
        TFKilometraje.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        TFKilometraje.setForeground(new java.awt.Color(255, 255, 255));

        TFFalla.setBackground(new java.awt.Color(0, 0, 0));
        TFFalla.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        TFFalla.setForeground(new java.awt.Color(255, 255, 255));

        TFPago.setBackground(new java.awt.Color(0, 0, 0));
        TFPago.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        TFPago.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel1.setText("Fecha:");

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 172, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 197, Short.MAX_VALUE)
                        .addComponent(CancelarBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(GuardarBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LTecnico)
                            .addComponent(LVehiculo)
                            .addComponent(LDir)
                            .addComponent(LKilometraje)
                            .addComponent(LFalla)
                            .addComponent(LPago))
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CBTecnico, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CBVehiculo, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CBTipoMantenimiento, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TFCodigo)
                            .addComponent(TFKilometraje)
                            .addComponent(TFFalla)
                            .addComponent(TFPago)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(LTitulo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(161, 161, 161)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(44, 44, 44))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LCodigo)
                    .addComponent(TFCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LTecnico)
                    .addComponent(CBTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LVehiculo)
                    .addComponent(CBVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LDir)
                    .addComponent(CBTipoMantenimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LKilometraje)
                    .addComponent(TFKilometraje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LFalla)
                    .addComponent(TFFalla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LPago)
                    .addComponent(TFPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CancelarBoton)
                    .addComponent(GuardarBoton))
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CBTecnicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBTecnicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CBTecnicoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Tecnico> CBTecnico;
    private javax.swing.JComboBox<String> CBTipoMantenimiento;
    private javax.swing.JComboBox<Vehiculo> CBVehiculo;
    private javax.swing.JButton CancelarBoton;
    private javax.swing.JButton GuardarBoton;
    private javax.swing.JLabel LCodigo;
    private javax.swing.JLabel LDir;
    private javax.swing.JLabel LFalla;
    private javax.swing.JLabel LKilometraje;
    private javax.swing.JLabel LPago;
    private javax.swing.JLabel LTecnico;
    private javax.swing.JLabel LTitulo;
    private javax.swing.JLabel LVehiculo;
    private javax.swing.JTextField TFCodigo;
    private javax.swing.JTextField TFFalla;
    private javax.swing.JTextField TFKilometraje;
    private javax.swing.JTextField TFPago;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
