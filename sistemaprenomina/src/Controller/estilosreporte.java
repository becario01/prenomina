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
    private HSSFFont contentFont;

    // Styles
    private HSSFCellStyle headerStyle;
    private HSSFCellStyle oddRowStyle;
    private HSSFCellStyle evenRowStyle;

    // Integer to store the index of the next row
    private int rowIndex;

    public HSSFWorkbook generateExcel(int idSemana) throws SQLException {

        // Initialize rowIndex
        rowIndex = 0;

        // New Workbook
        workbook = new HSSFWorkbook();

        // Generate fonts
        headerFont = createFont(HSSFColor.WHITE.index, (short) 12, true);
        contentFont = createFont(HSSFColor.BLACK.index, (short) 10, false);

        // Generate styles
        headerStyle = createStyle(headerFont, HSSFCellStyle.ALIGN_CENTER, HSSFColor.BLUE_GREY.index, true, HSSFColor.WHITE.index);
        oddRowStyle = createStyle(contentFont, HSSFCellStyle.ALIGN_LEFT, HSSFColor.GREY_25_PERCENT.index, true, HSSFColor.GREY_80_PERCENT.index);
        evenRowStyle = createStyle(contentFont, HSSFCellStyle.ALIGN_LEFT, HSSFColor.GREY_40_PERCENT.index, true, HSSFColor.GREY_80_PERCENT.index);
int fila = 3;
        // New sheet
        HSSFSheet sheet = workbook.createSheet("REPORTE");
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        row = sheet.createRow(0);
        row.createCell(0).setCellValue("REPORTE GENERAL ");
        CellRangeAddress re = new CellRangeAddress(0, 0, 0, 7);
        sheet.addMergedRegion(re);
        row = sheet.createRow(3);
       setBordersToMergedCells(sheet, re);

        // Table header dias
        HSSFRow headerRow2 = sheet.createRow(fila-2);
        List<String> headerValues2 = exportReporte.getTableHeaders2();

        HSSFCell headerCell2 = null;
        for (int i = 0; i < headerValues2.size(); i++) {
            headerCell2 = headerRow2.createCell(i);
            headerCell2.setCellStyle(headerStyle);
            headerCell2.setCellValue(headerValues2.get(i));

        }
        // Table header
        HSSFRow headerRow = sheet.createRow(fila-1);
        List<String> headerValues = exportReporte.getTableHeaders();

        HSSFCell headerCell = null;
        for (int i = 0; i < headerValues.size(); i++) {
            headerCell = headerRow.createCell(i);
            headerCell.setCellStyle(headerStyle);
            headerCell.setCellValue(headerValues.get(i));

        }
        
        CellRangeAddress region = new CellRangeAddress(fila-2, fila-2, 4, 7);
        sheet.addMergedRegion(region);
        region = new CellRangeAddress(fila-2, fila-2, 8, 11);
        sheet.addMergedRegion(region);
        region = new CellRangeAddress(fila-2, fila-2, 12, 15);
        sheet.addMergedRegion(region);
        region = new CellRangeAddress(fila-2, fila-2, 16, 19);
        sheet.addMergedRegion(region);
        region = new CellRangeAddress(fila-2, fila-2, 20, 23);
        sheet.addMergedRegion(region);
        region = new CellRangeAddress(fila-2, fila-2, 24, 27);
        sheet.addMergedRegion(region);
        region = new CellRangeAddress(fila-2, fila-2, 28, 31);
        sheet.addMergedRegion(region);

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

                row.createCell(0).setCellValue(datos[0]);
                row.createCell(1).setCellValue(datos[1]);
                row.createCell(2).setCellValue(datos[2]);
                row.createCell(3).setCellValue(datos[3]);

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
                            row.createCell(4).setCellValue(datos[4]);
                            row.createCell(5).setCellValue(datos[5]);
                            row.createCell(6).setCellValue(datos[6]);
                            row.createCell(7).setCellValue(datos[7]);
                        } else if (dia(rs1.getString("fecha")) == 3) {
                            row.createCell(8).setCellValue(datos[4]);
                            row.createCell(9).setCellValue(datos[5]);
                            row.createCell(10).setCellValue(datos[6]);
                            row.createCell(11).setCellValue(datos[7]);

                        } else if (dia(rs1.getString("fecha")) == 4) {
                            row.createCell(12).setCellValue(datos[4]);
                            row.createCell(13).setCellValue(datos[5]);
                            row.createCell(14).setCellValue(datos[6]);
                            row.createCell(15).setCellValue(datos[7]);

                        } else if (dia(rs1.getString("fecha")) == 5) {
                            row.createCell(16).setCellValue(datos[4]);
                            row.createCell(17).setCellValue(datos[5]);
                            row.createCell(18).setCellValue(datos[6]);
                            row.createCell(19).setCellValue(datos[7]);

                        } else if (dia(rs1.getString("fecha")) == 6) {
                            row.createCell(20).setCellValue(datos[4]);
                            row.createCell(21).setCellValue(datos[5]);
                            row.createCell(22).setCellValue(datos[6]);
                            row.createCell(23).setCellValue(datos[7]);

                        } else if (dia(rs1.getString("fecha")) == 7) {
                            row.createCell(24).setCellValue(datos[4]);
                            row.createCell(25).setCellValue(datos[5]);
                            row.createCell(26).setCellValue(datos[6]);
                            row.createCell(27).setCellValue(datos[7]);

                        } else if (dia(rs1.getString("fecha")) == 1) {
                            row.createCell(28).setCellValue(datos[4]);
                            row.createCell(29).setCellValue(datos[5]);
                            row.createCell(30).setCellValue(datos[6]);
                            row.createCell(31).setCellValue(datos[7]);

                        }

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
