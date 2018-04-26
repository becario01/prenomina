/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import BD.Rjefes;
import BD.Nomincidencia;
import BD.RegistrarIncidencia;
import Conexion.Conexion;
import Controller.EJefes;
import Controller.Rincidencia;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class select_incidencia extends javax.swing.JFrame{
      Nomincidencia rin;
      DefaultComboBoxModel <Rincidencia> modeloselincidencia;
      Rjefes rjf;
      String nom;
      String codi;
      public static ResultSet rs;
      private Connection userConn;
      private PreparedStatement st;
      Conexion con = new Conexion();
      Connection conn;
      PreparedStatement stmt;
      int x, y;
   
    public select_incidencia() {
        modeloselincidencia = new DefaultComboBoxModel<Rincidencia>();
        rin = new Nomincidencia();
        rjf = new Rjefes();
        cargarModeloSem();
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new java.awt.Color(233, 236, 241));
        cantidadhoras.hide();
        lblcant.hide();
    }
public void mostrardatosse(String nombre,String cod){
          codi = cod;
         nom = nombre;
    }

private void cargarModeloSem(){
            ArrayList<Rincidencia> listaSemanas;
        listaSemanas = rin.obtenerIncnidecnias();
  modeloselincidencia.addElement(new Rincidencia(0, "Selecciona opcion", 1));
        for(Rincidencia semana : listaSemanas){
            modeloselincidencia.addElement(semana);
        }
    }

  public static String obtenerDiaSemana(String fechaP) throws ParseException{
      String[] dias={"D","L","Ma","Mi","J","V","S"};
      String aux ="";
      fechaP = fechaP.replaceAll(" ", "");
       SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
             if (fechaP.equalsIgnoreCase("")) {
                 
             }else{
        String dateInString = fechaP;
         String[] dates = dateInString.split("-");
         String año = dates[0];
         String mes = dates[1];  
         String dia = dates[2];
         String fecha = dia+"-"+mes+"-"+año;
        fecha = fecha.replaceAll(" ", "");
        
          java.util.Date date = formatter.parse(fecha);
      int numeroDia=0;
      Calendar cal= Calendar.getInstance();
      cal.setTime(date);
      numeroDia=cal.get(Calendar.DAY_OF_WEEK);
    
       aux = dias[numeroDia - 1];
             }
      return aux;
    }//metodo obtenerDia
  
public void verfechas(int codigoemp ,String dia,String fecha,String horaextra,String comentario,int idNominci,String horastrab){
          Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    EJefes semana = (EJefes) JA_inicio.cmbSemana.getSelectedItem();
    int id = semana.getIdSemana();
    try {
        String sql = "select  * from incidencias where  empleadoId='" + codigoemp + "' and fecha='" + fecha + "' and idSemana='" + id + "'  and dia='" + dia + "'";
        conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
        stmt = conn.prepareStatement(sql);
        rs = stmt.executeQuery();

        if (!rs.next()) {
            RegistrarIncidencia regin = new RegistrarIncidencia();
            regin.insert(codigoemp, dia, fecha, horaextra, comentario, id, idNominci, horastrab);
        } else {

            JOptionPane.showMessageDialog(rootPane, "Está persona cuenta con incidencia en este dia!!", "Warning",
                    JOptionPane.WARNING_MESSAGE);
        }

    } catch (Exception e) {
        System.out.println("" + e);
    } finally {
        Conexion.close(rs);
        Conexion.close(stmt);
    }


}

    
      public static boolean isNumeric(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
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
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        cmbincidencia = new javax.swing.JComboBox();
        btnincidenciaL = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtcomentario = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        lblsem = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cantidadhoras = new javax.swing.JTextField();
        lblcant = new javax.swing.JLabel();

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
        setPreferredSize(new java.awt.Dimension(552, 444));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(138, 229, 239));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/minimizar.png"))); // NOI18N
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 0, 32, 30));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/error.png"))); // NOI18N
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 0, 32, 30));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/regresar.png"))); // NOI18N
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 0, 32, 30));

        jLabel3.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabel3MouseDragged(evt);
            }
        });
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel3MousePressed(evt);
            }
        });
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 550, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 52));

        cmbincidencia.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cmbincidencia.setModel(modeloselincidencia);
        cmbincidencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbincidenciaActionPerformed(evt);
            }
        });
        getContentPane().add(cmbincidencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 190, 280, 30));

        btnincidenciaL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/save1.png"))); // NOI18N
        btnincidenciaL.setBorder(null);
        btnincidenciaL.setBorderPainted(false);
        btnincidenciaL.setContentAreaFilled(false);
        btnincidenciaL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnincidenciaLActionPerformed(evt);
            }
        });
        getContentPane().add(btnincidenciaL, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 190, 40, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setText("REGISTRO DE INCIDENCIAS");
        jLabel13.setToolTipText("");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, -1, -1));

        txtcomentario.setColumns(20);
        txtcomentario.setFont(new java.awt.Font("Arial", 2, 14)); // NOI18N
        txtcomentario.setRows(5);
        jScrollPane3.setViewportView(txtcomentario);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 320, 361, -1));

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel2.setText("Fecha :");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 117, 77, -1));

        lblFecha.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        getContentPane().add(lblFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 118, 101, 18));

        lblsem.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        getContentPane().add(lblsem, new org.netbeans.lib.awtextra.AbsoluteConstraints(439, 110, 92, 26));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel1.setText("Comentario:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 101, 40));

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel4.setText("Selecione incidencia:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 165, 30));
        getContentPane().add(cantidadhoras, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 230, 50, 30));

        lblcant.setText("Cantidad de horas");
        getContentPane().add(lblcant, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 240, 130, 20));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
