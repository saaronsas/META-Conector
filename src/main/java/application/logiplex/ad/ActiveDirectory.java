package application.logiplex.ad;

import application.BaseApplication;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static application.Config.*;
import static application.Config.XML_EXTENSION;
import static application.logiplex.ad.Config.*;

public class ActiveDirectory extends BaseApplication {

    private static String scope = "";
    private static String name = "";
    private static String applicationName = "";
    private static String certDefName = "";
    private static String workgroupName = "";
    private static String certGroupName = "";

    private static String taskPrefix[] = {"-TaskDefinition-AccountAggregation-", "-TaskDefinition-GroupAggregation-", "-TaskDefinition-FullAggregation-"};
    private static ArrayList<String> taskLists = new ArrayList();
    private static ArrayList<String>[] dataExcel = new ArrayList[]{new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>()};

    /**
     * Constructor for objects of class ActiveDirectory
     * @param sheed number of excel sheet
     * @throws IOException
     */
    public ActiveDirectory(int sheed) throws IOException {
        super(sheed);
    }

    /**
     * Method to create application, tasks and other data
     * @throws IOException
     */
    public static void createActiveDirectory() throws IOException {

        System.out.println("---- CREATE AD APPS ----");
        chargeDataExcel(dataExcel);
        System.out.println(dataExcel[2].toString());
        for (int i = 1; i < dataExcel[0].size(); i++) {
            scope = dataExcel[1].get(i);
            name = dataExcel[2].get(i);
            applicationName = getApplicationName(name);
            saveFile(createDirectory(OUTPUT_XML_FILE_NAME + applicationName) + applicationName + XML_EXTENSION, readFile(chargeFile(INPUT_XML_LOGILPEX_APPLICATION_TEMPLATE), ""));
            createTasks(new String[]{INPUT_XML_ACCOUNT_AGGREGATION_TEMPLATE, INPUT_XML_GROUP_AGGREGATION_TEMPLATE, INPUT_XML_FULL_AGGREGATION_TEMPLATE});
            createCertifications();
        }

    }

    /**
     * Method to read and replace data from file
     * @param obj file
     * @param temporalName task name
     * @return String of data
     * @throws FileNotFoundException
     */
    private static String readFile(Object obj, String temporalName) throws FileNotFoundException {
        String data = "";
        while (((Scanner) obj).hasNextLine()) {
            String line = ((Scanner) obj).nextLine();

            if (line.contains(VARIABLE_APP_NAME)) {
                line = line.substring(0, line.indexOf(VARIABLE_START)) + applicationName + line.substring(line.indexOf(VARIABLE_END) + 2);
            } else if (line.contains(VARIABLE_SCOPE_NAME)) {
                line = line.substring(0, line.indexOf(VARIABLE_START)) + scope + line.substring(line.indexOf(VARIABLE_END) + 2);
            } else if (line.contains(VARIABLE_TASK_NAME)) {
                line = line.substring(0, line.indexOf(VARIABLE_START)) + temporalName + line.substring(line.indexOf(VARIABLE_END) + 2);
            } else if (line.contains(VARIABLE_TASK_LIST)) {
                line = line.substring(0, line.indexOf(VARIABLE_START)) + taskLists.toString().substring(taskLists.toString().indexOf("[") + 1, taskLists.toString().indexOf("]")) + line.substring(line.indexOf(VARIABLE_END) + 2);
            } else if (line.contains(VARIABLE_CERTIFICATION_NAME)) {
                line = line.substring(0, line.indexOf(VARIABLE_START)) + certDefName + line.substring(line.indexOf(VARIABLE_END) + 2);
            } else if (line.contains(VARIABLE_WORKGROUP_NAME)) {
                line = line.substring(0, line.indexOf(VARIABLE_START)) + workgroupName + line.substring(line.indexOf(VARIABLE_END) + 2);
            } else if (line.contains(VARIABLE_CERTIFICATION_GROUP_NAME)) {
                line = line.substring(0, line.indexOf(VARIABLE_START)) + certGroupName + line.substring(line.indexOf(VARIABLE_END) + 2);
            }

            data += line + "\n";
        }
        //System.out.println("---- DATA UPDATED ----");
        return data;
    }

    /**
     * Method to create tasks
     * @param tasks tasks to create
     * @throws IOException
     */
    private static void createTasks(String[] tasks) throws IOException {
        for (int i = 0; i < tasks.length; i++) {
            String taskName = scope + taskPrefix[i] + name;
            taskLists.add(taskName);
            saveFile(createSubDirectory(OUTPUT_XML_FILE_NAME + applicationName, "tasks") + taskName + XML_EXTENSION, readFile(chargeFile(tasks[i]), taskName));
        }
    }

    /**
     * Method to create certifications
     * @throws IOException
     */
    private static void createCertifications() throws IOException {
        certDefName = scope + "-CertificationDefinition-CISO " + name;
        workgroupName = scope + "-Workgroup-" + name + "_ITOWNER";
        certGroupName = scope + "-CertificationGroup-CISO " + name;
        saveFile(createSubDirectory(OUTPUT_XML_FILE_NAME + applicationName, "certifications") + certDefName + XML_EXTENSION, readFile(chargeFile(INPUT_XML_CERTIFICATION_DEFINITION_TEMPLATE), certDefName));
        saveFile(createSubDirectory(OUTPUT_XML_FILE_NAME + applicationName, "certifications") + certGroupName + XML_EXTENSION, readFile(chargeFile(INPUT_XML_CERTIFICATION_GROUP_TEMPLATE), certGroupName));
        saveFile(createSubDirectory(OUTPUT_XML_FILE_NAME + applicationName, "certifications") + workgroupName + XML_EXTENSION, readFile(chargeFile(INPUT_XML_WORKGROUP_TEMPLATE), workgroupName));
    }

    /**
     * Method to get custom ApplicationName
     * SAILPOINT form = SCOPE + File TYPE + NAME
     * @param name name of application
     * @return custom application name
     * @throws IOException
     */
    private static String getApplicationName(String name) throws IOException {
        return scope + "-Application-Logiplex-" + name;
    }
}