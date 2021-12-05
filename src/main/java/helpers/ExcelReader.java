package helpers;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {
    public static XSSFSheet readFile(String filePath) {
        FileInputStream file;
        try {
            file = new FileInputStream(filePath);
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            return workbook.getSheetAt(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
