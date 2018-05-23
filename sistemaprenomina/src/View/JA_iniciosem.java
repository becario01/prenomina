/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import BD.RegistrarIncidencia;
import BD.Rjefes;
import Conexion.Conexion;
import Controller.EJefes;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Becarios
 */
public class JA_iniciosem extends javax.swing.JInternalFrame {

    private TableRowSorter trsFiltro;
    int x, y;
    public static int iduser;
    Rjefes rjf;
    RegistrarIncidencia ric;
    Incidenciasgrupales ing;
    JA_newincidencia inc;
    select_incidencia slc;
    int ultimoIndiceSeleccionado = 0;
    DefaultComboBoxModel<EJefes> modelosemanas;
    public static ResultSet rs;
    private Connection userConn;
    private PreparedStatement st;
    Conexion con = new Conexion();
    Connection conn;
    PreparedStatement stmt;
    DefaultTableModel modeloincidenciasjefe = new DefaultTableModel(null, getColumas());
    public String tipof = "Semana";

    /**
     * Creates new form JA_iniciosem
     */
    public JA_iniciosem() {
        slc = new select_incidencia();
        ing = new Incidenciasgrupales();
        inc = new JA_newincidencia();
        ric = new RegistrarIncidencia();
        rjf = new Rjefes();
        modelosemanas = new DefaultComboBoxModel<EJefes>();
        cargarModeloSem();
        initComponents();
        this.setResizable(false);
        this.getContentPane().setBackground(new java.awt.Color(51, 102, 255));
        tbIncidencias.setRowHeight(25);
        tbIncidencias.getColumnModel().getColumn(0).setMaxWidth(100);
        tbIncidencias.getColumnModel().getColumn(1).setMaxWidth(60);
        tbIncidencias.getColumnModel().getColumn(2).setMaxWidth(240);
        tbIncidencias.getColumnModel().getColumn(3).setMaxWidth(150);
        tbIncidencias.getColumnModel().getColumn(4).setMaxWidth(150);
        tbIncidencias.getColumnModel().getColumn(5).setMaxWidth(150);
        tbIncidencias.getColumnModel().getColumn(6).setMaxWidth(150);
        tbIncidencias.getColumnModel().getColumn(7).setMaxWidth(150);
        tbIncidencias.getColumnModel().getColumn(8).setMaxWidth(150);
        tbIncidencias.getColumnModel().getColumn(9).setMaxWidth(150);
//        JTableHeader anHeader = tbIncidencias.getTableHeader();
//        anHeader.setFont(new java.awt.Font("Segoe UI", 0, 12));
        tbIncidencias.setBorder(BorderFactory.createCompoundBorder());
        ;
    }

    public void modificarculumnas() {
        EJefes semana = (EJefes) JA_iniciosem.cmbSemana.getSelectedItem();

        fechaL.setText(semana.getFechaL());
        fecham.setText(semana.getFechaM());
        fechami.setText(semana.getFechaMi());
        fechaj.setText(semana.getFechaJ());
        fechav.setText(semana.getFechaV());
        fechas.setText(semana.getFechaS());
        fechad.setText(semana.getFechaD());

    }

    private void cargarModeloSem() {
        ArrayList<EJefes> listaSemanas;
        listaSemanas = rjf.obtenerSemanas();

        for (EJefes semana : listaSemanas) {
            modelosemanas.addElement(semana);
        }
    }

