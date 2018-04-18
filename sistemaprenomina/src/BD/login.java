/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import java.sql.*; 
import Conexion.Conexion;
import View.JA_inicio;
import static View.JA_inicio.lblcargojefe;
import static View.JA_inicio.lblnombrejefe;
import View.JA_newincidencia;
import View.RH_Inicio;
import javax.swing.JOptionPane;
import View.JA_inicio;
import View.Jflogin;
import View.Seleccion;
/**
 *
 * @author Becarios
 */
public class login {
 private Connection userConn;
 String nombre;
    String depto;
    public login() {

    }
    public login(Connection conn) {
        this.userConn = conn;
    }
    public boolean validar_ingreso(String usuario, String pass){
  Connection conn = null;
  PreparedStatement stmt = null;
  ResultSet rs = null;
    int resultado=0;
    String SSQL="SELECT * FROM usuario WHERE usuario='"+usuario+"' AND pass='"+pass+"' AND estatus=1";
    try {
           conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SSQL);
            rs = stmt.executeQuery();
if (rs.next() == false) {
     JOptionPane.showMessageDialog(null,  "El Usuario no Existe","",JOptionPane.ERROR_MESSAGE);
     Jflogin usl = new Jflogin();
     usl.setVisible(true);
     
   
} else{
    nombre = rs.getString(2);
    depto = rs.getString(7);
    int tipousuario= rs.getInt(5);
   
    
    if (tipousuario == 0) {
        JOptionPane.showMessageDialog(null,  "Bienvenido Usuario Jefe","",JOptionPane.INFORMATION_MESSAGE);
         JA_inicio ini=new JA_inicio();
         JA_inicio.lblcargojefe.setText(depto);
         JA_inicio.lblnombrejefe.setText(nombre);

  
            ini.show(true);
    }else if(tipousuario == 1){
        JOptionPane.showMessageDialog(null,  "Bienvenido Usuario RH","",JOptionPane.INFORMATION_MESSAGE);
        Seleccion.nombre= nombre;
        Seleccion.depto= depto;
        Seleccion rh=new Seleccion();
            rh.show(true);   

            
         
    
    }
}   
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, ex, "Error de conexión", JOptionPane.ERROR_MESSAGE);
    }finally{
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
    }
     return true;
}
}



