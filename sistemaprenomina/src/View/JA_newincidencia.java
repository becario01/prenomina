/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import BD.Rjefes;
import Conexion.Conexion;
import Controller.EJefes;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author Vertsequer
 */
public class JA_newincidencia extends javax.swing.JFrame {

    select_incidencia inci;
    EJefes ejf;
    Rjefes rjf;

    private String semana;
    public static ResultSet rs;
    private Connection userConn;
    private PreparedStatement st;
    Conexion con = new Conexion();
    Connection conn;
    PreparedStatement stmt;

    public JA_newincidencia() {  
        rjf = new Rjefes();
        ejf = new EJefes();
        inci = new select_incidencia();
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new java.awt.Color(51, 102, 255));
        pnDia.setVisible(false);
         

    }
public void botonesnew (){
    btnregresar.setToolTipText("Regrear ventana anterior");
    btnminimizar.setToolTipText("Minimizar ventana");
    btncerrar.setToolTipText("Cerrar");
}
    public void mostrardatos(Object cod, Object nombre, String semana,String nomjefe,String cargo) {
        String nom = (String) nombre;
        Object codi = cod;
        lblnomemp.setText(nom);
        lblempid.setText((String) codi);
        lblsemana.setText(semana);
        lblnombrej.setText(nomjefe);
        lblcargo.setText(cargo);
 
    }
    
   

    public void selecSeman(String vsemana) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT *  from semanas WHERE semana = '" + vsemana + "'";
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt(1);
                String Semana = rs.getString(2);
                String fechal = rs.getString(3);
                String fecham = rs.getString(4);
                String fechami = rs.getString(5);
                String fechaj = rs.getString(6);
                String fechav = rs.getString(7);
                String fechas = rs.getString(8);
                String fechad = rs.getString(9);
                String estatus = rs.getString(10);

           
                lblfechal.setText(fechal);
                lblfecham.setText(fecham);
                lblfechami.setText(fechami);
                lblfechaj.setText(fechaj);
                lblfechav.setText(fechav);
                lblfechas.setText(fechas);
                lblfechad.setText(fechad);

            }

        } catch (Exception e) {
            System.out.println("" + e);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }

    }

      public void blocquear(String fechal,String fechama,String fechami,String fechaj,String fechav,String fechas,String fechad) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date fechaInicial = dateFormat.parse("2018-04-26");
            java.util.Date fecha = new java.util.Date();
            java.util.Date fechaFinal = new java.util.Date();
            String date1 =dateFormat.format(fechaInicial);
            String date2 = dateFormat.format(fechaFinal);
        
            if ( fechal.equals(date2) || fechama.equals(date2)|| fechami.equals(date2)|| fechaj.equals(date2)|| fechav.equals(date2)|| fechas.equals(date2)|| fechad.equals(date2)) {
                
            } else {
                btnrango.setEnabled(false);
                btnDia.setEnabled(false);
                JOptionPane.showMessageDialog(rootPane,"Por motivos de seguridad el sistema esta "
                        + "inabilitado para semanas anteriores");
            }
        } catch (ParseException ex) {
            Logger.getLogger(JA_newincidencia.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        pnDia = new javax.swing.JPanel();
        btnLunes = new javax.swing.JButton();
        btnDomingo = new javax.swing.JButton();
        btnJueves = new javax.swing.JButton();
        btnMiercoles = new javax.swing.JButton();
        btnMartes = new javax.swing.JButton();
        btnViernes = new javax.swing.JButton();
        btnSabado = new javax.swing.JButton();
        lblfechal = new javax.swing.JLabel();
        lblfecham = new javax.swing.JLabel();
        lblfechami = new javax.swing.JLabel();
        lblfechaj = new javax.swing.JLabel();
        lblfechav = new javax.swing.JLabel();
        lblfechas = new javax.swing.JLabel();
        lblfechad = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        btnDia = new javax.swing.JButton();
        txtNombreEmp = new javax.swing.JLabel();
        txtCodEmp = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        lblnomemp = new javax.swing.JTextField();
        lblempid = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnminimizar = new javax.swing.JButton();
        btncerrar = new javax.swing.JButton();
        btnregresar = new javax.swing.JButton();
        lblcargo = new javax.swing.JTextField();
        lblnombrej = new javax.swing.JTextField();
        lblsemana = new javax.swing.JLabel();
        btnrango = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 102, 255));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(51, 102, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnDia.setBackground(new java.awt.Color(51, 102, 255));

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

        lblfechal.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lblfechal.setForeground(new java.awt.Color(255, 255, 255));

        lblfecham.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lblfecham.setForeground(new java.awt.Color(255, 255, 255));

        lblfechami.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lblfechami.setForeground(new java.awt.Color(255, 255, 255));

        lblfechaj.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lblfechaj.setForeground(new java.awt.Color(255, 255, 255));

        lblfechav.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lblfechav.setForeground(new java.awt.Color(255, 255, 255));

        lblfechas.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lblfechas.setForeground(new java.awt.Color(255, 255, 255));

        lblfechad.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lblfechad.setForeground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Lunes");

        jLabel22.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Viernes");

        jLabel15.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Martes");

        jLabel23.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Sabado");

        jLabel17.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Miercoles");

        jLabel24.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Domingo");

        jLabel25.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Jueves");

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
                            .addComponent(lblfechal, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)))
                    .addGroup(pnDiaLayout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(btnViernes, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblfechav, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(215, 215, 215)
                        .addComponent(btnSabado, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblfechas, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23)))
                    .addGroup(pnDiaLayout.createSequentialGroup()
                        .addGap(291, 291, 291)
                        .addComponent(btnMartes, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblfecham, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(121, 121, 121)
                        .addComponent(btnMiercoles, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblfechami, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))))
                .addGroup(pnDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnDiaLayout.createSequentialGroup()
                        .addGap(133, 133, 133)
                        .addComponent(btnJueves, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblfechaj, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25))
                        .addGap(36, 36, 36))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnDiaLayout.createSequentialGroup()
                        .addComponent(btnDomingo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblfechad, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24))
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
                                .addComponent(jLabel23)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblfechas, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnDiaLayout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(btnSabado))))
                    .addGroup(pnDiaLayout.createSequentialGroup()
                        .addGroup(pnDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnDiaLayout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel25)
                                .addGap(12, 12, 12)
                                .addComponent(lblfechaj, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnDiaLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnJueves, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(50, 50, 50)
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblfechad, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnDiaLayout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addGroup(pnDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnDiaLayout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblfechami, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnDiaLayout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblfecham, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDomingo)))
                .addContainerGap(22, Short.MAX_VALUE))
            .addGroup(pnDiaLayout.createSequentialGroup()
                .addGroup(pnDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnDiaLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblfechal, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnDiaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnLunes, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(pnDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnDiaLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jLabel22)
                        .addGap(5, 5, 5)
                        .addComponent(lblfechav, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnDiaLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(btnViernes)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel2.add(pnDia, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1020, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 158, 1020, 387));

        btnDia.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnDia.setText("Día");
        btnDia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDiaActionPerformed(evt);
            }
        });
        getContentPane().add(btnDia, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 62, -1));

        txtNombreEmp.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNombreEmp.setForeground(new java.awt.Color(255, 255, 255));
        txtNombreEmp.setText("Nombre del empleado: ");
        getContentPane().add(txtNombreEmp, new org.netbeans.lib.awtextra.AbsoluteConstraints(178, 60, -1, -1));

        txtCodEmp.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCodEmp.setForeground(new java.awt.Color(255, 255, 255));
        txtCodEmp.setText("EmpID: ");
        getContentPane().add(txtCodEmp, new org.netbeans.lib.awtextra.AbsoluteConstraints(178, 101, -1, 20));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 80, 315, 20));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 120, 190, 20));

        lblnomemp.setBackground(new java.awt.Color(51, 102, 255));
        lblnomemp.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblnomemp.setForeground(new java.awt.Color(255, 255, 255));
        lblnomemp.setBorder(null);
        getContentPane().add(lblnomemp, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 60, 320, -1));

        lblempid.setBackground(new java.awt.Color(51, 102, 255));
        lblempid.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblempid.setForeground(new java.awt.Color(255, 255, 255));
        lblempid.setBorder(null);
        lblempid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lblempidActionPerformed(evt);
            }
        });
        getContentPane().add(lblempid, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 100, 190, 20));

        jPanel1.setBackground(new java.awt.Color(229, 230, 234));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/portafolio.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, -1, 40));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/user.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 40));

        btnminimizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/minimizar.png"))); // NOI18N
        btnminimizar.setBorderPainted(false);
        btnminimizar.setContentAreaFilled(false);
        btnminimizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnminimizarActionPerformed(evt);
            }
        });
        jPanel1.add(btnminimizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 0, 32, 30));

        btncerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/error.png"))); // NOI18N
        btncerrar.setBorderPainted(false);
        btncerrar.setContentAreaFilled(false);
        btncerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncerrarActionPerformed(evt);
            }
        });
        jPanel1.add(btncerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 0, 32, 30));

        btnregresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/regresar.png"))); // NOI18N
        btnregresar.setBorderPainted(false);
        btnregresar.setContentAreaFilled(false);
        btnregresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnregresarActionPerformed(evt);
            }
        });
        jPanel1.add(btnregresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 0, 32, 30));

        lblcargo.setBackground(new java.awt.Color(229, 230, 234));
        lblcargo.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblcargo.setForeground(new java.awt.Color(51, 102, 255));
        lblcargo.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(51, 102, 255)));
        lblcargo.setDisabledTextColor(new java.awt.Color(51, 102, 255));
        lblcargo.setEnabled(false);
        jPanel1.add(lblcargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 20, 330, 20));

        lblnombrej.setBackground(new java.awt.Color(229, 230, 234));
        lblnombrej.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblnombrej.setForeground(new java.awt.Color(51, 102, 255));
        lblnombrej.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(51, 102, 255)));
        lblnombrej.setDisabledTextColor(new java.awt.Color(51, 102, 255));
        lblnombrej.setEnabled(false);
        jPanel1.add(lblnombrej, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 320, 20));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1020, -1));

        lblsemana.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        lblsemana.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(lblsemana, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 50, 190, 40));

        btnrango.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnrango.setText("Rango");
        btnrango.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrangoActionPerformed(evt);
            }
        });
        getContentPane().add(btnrango, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 60, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDiaActionPerformed

        pnDia.setVisible(true);
        selecSeman(lblsemana.getText());
    }//GEN-LAST:event_btnDiaActionPerformed

    private void btnminimizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnminimizarActionPerformed
        this.setExtendedState(ICONIFIED);        // TODO add your handling code here:
    }//GEN-LAST:event_btnminimizarActionPerformed

    private void btncerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncerrarActionPerformed
        System.exit(0);        // TODO add your handling code here:
    }//GEN-LAST:event_btncerrarActionPerformed

    private void btnregresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnregresarActionPerformed
        JA_inicio ini = new JA_inicio();
        ini.setVisible(true);
        
        JA_inicio.lblnombrejefe.setText(lblnombrej.getText());
        JA_inicio.lblcargojefe.setText(lblcargo.getText());
        this.setVisible(false);
    }//GEN-LAST:event_btnregresarActionPerformed

    private void btnLunesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLunesActionPerformed
        select_incidencia inci = new select_incidencia();
        select_incidencia.lblFecha.setText(lblfechal.getText());
        select_incidencia.lblsem.setText(lblsemana.getText());
        inci.setVisible(true);

        inci.mostrardatosse(lblnombrej.getText(), lblempid.getText());
    }//GEN-LAST:event_btnLunesActionPerformed

    private void btnDomingoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDomingoActionPerformed

        select_incidencia inci = new select_incidencia();
        select_incidencia.lblFecha.setText(lblfechad.getText());
        select_incidencia.lblsem.setText(lblsemana.getText());

        inci.setVisible(true);
        inci.mostrardatosse(lblnombrej.getText(), lblempid.getText());
    }//GEN-LAST:event_btnDomingoActionPerformed

    private void btnJuevesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJuevesActionPerformed
        select_incidencia inci = new select_incidencia();
        select_incidencia.lblFecha.setText(lblfechaj.getText());
        select_incidencia.lblsem.setText(lblsemana.getText());

        inci.setVisible(true);
        inci.mostrardatosse(lblnombrej.getText(), lblempid.getText());
    }//GEN-LAST:event_btnJuevesActionPerformed

    private void btnMiercolesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMiercolesActionPerformed
        select_incidencia inci = new select_incidencia();
        select_incidencia.lblFecha.setText(lblfechami.getText());
        select_incidencia.lblsem.setText(lblsemana.getText());

        inci.setVisible(true);
        inci.mostrardatosse(lblnombrej.getText(), lblempid.getText());

    }//GEN-LAST:event_btnMiercolesActionPerformed

    private void btnMartesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMartesActionPerformed
        select_incidencia inci = new select_incidencia();
        select_incidencia.lblFecha.setText(lblfecham.getText());
        select_incidencia.lblsem.setText(lblsemana.getText());

        inci.setVisible(true);
        inci.mostrardatosse(lblnombrej.getText(), lblempid.getText());
    }//GEN-LAST:event_btnMartesActionPerformed

    private void btnViernesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViernesActionPerformed
        select_incidencia inci = new select_incidencia();
        select_incidencia.lblFecha.setText(lblfechav.getText());
        select_incidencia.lblsem.setText(lblsemana.getText());

        inci.setVisible(true);
        inci.mostrardatosse(lblnombrej.getText(), lblempid.getText());
    }//GEN-LAST:event_btnViernesActionPerformed

    private void btnSabadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSabadoActionPerformed
        select_incidencia inci = new select_incidencia();
        select_incidencia.lblFecha.setText(lblfechas.getText());
        select_incidencia.lblsem.setText(lblsemana.getText());

        inci.setVisible(true);
        inci.mostrardatosse(lblnombrej.getText(), lblempid.getText());
    }//GEN-LAST:event_btnSabadoActionPerformed

    private void btnrangoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrangoActionPerformed
    select_fechas slf = new select_fechas();
     select_fechas.lblsemana.setText(lblsemana.getText());
     select_fechas.idempleado.setText(lblempid.getText());
         slf.setVisible(true);

       
    }//GEN-LAST:event_btnrangoActionPerformed

    private void lblempidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblempidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblempidActionPerformed

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
            java.util.logging.Logger.getLogger(JA_newincidencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JA_newincidencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JA_newincidencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JA_newincidencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JA_newincidencia().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDia;
    private javax.swing.JButton btnDomingo;
    private javax.swing.JButton btnJueves;
    private javax.swing.JButton btnLunes;
    private javax.swing.JButton btnMartes;
    private javax.swing.JButton btnMiercoles;
    private javax.swing.JButton btnSabado;
    private javax.swing.JButton btnViernes;
    private javax.swing.JButton btncerrar;
    private javax.swing.JButton btnminimizar;
    private javax.swing.JButton btnrango;
    private javax.swing.JButton btnregresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel8;
    public static javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField lblcargo;
    private javax.swing.JTextField lblempid;
    private javax.swing.JLabel lblfechad;
    private javax.swing.JLabel lblfechaj;
    private javax.swing.JLabel lblfechal;
    private javax.swing.JLabel lblfecham;
    private javax.swing.JLabel lblfechami;
    private javax.swing.JLabel lblfechas;
    private javax.swing.JLabel lblfechav;
    private javax.swing.JTextField lblnombrej;
    private javax.swing.JTextField lblnomemp;
    private javax.swing.JLabel lblsemana;
    private javax.swing.JPanel pnDia;
    private javax.swing.JLabel txtCodEmp;
    private javax.swing.JLabel txtNombreEmp;
    // End of variables declaration//GEN-END:variables
}
