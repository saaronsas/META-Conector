package application.logiplex.azuread;

/**
 * Configuration class for Azure AD
 */
public class Config {

    public static final String INPUT_XML_LOGILPEX_APPLICATION_TEMPLATE = "src/main/resources/input/templates/azuread/logiplex-application.xml";

    /**
     * Variables for Azure AD
     * The values must be in the xml file
     */
    public static final String VARIABLE_APP_NAME = "%$application_name$%";
    public static final String VARIABLE_TASK_NAME = "%$task_name$%";
    public static final String VARIABLE_TASK_LIST = "%$task_list$%";

}
