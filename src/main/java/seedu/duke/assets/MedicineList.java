package seedu.duke.assets;


import seedu.duke.exception.DuplicateEntryException;
import seedu.duke.exception.UserInputErrorException;
import seedu.duke.exception.NotFoundException;
import seedu.duke.helper.UI;
import seedu.duke.helper.CommandLineTable;
import seedu.duke.helper.finder.MedicineFinder;

import java.time.LocalDate;
import java.util.ArrayList;

public class MedicineList extends List {

    private ArrayList<Medicine> medicines = new ArrayList<>();
    private ArrayList<Medicine> expiredMedicines = new ArrayList<>();
    private ArrayList<Medicine> returnedFinderArray = new ArrayList<>();

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
        for (Medicine medicine : medicines) {
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

    public void find(String[] command) {
    }

    public String getMedicineInfo(Medicine medicine) {
        return (medicine.getMedicineId() + ": "
                + medicine.getMedicineName() + ", "
                + Integer.toString(medicine.getDosage()) + ", " + medicine.getExpiry() + ", "
                + medicine.getSideEffects() + ", " + Integer.toString(medicine.getQuantity()));
    }


    //view particular medicine
    public void view(String medicineId) throws UserInputErrorException {
        Medicine medicine = getMedicine(medicineId);
        if (medicine == null) {
            throw new UserInputErrorException("Medicine doesn't exist please try again!");
        }
        CommandLineTable medicineTable = new CommandLineTable();
        medicineTable.setShowVerticalLines(true);
        medicineTable.setHeaders("MedicineId", "MedicineName", "Dosage", "Expiry", "SideEffects", "Quantity");
        medicineTable.addRow(medicine.getMedicineId(), medicine.getMedicineName(),
                String.valueOf(medicine.getDosage()),
                medicine.getExpiry(), medicine.getSideEffects(), String.valueOf(medicine.getQuantity()));
        medicineTable.print();
    }

    public void view() throws UserInputErrorException {
        CommandLineTable medicineTable = new CommandLineTable();
        //st.setRightAlign(true);//if true then cell text is right aligned
        medicineTable.setShowVerticalLines(true);
        medicineTable.setHeaders("MedicineId", "MedicineName", "Dosage", "Expiry", "SideEffects", "Quantity");

        if (medicines.size() == 0) {
            throw new UserInputErrorException("Medicine list is empty, please add medicine");
        }
        for (Medicine medicine : medicines) {
            medicineTable.addRow(medicine.getMedicineId(), medicine.getMedicineName(),
                    String.valueOf(medicine.getDosage()),
                    medicine.getExpiry(), medicine.getSideEffects(),
                    String.valueOf(medicine.getQuantity()));
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

    public void viewExpired() throws UserInputErrorException {
        CommandLineTable medicineTable = new CommandLineTable();
        //st.setRightAlign(true);//if true then cell text is right aligned
        medicineTable.setShowVerticalLines(true);
        medicineTable.setHeaders("MedicineId", "MedicineName", "Dosage", "Expiry", "SideEffects", "Quantity");
        if (expiredMedicines.size() == 0) {
            throw new UserInputErrorException("There are no expired medicines.");
        }
        for (Medicine medicine : expiredMedicines) {
            medicineTable.addRow(medicine.getMedicineId(), medicine.getMedicineName(),
                    String.valueOf(medicine.getDosage()),
                    medicine.getExpiry(), medicine.getSideEffects(),
                    String.valueOf(medicine.getQuantity()));
        }
        medicineTable.print();
    }

    public void updateStock() throws UserInputErrorException {
        for (int i = 0; i < medicines.size(); i++) {
            LocalDate date = LocalDate.parse(medicines.get(i).getExpiry());
            int quantity = medicines.get(i).getQuantity();
            LocalDate today = LocalDate.now();
            if (date.isBefore(today) || quantity == 0) {
                expiredMedicines.add(medicines.get(i));
                medicines.remove(i);
            }
        }
        viewExpired();
    }

    public void updateStockBackend() {
        for (int i = 0; i < medicines.size(); i++) {
            LocalDate date = LocalDate.parse(medicines.get(i).getExpiry());
            int quantity = medicines.get(i).getQuantity();
            LocalDate today = LocalDate.now();
            if (date.isBefore(today) || quantity == 0) {
                expiredMedicines.add(medicines.get(i));
                medicines.remove(i);
            }
        }
    }

    public void clearStock() {
        if (expiredMedicines.size() == 0) {
            UI.printParagraph("There are no expired medicines in the expired list!");
            return;
        }
        expiredMedicines.clear();
        UI.printParagraph("Expired medicines in the expired list has been cleared!");
    }

    public void checkStock(String [] medicines) throws UserInputErrorException {
        updateStockBackend();
        CommandLineTable medicineTable = new CommandLineTable();
        medicineTable.setShowVerticalLines(true);
        medicineTable.setHeaders("MedicineName", "Quantity");

        boolean hasShortage = false;

        for (int i = 0; i < medicines.length; i += 2) {
            String medicineName = medicines[i];
            int quantity = Integer.parseInt(medicines[i + 1]);
            for (Medicine a : this.medicines) {
                if (a.getMedicineName().equals(medicineName)) {
                    quantity -= a.getQuantity();
                    if (quantity <= 0) {
                        break;
                    }
                }
            }
            if (quantity > 0) {
                medicineTable.addRow(medicineName,medicines[i + 1]);
                hasShortage = true;
            }
        }

        if (hasShortage) {
            medicineTable.print();
            throw new UserInputErrorException("The medicines mentioned on the table"
                    + "above do not have enough stock to dispense!");
        }
    }

    public ArrayList<Medicine> getList() {
        return medicines;
    }

    public void findById(String[] parameters) {
        try {
            this.returnedFinderArray = MedicineFinder.findMedicineById(medicines, parameters[1]);
            createArrayOfFoundMedicines();
        } catch (NullPointerException e) {
            UI.printParagraph("Medicine with given id doesn't exist. Please try again!");
        }
    }

    public void findByDosage(String[] parameters) {
        try {
            this.returnedFinderArray = MedicineFinder.findMedicineByDosage(medicines, Integer.parseInt(parameters[1]));
            createArrayOfFoundMedicines();
        } catch (NullPointerException e) {
            UI.printParagraph("Medicine with given dosage doesn't exist. Please try again!");
        }
    }

    public void findByExpiry(String[] parameters) {
        try {
            this.returnedFinderArray = MedicineFinder.findMedicineByExpiry(medicines, parameters[1]);
            createArrayOfFoundMedicines();
        } catch (NullPointerException e) {
            UI.printParagraph("Medicine with given expiry doesn't exist. Please try again!");
        }
    }

    public void findBySideEffects(String[] parameters) {
        try {
            this.returnedFinderArray = MedicineFinder.findMedicineBySideEffects(medicines, parameters[1]);
            createArrayOfFoundMedicines();
        } catch (NullPointerException e) {
            UI.printParagraph("Medicine with given side effects doesn't exist. Please try again!");
        }
    }

    public void findByQuantity(String[] parameters) {
        try {
            this.returnedFinderArray = MedicineFinder.findMedicineByQuantity(medicines,
                    Integer.parseInt(parameters[1]));
            createArrayOfFoundMedicines();
        } catch (NullPointerException e) {
            UI.printParagraph("Medicine with given quantity doesn't exist. Please try again!");
        }
    }

    public void findByName(String[] parameters) {
        try {
            this.returnedFinderArray = MedicineFinder.findMedicineByName(medicines, parameters[1]);
            createArrayOfFoundMedicines();
        } catch (NullPointerException e) {
            UI.printParagraph("Medicine with given name doesn't exist. Please try again!");
        }
    }


    private void createArrayOfFoundMedicines() {
        if (returnedFinderArray.isEmpty()) {
            UI.printParagraph("Medicine doesn't exist please try again!");
        } else {
            CommandLineTable findMedicineTable = new CommandLineTable();
            for (int i = 0; i < returnedFinderArray.size(); i++) {
                findMedicineTable.setShowVerticalLines(true);
                findMedicineTable.setHeaders("MedicineId", "MedicineName", "Dosage", "Expiry",
                        "SideEffects", "Quantity");
                findMedicineTable.addRow(returnedFinderArray.get(i).getMedicineId(),
                        returnedFinderArray.get(i).getMedicineName(),
                        String.valueOf(returnedFinderArray.get(i).getDosage()),
                        returnedFinderArray.get(i).getExpiry(), returnedFinderArray.get(i).getSideEffects(),
                        String.valueOf(returnedFinderArray.get(i).getQuantity()));

            }
            findMedicineTable.print();
        }
    }

    public void dispenseMedicine(String[] medicineArray) {
        CommandLineTable medicineTable = new CommandLineTable();
        medicineTable.setShowVerticalLines(true);
        medicineTable.setHeaders("MedicineId", "MedicineName", "Expiry", "Quantity");
        medicines.sort(new ExpiryComparator());
        for (int i = 0; i < medicineArray.length; i += 2) {
            String medicineName = medicineArray[i];
            int quantity = Integer.parseInt(medicineArray[i + 1]);
            for (Medicine a : this.medicines) {
                if (a.getMedicineName().equals(medicineName)) {
                    int quantityTaken = (quantity > a.getQuantity()) ? a.getQuantity() : quantity;
                    quantity = (quantity > quantityTaken) ? quantity - quantityTaken : 0;
                    a.quantity = (a.getQuantity() > quantityTaken) ? a.getQuantity() - quantityTaken : 0;

                    medicineTable.addRow(a.getMedicineId(), a.getMedicineName(), a.getExpiry(),
                            String.valueOf(quantityTaken));
                }
                if (quantity == 0) {
                    break;
                }
            }
        }
        medicineTable.print();
    }
}

