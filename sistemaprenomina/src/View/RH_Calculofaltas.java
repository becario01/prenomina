/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import BD.RegistrarIncidencia;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Becarios
 */
public class RH_Calculofaltas extends javax.swing.JFrame {
int x,y;
    public RH_Calculofaltas() {
        initComponents();
          this.setResizable(false);
          this.setLocationRelativeTo(null);
          this.getContentPane().setBackground(new java.awt.Color(233, 236, 241));
    }

    
    public void calcular(String inicialD){
        
         if(jDateChooser2.getDate()==null && jDateChooser1.getDate()==null){//devuelve verdadero si es ese mismo el botón que se ha pulsado
  JOptionPane.showMessageDialog(null,"Ambos campos estas vacios");
  }else if(jDateChooser1.getDate()==null){
       JOptionPane.showMessageDialog(null,"El campo Fecha fin esta vacio");
    }else if(jDateChooser2.getDate()==null){
                JOptionPane.showMessageDialog(null,"El campo Fecha inicio esta vacio");
        }else{          
    
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
          //fecha inicio
            String formato = jDateChooser2.getDateFormatString();
            Date dates = jDateChooser2.getDate();
            String fechainicio = String.valueOf(sdf.format(dates));
            //fecha fin
            String formatofin = jDateChooser1.getDateFormatString();
            Date datefin = jDateChooser1.getDate();
            String fechafin = String.valueOf(sdf.format(datefin));
            String india = inicialD;
            fechainicio = fechainicio.replace("-", "");
            fechafin = fechafin.replace("-", "");
            
  
       RegistrarIncidencia  rgsi = new RegistrarIncidencia();
       rgsi.calculoFaltas(fechainicio, fechafin,india);
    }
   
    }
      public static String obtenerDiaSemana(String fechaP) throws ParseException{
          String[] dias = {"D", "L", "Ma", "Mi", "J", "V", "S"};
          String aux = "";
          fechaP = fechaP.replaceAll(" ", "");
          SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
          if (fechaP.equalsIgnoreCase("")) {

          } else {
              String dateInString = fechaP;
              String[] dates = dateInString.split("-");
              String año = dates[0];
              String mes = dates[1];
              String dia = dates[2];
              String fecha = dia + "-" + mes + "-" + año;
              fecha = fecha.replaceAll(" ", "");

              java.util.Date date = formatter.parse(fecha);
              int numeroDia = 0;
              Calendar cal = Calendar.getInstance();
              cal.setTime(date);
              numeroDia = cal.get(Calendar.DAY_OF_WEEK);

              aux = dias[numeroDia - 1];
          }
          return aux;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        pnDia = new javax.swing.JPanel();
        btnLunes = new javax.swing.JButton();
        btnDomingo = new javax.swing.JButton();
        btnJueves = new javax.swing.JButton();
        btnMiercoles = new javax.swing.JButton();
        btnMartes = new javax.swing.JButton();
        btnViernes = new javax.swing.JButton();
        btnSabado = new javax.swing.JButton();
        lbllunes = new javax.swing.JLabel();
        lblviernes = new javax.swing.JLabel();
        lblmartes = new javax.swing.JLabel();
        lblsabado = new javax.swing.JLabel();
        lblmiercoles = new javax.swing.JLabel();
        lbldomingo = new javax.swing.JLabel();
        lbljueves = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 90, 249, -1));
        getContentPane().add(jDateChooser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 90, 249, -1));

        pnDia.setBackground(new java.awt.Color(233, 236, 241));

        btnLunes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/calendarL.png"))); // NOI18N
        btnLunes.setContentAreaFilled(false);
        btnLunes.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/calendarLO.png"))); // NOI18N
        btnLunes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLunesActionPerformed(evt);
            }
        });

        btnDomingo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/calendarD.png"))); // NOI18N
        btnDomingo.setContentAreaFilled(false);
        btnDomingo.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/calendarDO.png"))); // NOI18N
        btnDomingo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDomingoActionPerformed(evt);
            }
        });

        btnJueves.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/calendarJ.png"))); // NOI18N
        btnJueves.setContentAreaFilled(false);
        btnJueves.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/calendarJO.png"))); // NOI18N
        btnJueves.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJuevesActionPerformed(evt);
            }
        });

        btnMiercoles.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/calendarMI.png"))); // NOI18N
        btnMiercoles.setContentAreaFilled(false);
        btnMiercoles.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/calendarMIO.png"))); // NOI18N
        btnMiercoles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMiercolesActionPerformed(evt);
            }
        });

        btnMartes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/calendarMA.png"))); // NOI18N
        btnMartes.setContentAreaFilled(false);
        btnMartes.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/calendarMAO.png"))); // NOI18N
        btnMartes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMartesActionPerformed(evt);
            }
        });

        btnViernes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/calendarV.png"))); // NOI18N
        btnViernes.setContentAreaFilled(false);
        btnViernes.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/calendarVO.png"))); // NOI18N
        btnViernes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViernesActionPerformed(evt);
            }
        });

        btnSabado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/calendarS.png"))); // NOI18N
        btnSabado.setContentAreaFilled(false);
        btnSabado.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/calendarSO.png"))); // NOI18N
        btnSabado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSabadoActionPerformed(evt);
            }
        });

        lbllunes.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbllunes.setText("Lunes");

        lblviernes.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblviernes.setText("Viernes");

        lblmartes.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblmartes.setText("Martes");

        lblsabado.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblsabado.setText("Sábado");

        lblmiercoles.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblmiercoles.setText("Miércoles");

        lbldomingo.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbldomingo.setText("Domingo");

        lbljueves.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbljueves.setText("Jueves");

        javax.swing.GroupLayout pnDiaLayout = new javax.swing.GroupLayout(pnDia);
        pnDia.setLayout(pnDiaLayout);
        pnDiaLayout.setHorizontalGroup(
            pnDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnDiaLayout.createSequentialGroup()
                .addGroup(pnDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnDiaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnLunes, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbllunes))
                    .addGroup(pnDiaLayout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(btnViernes, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblviernes, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(226, 226, 226)
                        .addComponent(btnSabado, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblsabado))
                    .addGroup(pnDiaLayout.createSequentialGroup()
                        .addGap(291, 291, 291)
                        .addComponent(btnMartes, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblmartes, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(121, 121, 121)
                        .addComponent(btnMiercoles, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblmiercoles)))
                .addGroup(pnDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnDiaLayout.createSequentialGroup()
                        .addGap(133, 133, 133)
                        .addComponent(btnJueves, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbljueves)
                        .addGap(50, 50, 50))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnDiaLayout.createSequentialGroup()
                        .addComponent(btnDomingo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbldomingo)
                        .addGap(133, 133, 133))))
        );
        pnDiaLayout.setVerticalGroup(
            pnDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnDiaLayout.createSequentialGroup()
                .addGroup(pnDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnDiaLayout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addGroup(pnDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnMartes)
                            .addComponent(btnMiercoles))
                        .addGroup(pnDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnDiaLayout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(lblsabado))
                            .addGroup(pnDiaLayout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(btnSabado))))
                    .addGroup(pnDiaLayout.createSequentialGroup()
                        .addGroup(pnDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnDiaLayout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(lbljueves))
                            .addGroup(pnDiaLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnJueves, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(50, 50, 50)
                        .addComponent(lbldomingo))
                    .addGroup(pnDiaLayout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addGroup(pnDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblmiercoles)
                            .addComponent(lblmartes))
                        .addGap(28, 28, 28)
                        .addComponent(btnDomingo)))
                .addContainerGap(22, Short.MAX_VALUE))
            .addGroup(pnDiaLayout.createSequentialGroup()
                .addGroup(pnDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnDiaLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(lbllunes))
                    .addGroup(pnDiaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnLunes, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(pnDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnDiaLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(lblviernes))
                    .addGroup(pnDiaLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(btnViernes)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(pnDia, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 1020, -1));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel1.setText("Fecha inicio");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, -1, 20));

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel2.setText("Fecha fin");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 90, -1, -1));

        jPanel1.setBackground(new java.awt.Color(138, 229, 239));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/minimizar.png"))); // NOI18N
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 0, 32, 30));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/error.png"))); // NOI18N
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 0, 32, 30));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/regresar.png"))); // NOI18N
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 0, 32, 30));

        jLabel3.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabel3MouseDragged(evt);
            }
        });
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel3MousePressed(evt);
            }
        });
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1050, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1050, 52));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLunesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLunesActionPerformed
 if(evt.getSource().equals(btnLunes)){
    calcular(lbllunes.getText());
 }
    }//GEN-LAST:event_btnLunesActionPerformed

    private void btnDomingoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDomingoActionPerformed
 if(evt.getSource().equals(btnDomingo)){//devuelve verdadero si es ese mismo el botón que se ha pulsado
    calcular(lbldomingo.getText());
   }
      
    }//GEN-LAST:event_btnDomingoActionPerformed

    private void btnJuevesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJuevesActionPerformed
        if(evt.getSource().equals(btnJueves)){//devuelve verdadero si es ese mismo el botón que se ha pulsado
    calcular(lbljueves.getText());
   }
    }//GEN-LAST:event_btnJuevesActionPerformed

    private void btnMiercolesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMiercolesActionPerformed
     if(evt.getSource().equals(btnMiercoles)){//devuelve verdadero si es ese mismo el botón que se ha pulsado
    calcular(lblmiercoles.getText());
   } 
    }//GEN-LAST:event_btnMiercolesActionPerformed

    private void btnMartesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMartesActionPerformed
 if(evt.getSource().equals(btnMartes)){//devuelve verdadero si es ese mismo el botón que se ha pulsado
    calcular(lblmartes.getText());
   }
    }//GEN-LAST:event_btnMartesActionPerformed

    private void btnViernesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViernesActionPerformed
        if(evt.getSource().equals(btnViernes)){//devuelve verdadero si es ese mismo el botón que se ha pulsado
    calcular(lblviernes.getText());
   }
    }//GEN-LAST:event_btnViernesActionPerformed

    private void btnSabadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSabadoActionPerformed
   if(evt.getSource().equals(btnSabado)){//devuelve verdadero si es ese mismo el botón que se ha pulsado
    calcular(lblsabado.getText());
   }
    }//GEN-LAST:event_btnSabadoActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.setExtendedState(ICONIFIED);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        System.exit(0);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jLabel3MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseDragged
        this.setLocation(this.getLocation().x+evt.getX()-x, this.getLocation().y+evt.getY()-y);
    }//GEN-LAST:event_jLabel3MouseDragged

    private void jLabel3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_jLabel3MousePressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RH_Calculofaltas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RH_Calculofaltas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RH_Calculofaltas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RH_Calculofaltas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RH_Calculofaltas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDomingo;
    private javax.swing.JButton btnJueves;
    private javax.swing.JButton btnLunes;
    private javax.swing.JButton btnMartes;
    private javax.swing.JButton btnMiercoles;
    private javax.swing.JButton btnSabado;
    private javax.swing.JButton btnViernes;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbldomingo;
    private javax.swing.JLabel lbljueves;
    private javax.swing.JLabel lbllunes;
    private javax.swing.JLabel lblmartes;
    private javax.swing.JLabel lblmiercoles;
    private javax.swing.JLabel lblsabado;
    private javax.swing.JLabel lblviernes;
    private javax.swing.JPanel pnDia;
    // End of variables declaration//GEN-END:variables
}
