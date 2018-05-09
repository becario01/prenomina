/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Conexion.Conexion;
import Controller.Eusuarios;
import Controller.Render;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Becarios
 */
public class Asignacion extends javax.swing.JFrame {
    public static ResultSet rs;
    private Connection userConn;
    private TableRowSorter trsFiltro;
    private PreparedStatement st;
    DefaultTableModel modelousuarios = new DefaultTableModel(null, getColumas());
    Conexion con = new Conexion();
    Connection conn;
    PreparedStatement stmt;
    int x,y;
    public Asignacion() {
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new java.awt.Color(51, 102, 255));
        SetFilas();
        tblasig.setRowHeight(25);
    }

      private String[] getColumas(){
        String columna[]={"Tipo Usuario ","Nombre","Empleado ID","Asignar"};
        return columna;
    }
      
         private void SetFilas(){
                   tblasig.setDefaultRenderer(Object.class, new Render());
        JButton btn1 = new JButton("Asignar");
        btn1.setName("Asignar");
            try {
                 String sql = "select * from usuario";
                  conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
                  stmt = conn.prepareStatement(sql);
                  rs = stmt.executeQuery();
                  Object datos[] = new Object[4];
                while (rs.next()) {
                    for (int i = 0; i < 4; i++) {
                        datos[0] = rs.getString("idUser");
                        datos[1] = rs.getString("nombre");
                        datos[2] = rs.getString("depto");
                       datos[3] = btn1;   
                    }
                    modelousuarios.addRow(datos);
                }
             } catch (Exception e) {
                System.out.println(""+e);
             }finally{
                 Conexion.close(rs);
                  Conexion.close(stmt);
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
        tblasig = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btnminimizar = new javax.swing.JButton();
        btncerrar = new javax.swing.JButton();
        btnregresar = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel13 = new javax.swing.JLabel();
        lblnombrejefe = new javax.swing.JTextField();
        lblcargojefe = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblasig.setModel(modelousuarios);
        tblasig.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblasigMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblasig);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 675, 220));

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator1.setAlignmentX(0.8F);
        jSeparator1.setAlignmentY(0.8F);
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 675, 10));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Listado de Usuarios");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 210, 20));

        jPanel2.setBackground(new java.awt.Color(229, 230, 234));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/portafolio.png"))); // NOI18N
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, -1, 40));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/user.png"))); // NOI18N
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, -1, 40));

        btnminimizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/minimizar.png"))); // NOI18N
        btnminimizar.setBorderPainted(false);
        btnminimizar.setContentAreaFilled(false);
        btnminimizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnminimizarActionPerformed(evt);
            }
        });
        jPanel2.add(btnminimizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 0, 32, 30));

        btncerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/error.png"))); // NOI18N
        btncerrar.setBorderPainted(false);
        btncerrar.setContentAreaFilled(false);
        btncerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncerrarActionPerformed(evt);
            }
        });
        jPanel2.add(btncerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 0, 32, 30));

        btnregresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/regresar.png"))); // NOI18N
        btnregresar.setBorderPainted(false);
        btnregresar.setContentAreaFilled(false);
        btnregresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnregresarActionPerformed(evt);
            }
        });
        jPanel2.add(btnregresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 0, 32, 30));

        jSeparator4.setBackground(new java.awt.Color(51, 102, 255));
        jPanel2.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 40, 230, 10));

        jSeparator5.setBackground(new java.awt.Color(51, 102, 255));
        jPanel2.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, 230, 10));

        jLabel13.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabel13MouseDragged(evt);
            }
        });
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel13MousePressed(evt);
            }
        });
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1160, 10));

        lblnombrejefe.setEditable(false);
        lblnombrejefe.setBackground(new java.awt.Color(229, 230, 234));
        lblnombrejefe.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblnombrejefe.setForeground(new java.awt.Color(51, 102, 255));
        lblnombrejefe.setAutoscrolls(false);
        lblnombrejefe.setBorder(null);
        lblnombrejefe.setCaretColor(new java.awt.Color(51, 102, 255));
        jPanel2.add(lblnombrejefe, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 230, 20));

        lblcargojefe.setBackground(new java.awt.Color(229, 230, 234));
        lblcargojefe.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblcargojefe.setForeground(new java.awt.Color(51, 102, 255));
        lblcargojefe.setBorder(null);
        lblcargojefe.setCaretColor(new java.awt.Color(51, 102, 255));
        jPanel2.add(lblcargojefe, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 20, 230, 20));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 790, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblasigMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblasigMouseClicked
      int column = tblasig.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY()/tblasig.getRowHeight();
        
        if(row < tblasig.getRowCount() && row >= 0 && column < tblasig.getColumnCount() && column >= 0){
            Object value = tblasig.getValueAt(row, column);
            if(value instanceof JButton){
                ((JButton)value).doClick();
                JButton boton = (JButton) value;  
                if(boton.getName().equals("Asignar")){
                String nombreusr = tblasig.getValueAt(row, 1).toString();
                String nombre = tblasig.getValueAt(row, 2).toString();
                String iduser = tblasig.getValueAt(row, 0).toString();               
                asignar asg = new asignar();
                asignar.txtnomasg.setText(nombreusr);
                asignar.idusers.setText(iduser);
                asignar.lblcargo.setText(lblcargojefe.getText());
                asignar.lblnombrerh.setText(lblnombrejefe.getText());
                asg.SetFilas(iduser);
                asg.setVisible(true);
                }
            }
          if(value instanceof JCheckBox){
                JCheckBox ch = (JCheckBox)value;
                if(ch.isSelected()==true){
                    ch.setSelected(false);
                }
                if(ch.isSelected()==false){
                    ch.setSelected(true);
                }
                
            }
        }
        
    }//GEN-LAST:event_tblasigMouseClicked

    private void btnminimizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnminimizarActionPerformed
        this.setExtendedState(ICONIFIED);        // TODO add your handling code here:
    }//GEN-LAST:event_btnminimizarActionPerformed

    private void btncerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncerrarActionPerformed
        System.exit(0);        // TODO add your handling code here:
    }//GEN-LAST:event_btncerrarActionPerformed

    private void btnregresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnregresarActionPerformed
        RH_registrarusrs ini = new RH_registrarusrs();
        ini.setVisible(true);
        RH_registrarusrs.lblcargojefe.setText(lblcargojefe.getText());
        RH_registrarusrs.lblnombrejefe.setText(lblnombrejefe.getText());
        this.hide();
    }//GEN-LAST:event_btnregresarActionPerformed

    private void jLabel13MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseDragged
        this.setLocation(this.getLocation().x+evt.getX()-x, this.getLocation().y+evt.getY()-y);
    }//GEN-LAST:event_jLabel13MouseDragged

    private void jLabel13MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_jLabel13MousePressed

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
            java.util.logging.Logger.getLogger(Asignacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Asignacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Asignacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Asignacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Asignacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btncerrar;
    public static javax.swing.JButton btnminimizar;
    public static javax.swing.JButton btnregresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    public static javax.swing.JTextField lblcargojefe;
    public static javax.swing.JTextField lblnombrejefe;
    private javax.swing.JTable tblasig;
    // End of variables declaration//GEN-END:variables

  
}
