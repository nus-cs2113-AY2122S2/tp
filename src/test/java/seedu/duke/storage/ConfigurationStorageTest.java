package seedu.duke.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.duke.util.Configuration.ConfigurationGroup.COMPLETED_TASK_SHOWN;

import seedu.duke.util.Configuration;
import seedu.duke.util.StringConstants;




public class ConfigurationStorageTest {

    private final String path = StringConstants.CONFIGURATION_TEST_PATH;
    private ConfigurationStorage configurationStorage;
    private Configuration configuration;

    @BeforeEach
    public void setUp() {
        configurationStorage = new ConfigurationStorage();
        configuration = new Configuration();
    }

    @Test
    public void modify_configuration_store_and_read() {
        try {
            assertEquals("false", configuration.getConfigurationValue(COMPLETED_TASK_SHOWN));
            configuration.configurationGroupHashMap.put(COMPLETED_TASK_SHOWN, "true");
            configurationStorage.writeData(configuration, path);
            Configuration loadedConfiguration = (Configuration) configurationStorage.loadData(path);
            assertEquals(configuration.getConfigurationsReport(), loadedConfiguration.getConfigurationsReport());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

}
