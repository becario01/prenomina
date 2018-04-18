/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Conexion.Conexion1;
import Controller.autorizacionRH;
import static View.RH_UsuariosConIncidencias.rs;
import static View.RH_UsuariosConIncidencias.tbIncidencias;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Programacion 2
 */
public class RH_uci_detalles extends javax.swing.JFrame {

      Connection conn;
      PreparedStatement stmt;
      public static ResultSet rs;
      private Connection userConn;

      /**
       * Creates new form RH_uci_detalles
       */
      public RH_uci_detalles() throws SQLException {
            initComponents();
            this.setResizable(false);
            this.setLocationRelativeTo(null);
            this.getContentPane().setBackground(new java.awt.Color(8, 50, 119));
            cargartitulos();
            int sem = RH_UsuariosConIncidencias.cmbSemana.getSelectedIndex();
            int idemp = Integer.parseInt(RH_UsuariosConIncidencias.codid);
            limpiar(tabla1);
            cargardatosFiltroSemana(sem, idemp);
            

      }
      DefaultTableModel tabla1 = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int Fila, int Colum) {
                  return false;
            }
      };

      public void cargartitulos() throws SQLException {

            tabla1.addColumn("Dia");
            tabla1.addColumn("ACTUALOZADO JA");
            tabla1.addColumn("ACTUALIZADO RH");
            tabla1.addColumn("FECHA");
            tabla1.addColumn("ENTRADA");
            tabla1.addColumn("SALIDA");
            tabla1.addColumn("HORAS");
            tabla1.addColumn("INCIDENCIA");
            tabla1.addColumn("COMENTARIO");
            this.tbdetalles.setModel(tabla1);
//            tabla1.addColumn(0, "");

            TableColumnModel columnModel = tbdetalles.getColumnModel();

            columnModel.getColumn(0).setPreferredWidth(70);
            columnModel.getColumn(1).setPreferredWidth(75);
            columnModel.getColumn(2).setPreferredWidth(75);
            columnModel.getColumn(3).setPreferredWidth(50);
            columnModel.getColumn(4).setPreferredWidth(30);
            columnModel.getColumn(5).setPreferredWidth(30);
            columnModel.getColumn(6).setPreferredWidth(30);
            columnModel.getColumn(7).setPreferredWidth(100);
            columnModel.getColumn(8).setPreferredWidth(100);

      }

      public void cargardatosFiltroSemana(int idSemana, int cod) throws SQLException {

            String sql = "SELECT inc.actualizadoJA, inc.actualizadoRH, emp.empleadoId, emp.nombre, inc.fecha, re.entrada, re.salida, inc.horasTrab, nomin.nombre AS nombreinc, inc.comentario \n"
                    + "from incidencias inc\n"
                    + "INNER JOIN empleados emp on inc.empleadoId= emp.empleadoId\n"
                    + "INNER JOIN registros re on inc.empleadoId=re.empleadoId and inc.fecha=re.fecha\n"
                    + "INNER JOIN NomIncidencia nomin on  nomin.idNomIncidencia = inc.idNomIncidencia\n"
                    + "INNER JOIN semanas se on inc.idSemana= se.idSemana\n"
                    + "where inc.idSemana='" + idSemana + "' and inc.empleadoId='" + cod + "' ";
            String datos[] = new String[10];
            try {
                  conn = (this.userConn != null) ? this.userConn : Conexion1.getConnection();
                  stmt = conn.prepareStatement(sql);
                  rs = stmt.executeQuery();

                  while (rs.next()) {
//                       
                        datos[1] = rs.getString("actualizadoJA");
                        datos[2] = rs.getString("actualizadoRH");
                        datos[3] = rs.getString("fecha");
                        datos[4] = rs.getString("entrada");
                        datos[5] = rs.getString("salida");
                        datos[6] = rs.getString("horasTrab");
                        datos[7] = rs.getString("nombreinc");
                        datos[8] = rs.getString("comentario");

                        tabla1.addRow(datos);
                  }
            } catch (Exception e) {
                  JOptionPane.showMessageDialog(null, "Error al cargar los datos\n" + e);
            } finally {
                  Conexion1.close(rs);
                  Conexion1.close(stmt);
                  if (this.userConn == null) {
                        Conexion1.close(conn);
                  }
            }
            dia();

      }

