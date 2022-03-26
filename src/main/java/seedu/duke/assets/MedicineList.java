package seedu.duke.assets;


import seedu.duke.exception.DuplicateEntryException;
import seedu.duke.exception.NotFoundException;
import seedu.duke.helper.UI;
import seedu.duke.helper.command.CommandLineTable;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class MedicineList extends List {
    private ArrayList<Medicine> medicines = new ArrayList<>();

    public int getSize() {
        return medicines.size();
    }

    public Medicine getMedicine(String medicineId) {
        for (Medicine medicine : medicines) {
            if (medicine.getMedicineId().equals(medicineId)) {
                return medicine;
            }
        }
        return null;
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
            throw new DuplicateEntryException("Medicine with given Batch ID already exists!");
        }
        Medicine newMedicine = new Medicine(parameterArray[0], parameterArray[1],
                Integer.parseInt(parameterArray[2]), parameterArray[3], parameterArray[4],
                Integer.parseInt(parameterArray[5]));
        medicines.add(newMedicine);
    }

    public void find(String[] command){
    }

    public String getMedicineInfo(Medicine medicine) {
        return (medicine.getMedicineId() + ": "
                + medicine.getMedicineName() + ", "
                + Integer.toString(medicine.getDosage()) + ", " + medicine.getExpiry() + ", "
                + medicine.getSideEffects() + ", " + Integer.toString(medicine.getQuantity()));
    }


    //view particular medicine
    public void view(String medicineId) {
        Medicine medicine = getMedicine(medicineId);
        if (medicine == null) {
            UI.printParagraph("Medicine doesn't exist please try again!");
            return;
        }
        CommandLineTable medicineTable = new CommandLineTable();
        medicineTable.setShowVerticalLines(true);
        medicineTable.setHeaders("MedicineId", "MedicineName","Dosage", "Expiry", "SideEffects", "Quantity");
        medicineTable.addRow(medicine.getMedicineId(), medicine.getMedicineName(), String.valueOf(medicine.getDosage()),
                medicine.getExpiry(), medicine.getSideEffects(), String.valueOf(medicine.getQuantity()));
        medicineTable.print();
    }

    public void view() {
        CommandLineTable medicineTable = new CommandLineTable();
        //st.setRightAlign(true);//if true then cell text is right aligned
        medicineTable.setShowVerticalLines(true);
        medicineTable.setHeaders("MedicineId", "MedicineName","Dosage", "Expiry", "SideEffects", "Quantity");

        for(Medicine medicine: medicines){
            medicineTable.addRow(medicine.getMedicineId(), medicine.getMedicineName(), String.valueOf(medicine.getDosage()),
                    medicine.getExpiry(), medicine.getSideEffects(), String.valueOf(medicine.getQuantity()));
        }
        medicineTable.print();
    }


    public void remove(String medicineId) throws NotFoundException {
        for (int i = 0; i < getSize(); i++) {
            if (medicines.get(i).getMedicineId().equals(medicineId)) {
                medicines.remove(i);
                return;
            }
        }
        throw new NotFoundException("There are no medicines with given Batch ID!");
    }

    public void edit(String[] parameterArray) throws NotFoundException {
        if (search(parameterArray[0]) != null) {
            Medicine medicine = search(parameterArray[0]);
            medicine.edit(parameterArray[1], Integer.parseInt(parameterArray[2]), parameterArray[3], parameterArray[4],
                    Integer.parseInt(parameterArray[5]));
            return;
        }
        throw new NotFoundException("There are no medicines with given Batch ID!");
    }

    public ArrayList<Medicine> getList() {
        return medicines;
    }


}
