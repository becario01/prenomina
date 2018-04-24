/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import BD.Nomincidencia;
import Conexion.Conexion;
import Controller.EJefes;
import Controller.Rincidencia;
import static View.Incidenciasgrupales.jtbdatosgrupos;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Becarios
 */
public class Incidenciasgrupales extends javax.swing.JFrame {
    Nomincidencia rin;
    DefaultComboBoxModel<Rincidencia> modeloselincidencia;
    public static ResultSet rs;
    private Connection userConn;
    private PreparedStatement st;
    Conexion con = new Conexion();
    Connection conn;
    PreparedStatement stmt;
    int x, y;


    /**
     * Creates new form Incidenciasgrupales
     */
    public Incidenciasgrupales() {
        modeloselincidencia = new DefaultComboBoxModel<Rincidencia>();
        rin = new Nomincidencia();
        initComponents();
//        Color myColor = new Color(255,255,208);
//        jtbdatosgrupos.setBackground(myColor);
        cargarModeloSem();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new java.awt.Color(229, 230, 234));
    }
    private void cargarModeloSem(){
            ArrayList<Rincidencia> listaSemanas;
        listaSemanas = rin.obtenerIncnidecnias();

        for(Rincidencia semana : listaSemanas){
            modeloselincidencia.addElement(semana);
        }
    }
public void selecSeman(String vsemana){
                 Connection conn = null;
                 PreparedStatement stmt = null;
                 ResultSet rs = null;
            try {
                 String sql = "SELECT *  from semanas WHERE semana = '"+vsemana+"'";
                  conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
                  stmt = conn.prepareStatement(sql);
                  rs = stmt.executeQuery();
                  
                while (rs.next()) {
                   int       id     =rs.getInt(1);
                   String    Semana =rs.getString(2);
                   String    fechal =rs.getString(3);
                   String    fecham =rs.getString(4);
                   String    fechami =rs.getString(5);
                   String    fechaj =rs.getString(6);
                   String    fechav =rs.getString(7);
                   String    fechas =rs.getString(8);
                   String    fechad =rs.getString(9);
                   String    estatus =rs.getString(10);
                   lblfechal.setText(fechal);
                   lblfecham.setText(fecham);
                   lblfechami.setText(fechami);
                   lblfechaj.setText(fechaj);
                   lblfechav.setText(fechav);
                   lblfechas.setText(fechas);
                   lblfechad.setText(fechad);

                   
                } 
        
             } catch (Exception e) {
                System.out.println(""+e);
             }finally{
                 Conexion.close(rs);
                  Conexion.close(stmt);
             }
             
            }




public int registrargrupos(int empleadoId,String dia,String fecha,int horasextra,String comentario,int idSemana,int idNomIncidencias,String horasTrab) throws SQLException{
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
          
        } finally {
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }

        return rows;
       
       
}     