//      public void cargarfaltas(int cod) {
//            String sql = "SELECT  emp.empleadoId, emp.nombre, re.fecha, re.entrada, re.salida, re.horas\n"
//                    + "from registros re \n"
//                    + "INNER JOIN empleados emp on re.empleadoId= emp.empleadoId\n"
//                    + "WHERE (re.Entrada='00:00:00.0000000' or re.Salida='00:00:00.0000000' or re.horas='00:00:00.0000000' or re.fecha='1111-11-11') and re.empleadoId='"+cod+"'";
//            String datos[] = new String[10];
//            try {
//                  conn = (this.userConn != null) ? this.userConn : Conexion1.getConnection();
//                  stmt = conn.prepareStatement(sql);
//                  rs = stmt.executeQuery();
//
//                  while (rs.next()) {
//
//                        datos[1] = "";
//                        datos[2] = "";
//                        datos[3] = rs.getString("fecha");
//                        datos[4] = rs.getString("entrada");
//                        datos[5] = rs.getString("salida");
//                        datos[6] = rs.getString("horas");
//                        datos[7] = "";
//                        datos[8] = "";
//
//                        tabla1.addRow(datos);
//                  }
//            } catch (Exception e) {
//                  JOptionPane.showMessageDialog(null, "Error al cargar los datos\n" + e);
//            } finally {
//                  Conexion1.close(rs);
//                  Conexion1.close(stmt);
//                  if (this.userConn == null) {
//                        Conexion1.close(conn);
//                  }
//            }
//            dia();
//      }

      public void dia() {
            GregorianCalendar cal = new GregorianCalendar();
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

            try {
                  for (int i = 0; i < tbdetalles.getRowCount(); i++) {
                        String fec = tbdetalles.getValueAt(i, 3).toString();
                        Date fecha = formato.parse(fec);
                        cal.setTime(fecha);
                        int dia = cal.get(Calendar.DAY_OF_WEEK);

                        if (dia == 1) {
                              tbdetalles.setValueAt("DOMINGO", i, 0);
                        } else if (dia == 2) {
                              tbdetalles.setValueAt("LUNES", i, 0);
                        } else if (dia == 3) {
                              tbdetalles.setValueAt("MARTES", i, 0);
                        } else if (dia == 4) {
                              tbdetalles.setValueAt("MIERCOLES", i, 0);
                        } else if (dia == 5) {
                              tbdetalles.setValueAt("JUEVEZ", i, 0);
                        } else if (dia == 6) {
                              tbdetalles.setValueAt("VIERNES", i, 0);
                        } else if (dia == 7) {
                              tbdetalles.setValueAt("SABADO", i, 0);
                        }

                  }

            } catch (Exception e) {

                  JOptionPane.showMessageDialog(null, "Errorn en: " + e);

            }

//   
      }

      public void limpiar(DefaultTableModel tabla) {
            for (int i = 0; i < tabla.getRowCount(); i++) {
                  tabla.removeRow(i);
                  i -= 1;
            }
      }

      /**
       * This method is called from within the constructor to initialize the
       * form. WARNING: Do NOT modify this code. The content of this method is
       * always regenerated by the Form Editor.
       */
      @SuppressWarnings("unchecked")
      // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
      private void initComponents() {

            accion = new javax.swing.JPopupMenu();
            itemAutorizar = new javax.swing.JMenuItem();
            itemNegar = new javax.swing.JMenuItem();
            jScrollPane1 = new javax.swing.JScrollPane();
            tbdetalles = new javax.swing.JTable();
            txtnombre = new javax.swing.JLabel();
            txtid = new javax.swing.JLabel();
            jLabel1 = new javax.swing.JLabel();
            jLabel2 = new javax.swing.JLabel();
            txtsemana = new javax.swing.JLabel();
            jButton10 = new javax.swing.JButton();

            itemAutorizar.setText("AUTORIZAR");
            itemAutorizar.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        itemAutorizarActionPerformed(evt);
                  }
            });
            accion.add(itemAutorizar);

            itemNegar.setText("NEGAR");
            itemNegar.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        itemNegarActionPerformed(evt);
                  }
            });
            accion.add(itemNegar);

            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
            addMouseListener(new java.awt.event.MouseAdapter() {
                  public void mouseEntered(java.awt.event.MouseEvent evt) {
                        formMouseEntered(evt);
                  }
            });

            tbdetalles.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
            tbdetalles.setModel(new javax.swing.table.DefaultTableModel(
                  new Object [][] {
                        {},
                        {},
                        {},
                        {}
                  },
                  new String [] {

                  }
            ));
            tbdetalles.setComponentPopupMenu(accion);
            tbdetalles.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
                  public void mouseMoved(java.awt.event.MouseEvent evt) {
                        tbdetallesMouseMoved(evt);
                  }
            });
            jScrollPane1.setViewportView(tbdetalles);

            txtnombre.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
            txtnombre.setForeground(new java.awt.Color(255, 255, 255));
            txtnombre.setText("Nombre");

            txtid.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
            txtid.setForeground(new java.awt.Color(255, 255, 255));
            txtid.setText("ID");

            jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
            jLabel1.setForeground(new java.awt.Color(255, 255, 255));
            jLabel1.setText("EMPLEADO: ");

            jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
            jLabel2.setForeground(new java.awt.Color(255, 255, 255));
            jLabel2.setText("ID:");

            txtsemana.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
            txtsemana.setForeground(new java.awt.Color(255, 255, 255));
            txtsemana.setText("SEMANA");

            jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/cancel.png"))); // NOI18N
            jButton10.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jButton10ActionPerformed(evt);
                  }
            });

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                  layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                              .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 895, Short.MAX_VALUE))
                              .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                          .addGroup(layout.createSequentialGroup()
                                                .addGap(27, 27, 27)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                      .addComponent(jLabel1)
                                                      .addComponent(jLabel2))
                                                .addGap(39, 39, 39)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                      .addComponent(txtid)
                                                      .addComponent(txtnombre)))
                                          .addGroup(layout.createSequentialGroup()
                                                .addGap(340, 340, 340)
                                                .addComponent(txtsemana)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
            );
            layout.setVerticalGroup(
                  layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                              .addGroup(layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(txtsemana)
                                    .addGap(26, 26, 26)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel2))
                                          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(txtnombre)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtid))))
                              .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            pack();
      }// </editor-fold>//GEN-END:initComponents

      private void tbdetallesMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbdetallesMouseMoved


      }//GEN-LAST:event_tbdetallesMouseMoved

      private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
            try {

            } catch (Exception e) {
                  JOptionPane.showMessageDialog(null, "Erro en: " + e);
            }
      }//GEN-LAST:event_formMouseEntered

      private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
            try {

                  this.show(false);
            } catch (Exception e) {
            }

      }//GEN-LAST:event_jButton10ActionPerformed

      private void itemAutorizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemAutorizarActionPerformed
            int fila = tbdetalles.getSelectedRow();
            if (fila >= 0) {
                  String cod = txtid.getText();
                  String fec = tbdetalles.getValueAt(fila, 3).toString();
                  int sem = RH_UsuariosConIncidencias.cmbSemana.getSelectedIndex();
                  int id = Integer.parseInt(cod);
                  autorizacionRH au = new autorizacionRH();
                  try {
                        au.autorizar(cod, fec);
                        limpiar(tabla1);
                        cargardatosFiltroSemana(sem, id);
                  } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Error en: " + e);
                  }
            } else {
                  JOptionPane.showMessageDialog(null, "Selecione una fila");
            }

      }//GEN-LAST:event_itemAutorizarActionPerformed

      private void itemNegarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemNegarActionPerformed

            int fila = tbdetalles.getSelectedRow();
            if (fila >= 0) {
                  String cod = txtid.getText();
                  String fec = tbdetalles.getValueAt(fila, 3).toString();
                  int sem = RH_UsuariosConIncidencias.cmbSemana.getSelectedIndex();
                  int id = Integer.parseInt(cod);
                  autorizacionRH au = new autorizacionRH();
                  try {
                        au.negar(cod, fec);
                        limpiar(tabla1);
                        cargardatosFiltroSemana(sem, id);
                        

                  } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Error en: " + e);
                  }
            } else {
                  JOptionPane.showMessageDialog(null, "Selecione una fila");
            }


      }//GEN-LAST:event_itemNegarActionPerformed

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
                  java.util.logging.Logger.getLogger(RH_uci_detalles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                  java.util.logging.Logger.getLogger(RH_uci_detalles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                  java.util.logging.Logger.getLogger(RH_uci_detalles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                  java.util.logging.Logger.getLogger(RH_uci_detalles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            //</editor-fold>
            //</editor-fold>

            /* Create and display the form */
            java.awt.EventQueue.invokeLater(new Runnable() {
                  public void run() {
                        try {
                              new RH_uci_detalles().setVisible(true);
                        } catch (SQLException ex) {
                              Logger.getLogger(RH_uci_detalles.class.getName()).log(Level.SEVERE, null, ex);
                        }
                  }
            });
      }

      // Variables declaration - do not modify//GEN-BEGIN:variables
      private javax.swing.JPopupMenu accion;
      private javax.swing.JMenuItem itemAutorizar;
      private javax.swing.JMenuItem itemNegar;
      private javax.swing.JButton jButton10;
      private javax.swing.JLabel jLabel1;
      private javax.swing.JLabel jLabel2;
      private javax.swing.JScrollPane jScrollPane1;
      private javax.swing.JTable tbdetalles;
      public static javax.swing.JLabel txtid;
      public static javax.swing.JLabel txtnombre;
      public static javax.swing.JLabel txtsemana;
      // End of variables declaration//GEN-END:variables
}
