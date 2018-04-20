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
    private HSSFCellStyle headerStyle;
    private HSSFCellStyle oddRowStyle;
    private HSSFCellStyle evenRowStyle;

    // Integer to store the index of the next row
    private int rowIndex;

    public HSSFWorkbook generateExcel(int idSemana, String semana) throws SQLException {

        // Initialize rowIndex
        rowIndex = 0;

        // New Workbook
        workbook = new HSSFWorkbook();

        // Generate fonts
        headerFont1 = createFont(HSSFColor.BLACK.index, (short) 19, true);
        headerFont = createFont(HSSFColor.WHITE.index, (short) 12, true);
        contentFont = createFont(HSSFColor.BLACK.index, (short) 10, false);

        // Generate styles
        headerStyle1 = createStyle(headerFont1, HSSFCellStyle.ALIGN_CENTER, HSSFColor.BLUE.index, true, HSSFColor.WHITE.index);
        headerStyle = createStyle(headerFont, HSSFCellStyle.ALIGN_CENTER, HSSFColor.DARK_BLUE.index, true, HSSFColor.DARK_BLUE.index);
        oddRowStyle = createStyle(contentFont, HSSFCellStyle.ALIGN_LEFT, HSSFColor.GREY_25_PERCENT.index, true, HSSFColor.WHITE.index);
        evenRowStyle = createStyle(contentFont, HSSFCellStyle.ALIGN_LEFT, HSSFColor.GREY_40_PERCENT.index, true, HSSFColor.GREY_80_PERCENT.index);
        int fila = 3;
        // New sheet
        HSSFSheet sheet = workbook.createSheet("REPORTE");
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        row = sheet.createRow(0);

        HSSFRow headerRow0 = sheet.createRow(0);

        HSSFCell headerCell0 = null;
        headerCell0 = headerRow0.createCell(0);
        headerCell0.setCellStyle(headerStyle);
        headerCell0.setCellValue("REPORTE GENERAL          "+semana);
        CellRangeAddress re = new CellRangeAddress(0, 0, 0, 7);
        sheet.addMergedRegion(re);
//        row = sheet.createRow(3);
//       setBordersToMergedCells(sheet, re);

        // Table header dias
        HSSFRow headerRow2 = sheet.createRow(fila - 2);
        List<String> headerValues2 = exportReporte.getTableHeaders2();

        HSSFCell headerCell2 = null;
        for (int i = 0; i < headerValues2.size(); i++) {
            headerCell2 = headerRow2.createCell(i);
            headerCell2.setCellStyle(headerStyle);
            headerCell2.setCellValue(headerValues2.get(i));

        }
        // Table header
        HSSFRow headerRow = sheet.createRow(fila - 1);
        List<String> headerValues = exportReporte.getTableHeaders();

        HSSFCell headerCell = null;
        for (int i = 0; i < headerValues.size(); i++) {
            headerCell = headerRow.createCell(i);
            headerCell.setCellStyle(headerStyle);
            headerCell.setCellValue(headerValues.get(i));

        }

        CellRangeAddress region = new CellRangeAddress(fila - 2, fila - 2, 4, 7);
        sheet.addMergedRegion(region);
        region = new CellRangeAddress(fila - 2, fila - 2, 8, 11);
        sheet.addMergedRegion(region);
        region = new CellRangeAddress(fila - 2, fila - 2, 12, 15);
        sheet.addMergedRegion(region);
        region = new CellRangeAddress(fila - 2, fila - 2, 16, 19);
        sheet.addMergedRegion(region);
        region = new CellRangeAddress(fila - 2, fila - 2, 20, 23);
        sheet.addMergedRegion(region);
        region = new CellRangeAddress(fila - 2, fila - 2, 24, 27);
        sheet.addMergedRegion(region);
        region = new CellRangeAddress(fila - 2, fila - 2, 28, 31);
        sheet.addMergedRegion(region);
         region = new CellRangeAddress(1,1, 0, 3);
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
        HSSFCell contentCell8 = null;
        HSSFCell contentCell9 = null;
        HSSFCell contentCell10 = null;
        HSSFCell contentCell11= null;
        HSSFCell contentCell12 = null;
        HSSFCell contentCell13 = null;

        // Table content
        HSSFRow contentRow = null;
        HSSFCell contentCell = null;

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
                for (int i = 4; i <= 31; i++) {
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
                
                
                
               
                
                
                

                String sql1 = "SELECT inc.actualizadoJA, inc.actualizadoRH, emp.empleadoId, emp.nombre, inc.fecha, re.entrada, re.salida, inc.horasTrab, nomin.nombre AS nombreinc, inc.comentario \n"
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
                        datos[4] = rs1.getString("fecha");
                        datos[5] = rs1.getString("horasTrab");
                        datos[6] = rs1.getString("nombreinc");
                        datos[7] = rs1.getString("comentario");

                        if (dia(rs1.getString("fecha")) == 2) {
                            contentCell5 = contentRow1.createCell(4);
                            contentCell5.setCellValue(datos[4]);
                            contentCell6 = contentRow1.createCell(5);
                            contentCell6.setCellValue(datos[5]);
                            contentCell7 = contentRow1.createCell(6);
                            contentCell7.setCellValue(datos[6]);
                            contentCell8 = contentRow1.createCell(7);
                            contentCell8.setCellValue(datos[7]);
                            
                        } else if (dia(rs1.getString("fecha")) == 3) {
                            contentCell5 = contentRow1.createCell(8);
                            contentCell5.setCellValue(datos[4]);
                            contentCell6 = contentRow1.createCell(9);
                            contentCell6.setCellValue(datos[5]);
                            contentCell7 = contentRow1.createCell(10);
                            contentCell7.setCellValue(datos[6]);
                            contentCell8 = contentRow1.createCell(11);
                            contentCell8.setCellValue(datos[7]);
                            

                        } else if (dia(rs1.getString("fecha")) == 4) {
                           contentCell5 = contentRow1.createCell(12);
                            contentCell5.setCellValue(datos[4]);
                            contentCell6 = contentRow1.createCell(13);
                            contentCell6.setCellValue(datos[5]);
                            contentCell7 = contentRow1.createCell(14);
                            contentCell7.setCellValue(datos[6]);
                            contentCell8 = contentRow1.createCell(15);
                            contentCell8.setCellValue(datos[7]);

                        } else if (dia(rs1.getString("fecha")) == 5) {
                            contentCell5 = contentRow1.createCell(16);
                            contentCell5.setCellValue(datos[4]);
                            contentCell6 = contentRow1.createCell(17);
                            contentCell6.setCellValue(datos[5]);
                            contentCell7 = contentRow1.createCell(18);
                            contentCell7.setCellValue(datos[6]);
                            contentCell8 = contentRow1.createCell(19);
                            contentCell8.setCellValue(datos[7]);

                        } else if (dia(rs1.getString("fecha")) == 6) {
                            contentCell5 = contentRow1.createCell(20);
                            contentCell5.setCellValue(datos[4]);
                            contentCell6 = contentRow1.createCell(21);
                            contentCell6.setCellValue(datos[5]);
                            contentCell7 = contentRow1.createCell(22);
                            contentCell7.setCellValue(datos[6]);
                            contentCell8 = contentRow1.createCell(23);
                            contentCell8.setCellValue(datos[7]);

                        } else if (dia(rs1.getString("fecha")) == 7) {
                            contentCell5 = contentRow1.createCell(24);
                            contentCell5.setCellValue(datos[4]);
                            contentCell6 = contentRow1.createCell(25);
                            contentCell6.setCellValue(datos[5]);
                            contentCell7 = contentRow1.createCell(26);
                            contentCell7.setCellValue(datos[6]);
                            contentCell8 = contentRow1.createCell(27);
                            contentCell8.setCellValue(datos[7]);

                        } else if (dia(rs1.getString("fecha")) == 1) {
                            contentCell5 = contentRow1.createCell(28);
                            contentCell5.setCellValue(datos[4]);
                            contentCell6 = contentRow1.createCell(29);
                            contentCell6.setCellValue(datos[5]);
                            contentCell7 = contentRow1.createCell(30);
                            contentCell7.setCellValue(datos[6]);
                            contentCell8 = contentRow1.createCell(31);
                            contentCell8.setCellValue(datos[7]);

                        }
                        contentCell1.setCellStyle(rowIndex % 2 == 0 ? oddRowStyle : evenRowStyle);
                        contentCell2.setCellStyle(rowIndex % 2 == 0 ? oddRowStyle : evenRowStyle);
                        contentCell3.setCellStyle(rowIndex % 2 == 0 ? oddRowStyle : evenRowStyle);
                        contentCell4.setCellStyle(rowIndex % 2 == 0 ? oddRowStyle : evenRowStyle);
                        contentCell5.setCellStyle(rowIndex % 2 == 0 ? oddRowStyle : evenRowStyle);
                        contentCell6.setCellStyle(rowIndex % 2 == 0 ? oddRowStyle : evenRowStyle);
                        contentCell7.setCellStyle(rowIndex % 2 == 0 ? oddRowStyle : evenRowStyle);
                        contentCell8.setCellStyle(rowIndex % 2 == 0 ? oddRowStyle : evenRowStyle);
                        

                    }

                    fila++;
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
}
