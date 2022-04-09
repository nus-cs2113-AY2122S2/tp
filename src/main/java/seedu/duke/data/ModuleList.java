package seedu.duke.data;

import seedu.duke.exceptions.NoSuchModuleException;

import java.util.ArrayList;

//@@author chooyikai
public class ModuleList {
    private ArrayList<Module> list;
    private final Module generalTasks;

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

    public Module removeModule(String moduleCode) throws NoSuchModuleException {
        Module module = getModule(moduleCode);
        list.remove(module);
        return module;
    }

    /**
     * Returns the module in the module list with the given module code.
     * @param moduleCode The module code to search for.
     * @return The associated module if it exists.
     * @throws NoSuchModuleException If the module does not exist.
     */
    public Module getModule(String moduleCode) throws NoSuchModuleException {
        for (Module m : list) {
            if (m.getModuleCode().equals(moduleCode)) {
                return m;
            }
        }
        throw new NoSuchModuleException();
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
        try {
            getModule(moduleCode);
            return true;
        } catch (NoSuchModuleException e) {
            return false;
        }
    }
}
