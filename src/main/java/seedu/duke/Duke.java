package seedu.duke;

import tp.IHospital;
import tp.IHospitalException;

public class Duke {
    public static void main(String[] args) throws IHospitalException {
        new IHospital().run();
    }
}