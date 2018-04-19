/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.EJefes;
import java.util.ArrayList;
import BD.Rjefes;
import BD.RegistrarIncidencia;
import javax.swing.DefaultComboBoxModel;
import Conexion.Conexion;
import Controller.EIncidencia;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import View.Incidenciasgrupales;

/**
 *
 * @author Vertsequer
 */
public class JA_inicio extends javax.swing.JFrame {

      private TableRowSorter trsFiltro;
      int x, y;
      Rjefes rjf;
      RegistrarIncidencia ric;
      Incidenciasgrupales ing;
      JA_newincidencia inc;
      int ultimoIndiceSeleccionado = 0;
      DefaultComboBoxModel<EJefes> modelosemanas;
      public static ResultSet rs;
      private Connection userConn;
      private PreparedStatement st;
      Conexion con = new Conexion();
      Connection conn;
      PreparedStatement stmt;
      DefaultTableModel modeloincidenciasjefe = new DefaultTableModel(null, getColumas());

      public JA_inicio() {
            ing = new Incidenciasgrupales();
            inc = new JA_newincidencia();
            ric = new RegistrarIncidencia();
            rjf = new Rjefes();
            modelosemanas = new DefaultComboBoxModel<EJefes>();
            cargarModeloSem();
            initComponents();
            this.setResizable(false);
            this.setLocationRelativeTo(null);
            this.getContentPane().setBackground(new java.awt.Color(51, 102, 255));
          tbIncidencias.getColumnModel().getColumn(0).setMaxWidth(80);
          tbIncidencias.getColumnModel().getColumn(1).setMaxWidth(60);
          tbIncidencias.getColumnModel().getColumn(2).setMaxWidth(240);
          tbIncidencias.getColumnModel().getColumn(3).setMaxWidth(150);
          tbIncidencias.getColumnModel().getColumn(4).setMaxWidth(150);
          tbIncidencias.getColumnModel().getColumn(5).setMaxWidth(150);
          tbIncidencias.getColumnModel().getColumn(6).setMaxWidth(150);
          tbIncidencias.getColumnModel().getColumn(7).setMaxWidth(150);
          tbIncidencias.getColumnModel().getColumn(8).setMaxWidth(150);
          tbIncidencias.getColumnModel().getColumn(9).setMaxWidth(150);
      }
      
//public void modificarculumnas(String fechaL,String fechaM,String FechaMi,String FechaJ, String FechaV,String FechaS,String fechaD ){
//tbIncidencias.getColumn("Lunes").setHeaderValue("Lunes"+"  "+fechaL);
//tbIncidencias.getColumn("Martes").setHeaderValue("Martes"+"  "+fechaM); 
//tbIncidencias.getColumn("Miercoles").setHeaderValue("Miercoles"+"  "+FechaMi); 
//tbIncidencias.getColumn("Jueves").setHeaderValue("Jueves"+"  "+FechaJ); 
//tbIncidencias.getColumn("Viernes").setHeaderValue("Viernes"+"  "+FechaV); 
//tbIncidencias.getColumn("Sabado").setHeaderValue("Sabado"+"  "+FechaS); 
//tbIncidencias.getColumn("Domingo").setHeaderValue("Domingo"+"  "+fechaD);
//}

      private void cargarModeloSem() {
            ArrayList<EJefes> listaSemanas;
            listaSemanas = rjf.obtenerSemanas();

            for (EJefes semana : listaSemanas) {
                  modelosemanas.addElement(semana);
            }
      }

