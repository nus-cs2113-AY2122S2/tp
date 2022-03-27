package seedu.duke.helper;


import org.junit.jupiter.api.Test;

import seedu.duke.exception.HalpmiException;



import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.fail;

/*
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

    @Test
    void parseDeletePatientTest() {
        String parameter = "S1234567A";
        String[] parameterArray = {"S1234567A"};
        try {
            assertArrayEquals(parameterArray,Parser.parseDeletePatient(parameter).parameterArray);
        } catch (HalpmiException e) {
            fail("Should not be throwing exception!");
        }
    }

    @Test
    void parseDeletePatientInvalidParamTest() {
        String[] parameters = {"S1234567","S1234567A,S1235467A", ""};
        for (String s : parameters) {
            try {
                Parser.parseDeletePatient(s);
                fail("Should be throwing exception! Failed for: " + s);
            } catch (HalpmiException h) {
                assert true;
            }
        }
    }

    @Test
    void parseDeleteDoctorTest() {
        String parameter = "S1234567A";
        String[] parameterArray = {"S1234567A"};
        try {
            assertArrayEquals(parameterArray,Parser.parseDeleteDoctor(parameter).parameterArray);
        } catch (HalpmiException e) {
            fail("Should not be throwing exception!");
        }
    }

    @Test
    void parseDeleteDoctorInvalidParamTest() {
        String[] parameters = {"S1234567","S1234567A,S1235467A", ""};
        for (String s : parameters) {
            try {
                Parser.parseDeleteDoctor(s);
                fail("Should be throwing exception! Failed for: " + s);
            } catch (HalpmiException h) {
                assert true;
            }
        }
    }

    @Test
    void parseDeleteMedicineTest() {
        String parameter = "S123";
        String[] parameterArray = {"S123"};
        try {
            assertArrayEquals(parameterArray,Parser.parseDeleteMedicine(parameter).parameterArray);
        } catch (HalpmiException e) {
            fail("Should not be throwing exception!");
        }
    }

    @Test
    void parseDeleteMedicineInvalidParamTest() {
        String[] parameters = {"S1234567A,S1235467A"};
        for (String s : parameters) {
            try {
                Parser.parseDeleteMedicine(s);
                fail("Should be throwing exception! Failed for: " + s);
            } catch (HalpmiException h) {
                assert true;
            }
        }
    }

    @Test
    void parseViewDoctorWithParamTest() {
        String parameter = "S1234567A";
        String[] parameterArray = {"S1234567A"};
        try {
            assertArrayEquals(parameterArray,Parser.parseViewDoctor(parameter).parameterArray);
        } catch (HalpmiException e) {
            fail("Should not be throwing exception!");
        }
    }

    @Test
    void parseViewDoctorWithoutParamTest() {
        String parameter = null;
        String[] parameterArray = null;
        try {
            assertArrayEquals(parameterArray,Parser.parseViewDoctor(parameter).parameterArray);
        } catch (HalpmiException e) {
            fail("Should not be throwing exception!");
        }
    }

    @Test
    void parseViewDoctorInvalidParamTest() {
        String[] parameters = {"S1234567","S1234567A,S1235467A", ""};

        for (String s : parameters) {
            try {
                Parser.parseViewDoctor(s);
                fail("Should be throwing exception! Failed for: " + s);
            } catch (HalpmiException e) {
                assert true;
            }
        }
    }

    @Test
    void parseViewPatientWithParamTest() {
        String parameter = "S1234567A";
        String[] parameterArray = {"S1234567A"};
        try {
            assertArrayEquals(parameterArray,Parser.parseViewPatient(parameter).parameterArray);
        } catch (HalpmiException e) {
            fail("Should not be throwing exception!");
        }
    }

    @Test
    void parseViewPatientWithoutParamTest() {
        String parameter = null;
        String[] parameterArray = null;
        try {
            assertArrayEquals(parameterArray,Parser.parseViewPatient(parameter).parameterArray);
        } catch (HalpmiException e) {
            fail("Should not be throwing exception!");
        }
    }

    @Test
    void parseViewPatientInvalidParamTest() {
        String[] parameters = {"S1234567","S1234567A,S1235467A", ""};

        for (String s : parameters) {
            try {
                Parser.parseViewPatient(s);
                fail("Should be throwing exception! Failed for: " + s);
            } catch (HalpmiException e) {
                assert true;
            }
        }
    }

    @Test
    void parseViewMedicineWithParamTest() {
        String parameter = "S1234";
        String[] parameterArray = {"S1234"};
        try {
            assertArrayEquals(parameterArray,Parser.parseViewMedicine(parameter).parameterArray);
        } catch (HalpmiException e) {
            fail("Should not be throwing exception!");
        }
    }

    @Test
    void parseViewMedicineWithoutParamTest() {
        String parameter = null;
        String[] parameterArray = null;
        try {
            assertArrayEquals(parameterArray,Parser.parseViewMedicine(parameter).parameterArray);
        } catch (HalpmiException e) {
            fail("Should not be throwing exception!");
        }
    }

    @Test
    void parseViewMedicineInvalidParamTest() {
        String[] parameters = {"S1234567A,S1235467A"};

        for (String s : parameters) {
            try {
                Parser.parseViewMedicine(s);
                fail("Should be throwing exception! Failed for: " + s);
            } catch (HalpmiException e) {
                assert true;
            }
        }
    }

    @Test
    void parseEditMedicineTest() {
        String parameter = "S123,Paracetamol,500,2025-02-02,Headaches,100";
        String[] parameterArray = { "S123","Paracetamol","500","2025-02-02","Headaches","100"};
        try {
            assertArrayEquals(parameterArray,Parser.parseEditMedicine(parameter).parameterArray);
        } catch (HalpmiException e) {
            fail("Should not be throwing exception");
        }
    }

    @Test
    void parseEditMedicineInvalidParamTest() {
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
*/