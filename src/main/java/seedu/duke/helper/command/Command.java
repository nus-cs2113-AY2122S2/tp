package seedu.duke.helper.command;

import seedu.duke.assets.AppointmentList;
import seedu.duke.assets.DoctorList;
import seedu.duke.assets.List;
import seedu.duke.assets.MedicineList;
import seedu.duke.assets.PatientList;
import seedu.duke.exception.DuplicateEntryException;
import seedu.duke.exception.HalpmiException;
import seedu.duke.exception.NotFoundException;
import seedu.duke.status.Status;


public abstract class Command {
    protected String[] parameterArray;

    Command(String[] parameterArray) {
        this.parameterArray = parameterArray;
    }

    public abstract Status execute(List list) throws DuplicateEntryException, NotFoundException;

}