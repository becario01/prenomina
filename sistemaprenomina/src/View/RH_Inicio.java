/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import javax.swing.JOptionPane;

/**
 *
 * @author Vertsequer
 */
public class RH_Inicio extends javax.swing.JFrame implements Runnable {

    String hora, minutos, segundos;
    Thread hilo;
    int x, y;
    public static String nombre;
    public static String depto;

    /**
     * Creates new form menu
     */
    public RH_Inicio() {
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new java.awt.Color(51, 102, 255));
        fecha();
        hilo = new Thread(this);
        hilo.start();
        setVisible(true);
    }

    public void fecha() {
        Date fechaHoy = new Date();
        SimpleDateFormat formatoEsMX = new SimpleDateFormat(
                "d/MMMM/yyyy", new Locale("ES", "MX"));
        String[] parts = formatoEsMX.format(fechaHoy).split("/");
        String dia = parts[0];
        String mes = parts[1];
        String anio = parts[2];
        lbldia.setText(dia);
        lblmes.setText(mes);
        lblaño.setText(anio);
    }

    public void hora() {
        Calendar calendario = new GregorianCalendar();
        Date horaactual = new Date();
        calendario.setTime(horaactual);
        hora = calendario.get(Calendar.HOUR_OF_DAY) > 9 ? "" + calendario.get(Calendar.HOUR_OF_DAY) : "0" + calendario.get(Calendar.HOUR_OF_DAY);
        minutos = calendario.get(Calendar.MINUTE) > 9 ? "" + calendario.get(Calendar.MINUTE) : "0" + calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND) > 9 ? "" + calendario.get(Calendar.SECOND) : "0" + calendario.get(Calendar.SECOND);
    }

    public void run() {
        //while
        Thread current = Thread.currentThread();
        while (current == hilo) {
            hora();
            lblhoradiaria.setText(hora + ":" + minutos + ":" + segundos);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        lblnombrerh = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        lblcargo = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        btnIncidencias = new javax.swing.JButton();
        lbldia = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblmes = new javax.swing.JLabel();
        lblaño = new javax.swing.JLabel();
        lblhoradiaria = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 102, 255));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1100, 500));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(229, 230, 234));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/prenomina/portafolio.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, -1, 40));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/prenomina/user.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, -1, 40));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/prenomina/minimizar.png"))); // NOI18N
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 0, 32, 30));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/prenomina/error.png"))); // NOI18N
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 0, 32, 30));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/prenomina/regresar.png"))); // NOI18N
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 0, 32, 30));

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

        jLabel5.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabel5MouseDragged(evt);
            }
        });
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel5MousePressed(evt);
            }
        });
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1110, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 52));

        jPanel2.setBackground(new java.awt.Color(51, 102, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton10.setBackground(new java.awt.Color(192, 179, 179));
        jButton10.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jButton10.setForeground(new java.awt.Color(51, 102, 255));
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/form.png"))); // NOI18N
        jButton10.setText("Percepciones Deducciones");
        jButton10.setBorderPainted(false);
        jPanel2.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 90, 250, 100));

        jButton11.setBackground(new java.awt.Color(242, 182, 146));
        jButton11.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jButton11.setForeground(new java.awt.Color(51, 102, 255));
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/team.png"))); // NOI18N
        jButton11.setText("Usuarios sin Incidencias ");
        jButton11.setBorder(null);
        jButton11.setBorderPainted(false);
        jPanel2.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 250, 100));

        jButton13.setBackground(new java.awt.Color(243, 162, 194));
        jButton13.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jButton13.setForeground(new java.awt.Color(51, 102, 255));
        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/calendario.png"))); // NOI18N
        jButton13.setText("  Semanas                 ");
        jButton13.setBorderPainted(false);
        jPanel2.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 180, 250, 100));

        jButton17.setBackground(new java.awt.Color(177, 224, 234));
        jButton17.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jButton17.setForeground(new java.awt.Color(51, 102, 255));
        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/problem.png"))); // NOI18N
        jButton17.setText("Usuario con Incidencias");
        jButton17.setBorderPainted(false);
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton17, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 250, 100));

        jButton16.setBackground(new java.awt.Color(183, 212, 62));
        jButton16.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jButton16.setForeground(new java.awt.Color(51, 102, 255));
        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/lecture.png"))); // NOI18N
        jButton16.setText("Listado Personal               ");
        jButton16.setBorder(null);
        jButton16.setBorderPainted(false);
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 100));

        jButton14.setBackground(new java.awt.Color(97, 209, 182));
        jButton14.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jButton14.setForeground(new java.awt.Color(51, 102, 255));
        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/usuarios.png"))); // NOI18N
        jButton14.setText("Usuarios                      ");
        jButton14.setBorderPainted(false);
        jPanel2.add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 250, 100));

        btnIncidencias.setBackground(new java.awt.Color(247, 203, 111));
        btnIncidencias.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnIncidencias.setForeground(new java.awt.Color(51, 102, 255));
        btnIncidencias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/test.png"))); // NOI18N
        btnIncidencias.setText("      Incidencias");
        btnIncidencias.setBorder(null);
        btnIncidencias.setBorderPainted(false);
        btnIncidencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncidenciasActionPerformed(evt);
            }
        });
        jPanel2.add(btnIncidencias, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 270, 250, 100));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 530, 430));

        lbldia.setBackground(new java.awt.Color(255, 255, 255));
        lbldia.setFont(new java.awt.Font("Century Gothic", 1, 60)); // NOI18N
        lbldia.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(lbldia, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 170, 100, 70));

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("de");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 210, 30, 30));

        lblmes.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lblmes.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(lblmes, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 230, 80, 30));

        lblaño.setFont(new java.awt.Font("Century Gothic", 1, 60)); // NOI18N
        lblaño.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(lblaño, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 270, 190, 60));

        lblhoradiaria.setFont(new java.awt.Font("Century Gothic", 0, 48)); // NOI18N
        lblhoradiaria.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(lblhoradiaria, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 360, 280, 50));

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 60)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(229, 230, 234));
        jLabel3.setText("Bienvenido");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 80, -1, 50));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        System.exit(0);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        this.setExtendedState(ICONIFIED);


    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       String dep = lblcargo.getText();
          String nom = lblnombrerh.getText();
        menuopciones us = new menuopciones();
        us.show(true);
        menuopciones.lblcargo.setText(dep);
        menuopciones.lblnombrerh.setText(nom);
        this.show(false);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnIncidenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncidenciasActionPerformed
        RH_Incidencias inc = new RH_Incidencias();
        inc.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnIncidenciasActionPerformed

    private void jLabel5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_jLabel5MousePressed

    private void jLabel5MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseDragged
        this.setLocation(this.getLocation().x + evt.getX() - x, this.getLocation().y + evt.getY() - y);
    }//GEN-LAST:event_jLabel5MouseDragged

      private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        String dep= lblcargo.getText();
        String nom= lblnombrerh.getText();
        
        
          try {
              RH_ListadoPersonal lis = new RH_ListadoPersonal();
              lis.show();
              RH_ListadoPersonal.lblcargo.setText(dep);
              RH_ListadoPersonal.lblnombrerh.setText(nom);
              this.show(false);
          } catch (Exception e) {
              JOptionPane.showMessageDialog(null, e);
          }

      }//GEN-LAST:event_jButton16ActionPerformed

      private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
          String dep = lblcargo.getText();
          String nom = lblnombrerh.getText();
          try {
              RH_UsuariosConIncidencias usu = new RH_UsuariosConIncidencias();
              usu.show();
               RH_UsuariosConIncidencias.lblcargo.setText(dep);
              RH_UsuariosConIncidencias.lblnombrerh.setText(nom);
              this.show(false);
          } catch (Exception e) {
              JOptionPane.showMessageDialog(null, e);
          }

      }//GEN-LAST:event_jButton17ActionPerformed

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
            java.util.logging.Logger.getLogger(RH_Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RH_Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RH_Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RH_Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RH_Inicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIncidencias;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel lblaño;
    public static javax.swing.JLabel lblcargo;
    private javax.swing.JLabel lbldia;
    private javax.swing.JLabel lblhoradiaria;
    private javax.swing.JLabel lblmes;
    public static javax.swing.JLabel lblnombrerh;
    // End of variables declaration//GEN-END:variables
}
