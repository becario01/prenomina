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
public class PercepcionesDeducciones {

    private Connection userConn;
    public static ResultSet rs;
    Connection conn = null;
    Conexion1 con = new Conexion1();
    PreparedStatement stmt = null;
    public static Vector<String> arrayid = new Vector<>();
    public static Vector<String> arraysemana = new Vector<>();

    public void insertar(String id, String semana, String datos[], JRootPane rootPane) throws SQLException {
        listar();
        Boolean com = false;
        Boolean fin = false;
        try {

            conn = (this.userConn != null) ? this.userConn : Conexion1.getConnection();
            String sql = "INSERT INTO percepciones(per1, per2, per3, per4, per5, per6, per7, per8, per9, per10, per11, empleadoId, idSemana) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, datos[0]);
            stmt.setString(2, datos[1]);
            stmt.setString(3, datos[2]);
            stmt.setString(4, datos[3]);
            stmt.setString(5, datos[4]);
            stmt.setString(6, datos[5]);
            stmt.setString(7, datos[6]);
            stmt.setString(8, datos[7]);
            stmt.setString(9, datos[8]);
            stmt.setString(10, datos[9]);
            stmt.setString(11, datos[10]);
            stmt.setString(12, id);
            stmt.setString(13, semana);

            for (int j = 0; j < arrayid.size(); j++) {
                com = arrayid.elementAt(j).equals(String.valueOf(id)) && arraysemana.elementAt(j).equals(semana);
                if (com) {
                    fin = true;
        
                }
            }
            if(fin){
                duplicado(id, semana, datos, rootPane);
                
            }else{
                stmt.execute();
                JOptionPane.showMessageDialog(null, "Registro Exitoso!");
            }

            

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en:  " + e);

        } finally {
            Conexion1.close(stmt);
            
            if (this.userConn == null) {
                Conexion1.close(conn);
            }

        }

    }

    public Vector<String> listar() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt1 = null;

        conn = (this.userConn != null) ? this.userConn : Conexion1.getConnection();
        String sql1 = "select * from percepciones";

        stmt1 = conn.prepareStatement(sql1);

        rs = stmt1.executeQuery();

        int con = 0;

        while (rs.next()) {

            arrayid.add(con, rs.getString("empleadoId"));
            arraysemana.add(con, rs.getString("idSemana"));

            con++;
//                                      

        }

        return arrayid;

    }
        public void duplicado(String id, String semana, String datos[],JRootPane rootPane){   
        Object [] opciones ={"Aceptar","Cancelar"};
        int eleccion = JOptionPane.showOptionDialog(rootPane,"Usted ya ha ingresado percepciones desea actualizarlas","Mensaje de Confirmacion",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE,null,opciones,"Aceptar");
            if (eleccion == JOptionPane.YES_OPTION){
                actualizar(id, semana, datos);
                
                }else{
            }
                }   
       public void actualizar(String id, String semana, String datos[]){
            try {
               
            conn = (this.userConn != null) ? this.userConn : Conexion1.getConnection();
            String sql = "UPDATE percepciones SET per1='"+datos[0]+"' , per2='"+datos[1]+"', per3='"+datos[2]+"', per4='"+datos[3]+"', per5='"+datos[4]+"', per6='"+datos[5]+"', per7='"+datos[6]+"', per8='"+datos[7]+"', per9='"+datos[8]+"', per10='"+datos[9]+"', per11='"+datos[10]+"'"
                    + " WHERE empleadoId='"+id+"' and  idSemana='"+semana+"' ";

        
            PreparedStatement pst = conn.prepareStatement(sql);
                                          pst.executeUpdate();
                                          conn.close();

            

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en:  " + e);

        } finally {
            Conexion1.close(stmt);
            JOptionPane.showMessageDialog(null, "Registro Exitoso!");
            if (this.userConn == null) {
                Conexion1.close(conn);
            }

        }
       }

}
