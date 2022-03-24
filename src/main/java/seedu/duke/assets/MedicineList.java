package seedu.duke.assets;


import seedu.duke.exception.DuplicateEntryException;
import seedu.duke.exception.NotFoundException;
import seedu.duke.helper.UI;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class MedicineList extends List {
    private ArrayList<Medicine> medicines = new ArrayList<>();

    public int getSize() {
        return medicines.size();
    }

    public Medicine search(String medicineId) {
        for (Medicine medicine: medicines) {
            if (medicine.getMedicineId().equals(medicineId)) {
                return medicine;
            }
        }
        return null;
    }

    public void add(String[] parameterArray) throws DuplicateEntryException {
        if (search(parameterArray[0]) != null) {
            throw new DuplicateEntryException();
        }
        Medicine newMedicine = new Medicine(parameterArray[0], parameterArray[1],
                Integer.parseInt(parameterArray[2]), parameterArray[3], parameterArray[4],
                Integer.parseInt(parameterArray[5]));
        medicines.add(newMedicine);
    }

    public String getMedicineInfo(Medicine medicine) {
        return (medicine.getMedicineId() + ": "
                + medicine.getMedicineName() + ", "
                + Integer.toString(medicine.getDosage()) + ", " + medicine.getExpiry() + ", "
                + medicine.getSideEffects() + ", " + Integer.toString(medicine.getQuantity()));
    }

    public void view() {
        if (getSize() == 0) {
            UI.printParagraph("There are no medicines currently.");
            return;
        }
        UI.printParagraph("Here is the list of medicines");
        for (Medicine medicine : medicines) {
            UI.printCont(getMedicineInfo(medicine));
        }
        UI.printCont("There are a total of " + Integer.toString(medicines.size())
                + " in the system.");
    }

    //todo: please change logic
    public void view(String parameters) {
        if (getSize() == 0) {
            UI.printParagraph("There are no medicines matching the given name.");
            return;
        }
        UI.printParagraph("Here is the list of batches of " + parameters + ":");
        boolean hasRecord = false;
        for (Medicine medicine : medicines) {
            if (medicine.getMedicineName().equals(parameters)) {
                UI.printCont(getMedicineInfo(medicine));
                hasRecord = true;
            }
        }
        if (!hasRecord) {
            UI.printCont("No matching medicine found!");
        }
    }

    public void remove(String medicineId) throws NotFoundException {
        for (int i = 0; i < getSize(); i++) {
            if (medicines.get(i).getMedicineId().equals(medicineId)) {
                medicines.remove(i);
                return;
            }
        }
        throw new NotFoundException();
    }

    public void edit(String[] parameterArray) throws NotFoundException {
        if (search(parameterArray[0]) != null) {
            Medicine medicine = search(parameterArray[0]);
            medicine.edit(parameterArray[1], Integer.parseInt(parameterArray[2]), parameterArray[3], parameterArray[4],
                    Integer.parseInt(parameterArray[5]));
            return;
        }
        throw new NotFoundException();
    }

    public ArrayList<Medicine> getList() {
        return medicines;
    }


}
