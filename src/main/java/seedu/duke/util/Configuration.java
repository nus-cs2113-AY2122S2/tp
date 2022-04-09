package seedu.duke.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

//@@author Ch40gRv1-Mu
public class Configuration {

    private static final String INDENT = StringConstants.INDENT;
    private static final String LS = StringConstants.LS;

    private static final String TRUE = StringConstants.TRUE;
    private static final String FALSE = StringConstants.FALSE;
    private static final String DESCRIPTION_FORMAT = StringConstants.DESCRIPTION_FORMAT;

    private static final String SHOW_COMPLETED_TASKS_NAME = StringConstants.SHOW_COMPLETED_TASKS_NAME;
    private static final String SHOW_COMPLETED_TASKS_EXPLAIN = StringConstants.SHOW_COMPLETED_TASKS_EXPLAIN;
    private static final String SHOW_COMPLETED_TASKS_TRUE = StringConstants.SHOW_COMPLETED_TASKS_TRUE;
    private static final String SHOW_COMPLETED_TASKS_FALSE = StringConstants.SHOW_COMPLETED_TASKS_FALSE;

    // Legal configuration groups.
    public enum ConfigurationGroup {
        SHOW_COMPLETED_TASKS
    }

    // Each configuration group shall have a default value.
    private static final String DEFAULT_VALUE_COMPLETED_TASK_SHOWN = FALSE;

    // Each configuration group shall have a well-defined legal values set.
    public static final HashSet<String> LEGAL_VALUE_OF_COMPLETED_TASK_SHOWN = new HashSet<>(Arrays.asList(TRUE, FALSE));

    // Add the explanation of the configuration group here for help.
    public static final HashSet<String> EXPLAIN_CONFIGURE_GROUP =
            new HashSet<>(Arrays.asList(
                    String.format(DESCRIPTION_FORMAT, SHOW_COMPLETED_TASKS_NAME, SHOW_COMPLETED_TASKS_EXPLAIN)
            ));

    // Add the explanation of each legal values of a configuration group.
    public static final HashSet<String> EXPLAIN_LEGAL_VALUE_OF_COMPLETED_TASK_SHOWN =
            new HashSet<>(Arrays.asList(
                    String.format(DESCRIPTION_FORMAT, TRUE, SHOW_COMPLETED_TASKS_TRUE),
                    String.format(DESCRIPTION_FORMAT, FALSE, SHOW_COMPLETED_TASKS_FALSE)
            ));

    // A HashSet integrating legal values set for all configuration groups.
    public static final HashMap<ConfigurationGroup, HashSet<String>> LEGAL_VALUES = new HashMap<>();
    // A HashSet integrating explanations sets of legal values for all configuration groups
    public static final HashMap<ConfigurationGroup, HashSet<String>> EXPLAIN_LEGAL_VALUES = new HashMap<>();

    // Initialise these values
    static {
        LEGAL_VALUES.put(ConfigurationGroup.SHOW_COMPLETED_TASKS, LEGAL_VALUE_OF_COMPLETED_TASK_SHOWN);
        EXPLAIN_LEGAL_VALUES.put(ConfigurationGroup.SHOW_COMPLETED_TASKS, EXPLAIN_LEGAL_VALUE_OF_COMPLETED_TASK_SHOWN);
    }

    // HashSet storing all current configuration settings.
    public HashMap<ConfigurationGroup, String> configurationGroupHashMap;


    public Configuration() {
        configurationGroupHashMap = new HashMap<>();
        LEGAL_VALUES.put(ConfigurationGroup.SHOW_COMPLETED_TASKS, LEGAL_VALUE_OF_COMPLETED_TASK_SHOWN);
        EXPLAIN_LEGAL_VALUES.put(ConfigurationGroup.SHOW_COMPLETED_TASKS, EXPLAIN_LEGAL_VALUE_OF_COMPLETED_TASK_SHOWN);

        // Set the value of each configuration group to default
        configurationGroupHashMap.put(ConfigurationGroup.SHOW_COMPLETED_TASKS, DEFAULT_VALUE_COMPLETED_TASK_SHOWN);
    }

    public Configuration(HashMap<ConfigurationGroup, String> configurationGroupStringHashMap) {
        this();
        configurationGroupHashMap = configurationGroupStringHashMap;
    }

    /**
     * Gets the explanation of each legal value of given configuration group.
     * @param configureGroup A configuration group to explain
     * @return Explanation report of legal values of the given configuration group.
     */
    public String getValueExplain(ConfigurationGroup configureGroup) {
        HashSet<String> valueOfConfigureGroup = EXPLAIN_LEGAL_VALUES.get(configureGroup);
        StringBuilder listResult = new StringBuilder();
        for (String explain : valueOfConfigureGroup) {
            listResult.append(INDENT).append(explain).append(LS);
        }
        return listResult.toString();
    }

    /**
     * Gets report of current configuration setting.
     * @return Report of current configuration setting.
     */
    public String getConfigurationsReport() {
        StringBuilder listResult = new StringBuilder();
        for (ConfigurationGroup group : ConfigurationGroup.values()) {
            listResult.append(INDENT).append(String.format(DESCRIPTION_FORMAT,
                     group, configurationGroupHashMap.get(group))).append(LS);
        }
        return listResult.toString();
    }

    /**
     * Gets current configuration value of a given configuration group.
     * @param group Given configuration group.
     * @return Current configuration value of a given configuration group.
     */
    public String getConfigurationValue(ConfigurationGroup group) {
        return configurationGroupHashMap.get(group);
    }

    /**
     * Returns a list of all config settings and their descriptions.
     */
    public static String getAllConfigurationExplanations() {
        StringBuilder result = new StringBuilder();
        for (String s : EXPLAIN_CONFIGURE_GROUP) {
            result.append(s).append(LS);
        }
        return result.toString();
    }
}
