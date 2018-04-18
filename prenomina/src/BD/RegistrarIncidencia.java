/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import Conexion.Conexion;
import Controller.EIncidencia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Becarios
 */
public class RegistrarIncidencia {
    
    private Connection userConn;
 

         public RegistrarIncidencia() {
    }

   
    public RegistrarIncidencia(Connection conn) {
        this.userConn = conn;
    }

    
    
    public int insert(int empleadoId,String dia,String fecha,int horasextra,String comentario,int idSemana,int idNomIncidencias,String horasTrab) throws SQLException {
      Connection conn = null;
     PreparedStatement stmt = null;
        int rows = 0;
        try {
            String SQL_INSERT = "INSERT INTO incidencias (empleadoId ,dia,fecha ,horasExtra ,comentario ,idSemana ,idNomIncidencia,horasTrab) VALUES ('"+empleadoId+"','"+dia+"' ,'"+fecha+"','"+horasextra+"','"+comentario+"','"+idSemana+"','"+idNomIncidencias+"','"+horasTrab+"')";
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            int index = 1;
//            stmt.setString(index, persona.getApellido());
            System.out.println("Ejecutando query:" + SQL_INSERT);
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados:" + rows);
            JOptionPane.showMessageDialog(null,"Incidencia registrada");
        } finally {
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }

        return rows;
       

       
    }
    
    
    
    
  
}
