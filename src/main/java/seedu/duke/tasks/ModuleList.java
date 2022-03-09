package seedu.duke.tasks;

import java.util.ArrayList;

public class ModuleList {
    private static final String LS = System.lineSeparator();
    private static final String ITEMIZE_FORMAT = "%d. %s" + LS;
    private ArrayList<Module> list;
    private final Module generalTasks;

    public ModuleList() {
        list = new ArrayList<>();
        generalTasks = new Module("General tasks");
    }

    public int size() {
        return list.size();
    }

    public Module addModule(Module m) {
        list.add(m);
        return m;
    }

    public Module getModule(int index) {
        return list.get(index);
    }

    public ArrayList<Module> getModuleList() {
        return list;
    }

    public Module getGeneralTasks() {
        return generalTasks;
    }

    public Module getModule(String moduleCode) {
        for (Module m : list) {
            if (m.getModuleCode().equals(moduleCode)) {
                return m;
            }
        }
        return null;
    }

    public boolean isModuleExists(String moduleCode) {
        return (getModule(moduleCode) != null);
    }
}
