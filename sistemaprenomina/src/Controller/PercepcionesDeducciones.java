/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Conexion.Conexion1;
import static Controller.exportReporte.conn;
import static Controller.exportReporte.stmt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
            
            
        public void insertar(String id, String semana, String datos[])  throws SQLException{
            
            try {
                
            
          
             conn = (this.userConn != null) ? this.userConn : Conexion1.getConnection();
       String sql="INSERT INTO percepciones(per1, per2, per3, per4, per5, per6, per7, per8, per8, per10, per11, empeadoId, idSemana) vales (?,?,?,?,?,?,?,?,?,?,?,?)";
       
       stmt = conn.prepareStatement(sql);
                              stmt.setString(1, "1");
                              stmt.setString(2, "1");
                              stmt.setString(3, "1");
                              stmt.setString(4, "1");
                              stmt.setString(5, "1");
                              stmt.setString(6, "1");
                              stmt.setString(7, "1");
                              stmt.setString(8, "1");
                              stmt.setString(9, "1");
                              stmt.setString(10, "1");
                              stmt.setString(11, "1");
                              stmt.setString(12, "1");
                              stmt.setString(13, "1");
                              stmt.execute();
                              
                              } finally {
                        Conexion1.close(stmt);
                        if (this.userConn == null) {
                              Conexion1.close(conn);
                        }

                  }
       
        }
    
}
