package seedu.duke.commands;

import java.util.Objects;

import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.UnknownConfigurationGroupWordException;
import seedu.duke.data.ModuleList;
import seedu.duke.exceptions.UnsupportedResultTypeException;
import seedu.duke.util.Configuration;
import seedu.duke.util.StringConstants;


public class OptionCommand extends Command {
    private static final String OPTION_CHECK_CONFIGURATIONS = StringConstants.OPTION_CHECK_CONFIGURATIONS;
    private static final String OPTION_SET_SUCCESS = StringConstants.OPTION_SET_SUCCESS;

    private Configuration.ConfigurationGroup configurationGroup = null;
    private String newValue = null;

    //@@author heekit73098

    /**
     * Constructs a new OptionCommand object.
     * @param configurationGroupWord The configuration word inputted
     * @param newValue The value of the configuration to be set
     * @throws ModHappyException If the configuration word is unknown,
     *                           or if the value is unsupported in the configuration word group
     */
    public OptionCommand(String configurationGroupWord, String newValue) throws ModHappyException {
        if (!Objects.isNull(configurationGroupWord)) {
            try {
                configurationGroup = Configuration.ConfigurationGroup.valueOf(configurationGroupWord);
            } catch (IllegalArgumentException e) {
                throw new UnknownConfigurationGroupWordException(configurationGroupWord);
            }
            if (!Configuration.LEGAL_VALUES.containsKey(configurationGroup)) {
                throw new UnknownConfigurationGroupWordException(configurationGroupWord);
            }
        }
        if (!Objects.isNull(newValue)) {
            if (Configuration.LEGAL_VALUES.get(configurationGroup).contains(newValue)) {
                this.newValue = newValue;
            } else {
                throw new UnsupportedResultTypeException(newValue, configurationGroupWord);
            }
        }
    }

    //@@author Ch40gRv1-Mu

    /**
     * Sets the new value or display help for the command depending on the command entered.
     * @param moduleList The list of modules
     * @param configuration The configuration settings of the application
     * @return A new {@code CommandResult} with the result string
     */
    @Override
    public CommandResult execute(ModuleList moduleList, Configuration configuration) {
        // enter "option" to check the list of configuration setting
        if (Objects.isNull(configurationGroup)) {
            return new CommandResult(OPTION_CHECK_CONFIGURATIONS + StringConstants.LS
                    + configuration.getConfigurationsReport());
        }

        // enter "option <CONFIGURATION_GROUP>" to check the legal values of a configuration group
        if (Objects.isNull(newValue)) {
            return new CommandResult(configurationGroup + StringConstants.LS
                    + configuration.getValueExplain(configurationGroup));
        }

        // enter "option <CONFIGURATION_GROUP>=<NEW_VALUE>" to set legal value for a configuration group
        configuration.configurationGroupHashMap.put(configurationGroup, newValue);
        return new CommandResult(OPTION_SET_SUCCESS + configurationGroup + "=" + newValue);
    }
}
