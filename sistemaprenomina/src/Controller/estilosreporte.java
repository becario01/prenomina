/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Conexion.Conexion1;
import static Controller.exportReporte.conn;
import static Controller.exportReporte.conn1;
import static Controller.exportReporte.dia;
import static Controller.exportReporte.rs;
import static Controller.exportReporte.rs1;
import static Controller.exportReporte.stmt;
import static Controller.exportReporte.stmt1;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;

public class estilosreporte {

    public static Connection conn;
    public static Connection conn1;
    public static Connection conn2;
    public static PreparedStatement stmt;
    public static PreparedStatement stmt2;
    public static ResultSet rs;
    public static ResultSet rs2;
    public static PreparedStatement stmt1;
    public static ResultSet rs1;
    private static Connection userConn;
    private static Connection userConn2;
    private static Connection userConn1;
    private HSSFWorkbook workbook;

    // Fonts
    private HSSFFont headerFont;
    private HSSFFont headerFont1;
    private HSSFFont contentFont;

    // Styles
//    private HSSFCellStyle ejem1;
//    private HSSFCellStyle ejem2;
//    private HSSFCellStyle ejem3;
//    private HSSFCellStyle ejem4;
//    private HSSFCellStyle ejem5;
//    private HSSFCellStyle ejem6;
//    private HSSFCellStyle ejem7;
//    private HSSFCellStyle ejem8;
//    private HSSFCellStyle ejem9;
//    private HSSFCellStyle ejem10;
//    private HSSFCellStyle ejem11;
//    private HSSFCellStyle ejem12;
//    private HSSFCellStyle ejem13;
//    private HSSFCellStyle ejem14;
//    private HSSFCellStyle ejem15;
//    private HSSFCellStyle ejem16;
//    private HSSFCellStyle ejem17;
//    private HSSFCellStyle ejem18;
//    private HSSFCellStyle ejem19;
//    private HSSFCellStyle ejem20;
//    private HSSFCellStyle ejem21;
//    private HSSFCellStyle ejem22;
//    private HSSFCellStyle ejem23;
//    private HSSFCellStyle ejem24;
//    private HSSFCellStyle ejem25;
//    private HSSFCellStyle ejem26;
//    private HSSFCellStyle ejem27;
//    private HSSFCellStyle ejem28;
//    private HSSFCellStyle ejem29;
//    private HSSFCellStyle ejem30;
//    private HSSFCellStyle ejem31;
//    private HSSFCellStyle ejem32;
//    private HSSFCellStyle ejem33;
//    private HSSFCellStyle ejem34;
//    private HSSFCellStyle ejem35;
//    private HSSFCellStyle ejem36;
//    private HSSFCellStyle ejem37;
//    private HSSFCellStyle ejem38;
//    private HSSFCellStyle ejem39;
//    private HSSFCellStyle ejem40;
//    private HSSFCellStyle ejem41;
//    private HSSFCellStyle ejem42;
//    private HSSFCellStyle ejem43;
//    private HSSFCellStyle ejem44;
//    private HSSFCellStyle ejem45;
//    private HSSFCellStyle ejem46;
//    private HSSFCellStyle ejem47;
//    private HSSFCellStyle ejem48;
//    private HSSFCellStyle ejem49;
//    private HSSFCellStyle ejem50;
//    private HSSFCellStyle ejem51;
    private HSSFCellStyle headerStyle1;
    private HSSFCellStyle headerStyle2;
    private HSSFCellStyle headerStyle;
    private HSSFCellStyle oddRowStyle;
    private HSSFCellStyle evenRowStyle;

    // Integer to store the index of the next row
    private int rowIndex;

