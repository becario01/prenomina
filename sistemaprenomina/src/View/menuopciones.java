/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.CargarXLS;

import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author Becarios
 */
public class menuopciones extends javax.swing.JFrame {
int x , y;
      /**
       * Creates new form menuopciones
       */
      public menuopciones() {
            initComponents();
            this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new java.awt.Color(51, 102, 255));
      }

      /**
       * This method is called from within the constructor to initialize the
       * form. WARNING: Do NOT modify this code. The content of this method is
       * always regenerated by the Form Editor.
       */
      @SuppressWarnings("unchecked")
      // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
      private void initComponents() {

            jLabel2 = new javax.swing.JLabel();
            btnEmpleados = new javax.swing.JButton();
            btnRegistros = new javax.swing.JButton();
            jButton1 = new javax.swing.JButton();
            jPanel1 = new javax.swing.JPanel();
            jLabel1 = new javax.swing.JLabel();
            jLabel3 = new javax.swing.JLabel();
            jButton2 = new javax.swing.JButton();
            jButton3 = new javax.swing.JButton();
            jButton4 = new javax.swing.JButton();
            lblnombrerh = new javax.swing.JLabel();
            jSeparator3 = new javax.swing.JSeparator();
            lblcargo = new javax.swing.JLabel();
            jSeparator2 = new javax.swing.JSeparator();
            jLabel5 = new javax.swing.JLabel();

            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
            setUndecorated(true);

            jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/logo.png"))); // NOI18N
            jLabel2.setText("jLabel2");

            btnEmpleados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/addUser.png"))); // NOI18N
            btnEmpleados.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnEmpleadosActionPerformed(evt);
                  }
            });

            btnRegistros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/addregistros.png"))); // NOI18N
            btnRegistros.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnRegistrosActionPerformed(evt);
                  }
            });

            jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/siguiente.png"))); // NOI18N
            jButton1.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jButton1ActionPerformed(evt);
                  }
            });

            jPanel1.setBackground(new java.awt.Color(229, 230, 234));
            jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/prenomina/portafolio.png"))); // NOI18N
            jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, -1, 40));

            jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/prenomina/user.png"))); // NOI18N
            jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, -1, 40));

            jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/prenomina/minimizar.png"))); // NOI18N
            jButton2.setBorderPainted(false);
            jButton2.setContentAreaFilled(false);
            jButton2.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jButton2ActionPerformed(evt);
                  }
            });
            jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 0, 32, 30));

            jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/prenomina/error.png"))); // NOI18N
            jButton3.setBorderPainted(false);
            jButton3.setContentAreaFilled(false);
            jButton3.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jButton3ActionPerformed(evt);
                  }
            });
            jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 0, 32, 30));

            jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/prenomina/regresar.png"))); // NOI18N
            jButton4.setBorderPainted(false);
            jButton4.setContentAreaFilled(false);
            jButton4.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jButton4ActionPerformed(evt);
                  }
            });
            jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 0, 32, 30));

            lblnombrerh.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
            lblnombrerh.setForeground(new java.awt.Color(51, 102, 255));
            jPanel1.add(lblnombrerh, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 230, 20));

            jSeparator3.setBackground(new java.awt.Color(51, 102, 255));
            jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, 230, 10));

            lblcargo.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
            lblcargo.setForeground(new java.awt.Color(51, 102, 255));
            jPanel1.add(lblcargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 20, 230, 20));

            jSeparator2.setBackground(new java.awt.Color(51, 102, 255));
            jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 40, 230, 10));

            jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
                  public void mousePressed(java.awt.event.MouseEvent evt) {
                        jLabel5MousePressed(evt);
                  }
            });
            jLabel5.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
                  public void mouseDragged(java.awt.event.MouseEvent evt) {
                        jLabel5MouseDragged(evt);
                  }
            });
            jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 910, 50));

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                  layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                  .addGroup(layout.createSequentialGroup()
                        .addGap(198, 198, 198)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                  .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(btnEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74)
                        .addComponent(btnRegistros)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(50, 50, 50))
            );

            layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnEmpleados, btnRegistros, jButton1});

            layout.setVerticalGroup(
                  layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                              .addGroup(layout.createSequentialGroup()
                                    .addComponent(jButton1)
                                    .addContainerGap())
                              .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                          .addComponent(btnRegistros)
                                          .addComponent(btnEmpleados))
                                    .addGap(25, 25, 25))))
            );

            layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnEmpleados, btnRegistros, jButton1});

            pack();
      }// </editor-fold>//GEN-END:initComponents

      private void btnEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmpleadosActionPerformed
            CargarXLS car = new CargarXLS();
            try {
                  car.cargarempleados();
            } catch (Exception e) {
                  JOptionPane.showMessageDialog(null,"Error en: "+ e);
            }
            RH_Inicio rh = new RH_Inicio();
            rh.show(true);
            this.setVisible(false);

      }//GEN-LAST:event_btnEmpleadosActionPerformed

      private void btnRegistrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrosActionPerformed

            CargarXLS car = new CargarXLS();
            try {
                  car.cargarregistros();
            } catch (Exception e) {
                  JOptionPane.showMessageDialog(null,"Error en: "+ e);
            }
            RH_Inicio rh = new RH_Inicio();
            rh.show(true);
            this.setVisible(false);
      }//GEN-LAST:event_btnRegistrosActionPerformed

      private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
            try {
                   RH_Inicio rh = new RH_Inicio();
            rh.show(true);
            this.setVisible(false);
            } catch (Exception e) {
            }
            // TODO add your handling code here:
      }//GEN-LAST:event_jButton1ActionPerformed

      private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

            this.setExtendedState(ICONIFIED);
      }//GEN-LAST:event_jButton2ActionPerformed

      private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
            System.exit(0);        // TODO add your handling code here:
      }//GEN-LAST:event_jButton3ActionPerformed

      private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
            try {
                  Seleccion sele = new Seleccion();
                  sele.setVisible(true);
                  this.setVisible(false);
            } catch (Exception e) {
                  JOptionPane.showMessageDialog(null, e);
            }
                  
      }//GEN-LAST:event_jButton4ActionPerformed

      private void jLabel5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MousePressed
            x = evt.getX();
            y = evt.getY();
      }//GEN-LAST:event_jLabel5MousePressed

      private void jLabel5MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseDragged
            this.setLocation(this.getLocation().x + evt.getX() - x, this.getLocation().y + evt.getY() - y);
      }//GEN-LAST:event_jLabel5MouseDragged

      /**
       * @param args the command line arguments
       */
      public static void main(String args[]) {
            /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
             * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
             */
          
        //</editor-fold>

            /* Create and display the form */
            java.awt.EventQueue.invokeLater(new Runnable() {
                  public void run() {
                        new menuopciones().setVisible(true);
                  }
            });
      }

      // Variables declaration - do not modify//GEN-BEGIN:variables
      private javax.swing.JButton btnEmpleados;
      private javax.swing.JButton btnRegistros;
      private javax.swing.JButton jButton1;
      private javax.swing.JButton jButton2;
      private javax.swing.JButton jButton3;
      private javax.swing.JButton jButton4;
      private javax.swing.JLabel jLabel1;
      private javax.swing.JLabel jLabel2;
      private javax.swing.JLabel jLabel3;
      private javax.swing.JLabel jLabel5;
      private javax.swing.JPanel jPanel1;
      private javax.swing.JSeparator jSeparator2;
      private javax.swing.JSeparator jSeparator3;
      public static javax.swing.JLabel lblcargo;
      public static javax.swing.JLabel lblnombrerh;
      // End of variables declaration//GEN-END:variables
}
