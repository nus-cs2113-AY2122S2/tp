package tp.command;

import tp.AppointmentList;
import tp.AppointmentStorage;
import tp.DoctorList;
import tp.DoctorStorage;
import tp.IHospitalException;
import tp.NurseList;
import tp.NurseStorage;
import tp.PatientList;
import tp.PatientStorage;
import tp.Ui;
import tp.WardList;
import tp.WardStorage;

public class AddWardCommand extends Command {
    protected int[] docIndexes;
    protected int[] patientIndexes;
    protected int[] nurseIndexes;
    protected int wardNumber;

    public AddWardCommand(int[] docIndexes, int[] patientIndexes, int[] nurseIndexes, int wardNumber) {
        this.docIndexes = docIndexes;
        this.patientIndexes = patientIndexes;
        this.nurseIndexes = nurseIndexes;
        this.wardNumber = wardNumber;
    }


    @Override
    public String execute(DoctorList doctorList, PatientList patientList, NurseList nurseList,
                          WardList wardList, AppointmentList appointmentList, Ui ui,
                          DoctorStorage doctorStorage, WardStorage wardStorage,
                          PatientStorage patientStorage, NurseStorage nurseStorage,
                          AppointmentStorage appointmentStorage) throws IHospitalException {
        if (wardNumber <= 0 || wardNumber > wardList.getSize()) {
            throw new IHospitalException("The ward does not exist\n");
        }

        for (int docs : docIndexes) {
            if (doctorList.getDoctor(docs).getWardNumber() == -1) {
                throw new IHospitalException("The doctor" + docs + "already assigned to a ward\n");
            }
        }

        for (int docs : nurseIndexes) {
            if (nurseList.getNurse(docs).getWardNumber() == -1) {
                throw new IHospitalException("The nurse" + docs + "already assigned to a ward\n");
            }
        }


        wardList.addWard(docIndexes, patientIndexes, nurseIndexes,wardNumber);
        return String.format(boundary + "Noted. I've added this ward:"
                                     + "\n" + wardList.getWard(wardList.getSize())
                                     + "\n" + "Now you have " + wardList.getSize()
                                     + " wards recorded in the system." + System.lineSeparator() + boundary);
    }

}