    public HSSFWorkbook generateExcel(int idSemana, String semana, String empleado, String cargo, String nomdep) throws SQLException {

        // Initialize rowIndex
        rowIndex = 0;

        // New Workbook
        workbook = new HSSFWorkbook();

        // Generate fonts
        headerFont = createFont(HSSFColor.WHITE.index, (short) 12, true);
        headerFont1 = createFont(HSSFColor.WHITE.index, (short) 19, true);
        contentFont = createFont(HSSFColor.BLACK.index, (short) 11, false);

        // Generate styles
//        ejem1 = createStyle(headerFont1, HSSFCellStyle.ALIGN_CENTER, HSSFColor.AQUA.index, false, HSSFColor.BLACK.index);
//        ejem2 = createStyle(headerFont1, HSSFCellStyle.ALIGN_CENTER, HSSFColor.AUTOMATIC.index, false, HSSFColor.BLACK.index);
//        ejem3 = createStyle(headerFont1, HSSFCellStyle.ALIGN_CENTER, HSSFColor.BLACK.index, false, HSSFColor.BLACK.index);
//        ejem4 = createStyle(headerFont1, HSSFCellStyle.ALIGN_CENTER, HSSFColor.BLUE.index, false, HSSFColor.BLACK.index);
//        ejem5 = createStyle(headerFont1, HSSFCellStyle.ALIGN_CENTER, HSSFColor.BLUE_GREY.index, false, HSSFColor.BLACK.index);
//        ejem6 = createStyle(headerFont1, HSSFCellStyle.ALIGN_CENTER, HSSFColor.BRIGHT_GREEN.index, false, HSSFColor.BLACK.index);
//        ejem7 = createStyle(headerFont1, HSSFCellStyle.ALIGN_CENTER, HSSFColor.BROWN.index, false, HSSFColor.BLACK.index);
//        ejem8 = createStyle(headerFont1, HSSFCellStyle.ALIGN_CENTER, HSSFColor.CORAL.index, false, HSSFColor.BLACK.index);
//        ejem9 = createStyle(headerFont1, HSSFCellStyle.ALIGN_CENTER, HSSFColor.CORNFLOWER_BLUE.index, false, HSSFColor.BLACK.index);
//        ejem11 = createStyle(headerFont1, HSSFCellStyle.ALIGN_CENTER, HSSFColor.DARK_BLUE.index, false, HSSFColor.BLACK.index);
//        ejem12 = createStyle(headerFont1, HSSFCellStyle.ALIGN_CENTER, HSSFColor.DARK_GREEN.index, false, HSSFColor.BLACK.index);
//        ejem13 = createStyle(headerFont1, HSSFCellStyle.ALIGN_CENTER, HSSFColor.DARK_RED.index, false, HSSFColor.BLACK.index);
//        ejem14 = createStyle(headerFont1, HSSFCellStyle.ALIGN_CENTER, HSSFColor.DARK_TEAL.index, false, HSSFColor.BLACK.index);
//        ejem15 = createStyle(headerFont1, HSSFCellStyle.ALIGN_CENTER, HSSFColor.DARK_YELLOW.index, false, HSSFColor.BLACK.index);
//        ejem16 = createStyle(headerFont1, HSSFCellStyle.ALIGN_CENTER, HSSFColor.GOLD.index, false, HSSFColor.BLACK.index);
//        ejem17 = createStyle(headerFont1, HSSFCellStyle.ALIGN_CENTER, HSSFColor.GREEN.index, false, HSSFColor.BLACK.index);
//        ejem18 = createStyle(headerFont1, HSSFCellStyle.ALIGN_CENTER, HSSFColor.GREY_25_PERCENT.index, false, HSSFColor.BLACK.index);
//        ejem19 = createStyle(headerFont1, HSSFCellStyle.ALIGN_CENTER, HSSFColor.GREY_40_PERCENT.index, false, HSSFColor.BLACK.index);
//        ejem20 = createStyle(headerFont1, HSSFCellStyle.ALIGN_CENTER, HSSFColor.GREY_50_PERCENT.index, false, HSSFColor.BLACK.index);
//        ejem21 = createStyle(headerFont1, HSSFCellStyle.ALIGN_CENTER, HSSFColor.GREY_80_PERCENT.index, false, HSSFColor.BLACK.index);
//        ejem22 = createStyle(headerFont1, HSSFCellStyle.ALIGN_CENTER, HSSFColor.INDIGO.index, false, HSSFColor.BLACK.index);
//        ejem23 = createStyle(headerFont1, HSSFCellStyle.ALIGN_CENTER, HSSFColor.LAVENDER.index, false, HSSFColor.BLACK.index);
//        ejem24 = createStyle(headerFont1, HSSFCellStyle.ALIGN_CENTER, HSSFColor.LEMON_CHIFFON.index, false, HSSFColor.BLACK.index);
//        ejem25 = createStyle(headerFont1, HSSFCellStyle.ALIGN_CENTER, HSSFColor.LIGHT_BLUE.index, false, HSSFColor.BLACK.index);
//        ejem26 = createStyle(headerFont1, HSSFCellStyle.ALIGN_CENTER, HSSFColor.LIGHT_CORNFLOWER_BLUE.index, false, HSSFColor.BLACK.index);
//        ejem27 = createStyle(headerFont1, HSSFCellStyle.ALIGN_CENTER, HSSFColor.LIGHT_GREEN.index, false, HSSFColor.BLACK.index);
//        ejem28 = createStyle(headerFont1, HSSFCellStyle.ALIGN_CENTER, HSSFColor.LIGHT_ORANGE.index, false, HSSFColor.BLACK.index);
//        ejem29 = createStyle(headerFont1, HSSFCellStyle.ALIGN_CENTER, HSSFColor.LIGHT_TURQUOISE.index, false, HSSFColor.BLACK.index);
//        ejem30 = createStyle(headerFont1, HSSFCellStyle.ALIGN_CENTER, HSSFColor.LIGHT_YELLOW.index, false, HSSFColor.BLACK.index);
//        ejem31 = createStyle(headerFont1, HSSFCellStyle.ALIGN_CENTER, HSSFColor.LIME.index, false, HSSFColor.BLACK.index);
//        ejem32 = createStyle(headerFont1, HSSFCellStyle.ALIGN_CENTER, HSSFColor.MAROON.index, false, HSSFColor.BLACK.index);
//        ejem33 = createStyle(headerFont1, HSSFCellStyle.ALIGN_CENTER, HSSFColor.OLIVE_GREEN.index, false, HSSFColor.BLACK.index);
//        ejem34 = createStyle(headerFont1, HSSFCellStyle.ALIGN_CENTER, HSSFColor.ORANGE.index, false, HSSFColor.BLACK.index);
//        ejem35 = createStyle(headerFont1, HSSFCellStyle.ALIGN_CENTER, HSSFColor.ORCHID.index, false, HSSFColor.BLACK.index);
//        ejem36 = createStyle(headerFont1, HSSFCellStyle.ALIGN_CENTER, HSSFColor.PALE_BLUE.index, false, HSSFColor.BLACK.index);
//        ejem37 = createStyle(headerFont1, HSSFCellStyle.ALIGN_CENTER, HSSFColor.PINK.index, false, HSSFColor.BLACK.index);
//        ejem38 = createStyle(headerFont1, HSSFCellStyle.ALIGN_CENTER, HSSFColor.PLUM.index, false, HSSFColor.BLACK.index);
//        ejem39 = createStyle(headerFont1, HSSFCellStyle.ALIGN_CENTER, HSSFColor.RED.index, false, HSSFColor.BLACK.index);
//        ejem40 = createStyle(headerFont1, HSSFCellStyle.ALIGN_CENTER, HSSFColor.ROSE.index, false, HSSFColor.BLACK.index);
//        ejem41 = createStyle(headerFont1, HSSFCellStyle.ALIGN_CENTER, HSSFColor.ROYAL_BLUE.index, false, HSSFColor.BLACK.index);
//        ejem42 = createStyle(headerFont1, HSSFCellStyle.ALIGN_CENTER, HSSFColor.SEA_GREEN.index, false, HSSFColor.BLACK.index);
//        ejem43 = createStyle(headerFont1, HSSFCellStyle.ALIGN_CENTER, HSSFColor.SKY_BLUE.index, false, HSSFColor.BLACK.index);
//        ejem44 = createStyle(headerFont1, HSSFCellStyle.ALIGN_CENTER, HSSFColor.TAN.index, false, HSSFColor.BLACK.index);
//        ejem45 = createStyle(headerFont1, HSSFCellStyle.ALIGN_CENTER, HSSFColor.TEAL.index, false, HSSFColor.BLACK.index);
//        ejem46 = createStyle(headerFont1, HSSFCellStyle.ALIGN_CENTER, HSSFColor.TURQUOISE.index, false, HSSFColor.BLACK.index);
//        ejem47 = createStyle(headerFont1, HSSFCellStyle.ALIGN_CENTER, HSSFColor.VIOLET.index, false, HSSFColor.BLACK.index);
//        ejem48 = createStyle(headerFont1, HSSFCellStyle.ALIGN_CENTER, HSSFColor.WHITE.index, false, HSSFColor.BLACK.index);
//        ejem49 = createStyle(headerFont1, HSSFCellStyle.ALIGN_CENTER, HSSFColor.YELLOW.index, false, HSSFColor.BLACK.index);
//        ejem50 = createStyle(headerFont1, HSSFCellStyle.ALIGN_CENTER, HSSFColor.LIME.index, false, HSSFColor.BLACK.index);
//        ejem51 = createStyle(headerFont1, HSSFCellStyle.ALIGN_CENTER, HSSFColor.LIME.index, false, HSSFColor.BLACK.index);

        headerStyle1 = createStyle(headerFont1, HSSFCellStyle.ALIGN_CENTER, HSSFColor.BLUE.index, false, HSSFColor.BLACK.index);
        headerStyle2 = createStyle(headerFont, HSSFCellStyle.ALIGN_CENTER, HSSFColor.LIGHT_BLUE.index, false, HSSFColor.WHITE.index);
        headerStyle = createStyle(headerFont, HSSFCellStyle.ALIGN_CENTER, HSSFColor.LIGHT_BLUE.index, false, HSSFColor.DARK_BLUE.index);
        oddRowStyle = createStyle(contentFont, HSSFCellStyle.ALIGN_LEFT, HSSFColor.WHITE.index, false, HSSFColor.GREY_25_PERCENT.index);
        evenRowStyle = createStyle(contentFont, HSSFCellStyle.ALIGN_LEFT, HSSFColor.LIGHT_TURQUOISE.index, false, HSSFColor.WHITE.index);

        int fila = 3;
        // New sheet
        HSSFSheet sheet = workbook.createSheet("REPORTE");
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        row = sheet.createRow(0);

        // Table header dias
        HSSFRow headerRow2 = sheet.createRow(1);
        List<String> headerValues2 = exportReporte.getTableHeaders2(idSemana);

        HSSFCell headerCell2 = null;
        for (int i = 0; i < headerValues2.size(); i++) {
            headerCell2 = headerRow2.createCell(i);
            headerCell2.setCellStyle(headerStyle2);
            headerCell2.setCellValue(headerValues2.get(i));

        }

        HSSFRow headerRow0 = sheet.createRow(0);
        HSSFCell headerCell0 = null;
        HSSFCell headerCell22 = null;
        headerCell0 = headerRow0.createCell(0);
        headerCell0.setCellStyle(headerStyle1);
        headerCell0.setCellValue("REPORTE GENERAL          " + semana);
        CellRangeAddress re = new CellRangeAddress(0, 0, 0, 9);
        sheet.addMergedRegion(re);
        for (int i = 8; i < 25; i++) {
            headerCell22 = headerRow0.createCell(i);
            headerCell22.setCellStyle(headerStyle1);

        }

//////////////////////////////////////////////////////////////////////////////////        
//        HSSFRow headerRow7 = sheet.createRow(20);
//        headerCell0 = headerRow7.createCell(1);
//        headerCell0.setCellStyle(ejem1);
//        headerCell0.setCellValue("REPORTE GENERAL          ");
//        headerRow7 = sheet.createRow(21);
//        headerCell0 = headerRow7.createCell(1);
//        headerCell0.setCellStyle(ejem2);
//        headerCell0.setCellValue("REPORTE GENERAL          ");
//        headerRow7 = sheet.createRow(22);
//        headerCell0 = headerRow7.createCell(1);
//        headerCell0.setCellStyle(ejem3);
//        headerCell0.setCellValue("REPORTE GENERAL          ");
//        headerRow7 = sheet.createRow(23);
//        headerCell0 = headerRow7.createCell(1);
//        headerCell0.setCellStyle(ejem4);
//        headerCell0.setCellValue("REPORTE GENERAL          ");
//        headerRow7 = sheet.createRow(24);
//        headerCell0 = headerRow7.createCell(1);
//        headerCell0.setCellStyle(ejem5);
//        headerCell0.setCellValue("REPORTE GENERAL          ");
//        headerRow7 = sheet.createRow(25);
//        headerCell0 = headerRow7.createCell(1);
//        headerCell0.setCellStyle(ejem6);
//        headerCell0.setCellValue("REPORTE GENERAL          ");
//        headerRow7 = sheet.createRow(26);
//        headerCell0 = headerRow7.createCell(1);
//        headerCell0.setCellStyle(ejem7);
//        headerCell0.setCellValue("REPORTE GENERAL          ");
//        headerRow7 = sheet.createRow(27);
//        headerCell0 = headerRow7.createCell(1);
//        headerCell0.setCellStyle(ejem8);
//        headerCell0.setCellValue("REPORTE GENERAL          ");
//        headerRow7 = sheet.createRow(28);
//        headerCell0 = headerRow7.createCell(1);
//        headerCell0.setCellStyle(ejem9);
//        headerCell0.setCellValue("REPORTE GENERAL          ");
//        headerRow7 = sheet.createRow(29);
//        headerCell0 = headerRow7.createCell(1);
//        headerCell0.setCellStyle(ejem10);
//        headerCell0.setCellValue("REPORTE GENERAL          ");
//        headerRow7 = sheet.createRow(30);
//        headerCell0 = headerRow7.createCell(1);
//        headerCell0.setCellStyle(ejem11);
//        headerCell0.setCellValue("REPORTE GENERAL          ");
//        headerRow7 = sheet.createRow(31);
//        headerCell0 = headerRow7.createCell(1);
//        headerCell0.setCellStyle(ejem12);
//        headerCell0.setCellValue("REPORTE GENERAL          ");
//        headerRow7 = sheet.createRow(32);
//        headerCell0 = headerRow7.createCell(1);
//        headerCell0.setCellStyle(ejem13);
//        headerCell0.setCellValue("REPORTE GENERAL          ");
//        headerRow7 = sheet.createRow(33);
//        headerCell0 = headerRow7.createCell(1);
//        headerCell0.setCellStyle(ejem14);
//        headerCell0.setCellValue("REPORTE GENERAL          ");
//        headerRow7 = sheet.createRow(34);
//        headerCell0 = headerRow7.createCell(1);
//        headerCell0.setCellStyle(ejem15);
//        headerCell0.setCellValue("REPORTE GENERAL          ");
//        headerRow7 = sheet.createRow(35);
//        headerCell0 = headerRow7.createCell(1);
//        headerCell0.setCellStyle(ejem16);
//        headerCell0.setCellValue("REPORTE GENERAL          ");
//        headerRow7 = sheet.createRow(36);
//        headerCell0 = headerRow7.createCell(1);
//        headerCell0.setCellStyle(ejem17);
//        headerCell0.setCellValue("REPORTE GENERAL          ");
//        headerRow7 = sheet.createRow(37);
//        headerCell0 = headerRow7.createCell(1);
//        headerCell0.setCellStyle(ejem18);
//        headerCell0.setCellValue("REPORTE GENERAL          ");
//        headerRow7 = sheet.createRow(38);
//        headerCell0 = headerRow7.createCell(1);
//        headerCell0.setCellStyle(ejem19);
//        headerCell0.setCellValue("REPORTE GENERAL          ");
//        headerRow7 = sheet.createRow(39);
//        headerCell0 = headerRow7.createCell(1);
//        headerCell0.setCellStyle(ejem20);
//        headerCell0.setCellValue("REPORTE GENERAL          ");
//        headerRow7 = sheet.createRow(40);
//        headerCell0 = headerRow7.createCell(1);
//        headerCell0.setCellStyle(ejem21);
//        headerCell0.setCellValue("REPORTE GENERAL          ");
//        headerRow7 = sheet.createRow(41);
//        headerCell0 = headerRow7.createCell(1);
//        headerCell0.setCellStyle(ejem22);
//        headerCell0.setCellValue("REPORTE GENERAL          ");
//        headerRow7 = sheet.createRow(42);
//        headerCell0 = headerRow7.createCell(1);
//        headerCell0.setCellStyle(ejem23);
//        headerCell0.setCellValue("REPORTE GENERAL          ");
//        headerRow7 = sheet.createRow(43);
//        headerCell0 = headerRow7.createCell(1);
//        headerCell0.setCellStyle(ejem24);
//        headerCell0.setCellValue("REPORTE GENERAL          ");
//        headerRow7 = sheet.createRow(44);
//        headerCell0 = headerRow7.createCell(1);
//        headerCell0.setCellStyle(ejem25);
//        headerCell0.setCellValue("REPORTE GENERAL          ");
//        headerRow7 = sheet.createRow(45);
//        headerCell0 = headerRow7.createCell(1);
//        headerCell0.setCellStyle(ejem26);
//        headerCell0.setCellValue("REPORTE GENERAL          ");
//        headerRow7 = sheet.createRow(46);
//        headerCell0 = headerRow7.createCell(1);
//        headerCell0.setCellStyle(ejem27);
//        headerCell0.setCellValue("REPORTE GENERAL          ");
//        headerRow7 = sheet.createRow(47);
//        headerCell0 = headerRow7.createCell(1);
//        headerCell0.setCellStyle(ejem28);
//        headerCell0.setCellValue("REPORTE GENERAL          ");
//        headerRow7 = sheet.createRow(48);
//        headerCell0 = headerRow7.createCell(1);
//        headerCell0.setCellStyle(ejem29);
//        headerCell0.setCellValue("REPORTE GENERAL          ");
//        headerRow7 = sheet.createRow(49);
//        headerCell0 = headerRow7.createCell(1);
//        headerCell0.setCellStyle(ejem30);
//        headerCell0.setCellValue("REPORTE GENERAL          ");
//        headerRow7 = sheet.createRow(50);
//        headerCell0 = headerRow7.createCell(1);
//        headerCell0.setCellStyle(ejem31);
//        headerCell0.setCellValue("REPORTE GENERAL          ");
//        headerRow7 = sheet.createRow(51);
//        headerCell0 = headerRow7.createCell(1);
//        headerCell0.setCellStyle(ejem32);
//        headerCell0.setCellValue("REPORTE GENERAL          ");
//        headerRow7 = sheet.createRow(52);
//        headerCell0 = headerRow7.createCell(1);
//        headerCell0.setCellStyle(ejem33);
//        headerCell0.setCellValue("REPORTE GENERAL          ");
//        headerRow7 = sheet.createRow(53);
//        headerCell0 = headerRow7.createCell(1);
//        headerCell0.setCellStyle(ejem34);
//        headerCell0.setCellValue("REPORTE GENERAL          ");
//        headerRow7 = sheet.createRow(54);
//        headerCell0 = headerRow7.createCell(1);
//        headerCell0.setCellStyle(ejem35);
//        headerCell0.setCellValue("REPORTE GENERAL          ");
//        headerRow7 = sheet.createRow(55);
//        headerCell0 = headerRow7.createCell(1);
//        headerCell0.setCellStyle(ejem36);
//        headerCell0.setCellValue("REPORTE GENERAL          ");
//        headerRow7 = sheet.createRow(56);
//        headerCell0 = headerRow7.createCell(1);
//        headerCell0.setCellStyle(ejem37);
//        headerCell0.setCellValue("REPORTE GENERAL          ");
//        headerRow7 = sheet.createRow(57);
//        headerCell0 = headerRow7.createCell(1);
//        headerCell0.setCellStyle(ejem38);
//        headerCell0.setCellValue("REPORTE GENERAL          ");
//        headerRow7 = sheet.createRow(58);
//        headerCell0 = headerRow7.createCell(1);
//        headerCell0.setCellStyle(ejem39);
//        headerCell0.setCellValue("REPORTE GENERAL          ");
//        headerRow7 = sheet.createRow(59);
//        headerCell0 = headerRow7.createCell(1);
//        headerCell0.setCellStyle(ejem40);
//        headerCell0.setCellValue("REPORTE GENERAL          ");
//        headerRow7 = sheet.createRow(60);
//        headerCell0 = headerRow7.createCell(1);
//        headerCell0.setCellStyle(ejem41);
//        headerCell0.setCellValue("REPORTE GENERAL          ");
//        headerRow7 = sheet.createRow(61);
//        headerCell0 = headerRow7.createCell(1);
//        headerCell0.setCellStyle(ejem42);
//        headerCell0.setCellValue("REPORTE GENERAL          ");
//        headerRow7 = sheet.createRow(62);
//        headerCell0 = headerRow7.createCell(1);
//        headerCell0.setCellStyle(ejem43);
//        headerCell0.setCellValue("REPORTE GENERAL          ");
//        headerRow7 = sheet.createRow(63);
//        headerCell0 = headerRow7.createCell(1);
//        headerCell0.setCellStyle(ejem44);
//        headerCell0.setCellValue("REPORTE GENERAL          ");
//        headerRow7 = sheet.createRow(64);
//        headerCell0 = headerRow7.createCell(1);
//        headerCell0.setCellStyle(ejem45);
//        headerCell0.setCellValue("REPORTE GENERAL          ");
//        headerRow7 = sheet.createRow(65);
//        headerCell0 = headerRow7.createCell(1);
//        headerCell0.setCellStyle(ejem46);
//        headerCell0.setCellValue("REPORTE GENERAL          ");
//        headerRow7 = sheet.createRow(66);
//        headerCell0 = headerRow7.createCell(1);
//        headerCell0.setCellStyle(ejem47);
//        headerCell0.setCellValue("REPORTE GENERAL          ");
//        headerRow7 = sheet.createRow(67);
//        headerCell0 = headerRow7.createCell(1);
//        headerCell0.setCellStyle(ejem48);
//        headerCell0.setCellValue("REPORTE GENERAL          ");
//        headerRow7 = sheet.createRow(68);
//        headerCell0 = headerRow7.createCell(1);
//        headerCell0.setCellStyle(ejem49);
//        headerCell0.setCellValue("REPORTE GENERAL          ");
//        headerRow7 = sheet.createRow(69);
//        headerCell0 = headerRow7.createCell(1);
//        headerCell0.setCellStyle(ejem50);
//        headerCell0.setCellValue("REPORTE GENERAL          ");
//        headerRow7 = sheet.createRow(70);
//        headerCell0 = headerRow7.createCell(1);
//        headerCell0.setCellStyle(ejem51);
//        headerCell0.setCellValue("REPORTE GENERAL          ");

//////////////////////////////////////////////////////////////////////////////////        
//        row = sheet.createRow(3);
//       setBordersToMergedCells(sheet, re);
        // Table header
        HSSFRow headerRow = sheet.createRow(fila - 1);
        List<String> headerValues = exportReporte.getTableHeaders();

        HSSFCell headerCell = null;
        for (int i = 0; i < headerValues.size(); i++) {
            headerCell = headerRow.createCell(i);
            headerCell.setCellStyle(headerStyle);
            headerCell.setCellValue(headerValues.get(i));

        }

        CellRangeAddress region = new CellRangeAddress(fila - 2, fila - 2, 4, 6);
        sheet.addMergedRegion(region);
        region = new CellRangeAddress(fila - 2, fila - 2, 7, 9);
        sheet.addMergedRegion(region);
        region = new CellRangeAddress(fila - 2, fila - 2, 10, 12);
        sheet.addMergedRegion(region);
        region = new CellRangeAddress(fila - 2, fila - 2, 13, 15);
        sheet.addMergedRegion(region);
        region = new CellRangeAddress(fila - 2, fila - 2, 16, 18);
        sheet.addMergedRegion(region);
        region = new CellRangeAddress(fila - 2, fila - 2, 19, 21);
        sheet.addMergedRegion(region);
        region = new CellRangeAddress(fila - 2, fila - 2, 22, 24);
        sheet.addMergedRegion(region);
        region = new CellRangeAddress(1, 1, 0, 3);
        sheet.addMergedRegion(region);

        HSSFRow contentRow1 = null;

        HSSFRow contentRow2 = null;

        HSSFCell contentCell1 = null;
        HSSFCell contentCell2 = null;
        HSSFCell contentCell3 = null;
        HSSFCell contentCell4 = null;
        HSSFCell contentCell5 = null;
        HSSFCell contentCell6 = null;
        HSSFCell contentCell7 = null;
//        HSSFCell contentCell8 = null;
        HSSFCell contentCell9 = null;
        String sql = "";
        if (nomdep.contains("-SELECCIONE UNA OPCION-")) {

            sql = "SELECT DISTINCT  emp.empleadoId, emp.nombre, emp.depto, emp.puesto\n"
                    + "                    from incidencias inc\n"
                    + "                    INNER JOIN empleados emp on inc.empleadoId= emp.empleadoId\n"
                    + "                    INNER JOIN semanas se on inc.idSemana= se.idSemana\n"
                    + "                    where inc.idSemana='" + idSemana + "'";
        } else {
            sql = "SELECT DISTINCT  emp.empleadoId, emp.nombre, emp.depto, emp.puesto\n"
                    + "                    from incidencias inc\n"
                    + "                    INNER JOIN empleados emp on inc.empleadoId= emp.empleadoId\n"
                    + "                    INNER JOIN semanas se on inc.idSemana= se.idSemana\n"
                    + "                    where inc.idSemana='" + idSemana + "' and emp.depto='" + nomdep + "' ";
            headerCell0 = headerRow0.createCell(0);
            headerCell0.setCellStyle(headerStyle1);
            headerCell0.setCellValue("REPORTE GENERAL          " + semana + "                   " + nomdep);
        }
        String datos[] = new String[11];
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion1.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {

                datos[0] = rs.getString("empleadoId");
                datos[1] = rs.getString("nombre");
                datos[2] = rs.getString("depto");
                datos[3] = rs.getString("puesto");

                row = sheet.createRow(fila);

                contentRow1 = sheet.createRow(fila);

                for (int i = 4; i <= 24; i++) {
                    contentCell9 = contentRow1.createCell(i);
                    contentCell9.setCellStyle(rowIndex % 2 == 0 ? oddRowStyle : evenRowStyle);

                }
                contentCell1 = contentRow1.createCell(0);
                contentCell1.setCellValue(datos[0]);
                contentCell2 = contentRow1.createCell(1);
                contentCell2.setCellValue(datos[1]);
                contentCell3 = contentRow1.createCell(2);
                contentCell3.setCellValue(datos[2]);
                contentCell4 = contentRow1.createCell(3);
                contentCell4.setCellValue(datos[3]);

                String sql1 = "SELECT inc.actualizadoJA, inc.actualizadoRH, emp.empleadoId, emp.nombre, inc.fecha, re.entrada, re.salida, inc.horasTrab, nomin.nombre AS nombreinc, inc.comentario, inc.horasExtra \n"
                        + "from incidencias inc\n"
                        + "INNER JOIN empleados emp on inc.empleadoId= emp.empleadoId\n"
                        + "INNER JOIN registros re on inc.empleadoId=re.empleadoId \n"
                        + "INNER JOIN NomIncidencia nomin on  nomin.idNomIncidencia = inc.idNomIncidencia\n"
                        + "INNER JOIN semanas se on inc.idSemana= se.idSemana\n"
                        + "where inc.idSemana='" + idSemana + "' and inc.empleadoId='" + datos[0] + "' ";

                try {
                    conn1 = (this.userConn1 != null) ? this.userConn1 : Conexion1.getConnection();
                    stmt1 = conn1.prepareStatement(sql1);
                    rs1 = stmt1.executeQuery();

                    while (rs1.next()) {

                        datos[5] = rs1.getString("nombreinc");
                        datos[6] = rs1.getString("comentario");
                        datos[7] = rs1.getString("horasExtra");

                        if (dia(rs1.getString("fecha")) == 2) {

                            contentCell5 = contentRow1.createCell(4);
                            contentCell5.setCellValue(datos[5]);
                            contentCell6 = contentRow1.createCell(5);
                            contentCell6.setCellValue(datos[6]);
                            contentCell7 = contentRow1.createCell(6);
                            contentCell7.setCellValue(datos[7]);

                        } else if (dia(rs1.getString("fecha")) == 3) {

                            contentCell5 = contentRow1.createCell(7);
                            contentCell5.setCellValue(datos[5]);
                            contentCell6 = contentRow1.createCell(8);
                            contentCell6.setCellValue(datos[6]);
                            contentCell7 = contentRow1.createCell(9);
                            contentCell7.setCellValue(datos[7]);

                        } else if (dia(rs1.getString("fecha")) == 4) {

                            contentCell5 = contentRow1.createCell(10);
                            contentCell5.setCellValue(datos[5]);
                            contentCell6 = contentRow1.createCell(11);
                            contentCell6.setCellValue(datos[6]);
                            contentCell7 = contentRow1.createCell(12);
                            contentCell7.setCellValue(datos[7]);
                        } else if (dia(rs1.getString("fecha")) == 5) {

                            contentCell5 = contentRow1.createCell(13);
                            contentCell5.setCellValue(datos[5]);
                            contentCell6 = contentRow1.createCell(14);
                            contentCell6.setCellValue(datos[6]);
                            contentCell7 = contentRow1.createCell(15);
                            contentCell7.setCellValue(datos[7]);

                        } else if (dia(rs1.getString("fecha")) == 6) {

                            contentCell5 = contentRow1.createCell(16);
                            contentCell5.setCellValue(datos[5]);
                            contentCell6 = contentRow1.createCell(17);
                            contentCell6.setCellValue(datos[6]);
                            contentCell7 = contentRow1.createCell(18);
                            contentCell7.setCellValue(datos[7]);

                        } else if (dia(rs1.getString("fecha")) == 7) {

                            contentCell5 = contentRow1.createCell(19);
                            contentCell5.setCellValue(datos[5]);
                            contentCell6 = contentRow1.createCell(20);
                            contentCell6.setCellValue(datos[6]);
                            contentCell7 = contentRow1.createCell(21);
                            contentCell7.setCellValue(datos[7]);
                        } else if (dia(rs1.getString("fecha")) == 1) {

                            contentCell5 = contentRow1.createCell(22);
                            contentCell5.setCellValue(datos[5]);
                            contentCell6 = contentRow1.createCell(23);
                            contentCell6.setCellValue(datos[6]);
                            contentCell7 = contentRow1.createCell(24);
                            contentCell7.setCellValue(datos[7]);

                        }
                        contentCell1.setCellStyle(rowIndex % 2 == 0 ? oddRowStyle : evenRowStyle);
                        contentCell2.setCellStyle(rowIndex % 2 == 0 ? oddRowStyle : evenRowStyle);
                        contentCell3.setCellStyle(rowIndex % 2 == 0 ? oddRowStyle : evenRowStyle);
                        contentCell4.setCellStyle(rowIndex % 2 == 0 ? oddRowStyle : evenRowStyle);
                        contentCell5.setCellStyle(rowIndex % 2 == 0 ? oddRowStyle : evenRowStyle);
                        contentCell6.setCellStyle(rowIndex % 2 == 0 ? oddRowStyle : evenRowStyle);
                        contentCell7.setCellStyle(rowIndex % 2 == 0 ? oddRowStyle : evenRowStyle);

                    }

                    fila++;
                    rowIndex = fila;
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error al cargar los datos\n" + e,"ERROR",JOptionPane.ERROR_MESSAGE);
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar los datos\n" + e,"ERROR",JOptionPane.ERROR_MESSAGE);
        } finally {
            Conexion1.close(rs);
            Conexion1.close(stmt);
            if (this.userConn == null) {
                Conexion1.close(conn);
            }
            Conexion1.close(rs1);
            Conexion1.close(stmt1);
            if (this.userConn1 == null) {
                Conexion1.close(conn1);
            }

        }

        HSSFRow headerRow00 = sheet.createRow(rowIndex++);
        String dato = empleado + "   " + cargo;
        String da[] = new String[5];
        da[1] = "Realizado por";
        da[2] = "Fecha y Hora";
        da[3] = dato;
        da[4] = fecha();

        HSSFCell headerCell00 = null;
        for (int i = 1; i < 3; i++) {
            headerCell00 = headerRow00.createCell(i);
            headerCell00.setCellStyle(headerStyle);
            headerCell00.setCellValue(da[i]);
        }
        HSSFRow headerRow000 = sheet.createRow(rowIndex++);
        HSSFCell headerCell000 = null;
        for (int i = 1; i < 3; i++) {

            headerCell000 = headerRow000.createCell(i);
            headerCell000.setCellStyle(oddRowStyle);
            headerCell000.setCellValue(da[i + 2]);
        }
        for (int i = 0; i < headerValues.size(); sheet.autoSizeColumn(i++));

        return workbook;
    }

    private HSSFFont createFont(short fontColor, short fontHeight, boolean fontBold) {

        HSSFFont font = workbook.createFont();
        font.setBold(fontBold);
        font.setColor(fontColor);
        font.setFontName("Arial");
        font.setFontHeightInPoints(fontHeight);

        return font;
    }

    private HSSFCellStyle createStyle(HSSFFont font, short cellAlign, short cellColor, boolean cellBorder, short cellBorderColor) {

        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        style.setAlignment(cellAlign);
        style.setFillForegroundColor(cellColor);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        if (cellBorder) {
            style.setBorderTop(HSSFCellStyle.BORDER_THIN);
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style.setBorderBottom(HSSFCellStyle.BORDER_THIN);

            style.setTopBorderColor(cellBorderColor);
            style.setLeftBorderColor(cellBorderColor);
            style.setRightBorderColor(cellBorderColor);
            style.setBottomBorderColor(cellBorderColor);
        }

        return style;

    }

    protected void setBordersToMergedCells(Sheet sheet, CellRangeAddress rangeAddress) {
        RegionUtil.setBorderTop(BorderStyle.MEDIUM, rangeAddress, sheet);
        RegionUtil.setBorderLeft(BorderStyle.MEDIUM, rangeAddress, sheet);
        RegionUtil.setBorderRight(BorderStyle.MEDIUM, rangeAddress, sheet);
        RegionUtil.setBorderBottom(BorderStyle.MEDIUM, rangeAddress, sheet);
    }

    public String fecha() {

        Date now = new Date(System.currentTimeMillis());
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat hour = new SimpleDateFormat("HH:mm:ss");

        String fecha = date.format(now) + "      " + hour.format(now);
        return fecha;
    }
}