    private String[] getColumas() {
        String columna[] = {"Actualizado", "ID", "Nombre", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"};
        return columna;
    }

    public void filtroBusqueda(JTextField txt) {
        trsFiltro.setRowFilter(RowFilter.regexFilter(txt.getText()));
    }

    public void SetFilas() {
        tbIncidencias.setDefaultRenderer(Object.class, new EJefes());
        EJefes idsem = (EJefes) cmbSemana.getSelectedItem();

        try {
            String sql;
            sql = "SELECT emp.empleadoId ,\n"
                    + "emp.nombre, \n"
                    + "       STUFF(( SELECT ','+nomin.nombre \n"
                    + "               FROM  incidencias inc\n"
                    + "        INNER JOIN NomIncidencia nomin\n"
                    + "ON nomin.idNomIncidencia = inc.idNomIncidencia\n"
                    + "               WHERE\n"
                    + "                 emp.empleadoId = inc.empleadoId\n"
                    + "and  	inc.idSemana = '" + idsem.getIdSemana() + "'\n"
                    + "               order by fecha \n"
                    + "               FOR XML PATH('')\n"
                    + "             ) , 1, 1, '' )as datos,\n"
                    + "\n"
                    + "               STUFF(( SELECT ','+inc.dia\n"
                    + "               FROM  incidencias inc\n"
                    + "        INNER JOIN NomIncidencia nomin\n"
                    + "ON nomin.idNomIncidencia = inc.idNomIncidencia\n"
                    + "and  	inc.idSemana = '" + idsem.getIdSemana() + "'   WHERE\n"
                    + "                 emp.empleadoId = inc.empleadoId\n"
                    + "               order by fecha \n"
                    + "               FOR XML PATH('')\n"
                    + "             ) , 1, 1, '' )as dias\n"
                    + "\n"
                    + "  FROM  	empleados emp inner JOIN asignacion asg  on emp.empleadoId = asg.empleadoId\n"
                    + "where emp.estatus=1 AND  asg.idUser = '" + iduser + "'"
                    + "";
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            Object datos[] = new Object[10];
            while (rs.next()) {

                String FLunesInc = "";
                String FMartesInc = "";
                String FMiercolesInc = "";
                String FJuevesInc = "";
                String FViernesInc = "";
                String FSabadoInc = "";
                String FDomingoInc = "";

                String DL = "", DMA = "", DMI = "", DJ = "", DV = "", DS = "", DD = "";
                String LunesInc = "";
                String MartesInc = "";
                String MiercolesInc = "";
                String JuevesInc = "";
                String ViernesInc = "";
                String SabadoInc = "";
                String DomingoInc = "";

                if (rs.getString("datos") == null || rs.getString("datos") == "") {
                    String empid = rs.getString("empleadoId");
                    String nom = rs.getString("nombre");

                } else {
                    String empid = rs.getString("empleadoId");
                    String nom = rs.getString("nombre");
                    String Dias = rs.getString("datos");
                    String Fechas = rs.getString("dias");
                    if (Dias.contains(",") || Fechas.contains(",")) {

                        String[] days = Dias.split(",");
                        String[] dates = Fechas.split(",");

                        if (0 == days.length && 0 == dates.length) {
                            Dias += ", , , , , , , ,";
                            Fechas += ", , , , , , , ,";
                        }
                        if (1 == days.length && 1 == dates.length) {
                            Dias += " , , , , , , ,";
                            Fechas += " , , , , , , ,";
                        }
                        if (2 == days.length && 2 == dates.length) {
                            Dias += " , , , , , ,";
                            Fechas += " , , , , , ,";

                        }
                        if (3 == days.length && 3 == dates.length) {
                            Dias += " , , , , ,";
                            Fechas += " , , , , ,";

                        }
                        if (4 == days.length && 4 == dates.length) {
                            Dias += " , , , ,";
                            Fechas += " , , , ,";

                        }
                        if (5 == days.length && 5 == dates.length) {
                            Dias += " , , ,";
                            Fechas += " , , ,";

                        }
                        if (6 == days.length && 6 == dates.length) {
                            Dias += " , ,";
                            Fechas += " , ,";

                        }

                        days = Dias.split(",");
                        dates = Fechas.split(",");
                        FLunesInc = dates[0];
                        FMartesInc = dates[1];
                        FMiercolesInc = dates[2];
                        FJuevesInc = dates[3];
                        FViernesInc = dates[4];
                        FSabadoInc = dates[5];
                        FDomingoInc = dates[6];
                        FLunesInc = FLunesInc.replaceAll(" ", "");
                        FMartesInc = FMartesInc.replaceAll(" ", "");
                        FMiercolesInc = FMiercolesInc.replaceAll(" ", "");
                        FJuevesInc = FJuevesInc.replaceAll(" ", "");
                        FViernesInc = FViernesInc.replaceAll(" ", "");
                        FSabadoInc = FSabadoInc.replaceAll(" ", "");
                        FDomingoInc = FDomingoInc.replaceAll(" ", "");

//                            ¿
                        //lunes
                        if (FLunesInc.equalsIgnoreCase("L")) {
                            LunesInc = days[0];

                        } else if (FLunesInc.equalsIgnoreCase("Ma")) {
                            MartesInc = days[0];

                        } else if (FLunesInc.equalsIgnoreCase("Mi")) {
                            MiercolesInc = days[0];
                        } else if (FLunesInc.equalsIgnoreCase("J")) {
                            JuevesInc = days[0];

                        } else if (FLunesInc.equalsIgnoreCase("V")) {
                            ViernesInc = days[0];
                        } else if (FLunesInc.equalsIgnoreCase("S")) {
                            SabadoInc = days[0];
                        } else if (FLunesInc.equalsIgnoreCase("D")) {
                            DomingoInc = days[0];
                        }
                        //martes        
                        if (FMartesInc.equalsIgnoreCase("L")) {
                            LunesInc = days[1];

                        } else if (FMartesInc.equalsIgnoreCase("Ma")) {
                            MartesInc = days[1];

                        } else if (FMartesInc.equalsIgnoreCase("Mi")) {
                            MiercolesInc = days[1];
                        } else if (FMartesInc.equalsIgnoreCase("J")) {
                            JuevesInc = days[1];

                        } else if (FMartesInc.equalsIgnoreCase("V")) {
                            ViernesInc = days[1];
                        } else if (FMartesInc.equalsIgnoreCase("S")) {
                            SabadoInc = days[1];
                        } else if (FMartesInc.equalsIgnoreCase("D")) {
                            DomingoInc = days[1];
                        }

                        //miercoles 
                        if (FMiercolesInc.equalsIgnoreCase("L")) {
                            LunesInc = days[2];

                        } else if (FMiercolesInc.equalsIgnoreCase("Ma")) {
                            MartesInc = days[2];

                        } else if (FMiercolesInc.equalsIgnoreCase("Mi")) {
                            MiercolesInc = days[2];
                        } else if (FMiercolesInc.equalsIgnoreCase("J")) {
                            JuevesInc = days[2];

                        } else if (FMiercolesInc.equalsIgnoreCase("V")) {
                            ViernesInc = days[2];
                        } else if (FMiercolesInc.equalsIgnoreCase("S")) {
                            SabadoInc = days[2];
                        } else if (FMiercolesInc.equalsIgnoreCase("D")) {
                            DomingoInc = days[2];
                        }

                        //jueves
                        if (FJuevesInc.equalsIgnoreCase("L")) {
                            LunesInc = days[3];

                        } else if (FJuevesInc.equalsIgnoreCase("Ma")) {
                            MartesInc = days[3];

                        } else if (FJuevesInc.equalsIgnoreCase("Mi")) {
                            MiercolesInc = days[3];
                        } else if (FJuevesInc.equalsIgnoreCase("J")) {
                            JuevesInc = days[3];

                        } else if (FJuevesInc.equalsIgnoreCase("V")) {
                            ViernesInc = days[3];
                        } else if (FJuevesInc.equalsIgnoreCase("S")) {
                            SabadoInc = days[3];
                        } else if (FJuevesInc.equalsIgnoreCase("D")) {
                            DomingoInc = days[3];
                        }

                        //viernes 
                        if (FViernesInc.equalsIgnoreCase("L")) {
                            LunesInc = days[4];

                        } else if (FViernesInc.equalsIgnoreCase("Ma")) {
                            MartesInc = days[4];

                        } else if (FViernesInc.equalsIgnoreCase("Mi")) {
                            MiercolesInc = days[4];
                        } else if (FViernesInc.equalsIgnoreCase("J")) {
                            JuevesInc = days[4];

                        } else if (FViernesInc.equalsIgnoreCase("V")) {
                            ViernesInc = days[4];
                        } else if (FViernesInc.equalsIgnoreCase("S")) {
                            SabadoInc = days[4];
                        } else if (FViernesInc.equalsIgnoreCase("D")) {
                            DomingoInc = days[4];
                        }

                        //sabado
                        if (FSabadoInc.equalsIgnoreCase("L")) {
                            LunesInc = days[5];
                        } else if (FSabadoInc.equalsIgnoreCase("Ma")) {
                            MartesInc = days[5];

                        } else if (FSabadoInc.equalsIgnoreCase("Mi")) {
                            MiercolesInc = days[5];
                        } else if (FSabadoInc.equalsIgnoreCase("J")) {
                            JuevesInc = days[5];

                        } else if (FSabadoInc.equalsIgnoreCase("V")) {
                            ViernesInc = days[5];
                        } else if (FSabadoInc.equalsIgnoreCase("S")) {
                            SabadoInc = days[5];
                        } else if (FSabadoInc.equalsIgnoreCase("D")) {
                            DomingoInc = days[5];
                        }

                        //domingo
                        if (FDomingoInc.equalsIgnoreCase("L")) {
                            LunesInc = days[6];

                        } else if (FDomingoInc.equalsIgnoreCase("Ma")) {
                            MartesInc = days[6];

                        } else if (FDomingoInc.equalsIgnoreCase("Mi")) {
                            MiercolesInc = days[6];
                        } else if (FDomingoInc.equalsIgnoreCase("J")) {
                            JuevesInc = days[6];

                        } else if (FDomingoInc.equalsIgnoreCase("V")) {
                            ViernesInc = days[6];
                        } else if (FDomingoInc.equalsIgnoreCase("S")) {
                            SabadoInc = days[6];
                        } else if (FDomingoInc.equalsIgnoreCase("D")) {
                            DomingoInc = days[6];
                        }

                    } else {

                        String[] days = Dias.split(",");
                        String[] dates = Fechas.split(",");

                        if (0 == days.length && 0 == dates.length) {
                            Dias += ", , , , , , , ,";
                            Fechas += ", , , , , , , ,";
                        }
                        if (1 == days.length && 1 == dates.length) {
                            Dias += " , , , , , , ,";
                            Fechas += " , , , , , , ,";
                        }
                        if (2 == days.length && 2 == dates.length) {
                            Dias += " , , , , , ,";
                            Fechas += " , , , , , ,";

                        }
                        if (3 == days.length && 3 == dates.length) {
                            Dias += " , , , , ,";
                            Fechas += " , , , , ,";

                        }
                        if (4 == days.length && 4 == dates.length) {
                            Dias += " , , , ,";
                            Fechas += " , , , ,";

                        }
                        if (5 == days.length && 5 == dates.length) {
                            Dias += " , , ,";
                            Fechas += " , , ,";

                        }
                        if (6 == days.length && 6 == dates.length) {
                            Dias += " , ,";
                            Fechas += " , ,";

                        }

                        days = Dias.split(",");
                        dates = Fechas.split(",");
                        FLunesInc = dates[0];
                        FMartesInc = dates[1];
                        FMiercolesInc = dates[2];
                        FJuevesInc = dates[3];
                        FViernesInc = dates[4];
                        FSabadoInc = dates[5];
                        FDomingoInc = dates[6];
                        FLunesInc = FLunesInc.replaceAll(" ", "");
                        FMartesInc = FMartesInc.replaceAll(" ", "");
                        FMiercolesInc = FMiercolesInc.replaceAll(" ", "");
                        FJuevesInc = FJuevesInc.replaceAll(" ", "");
                        FViernesInc = FViernesInc.replaceAll(" ", "");
                        FSabadoInc = FSabadoInc.replaceAll(" ", "");
                        FDomingoInc = FDomingoInc.replaceAll(" ", "");

//                            ¿
                        //lunes
                        if (FLunesInc.equalsIgnoreCase("L")) {
                            LunesInc = days[0];

                        } else if (FLunesInc.equalsIgnoreCase("Ma")) {
                            MartesInc = days[0];

                        } else if (FLunesInc.equalsIgnoreCase("Mi")) {
                            MiercolesInc = days[0];
                        } else if (FLunesInc.equalsIgnoreCase("J")) {
                            JuevesInc = days[0];

                        } else if (FLunesInc.equalsIgnoreCase("V")) {
                            ViernesInc = days[0];
                        } else if (FLunesInc.equalsIgnoreCase("S")) {
                            SabadoInc = days[0];
                        } else if (FLunesInc.equalsIgnoreCase("D")) {
                            DomingoInc = days[0];
                        }
                        //martes        
                        if (FMartesInc.equalsIgnoreCase("L")) {
                            LunesInc = days[1];

                        } else if (FMartesInc.equalsIgnoreCase("Ma")) {
                            MartesInc = days[1];

                        } else if (FMartesInc.equalsIgnoreCase("Mi")) {
                            MiercolesInc = days[1];
                        } else if (FLunesInc.equalsIgnoreCase("J")) {
                            JuevesInc = days[1];

                        } else if (FMartesInc.equalsIgnoreCase("V")) {
                            ViernesInc = days[1];
                        } else if (FMartesInc.equalsIgnoreCase("S")) {
                            SabadoInc = days[1];
                        } else if (FMartesInc.equalsIgnoreCase("D")) {
                            DomingoInc = days[1];
                        }

                        //miercoles 
                        if (FMiercolesInc.equalsIgnoreCase("L")) {
                            LunesInc = days[2];

                        } else if (FMiercolesInc.equalsIgnoreCase("Ma")) {
                            MartesInc = days[2];

                        } else if (FMiercolesInc.equalsIgnoreCase("Mi")) {
                            MiercolesInc = days[2];
                        } else if (FLunesInc.equalsIgnoreCase("J")) {
                            JuevesInc = days[2];

                        } else if (FMiercolesInc.equalsIgnoreCase("V")) {
                            ViernesInc = days[2];
                        } else if (FMiercolesInc.equalsIgnoreCase("S")) {
                            SabadoInc = days[2];
                        } else if (FMiercolesInc.equalsIgnoreCase("D")) {
                            DomingoInc = days[2];
                        }

                        //jueves
                        if (FJuevesInc.equalsIgnoreCase("L")) {
                            LunesInc = days[3];

                        } else if (FJuevesInc.equalsIgnoreCase("Ma")) {
                            MartesInc = days[3];

                        } else if (FJuevesInc.equalsIgnoreCase("Mi")) {
                            MiercolesInc = days[3];
                        } else if (FJuevesInc.equalsIgnoreCase("J")) {
                            JuevesInc = days[3];

                        } else if (FJuevesInc.equalsIgnoreCase("V")) {
                            ViernesInc = days[3];
                        } else if (FJuevesInc.equalsIgnoreCase("S")) {
                            SabadoInc = days[3];
                        } else if (FJuevesInc.equalsIgnoreCase("D")) {
                            DomingoInc = days[3];
                        }

                        //viernes 
                        if (FViernesInc.equalsIgnoreCase("L")) {
                            LunesInc = days[4];

                        } else if (FViernesInc.equalsIgnoreCase("Ma")) {
                            MartesInc = days[4];

                        } else if (FViernesInc.equalsIgnoreCase("Mi")) {
                            MiercolesInc = days[4];
                        } else if (FViernesInc.equalsIgnoreCase("J")) {
                            JuevesInc = days[4];

                        } else if (FViernesInc.equalsIgnoreCase("V")) {
                            ViernesInc = days[4];
                        } else if (FViernesInc.equalsIgnoreCase("S")) {
                            SabadoInc = days[4];
                        } else if (FViernesInc.equalsIgnoreCase("D")) {
                            DomingoInc = days[4];
                        }

                        //sabado
                        if (FSabadoInc.equalsIgnoreCase("L")) {
                            LunesInc = days[5];
                        } else if (FSabadoInc.equalsIgnoreCase("Ma")) {
                            MartesInc = days[5];

                        } else if (FSabadoInc.equalsIgnoreCase("Mi")) {
                            MiercolesInc = days[5];
                        } else if (FSabadoInc.equalsIgnoreCase("J")) {
                            JuevesInc = days[5];

                        } else if (FSabadoInc.equalsIgnoreCase("V")) {
                            ViernesInc = days[5];
                        } else if (FSabadoInc.equalsIgnoreCase("S")) {
                            SabadoInc = days[5];
                        } else if (FSabadoInc.equalsIgnoreCase("D")) {
                            DomingoInc = days[5];
                        }

                        //domingo
                        if (FDomingoInc.equalsIgnoreCase("L")) {
                            LunesInc = days[6];

                        } else if (FDomingoInc.equalsIgnoreCase("Ma")) {
                            MartesInc = days[6];

                        } else if (FDomingoInc.equalsIgnoreCase("Mi")) {
                            MiercolesInc = days[6];
                        } else if (FDomingoInc.equalsIgnoreCase("J")) {
                            JuevesInc = days[6];

                        } else if (FDomingoInc.equalsIgnoreCase("V")) {
                            ViernesInc = days[6];
                        } else if (FDomingoInc.equalsIgnoreCase("S")) {
                            SabadoInc = days[6];
                        } else if (FDomingoInc.equalsIgnoreCase("D")) {
                            DomingoInc = days[6];
                        }

                    }

                }
                tbIncidencias.setDefaultRenderer(Object.class, new EJefes());
                if (rs.getString("datos") == null || rs.getString("datos") == "") {
                    datos[0] = new JLabel(new ImageIcon(getClass().getResource("/View/img/noactualizadoj.png")));
                } else {
                    datos[0] = new JLabel(new ImageIcon(getClass().getResource("/View/img/actulizadoj.png")));
                }
                datos[1] = rs.getString("empleadoId");
                datos[2] = rs.getString("nombre");
                datos[3] = LunesInc;
                datos[4] = MartesInc;
                datos[5] = MiercolesInc;
                datos[6] = JuevesInc;
                datos[7] = ViernesInc;
                datos[8] = SabadoInc;
                datos[9] = DomingoInc;

                modeloincidenciasjefe.addRow(datos);
            }

//                        tabla1.addRow(datos);
        } catch (Exception e) {
            System.out.println("" + e);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }

    }

    public void limpiar(DefaultTableModel tabla) {
        for (int i = 0; i < tabla.getRowCount(); i++) {
            tabla.removeRow(i);
            i -= 1;
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

        jPopupMenu1 = new javax.swing.JPopupMenu();
        pmiRegistrar = new javax.swing.JMenuItem();
        jPanel3 = new javax.swing.JPanel();
        fechad = new javax.swing.JLabel();
        fechaL = new javax.swing.JLabel();
        fecham = new javax.swing.JLabel();
        fechami = new javax.swing.JLabel();
        fechaj = new javax.swing.JLabel();
        fechav = new javax.swing.JLabel();
        fechas = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cmbSemana = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbIncidencias = new rojerusan.RSTableMetro();

        pmiRegistrar.setText("Insertar Incidencia");
        pmiRegistrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pmiRegistrarMouseClicked(evt);
            }
        });
        pmiRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pmiRegistrarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(pmiRegistrar);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setForeground(java.awt.Color.lightGray);
        setTitle("Incidencias por Semana");
        setPreferredSize(new java.awt.Dimension(1515, 535));

