package application;

/**
 * This class is used to store the global configuration of the application.
 */
public class Config {

    public static final String OUTPUT_XML_FILE_NAME = "src/main/resources/output/";
    public static final String INPUT_XLSX_FILE_NAME = "src/main/resources/input/inputExcel.xlsx";
    public static final String XML_EXTENSION = ".xml";

    /**
     * Input Tasks
     */
    public static final String INPUT_XML_ACCOUNT_AGGREGATION_TEMPLATE = "src/main/resources/input/templates/tasks/accountAggregation-taskDefinition.xml";
    public static final String INPUT_XML_GROUP_AGGREGATION_TEMPLATE = "src/main/resources/input/templates/tasks/groupAggregation-taskDefinition.xml";
    public static final String INPUT_XML_FULL_AGGREGATION_TEMPLATE = "src/main/resources/input/templates/tasks/fullAggregation-taskDefinition.xml";

    /**
     * The variable locators
     */
    public static final String VARIABLE_START = "%$";
    public static final String VARIABLE_END = "$%";

}
