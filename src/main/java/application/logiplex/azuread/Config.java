package application.logiplex.azuread;

/**
 * Configuration class for Azure AD
 */
public class Config {

    /**
     * Variables for Azure AD
     * The values must be in the xml file
     */
    public static final String VARIABLE_APP_NAME = "%$application_name$%";
    public static final String VARIABLE_SCOPE_NAME = "%$scope$%";
    public static final String VARIABLE_TASK_NAME = "%$task_name$%";
    public static final String VARIABLE_TASK_LIST = "%$task_list$%";
    public static final String VARIABLE_CERTIFICATION_NAME = "%$certDef_name$%";
    public static final String VARIABLE_WORKGROUP_NAME = "%$workgroup_name$%";
    public static final String VARIABLE_CERTIFICATION_GROUP_NAME = "%$certGroup_name$%";

    /**
     * Template for Active Directory
     */
    public static final String INPUT_XML_LOGILPEX_APPLICATION_TEMPLATE = "src/main/resources/input/templates/azuread/logiplex-application.xml";
    public static final String INPUT_XML_CERTIFICATION_DEFINITION_TEMPLATE = "src/main/resources/input/templates/ad/certificationDefinition-CISO.xml";
    public static final String INPUT_XML_CERTIFICATION_GROUP_TEMPLATE = "src/main/resources/input/templates/ad/certificationGroup-CISO.xml";
    public static final String INPUT_XML_WORKGROUP_TEMPLATE = "src/main/resources/input/templates/ad/workgroup-CISO.xml";

}
