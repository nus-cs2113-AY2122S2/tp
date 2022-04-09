package seedu.duke.helper.command;

import seedu.duke.assets.List;
import seedu.duke.assets.Medicine;
import seedu.duke.assets.MedicineList;
import seedu.duke.exception.DuplicateEntryException;
import seedu.duke.exception.HalpmiException;
import seedu.duke.exception.NotFoundException;
import seedu.duke.status.Status;

import java.util.Arrays;

/*
 * Class that extends Abstract Command Class.
 */
public class CheckForMedicineStockCommand extends Command {

    /*
     * Constructor method that calls constructor of super class
     * @ param parameterArray an array of Strings - additional parameters given by user
     */
    public CheckForMedicineStockCommand(String[] parameterArray) {
        super(parameterArray);
    }

    /*
     * Method that takes in  MedicineList that needs to be acted on.
     * Uses parameterArray as inputs to find if required medicines exit in List.
     * @ param list a List object - should be an instance of MedicineList
     * @ return Status.MEDICINE_STOCK_FOUND_SUCCESS if changes made to list was successful
     * @ throws HalpMiException if some medicines cannot be found.
     * If exception thrown, a table of missing medicines printed.
     */
    @Override
    public Status execute(List medicineList) throws DuplicateEntryException, NotFoundException, HalpmiException {
        String[] medicineArray = Arrays.copyOfRange(parameterArray,1,parameterArray.length);
        if (medicineList instanceof MedicineList) {
            ((MedicineList) medicineList).checkStock(medicineArray);
            return Status.MEDICINE_STOCK_FOUND_SUCCESS;
        }
        assert false;
        throw new HalpmiException("Error with code! Approach Developer.");
    }
}
