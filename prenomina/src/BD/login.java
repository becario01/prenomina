/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import java.sql.*; 
import Conexion.Conexion;
//import View.JA_inicio;
////import static View.JA_inicio.lblcargojefe;
////import static View.JA_inicio.lblnombrejefe;
//import View.JA_newincidencia;
//import View.RH_Inicio;
//import View.UserLogin;
import javax.swing.JOptionPane;
/**
 *
 * @author Becarios
 */
public class login {
 private Connection userConn;
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
//     UserLogin usl = new UserLogin();
//     usl.show();
} else{
    String nombre = rs.getString(2);
    String depto = rs.getString(7);
    int tipousuario= rs.getInt(5);
//    JA_inicio inicion = new JA_inicio();
    
    if (tipousuario == 0) {
        JOptionPane.showMessageDialog(null,  "Bienvenido Usuario Jefe","",JOptionPane.INFORMATION_MESSAGE);
//         JA_inicio ini=new JA_inicio();
//         JA_inicio.lblcargojefe.setText(depto);
////         JA_inicio.lblnombrejefe.setText(nombre);
//  
//            ini.show(true);
    }else if(tipousuario == 1){
        JOptionPane.showMessageDialog(null,  "Bienvenido Usuario RH","",JOptionPane.INFORMATION_MESSAGE);
//          RH_Inicio rh=new RH_Inicio();
//            rh.show(true);   
//             JA_inicio ini=new JA_inicio();
//         JA_inicio.lblcargojefe.setText(depto);
//         JA_inicio.lblnombrejefe.setText(nombre);
    
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



