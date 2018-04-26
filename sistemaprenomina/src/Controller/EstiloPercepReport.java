/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Conexion.Conexion1;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import org.apache.poi.ss.util.CellRangeAddress;

/**
 *
 * @author Programacion 04
 */
public class EstiloPercepReport {

    static Connection conn;
    static PreparedStatement stmt;
    public static ResultSet rs;
    private static Connection userConn;
    // Excel work book
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

    /**
     * Make a new excel workbook with sheet that contains a stylized table
     *
     * @return
     */
    public HSSFWorkbook generateExcel(int semana, String nomsem, String empleado, String cargo) {

        // Initialize rowIndex
        rowIndex = 0;

        // New Workbook
        workbook = new HSSFWorkbook();

        // Generate fonts
        headerFont = createFont(HSSFColor.WHITE.index, (short) 12, true);
        headerFont1 = createFont(HSSFColor.WHITE.index, (short) 18, true);
        contentFont = createFont(HSSFColor.BLACK.index, (short) 11, false);

        // Generate styles
        headerStyle1 = createStyle(headerFont1, HSSFCellStyle.ALIGN_CENTER, HSSFColor.BLACK.index, true, HSSFColor.BLACK.index);
        headerStyle = createStyle(headerFont, HSSFCellStyle.ALIGN_CENTER, HSSFColor.DARK_BLUE.index, true, HSSFColor.DARK_BLUE.index);
        oddRowStyle = createStyle(contentFont, HSSFCellStyle.ALIGN_LEFT, HSSFColor.WHITE.index, true, HSSFColor.WHITE.index);
        evenRowStyle = createStyle(contentFont, HSSFCellStyle.ALIGN_LEFT, HSSFColor.GREY_25_PERCENT.index, true, HSSFColor.GREY_25_PERCENT.index);

        // New sheet
        HSSFSheet sheet = workbook.createSheet("PERCEPCIONES Y DEDUCCIONES");
        HSSFRow headerRow1 = sheet.createRow(rowIndex++);

        HSSFCell headerCell1 = null;
        HSSFCell headerCell2 = null;

        headerCell1 = headerRow1.createCell(0);
        headerCell1.setCellStyle(headerStyle1);
        headerCell1.setCellValue("PERCEPCIONES Y DEDUCCIONES        " + nomsem);
        CellRangeAddress re = new CellRangeAddress(0, 0, 0, 7);
        sheet.addMergedRegion(re);

        for (int i = 8; i < 13; i++) {
            headerCell2 = headerRow1.createCell(i);
            headerCell2.setCellStyle(headerStyle1);

        }
       
        
        
        // Table header
        HSSFRow headerRow = sheet.createRow(rowIndex++);
        List<String> headerValues = PercepcionesReport.getHeaders();

        HSSFCell headerCell = null;
        for (int i = 0; i < headerValues.size(); i++) {
            headerCell = headerRow.createCell(i);
            headerCell.setCellStyle(headerStyle);
            headerCell.setCellValue(headerValues.get(i));
        }

        // Table content
        HSSFRow contentRow = null;
        HSSFCell contentCell = null;

        // Obtain table content values
        List<List<String>> contentRowValues = PercepcionesReport.getContent(contador(semana), semana);
        for (List<String> rowValues : contentRowValues) {

            // At each row creation, rowIndex must grow one unit
            contentRow = sheet.createRow(rowIndex++);
            for (int i = 0; i < rowValues.size(); i++) {
                contentCell = contentRow.createCell(i);
                contentCell.setCellValue(rowValues.get(i));

                // Style depends on if row is odd or even
                contentCell.setCellStyle(rowIndex % 2 == 0 ? oddRowStyle : evenRowStyle);
            }
        }
 HSSFRow headerRow00 = sheet.createRow(rowIndex++);
 String datos= empleado+"   "+cargo;
 String da[]= new String[5];
 da[1]="Realizado por";
 da[2]="Fecha y Hora";
 da[3]=datos;
 da[4]=fecha();
 
        
        HSSFCell headerCell00 = null;
        for(int i=1; i<3;i++){
        headerCell00 = headerRow00.createCell(i);
        headerCell00.setCellStyle(headerStyle);
        headerCell00.setCellValue(da[i]);
        }
         HSSFRow headerRow000 = sheet.createRow(rowIndex++);
        HSSFCell headerCell000 = null;
        for(int i=1;i<3;i++){
       
        headerCell000 = headerRow000.createCell(i);
        headerCell000.setCellStyle(oddRowStyle);
        headerCell000.setCellValue(da[i+2]);
        }
        // Autosize columns
        for (int i = 0; i < headerValues.size(); sheet.autoSizeColumn(i++));

        return workbook;
    }

    /**
     * Create a new font on base workbook
     *
     * @param fontColor Font color (see {@link HSSFColor})
     * @param fontHeight Font height in points
     * @param fontBold Font is boldweight (<code>true</code>) or not
     * (<code>false</code>)
     *
     * @return New cell style
     */
    private HSSFFont createFont(short fontColor, short fontHeight, boolean fontBold) {

        HSSFFont font = workbook.createFont();
        font.setBold(fontBold);
        font.setColor(fontColor);
        font.setFontName("Arial");
        font.setFontHeightInPoints(fontHeight);

        return font;
    }

    /**
     * Create a style on base workbook
     *
     * @param font Font used by the style
     * @param cellAlign Cell alignment for contained text (see
     * {@link HSSFCellStyle})
     * @param cellColor Cell background color (see {@link HSSFColor})
     * @param cellBorder Cell has border (<code>true</code>) or not
     * (<code>false</code>)
     * @param cellBorderColor Cell border color (see {@link HSSFColor})
     *
     * @return New cell style
     */
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

    public static int contador(int semana) {

        int con = 0;
        String sql = "SELECT  em.empleadoId, em.nombre, per.per1, per.per2, per.per3, per.per4, per.per5, per.per6, per.per7, per.per8, per.per9, per.per10, per.per11 \n"
                + "FROM percepciones per \n"
                + "INNER JOIN empleados em on per.empleadoId=em.empleadoId\n"
                + "where per.idSemana='" + semana + "'";

        try {
            conn = Conexion1.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                con = rs.getRow();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar los datos\n" + e,"ERROR",JOptionPane.ERROR_MESSAGE);
        } finally {
            Conexion1.close(rs);
            Conexion1.close(stmt);
            if (userConn == null) {
                Conexion1.close(conn);
            }
        }

        return con;

    }
    public String fecha(){


Date now = new Date(System.currentTimeMillis());
SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
SimpleDateFormat hour = new SimpleDateFormat("HH:mm:ss");


String fecha=date.format(now)+"      "+hour.format(now);
return fecha;
    }

}
