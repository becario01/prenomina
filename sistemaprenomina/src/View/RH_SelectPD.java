/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Conexion.Conexion1;
import Controller.PercepcionesDeducciones;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Programacion 2
 */
public class RH_SelectPD extends javax.swing.JFrame {
Connection conn;
    PreparedStatement stmt;
    public static ResultSet rs;
    private Connection userConn;
    static String nombresem;
    public RH_SelectPD(String nomsem) {
        initComponents();
        nombresem=nomsem;
            this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new java.awt.Color(233, 236, 241));
    }

    public String[] datos() {

        String datos[] = new String[11];
        datos[0] = cmbp1.getSelectedItem().toString();
        datos[1] = cmbp2.getSelectedItem().toString();
        datos[2] = cmbp3.getSelectedItem().toString();
        datos[3] = cmbp4.getSelectedItem().toString();
        datos[4] = cmbp5.getSelectedItem().toString();
        datos[5] = cmbp6.getSelectedItem().toString();
        datos[6] = cmbp7.getSelectedItem().toString();
        datos[7] = cmbp8.getSelectedItem().toString();
        datos[8] = cmbp9.getSelectedItem().toString();
        datos[9] = cmbp10.getSelectedItem().toString();
        datos[10] = cmbp11.getSelectedItem().toString();
    
        
        
        return datos;
    }
       

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox4 = new javax.swing.JComboBox();
        jComboBox7 = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cmbp1 = new javax.swing.JComboBox();
        cmbp2 = new javax.swing.JComboBox();
        cmbp3 = new javax.swing.JComboBox();
        cmbp5 = new javax.swing.JComboBox();
        cmbp4 = new javax.swing.JComboBox();
        cmbp6 = new javax.swing.JComboBox();
        cmbp7 = new javax.swing.JComboBox();
        cmbp8 = new javax.swing.JComboBox();
        cmbp9 = new javax.swing.JComboBox();
        cmbp10 = new javax.swing.JComboBox();
        cmbp11 = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        lblcod = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        lblnombre = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1" }));

        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Bono de productividad");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Premio de asistencia");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Diferencia de sueldo");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Suplencia");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Festivo");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("Prima dominical");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("Sobre sueldo");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setText("Falta administrativa");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setText("Falta sancion ");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setText("Daño a la producción ");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setText("Daño a la calidad");

        cmbp1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cmbp1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1" }));

        cmbp2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cmbp2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1" }));

        cmbp3.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cmbp3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1" }));

        cmbp5.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cmbp5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1" }));

        cmbp4.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cmbp4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1" }));

        cmbp6.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cmbp6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1" }));

        cmbp7.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cmbp7.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1" }));

        cmbp8.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cmbp8.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1" }));

        cmbp9.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cmbp9.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1" }));

        cmbp10.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cmbp10.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1" }));

        cmbp11.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cmbp11.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1" }));

        jPanel2.setBackground(new java.awt.Color(138, 229, 239));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/minimizar.png"))); // NOI18N
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 32, 30));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/regresar.png"))); // NOI18N
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 0, 32, 30));
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 360, 50));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/save1.png"))); // NOI18N
        jButton3.setContentAreaFilled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        lblcod.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lblcod.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jSeparator3.setBackground(new java.awt.Color(204, 204, 204));

        lblnombre.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lblnombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jSeparator4.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblcod, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbp1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbp2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbp3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbp5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbp4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbp6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbp7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbp8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbp9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbp10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbp11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(43, 43, 43))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cmbp1, cmbp10, cmbp11, cmbp2, cmbp3, cmbp4, cmbp5, cmbp6, cmbp7, cmbp8, cmbp9});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(lblcod, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cmbp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cmbp2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cmbp3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cmbp4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)))
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addComponent(cmbp5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbp6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbp7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbp8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cmbp9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbp10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cmbp11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cmbp1, cmbp10, cmbp11, cmbp2, cmbp3, cmbp4, cmbp5, cmbp6, cmbp7, cmbp8, cmbp9});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.setExtendedState(ICONIFIED);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        try {
            this.show(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en: " + e,"ERROR",JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            String id = lblcod.getText();
            
            String idsem= String.valueOf(semana(nombresem));
            PercepcionesDeducciones per= new PercepcionesDeducciones();
            per.insertar(id, idsem, datos(),rootPane);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR EN:  " + e,"ERROR",JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_jButton3ActionPerformed
public int semana( String nomsem){
    String sql = "select * from semanas where semana='"+nomsem+"' ";
     
int codigo=0;
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion1.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
              codigo=Integer.valueOf(rs.getString("idSemana"));
            }
        } catch (NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar los datos\n" + e, "ERROR", JOptionPane.ERROR_MESSAGE);
        } finally {
            Conexion1.close(rs);
            Conexion1.close(stmt);
            if (this.userConn == null) {
                Conexion1.close(conn);
            }
        }
        return codigo;
    
}
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
             * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RH_SelectPD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RH_SelectPD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RH_SelectPD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RH_SelectPD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RH_SelectPD(nombresem).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbp1;
    private javax.swing.JComboBox cmbp10;
    private javax.swing.JComboBox cmbp11;
    private javax.swing.JComboBox cmbp2;
    private javax.swing.JComboBox cmbp3;
    private javax.swing.JComboBox cmbp4;
    private javax.swing.JComboBox cmbp5;
    private javax.swing.JComboBox cmbp6;
    private javax.swing.JComboBox cmbp7;
    private javax.swing.JComboBox cmbp8;
    private javax.swing.JComboBox cmbp9;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox jComboBox4;
    private javax.swing.JComboBox jComboBox7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    public static javax.swing.JLabel lblcod;
    public static javax.swing.JLabel lblnombre;
    // End of variables declaration//GEN-END:variables
}
