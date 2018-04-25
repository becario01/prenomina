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
    private HSSFCellStyle headerStyle1;
    private HSSFCellStyle headerStyle2;
    private HSSFCellStyle headerStyle;
    private HSSFCellStyle oddRowStyle;
    private HSSFCellStyle evenRowStyle;

    // Integer to store the index of the next row
    private int rowIndex;

    public HSSFWorkbook generateExcel(int idSemana, String semana, String empleado, String cargo) throws SQLException {

        // Initialize rowIndex
        rowIndex = 0;

        // New Workbook
        workbook = new HSSFWorkbook();

        // Generate fonts
        headerFont = createFont(HSSFColor.WHITE.index, (short) 12, true);
        headerFont1 = createFont(HSSFColor.WHITE.index, (short) 19, true);
        contentFont = createFont(HSSFColor.BLACK.index, (short) 11, false);

        // Generate styles
        headerStyle1 = createStyle(headerFont1, HSSFCellStyle.ALIGN_CENTER, HSSFColor.BLACK.index, false , HSSFColor.BLACK.index);
        headerStyle2 = createStyle(headerFont, HSSFCellStyle.ALIGN_CENTER, HSSFColor.DARK_BLUE.index, false, HSSFColor.WHITE.index);
        headerStyle = createStyle(headerFont, HSSFCellStyle.ALIGN_CENTER, HSSFColor.DARK_BLUE.index, false, HSSFColor.DARK_BLUE.index);
        oddRowStyle = createStyle(contentFont, HSSFCellStyle.ALIGN_LEFT, HSSFColor.WHITE.index, false, HSSFColor.GREY_25_PERCENT.index);
        evenRowStyle = createStyle(contentFont, HSSFCellStyle.ALIGN_LEFT, HSSFColor.GREY_25_PERCENT.index, false, HSSFColor.WHITE.index);

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
        CellRangeAddress re = new CellRangeAddress(0, 0, 0, 7);
        sheet.addMergedRegion(re);
        for (int i = 8; i < 25; i++) {
            headerCell22 = headerRow0.createCell(i);
            headerCell22.setCellStyle(headerStyle1);

        }

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

        String sql = "SELECT DISTINCT  emp.empleadoId, emp.nombre, emp.depto, emp.puesto\n"
                + "                    from incidencias inc\n"
                + "                    INNER JOIN empleados emp on inc.empleadoId= emp.empleadoId\n"
                + "                    INNER JOIN semanas se on inc.idSemana= se.idSemana\n"
                + "                    where inc.idSemana='" + idSemana + "'";
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
                    JOptionPane.showMessageDialog(null, "Error al cargar los datos\n" + e);
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar los datos\n" + e);
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
