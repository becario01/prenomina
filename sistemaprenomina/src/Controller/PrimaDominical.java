/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Conexion.Conexion;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;

/**
 *
 * @author Programacion 04
 */
public class PrimaDominical {

    public static ResultSet rs;
    private Connection userConn;
    Connection conn;
    PreparedStatement stmt1;
    public static ResultSet rs1;
    private Connection userConn1;
    Connection conn1;
    PreparedStatement stmt2;
    public static ResultSet rs2;
    private Connection userConn2;
    Connection conn2;
    PreparedStatement stmt3;
    public static ResultSet rs3;
    private Connection userConn3;
    Connection conn3;
    PreparedStatement stmt4;
    public static ResultSet rs4;
    private Connection userConn4;
    Connection conn4;
    PreparedStatement stmt;
    Conexion con = new Conexion();
    Vector<String> arrayidR = new Vector<>();
    Vector<String> arrayfechaR = new Vector<>();
    Vector<String> arraynomsema = new Vector<>();
    Vector<String> arrayemp = new Vector<>();
    Vector<String> arrayfecha = new Vector<>();
    Vector<String> arrayDomingos = new Vector<>();
    Vector<String> arrayDias = new Vector<>();

    public PrimaDominical(Vector<String> dias) {
        arrayDias.clear();
        arrayDias = dias;
    }


    public void domingos() {
        GregorianCalendar cal = new GregorianCalendar();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

        try {
            for (int i = 0; i < arrayDias.size(); i++) {
            String fechadia = arrayDias.elementAt(i);
                Date fecha = formato.parse(fechadia);
                cal.setTime(fecha);
                int dia = cal.get(Calendar.DAY_OF_WEEK);

                if (dia == 1) {
                   arrayDomingos.add(fechadia);
                }
            }
        } catch (ParseException e) {

            JOptionPane.showMessageDialog(null, "Errorn en: " + e, "ERROR", JOptionPane.ERROR_MESSAGE);

        }

//   
    }
    public Vector<String> empleado() throws SQLException, ParseException {
        arrayemp.clear();
        for (int j = 0; j < arrayDomingos.size(); j++) {
            String domingo = arrayDomingos.elementAt(j);
            String sql = "select empleadoId, fecha from registros where fecha='" + domingo + "'";
            try {
                conn1 = (this.userConn1 != null) ? this.userConn1 : Conexion.getConnection();
                stmt1 = conn1.prepareStatement(sql);
                rs1 = stmt1.executeQuery();
                int cont = 0;
                while (rs1.next()) {
                    arrayemp.add(rs1.getString("empleadoId"));
                    arrayfecha.add(rs1.getString("fecha"));
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cargar los datos\n" + e, "ERROR", JOptionPane.ERROR_MESSAGE);
            } finally {
                Conexion.close(rs1);
                Conexion.close(stmt1);
                if (this.userConn1 == null) {
                    Conexion.close(conn1);
                }
            }
        }
        for (int i = 0; i < arrayfecha.size(); i++) {
            String fechadomi=arrayfecha.elementAt(i);
            arraynomsema.add(nomsenanas(fechadomi));
        }
        return arrayemp;
    }

    public void insertar() throws SQLException, ParseException {

        listar();
        domingos();
        empleado();
        System.out.println(arrayDomingos+"***");
        Boolean com = false;
        Boolean fin = false;
        try {

            for (int i = 0; i < arrayemp.size(); i++) {
                conn2 = (this.userConn2 != null) ? this.userConn2 : Conexion.getConnection();
                String sql = "INSERT INTO percepciones( empleadoId, fecha, idNomPer, dia, comentario, Semana) values (?,?,?,?,?,?)";

                stmt2 = conn2.prepareStatement(sql);
                stmt2.setString(1, arrayemp.elementAt(i));
                stmt2.setString(2, arrayfecha.elementAt(i));
                stmt2.setString(3, "1");
                stmt2.setString(4, "DOMINGO");
                stmt2.setString(5, "PRIMA DOMINICAL");
                stmt2.setString(6, arraynomsema.elementAt(i));
               
                for (int j = 0; j < arrayidR.size(); j++) {
                    com = arrayidR.elementAt(j).equals(String.valueOf(arrayemp.elementAt(i))) && arrayfechaR.elementAt(j).equals(arrayfecha.elementAt(i));
                        if (com) {
                            fin = true;
                        }
                    }
                
                if (fin) {
                    String idem=arrayemp.elementAt(i);
                    String fech=arrayfecha.elementAt(i);
                    String se=arraynomsema.elementAt(i);
                    actualizar(idem, fech, se);

                } else {
                    
 stmt2.execute();
                }

            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en:  " + e, "ERROR", JOptionPane.ERROR_MESSAGE);

        } finally {
            Conexion.close(stmt2);

            if (this.userConn2 == null) {
                Conexion.close(conn2);
            }

        }

    }

    public Vector<String> listar()  {
        try {
             conn3 = (this.userConn3 != null) ? this.userConn3 : Conexion.getConnection();
        String sql1 = "select * from percepciones";

        stmt3 = conn3.prepareStatement(sql1);

        rs3 = stmt3.executeQuery();

        int con2 = 0;

        while (rs3.next()) {

            arrayidR.add(con2, rs3.getString("empleadoId"));
            arrayfechaR.add(con2, rs3.getString("fecha"));
            con2++;
        }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e,"ERROR",JOptionPane.ERROR_MESSAGE);
        } finally {
            Conexion.close(stmt3);
            if (this.userConn3 == null) {
                Conexion.close(conn3);
            }
        }
        return arrayidR;

    }

    public void actualizar(String idem, String fech, String semana) {
      
            try {
                conn4 = (this.userConn4 != null) ? this.userConn4 : Conexion.getConnection();
                String sql = "UPDATE percepciones SET  idNomPer='1', dia='DOMINGO', comentario='PRIMA DOMINICAL', Semana='"+semana+"'"
                        + " WHERE empleadoId='" + idem + "' and  fecha='" + fech+ "' ";

                PreparedStatement pst = conn4.prepareStatement(sql);
                pst.executeUpdate();
                conn4.close();

            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(null, "Error en:  " + e, "ERROR", JOptionPane.ERROR_MESSAGE);

            } finally {
                Conexion.close(stmt4);

                if (this.userConn4 == null) {
                    Conexion.close(conn4);
                }

            }

        

    }
    public String nomsenanas(String fecha) throws ParseException, SQLException {

        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setMinimalDaysInFirstWeek(4);
        java.util.Date date = d.parse(fecha);
        calendar.setTime(date);
        int numberWeekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
        int year = calendar.get(Calendar.YEAR);
        String numbstring = String.valueOf(numberWeekOfYear);
        String strinyear = String.valueOf(year);
        String sSubCadena = strinyear.substring(2, 4);
        String nomsemana = "SEMANA" + " " + numbstring + "_" + sSubCadena;
        return nomsemana;
    }
  
}
