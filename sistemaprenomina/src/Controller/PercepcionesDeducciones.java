/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Conexion.Conexion1;

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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;

/**
 *
 * @author Programacion 04
 */
public class PercepcionesDeducciones {

    private Connection userConn;
    private Connection userConn1;
    private Connection userConn2;
    private Connection userConn3;
    private Connection userConn4;
    private Connection userConn5;
    private Connection userConn6;
    public static Vector<String> arrayidR = new Vector<>();
    public static Vector<String> arrayfeR = new Vector<>();
    public static Vector<String> arrayfechas = new Vector<>();
    String percep;

    public void insertar(String empleadoid, String fecha, int idper, String coment, JRootPane rootPane) throws SQLException {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        listar();
        System.out.println("°°°°" + arrayidR);
        System.out.println("°°°°" + arrayfeR);

        percep = String.valueOf(idper);
        try {
            obtenerfechas(fecha);
            System.out.println(arrayfechas + "´´´´");
            conn = (this.userConn != null) ? this.userConn : Conexion1.getConnection();
            String sql = "INSERT INTO percepciones( empleadoId, fecha, idNomPer, dia, comentario, Semana) values (?,?,?,?,?,?)";
            System.out.println(arrayfechas.size() + "#");
            if (arrayfechas.size() == 1) {
                Boolean comu = false;
                Boolean comf = false;
                Boolean fin = false;
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, empleadoid);
                stmt.setString(2, fecha);
                stmt.setString(3, percep);
                stmt.setString(4, dia(fecha));
                stmt.setString(5, coment);
                stmt.setString(6, nomsenanas(fecha));
                for (int j = 0; j < arrayfeR.size(); j++) {
                    comf = arrayfeR.elementAt(j).equals(fecha);
                    if (comf) {
                        comu = arrayidR.elementAt(j).equals(String.valueOf(empleadoid));
                        if (comu) {
                            fin = true;
                        }
                    }
                }
                if (fin) {
                    duplicado(empleadoid, fecha, percep, dia(fecha), coment, nomsenanas(fecha), rootPane);
                } else {
                    stmt.execute();
                    JOptionPane.showMessageDialog(null, "Registro Exitoso!");
                }
            } else if (arrayfechas.size() > 1) {
                repetido(empleadoid, stmt, rs, conn, sql, coment, rootPane);
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en:  " + e, "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (ParseException ex) {
            Logger.getLogger(PercepcionesDeducciones.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion1.close(stmt);
            if (this.userConn == null) {
                Conexion1.close(conn);
            }
        }
    }

    public void repetido(String empleadoid, PreparedStatement stmt, ResultSet rs, Connection conn, String sql, String coment, JRootPane rootPane) {
        try {

            for (int i = 0; i < arrayfechas.size(); i++) {
                Boolean comu = false;
                Boolean comf = false;
                Boolean fin = false;
                String fechaFin = arrayfechas.elementAt(i);
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, empleadoid);
                stmt.setString(2, fechaFin);
                stmt.setString(3, percep);
                stmt.setString(4, dia(fechaFin));
                stmt.setString(5, coment);
                stmt.setString(6, nomsenanas(fechaFin));

               for (int j = 0; j < arrayfeR.size(); j++) {
                    comf = arrayfeR.elementAt(j).equals(fechaFin);
                    if (comf) {
                        comu = arrayidR.elementAt(j).equals(String.valueOf(empleadoid));
                        if (comu) {
                            fin = true;
                        }
                    }
                }
                if (fin) {
                    duplicado(empleadoid, fechaFin, percep, dia(fechaFin), coment, nomsenanas(fechaFin), rootPane);
                } else {
                    stmt.execute();
                }
            }
        } catch (HeadlessException | SQLException | ParseException e) {
            JOptionPane.showMessageDialog(null, e, "ERROR", JOptionPane.ERROR_MESSAGE);
        } finally {
            JOptionPane.showMessageDialog(null, "Registros Exitosos!");
        }

    }

    public void obtenerfechas(String fecha) throws ParseException {

        ResultSet rs6;
        Connection conn6 = null;
        PreparedStatement stmt6 = null;
        String numerod = null;
        try {
            String sql1 = "select * from nomPercepciones where idNomPer='" + percep + "'";
            conn6 = (this.userConn6 != null) ? this.userConn6 : Conexion1.getConnection();
            stmt6 = conn6.prepareStatement(sql1);
            rs6 = stmt6.executeQuery();
            while (rs6.next()) {
                numerod = rs6.getString("dias");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e, "ERROR", JOptionPane.ERROR_MESSAGE);
        } finally {
            Conexion1.close(stmt6);
            Conexion1.close(stmt6);
            if (this.userConn6 == null) {
                Conexion1.close(conn6);
            }

        }
        int numd = Integer.valueOf(numerod);
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cale2 = Calendar.getInstance();
        Date date1 = formato.parse(fecha);
        cale2.setTime(date1);
        cale2.add(Calendar.DAY_OF_YEAR, numd - 1);
        Date date2 = cale2.getTime();
        String fecha2 = formato.format(date2);
        System.out.println(fecha);
        System.out.println(fecha2);
        listarfechas(date1, date2);
    }

    public void listarfechas(Date date1, Date date2) throws ParseException {

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Vector<Date> listaFechas = new Vector<>();
        listaFechas.clear();
        arrayfechas.clear();
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date1);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(date2);

        while (!c1.after(c2)) {
            listaFechas.add(c1.getTime());
            c1.add(Calendar.DAY_OF_MONTH, 1);
        }
        arrayfechas.clear();
        for (int i = 0; i < listaFechas.size(); i++) {
            arrayfechas.add(formato.format(listaFechas.elementAt(i)));

        }

    }

