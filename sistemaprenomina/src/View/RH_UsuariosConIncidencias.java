/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Conexion.Conexion1;
import Controller.autorizacionRH;
import Controller.exportReporte;
import static View.RH_ListadoPersonal.rs;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Programacion 2
 */
public class RH_UsuariosConIncidencias extends javax.swing.JFrame {

      Connection conn;
      PreparedStatement stmt;
      public static ResultSet rs;
      private Connection userConn;
      private TableRowSorter trsFiltro;
      public static String codid;

      /**
       * Creates new form RH_UsuariosConIncidencias
       */
      public RH_UsuariosConIncidencias() throws SQLException {
            initComponents();
            this.setResizable(false);
            this.setLocationRelativeTo(null);
            this.getContentPane().setBackground(new java.awt.Color(8, 50, 119));
            combosemana();
            combodepartamento();
            cargarTitulos1();

      }

      DefaultTableModel tabla1 = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int Fila, int Colum) {
                  return false;
            }
      };

      public void cargarTitulos1() throws SQLException {

            tabla1.addColumn("ID");
            tabla1.addColumn("NOMBRE");
            tabla1.addColumn("DEPARTAMENTO");
            tabla1.addColumn("PUESTO");

            this.tbIncidencias.setModel(tabla1);

            TableColumnModel columnModel = tbIncidencias.getColumnModel();

            columnModel.getColumn(0).setPreferredWidth(75);
            columnModel.getColumn(1).setPreferredWidth(75);
            columnModel.getColumn(2).setPreferredWidth(30);
            columnModel.getColumn(3).setPreferredWidth(200);

      }

      public void combosemana() {

            String sql = "select semana from semanas where estatus=1";
            String datos[] = new String[10];

            try {
                  conn = (this.userConn != null) ? this.userConn : Conexion1.getConnection();
                  stmt = conn.prepareStatement(sql);
                  rs = stmt.executeQuery();
                  cmbSemana.addItem("-SELECCIONE UNA OPCION-");
                  while (rs.next()) {
                        String nombre = rs.getString("semana");
                        cmbSemana.addItem(nombre);

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
      }

      public void combodepartamento() {

            String sql = "select DISTINCT depto from empleados";
            String datos[] = new String[10];

            try {
                  conn = (this.userConn != null) ? this.userConn : Conexion1.getConnection();
                  stmt = conn.prepareStatement(sql);
                  rs = stmt.executeQuery();
                  cmbDepto.addItem("-SELECCIONE UNA OPCION-");
                  while (rs.next()) {
                        String nombre = rs.getString("depto");
                        cmbDepto.addItem(nombre);

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
      }

      public void cargardatosFiltroSemana(int idSemana) throws SQLException {
            String sql = "SELECT DISTINCT  emp.empleadoId, emp.nombre, emp.depto, emp.puesto\n"
                    + "                    from incidencias inc\n"
                    + "                    INNER JOIN empleados emp on inc.empleadoId= emp.empleadoId\n"
                    + "                    INNER JOIN semanas se on inc.idSemana= se.idSemana\n"
                    + "                    where inc.idSemana='" + idSemana + "'";
            String datos[] = new String[10];
            try {
                  conn = (this.userConn != null) ? this.userConn : Conexion1.getConnection();
                  stmt = conn.prepareStatement(sql);
                  rs = stmt.executeQuery();
                  while (rs.next()) {
                        datos[0] = rs.getString("empleadoId");
                        datos[1] = rs.getString("nombre");
                        datos[2] = rs.getString("depto");
                        datos[3] = rs.getString("puesto");
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
      }

      public void cargardatosFiltroDepto(int idSemana, String depto) throws SQLException {
            String sql = "SELECT DISTINCT  emp.empleadoId, emp.nombre, emp.depto, emp.puesto\n"
                    + "                    from incidencias inc\n"
                    + "                    INNER JOIN empleados emp on inc.empleadoId= emp.empleadoId\n"
                    + "                    INNER JOIN semanas se on inc.idSemana= se.idSemana\n"
                    + "                    where inc.idSemana='" + idSemana + "' and emp.depto='" + depto + "'";
            String datos[] = new String[10];
            try {
                  conn = (this.userConn != null) ? this.userConn : Conexion1.getConnection();
                  stmt = conn.prepareStatement(sql);
                  rs = stmt.executeQuery();
                  while (rs.next()) {
                        datos[0] = rs.getString("empleadoId");
                        datos[1] = rs.getString("nombre");
                        datos[2] = rs.getString("depto");
                        datos[3] = rs.getString("puesto");
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
      }

      public void prueba() throws SQLException {
            int sem = cmbSemana.getSelectedIndex();
            if (sem != 0) {
                  limpiar(tabla1);

                  int dep = cmbSemana.getSelectedIndex();

                  if (dep == 0) {
                        cargardatosFiltroSemana(sem);

                  } else {
                        String depp = cmbSemana.getSelectedItem().toString();

                        cargardatosFiltroDepto(sem, depp);

                  }

            } else {
                  cmbSemana.setSelectedIndex(0);
                  JOptionPane.showMessageDialog(null, "Seleccione antes una semana");
            }
      }

      public void filtroBusqueda(JTextField txt) {
            trsFiltro.setRowFilter(RowFilter.regexFilter(txt.getText()));
      }

      public void limpiar(DefaultTableModel tabla) {
            for (int i = 0; i < tabla.getRowCount(); i++) {
                  tabla.removeRow(i);
                  i -= 1;
            }
      }

//      public void cargarfaltadepto(String depto) {
//            String sql = "select emp.empleadoId, emp.nombre, emp.depto, emp.puesto \n"
//                    + "from registros  reg \n"
//                    + "INNER JOIN empleados emp on reg.empleadoId= emp.empleadoId \n"
//                    + "WHERE ( reg.Entrada='00:00:00.0000000' or reg.Salida='00:00:00.0000000'  or reg.fecha='1111-11-11') and emp.depto='" + depto + "'";
//            String datos[] = new String[10];
//            try {
//                  conn = (this.userConn != null) ? this.userConn : Conexion1.getConnection();
//                  stmt = conn.prepareStatement(sql);
//                  rs = stmt.executeQuery();
//                  while (rs.next()) {
//                        datos[0] = rs.getString("empleadoId");
//                        datos[1] = rs.getString("nombre");
//                        datos[2] = rs.getString("depto");
//                        datos[3] = rs.getString("puesto");
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
//
//      }
//      public void cargarfaltasemana() {
//            String sql = "select emp.empleadoId, emp.nombre, emp.depto, emp.puesto \n"
//                    + "from registros  reg \n"
//                    + "INNER JOIN empleados emp on reg.empleadoId= emp.empleadoId \n"
//                    + "WHERE reg.Entrada='00:00:00.0000000' or reg.Salida='00:00:00.0000000'  or reg.fecha='1111-11-11' ";
//            String datos[] = new String[10];
//            try {
//                  conn = (this.userConn != null) ? this.userConn : Conexion1.getConnection();
//                  stmt = conn.prepareStatement(sql);
//                  rs = stmt.executeQuery();
//                  while (rs.next()) {
//                        datos[0] = rs.getString("empleadoId");
//                        datos[1] = rs.getString("nombre");
//                        datos[2] = rs.getString("depto");
//                        datos[3] = rs.getString("puesto");
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
//
//      }
      /**
       * This method is called from within the constructor to initialize the
       * form. WARNING: Do NOT modify this code. The content of this method is
       * always regenerated by the Form Editor.
       */
      @SuppressWarnings("unchecked")
      // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
      private void initComponents() {

            pmAutorizar = new javax.swing.JPopupMenu();
            itemDetalles = new javax.swing.JMenuItem();
            jPanel3 = new javax.swing.JPanel();
            jLabel27 = new javax.swing.JLabel();
            lbCargo2 = new javax.swing.JLabel();
            lbNombre2 = new javax.swing.JLabel();
            jLabel31 = new javax.swing.JLabel();
            jLabel32 = new javax.swing.JLabel();
            jLabel1 = new javax.swing.JLabel();
            cmbSemana = new javax.swing.JComboBox();
            txtBuscar = new javax.swing.JTextField();
            jScrollPane2 = new javax.swing.JScrollPane();
            tbIncidencias = new javax.swing.JTable();
            jButton10 = new javax.swing.JButton();
            jPanel1 = new javax.swing.JPanel();
            btnBuscar6 = new javax.swing.JButton();
            btnBuscar7 = new javax.swing.JButton();
            btnBuscar4 = new javax.swing.JButton();
            btnBuscar5 = new javax.swing.JButton();
            btnBuscar2 = new javax.swing.JButton();
            btnBuscar3 = new javax.swing.JButton();
            jLabel2 = new javax.swing.JLabel();
            jLabel3 = new javax.swing.JLabel();
            jLabel4 = new javax.swing.JLabel();
            jLabel5 = new javax.swing.JLabel();
            jLabel6 = new javax.swing.JLabel();
            jLabel7 = new javax.swing.JLabel();
            jLabel8 = new javax.swing.JLabel();
            cmbDepto = new javax.swing.JComboBox();

            itemDetalles.setText("Detalles");
            itemDetalles.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        itemDetallesActionPerformed(evt);
                  }
            });
            pmAutorizar.add(itemDetalles);

            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

            jPanel3.setBackground(new java.awt.Color(0, 153, 255));

            jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/icono1.png"))); // NOI18N

            lbCargo2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
            lbCargo2.setText("CARGO");

            lbNombre2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
            lbNombre2.setText("NOMBRE");

            jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/user.png"))); // NOI18N

            jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/portafolio.png"))); // NOI18N

            javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
            jPanel3.setLayout(jPanel3Layout);
            jPanel3Layout.setHorizontalGroup(
                  jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel27)
                        .addGap(43, 43, 43)
                        .addComponent(jLabel32)
                        .addGap(18, 18, 18)
                        .addComponent(lbCargo2, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel31)
                        .addGap(30, 30, 30)
                        .addComponent(lbNombre2, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(391, Short.MAX_VALUE))
            );
            jPanel3Layout.setVerticalGroup(
                  jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                              .addComponent(lbNombre2)
                              .addComponent(jLabel31)
                              .addComponent(lbCargo2)
                              .addComponent(jLabel32)
                              .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(12, Short.MAX_VALUE))
            );

            jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
            jLabel1.setForeground(new java.awt.Color(255, 255, 255));
            jLabel1.setText("Reportar semana");

            cmbSemana.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
            cmbSemana.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        cmbSemanaActionPerformed(evt);
                  }
            });

            txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
                  public void keyTyped(java.awt.event.KeyEvent evt) {
                        txtBuscarKeyTyped(evt);
                  }
            });

            tbIncidencias.setModel(new javax.swing.table.DefaultTableModel(
                  new Object [][] {
                        {},
                        {},
                        {},
                        {},
                        {},
                        {},
                        {},
                        {},
                        {},
                        {},
                        {},
                        {},
                        {}
                  },
                  new String [] {

                  }
            ));
            tbIncidencias.setComponentPopupMenu(pmAutorizar);
            jScrollPane2.setViewportView(tbIncidencias);

            jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/cancel.png"))); // NOI18N
            jButton10.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jButton10ActionPerformed(evt);
                  }
            });

            jPanel1.setBackground(new java.awt.Color(8, 50, 119));

            btnBuscar6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/calcular1.png"))); // NOI18N

            btnBuscar7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/calcular.png"))); // NOI18N

            btnBuscar4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/xls1.png"))); // NOI18N

            btnBuscar5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/xls1.png"))); // NOI18N
            btnBuscar5.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnBuscar5ActionPerformed(evt);
                  }
            });

            btnBuscar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/txt.png"))); // NOI18N

            btnBuscar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/calendar.png"))); // NOI18N

            jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
            jLabel2.setForeground(new java.awt.Color(255, 255, 255));
            jLabel2.setText("Exportar datos a TXT");

            jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
            jLabel3.setForeground(new java.awt.Color(255, 255, 255));
            jLabel3.setText("Reporte General");

            jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
            jLabel4.setForeground(new java.awt.Color(255, 255, 255));
            jLabel4.setText("Perceciones y Deducciones");

            jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
            jLabel5.setForeground(new java.awt.Color(255, 255, 255));
            jLabel5.setText("Calcular Faltas");

            jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
            jLabel6.setForeground(new java.awt.Color(255, 255, 255));
            jLabel6.setText("Calcular Prima Dominical");

            jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
            jLabel7.setForeground(new java.awt.Color(255, 255, 255));
            jLabel7.setText("Calcular Sobre Sueldo");

            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                  jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                              .addComponent(btnBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                              .addComponent(btnBuscar3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                              .addComponent(jLabel5)
                              .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                              .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(btnBuscar7, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel6))
                              .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(btnBuscar5, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel3)))
                        .addGap(84, 84, 84)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                              .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(btnBuscar6, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel7))
                              .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(btnBuscar4, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel4)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnBuscar2, btnBuscar3, btnBuscar4, btnBuscar5, btnBuscar6, btnBuscar7});

            jPanel1Layout.setVerticalGroup(
                  jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                              .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(btnBuscar4, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                              .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(26, 26, 26)
                                    .addComponent(jLabel2))
                              .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(28, 28, 28)
                                    .addComponent(jLabel4))
                              .addComponent(btnBuscar2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                              .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(btnBuscar6, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 6, Short.MAX_VALUE))
                              .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                          .addComponent(btnBuscar3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel5)
                                                .addGap(28, 28, 28))
                                          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel7)
                                                .addGap(31, 31, 31))))))
                  .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                              .addComponent(btnBuscar5, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(29, 29, 29)
                                    .addComponent(jLabel3)))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                              .addComponent(btnBuscar7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addGap(30, 30, 30))))
            );

            jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnBuscar2, btnBuscar3, btnBuscar4, btnBuscar5, btnBuscar6, btnBuscar7});

            jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
            jLabel8.setForeground(new java.awt.Color(255, 255, 255));
            jLabel8.setText("Reportar area");

            cmbDepto.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
            cmbDepto.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        cmbDeptoActionPerformed(evt);
                  }
            });

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                  layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                  .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32))
                  .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                              .addGroup(layout.createSequentialGroup()
                                    .addGap(34, 34, 34)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cmbSemana, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(62, 62, 62)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(99, 99, 99)
                                    .addComponent(cmbDepto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE))
                              .addGroup(layout.createSequentialGroup()
                                    .addGap(24, 24, 24)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                          .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                          .addGroup(layout.createSequentialGroup()
                                                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE)))))
                        .addContainerGap())
                  .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addContainerGap())
            );

            layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cmbDepto, cmbSemana});

            layout.setVerticalGroup(
                  layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                              .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbSemana, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                              .addComponent(cmbDepto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cmbDepto, cmbSemana});

            pack();
      }// </editor-fold>//GEN-END:initComponents

      private void cmbSemanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSemanaActionPerformed
            limpiar(tabla1);
            int sem = cmbSemana.getSelectedIndex();
            try {
                  if (sem != 0) {
                        cargardatosFiltroSemana(sem);

                  }

            } catch (Exception e) {
                  JOptionPane.showMessageDialog(null, "Error en: " + e);
            }


      }//GEN-LAST:event_cmbSemanaActionPerformed

      private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
            RH_Inicio rh_i = new RH_Inicio();
            rh_i.show(true);
            this.show(false);
      }//GEN-LAST:event_jButton10ActionPerformed

      private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
            txtBuscar.addKeyListener(new KeyAdapter() {
                  public void keyReleased(final KeyEvent e) {
                        String cadena = (txtBuscar.getText()).toUpperCase();
                        txtBuscar.setText(cadena);
                        repaint();
                        filtroBusqueda(txtBuscar);
                  }
            });
            trsFiltro = new TableRowSorter(tbIncidencias.getModel());
            tbIncidencias.setRowSorter(trsFiltro);


      }//GEN-LAST:event_txtBuscarKeyTyped

      private void cmbDeptoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDeptoActionPerformed
            try {
                  int index = cmbSemana.getSelectedIndex();
                  if (index != 0) {
                        int sem = cmbSemana.getSelectedIndex();
                        if (sem != 0) {
                              limpiar(tabla1);
                              int dep = cmbDepto.getSelectedIndex();
                              if (dep == 0) {
                                    cargardatosFiltroSemana(sem);

                              } else {
                                    String depp = cmbDepto.getSelectedItem().toString();
                                    cargardatosFiltroDepto(sem, depp);

                              }
                        } else {
                              cmbDepto.setSelectedIndex(0);
                              JOptionPane.showMessageDialog(null, "Si desea hacer un filtro por departamento SELECCIONE ANTES UNA SEMANA");
                        }
                  } else {
                        cmbDepto.setSelectedIndex(0);
                        JOptionPane.showMessageDialog(null, "Si desea hacer un filtro por departamento SELECCIONE ANTES UNA SEMANA");
                  }
            } catch (Exception e) {
                  JOptionPane.showMessageDialog(null, "Error en: " + e);
            }
      }//GEN-LAST:event_cmbDeptoActionPerformed

      private void btnBuscar5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar5ActionPerformed