      private String[] getColumas() {
   
  
            String columna[] = {"Actualizado", "ID", "Nombre", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"};
            return columna;

      }

      public void filtroBusqueda(JTextField txt) {
            trsFiltro.setRowFilter(RowFilter.regexFilter(txt.getText()));
      }

      public void SetFilas() {

            EJefes idsem = (EJefes) cmbSemana.getSelectedItem();

            try {
                  String sql;
                  sql = "SELECT emp.empleadoId ,\n"
                          + "emp.nombre, \n"
                          + "       STUFF(( SELECT ','+nomin.nombre \n"
                          + "               FROM  incidencias inc\n"
                          + "        INNER JOIN NomIncidencia nomin\n"
                          + "ON nomin.idNomIncidencia = inc.idNomIncidencia\n"
                          + "               WHERE\n"
                          + "                 emp.empleadoId = inc.empleadoId\n"
                          + "and  	inc.idSemana = '" + idsem.getIdSemana() + "'\n"
                          + "               order by fecha \n"
                          + "               FOR XML PATH('')\n"
                          + "             ) , 1, 1, '' )as datos,\n"
                          + "\n"
                          + "               STUFF(( SELECT ','+inc.dia\n"
                          + "               FROM  incidencias inc\n"
                          + "        INNER JOIN NomIncidencia nomin\n"
                          + "ON nomin.idNomIncidencia = inc.idNomIncidencia\n"
                          + "and  	inc.idSemana = '" + idsem.getIdSemana() + "'   WHERE\n"
                          + "                 emp.empleadoId = inc.empleadoId\n"
                          + "               order by fecha \n"
                          + "               FOR XML PATH('')\n"
                          + "             ) , 1, 1, '' )as dias\n"
                          + "\n"
                          + "  FROM  	empleados emp";
                  conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
                  stmt = conn.prepareStatement(sql);
                  rs = stmt.executeQuery();
                  Object datos[] = new Object[10];
                  while (rs.next()) {

                        String FLunesInc = "";
                        String FMartesInc = "";
                        String FMiercolesInc = "";
                        String FJuevesInc = "";
                        String FViernesInc = "";
                        String FSabadoInc = "";
                        String FDomingoInc = "";

                        String DL = "", DMA = "", DMI = "", DJ = "", DV = "", DS = "", DD = "";
                        String LunesInc = "";
                        String MartesInc = "";
                        String MiercolesInc = "";
                        String JuevesInc = "";
                        String ViernesInc = "";
                        String SabadoInc = "";
                        String DomingoInc = "";

                        if (rs.getString("datos") == null || rs.getString("datos") == "") {
                              String empid = rs.getString("empleadoId");
                              String nom = rs.getString("nombre");

                        } else {
                              String empid = rs.getString("empleadoId");
                              String nom = rs.getString("nombre");
                              String Dias = rs.getString("datos");
                              String Fechas = rs.getString("dias");
                              if (Dias.contains(",") || Fechas.contains(",")) {

                                    String[] days = Dias.split(",");
                                    String[] dates = Fechas.split(",");

                                    if (0 == days.length && 0 == dates.length) {
                                          Dias += ", , , , , , , ,";
                                          Fechas += ", , , , , , , ,";
                                    }
                                    if (1 == days.length && 1 == dates.length) {
                                          Dias += " , , , , , , ,";
                                          Fechas += " , , , , , , ,";
                                    }
                                    if (2 == days.length && 2 == dates.length) {
                                          Dias += " , , , , , ,";
                                          Fechas += " , , , , , ,";

                                    }
                                    if (3 == days.length && 3 == dates.length) {
                                          Dias += " , , , , ,";
                                          Fechas += " , , , , ,";

                                    }
                                    if (4 == days.length && 4 == dates.length) {
                                          Dias += " , , , ,";
                                          Fechas += " , , , ,";

                                    }
                                    if (5 == days.length && 5 == dates.length) {
                                          Dias += " , , ,";
                                          Fechas += " , , ,";

                                    }
                                    if (6 == days.length && 6 == dates.length) {
                                          Dias += " , ,";
                                          Fechas += " , ,";

                                    }

                                    days = Dias.split(",");
                                    dates = Fechas.split(",");
                                    FLunesInc = dates[0];
                                    FMartesInc = dates[1];
                                    FMiercolesInc = dates[2];
                                    FJuevesInc = dates[3];
                                    FViernesInc = dates[4];
                                    FSabadoInc = dates[5];
                                    FDomingoInc = dates[6];
                                    FLunesInc = FLunesInc.replaceAll(" ", "");
                                    FMartesInc = FMartesInc.replaceAll(" ", "");
                                    FMiercolesInc = FMiercolesInc.replaceAll(" ", "");
                                    FJuevesInc = FJuevesInc.replaceAll(" ", "");
                                    FViernesInc = FViernesInc.replaceAll(" ", "");
                                    FSabadoInc = FSabadoInc.replaceAll(" ", "");
                                    FDomingoInc = FDomingoInc.replaceAll(" ", "");

//                            ¿
                                    //lunes
                                    if (FLunesInc.equalsIgnoreCase("L")) {
                                          LunesInc = days[0];

                                    } else if (FLunesInc.equalsIgnoreCase("Ma")) {
                                          MartesInc = days[0];

                                    } else if (FLunesInc.equalsIgnoreCase("Mi")) {
                                          MiercolesInc = days[0];
                                    } else if (FLunesInc.equalsIgnoreCase("J")) {
                                          JuevesInc = days[0];

                                    } else if (FLunesInc.equalsIgnoreCase("V")) {
                                          ViernesInc = days[0];
                                    } else if (FLunesInc.equalsIgnoreCase("S")) {
                                          SabadoInc = days[0];
                                    } else if (FLunesInc.equalsIgnoreCase("D")) {
                                          DomingoInc = days[0];
                                    }
                                    //martes        
                                    if (FMartesInc.equalsIgnoreCase("L")) {
                                          LunesInc = days[1];

                                    } else if (FMartesInc.equalsIgnoreCase("Ma")) {
                                          MartesInc = days[1];

                                    } else if (FMartesInc.equalsIgnoreCase("Mi")) {
                                          MiercolesInc = days[1];
                                    } else if (FMartesInc.equalsIgnoreCase("J")) {
                                          JuevesInc = days[1];

                                    } else if (FMartesInc.equalsIgnoreCase("V")) {
                                          ViernesInc = days[1];
                                    } else if (FMartesInc.equalsIgnoreCase("S")) {
                                          SabadoInc = days[1];
                                    } else if (FMartesInc.equalsIgnoreCase("D")) {
                                          DomingoInc = days[1];
                                    }

                                    //miercoles 
                                    if (FMiercolesInc.equalsIgnoreCase("L")) {
                                          LunesInc = days[2];

                                    } else if (FMiercolesInc.equalsIgnoreCase("Ma")) {
                                          MartesInc = days[2];

                                    } else if (FMiercolesInc.equalsIgnoreCase("Mi")) {
                                          MiercolesInc = days[2];
                                    } else if (FMiercolesInc.equalsIgnoreCase("J")) {
                                          JuevesInc = days[2];

                                    } else if (FMiercolesInc.equalsIgnoreCase("V")) {
                                          ViernesInc = days[2];
                                    } else if (FMiercolesInc.equalsIgnoreCase("S")) {
                                          SabadoInc = days[2];
                                    } else if (FMiercolesInc.equalsIgnoreCase("D")) {
                                          DomingoInc = days[2];
                                    }

                                    //jueves
                                    if (FJuevesInc.equalsIgnoreCase("L")) {
                                          LunesInc = days[3];

                                    } else if (FJuevesInc.equalsIgnoreCase("Ma")) {
                                          MartesInc = days[3];

                                    } else if (FJuevesInc.equalsIgnoreCase("Mi")) {
                                          MiercolesInc = days[3];
                                    } else if (FJuevesInc.equalsIgnoreCase("J")) {
                                          JuevesInc = days[3];

                                    } else if (FJuevesInc.equalsIgnoreCase("V")) {
                                          ViernesInc = days[3];
                                    } else if (FJuevesInc.equalsIgnoreCase("S")) {
                                          SabadoInc = days[3];
                                    } else if (FJuevesInc.equalsIgnoreCase("D")) {
                                          DomingoInc = days[3];
                                    }

                                    //viernes 
                                    if (FViernesInc.equalsIgnoreCase("L")) {
                                          LunesInc = days[4];

                                    } else if (FViernesInc.equalsIgnoreCase("Ma")) {
                                          MartesInc = days[4];

                                    } else if (FViernesInc.equalsIgnoreCase("Mi")) {
                                          MiercolesInc = days[4];
                                    } else if (FViernesInc.equalsIgnoreCase("J")) {
                                          JuevesInc = days[4];

                                    } else if (FViernesInc.equalsIgnoreCase("V")) {
                                          ViernesInc = days[4];
                                    } else if (FViernesInc.equalsIgnoreCase("S")) {
                                          SabadoInc = days[4];
                                    } else if (FViernesInc.equalsIgnoreCase("D")) {
                                          DomingoInc = days[4];
                                    }

                                    //sabado
                                    if (FSabadoInc.equalsIgnoreCase("L")) {
                                          LunesInc = days[5];
                                    } else if (FSabadoInc.equalsIgnoreCase("Ma")) {
                                          MartesInc = days[5];

                                    } else if (FSabadoInc.equalsIgnoreCase("Mi")) {
                                          MiercolesInc = days[5];
                                    } else if (FSabadoInc.equalsIgnoreCase("J")) {
                                          JuevesInc = days[5];

                                    } else if (FSabadoInc.equalsIgnoreCase("V")) {
                                          ViernesInc = days[5];
                                    } else if (FSabadoInc.equalsIgnoreCase("S")) {
                                          SabadoInc = days[5];
                                    } else if (FSabadoInc.equalsIgnoreCase("D")) {
                                          DomingoInc = days[5];
                                    }

                                    //domingo
                                    if (FDomingoInc.equalsIgnoreCase("L")) {
                                          LunesInc = days[6];

                                    } else if (FDomingoInc.equalsIgnoreCase("Ma")) {
                                          MartesInc = days[6];

                                    } else if (FDomingoInc.equalsIgnoreCase("Mi")) {
                                          MiercolesInc = days[6];
                                    } else if (FDomingoInc.equalsIgnoreCase("J")) {
                                          JuevesInc = days[6];

                                    } else if (FDomingoInc.equalsIgnoreCase("V")) {
                                          ViernesInc = days[6];
                                    } else if (FDomingoInc.equalsIgnoreCase("S")) {
                                          SabadoInc = days[6];
                                    } else if (FDomingoInc.equalsIgnoreCase("D")) {
                                          DomingoInc = days[6];
                                    }

                              } else {

                                    String[] days = Dias.split(",");
                                    String[] dates = Fechas.split(",");

                                    if (0 == days.length && 0 == dates.length) {
                                          Dias += ", , , , , , , ,";
                                          Fechas += ", , , , , , , ,";
                                    }
                                    if (1 == days.length && 1 == dates.length) {
                                          Dias += " , , , , , , ,";
                                          Fechas += " , , , , , , ,";
                                    }
                                    if (2 == days.length && 2 == dates.length) {
                                          Dias += " , , , , , ,";
                                          Fechas += " , , , , , ,";

                                    }
                                    if (3 == days.length && 3 == dates.length) {
                                          Dias += " , , , , ,";
                                          Fechas += " , , , , ,";

                                    }
                                    if (4 == days.length && 4 == dates.length) {
                                          Dias += " , , , ,";
                                          Fechas += " , , , ,";

                                    }
                                    if (5 == days.length && 5 == dates.length) {
                                          Dias += " , , ,";
                                          Fechas += " , , ,";

                                    }
                                    if (6 == days.length && 6 == dates.length) {
                                          Dias += " , ,";
                                          Fechas += " , ,";

                                    }

                                    days = Dias.split(",");
                                    dates = Fechas.split(",");
                                    FLunesInc = dates[0];
                                    FMartesInc = dates[1];
                                    FMiercolesInc = dates[2];
                                    FJuevesInc = dates[3];
                                    FViernesInc = dates[4];
                                    FSabadoInc = dates[5];
                                    FDomingoInc = dates[6];
                                    FLunesInc = FLunesInc.replaceAll(" ", "");
                                    FMartesInc = FMartesInc.replaceAll(" ", "");
                                    FMiercolesInc = FMiercolesInc.replaceAll(" ", "");
                                    FJuevesInc = FJuevesInc.replaceAll(" ", "");
                                    FViernesInc = FViernesInc.replaceAll(" ", "");
                                    FSabadoInc = FSabadoInc.replaceAll(" ", "");
                                    FDomingoInc = FDomingoInc.replaceAll(" ", "");

//                            ¿
                                    //lunes
                                    if (FLunesInc.equalsIgnoreCase("L")) {
                                          LunesInc = days[0];

                                    } else if (FLunesInc.equalsIgnoreCase("Ma")) {
                                          MartesInc = days[0];

                                    } else if (FLunesInc.equalsIgnoreCase("Mi")) {
                                          MiercolesInc = days[0];
                                    } else if (FLunesInc.equalsIgnoreCase("J")) {
                                          JuevesInc = days[0];

                                    } else if (FLunesInc.equalsIgnoreCase("V")) {
                                          ViernesInc = days[0];
                                    } else if (FLunesInc.equalsIgnoreCase("S")) {
                                          SabadoInc = days[0];
                                    } else if (FLunesInc.equalsIgnoreCase("D")) {
                                          DomingoInc = days[0];
                                    }
                                    //martes        
                                    if (FMartesInc.equalsIgnoreCase("L")) {
                                          LunesInc = days[1];

                                    } else if (FMartesInc.equalsIgnoreCase("Ma")) {
                                          MartesInc = days[1];

                                    } else if (FMartesInc.equalsIgnoreCase("Mi")) {
                                          MiercolesInc = days[1];
                                    } else if (FLunesInc.equalsIgnoreCase("J")) {
                                          JuevesInc = days[1];

                                    } else if (FMartesInc.equalsIgnoreCase("V")) {
                                          ViernesInc = days[1];
                                    } else if (FMartesInc.equalsIgnoreCase("S")) {
                                          SabadoInc = days[1];
                                    } else if (FMartesInc.equalsIgnoreCase("D")) {
                                          DomingoInc = days[1];
                                    }

                                    //miercoles 
                                    if (FMiercolesInc.equalsIgnoreCase("L")) {
                                          LunesInc = days[2];

                                    } else if (FMiercolesInc.equalsIgnoreCase("Ma")) {
                                          MartesInc = days[2];

                                    } else if (FMiercolesInc.equalsIgnoreCase("Mi")) {
                                          MiercolesInc = days[2];
                                    } else if (FLunesInc.equalsIgnoreCase("J")) {
                                          JuevesInc = days[2];

                                    } else if (FMiercolesInc.equalsIgnoreCase("V")) {
                                          ViernesInc = days[2];
                                    } else if (FMiercolesInc.equalsIgnoreCase("S")) {
                                          SabadoInc = days[2];
                                    } else if (FMiercolesInc.equalsIgnoreCase("D")) {
                                          DomingoInc = days[2];
                                    }

                                    //jueves
                                    if (FJuevesInc.equalsIgnoreCase("L")) {
                                          LunesInc = days[3];

                                    } else if (FJuevesInc.equalsIgnoreCase("Ma")) {
                                          MartesInc = days[3];

                                    } else if (FJuevesInc.equalsIgnoreCase("Mi")) {
                                          MiercolesInc = days[3];
                                    } else if (FJuevesInc.equalsIgnoreCase("J")) {
                                          JuevesInc = days[3];

                                    } else if (FJuevesInc.equalsIgnoreCase("V")) {
                                          ViernesInc = days[3];
                                    } else if (FJuevesInc.equalsIgnoreCase("S")) {
                                          SabadoInc = days[3];
                                    } else if (FJuevesInc.equalsIgnoreCase("D")) {
                                          DomingoInc = days[3];
                                    }

                                    //viernes 
                                    if (FViernesInc.equalsIgnoreCase("L")) {
                                          LunesInc = days[4];

                                    } else if (FViernesInc.equalsIgnoreCase("Ma")) {
                                          MartesInc = days[4];

                                    } else if (FViernesInc.equalsIgnoreCase("Mi")) {
                                          MiercolesInc = days[4];
                                    } else if (FViernesInc.equalsIgnoreCase("J")) {
                                          JuevesInc = days[4];

                                    } else if (FViernesInc.equalsIgnoreCase("V")) {
                                          ViernesInc = days[4];
                                    } else if (FViernesInc.equalsIgnoreCase("S")) {
                                          SabadoInc = days[4];
                                    } else if (FViernesInc.equalsIgnoreCase("D")) {
                                          DomingoInc = days[4];
                                    }

                                    //sabado
                                    if (FSabadoInc.equalsIgnoreCase("L")) {
                                          LunesInc = days[5];
                                    } else if (FSabadoInc.equalsIgnoreCase("Ma")) {
                                          MartesInc = days[5];

                                    } else if (FSabadoInc.equalsIgnoreCase("Mi")) {
                                          MiercolesInc = days[5];
                                    } else if (FSabadoInc.equalsIgnoreCase("J")) {
                                          JuevesInc = days[5];

                                    } else if (FSabadoInc.equalsIgnoreCase("V")) {
                                          ViernesInc = days[5];
                                    } else if (FSabadoInc.equalsIgnoreCase("S")) {
                                          SabadoInc = days[5];
                                    } else if (FSabadoInc.equalsIgnoreCase("D")) {
                                          DomingoInc = days[5];
                                    }

                                    //domingo
                                    if (FDomingoInc.equalsIgnoreCase("L")) {
                                          LunesInc = days[6];

                                    } else if (FDomingoInc.equalsIgnoreCase("Ma")) {
                                          MartesInc = days[6];

                                    } else if (FDomingoInc.equalsIgnoreCase("Mi")) {
                                          MiercolesInc = days[6];
                                    } else if (FDomingoInc.equalsIgnoreCase("J")) {
                                          JuevesInc = days[6];

                                    } else if (FDomingoInc.equalsIgnoreCase("V")) {
                                          ViernesInc = days[6];
                                    } else if (FDomingoInc.equalsIgnoreCase("S")) {
                                          SabadoInc = days[6];
                                    } else if (FDomingoInc.equalsIgnoreCase("D")) {
                                          DomingoInc = days[6];
                                    }

                              }

                        }

                        datos[1] = rs.getString("empleadoId");
                        datos[2] = rs.getString("nombre");
                        datos[3] = LunesInc;
                        datos[4] = MartesInc;
                        datos[5] = MiercolesInc;
                        datos[6] = JuevesInc;
                        datos[7] = ViernesInc;
                        datos[8] = SabadoInc;
                        datos[9] = DomingoInc;

                        modeloincidenciasjefe.addRow(datos);
                  }

//                        tabla1.addRow(datos);
            } catch (Exception e) {
                  System.out.println("" + e);
            } finally {
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
       * This method is called from within the constructor to initialize the
       * form. WARNING: Do NOT modify this code. The content of this method is
       * always regenerated by the Form Editor.
       */
      @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        pmiRegistrar = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        lblnombrejefe = new javax.swing.JTextField();
        lblcargojefe = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbIncidencias = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cmbSemana = new javax.swing.JComboBox();

        pmiRegistrar.setText("Insertar Incidencia");
        pmiRegistrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pmiRegistrarMouseClicked(evt);
            }
        });
        pmiRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pmiRegistrarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(pmiRegistrar);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 102, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1362, 555));

        jPanel1.setBackground(new java.awt.Color(229, 230, 234));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/prenomina/portafolio.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, -1, 40));

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
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 0, 32, 30));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/prenomina/error.png"))); // NOI18N
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1330, 0, 32, 30));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/prenomina/regresar.png"))); // NOI18N
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1290, 0, 32, 30));

        jSeparator2.setBackground(new java.awt.Color(51, 102, 255));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 40, 230, 10));

        jSeparator3.setBackground(new java.awt.Color(51, 102, 255));
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, 230, 10));

        jLabel6.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabel6MouseDragged(evt);
            }
        });
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel6MousePressed(evt);
            }
        });
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1360, 50));

        lblnombrejefe.setBackground(new java.awt.Color(229, 230, 234));
        lblnombrejefe.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblnombrejefe.setForeground(new java.awt.Color(51, 102, 255));
        lblnombrejefe.setAutoscrolls(false);
        lblnombrejefe.setBorder(null);
        lblnombrejefe.setCaretColor(new java.awt.Color(51, 102, 255));
        jPanel1.add(lblnombrejefe, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 230, 20));

        lblcargojefe.setBackground(new java.awt.Color(229, 230, 234));
        lblcargojefe.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblcargojefe.setForeground(new java.awt.Color(51, 102, 255));
        lblcargojefe.setToolTipText("");
        lblcargojefe.setBorder(null);
        lblcargojefe.setCaretColor(new java.awt.Color(51, 102, 255));
        lblcargojefe.setDisabledTextColor(new java.awt.Color(51, 102, 255));
        lblcargojefe.setEnabled(false);
        jPanel1.add(lblcargojefe, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 20, 230, 20));

        jPanel2.setBackground(new java.awt.Color(51, 102, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(910, 610));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(51, 102, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator1.setAlignmentX(0.7F);
        jSeparator1.setAlignmentY(0.8F);
        jPanel4.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, 254, 21));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/prenomina/search1.png"))); // NOI18N
        jLabel4.setToolTipText("");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 40, 40));

        txtBuscar.setBackground(new java.awt.Color(51, 102, 255));
        txtBuscar.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        txtBuscar.setForeground(new java.awt.Color(255, 255, 255));
        txtBuscar.setBorder(null);
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
            }
        });
        jPanel4.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 250, 20));

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 730, 80));

        tbIncidencias.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        tbIncidencias.setModel(modeloincidenciasjefe);
        tbIncidencias.setComponentPopupMenu(jPopupMenu1);
        jScrollPane4.setViewportView(tbIncidencias);

        jPanel2.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 1360, 270));

        jLabel5.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Semana:");

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Seleciona Semana:");

        cmbSemana.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cmbSemana.setModel(modelosemanas);
        cmbSemana.setToolTipText("");
        cmbSemana.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbSemanaItemStateChanged(evt);
            }
        });
        cmbSemana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSemanaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(cmbSemana, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbSemana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(13, 13, 13)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
          System.exit(0);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
          this.setExtendedState(ICONIFIED);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
          Jflogin us = new Jflogin();
          us.show(true);
          this.show(false);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jLabel6MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseDragged
          this.setLocation(this.getLocation().x + evt.getX() - x, this.getLocation().y + evt.getY() - y);
    }//GEN-LAST:event_jLabel6MouseDragged

    private void jLabel6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MousePressed
          x = evt.getX();
          y = evt.getY();
    }//GEN-LAST:event_jLabel6MousePressed

    private void cmbSemanaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbSemanaItemStateChanged

    }//GEN-LAST:event_cmbSemanaItemStateChanged

    private void cmbSemanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSemanaActionPerformed
          limpiar(modeloincidenciasjefe);
          int sem = cmbSemana.getSelectedIndex();

          if (sem < 0) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar", "Verificar", JOptionPane.WARNING_MESSAGE);
          } else {

                String Semana = cmbSemana.getSelectedItem().toString();

                JA_newincidencia inc = new JA_newincidencia();
                inc.selecSeman(Semana);
                 EJefes semana = (EJefes) JA_inicio.cmbSemana.getSelectedItem();
//               modificarculumnas(semana.getFechaL(),semana.getFechaM(),semana.getFechaMi(),semana.getFechaJ(),semana.getFechaV(),semana.getFechaS(),semana.getFechaD());
                SetFilas();

          }
    }//GEN-LAST:event_cmbSemanaActionPerformed

    private void pmiRegistrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pmiRegistrarMouseClicked

    }//GEN-LAST:event_pmiRegistrarMouseClicked

    private void pmiRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pmiRegistrarActionPerformed

          int cuentaFilasSeleccionadas = tbIncidencias.getSelectedRowCount();

          System.out.println("Hay seleccionadas: " + cuentaFilasSeleccionadas + " filas");
          if (cuentaFilasSeleccionadas == 1) {
              int fila = tbIncidencias.getSelectedRow();
              String empid = tbIncidencias.getValueAt(fila, 1).toString();
              String nombre = tbIncidencias.getValueAt(fila, 2).toString();
              String Semana = cmbSemana.getSelectedItem().toString();
              String nombrejefe = lblnombrejefe.getText();
              String cargojefe = lblcargojefe.getText();
              inc.setVisible(true);
              inc.mostrardatos(empid, nombre, Semana, nombrejefe, cargojefe);
                  inc.botonesnew();
              this.setVisible(false);

          } else {

                TableModel model1 = tbIncidencias.getModel();
                int indexs[] = tbIncidencias.getSelectedRows();
                String Semana = cmbSemana.getSelectedItem().toString();
                ing.Semna.setText(Semana);
                Object[] row = new Object[4];

                DefaultTableModel model2 = (DefaultTableModel) ing.jtbdatosgrupos.getModel();

                for (int i = 0; i < indexs.length; i++) {
                      row[0] = model1.getValueAt(indexs[i], 1);
                      row[1] = model1.getValueAt(indexs[i], 2);

                      model2.addRow(row);
                }

                ing.selecSeman(Semana);
                ing.setVisible(true);

          }

    }//GEN-LAST:event_pmiRegistrarActionPerformed

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
                  java.util.logging.Logger.getLogger(JA_inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                  java.util.logging.Logger.getLogger(JA_inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                  java.util.logging.Logger.getLogger(JA_inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                  java.util.logging.Logger.getLogger(JA_inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        //</editor-fold>
            //</editor-fold>
        //</editor-fold>
            //</editor-fold>

            /* Create and display the form */
            java.awt.EventQueue.invokeLater(new Runnable() {
                  public void run() {
                        new JA_inicio().setVisible(true);
                  }
            });
      }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JComboBox cmbSemana;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    public static javax.swing.JTextField lblcargojefe;
    public static javax.swing.JTextField lblnombrejefe;
    private javax.swing.JMenuItem pmiRegistrar;
    private javax.swing.JTable tbIncidencias;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
