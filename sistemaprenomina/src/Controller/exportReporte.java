/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Conexion.Conexion1;
import static View.RH_ListadoPersonal.rs;
//import static View.RH_UsuariosConIncidencias.rs;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Programacion 2
 */
public class exportReporte {

    public static Connection conn;
    public static Connection conn1;
    public static Connection conn2;
    public static PreparedStatement stmt;
    public static PreparedStatement stmt2;
    public static ResultSet rs;
    public static ResultSet rs2;
    public static PreparedStatement stmt1;
    public static ResultSet rs1;
    private static Connection userConn;
    private static Connection userConn2;
    private static Connection userConn1;
    boolean dom = false;
    boolean lu = false;
    boolean ma = false;
    boolean mi = false;
    boolean ju = false;
    boolean vi = false;
    boolean sa = false;
    public static String fec[] = new String[7];

    public static List<String> getTableHeaders() {
        List<String> tableHeader = new ArrayList<String>();
        tableHeader.add("ID");
        tableHeader.add("NOMBRE");
        tableHeader.add("DEPARTAMENTO");
        tableHeader.add("PUESTO");
        tableHeader.add("FECHA");
        tableHeader.add("INCIDENCIA");
        tableHeader.add("COMENTARIO");
        tableHeader.add("HORAS EXTRA");
        tableHeader.add("FECHA");
        tableHeader.add("INCIDENCIA");
        tableHeader.add("COMENTARIO");
        tableHeader.add("HORAS EXTRA");
        tableHeader.add("FECHA");
        tableHeader.add("INCIDENCIA");
        tableHeader.add("COMENTARIO");
        tableHeader.add("HORAS EXTRA");
        tableHeader.add("FECHA");
        tableHeader.add("INCIDENCIA");
        tableHeader.add("COMENTARIO");
        tableHeader.add("HORAS EXTRA");
        tableHeader.add("FECHA");
        tableHeader.add("INCIDENCIA");
        tableHeader.add("COMENTARIO");
        tableHeader.add("HORAS EXTRA");
        tableHeader.add("FECHA");
        tableHeader.add("INCIDENCIA");
        tableHeader.add("COMENTARIO");
        tableHeader.add("HORAS EXTRA");
        tableHeader.add("FECHA");
        tableHeader.add("INCIDENCIA");
        tableHeader.add("COMENTARIO");
        tableHeader.add("HORAS EXTRA");

        return tableHeader;
    }

    public static List<String> getTableHeaders2(int idSemana) {
//        fechas(idSemana);
        List<String> tableHeader = new ArrayList<String>();
        tableHeader.add("");
        tableHeader.add("");
        tableHeader.add("");
        tableHeader.add("");
        tableHeader.add("LUNES    ");
        tableHeader.add("");
        tableHeader.add("");
        tableHeader.add("");

        tableHeader.add("MARTES    ");
        tableHeader.add("");
        tableHeader.add("");
        tableHeader.add("");

        tableHeader.add("MIERCOLES    ");
        tableHeader.add("");
        tableHeader.add("");
        tableHeader.add("");

        tableHeader.add("JUEVEZ    ");
        tableHeader.add("");
        tableHeader.add("");
        tableHeader.add("");

        tableHeader.add("VIERNES    ");
        tableHeader.add("");
        tableHeader.add("");
        tableHeader.add("");

        tableHeader.add("SABADO    ");
        tableHeader.add("");
        tableHeader.add("");
        tableHeader.add("");

        tableHeader.add("DOMINGO    ");
        tableHeader.add("");
        tableHeader.add("");
        tableHeader.add("");

        return tableHeader;
    }

    public static int dia(String fec) {

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

    public static int contador() {

        int con = 0;
        String sql = "SELECT DISTINCT inc.empleadoId  \n"
                + "                 from incidencias inc\n"
                + "               INNER JOIN empleados emp on inc.empleadoId= emp.empleadoId\n"
                + "                 INNER JOIN semanas se on inc.idSemana= se.idSemana\n"
                + "                 where inc.idSemana='1' ";

        try {
            conn2 = Conexion1.getConnection();
            stmt2 = conn2.prepareStatement(sql);
            rs2 = stmt2.executeQuery();
            while (rs2.next()) {
                con = rs2.getRow();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar los datos\n" + e);
        } finally {
            Conexion1.close(rs2);
            Conexion1.close(stmt2);
            if (userConn2 == null) {
                Conexion1.close(conn2);
            }
        }

        return con;

    }

    public static String[] fechas(int idSemana) {

        String sql = "SELECT * from semanas where idSemana='" + idSemana + "' ";

        try {
            conn2 = Conexion1.getConnection();
            stmt2 = conn2.prepareStatement(sql);
            rs2 = stmt2.executeQuery();
            while (rs2.next()) {
                fec[0] = rs2.getString("fechaL");
                fec[1] = rs2.getString("fechaMa");
                fec[2] = rs2.getString("fechaMi");
                fec[3] = rs2.getString("fechaJ");
                fec[4] = rs2.getString("fechaV");
                fec[5] = rs2.getString("fechaS");
                fec[6] = rs2.getString("fechaD");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar los datos\n" + e);
        } finally {
            Conexion1.close(rs2);
            Conexion1.close(stmt2);
            if (userConn2 == null) {
                Conexion1.close(conn2);
            }
        }

        return fec;

    }

}
