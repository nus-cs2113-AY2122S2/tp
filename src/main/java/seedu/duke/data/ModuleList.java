package seedu.duke.data;

import java.util.ArrayList;
import java.util.Scanner;

import seedu.duke.exceptions.NoSuchModuleException;
import seedu.duke.exceptions.ParseException;
import seedu.duke.util.StringConstants;

public class ModuleList {
    private ArrayList<Module> list;
    private final Module generalTasks;

    private static final String DELETE_CONFIRMATION = StringConstants.DELETE_CONFIRMATION;

    public ModuleList() {
        list = new ArrayList<>();
        generalTasks = new Module("General tasks");
    }

    /**
     * Adds the specified module to the module list, then returns it for convenience.
     * @param m the module to be added
     */
    public Module addModule(Module m) {
        list.add(m);
        return m;
    }

    /**
     * Removes specified module from the module list.
     *
     * @param moduleCode the module code to be removed
     */
    public Module removeModule(String moduleCode) throws NoSuchModuleException, ParseException {
        Module module = getModule(moduleCode);
        if (module == null) {
            throw new NoSuchModuleException();
        }
        String userConfirmation = "";
        if (module.getTaskList().size() > 0) {
            Scanner scanner = new Scanner(System.in);
            String reply = String.format(DELETE_CONFIRMATION, module);
            System.out.println(reply);
            userConfirmation = scanner.nextLine().toLowerCase();
        }
        if (userConfirmation.equals("yes")) {
            list.remove(module);
            return module;
        } else if (userConfirmation.equals("no")) {
            return null;
        }
        throw new ParseException();
    }

    /**
     * Returns the module in the module list with the given module code.
     * @param moduleCode The module code to search for
     * @return the associated module if it exists, or null if it does not.
     */
    public Module getModule(String moduleCode) {
        for (Module m : list) {
            if (m.getModuleCode().equals(moduleCode)) {
                return m;
            }
        }
        return null;
    }

    public ArrayList<Module> getModuleList() {
        return list;
    }

    public void setModuleList(ArrayList<Module> list) {
        this.list = list;
    }

    public Module getGeneralTasks() {
        return generalTasks;
    }

    public void initialiseGeneralTasksFromTaskList(ArrayList<Task> generalTaskList) {
        generalTasks.setTaskArrayList(generalTaskList);
    }

    public void reset() {
        list.clear();
        generalTasks.getTaskList().reset();
    }

    /**
     * Checks whether a module with the specified module code already exists in the module list.
     * @param moduleCode the module code to check for.
     * @return true if a module in the module list already has that module code, or false otherwise.
     */
    public boolean isModuleExists(String moduleCode) {
        return (getModule(moduleCode) != null);
    }
}
