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
    public static final String VARIABLE_URL_MATER_APP = "%$url$%";
    public static final String VARIABLE_CLIENT_ID_MATER_APP = "%$clientId$%";
    public static final String VARIABLE_CLIENT_SECRET_MATER_APP = "%$clientSecret$%";

    /**
     * Template for Active Directory
     */
    public static final String INPUT_XML_LOGILPEX_APPLICATION_TEMPLATE = "src/main/resources/input/templates/azuread/logiplex-application.xml";
    public static final String INPUT_XML_CERTIFICATION_DEFINITION_TEMPLATE = "src/main/resources/input/templates/azuread/certificationDefinition-CISO.xml";
    public static final String INPUT_XML_CERTIFICATION_GROUP_TEMPLATE = "src/main/resources/input/templates/azuread/certificationGroup-CISO.xml";
    public static final String INPUT_XML_WORKGROUP_TEMPLATE = "src/main/resources/input/templates/azuread/workgroup-CISO.xml";
    public static final String INPUT_XML_MASTER_APPLICATION_TEMPLATE = "src/main/resources/input/templates/azuread/PROTECT-Application-AzureAD.xml";

    /**
     * File names
     */
    public static final String MASTER_APPLICATION_NAME = "PROTECT-Application-AzureAD";
}
