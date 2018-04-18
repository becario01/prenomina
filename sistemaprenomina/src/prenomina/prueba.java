/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prenomina;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author Vertsequer
 */
public class prueba {
    
    public static void main(String[] args) {
   SimpleDateFormat formateador = new SimpleDateFormat(
   "dd 'de' MMMM 'de' yyyy", new Locale("es_ES"));
   Date fechaDate = new Date();
   String fecha = formateador.format(fechaDate);
   System.out.println(fecha);
    }
    
    
    
}
