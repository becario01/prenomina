/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Conexion.Conexion1;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Programacion 04
 */
public class PercepcionesReport {

    static Connection conn;
    static PreparedStatement stmt;
    public static ResultSet rs;
    private static Connection userConn;

    public static List<String> getHeaders() {
        List<String> tableHeader = new ArrayList<>();
        tableHeader.add("EMPLEADO ID");
        tableHeader.add("NOMBRE EMPLEADO");
        tableHeader.add("BONO DE PRODUCTIVIDAD");
        tableHeader.add("PREMIO DE ASISTENCIA");
        tableHeader.add("DIFERENCIA DE SUELDO");
        tableHeader.add("SUPLENCIA");
        tableHeader.add("FESTIVO");
        tableHeader.add("PRIMA DOMINICAL");
        tableHeader.add("SOBRESUELDO");
        tableHeader.add("FALTA ADMINISTRATIVA");
        tableHeader.add("FALTA SANCION");
        tableHeader.add("DAÑO A LA PRODUCCION");
        tableHeader.add("DAÑO A LA CALIDAD");

        return tableHeader;

    }

    public static List<List<String>> getContent(int numRow, int semana) {
      List<List<String>> tableContent = new ArrayList<List<String>>();
        List<String> row = null;

        
        String sql = "SELECT  em.empleadoId, em.nombre, per.per1, per.per2, per.per3, per.per4, per.per5, per.per6, per.per7, per.per8, per.per9, per.per10, per.per11 \n"
                + "FROM percepciones per \n"
                + "INNER JOIN empleados em on per.empleadoId=em.empleadoId\n"
                + "where per.idSemana='" + semana + "'";

        try {
            conn = Conexion1.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            int fila = 4;
            while (rs.next()) {
                tableContent.add(row = new ArrayList<String>());
               row.add(rs.getString("empleadoId"));
               row.add(rs.getString("nombre"));
               row.add(rs.getString("per1"));
               row.add(rs.getString("per2"));
               row.add(rs.getString("per3"));
               row.add(rs.getString("per4"));
               row.add(rs.getString("per5"));
               row.add(rs.getString("per6"));
               row.add(rs.getString("per7"));
               row.add(rs.getString("per8"));
               row.add(rs.getString("per9"));
               row.add(rs.getString("per10"));
               row.add(rs.getString("per11"));

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar los datos\n" + e);
        } finally {
            Conexion1.close(rs);
            Conexion1.close(stmt);
            if (userConn == null) {
                Conexion1.close(conn);
            }

        }

        return tableContent;

    }

    public void cargar(int semana) throws SQLException {
        String sql = "SELECT  em.empleadoId, em.nombre, per.per1, per.per2, per.per3, per.per4, per.per5, per.per6, per.per7, per.per8, per.per9, per.per10, per.per11 \n"
                + "FROM percepciones per \n"
                + "INNER JOIN empleados em on per.empleadoId=em.empleadoId\n"
                + "where per.idSemana='" + semana + "'";

        try {
            conn = (this.userConn != null) ? this.userConn : Conexion1.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            int fila = 4;
            while (rs.next()) {

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
}
