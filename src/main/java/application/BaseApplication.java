package application;

import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import static application.Config.*;

public class BaseApplication {

    private static int sheed;

    /**
     * Constructor for objects of class AzureAD
     * @param sheed number of excel sheet
     * @throws IOException
     */
    public BaseApplication(int sheed) {
        this.sheed = sheed;
    }


    /**
     * Method to charge file from xml template
     * @param fileName name of file
     * @return file
     * @throws FileNotFoundException
     */
    public static Object chargeFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Object obj = new Scanner(file);
        //System.out.println("---- FILE LOADED ----> " + fileName + " <----");
        return obj;
    }

    /**
     * Method to save file
     * @param fileName name of file
     * @param data data to save
     * @throws IOException
     */
    public static void saveFile(String fileName, String data) throws IOException {
        File file = new File(fileName);
        FileWriter fw = new FileWriter(file);
        fw.write(data);
        fw.close();
        //System.out.println("---- FILE SAVED ----> " + fileName + " <----");
    }

    /**
     * Method to charge data from excel
     * @throws IOException
     */
    public static void chargeDataExcel(ArrayList[] dataExcel) throws IOException {
        File file = new File(INPUT_XLSX_FILE_NAME);
        InputStream inp = new FileInputStream(file);
        Workbook wb = WorkbookFactory.create(inp);
        Sheet sheet = wb.getSheetAt(sheed);
        Iterator<Row> rowIt = sheet.rowIterator();
        while (rowIt.hasNext()) {
            int i = 0;
            Row row = rowIt.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                dataExcel[i].add(cell.getStringCellValue());
                i++;
            }
        }
    }

    /**
     * Method to create and get personal directory
     * @param directory
     * @return
     */
    public static String createDirectory(String directory) {
        File file = new File(directory);
        if (!file.exists()) {
            file.mkdir();
        }
        return file.toString() + "/";
    }

    public static String createSubDirectory(String directory, String subDirectory) {
        File file = new File(directory + "/"+ subDirectory);
        if (!file.exists()) {
            file.mkdir();
        }
        return file.toString() + "/";
    }

}
