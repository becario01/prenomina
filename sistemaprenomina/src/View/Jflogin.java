/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Conexion.Conexion;
import java.awt.Color;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

/**
 *
 * @author Vertsequer
 */
public class Jflogin extends javax.swing.JFrame {

    public static ResultSet rs;
    private Connection userConn;
    private PreparedStatement st;
    Conexion con = new Conexion();
    Connection conn;
    PreparedStatement stmt;
    int x, y;

    public Jflogin() {
        
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        SimpleDateFormat formateador = new SimpleDateFormat(
                "dd 'de' MMMM 'de' yyyy", new Locale("es_ES"));
        Date fechaDate = new Date();
        String fecha = formateador.format(fechaDate);
        loginfecha.setText(fecha);
        panelloginm.setBackground(new Color(51, 102, 255, 200));
        loginfecha.setHorizontalAlignment(loginfecha.CENTER);
        loginfecha.setVerticalAlignment(loginfecha.CENTER);
        boolean datoscon = consultar();
        if (datoscon == true) {
            btnconfig.setVisible(true);
        } else {
            btnconfig.setVisible(false);
        }
    }
  public boolean consultar() {
        boolean datos = false;
        try {
            String sql = "SELECT * FROM DatosIniciales";
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            if (!rs.next()) {
                System.out.println("vacio");
                btnconfig.setToolTipText("Configuracion de rangos de Fechas");
                 btnconfig.setBorder(new LineBorder(Color.BLACK));
                datos = true;
            } else {
                datos = false;
                System.out.println("llleno");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatosIniciales.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datos;

    }
  public void conf(){
         EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
              

                ImageIcon icon = new ImageIcon(Jflogin.class.getResource("/View/img/alternativas.png"));
          
                JOptionPane.showMessageDialog(
                        null,
                        new JLabel("Este icono configura Rangos de fechas, Es necesario!!",icon, JLabel.RIGHT),
                        "Hello", JOptionPane.INFORMATION_MESSAGE);

            }
        });
 }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelloginm = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        loginfecha = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtPass = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        btnCancel = new javax.swing.JButton();
        btnOk = new javax.swing.JButton();
        btnconfig = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelloginm.setBackground(new java.awt.Color(56, 116, 214));
        panelloginm.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator3.setForeground(new java.awt.Color(255, 255, 255));
        panelloginm.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, 210, 20));

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Bienvenido");
        panelloginm.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 100, 30));

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));
        panelloginm.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, 160, 20));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/logoZunfeld.png"))); // NOI18N
        panelloginm.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, 160, 90));

        loginfecha.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        loginfecha.setForeground(new java.awt.Color(255, 255, 255));
        panelloginm.add(loginfecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 210, 180, 20));

        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
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
        panelloginm.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 590, 80));

        getContentPane().add(panelloginm, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 290, 350));

        jPanel2.setBackground(new java.awt.Color(229, 230, 234));
        jPanel2.setInheritsPopupMenu(true);
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel3.setText("Usuario:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, 120, 30));

        txtUsuario.setBackground(new java.awt.Color(229, 230, 234));
        txtUsuario.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtUsuario.setText("Ingresar Usuario");
        txtUsuario.setToolTipText("");
        txtUsuario.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(51, 102, 255)));
        txtUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtUsuarioMouseClicked(evt);
            }
        });
        jPanel2.add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 200, 20));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel1.setText("Contraseña:");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, 120, 30));

        txtPass.setBackground(new java.awt.Color(229, 230, 234));
        txtPass.setText("jPasswordField1");
        txtPass.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(51, 102, 255)));
        txtPass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtPassMouseClicked(evt);
            }
        });
        jPanel2.add(txtPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, 200, 20));

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel4.setText("Iniciar Sesión");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 120, -1));

        btnCancel.setBackground(new java.awt.Color(255, 0, 51));
        btnCancel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnCancel.setForeground(new java.awt.Color(255, 255, 255));
        btnCancel.setText("Cancelar");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        jPanel2.add(btnCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 250, 110, 40));

        btnOk.setBackground(new java.awt.Color(51, 102, 255));
        btnOk.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnOk.setForeground(new java.awt.Color(255, 255, 255));
        btnOk.setText("Acceder");
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });
        jPanel2.add(btnOk, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, 110, 40));

        btnconfig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/alternativas.png"))); // NOI18N
        btnconfig.setBorderPainted(false);
        btnconfig.setContentAreaFilled(false);
        btnconfig.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/alternativasO.png"))); // NOI18N
        btnconfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnconfigActionPerformed(evt);
            }
        });
        jPanel2.add(btnconfig, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 30, 40));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 0, 300, 350));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/fondologin.png"))); // NOI18N
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 290, 350));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtUsuarioMouseClicked
        txtUsuario.setText(""); // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioMouseClicked

    private void txtPassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPassMouseClicked
        txtPass.setText("");   // TODO add your handling code here:
    }//GEN-LAST:event_txtPassMouseClicked

    private void jLabel5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_jLabel5MousePressed

    private void jLabel5MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseDragged
        this.setLocation(this.getLocation().x + evt.getX() - x, this.getLocation().y + evt.getY() - y);        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel5MouseDragged

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        cerrar();        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
      String Usuario = txtUsuario.getText();
        String Pass = txtPass.getText();
        if (Usuario.equals("")) {
            JOptionPane.showMessageDialog(null, "Verifique sus datos .\n Usuario Vacio", "Usuario Vacio", JOptionPane.WARNING_MESSAGE);
        } else if (Pass.equals("")) {
            JOptionPane.showMessageDialog(null, "Verifique sus datos.\n Contraseña Vacia", "Contraseña Vacia", JOptionPane.WARNING_MESSAGE);
        } else {
            BD.login lo = new BD.login();
            boolean confv = consultar();
            if (confv == true) {
               conf();
                this.setVisible(true);
            } else {
                lo.validar_ingreso(txtUsuario.getText(), txtPass.getText());
                this.setVisible(false);
            }

        }
    }//GEN-LAST:event_btnOkActionPerformed

    private void btnconfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnconfigActionPerformed
    DatosIniciales dto = new DatosIniciales();
        dto.show();
        this.hide();
    }//GEN-LAST:event_btnconfigActionPerformed
    public void cerrar() {
        Object[] opciones = {"Aceptar", "Cancelar"};
        int eleccion = JOptionPane.showOptionDialog(rootPane, "En realidad desea realizar cerrar la aplicacion", "Mensaje de Confirmacion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, opciones, "Aceptar");
        if (eleccion == JOptionPane.YES_OPTION) {
            System.exit(0);
        } else {
        }
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
            java.util.logging.Logger.getLogger(Jflogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Jflogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Jflogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Jflogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Jflogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnOk;
    public static javax.swing.JButton btnconfig;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel loginfecha;
    private javax.swing.JPanel panelloginm;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