System.exit(0);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
  this.setExtendedState(ICONIFIED);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
   this.setVisible(false);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void cmbincidenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbincidenciaActionPerformed
     Rincidencia incidencia = (Rincidencia) cmbincidencia.getSelectedItem();
        if (incidencia.getIncidencia().equalsIgnoreCase("Horas extras")) {
            cantidadhoras.show();
            lblcant.show();

        } else {
            cantidadhoras.hide();
            lblcant.hide();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_cmbincidenciaActionPerformed

    private void btnincidenciaLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnincidenciaLActionPerformed
  if (cmbincidencia.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Seleciona Incidencia por favor!");

        } else {
            Rincidencia incidencia = (Rincidencia) cmbincidencia.getSelectedItem();
            int codigoemp = Integer.parseInt(codi);
            String fecha = lblFecha.getText();
            String comentario = txtcomentario.getText();
            String cantidad = cantidadhoras.getText();

            if (isNumeric(cantidad)) {
                cantidad = cantidadhoras.getText();
                System.out.println(cantidad);
            } else if (cantidad.equalsIgnoreCase("")) {
                cantidad = " ";
                System.out.println(cantidad);
            } else {
                JOptionPane.showMessageDialog(rootPane, "Debe ingresar un numero");
            }
            String dia = "";
            try {
                dia = obtenerDiaSemana(fecha);

            } catch (ParseException ex) {
                Logger.getLogger(select_incidencia.class.getName()).log(Level.SEVERE, null, ex);
            }
            String horastrab="";

            verfechas(codigoemp, dia, fecha, cantidad, comentario, incidencia.getIdNomIncidencia(), horastrab);
        }
    }//GEN-LAST:event_btnincidenciaLActionPerformed

    private void jLabel3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MousePressed
          x = evt.getX();
         y = evt.getY();
    }//GEN-LAST:event_jLabel3MousePressed

    private void jLabel3MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseDragged
       this.setLocation(this.getLocation().x+evt.getX()-x, this.getLocation().y+evt.getY()-y);
    }//GEN-LAST:event_jLabel3MouseDragged

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
            java.util.logging.Logger.getLogger(select_incidencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(select_incidencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(select_incidencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(select_incidencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new select_incidencia().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnincidenciaL;
    private javax.swing.JTextField cantidadhoras;
    private javax.swing.JComboBox cmbincidencia;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    public static javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblcant;
    public static javax.swing.JLabel lblsem;
    private javax.swing.JTextArea txtcomentario;
    // End of variables declaration//GEN-END:variables
}
