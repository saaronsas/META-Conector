package application;

import application.logiplex.ad.ActiveDirectory;
import application.logiplex.azuread.AzureAD;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static application.Config.*;

public class Main {

    private static HashMap[] dataExcel = new HashMap[]{new HashMap(), new HashMap(), new HashMap()};

    /**
     * Method main
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        emptyDirectory();
        chargeTypeExcel(dataExcel);
        for (int numberSheet = 0; numberSheet < dataExcel.length; numberSheet++) {
            if(dataExcel[numberSheet].size() > 0) {
                switch (dataExcel[numberSheet].get(1).toString().toUpperCase()) {
                    case "AZUREAD":
                        AzureAD azureAD = new AzureAD(numberSheet);
                        azureAD.createAzureAD();
                        break;
                    case "AD":
                        ActiveDirectory activeDirectory = new ActiveDirectory(numberSheet);
                        activeDirectory.createActiveDirectory();
                        break;
                }

            }
        }

    }

    /**
     * Method to charge the application type of the excel
     * @param dataMap Array of HashMap to save the data
     * @throws IOException
     */
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

    /**
     * Method to empty the directory and create the new one
     */
    private static void emptyDirectory() {
        File directory = new File(OUTPUT_XML_FILE_NAME);
        if (directory.exists()) {
            for (File file : directory.listFiles()) {
                for (File file1 : file.listFiles()) {
                    for (File file2 : file1.listFiles()) {
                        if (file2.delete()) {
                            System.out.println("File deleted: " + file2.getName());
                        }
                    }
                    if (file1.delete()) {
                        System.out.println("File deleted: " + file1.getName());
                    }
                }
                if (file.delete()) {
                    System.out.println("File deleted: " + file.getName());
                }
            }
        }
    }

}