        jPanel3.setBackground(new java.awt.Color(238, 240, 245));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fechad.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        fechad.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fechad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel3.add(fechad, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 100, 140, 20));

        fechaL.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        fechaL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fechaL.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel3.add(fechaL, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 100, 140, 20));

        fecham.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        fecham.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fecham.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel3.add(fecham, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 100, 150, 20));

        fechami.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        fechami.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fechami.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel3.add(fechami, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 100, 140, 20));

        fechaj.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        fechaj.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fechaj.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel3.add(fechaj, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 100, 140, 20));

        fechav.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        fechav.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fechav.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel3.add(fechav, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 100, 140, 20));

        fechas.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        fechas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fechas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel3.add(fechas, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 100, 140, 20));

        txtBuscar.setBackground(new java.awt.Color(238, 240, 245));
        txtBuscar.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        txtBuscar.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 102, 255)));
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
            }
        });
        jPanel3.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 250, 20));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/search1.png"))); // NOI18N
        jLabel4.setToolTipText("");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 40, 40));

        cmbSemana.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cmbSemana.setModel(modelosemanas);
        cmbSemana.setToolTipText("");
        cmbSemana.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbSemanaItemStateChanged(evt);
            }
        });
        cmbSemana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSemanaActionPerformed(evt);
            }
        });
        jPanel3.add(cmbSemana, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 247, -1));

        tbIncidencias= new rojerusan.RSTableMetro(){
            public boolean  isCellEditable(int rowIndex,int conlIndex ){
                return false;
            }
        };
        tbIncidencias.setModel(modeloincidenciasjefe);
        tbIncidencias.setAltoHead(20);
        tbIncidencias.setColorBackgoundHead(new java.awt.Color(51, 102, 255));
        tbIncidencias.setColorBordeFilas(new java.awt.Color(204, 204, 204));
        tbIncidencias.setColorBordeHead(new java.awt.Color(204, 204, 204));
        tbIncidencias.setColorFilasForeground1(new java.awt.Color(153, 153, 153));
        tbIncidencias.setColorFilasForeground2(new java.awt.Color(153, 153, 153));
        tbIncidencias.setColorSelBackgound(new java.awt.Color(0, 255, 255));
        tbIncidencias.setComponentPopupMenu(jPopupMenu1);
        tbIncidencias.setFuenteFilas(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tbIncidencias.setFuenteFilasSelect(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tbIncidencias.setFuenteHead(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        tbIncidencias.setGridColor(new java.awt.Color(153, 204, 0));
        tbIncidencias.setSelectionBackground(new java.awt.Color(0, 149, 142));
        tbIncidencias.setSurrendersFocusOnKeystroke(true);
        jScrollPane1.setViewportView(tbIncidencias);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 1380, 370));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 1394, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 66, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbSemanaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbSemanaItemStateChanged

    }//GEN-LAST:event_cmbSemanaItemStateChanged

    private void cmbSemanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSemanaActionPerformed
        limpiar(modeloincidenciasjefe);
        int sem = cmbSemana.getSelectedIndex();

        if (sem < 0) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar ", "Verificar", JOptionPane.WARNING_MESSAGE);
        } else {
            String Semana = cmbSemana.getSelectedItem().toString();
            EJefes semana = (EJefes) this.cmbSemana.getSelectedItem();
            modificarculumnas();
            SetFilas();

        }
    }//GEN-LAST:event_cmbSemanaActionPerformed

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
        txtBuscar.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {

                repaint();
                filtroBusqueda(txtBuscar);
            }
        });
        trsFiltro = new TableRowSorter(tbIncidencias.getModel());
        tbIncidencias.setRowSorter(trsFiltro);
    }//GEN-LAST:event_txtBuscarKeyTyped

    private void pmiRegistrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pmiRegistrarMouseClicked

    }//GEN-LAST:event_pmiRegistrarMouseClicked

    private void pmiRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pmiRegistrarActionPerformed
        JA_Menu me = new JA_Menu();
        int cuentaFilasSeleccionadas = tbIncidencias.getSelectedRowCount();
        if (cuentaFilasSeleccionadas == 1) {
            int fila = tbIncidencias.getSelectedRow();
            String empid = tbIncidencias.getValueAt(fila, 1).toString();
            String nombre = tbIncidencias.getValueAt(fila, 2).toString();
            String Semana = cmbSemana.getSelectedItem().toString();
            String nombrejefe = JA_Menu.lblnombrejefe.getText();
            String cargojefe = JA_Menu.lblcargojefe.getText();
            slc.mostrardatos(empid, nombre);
            slc.setVisible(true);
        } else {
            TableModel model1 = tbIncidencias.getModel();
            int indexs[] = tbIncidencias.getSelectedRows();
            String Semana = cmbSemana.getSelectedItem().toString();
            Object[] row = new Object[4];
            DefaultTableModel model2 = (DefaultTableModel) ing.jtbdatosgrupos.getModel();
            for (int i = 0; i < indexs.length; i++) {
                row[0] = model1.getValueAt(indexs[i], 1);
                row[1] = model1.getValueAt(indexs[i], 2);

                model2.addRow(row);
            }
            ing.show();
        }

    }//GEN-LAST:event_pmiRegistrarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JComboBox cmbSemana;
    private javax.swing.JLabel fechaL;
    private javax.swing.JLabel fechad;
    private javax.swing.JLabel fechaj;
    private javax.swing.JLabel fecham;
    private javax.swing.JLabel fechami;
    private javax.swing.JLabel fechas;
    private javax.swing.JLabel fechav;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem pmiRegistrar;
    private rojerusan.RSTableMetro tbIncidencias;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
