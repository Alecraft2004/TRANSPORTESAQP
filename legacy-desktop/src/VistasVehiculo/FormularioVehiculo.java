package VistasVehiculo;

import Controladores.ControladorVehiculo;
import Modelo.Vehiculo;
import Modelo.DAOVehiculo;

import javax.swing.*;

public class FormularioVehiculo extends javax.swing.JFrame {
    private Vehiculo vehiculo;
    private ControladorVehiculo controladorVehiculo;
    private DAOVehiculo DAOvehiculo; // Añadimos esta línea para la instancia de VehiculoDAO
    
    private boolean validarCodigo() {
        String codigo = TFCodigo.getText().trim();
        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El código es obligatorio.");
            return false;
        }

        try {
            int codigoInt = Integer.parseInt(codigo);

            // Verifica si el código ya existe en la base de datos
            if (DAOvehiculo.existeCodigo(codigoInt)) {
                int ultimoCodigo = DAOvehiculo.obtenerUltimoCodigo();
                JOptionPane.showMessageDialog(this, "El código ya existe. El último código utilizado fue: " + ultimoCodigo);
                return false;
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El código debe ser un número válido.");
            return false;
        }

        return true;
    }
    
     private boolean validarPlaca() {
        String placa = TFPlaca.getText().trim();
        if (placa.isEmpty()) {
            JOptionPane.showMessageDialog(this, "La placa es obligatoria.");
            return false;
        }
        return true;
    }

