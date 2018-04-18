/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Conexion.Conexion1;
//import static View.RH_UsuariosConIncidencias.rs;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.*;
import jxl.write.biff.RowsExceededException;
import jxl.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Programacion 2
 */
public class exportReporte {

      Connection conn;
      Connection conn1;
      PreparedStatement stmt;
      public static ResultSet rs;
      PreparedStatement stmt1;
      public static ResultSet rs1;
      private Connection userConn;
      private Connection userConn1;
      boolean dom = false;
      boolean lu = false;
      boolean ma = false;
      boolean mi = false;
      boolean ju = false;
      boolean vi = false;
      boolean sa = false;

      public void cargardatosFiltroSemana(int idSemana) throws SQLException {
            org.apache.poi.ss.usermodel.Workbook book = new XSSFWorkbook();
            org.apache.poi.ss.usermodel.Sheet sheet = book.createSheet("REPORTE INCIDENCIAS");
            Row row = sheet.createRow(0);
            row = sheet.createRow(2);
            row.createCell(4).setCellValue("LUNES");
            row.createCell(8).setCellValue("MARTES");
            row.createCell(12).setCellValue("MIERCOLES");
            row.createCell(16).setCellValue("JUEVEZ");
            row.createCell(20).setCellValue("VIERNES");
            row.createCell(24).setCellValue("SABADO");
            row.createCell(28).setCellValue("DOMINGO");

            row = sheet.createRow(3);
            row.createCell(0).setCellValue("ID");
            row.createCell(1).setCellValue("NOMBRE");
            row.createCell(2).setCellValue("DEPARTAMENTO");
            row.createCell(3).setCellValue("PUESTO");
            row.createCell(4).setCellValue("FECHA");
            row.createCell(5).setCellValue("HORAS TRABAJADAS");
            row.createCell(6).setCellValue("INCIDENCIA");
            row.createCell(7).setCellValue("COMENTARIO");
            row.createCell(8).setCellValue("FECHA");
            row.createCell(9).setCellValue("HORAS TRABAJADAS");
            row.createCell(10).setCellValue("INCIDENCIA");
            row.createCell(11).setCellValue("COMENTARIO");
            row.createCell(12).setCellValue("FECHA");
            row.createCell(13).setCellValue("HORAS TRABAJADAS");
            row.createCell(14).setCellValue("INCIDENCIA");
            row.createCell(15).setCellValue("COMENTARIO");
            row.createCell(16).setCellValue("FECHA");
            row.createCell(17).setCellValue("HORAS TRABAJADAS");
            row.createCell(18).setCellValue("INCIDENCIA");
            row.createCell(19).setCellValue("COMENTARIO");
            row.createCell(20).setCellValue("FECHA");
            row.createCell(21).setCellValue("HORAS TRABAJADAS");
            row.createCell(22).setCellValue("INCIDENCIA");
            row.createCell(23).setCellValue("COMENTARIO");
            row.createCell(24).setCellValue("FECHA");
            row.createCell(25).setCellValue("HORAS TRABAJADAS");
            row.createCell(26).setCellValue("INCIDENCIA");
            row.createCell(27).setCellValue("COMENTARIO");
            row.createCell(28).setCellValue("FECHA");
            row.createCell(29).setCellValue("HORAS TRABAJADAS");
            row.createCell(30).setCellValue("INCIDENCIA");
            row.createCell(31).setCellValue("COMENTARIO");

            String sql = "SELECT DISTINCT  emp.empleadoId, emp.nombre, emp.depto, emp.puesto\n"
                    + "                    from incidencias inc\n"
                    + "                    INNER JOIN empleados emp on inc.empleadoId= emp.empleadoId\n"
                    + "                    INNER JOIN semanas se on inc.idSemana= se.idSemana\n"
                    + "                    where inc.idSemana='" + idSemana + "'";
            String datos[] = new String[11];
            try {
                  conn = (this.userConn != null) ? this.userConn : Conexion1.getConnection();
                  stmt = conn.prepareStatement(sql);
                  rs = stmt.executeQuery();
                  int fila = 4;
                  while (rs.next()) {

                        datos[0] = rs.getString("empleadoId");
                        datos[1] = rs.getString("nombre");
                        datos[2] = rs.getString("depto");
                        datos[3] = rs.getString("puesto");
                        
                        row = sheet.createRow(fila);
                        row.createCell(0).setCellValue(datos[0]);
                        row.createCell(1).setCellValue(datos[1]);
                        row.createCell(2).setCellValue(datos[2]);
                        row.createCell(3).setCellValue(datos[3]);

                        String sql1 = "SELECT inc.actualizadoJA, inc.actualizadoRH, emp.empleadoId, emp.nombre, inc.fecha, re.entrada, re.salida, inc.horasTrab, nomin.nombre AS nombreinc, inc.comentario \n"
                                + "from incidencias inc\n"
                                + "INNER JOIN empleados emp on inc.empleadoId= emp.empleadoId\n"
                                + "INNER JOIN registros re on inc.empleadoId=re.empleadoId \n"
                                + "INNER JOIN NomIncidencia nomin on  nomin.idNomIncidencia = inc.idNomIncidencia\n"
                                + "INNER JOIN semanas se on inc.idSemana= se.idSemana\n"
                                + "where inc.idSemana='" + idSemana + "' and inc.empleadoId='" + datos[0] + "' ";

                        try {
                              conn1 = (this.userConn1 != null) ? this.userConn1 : Conexion1.getConnection();
                              stmt1 = conn1.prepareStatement(sql1);
                              rs1 = stmt1.executeQuery();

                              while (rs1.next()) {
                                    datos[4] = rs1.getString("fecha");
                                    datos[5] = rs1.getString("horasTrab");
                                    datos[6] = rs1.getString("nombreinc");
                                    datos[7] = rs1.getString("comentario");

                                    

                                    if (dia(rs1.getString("fecha")) == 2) {
                                          row.createCell(4).setCellValue(datos[4]);
                                          row.createCell(5).setCellValue(datos[5]);
                                          row.createCell(6).setCellValue(datos[6]);
                                          row.createCell(7).setCellValue(datos[7]);
                                    } else if (dia(rs1.getString("fecha")) == 3) {
                                          row.createCell(8).setCellValue(datos[4]);
                                          row.createCell(9).setCellValue(datos[5]);
                                          row.createCell(10).setCellValue(datos[6]);
                                          row.createCell(11).setCellValue(datos[7]);

                                    } else if (dia(rs1.getString("fecha")) == 4) {
                                          row.createCell(12).setCellValue(datos[4]);
                                          row.createCell(13).setCellValue(datos[5]);
                                          row.createCell(14).setCellValue(datos[6]);
                                          row.createCell(15).setCellValue(datos[7]);

                                    } else if (dia(rs1.getString("fecha")) == 5) {
                                          row.createCell(16).setCellValue(datos[4]);
                                          row.createCell(17).setCellValue(datos[5]);
                                          row.createCell(18).setCellValue(datos[6]);
                                          row.createCell(19).setCellValue(datos[7]);

                                    } else if (dia(rs1.getString("fecha")) == 6) {
                                          row.createCell(20).setCellValue(datos[4]);
                                          row.createCell(21).setCellValue(datos[5]);
                                          row.createCell(22).setCellValue(datos[6]);
                                          row.createCell(23).setCellValue(datos[7]);

                                    } else if (dia(rs1.getString("fecha")) == 7) {
                                          row.createCell(24).setCellValue(datos[4]);
                                          row.createCell(25).setCellValue(datos[5]);
                                          row.createCell(26).setCellValue(datos[6]);
                                          row.createCell(27).setCellValue(datos[7]);

                                    } else if (dia(rs1.getString("fecha")) == 1) {
                                          row.createCell(28).setCellValue(datos[4]);
                                          row.createCell(29).setCellValue(datos[5]);
                                          row.createCell(30).setCellValue(datos[6]);
                                          row.createCell(31).setCellValue(datos[7]);

                                    }

                              }
                       
                        fila++;
                         } catch (Exception e) {
                              JOptionPane.showMessageDialog(null, "Error al cargar los datos\n" + e);
                        } 
                  }
                        
            } catch (Exception e) {
                  JOptionPane.showMessageDialog(null, "Error al cargar los datos\n" + e);
            } finally {
                  Conexion1.close(rs);
                  Conexion1.close(stmt);
                  if (this.userConn == null) {
                        Conexion1.close(conn);
                  }
                  Conexion1.close(rs1);
                              Conexion1.close(stmt1);
                              if (this.userConn1 == null) {
                                    Conexion1.close(conn1);
                              }
                  
                  
            }
          

            JFileChooser guardar = new JFileChooser();
            guardar.setApproveButtonText("Guardar");
            guardar.showSaveDialog(null);

            try {
                  FileOutputStream fileout = new FileOutputStream(guardar.getSelectedFile() + ".xls");
                  book.write(fileout);
                  fileout.close();

            } catch (FileNotFoundException ex) {
                  JOptionPane.showMessageDialog(null, "Error en: " + ex);
            } catch (IOException ex) {
                  JOptionPane.showMessageDialog(null, "Error en: " + ex);
            }

      }

