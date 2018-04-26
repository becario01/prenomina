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
    PreparedStatement stmt;
    Conexion1 con = new Conexion1();
    public static Vector<String> arrayid = new Vector<>();
    public static Vector<String> arraysemana = new Vector<>();
    public static Vector<String> arrayemp = new Vector<>();
    public static ResultSet rs1;
    private Connection userConn1;
    Connection conn1;
    PreparedStatement stmt1;
    public static ResultSet rs2;
    private Connection userConn2;
    Connection conn2;
    PreparedStatement stmt2;
    String domingo = "";

    public String domingos(String semana) throws SQLException {
        String sql = "select fechaD from semanas where idSemana='" + semana + "'";

        try {
            conn = (this.userConn != null) ? this.userConn : Conexion1.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                domingo = rs.getString("fechaD");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar los datos\n" + e,"ERROR",JOptionPane.ERROR_MESSAGE);
        } finally {
            Conexion1.close(rs);
            Conexion1.close(stmt);
            if (this.userConn == null) {
                Conexion1.close(conn);
            }
        }
        
        return domingo;
    }

    public Vector<String> empleado(String semana) throws SQLException {
        arrayemp.clear();
        
        String sql = "select empleadoId, fecha from registros where fecha='" + domingo + "'";
        String id = "";
        try {
            conn1 = (this.userConn1 != null) ? this.userConn1 : Conexion1.getConnection();
            stmt1 = conn1.prepareStatement(sql);
            rs1 = stmt1.executeQuery();
            int cont=0;
            while (rs1.next()) {
                arrayemp.add(rs1.getString("empleadoId"));
                System.out.println("empleado "+cont+"  "+rs1.getString("empleadoId"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar los datos\n" + e,"ERROR",JOptionPane.ERROR_MESSAGE);
        } finally {
            Conexion1.close(rs1);
            Conexion1.close(stmt1);
            if (this.userConn1 == null) {
                Conexion1.close(conn1);
            }
        }
        return arrayemp;
    }

    public void insertar(String semana) throws SQLException {
   
        listar();
        domingos(semana);
        empleado(semana);
       
       
        Boolean com = false;
        Boolean fin = false;
        try {

            
            for (int i = 0; i < arrayemp.size(); i++) {
                conn2 = (this.userConn2 != null) ? this.userConn2 : Conexion1.getConnection();
            String sql = "INSERT INTO percepciones( per6, empleadoId, idSemana) values (?,?,?)";

                stmt2 = conn2.prepareStatement(sql);
                stmt2.setString(1, "1");

                stmt2.setString(2, arrayemp.elementAt(i));
                stmt2.setString(3, semana);
         
for (int j = 0; j < arrayid.size(); j++) {
    for (int k = 0; k < arrayemp.size(); k++) {
        
    
                com = arrayid.elementAt(j).equals(String.valueOf(arrayemp.elementAt(k))) && arraysemana.elementAt(j).equals(semana);
                if (com) {
                    fin = true;
        
                }
            }}
            if(fin){
                actualizar(semana);
                
            }else{
                stmt2.execute();
                
            }
           
                
        }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en:  " + e,"ERROR",JOptionPane.ERROR_MESSAGE);

        } finally {
            Conexion1.close(stmt2);
 
            if (this.userConn2 == null) {
                Conexion1.close(conn2);
            }

        }

    }

    public Vector<String> listar() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt1 = null;

        conn = (this.userConn2 != null) ? this.userConn2 : Conexion1.getConnection();
        String sql1 = "select * from percepciones";

        stmt1 = conn.prepareStatement(sql1);

        rs2 = stmt1.executeQuery();

        int con = 0;

        while (rs2.next()) {

            arrayid.add(con, rs2.getString("empleadoId"));
            arraysemana.add(con, rs2.getString("idSemana"));

            con++;
//                                      

        }

        return arrayid;

    }

    public void actualizar(String semana) {
        for (int i = 0; i < arrayemp.size(); i++) {
        try {
           
                
            
            conn2 = (this.userConn2 != null) ? this.userConn2 : Conexion1.getConnection();
            String sql = "UPDATE percepciones SET  per6='1'"
                    + " WHERE empleadoId='" + arrayemp.elementAt(i) + "' and  idSemana='" + semana + "' ";

            PreparedStatement pst = conn2.prepareStatement(sql);
            pst.executeUpdate();
            conn2.close();

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en:  " + e,"ERROR",JOptionPane.ERROR_MESSAGE);

        } finally {
            Conexion1.close(stmt2);
            
            if (this.userConn2 == null) {
                Conexion1.close(conn2);
            }

        }
        
       
    }
       
    }

}