    public void listar() {
        ResultSet rs1;
        Connection conn1 = null;
        PreparedStatement stmt1 = null;
        arrayidR.clear();
        arrayfeR.clear();
        try {
            conn1 = (this.userConn1 != null) ? this.userConn1 : Conexion1.getConnection();
            String sql1 = "select * from percepciones";
            stmt1 = conn1.prepareStatement(sql1);
            rs1 = stmt1.executeQuery();
            int con2 = 0;
            while (rs1.next()) {
                arrayidR.add(con2, rs1.getString("empleadoId"));
                arrayfeR.add(con2, rs1.getString("fecha"));
                con2++;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e, "ERROR", JOptionPane.ERROR_MESSAGE);
        } finally {
            Conexion1.close(stmt1);

            if (this.userConn1 == null) {
                Conexion1.close(conn1);
            }

        }

    }

    public void duplicado(String empleadoid, String fecha, String idper, String dia, String coment, String semana, JRootPane rootPane) {
        Object[] opciones = {"Aceptar", "Cancelar"};
        int eleccion = JOptionPane.showOptionDialog(rootPane, fecha + " cuanta ya con alguna percepcion \no deduccion desea actualizarla", "Mensaje de Confirmacion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, opciones, "Aceptar");
        if (eleccion == JOptionPane.YES_OPTION) {
            actualizar(empleadoid, fecha, percep, dia, coment, semana);

        } else {
        }
    }

    public void actualizar(String empleadoid, String fecha, String idper, String dia, String coment, String semana) {
        ResultSet rs2;
        Connection conn2 = null;
        PreparedStatement stmt2 = null;
        try {

            conn2 = (this.userConn2 != null) ? this.userConn2 : Conexion1.getConnection();
            String sql = "UPDATE percepciones SET idNomPer='" + idper + "', comentario='" + coment + "'"
                    + " WHERE empleadoId='" + empleadoid + "' and  fecha='" + fecha + "' ";

            PreparedStatement pst = conn2.prepareStatement(sql);
            pst.executeUpdate();
            conn2.close();

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en:  " + e, "ERROR", JOptionPane.ERROR_MESSAGE);

        } finally {
            Conexion1.close(stmt2);
            JOptionPane.showMessageDialog(null, "Actualizacion Exitosa!");
            if (this.userConn2 == null) {
                Conexion1.close(conn2);
            }

        }
    }

    public void newPercep(String nombre, String dias) throws SQLException {
        String estatus = "1";
        ResultSet rs3;
        Connection conn3 = null;
        PreparedStatement stmt3 = null;
        try {
            conn3 = (this.userConn3 != null) ? this.userConn3 : Conexion1.getConnection();
            String sql = "INSERT INTO nomPercepciones(nombre, estatus, dias) values (?,?,?)";

            stmt3 = conn3.prepareStatement(sql);
            stmt3.setString(1, nombre);
            stmt3.setString(2, estatus);
            stmt3.setString(3, dias);

            stmt3.execute();
            JOptionPane.showMessageDialog(null, "Registro Exitoso!");

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en:  " + e, "ERROR", JOptionPane.ERROR_MESSAGE);

        } finally {
            Conexion1.close(stmt3);

            if (this.userConn3 == null) {
                Conexion1.close(conn3);
            }

        }

    }

    public void desactivar(String semana) throws SQLException {
        String sql = "UPDATE nomPercepciones SET estatus=0 WHERE  idNomPer='" + semana + "'";
        ResultSet rs4;
        Connection conn4 = null;
        PreparedStatement stmt4 = null;
        int rows = 0;
        try {
            conn4 = (this.userConn4 != null) ? this.userConn4 : Conexion1.getConnection();

            stmt4 = conn4.prepareStatement(sql);

            rows = stmt4.executeUpdate();

        } finally {
            Conexion1.close(stmt4);
            if (this.userConn4 == null) {
                Conexion1.close(conn4);
            }
        }
    }

    public void activar(String semana) throws SQLException {
        String sql = "UPDATE nomPercepciones SET estatus=1 WHERE  idNomPer='" + semana + "'";
        ResultSet rs5;
        Connection conn5 = null;
        PreparedStatement stmt5 = null;
        int rows = 0;
        try {
            conn5 = (this.userConn5 != null) ? this.userConn5 : Conexion1.getConnection();

            stmt5 = conn5.prepareStatement(sql);

            rows = stmt5.executeUpdate();

        } finally {
            Conexion1.close(stmt5);
            if (this.userConn5 == null) {
                Conexion1.close(conn5);
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
        System.out.println(nomsemana);

        return nomsemana;
    }

    public String dia(String fec) {
        GregorianCalendar cal = new GregorianCalendar();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String nomdia = "";
        try {
            Date fecha = formato.parse(fec);
            cal.setTime(fecha);
            int dia = cal.get(Calendar.DAY_OF_WEEK);
            switch (dia) {
                case 1:
                    nomdia = "DOMINGO";
                    break;
                case 2:
                    nomdia = "LUNES";
                    break;
                case 3:
                    nomdia = "MARTES";
                    break;
                case 4:
                    nomdia = "MIERCOLES";
                    break;
                case 5:
                    nomdia = "JUEVEZ";
                    break;
                case 6:
                    nomdia = "VIERNES";
                    break;
                case 7:
                    nomdia = "SABADO";
                    break;
                default:
                    break;
            }
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Errorn en: " + e, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return nomdia;
    }
}
