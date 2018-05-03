/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Conexion.Conexion;
import Conexion.Conexion1;
import Controller.EJefes;
import Controller.EstiloPercepReport;
import Controller.PrimaDominical;
import Controller.autorizacionRH;
import Controller.estilosreporte;
import Controller.exportReporte;
import static View.RH_Inicio.lblcargo;
import static View.RH_Inicio.lblnombrerh;
import static View.RH_ListadoPersonal.rs;
import static View.RH_UsuariosConIncidencias.cmbSemana;
import static View.RH_UsuariosConIncidencias.codid;
import static View.RH_UsuariosConIncidencias.lblcargo;
import static View.RH_UsuariosConIncidencias.lblnombrerh;
import static View.RH_UsuariosConIncidencias.tbIncidencias;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author Programacion 2
 */
public class RH_UsuariosSinIncidencias extends javax.swing.JFrame {

    Connection conn;
    PreparedStatement stmt;
    public static ResultSet rs;
    private Connection userConn;
    private TableRowSorter trsFiltro;
    public static String codid;
    int x, y;

    /**
     * Creates new form RH_UsuariosConIncidencias
     */
    public RH_UsuariosSinIncidencias() throws SQLException {
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new java.awt.Color(51, 102, 255));
        combosemana();
        combodepartamento();
        cargarTitulos1();
        panelincidencias.setVisible(false);

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

        this.tbsinIncidencias.setModel(tabla1);

        TableColumnModel columnModel = tbsinIncidencias.getColumnModel();

