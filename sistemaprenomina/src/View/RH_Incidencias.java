/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import BD.Nomincidencia;
import Conexion.Conexion;
import Controller.Rincidencia;
import static java.lang.Math.E;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class RH_Incidencias extends javax.swing.JFrame {
   public static String nombre="";
   public static String cargo="";

   DefaultTableModel modeloincidencias = new DefaultTableModel(null,getColums());
      public static ResultSet rs;
      private Connection userConn;
      private TableRowSorter trsFiltro;
      private PreparedStatement st;
      Conexion con = new Conexion();
      Connection conn;
      PreparedStatement stmt;
      Nomincidencia nom;
      
    public RH_Incidencias() {
          nom = new Nomincidencia();
        modeloincidencias = new DefaultTableModel(null,getColums());
        SetFilas();
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new java.awt.Color(51, 102, 255));
    }

      private String[] getColums(){
        
        String columna[]={"Clave","Nombre","Estatus"};
           return columna;
        
    }
        
         private void SetFilas(){
            
       
            try {
                 String sql = "select idNomIncidencia, nombre,estatus from nomIncidencia";
                  conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
                  stmt = conn.prepareStatement(sql);
                  rs = stmt.executeQuery();
                  Object datos[] = new Object[3];
                while (rs.next()) {
                    for (int i = 0; i < 3; i++) {
                        datos[0] = rs.getString("idNomIncidencia");
                        datos[1] = rs.getString("nombre");
                        int est = rs.getInt("estatus");
                        if (est== 1){
                        datos[2] = "Activo";
                        }else{
                        datos[2] = "Inactivo";
                        }
                    }
                    modeloincidencias.addRow(datos);
                }
                      
//                        tabla1.addRow(datos);
             } catch (Exception e) {
                System.out.println(""+e);
             }finally{
                 Conexion.close(rs);
                  Conexion.close(stmt);
             }
             
           
         }
   public void limpiar(DefaultTableModel tabla) {
            for (int i = 0; i < tabla.getRowCount(); i++) {
                  tabla.removeRow(i);
                  i -= 1;
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

        asci = new javax.swing.JPopupMenu();
        actinc = new javax.swing.JMenuItem();
        desci = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblincidencias = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNomIncidencia = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        GuardarIn = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();

        actinc.setText("Activar");
        actinc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actincActionPerformed(evt);
            }
        });
        asci.add(actinc);

        desci.setText("Desactivar");
        desci.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                desciActionPerformed(evt);
            }
        });
        asci.add(desci);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 102, 255));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(229, 230, 234));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/minimizar.png"))); // NOI18N
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 0, 32, 30));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/error.png"))); // NOI18N
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 0, 32, 30));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/regresar.png"))); // NOI18N
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 0, 32, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 52));

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Registro Incidencias");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 60, 186, 30));

        tblincidencias= new javax.swing.JTable(){
            public boolean  isCellEditable(int rowIndex,int conlIndex ){
                return false;
            }
        };
        tblincidencias.setAutoCreateRowSorter(true);
        tblincidencias.setForeground(new java.awt.Color(51, 51, 51));
        tblincidencias.setModel(modeloincidencias);
        tblincidencias.setComponentPopupMenu(asci);
        tblincidencias.setDropMode(javax.swing.DropMode.INSERT_ROWS);
        tblincidencias.setFillsViewportHeight(true);
        tblincidencias.setGridColor(new java.awt.Color(255, 255, 255));
        tblincidencias.setInheritsPopupMenu(true);
        tblincidencias.setIntercellSpacing(new java.awt.Dimension(2, 2));
        tblincidencias.setSelectionBackground(new java.awt.Color(108, 180, 221));
        tblincidencias.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jScrollPane3.setViewportView(tblincidencias);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 650, 230));

        jPanel2.setBackground(new java.awt.Color(51, 102, 255));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Nombre Incidencia");

        txtNomIncidencia.setBackground(new java.awt.Color(51, 102, 255));
        txtNomIncidencia.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNomIncidencia.setForeground(new java.awt.Color(255, 255, 255));
        txtNomIncidencia.setBorder(null);
        txtNomIncidencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNomIncidenciaKeyReleased(evt);
            }
        });

        jSeparator3.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator3.setAlignmentX(0.7F);
        jSeparator3.setAlignmentY(0.8F);

        GuardarIn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/save1.png"))); // NOI18N
        GuardarIn.setContentAreaFilled(false);
        GuardarIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarInActionPerformed(evt);
            }
        });

        txtBuscar.setBackground(new java.awt.Color(51, 102, 255));
        txtBuscar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtBuscar.setForeground(new java.awt.Color(255, 255, 255));
        txtBuscar.setBorder(null);
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator1.setAlignmentX(0.7F);
        jSeparator1.setAlignmentY(0.8F);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/search1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(135, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNomIncidencia, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addComponent(GuardarIn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(132, 132, 132))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtNomIncidencia, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(GuardarIn)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 650, 180));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
