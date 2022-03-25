package seedu.duke.helper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import seedu.duke.exception.HalpmiException;
import seedu.duke.helper.command.AddPatientCommand;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;


class ParserTest {

    @Test
    void parseAddPatientTest() {
        String parameter = "S1234567A,JOHN,23,M,SINGAPORE,1999-01-01,2022-01-01";
        String[] parameterArray = {"S1234567A","JOHN","23","M","SINGAPORE","1999-01-01","2022-01-01"};
        try {
            assertArrayEquals(parameterArray,Parser.parseAddPatient(parameter).parameterArray);
        } catch (HalpmiException e) {
            fail("Should not be throwing exception!");
        }
    }

    @Test
    void parseAddPatientInvalidParamTest() {
        String[] parameters = {"S1234567,JOHN,23,M,SINGAPORE,1999-01-01,2022-01-01",
                "S1234567A,JOHN1,23,M,SINGAPORE,1999-01-01,2022-01-01",
                "S1234567A,JOHN,2B,M,SINGAPORE,1999-01-01,2022-01-01",
                "S1234567A,JOHN,23,T,SINGAPORE,1999-01-01,2022-01-01",
                "S1234567A,JOHN,23,M,SINGAPORE,1999,2022-01-01",
                "S1234567A,JOHN,23,M,SINGAPORE,1999-01-01,2022-100-01",
                "S1234567A,JOHN,23,M,SINGAPORE,1999-01-01,2022-01-01,123"};
        for (String s : parameters) {
            try {
                Parser.parseAddPatient(s);
                fail("Should be throwing exception! Failed for: " + s);
            } catch (HalpmiException e) {
                assert true;
            }
        }
    }

    @Test
    void parseAddDoctorTest() {
        String parameter = "S1234567A,JOHN,23,M,SINGAPORE,1999-01-01,Paediatrics";
        String[] parameterArray = {"S1234567A","JOHN","23","M","SINGAPORE","1999-01-01","Paediatrics"};
        try {
            assertArrayEquals(parameterArray,Parser.parseAddDoctor(parameter).parameterArray);
        } catch (HalpmiException e) {
            fail("Should not be throwing exception!");
        }
    }

    @Test
    void parseAddDoctorInvalidParamTest() {
        String[] parameters = {"S1234567,JOHN,23,M,SINGAPORE,1999-01-01,Paediatrics",
                "S1234567A,JOHN1,23,M,SINGAPORE,1999-01-01,Paediatrics",
                "S1234567A,JOHN,2B,M,SINGAPORE,1999-01-01,Paediatrics",
                "S1234567A,JOHN,23,T,SINGAPORE,1999-01-01,Paediatrics",
                "S1234567A,JOHN,23,M,SINGAPORE,1999,Paediatrics",
                "S1234567A,JOHN,23,M,SINGAPORE,1999-01-01,Paediatrics1",
                "S1234567A,JOHN,23,M,SINGAPORE,1999-01-01"};
        for (String s : parameters) {
            try {
                Parser.parseAddDoctor(s);
                fail("Should be throwing exception! Failed for: " + s);
            } catch (HalpmiException e) {
                assert true;
            }
        }
    }

    @Test
    void parseAddMedicineTest() {
        String parameter = "S123,Paracetamol,500,2025-02-02,Headaches,100";
        String[] parameterArray = { "S123","Paracetamol","500","2025-02-02","Headaches","100"};
        try {
            assertArrayEquals(parameterArray,Parser.parseAddMedicine(parameter).parameterArray);
        } catch (HalpmiException e) {
            fail("Should not be throwing exception");
        }
    }

    @Test
    void parseAddMedicineInvalidParamTest() {
        String[] parameters = {"S123,Paracetamol1,500,2025-02-02,Headaches,100",
                "S123,Paracetamol,-1,2025-02-02,Headaches,100",
                "S123,Paracetamol,50b,2025-02-02,Headaches,100",
                "S123,Paracetamol,500,2025-24-02,Headaches,100",
                "S123,Paracetamol,500,2025-02-02,Headaches"};

        for (String s : parameters) {
            try {
                Parser.parseAddMedicine(s);
                fail("Should be throwing exception! Failed for: " + s);
            } catch (HalpmiException e) {
                assert true;
            }
        }
    }



}