public  String guardar(String fechas) throws SQLException{
          
    select_incidencia sin = new select_incidencia();

        String dia;
          try {
       dia = select_incidencia.obtenerDiaSemana(fechas);
         EJefes semana = (EJefes) JA_inicio.cmbSemana.getSelectedItem();
        Rincidencia incidencia = (Rincidencia)cmbincidencia.getSelectedItem();
        String fecha = fechas;
        String comentario = txtcomentario.getText();
        int horaextra = 1;
        String horastrab= "10";
         int idsemana = semana.getIdSemana();
        int tabla =  jtbdatosgrupos.getRowCount();
     
       
            
               for (int i = 0; i <tabla ; i++) {
                 String codigos = jtbdatosgrupos.getValueAt(i, 0).toString();
         int codigo = Integer.parseInt(codigos);
                   System.out.println(codigos);
             registrargrupos(codigo,dia, fecha, horaextra, comentario,semana.getIdSemana(),incidencia.getIdNomIncidencia(),horastrab);
        }
          } catch (ParseException ex) {
              Logger.getLogger(Incidenciasgrupales.class.getName()).log(Level.SEVERE, null, ex);
          }
       
         
    return fechas;
    
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jtbdatosgrupos = new javax.swing.JTable();
        Semna = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        cmbincidencia = new javax.swing.JComboBox();
        pnDia = new javax.swing.JPanel();
        btnLunes = new javax.swing.JButton();
        btnDomingo = new javax.swing.JButton();
        btnJueves = new javax.swing.JButton();
        btnMiercoles = new javax.swing.JButton();
        btnMartes = new javax.swing.JButton();
        btnViernes = new javax.swing.JButton();
        btnSabado = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        lblfechal = new javax.swing.JLabel();
        lblfecham = new javax.swing.JLabel();
        lblfechami = new javax.swing.JLabel();
        lblfechaj = new javax.swing.JLabel();
        lblfechav = new javax.swing.JLabel();
        lblfechas = new javax.swing.JLabel();
        lblfechad = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtcomentario = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(512, 100));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1000, 560));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtbdatosgrupos.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jtbdatosgrupos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "EmpleadoId", "Nombre"
            }
        ));
        jtbdatosgrupos.setEnabled(false);
        jtbdatosgrupos.setOpaque(false);
        jScrollPane2.setViewportView(jtbdatosgrupos);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 300, 134));

        Semna.setBackground(new java.awt.Color(0, 0, 0));
        Semna.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        getContentPane().add(Semna, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 60, 135, 20));

        jPanel1.setBackground(new java.awt.Color(138, 229, 239));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/prenomina/minimizar.png"))); // NOI18N
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 0, 32, 30));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/prenomina/error.png"))); // NOI18N
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 0, 32, 30));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/prenomina/regresar.png"))); // NOI18N
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 0, 32, 30));

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
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 52));

        cmbincidencia.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cmbincidencia.setModel(modeloselincidencia);
        getContentPane().add(cmbincidencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 80, 170, 30));

        pnDia.setBackground(new java.awt.Color(229, 230, 234));

        btnLunes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/calendarL.png"))); // NOI18N
        btnLunes.setBorder(null);
        btnLunes.setBorderPainted(false);
        btnLunes.setContentAreaFilled(false);
        btnLunes.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        btnLunes.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/calendarLO.png"))); // NOI18N
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
        btnMartes.setBorderPainted(false);
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

        jLabel7.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel7.setText("Lunes");

        jLabel14.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel14.setText("Martes");

        jLabel16.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel16.setText("Miercoles");

        jLabel18.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel18.setText("Jueves");

        jLabel19.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel19.setText("Viernes");

        jLabel20.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel20.setText("Sabado");

        jLabel21.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel21.setText("Domingo");

        lblfechal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lblfecham.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lblfechami.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblfechami.setForeground(new java.awt.Color(255, 255, 255));

        lblfechaj.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lblfechav.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lblfechas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lblfechad.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

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
                        .addGroup(pnDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(lblfechal, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnDiaLayout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(btnViernes, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(lblfechav, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnDiaLayout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(btnMartes, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(lblfecham, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(111, 111, 111)
                                .addComponent(btnMiercoles, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16)
                                    .addComponent(lblfechami, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pnDiaLayout.createSequentialGroup()
                                .addGap(166, 166, 166)
                                .addComponent(btnSabado, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel20)
                                    .addComponent(lblfechas, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnDiaLayout.createSequentialGroup()
                        .addComponent(btnDomingo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addComponent(lblfechad, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(188, 188, 188))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnDiaLayout.createSequentialGroup()
                        .addComponent(btnJueves, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblfechaj, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18))
                        .addGap(101, 101, 101))))
        );
        pnDiaLayout.setVerticalGroup(
            pnDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnDiaLayout.createSequentialGroup()
                .addGroup(pnDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnDiaLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(pnDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnDiaLayout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addGroup(pnDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnDiaLayout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblfecham, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnDiaLayout.createSequentialGroup()
                                        .addComponent(jLabel16)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblfechami, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(pnDiaLayout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addComponent(btnMartes))
                            .addGroup(pnDiaLayout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addComponent(btnMiercoles)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                        .addComponent(btnSabado, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnDiaLayout.createSequentialGroup()
                        .addGap(179, 179, 179)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblfechas, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnDiaLayout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addGroup(pnDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnDiaLayout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(jLabel21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblfechad, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnDiaLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnDomingo)
                                .addGap(44, 44, 44)))))
                .addContainerGap(38, Short.MAX_VALUE))
            .addGroup(pnDiaLayout.createSequentialGroup()
                .addGroup(pnDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnDiaLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblfechal, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnDiaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnDiaLayout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblfechaj, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnJueves, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLunes, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(pnDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnDiaLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblfechav, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnDiaLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(btnViernes, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(pnDia, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 940, -1));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel1.setText("Personas Seleccionadas");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, 190, 20));

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel2.setText("Incidencia para Asignar");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 80, 150, 30));

        txtcomentario.setColumns(20);
        txtcomentario.setRows(5);
        jScrollPane1.setViewportView(txtcomentario);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 150, 300, -1));

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel4.setText("Comentario");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 124, 90, 20));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.setExtendedState(ICONIFIED);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        System.exit(0);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
     DefaultTableModel model2 = (DefaultTableModel)jtbdatosgrupos.getModel();
     for (int i = 0; i < jtbdatosgrupos.getRowCount(); i++) {
              model2.removeRow(i);
                 i-=1;
            }
        this.setVisible(false);
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnLunesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLunesActionPerformed
           try {
            guardar(lblfechal.getText());
              JOptionPane.showMessageDialog(rootPane, "Registro exitoso");
        } catch (SQLException ex) {
            Logger.getLogger(Incidenciasgrupales.class.getName()).log(Level.SEVERE, null, ex);
        }



    }//GEN-LAST:event_btnLunesActionPerformed

    private void btnDomingoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDomingoActionPerformed
        try {
            guardar(lblfechad.getText());
            JOptionPane.showMessageDialog(rootPane, "Registro exitoso");

        } catch (SQLException ex) {
            Logger.getLogger(Incidenciasgrupales.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnDomingoActionPerformed

    private void btnJuevesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJuevesActionPerformed
        try {
            guardar(lblfechaj.getText());
            JOptionPane.showMessageDialog(rootPane, "Registro exitoso");

        } catch (SQLException ex) {
            Logger.getLogger(Incidenciasgrupales.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnJuevesActionPerformed

    private void btnMiercolesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMiercolesActionPerformed
        try {
            guardar(lblfechami.getText());
            JOptionPane.showMessageDialog(rootPane, "Registro exitoso");

        } catch (SQLException ex) {
            Logger.getLogger(Incidenciasgrupales.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnMiercolesActionPerformed

    private void btnMartesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMartesActionPerformed
        try {
            guardar(lblfecham.getText());
            JOptionPane.showMessageDialog(rootPane, "Registro exitoso");

        } catch (SQLException ex) {
            Logger.getLogger(Incidenciasgrupales.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnMartesActionPerformed

    private void btnViernesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViernesActionPerformed
        try {
            guardar(lblfechav.getText());
            JOptionPane.showMessageDialog(rootPane, "Registro exitoso");

        } catch (SQLException ex) {
            Logger.getLogger(Incidenciasgrupales.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }//GEN-LAST:event_btnViernesActionPerformed

    private void btnSabadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSabadoActionPerformed
        try {
            guardar(lblfechas.getText());
            JOptionPane.showMessageDialog(rootPane, "Registro exitoso");

        } catch (SQLException ex) {
            Logger.getLogger(Incidenciasgrupales.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSabadoActionPerformed

    private void jLabel3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MousePressed
         x = evt.getX();
         y = evt.getY();
    }//GEN-LAST:event_jLabel3MousePressed

    private void jLabel3MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseDragged
       this.setLocation(this.getLocation().x+evt.getX()-x, this.getLocation().y+evt.getY()-y);
    }//GEN-LAST:event_jLabel3MouseDragged

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
            java.util.logging.Logger.getLogger(Incidenciasgrupales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Incidenciasgrupales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Incidenciasgrupales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Incidenciasgrupales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Incidenciasgrupales().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel Semna;
    private javax.swing.JButton btnDomingo;
    private javax.swing.JButton btnJueves;
    private javax.swing.JButton btnLunes;
    private javax.swing.JButton btnMartes;
    private javax.swing.JButton btnMiercoles;
    private javax.swing.JButton btnSabado;
    private javax.swing.JButton btnViernes;
    private javax.swing.JComboBox cmbincidencia;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JTable jtbdatosgrupos;
    private javax.swing.JLabel lblfechad;
    private javax.swing.JLabel lblfechaj;
    private javax.swing.JLabel lblfechal;
    private javax.swing.JLabel lblfecham;
    private javax.swing.JLabel lblfechami;
    private javax.swing.JLabel lblfechas;
    private javax.swing.JLabel lblfechav;
    private javax.swing.JPanel pnDia;
    private javax.swing.JTextArea txtcomentario;
    // End of variables declaration//GEN-END:variables
}
