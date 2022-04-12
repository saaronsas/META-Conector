package application.logiplex.azuread;

public class Config {

    public static final String OUTPUT_XML_FILE_NAME = "src/main/resources/output/";
    public static final String INPUT_XLSX_FILE_NAME = "src/main/resources/input/inputExcel.xlsx";
    public static final String XML_EXTENSION = ".xml";
    public static final String INPUT_XML_LOGILPEX_APPLICATION_TEMPLATE = "src/main/resources/input/templates/logiplex-application.xml";
    public static final String INPUT_XML_ACCOUNT_AGGREGATION_TEMPLATE = "src/main/resources/input/templates/accountAggregation-taskDefinition.xml";
    public static final String INPUT_XML_GROUP_AGGREGATION_TEMPLATE = "src/main/resources/input/templates/groupAggregation-taskDefinition.xml";
    public static final String INPUT_XML_FULL_AGGREGATION_TEMPLATE = "src/main/resources/input/templates/fullAggregation-taskDefinition.xml";
    public static final String VARIABLE_START = "%$";
    public static final String VARIABLE_END = "$%";

    public static final String VARIABLE_APP_NAME = "%$application_name$%";
    public static final String VARIABLE_TASK_NAME = "%$task_name$%";
    public static final String VARIABLE_TASK_LIST = "%$task_list$%";

}