      public int dia(String fec) {

            String nomdia = "";
            int dia = 0;

            GregorianCalendar cal = new GregorianCalendar();
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

            try {

                  Date fecha = formato.parse(fec);
                  cal.setTime(fecha);
                  dia = cal.get(Calendar.DAY_OF_WEEK);

            } catch (Exception e) {

                  JOptionPane.showMessageDialog(null, "Errorn en: " + e);

            }

            return dia;

      }

//      public void crear() {
//            org.apache.poi.ss.usermodel.Workbook book = new XSSFWorkbook();
//            org.apache.poi.ss.usermodel.Sheet sheet = book.createSheet("REPORTE INCIDENCIAS");
//
//            Row row = sheet.createRow(0);
//            row.createCell(0).setCellValue("Actualizado JA");
//            row.createCell(1).setCellValue("Actualizado RH");
//            row.createCell(2).setCellValue("ID");
//            row.createCell(3).setCellValue("NOMBRE");
//            row.createCell(4).setCellValue("FECHA");
//            row.createCell(5).setCellValue("ENTRADA");
//            row.createCell(6).setCellValue("SALIDA");
//            row.createCell(7).setCellValue("HORAS");
//            row.createCell(8).setCellValue("INCIDENCIA");
//            row.createCell(9).setCellValue("COMENTARIO");
//
//            row = sheet.createRow(1);
//            row.createCell(0).setCellValue(7);
//            row.createCell(1).setCellValue(8);
////
////            org.apache.poi.ss.usermodel.Cell celdados = rowUno.createCell(2);
////            celdados.setCellFormula(String.format("A%d+B%d", 2, 2));
//
//            JFileChooser guardar = new JFileChooser();
//            guardar.setApproveButtonText("Guardar");
//            guardar.showSaveDialog(null);
//
//            try {
//                  FileOutputStream fileout = new FileOutputStream(guardar.getSelectedFile() + ".xls");
//                  book.write(fileout);
//                  fileout.close();
//
//            } catch (FileNotFoundException ex) {
//                  JOptionPane.showMessageDialog(null, "Error en: " + ex);
//            } catch (IOException ex) {
//                  JOptionPane.showMessageDialog(null, "Error en: " + ex);
//            }
//      }
}
