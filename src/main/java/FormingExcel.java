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

        Workbook wb = new HSSFWorkbook(); // открываем виртуальную книгу excel
        Sheet newAppartments = wb.createSheet(SHEET_NAME); // и задаем имя первого листа книги
        int rowNum= 0;
        Row row = newAppartments.createRow(rowNum); // создаем подписи к столбцам (это будет первая строчка в листе Excel файла)
        row.createCell(0).setCellValue("Город");
        row.createCell(1).setCellValue("Район");
        row.createCell(2).setCellValue("Адрес");
        row.createCell(3).setCellValue("Ст-я \n метро");
        row.createCell(4).setCellValue("Площадь");
        row.createCell(5).setCellValue("Этаж");
        row.createCell(6).setCellValue("Год \n постройки");
        row.createCell(7).setCellValue("Цена \n руб");
        row.createCell(8).setCellValue("Цена \n доллар");
        row.createCell(9).setCellValue("Цена \n евро");
        row.createCell(10).setCellValue("Дата \n размещения");

        HSSFFont font = (HSSFFont) wb.createFont(); // устанавливаем стили шрифта
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setFontHeight((short) 220);
        HSSFCellStyle style = (HSSFCellStyle) wb.createCellStyle();
        style.setFont(font); // применяем к этому стилю жирный шрифт
        for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
            row.getCell(i).setCellStyle(style); // применяем созданный выше стиль к каждой ячейке
        }
        row = newAppartments.createRow(++rowNum); // устанавливаем номер следующей строки в excel файле и переходим на эту строку
        int j = 0; // счетчик количества ячеек в строке (11 ячеек)
        for (String s : flatsList) {
            row.createCell(j).setCellValue(s); // записываем очередной элемент списка в Excel
            j++;
            if (j==11) {
                j=0;
                row = newAppartments.createRow(++rowNum); // если строка закончилась (11 ячеек) переходим на следующую строку
            }
        }
        for (int i = 0; i < 11; i++) { newAppartments.autoSizeColumn(i); }//выравнивание столбцов в таблице Excel по ширине
        try (OutputStream fileOut = new FileOutputStream("C:\\FUNIKOV\\ParserResult\\RealtorBook.xls")) {
            wb.write(fileOut);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        wb.close();
    }
}