System.exit(0);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
  this.setExtendedState(ICONIFIED);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
cargo= RH_Inicio.depto;
nombre= RH_Inicio.nombre;
        RH_Inicio rh_i=new RH_Inicio();
        rh_i.setVisible(true);
        RH_Inicio.lblcargo.setText(cargo);
        RH_Inicio.lblnombrerh.setText(nombre);
        this.setVisible(false);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txtNomIncidenciaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomIncidenciaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomIncidenciaKeyReleased

    private void GuardarInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarInActionPerformed
        String NomIncidencia= txtNomIncidencia.getText();

        if (NomIncidencia.equals("")) {
            JOptionPane.showMessageDialog(null,"El campo esta vacio","",JOptionPane.WARNING_MESSAGE);
        }else{
            try {

                nom.insert(NomIncidencia);
                txtNomIncidencia.setText("");
                limpiar(modeloincidencias);
                SetFilas();
            } catch (SQLException ex) {
                Logger.getLogger(RH_Incidencias.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }//GEN-LAST:event_GuardarInActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
      limpiar(modeloincidencias);
        String cadenaBusqueda = txtBuscar.getText();
      
      ArrayList<Rincidencia> listarIncidencias = nom.obteneriIncidenciaPorCriterio(cadenaBusqueda);
        
        int numeroProductos = listarIncidencias.size();
        
        modeloincidencias.setNumRows(numeroProductos);
        
        for(int i = 0; i < numeroProductos; i++){
            Rincidencia inc = listarIncidencias.get(i);
            int clave = inc.getIdNomIncidencia();
            String nombre = inc.getIncidencia();
            int estatus = inc.getEstatus();
           String estatu;
            if (estatus== 1) {
               estatu = "Activo";
            }else{
               estatu = "Inactivo";

            }
            
            modeloincidencias.setValueAt(clave, i, 0);
            modeloincidencias.setValueAt(nombre, i, 1);
            modeloincidencias.setValueAt(estatu, i, 2);
         
        }
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void actincActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actincActionPerformed
         int fila = tblincidencias.getSelectedRow();
        int estatus=0;
            if (fila >= 0) {
                  String cod = tblincidencias.getValueAt(fila, 0).toString();
                  String status = tblincidencias.getValueAt(fila,2).toString();
             
                 
                 
            try {
                  nom.Activar(cod);
                  limpiar(modeloincidencias);
                  SetFilas();
                  JOptionPane.showMessageDialog(null, "Incidencia Desactivada");
            } catch (SQLException ex) {
                Logger.getLogger(RH_Incidencias.class.getName()).log(Level.SEVERE, null, ex);
            }
            } else {
                  JOptionPane.showMessageDialog(null, "Selecione una fila ");
            }
    }//GEN-LAST:event_actincActionPerformed

    private void desciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_desciActionPerformed
        int fila = tblincidencias.getSelectedRow();
        int estatus=0;
            if (fila >= 0) {
                  String cod = tblincidencias.getValueAt(fila, 0).toString();
                  String status = tblincidencias.getValueAt(fila,2).toString();
            try {
                nom.Desactivar(cod);
                limpiar(modeloincidencias);
                SetFilas();
                JOptionPane.showMessageDialog(null, "Incidencia Desactivada");

            } catch (SQLException ex) {
                Logger.getLogger(RH_Incidencias.class.getName()).log(Level.SEVERE, null, ex);
            }
            } else {
                  JOptionPane.showMessageDialog(null, "Selecione una fila ");
            }
    }//GEN-LAST:event_desciActionPerformed

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
            java.util.logging.Logger.getLogger(RH_Incidencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RH_Incidencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RH_Incidencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RH_Incidencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RH_Incidencias().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton GuardarIn;
    private javax.swing.JMenuItem actinc;
    private javax.swing.JPopupMenu asci;
    private javax.swing.JMenuItem desci;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable tblincidencias;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtNomIncidencia;
    // End of variables declaration//GEN-END:variables
}
