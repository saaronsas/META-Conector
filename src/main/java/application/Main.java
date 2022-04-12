package application;

import application.logiplex.azuread.AzureAD;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static application.logiplex.azuread.Config.INPUT_XLSX_FILE_NAME;


public class Main {

    private static HashMap[] dataExcel = new HashMap[]{new HashMap(), new HashMap(), new HashMap()};

    public static void main(String[] args) throws IOException {

        chargeTypeExcel(dataExcel);
        for (int numberSheet = 0; numberSheet < dataExcel.length; numberSheet++) {
            for (int i = 1; i <= dataExcel[numberSheet].size(); i++) {
                switch (dataExcel[numberSheet].get(i).toString().toUpperCase()) {
                    case "AZUREAD":
                        AzureAD azureAD = new AzureAD(numberSheet);
                        azureAD.createAzureAD();
                        break;
                    case "AD":
                        break;
                }

            }
        }

    }

    private static void chargeTypeExcel(Map[] dataMap) throws IOException {
        File file = new File(INPUT_XLSX_FILE_NAME);
        InputStream inp = new FileInputStream(file);
        Workbook wb = WorkbookFactory.create(inp);
        for (int i = 0; i < wb.getNumberOfSheets(); i++) {
            Iterator<Row> rowIt = wb.getSheetAt(i).rowIterator();
            int cellCount = 0;
            while (rowIt.hasNext()) {
                Row row = rowIt.next();
                if(cellCount != 0) {
                    Iterator<Cell> cellIterator = row.cellIterator();
                    dataMap[i].put(cellCount, cellIterator.next().getStringCellValue());
                }
                cellCount++;
            }
        }
    }

}