//            JFileChooser dialog = new JFileChooser();
//            int opcion = dialog.showSaveDialog(RH_UsuariosConIncidencias.this);
//
//            if (opcion == JFileChooser.APPROVE_OPTION) {
//
//                  File dir = dialog.getSelectedFile();
//
//                  try {
//                        List<JTable> tb = new ArrayList<JTable>();
//                        tb.add(tbIncidencias);
//                        //-------------------
//                        exportReporte excelExporter = new exportReporte(tb, new File(dir.getAbsolutePath() + ".xls"));
//                        if (excelExporter.export()) {
//                              JOptionPane.showMessageDialog(null, "REPORTE EXPORTADO CON EXITO");
//                        }
//                  } catch (Exception ex) {
//                        ex.printStackTrace();
//                  }
//            }
            
            try {
                   exportReporte exp = new exportReporte();
            int semana =1;
            exp.cargardatosFiltroSemana(semana);
            } catch (Exception e) {
                  JOptionPane.showMessageDialog(null, e);
            
            }

           

      }//GEN-LAST:event_btnBuscar5ActionPerformed

      private void itemDetallesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemDetallesActionPerformed
            int fila = tbIncidencias.getSelectedRow();
            if (fila >= 0) {
            try {
                  
                  String nom = tbIncidencias.getValueAt(fila, 1).toString();
                  codid = tbIncidencias.getValueAt(fila, 0).toString();
                  
                        RH_uci_detalles deta = new RH_uci_detalles();
                        deta.show();

                        RH_uci_detalles.txtnombre.setText(nom);
                        RH_uci_detalles.txtid.setText(codid);
                        RH_uci_detalles.txtsemana.setText(cmbSemana.getSelectedItem().toString());

                 

            } catch (Exception e) {
                  JOptionPane.showMessageDialog(null, "Error en: " + e);
            } } else {
                        JOptionPane.showMessageDialog(null, "Seleccione una fila ");
                  }

      }//GEN-LAST:event_itemDetallesActionPerformed

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
                  java.util.logging.Logger.getLogger(RH_UsuariosConIncidencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                  java.util.logging.Logger.getLogger(RH_UsuariosConIncidencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                  java.util.logging.Logger.getLogger(RH_UsuariosConIncidencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                  java.util.logging.Logger.getLogger(RH_UsuariosConIncidencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            //</editor-fold>
            //</editor-fold>

            /* Create and display the form */
            java.awt.EventQueue.invokeLater(new Runnable() {
                  public void run() {
                        try {
                              new RH_UsuariosConIncidencias().setVisible(true);
                        } catch (SQLException ex) {
                              Logger.getLogger(RH_UsuariosConIncidencias.class.getName()).log(Level.SEVERE, null, ex);
                        }
                  }
            });
      }

      // Variables declaration - do not modify//GEN-BEGIN:variables
      private javax.swing.JButton btnBuscar2;
      private javax.swing.JButton btnBuscar3;
      private javax.swing.JButton btnBuscar4;
      private javax.swing.JButton btnBuscar5;
      private javax.swing.JButton btnBuscar6;
      private javax.swing.JButton btnBuscar7;
      private javax.swing.JComboBox cmbDepto;
      public static javax.swing.JComboBox cmbSemana;
      private javax.swing.JMenuItem itemDetalles;
      private javax.swing.JButton jButton10;
      private javax.swing.JLabel jLabel1;
      private javax.swing.JLabel jLabel2;
      private javax.swing.JLabel jLabel27;
      private javax.swing.JLabel jLabel3;
      private javax.swing.JLabel jLabel31;
      private javax.swing.JLabel jLabel32;
      private javax.swing.JLabel jLabel4;
      private javax.swing.JLabel jLabel5;
      private javax.swing.JLabel jLabel6;
      private javax.swing.JLabel jLabel7;
      private javax.swing.JLabel jLabel8;
      private javax.swing.JPanel jPanel1;
      private javax.swing.JPanel jPanel3;
      private javax.swing.JScrollPane jScrollPane2;
      private javax.swing.JLabel lbCargo2;
      private javax.swing.JLabel lbNombre2;
      private javax.swing.JPopupMenu pmAutorizar;
      public static javax.swing.JTable tbIncidencias;
      private javax.swing.JTextField txtBuscar;
      // End of variables declaration//GEN-END:variables

}