        columnModel.getColumn(0).setPreferredWidth(30);
        columnModel.getColumn(1).setPreferredWidth(200);
        columnModel.getColumn(2).setPreferredWidth(150);
        columnModel.getColumn(3).setPreferredWidth(150);

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
        String sql = "SELECT emp.empleadoId, emp.nombre, emp.depto, emp.puesto  FROM empleados emp \n"
                + "LEFT JOIN incidencias inc ON emp.empleadoId = inc.empleadoId AND inc.idSemana='"+idSemana+"'\n"
                + "WHERE  inc.empleadoId  IS NULL ";
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
    public void reportetxt(int semana) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("todos los archivos *.txt", "txt", "TXT"));//filtro para ver solo archivos .edu
        int seleccion = fileChooser.showSaveDialog(null);
        try {
            if (seleccion == JFileChooser.APPROVE_OPTION) {//comprueba si ha presionado el boton de aceptar
                File JFC = fileChooser.getSelectedFile();
                String PATH = JFC.getAbsolutePath();//obtenemos el path del archivo a guardar
                PrintWriter printwriter = new PrintWriter(JFC);
                BufferedWriter bw = new BufferedWriter(printwriter);
                Connection conn = null;
                PreparedStatement stmt = null;
                PreparedStatement nstmt = null;
                ResultSet rs = null;
                ResultSet interno = null;
                try {
                    String sql = "SELECT DISTINCT  empleadoId  from incidencias";
                    conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
                    stmt = conn.prepareStatement(sql);
                    rs = stmt.executeQuery();

                    while (rs.next()) {
                        String idempleado = rs.getString("empleadoId");
                        bw.write("E\t" + idempleado);
                        bw.newLine();
                        String incidencias = " select nomi.nombre,inc.fecha from incidencias inc  inner join NomIncidencia nomi   on inc.idNomIncidencia  = nomi.idNomIncidencia where inc.empleadoId ='" + idempleado + "' and inc.idSemana ='" + semana + "' ";
                        conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
                        nstmt = conn.prepareStatement(incidencias);
                        interno = nstmt.executeQuery();
                        while (interno.next()) {
                            String nomcidencia = interno.getString("nombre");
                            String Fechainc = interno.getString("fecha");
                            String[] datos = Fechainc.split("-");
                            String año = datos[0];
                            String mes = datos[1];
                            String dia = datos[2];
                            String fecha = dia + "/" + mes + "/" + año;
                            String incidencia = "D " + nomcidencia;
                            Calendar cal = Calendar.getInstance();
                            int añoact = cal.get(Calendar.YEAR);
                            if (incidencia.length() < 40) {
                                for (int i = incidencia.length(); i < 40; i++) {
                                    incidencia += " ";
                                }
                            }
                            bw.write(incidencia);
                            bw.write("" + fecha + "\t" + añoact);
                            bw.newLine();

                        }

                    }
                    bw.close();//cierra el archivo
                    if (!(PATH.endsWith(".txt"))) {
                        File temp = new File(PATH + ".txt");
                        JFC.renameTo(temp);//renombramos el archivo
                    }

                    JOptionPane.showMessageDialog(null, "Guardado exitoso!", "Guardado exitoso!", JOptionPane.INFORMATION_MESSAGE);

                } catch (Exception e) {
                    System.out.println("" + e);
                } finally {
                    Conexion.close(rs);
                    Conexion.close(stmt);
                }

                //comprobamos si a la hora de guardar obtuvo la extension y si no se la asignamos
            }
        } catch (Exception e) {//por alguna excepcion salta un mensaje de error
            JOptionPane.showMessageDialog(null, "Error al guardar el archivo!", "Oops! Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void enviarfechas(String fechainicio, String fechafin) {

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pmAutorizar = new javax.swing.JPopupMenu();
        itemDetalles = new javax.swing.JMenuItem();
        itemPercepciones = new javax.swing.JMenuItem();
        jLabel1 = new javax.swing.JLabel();
        cmbSemana = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        cmbDepto = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        lblnombrerh = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        lblcargo = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        panelincidencias = new javax.swing.JPanel();
        txtBuscar = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbsinIncidencias = new javax.swing.JTable();

        itemDetalles.setText("Detalles");
        itemDetalles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemDetallesActionPerformed(evt);
            }
        });
        pmAutorizar.add(itemDetalles);

        itemPercepciones.setText("Percepciones y Deducciones");
        itemPercepciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemPercepcionesActionPerformed(evt);
            }
        });
        pmAutorizar.add(itemPercepciones);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Reportar semana");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 88, 151, 30));

        cmbSemana.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cmbSemana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSemanaActionPerformed(evt);
            }
        });
        getContentPane().add(cmbSemana, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, 229, 30));

        jLabel8.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Reportar area");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 90, 160, 30));

        cmbDepto.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cmbDepto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDeptoActionPerformed(evt);
            }
        });
        getContentPane().add(cmbDepto, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 90, 229, 30));

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
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 0, 32, 30));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/error.png"))); // NOI18N
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 0, 32, 30));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/regresar.png"))); // NOI18N
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 0, 32, 30));

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

        jLabel11.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabel11MouseDragged(evt);
            }
        });
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel11MousePressed(evt);
            }
        });
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1040, 50));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1053, 52));

        panelincidencias.setBackground(new java.awt.Color(51, 102, 255));
        panelincidencias.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
            }
        });
        panelincidencias.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 254, 35));

        tbsinIncidencias.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        tbsinIncidencias.setModel(new javax.swing.table.DefaultTableModel(
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
        tbsinIncidencias.setComponentPopupMenu(pmAutorizar);
        jScrollPane2.setViewportView(tbsinIncidencias);

        panelincidencias.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 1053, 410));

        getContentPane().add(panelincidencias, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 142, 1053, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

      private void cmbSemanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSemanaActionPerformed
          limpiar(tabla1);
          int sem = cmbSemana.getSelectedIndex();
          try {
              if (sem != 0) {
                  panelincidencias.setVisible(true);
                  cargardatosFiltroSemana(sem);

              }

          } catch (Exception e) {
              JOptionPane.showMessageDialog(null, "Error en: " + e);
          }


      }//GEN-LAST:event_cmbSemanaActionPerformed

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

      private void itemDetallesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemDetallesActionPerformed
 
  

            String dep = lblcargo.getText();
            String nomm = lblnombrerh.getText();
            int fila = tbsinIncidencias.getSelectedRow();
            String nom = tbsinIncidencias.getValueAt(fila, 1).toString();
            codid = tbsinIncidencias.getValueAt(fila, 0).toString();
            RH_snci_detalles deta = new RH_snci_detalles();
            deta.show();
            RH_snci_detalles.lblcargo.setText(dep);
            RH_snci_detalles.lblnombrerh.setText(nomm);
            RH_snci_detalles.txtnombre.setText(nom);
            RH_snci_detalles.txtid.setText(codid);
            RH_snci_detalles.txtsemana.setText(cmbSemana.getSelectedItem().toString());
    
             
            

      }//GEN-LAST:event_itemDetallesActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        this.setExtendedState(ICONIFIED);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        System.exit(0);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String dep = lblcargo.getText();
        String nom = lblnombrerh.getText();
        try {
            RH_Inicio sele = new RH_Inicio();
            sele.setVisible(true);
            RH_Inicio.lblcargo.setText(dep);
            RH_Inicio.lblnombrerh.setText(nom);
            this.setVisible(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jLabel11MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_jLabel11MousePressed

    private void jLabel11MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseDragged
        this.setLocation(this.getLocation().x + evt.getX() - x, this.getLocation().y + evt.getY() - y);
    }//GEN-LAST:event_jLabel11MouseDragged

    private void itemPercepcionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemPercepcionesActionPerformed
        try {
            int fila = tbsinIncidencias.getSelectedRow();
            if (fila != -1) {
                String nom = tbsinIncidencias.getValueAt(fila, 1).toString();
                String idemp = tbsinIncidencias.getValueAt(fila, 0).toString();
                RH_SelectPD1 per = new RH_SelectPD1();
                RH_SelectPD1.lblcod.setText(idemp);
                RH_SelectPD1.lblnombre.setText(nom);
                per.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Selecciona una fila");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR EN: " + e);
        }
    }//GEN-LAST:event_itemPercepcionesActionPerformed

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
        txtBuscar.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                String cadena = (txtBuscar.getText()).toUpperCase();
                txtBuscar.setText(cadena);
                repaint();
                filtroBusqueda(txtBuscar);
            }
        });
        trsFiltro = new TableRowSorter(tbsinIncidencias.getModel());
        tbsinIncidencias.setRowSorter(trsFiltro);

    }//GEN-LAST:event_txtBuscarKeyTyped

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
            java.util.logging.Logger.getLogger(RH_UsuariosSinIncidencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RH_UsuariosSinIncidencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RH_UsuariosSinIncidencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RH_UsuariosSinIncidencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new RH_UsuariosSinIncidencias().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(RH_UsuariosSinIncidencias.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbDepto;
    public static javax.swing.JComboBox cmbSemana;
    private javax.swing.JMenuItem itemDetalles;
    private javax.swing.JMenuItem itemPercepciones;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    public static javax.swing.JLabel lblcargo;
    public static javax.swing.JLabel lblnombrerh;
    private javax.swing.JPanel panelincidencias;
    private javax.swing.JPopupMenu pmAutorizar;
    private javax.swing.JTable tbsinIncidencias;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables

}
