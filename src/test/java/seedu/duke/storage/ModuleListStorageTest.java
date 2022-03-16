package seedu.duke.storage;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import seedu.duke.tasks.Module;
import seedu.duke.tasks.Task;
import seedu.duke.tasks.TaskList;
import seedu.duke.util.StringConstants;


public class ModuleListStorageTest {
    private ModuleListStorage moduleListStorage;
    private ArrayList<Module> moduleList;
    private final String path = StringConstants.MODULE_PATH;

    @BeforeEach
    public void setUp() {
        moduleListStorage = new ModuleListStorage();
        moduleList = new ArrayList<>();
    }

    @Test
    public void store_empty_module_list_and_read() {
        try {
            moduleListStorage.jsonWriter(moduleList, path);
            ArrayList<Module> list = moduleListStorage.jsonReader(path);
            assertTrue(list.containsAll(moduleList) && moduleList.containsAll(list));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void store_module_without_task_list_and_read() {
        try {
            Module module1 = new Module("CS2113T", "d1");
            Module module2 = new Module("CS2101", "d2");
            Module module3 = new Module("CS2040", "d3");
            moduleList.add(module1);
            moduleList.add(module2);
            moduleList.add(module3);
            moduleListStorage.jsonWriter(moduleList, path);
            ArrayList<Module> list = moduleListStorage.jsonReader(path);
            assertEquals(list.size(), moduleList.size());
            for (int i = 0; i < list.size(); i++) {
                assertEquals(list.get(i).getModuleCode(), moduleList.get(i).getModuleCode());
                assertEquals(list.get(i).getModuleDescription(), moduleList.get(i).getModuleDescription());
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void store_module_with_task_list_and_read() {
        try {
            Task task1 = new Task("t1", "dt1", "2h");
            Task task2 = new Task("t2", "dt2", "3h");
            Task task3 = new Task("t3", "dt3", "4h");
            Module module1 = new Module("CS2113T", "d1");
            Module module2 = new Module("CS2101", "d2");
            Module module3 = new Module("CS2040", "d3");
            module1.addTask(task1);
            module2.addTask(task2);
            module3.addTask(task3);
            moduleList.add(module1);
            moduleList.add(module2);
            moduleList.add(module3);
            moduleListStorage.jsonWriter(moduleList, path);
            ArrayList<Module> list = moduleListStorage.jsonReader(path);
            assertEquals(list.size(), moduleList.size());
            for (int i = 0; i < list.size(); i++) {
                assertEquals(list.get(i).getModuleCode(), moduleList.get(i).getModuleCode());
                assertEquals(list.get(i).getModuleDescription(), moduleList.get(i).getModuleDescription());
                TaskList taskList1 = list.get(i).getTaskList();
                TaskList taskList2 = moduleList.get(i).getTaskList();
                assertEquals(taskList1.size(), taskList2.size());
                for (int j = 0; j < taskList1.size(); j++) {
                    assertEquals(taskList1.getTask(j).getTaskName(), taskList2.getTask(j).getTaskName());
                    assertEquals(taskList1.getTask(j).getTaskDescription(), taskList2.getTask(j).getTaskDescription());
                    assertEquals(taskList1.getTask(j).getEstimatedWorkingTime(),
                            taskList2.getTask(j).getEstimatedWorkingTime());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }


}
