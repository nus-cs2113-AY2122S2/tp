package seedu.allonus.storage;

import seedu.allonus.modules.StudyManager;
import seedu.allonus.modules.Module;

import java.util.ArrayList;

import static seedu.allonus.storage.StorageFile.ASSERT_CATEGORY_IS_ACCOUNTED_FOR;

/**
 * Stub class for StudyManager for unit testing.
 */
public class StudyManagerStub extends StudyManager {
    private ArrayList<Module> list = new ArrayList<Module>();
    private static int number;

    public StudyManagerStub() {
        this.number = 0;
    }

    @Override
    public int getModuleCount() {
        return number;
    }

    @Override
    public ArrayList<Module> getModulesList() {
        return list;
    }

    @Override
    public void loadAdd(String savedModule) {
        String[] components = savedModule.split(" ",5);
        String category = "";
        switch (components[2].substring(2)) {
        case "lec":
            category = "Lecture";
            break;
        case "exam":
            category = "Exam";
            break;
        case "tut":
            category = "Tutorial";
            break;
        case "lab":
            category = "Laboratory";
            break;
        default:
            assert false : ASSERT_CATEGORY_IS_ACCOUNTED_FOR;
        }
        Module module = new Module(components[1].substring(2),category,
                components[3].substring(2),components[4].substring(2));
        list.add(module);
        number += 1;
    }
}