    private boolean validarAnhoFabricacion() {
        String anho = TFAnho.getText().trim();
        try {
            int anhoFabricacion = Integer.parseInt(anho);
            int anhoActual = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
            if (anhoFabricacion < 1900 || anhoFabricacion > anhoActual) {
                JOptionPane.showMessageDialog(this, "El año de fabricación debe estar entre 1900 y " + anhoActual + ".");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El año de fabricación debe ser un número.");
            return false;
        }
        return true;
    }

    private boolean validarCilindrada() {
        String cilindrada = TFClindridada.getText().trim();
        try {
            double cilindradaValue = Double.parseDouble(cilindrada);
            if (cilindradaValue <= 0) {
                JOptionPane.showMessageDialog(this, "La cilindrada debe ser un número positivo.");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La cilindrada debe ser un número válido.");
            return false;
        }
        return true;
    }
    
    public FormularioVehiculo(Vehiculo vehiculo, ControladorVehiculo controladorVehiculo) {
        initComponents();
        this.vehiculo = vehiculo;
        this.controladorVehiculo = controladorVehiculo;
        this.DAOvehiculo = new DAOVehiculo(); // Inicializamos vehiculoDAO aquí
        // Si el vehículo no es null, estamos en modo editar y cargamos los datos
        if (vehiculo != null) {
            cargarDatosVehiculo();
        }

        // Configurar acciones para los botones
        GuardarBoton.addActionListener(e -> guardarVehiculo());
        CancelarBoton.addActionListener(e -> dispose()); // Cierra el formulario sin guardar
    }

    private void cargarDatosVehiculo() {
        TFCodigo.setText(String.valueOf(vehiculo.getCodigo()));
        TFPlaca.setText(vehiculo.getPlaca());
        TFSerie.setText(vehiculo.getNumeroSerie());
        TFAnho.setText(String.valueOf(vehiculo.getAnhoFabricacion()));
        TFColor.setText(vehiculo.getColor());
        TFPuertas.setText(String.valueOf(vehiculo.getCantidadPuertas()));
        TFClindridada.setText(String.valueOf(vehiculo.getCilindrada()));
        TFDuenho.setText(vehiculo.getDuenho());

        // El campo "Código" no debe ser editable en el caso de edición
        TFCodigo.setEditable(false);
    }

    private void guardarVehiculo() {
        // Primero, validamos el código (solo para nuevo registro)
        if (vehiculo == null && !validarCodigo()) return;
        if (!validarPlaca()) return;
        if (!validarAnhoFabricacion()) return;
        if (!validarCilindrada()) return;

        try {
            String placa = TFPlaca.getText();
            String numeroSerie = TFSerie.getText();
            int anhoFabricacion = Integer.parseInt(TFAnho.getText());
            String color = TFColor.getText();
            int cantidadPuertas = Integer.parseInt(TFPuertas.getText());
            double cilindrada = Double.parseDouble(TFClindridada.getText());
            String duenho = TFDuenho.getText();

            if (vehiculo == null) { // Modo Agregar
                vehiculo = new Vehiculo(0, placa, numeroSerie, anhoFabricacion, color, cantidadPuertas, cilindrada, duenho);
                controladorVehiculo.agregarVehiculo(vehiculo);
                JOptionPane.showMessageDialog(this, "Vehículo agregado exitosamente.");
            } else { // Modo Editar
                vehiculo.setPlaca(placa);
                vehiculo.setNumeroSerie(numeroSerie);
                vehiculo.setAnhoFabricacion(anhoFabricacion);
                vehiculo.setColor(color);
                vehiculo.setCantidadPuertas(cantidadPuertas);
                vehiculo.setCilindrada(cilindrada);
                vehiculo.setDuenho(duenho);
                controladorVehiculo.actualizarVehiculo(vehiculo);
                JOptionPane.showMessageDialog(this, "Vehículo actualizado exitosamente.");
            }
            dispose(); // Cerrar el formulario después de guardar
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese valores válidos en los campos numéricos.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
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
        LPlaca = new javax.swing.JLabel();
        LSerie = new javax.swing.JLabel();
        LAnho = new javax.swing.JLabel();
        LColor = new javax.swing.JLabel();
        LPuertaa = new javax.swing.JLabel();
        LCilindrada = new javax.swing.JLabel();
        LDuenho = new javax.swing.JLabel();
        TFCodigo = new javax.swing.JTextField();
        TFPlaca = new javax.swing.JTextField();
        TFSerie = new javax.swing.JTextField();
        TFAnho = new javax.swing.JTextField();
        TFColor = new javax.swing.JTextField();
        TFPuertas = new javax.swing.JTextField();
        TFClindridada = new javax.swing.JTextField();
        TFDuenho = new javax.swing.JTextField();
        CancelarBoton = new javax.swing.JButton();
        GuardarBoton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        LTitulo.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        LTitulo.setText("FORMULARIO VEHÍCULO");

        LCodigo.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        LCodigo.setText("Código:");

        LPlaca.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        LPlaca.setText("Placa:");

        LSerie.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        LSerie.setText("Número de serie:");

        LAnho.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        LAnho.setText("Año de Fabricación:");

        LColor.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        LColor.setText("Color:");

        LPuertaa.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        LPuertaa.setText("Cantidad de Puertas:");

        LCilindrada.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        LCilindrada.setText("Cilindrada:");

        LDuenho.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        LDuenho.setText("Dueño:");

        TFCodigo.setBackground(new java.awt.Color(0, 0, 0));
        TFCodigo.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        TFCodigo.setForeground(new java.awt.Color(255, 255, 255));
        TFCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFCodigoActionPerformed(evt);
            }
        });

        TFPlaca.setBackground(new java.awt.Color(0, 0, 0));
        TFPlaca.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        TFPlaca.setForeground(new java.awt.Color(255, 255, 255));

        TFSerie.setBackground(new java.awt.Color(0, 0, 0));
        TFSerie.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        TFSerie.setForeground(new java.awt.Color(255, 255, 255));

        TFAnho.setBackground(new java.awt.Color(0, 0, 0));
        TFAnho.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        TFAnho.setForeground(new java.awt.Color(255, 255, 255));

        TFColor.setBackground(new java.awt.Color(0, 0, 0));
        TFColor.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        TFColor.setForeground(new java.awt.Color(255, 255, 255));

        TFPuertas.setBackground(new java.awt.Color(0, 0, 0));
        TFPuertas.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        TFPuertas.setForeground(new java.awt.Color(255, 255, 255));

        TFClindridada.setBackground(new java.awt.Color(0, 0, 0));
        TFClindridada.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        TFClindridada.setForeground(new java.awt.Color(255, 255, 255));

        TFDuenho.setBackground(new java.awt.Color(0, 0, 0));
        TFDuenho.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        TFDuenho.setForeground(new java.awt.Color(255, 255, 255));

        CancelarBoton.setBackground(new java.awt.Color(255, 0, 0));
        CancelarBoton.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        CancelarBoton.setText("Cancelar");

        GuardarBoton.setBackground(new java.awt.Color(51, 255, 0));
        GuardarBoton.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        GuardarBoton.setText("Guardar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(CancelarBoton, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(GuardarBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(LCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(LPlaca)
                                    .addComponent(LSerie)
                                    .addComponent(LAnho)
                                    .addComponent(LColor)
                                    .addComponent(LPuertaa)
                                    .addComponent(LCilindrada)
                                    .addComponent(LDuenho))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(TFCodigo)
                                    .addComponent(TFPlaca)
                                    .addComponent(TFSerie)
                                    .addComponent(TFAnho)
                                    .addComponent(TFColor)
                                    .addComponent(TFPuertas)
                                    .addComponent(TFClindridada)
                                    .addComponent(TFDuenho, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(LTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
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
                    .addComponent(LPlaca)
                    .addComponent(TFPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LSerie)
                    .addComponent(TFSerie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LAnho)
                    .addComponent(TFAnho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LColor)
                    .addComponent(TFColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LPuertaa)
                    .addComponent(TFPuertas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LCilindrada)
                    .addComponent(TFClindridada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LDuenho)
                    .addComponent(TFDuenho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CancelarBoton)
                    .addComponent(GuardarBoton))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TFCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFCodigoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CancelarBoton;
    private javax.swing.JButton GuardarBoton;
    private javax.swing.JLabel LAnho;
    private javax.swing.JLabel LCilindrada;
    private javax.swing.JLabel LCodigo;
    private javax.swing.JLabel LColor;
    private javax.swing.JLabel LDuenho;
    private javax.swing.JLabel LPlaca;
    private javax.swing.JLabel LPuertaa;
    private javax.swing.JLabel LSerie;
    private javax.swing.JLabel LTitulo;
    private javax.swing.JTextField TFAnho;
    private javax.swing.JTextField TFClindridada;
    private javax.swing.JTextField TFCodigo;
    private javax.swing.JTextField TFColor;
    private javax.swing.JTextField TFDuenho;
    private javax.swing.JTextField TFPlaca;
    private javax.swing.JTextField TFPuertas;
    private javax.swing.JTextField TFSerie;
    // End of variables declaration//GEN-END:variables
}
