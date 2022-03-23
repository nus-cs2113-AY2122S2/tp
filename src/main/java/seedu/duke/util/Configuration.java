package seedu.duke.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Configuration {

    private static final String INDENT = StringConstants.INDENT;
    private static final String LS = StringConstants.LS;

    private static final String TRUE = StringConstants.TRUE;
    private static final String FALSE = StringConstants.FALSE;
    private static final String DESCRIPTION_FORMAT = StringConstants.DESCRIPTION_FORMAT;

    private static final String COMPLETED_TASKS_SHOWN_NAME = StringConstants.COMPLETED_TASKS_SHOWN_NAME;
    private static final String COMPLETED_TASKS_SHOWN_EXPLAIN = StringConstants.COMPLETED_TASKS_SHOWN_EXPLAIN;
    private static final String COMPLETED_TASKS_SHOWN_TRUE = StringConstants.COMPLETED_TASKS_SHOWN_TRUE;
    private static final String COMPLETED_TASKS_SHOWN_FALSE = StringConstants.COMPLETED_TASKS_SHOWN_FALSE;

    // Legal configuration groups.
    public enum ConfigurationGroup {
        COMPLETED_TASKS_SHOWN;
    }

    // Each configuration group shall have a default value.
    private static final String DEFAULT_VALUE_COMPLETED_TASK_SHOWN = FALSE;

    // Each configuration group shall have a well defined legal values set.
    public static final HashSet<String> LEGAL_VALUE_OF_COMPLETED_TASK_SHOWN = new HashSet<>(Arrays.asList(TRUE, FALSE));

    // Add the explanation of the configuration group here for help.
    public static final HashSet<String> EXPLAIN_CONFIGURE_GROUP =
            new HashSet<>(Arrays.asList(
                    String.format(DESCRIPTION_FORMAT, COMPLETED_TASKS_SHOWN_NAME, COMPLETED_TASKS_SHOWN_EXPLAIN)
            ));

    // Add the explanation of each legal values of a configuration group.
    public static final HashSet<String> EXPLAIN_LEGAL_VALUE_OF_COMPLETED_TASK_SHOWN =
            new HashSet<>(Arrays.asList(
                    String.format(DESCRIPTION_FORMAT, TRUE, COMPLETED_TASKS_SHOWN_TRUE),
                    String.format(DESCRIPTION_FORMAT, FALSE, COMPLETED_TASKS_SHOWN_FALSE)
            ));

    // A HashSet integrating legal values set for all configuration groups.
    public static final HashMap<ConfigurationGroup, HashSet<String>> LEGAL_VALUES = new HashMap<>();
    // A HashSet integrating explanations sets of legal values for all configuration groups
    public static final HashMap<ConfigurationGroup, HashSet<String>> EXPLAIN_LEGAL_VALUES = new HashMap<>();

    // HashSet storing all current configuration settings.
    public HashMap<ConfigurationGroup, String> configurationGroupHashMap;


    public Configuration() {
        configurationGroupHashMap = new HashMap<>();
        LEGAL_VALUES.put(ConfigurationGroup.COMPLETED_TASKS_SHOWN, LEGAL_VALUE_OF_COMPLETED_TASK_SHOWN);
        EXPLAIN_LEGAL_VALUES.put(ConfigurationGroup.COMPLETED_TASKS_SHOWN, EXPLAIN_LEGAL_VALUE_OF_COMPLETED_TASK_SHOWN);

        // Shall set the value of each configuration group to default
        configurationGroupHashMap.put(ConfigurationGroup.COMPLETED_TASKS_SHOWN, DEFAULT_VALUE_COMPLETED_TASK_SHOWN);
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
        String listResult = "";
        for (String explain : valueOfConfigureGroup) {
            listResult += INDENT + explain + LS;
        }
        return listResult;
    }

    /**
     * Gets report of current configuration setting.
     * @return Report of current configuration setting.
     */
    public String getConfigurationsReport() {
        String listResult = "";
        for (ConfigurationGroup group : ConfigurationGroup.values()) {
            listResult += INDENT + String.format(DESCRIPTION_FORMAT, group, configurationGroupHashMap.get(group)) + LS;
        }
        return listResult;
    }

    /**
     * Gets current configuration value of a given configuration group.
     * @param group Given configuration group.
     * @return Current configuration value of a given configuration group.
     */
    public String getConfigurationValue(ConfigurationGroup group) {
        return configurationGroupHashMap.get(group);
    }
}
