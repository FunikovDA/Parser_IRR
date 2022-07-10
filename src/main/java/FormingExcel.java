import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class FormingExcel {

    public FormingExcel(ArrayList<String> flatsList, String SHEET_NAME) throws IOException {

        Workbook wb = new HSSFWorkbook(); // ��������� ����������� ����� excel
        Sheet newAppartments = wb.createSheet(SHEET_NAME); // � ������ ��� ������� ����� �����
        int rowNum= 0;
        Row row = newAppartments.createRow(rowNum); // ������� ������� � �������� (��� ����� ������ ������� � ����� Excel �����)
        row.createCell(0).setCellValue("�����");
        row.createCell(1).setCellValue("�����");
        row.createCell(2).setCellValue("�����");
        row.createCell(3).setCellValue("��-� \n �����");
        row.createCell(4).setCellValue("�������");
        row.createCell(5).setCellValue("����");
        row.createCell(6).setCellValue("��� \n ���������");
        row.createCell(7).setCellValue("���� \n ���");
        row.createCell(8).setCellValue("���� \n ������");
        row.createCell(9).setCellValue("���� \n ����");
        row.createCell(10).setCellValue("���� \n ����������");

        HSSFFont font = (HSSFFont) wb.createFont(); // ������������� ����� ������
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setFontHeight((short) 220);
        HSSFCellStyle style = (HSSFCellStyle) wb.createCellStyle();
        style.setFont(font); // ��������� � ����� ����� ������ �����
        for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
            row.getCell(i).setCellStyle(style); // ��������� ��������� ���� ����� � ������ ������
        }
        row = newAppartments.createRow(++rowNum); // ������������� ����� ��������� ������ � excel ����� � ��������� �� ��� ������
        int j = 0; // ������� ���������� ����� � ������ (11 �����)
        for (String s : flatsList) {
            row.createCell(j).setCellValue(s); // ���������� ��������� ������� ������ � Excel
            j++;
            if (j==11) {
                j=0;
                row = newAppartments.createRow(++rowNum); // ���� ������ ����������� (11 �����) ��������� �� ��������� ������
            }
        }
        for (int i = 0; i < 11; i++) { newAppartments.autoSizeColumn(i); }//������������ �������� � ������� Excel �� ������
        try (OutputStream fileOut = new FileOutputStream("C:\\FUNIKOV\\ParserResult\\RealtorBook.xls")) {
            wb.write(fileOut);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        wb.close();
    }
}

