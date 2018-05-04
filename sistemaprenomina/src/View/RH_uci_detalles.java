/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Conexion.Conexion1;
import Controller.EJefes;
import Controller.autorizacionRH;
import static View.RH_UsuariosConIncidencias.rs;
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
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import static View.RH_UsuariosConIncidencias.tbincidencias;

/**
 *
 * @author Programacion 2
 */
public class RH_uci_detalles extends javax.swing.JFrame {

    Connection conn;
    PreparedStatement stmt;
    public static ResultSet rs;
    private Connection userConn;
    Connection conn1;
    PreparedStatement stmt1;
    public static ResultSet rs1;
    private Connection userConn1;
    int idemp = Integer.parseInt(RH_UsuariosConIncidencias.codid);
    String nomsem = RH_UsuariosConIncidencias.cmbSemana.getSelectedItem().toString();
RH_UsuariosConIncidencias inci = new RH_UsuariosConIncidencias();

    /**
     * Creates new form RH_uci_detalles
     */
    public RH_uci_detalles() throws SQLException {
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new java.awt.Color(8, 50, 119));
        cargartitulos();
        

        limpiar(tabla1);
        cargardatosFiltroSemana(inci.semana(nomsem), idemp);
        tbdetalles.setDefaultRenderer(Object.class, new EJefes());
    }
    DefaultTableModel tabla1 = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int Fila, int Colum) {
            return false;
        }
    };

    public void cargartitulos() throws SQLException {

        tabla1.addColumn("ACTUALOZADO JA");
        tabla1.addColumn("ACTUALIZADO RH");
        tabla1.addColumn("Dia");
        tabla1.addColumn("FECHA");
        tabla1.addColumn("ENTRADA");
        tabla1.addColumn("SALIDA");
        tabla1.addColumn("HORAS");
        tabla1.addColumn("EXTRA");
        tabla1.addColumn("INCIDENCIA");
        tabla1.addColumn("COMENTARIO");

        this.tbdetalles.setModel(tabla1);
//            tabla1.addColumn(0, "");

        TableColumnModel columnModel = tbdetalles.getColumnModel();

        columnModel.getColumn(0).setPreferredWidth(75);
        columnModel.getColumn(1).setPreferredWidth(75);
        columnModel.getColumn(2).setPreferredWidth(40);
        columnModel.getColumn(3).setPreferredWidth(50);
        columnModel.getColumn(4).setPreferredWidth(30);
        columnModel.getColumn(5).setPreferredWidth(30);
        columnModel.getColumn(6).setPreferredWidth(30);
        columnModel.getColumn(7).setPreferredWidth(30);
        columnModel.getColumn(8).setPreferredWidth(100);
        columnModel.getColumn(9).setPreferredWidth(200);

    }

    public void cargardatosFiltroSemana(int idSemana, int cod) throws SQLException {

        String sql = "SELECT inc.actualizadoJA, inc.actualizadoRH, emp.empleadoId, emp.nombre, inc.fecha,  nomin.nombre AS nombreinc, inc.comentario, inc.horasExtra \n"
                + "from incidencias inc\n"
                + "INNER JOIN empleados emp on inc.empleadoId= emp.empleadoId\n"
                + "INNER JOIN NomIncidencia nomin on  nomin.idNomIncidencia = inc.idNomIncidencia\n"
                + "INNER JOIN semanas se on inc.idSemana= se.idSemana\n"
                + "where inc.idSemana='" + idSemana + "' and inc.empleadoId='" + cod + "' ";
        Object datos[] = new Object[10];
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion1.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
//                       

                if (rs.getString("actualizadoJA") == null || "".equals(rs.getString("actualizadoJA")) || rs.getString("actualizadoJA").equalsIgnoreCase("NEGADO")) {
                    datos[0] = new JLabel(new ImageIcon(getClass().getResource("/View/img/noactualizadoj.png")));
                } else {
                    datos[0] = new JLabel(new ImageIcon(getClass().getResource("/View/img/actulizadoj.png")));
                }
                if (rs.getString("actualizadoRH") == null || "".equals(rs.getString("actualizadoRH")) || rs.getString("actualizadoRH").equalsIgnoreCase("NEGADO")) {
                    datos[1] = new JLabel(new ImageIcon(getClass().getResource("/View/img/noactualizadoj.png")));
                } else {
                    datos[1] = new JLabel(new ImageIcon(getClass().getResource("/View/img/actulizadoj.png")));
                }
//                datos[1] = rs.getString("actualizadoJA");
//                datos[2] = rs.getString("actualizadoRH");
                datos[3] = rs.getString("fecha");
                datos[4] = "";
                datos[5] = "";
                datos[6] = "";
                datos[7] = rs.getString("horasExtra");
                datos[8] = rs.getString("nombreinc");
                datos[9] = rs.getString("comentario");

                String sql1 = "SELECT entrada, salida, horas from registros where empleadoId='" + cod + "' and fecha='" + datos[3] + "'";
                try {
                    conn1 = (this.userConn1 != null) ? this.userConn1 : Conexion1.getConnection();
                    stmt1 = conn1.prepareStatement(sql1);
                    rs1 = stmt1.executeQuery();

                    while (rs1.next()) {
                        datos[4] = rs1.getString("entrada");
                        datos[5] = rs1.getString("salida");
                        datos[6] = rs1.getString("horas");

                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error al cargar los datos\n" + e, "ERROR", JOptionPane.ERROR_MESSAGE);
                } finally {
                    Conexion1.close(rs1);
                    Conexion1.close(stmt1);
                    if (this.userConn1 == null) {
                        Conexion1.close(conn1);
                    }
                }

                tabla1.addRow(datos);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar los datos\n" + e, "ERROR", JOptionPane.ERROR_MESSAGE);
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
                    tbdetalles.setValueAt("DOMINGO", i, 2);
                } else if (dia == 2) {
                    tbdetalles.setValueAt("LUNES", i, 2);
                } else if (dia == 3) {
                    tbdetalles.setValueAt("MARTES", i, 2);
                } else if (dia == 4) {
                    tbdetalles.setValueAt("MIERCOLES", i, 2);
                } else if (dia == 5) {
                    tbdetalles.setValueAt("JUEVEZ", i, 2);
                } else if (dia == 6) {
                    tbdetalles.setValueAt("VIERNES", i, 2);
                } else if (dia == 7) {
                    tbdetalles.setValueAt("SABADO", i, 2);
                }

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Errorn en: " + e, "ERROR", JOptionPane.ERROR_MESSAGE);

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
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
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
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        lblnombrerh = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        lblcargo = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();

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
        setUndecorated(true);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
        });

        tbdetalles.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
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

        jPanel2.setBackground(new java.awt.Color(229, 230, 234));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/portafolio.png"))); // NOI18N
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, -1, 40));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/user.png"))); // NOI18N
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, -1, 40));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/minimizar.png"))); // NOI18N
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 0, 32, 30));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/regresar.png"))); // NOI18N
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 0, 32, 30));

        lblnombrerh.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lblnombrerh.setForeground(new java.awt.Color(51, 102, 255));
        jPanel2.add(lblnombrerh, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 230, 20));

        jSeparator3.setBackground(new java.awt.Color(51, 102, 255));
        jPanel2.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, 230, 10));

        lblcargo.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lblcargo.setForeground(new java.awt.Color(51, 102, 255));
        jPanel2.add(lblcargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 20, 230, 20));

        jSeparator2.setBackground(new java.awt.Color(51, 102, 255));
        jPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 40, 230, 10));
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(362, 362, 362)
                        .addComponent(txtsemana))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(89, 89, 89)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(144, 144, 144)
                                .addComponent(jLabel2)))
                        .addGap(74, 74, 74)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtid)
                            .addComponent(txtnombre))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtsemana)
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtnombre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtid))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

      private void tbdetallesMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbdetallesMouseMoved


      }//GEN-LAST:event_tbdetallesMouseMoved

      private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
          try {

          } catch (Exception e) {
              JOptionPane.showMessageDialog(null, "Erro en: " + e, "ERROR", JOptionPane.ERROR_MESSAGE);
          }
      }//GEN-LAST:event_formMouseEntered

      private void itemAutorizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemAutorizarActionPerformed
          int fila = tbdetalles.getSelectedRow();
          String cod = txtid.getText();
          int sem = RH_UsuariosConIncidencias.cmbSemana.getSelectedIndex();
          int id = Integer.parseInt(cod);
          autorizacionRH au = new autorizacionRH();
          if (fila != -1) {
              int contador = tbdetalles.getSelectedRowCount();
              if (contador == 1) {
                  String fec = tbdetalles.getValueAt(fila, 3).toString();
                  try {
                      au.autorizar(cod, fec);
                      limpiar(tabla1);
                      cargardatosFiltroSemana(inci.semana(nomsem), id);
                  } catch (Exception e) {
                      JOptionPane.showMessageDialog(null, "Error en: " + e, "ERROR", JOptionPane.ERROR_MESSAGE);
                  }
              } else {
                  try {
                      int codi[] = tbdetalles.getSelectedRows();
                      String empfecha[] = new String[codi.length];
                      for (int i = 0; i < codi.length; i++) {
                          empfecha[i] = tbdetalles.getValueAt(codi[i], 3).toString();
                          String fec = empfecha[i];
                          au.autorizar(cod, fec);
                      }
                      limpiar(tabla1);
                      cargardatosFiltroSemana(inci.semana(nomsem), id);
                  } catch (Exception e) {
                      JOptionPane.showMessageDialog(null, "Error en: " + e, "ERROR", JOptionPane.ERROR_MESSAGE);
                  }
              }

          } else if (fila == -1) {
              JOptionPane.showMessageDialog(null, "Selecione una fila", "", JOptionPane.WARNING_MESSAGE);
          }
      }//GEN-LAST:event_itemAutorizarActionPerformed

      private void itemNegarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemNegarActionPerformed

          int fila = tbdetalles.getSelectedRow();
          String cod = txtid.getText();
          int sem = RH_UsuariosConIncidencias.cmbSemana.getSelectedIndex();
          int id = Integer.parseInt(cod);
          autorizacionRH au = new autorizacionRH();
          if (fila != -1) {
              int contador = tbdetalles.getSelectedRowCount();
              if (contador == 1) {
                  String fec = tbdetalles.getValueAt(fila, 3).toString();
                  try {
                      au.negar(cod, fec);
                      limpiar(tabla1);
                      cargardatosFiltroSemana(sem, id);
                  } catch (Exception e) {
                      JOptionPane.showMessageDialog(null, "Error en: " + e, "ERROR", JOptionPane.ERROR_MESSAGE);
                  }
              } else {
                  try {
                      int codi[] = tbdetalles.getSelectedRows();
                      String empfecha[] = new String[codi.length];
                      for (int i = 0; i < codi.length; i++) {
                          empfecha[i] = tbdetalles.getValueAt(codi[i], 3).toString();
                          String fec = empfecha[i];
                          au.negar(cod, fec);
                      }
                      limpiar(tabla1);
                      cargardatosFiltroSemana(sem, id);
                  } catch (Exception e) {
                      JOptionPane.showMessageDialog(null, "Error en: " + e, "ERROR", JOptionPane.ERROR_MESSAGE);
                  }
              }

          } else if (fila == -1) {
              JOptionPane.showMessageDialog(null, "Selecione una fila", "", JOptionPane.WARNING_MESSAGE);
          }

      }//GEN-LAST:event_itemNegarActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        try {

            this.show(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en: " + e, "ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.setExtendedState(ICONIFIED);
    }//GEN-LAST:event_jButton2ActionPerformed

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
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    public static javax.swing.JLabel lblcargo;
    public static javax.swing.JLabel lblnombrerh;
    private javax.swing.JTable tbdetalles;
    public static javax.swing.JLabel txtid;
    public static javax.swing.JLabel txtnombre;
    public static javax.swing.JLabel txtsemana;
    // End of variables declaration//GEN-END:variables
}
