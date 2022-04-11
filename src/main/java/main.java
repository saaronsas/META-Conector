import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;


public class main {

    private static final String INPUT_XML_FILE_NAME = "src/main/resources/input/data.xml";
    private static final String OUTPUT_XML_FILE_NAME = "src/main/resources/output/";
    private static final String INPUT_XLSX_FILE_NAME = "src/main/resources/input/inputExcel.xlsx";
    private static final String XML_EXTENSION = ".xml";
    private static final String INPUT_XML_LOGILPEX_APPLICATION_TEMPLATE = "src/main/resources/input/templates/logiplex-application.xml";
    private static final String INPUT_XML_ACCOUNT_AGGREGATION_TEMPLATE = "src/main/resources/input/templates/accountAggregation-taskDefinition.xml";
    private static final String INPUT_XML_GROUP_AGGREGATION_TEMPLATE = "src/main/resources/input/templates/groupAggregation-taskDefinition.xml";
    private static final String INPUT_XML_FULL_AGGREGATION_TEMPLATE = "src/main/resources/input/templates/fullAggregation-taskDefinition.xml";
    private static final String VARIABLE_START = "%$";
    private static final String VARIABLE_END = "$%";

    private static final String VARIABLE_APP_NAME = "%$application_name$%";
    private static final String VARIABLE_TASK_NAME = "%$task_name$%";
    private static final String VARIABLE_TASK_LIST = "%$task_list$%";

    private static String taskPrefix[] = {"-TaskDefinition-AccountAggregation-", "-TaskDefinition-GroupAggregation-", "-TaskDefinition-FullAggregation-"};
    private static ArrayList<String> taskLists = new ArrayList();

    private static String type = "";
    private static String scope = "";
    private static String name = "";
    private static String applicationName = "";


    private static ArrayList<String>[] dataExcel = new ArrayList[]{new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>()};

    public static void main(String[] args) throws IOException {

        setVariablesFromXSLX(1);
        switch (type.toUpperCase()) {
            case "LOGIPLEX":
                saveFile(OUTPUT_XML_FILE_NAME + applicationName + XML_EXTENSION, readFile(chargeFile(INPUT_XML_LOGILPEX_APPLICATION_TEMPLATE), ""));
                createTasks(new String[]{INPUT_XML_ACCOUNT_AGGREGATION_TEMPLATE, INPUT_XML_GROUP_AGGREGATION_TEMPLATE, INPUT_XML_FULL_AGGREGATION_TEMPLATE});
                break;
        }

    }

    private static Object chargeFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Object obj = new Scanner(file);
        System.out.println("---- FILE LOADED ----> " + fileName + " <----");
        return obj;
    }

    private static String readFile(Object obj, String temporalName) throws FileNotFoundException {
        String data = "";
        while (((Scanner) obj).hasNextLine()) {
            String line = ((Scanner) obj).nextLine();

            if (line.contains(VARIABLE_APP_NAME)) {
                line = line.substring(0, line.indexOf(VARIABLE_START)) + applicationName + line.substring(line.indexOf(VARIABLE_END) + 2);
            } else if (line.contains(VARIABLE_TASK_NAME)) {
                line = line.substring(0, line.indexOf(VARIABLE_START)) + temporalName + line.substring(line.indexOf(VARIABLE_END) + 2);
            } else if (line.contains(VARIABLE_TASK_LIST)) {
                line = line.substring(0, line.indexOf(VARIABLE_START)) + taskLists.toString().substring(taskLists.toString().indexOf("[") + 1, taskLists.toString().indexOf("]")) + line.substring(line.indexOf(VARIABLE_END) + 2);
            }

            data += line + "\n";
        }
        System.out.println("---- DATA UPDATED ----");
        return data;
    }

    private static void saveFile(String fileName, String data) throws IOException {
        File file = new File(fileName);
        FileWriter fw = new FileWriter(file);
        fw.write(data);
        fw.close();
        System.out.println("---- FILE SAVED ----> " + fileName + " <----");
    }

    private static void createTasks(String[] tasks) throws IOException {
        for (int i = 0; i < tasks.length; i++) {
            String taskName = scope + taskPrefix[i] + name;
            taskLists.add(taskName);
            saveFile(OUTPUT_XML_FILE_NAME + taskName + XML_EXTENSION, readFile(chargeFile(tasks[i]), taskName));
        }
    }

    private static void chargeDataExcel() throws IOException {
        File file = new File(INPUT_XLSX_FILE_NAME);
        InputStream inp = new FileInputStream(file);
        Workbook wb = WorkbookFactory.create(inp);
        Sheet sheet = wb.getSheetAt(0);
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

    private static void setVariablesFromXSLX(int position) throws IOException {

        chargeDataExcel();

        type = dataExcel[0].get(position);
        scope = dataExcel[1].get(position);
        name = dataExcel[2].get(position);
        applicationName = scope + "-Application-Logiplex-" + dataExcel[2].get(position);
    }

}