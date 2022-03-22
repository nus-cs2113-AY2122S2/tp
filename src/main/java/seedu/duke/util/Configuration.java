package seedu.duke.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.UnkownConfigurationGroupWord;




import static java.util.Map.entry;

public class Configuration {

    // Legal configuration groups.
    public enum ConfigurationGroup {
        COMPLETED_TASK_SHOWN;
    }

    // Each configuration group shall have a default value.
    private static final String DEFAULT_VALUE_COMPLETED_TASK_SHOWN = "false";

    // Each configuration group shall have a well defined legal values set.
    public static final HashSet<String> LEGAL_VALUE_OF_COMPLETED_TASK_SHOWN =
            new HashSet<>(Arrays.asList("true", "false"));

    // Add the explanation of the configuration group here for help.
    public static final HashSet<String> EXPLAIN_CONFIGURE_GROUP =
            new HashSet<>(Arrays.asList(
                    "COMPLETED_TASK_SHOWN: decide whether to show completed tasks."
            ));

    // Add the explanation of each legal values of a configuration group.
    public static final HashSet<String> EXPLAIN_LEGAL_VALUE_OF_COMPLETED_TASK_SHOWN =
            new HashSet<>(Arrays.asList(
                    "true: Show completed tasks",
                    "false: Hide completed tasks"
            ));

    // A HashSet integrating legal values set for all configuration groups.
    public static final HashMap<ConfigurationGroup, HashSet<String>> LEGAL_VALUES = new HashMap<>();
    // A HashSet integrating explanations sets of legal values for all configuration groups
    public static final HashMap<ConfigurationGroup, HashSet<String>> EXPLAIN_LEGAL_VALUES = new HashMap<>();

    // HashSet storing all current configuration settings.
    public HashMap<ConfigurationGroup, String> configurationGroupHashMap = new HashMap<>();


    public Configuration() {
        this.configurationGroupHashMap = new HashMap<>();
        LEGAL_VALUES.put(ConfigurationGroup.COMPLETED_TASK_SHOWN, LEGAL_VALUE_OF_COMPLETED_TASK_SHOWN);
        EXPLAIN_LEGAL_VALUES.put(ConfigurationGroup.COMPLETED_TASK_SHOWN, EXPLAIN_LEGAL_VALUE_OF_COMPLETED_TASK_SHOWN);

        // Shall set the value of each configuration group to default
        this.configurationGroupHashMap.put(ConfigurationGroup.COMPLETED_TASK_SHOWN, DEFAULT_VALUE_COMPLETED_TASK_SHOWN);
    }


    public Configuration(HashMap<ConfigurationGroup, String> configurationGroupStringHashMap) {
        this();
        this.configurationGroupHashMap = configurationGroupStringHashMap;
    }

    /**
     * Gets the explanation of each configuration group.
     * @return Explanation report of each configuration group.
     */
    public String getConfigurationGroupExplain() {
        String listResult = "";
        int count = 1;
        for (String explain : EXPLAIN_CONFIGURE_GROUP) {
            listResult += Integer.toString(count) + StringConstants.INDENT;
            listResult += explain;
            listResult += StringConstants.LS;
            count += 1;
        }
        return listResult;
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
            listResult += StringConstants.INDENT;
            listResult += explain;
            listResult += StringConstants.LS;
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
            listResult += StringConstants.INDENT;
            listResult += group;
            listResult += StringConstants.COLON;
            listResult += configurationGroupHashMap.get(group);
            listResult += StringConstants.LS;
        }
        return listResult;
    }

    /**
     * Gets current configuration value of a given configuration group.
     * @param group Given configuration group.
     * @return Current configuration value of a given configuration group.
     * @throws ModHappyException The given configuration group is illegal or fail to read.
     */
    public String getConfigurationValue(ConfigurationGroup group) throws ModHappyException {
        try {
            return configurationGroupHashMap.get(group);
        } catch (Exception e) {
            throw new UnkownConfigurationGroupWord(group.toString());
        }
    }

}
