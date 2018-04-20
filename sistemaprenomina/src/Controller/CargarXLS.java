/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Conexion.Conexion1;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import jxl.read.biff.BiffException;
import java.io.FileInputStream;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane;
import jxl.Sheet;
import jxl.Workbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Programacion 2
 */
public class CargarXLS {

      public static ResultSet rs;

      private Connection userConn;
      String nombre;
      String employedId;
      String puesto;
      String depto;
      String fecha;
      String horas;
      String entrada;
      String Salida;
      String estatus = "1";
      String comid;
      String comfe;
      public static Vector<String> arrayfecha = new Vector<>();
      public static Vector<String> arrayidemp = new Vector<String>();
      public static Vector<String> arrayid = new Vector<String>();
      public static Vector<String> arraynombre = new Vector<String>();

      public void cargarempleados() throws IOException, SQLException, ParseException, BiffException {

            Connection conn = null;
            Conexion1 con = new Conexion1();
            PreparedStatement stmt = null;

            int rows = 0;
            FileNameExtensionFilter filter1 = new FileNameExtensionFilter("Doc - MS-Office 2003", "xls");
//            FileNameExtensionFilter filter2 = new FileNameExtensionFilter("Docx - MS-Office 2007", "xlsx");
            JFileChooser explorador = new JFileChooser();
            explorador.setDialogTitle("Abrir documento...");
            explorador.setFileFilter(filter1);
            

            int seleccion = explorador.showDialog(null, "Abrir!");

//analizamos la respuesta
            switch (seleccion) {
                  case JFileChooser.APPROVE_OPTION:
                        //seleccionó abrir
                        break;

                  case JFileChooser.CANCEL_OPTION:
                        System.out.println("Se cancelo la operacion");
                        break;

                  case JFileChooser.ERROR_OPTION:
                        //llega aqui si sucede un error
                        break;
            }

            File archivo = explorador.getSelectedFile();

//y guardar una ruta
            String ruta = archivo.getPath();

             if (explorador.getFileFilter() == filter1) {
                  Boolean com = false;
                  Boolean fin = false;


                  try {
                        Workbook arch = Workbook.getWorkbook(new File(ruta));
                        for (int hojas = 0; hojas < arch.getNumberOfSheets(); hojas++) {
                              Sheet hoja = arch.getSheet(hojas);
                              int numfila = hoja.getRows();

                              for (int fila = 1; fila < numfila; fila++) {

                                    nombre = hoja.getCell(0, fila).getContents();
                                    employedId = hoja.getCell(1, fila).getContents();
                                    puesto = hoja.getCell(2, fila).getContents();
                                    depto = hoja.getCell(3, fila).getContents();
                                 
                                    if (hoja.getCell(1, fila).getContents().length() == 0) {
                                          employedId = "0";
                                    }

                                    int idempleado = Integer.parseInt(employedId);
                                    String nomemp=nombre;
                                    listaemp();

                                    com = arrayidemp.contains(String.valueOf(idempleado));
                                    if (idempleado == 0) {
                                        
                                          com = false;
                                           for (int j = 0; j < arrayidemp.size(); j++) {
                                          fin = arrayidemp.elementAt(j).equals(String.valueOf(idempleado)) && arraynombre.elementAt(j).equals(nomemp);
                                          if (fin) {
                                                com = true;
                                          }
                                    }
                                          
                                    }

                                   

                                    if (com) {
                                          System.out.println("ETE REGISTRO YA EXISTE EN LA BASE ");
                                    } else {

                                          conn = (this.userConn != null) ? this.userConn : Conexion1.getConnection();
                                          

                                          String sentencia = "INSERT INTO empleados( empleadoId, nombre, estatus, puesto, depto) VALUES ('" + employedId + "','" + nombre + "','" + estatus + "','" + puesto + "','" + depto + "')";
                                          PreparedStatement pst = conn.prepareStatement(sentencia);
                                          pst.executeUpdate();
                                          conn.close();
                                    }
                              }
                        }

                  } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Error en: " + e);
                  } finally {
                        Conexion1.close(stmt);
                        if (this.userConn == null) {
                              Conexion1.close(conn);
                        }

                  }

            }

      }

      public void cargarregistros() throws IOException, SQLException, ParseException, BiffException {

            Connection conn = null;
            Conexion1 con = new Conexion1();
            PreparedStatement stmt = null;

            int rows = 0;
            FileNameExtensionFilter filter1 = new FileNameExtensionFilter("Doc - MS-Office 2003", "xls");
//            FileNameExtensionFilter filter2 = new FileNameExtensionFilter("Docx - MS-Office 2007", "xlsx");
            JFileChooser explorador = new JFileChooser();
            explorador.setDialogTitle("Abrir documento...");
            explorador.setFileFilter(filter1);
//            explorador.setFileFilter(filter2);

            int seleccion = explorador.showDialog(null, "Abrir!");

//analizamos la respuesta
            switch (seleccion) {
                  case JFileChooser.APPROVE_OPTION:
                        //seleccionó abrir
                        break;

                  case JFileChooser.CANCEL_OPTION:
                        System.out.println("Se cancelo la operacion");
                        break;

                  case JFileChooser.ERROR_OPTION:
                        //llega aqui si sucede un error
                        break;
            }

            File archivo = explorador.getSelectedFile();

//y guardar una ruta
            String ruta = archivo.getPath();

         if (explorador.getFileFilter() == filter1) {
                  Boolean com = false;

                  listare();

                  String fechafinal;
//                   System.out.println(filter1.getDescription() + "**"); 

                  try {
                        Workbook arch = Workbook.getWorkbook(new File(ruta));
                        for (int hojas = 0; hojas < arch.getNumberOfSheets(); hojas++) {
                              Sheet hoja = arch.getSheet(hojas);
                              int numfila = hoja.getRows();

                              for (int fila = 1; fila < numfila; fila++) {
                                    if (hoja.getCell(4, fila).getContents().length() == 0) {
                                          fechafinal = "1111-11-11";
                                    } else {

                                          SimpleDateFormat d = new SimpleDateFormat("dd/MM/yy");
                                          Date fe = d.parse(hoja.getCell(4, fila).getContents());

                                          SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");

                                          fechafinal = formatoDeFecha.format(fe);
                                    }
                                    Boolean fin = false;

                                    employedId = hoja.getCell(1, fila).getContents();

                                    fecha = fechafinal;
                                    horas = hoja.getCell(5, fila).getContents();
                                    entrada = hoja.getCell(6, fila).getContents();
                                    Salida = hoja.getCell(7, fila).getContents();

                                    if (hoja.getCell(1, fila).getContents().length() == 0) {
                                          employedId = "0";

                                    }
                                    int idempleado = Integer.parseInt(employedId);
                                    for (int j = 0; j < arrayid.size(); j++) {
                                          com = arrayid.elementAt(j).equals(String.valueOf(idempleado)) && arrayfecha.elementAt(j).equals(fecha);
                                          if (com) {
                                                fin = true;
                                          }
                                    }
                                    
                                    

                                    if (fin) {
                                          System.out.println("ETE REGISTRO YA EXISTE EN LA BASE ");
                                    } else {

                                          conn = (this.userConn != null) ? this.userConn : Conexion1.getConnection();
                                         

                                          String sentencia = "INSERT INTO registros(empleadoId, Entrada, Salida, horas, fecha) VALUES ('" + employedId + "','" + entrada + "','" + Salida + "','" + horas + "','" + fecha + "')";
                                          PreparedStatement pst = conn.prepareStatement(sentencia);
                                          pst.executeUpdate();
                                          conn.close();
                                    }
                              }
                        }

                  } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Error en: " + e);
                  } finally {
                        Conexion1.close(stmt);
                        if (this.userConn == null) {
                              Conexion1.close(conn);
                        }

                  }

            }

      }

      public Vector<String> listare() throws SQLException {
            Connection conn = null;
            PreparedStatement stmt1 = null;

            conn = (this.userConn != null) ? this.userConn : Conexion1.getConnection();
            String sql1 = "select * from registros";

            stmt1 = conn.prepareStatement(sql1);

            rs = stmt1.executeQuery();

            int con = 0;

            while (rs.next()) {

                  arrayid.add(con, rs.getString("empleadoId"));
                  arrayfecha.add(con, rs.getString("fecha"));
                  con++;
//                                      

            }

       

//                                   
            return arrayid;

      }

      public Vector<String> listaemp() throws SQLException {
            Connection conn = null;
            PreparedStatement stmt1 = null;

            conn = (this.userConn != null) ? this.userConn : Conexion1.getConnection();
            String sql1 = "select * from empleados";

            stmt1 = conn.prepareStatement(sql1);

            rs = stmt1.executeQuery();

            int con = 0;

            while (rs.next()) {

                  arrayidemp.add(con, rs.getString("empleadoId"));
                  arraynombre.add(con, rs.getString("nombre"));

                  con++;
//                                      

            }

           
            return arrayidemp;

      }

}
