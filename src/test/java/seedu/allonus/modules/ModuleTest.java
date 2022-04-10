package seedu.allonus.modules;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Represents test class to test Module class methods.
 */
public class ModuleTest {

    Module cs2113;
    Module cs3244;
    Module ee4204;

    @BeforeEach
    public void setUp() {
        cs2113 = new Module("CS2113", "Lecture", "Friday", "4:00pm-6:00pm");
        cs3244 = new Module("CS3244", "Tutorial", "Monday", "2:00pm-3:00pm");
        ee4204 = new Module("EE4204", "Exam", "Monday", "10:00am-12:00pm");
    }

    /**
     * Tests the toString() method in Module class.
     */
    @Test
    public void testStringConversion() {
        assertEquals("[Module] CS2113 Lecture: Thursday, 2:00pm-4:00pm",
                new Module("CS2113","Lecture","Thursday","2:00pm-4:00pm").toString());
    }


    @Test
    public void testGetModuleCode() {
        assertEquals("CS2113", cs2113.getModuleCode());
        assertEquals("CS3244", cs3244.getModuleCode());
        assertEquals("EE4204", ee4204.getModuleCode());

        assertNotEquals("CS3244", cs2113.getModuleCode());
        assertNotEquals("EE4204", cs3244.getModuleCode());
        assertNotEquals("CS2113", ee4204.getModuleCode());
    }

    @Test
    public void testGetCategory() {
        assertEquals("Lecture", cs2113.getCategory());
        assertEquals("Tutorial", cs3244.getCategory());
        assertEquals("Exam", ee4204.getCategory());

        assertNotEquals("Tutorial", cs2113.getCategory());
        assertNotEquals("Lecture", cs3244.getCategory());
        assertNotEquals("Lecture", ee4204.getCategory());
    }

    @Test
    public void testGetDay() {
        assertEquals("Friday", cs2113.getDay());
        assertEquals("Monday", cs3244.getDay());
        assertEquals("Monday", ee4204.getDay());

        assertNotEquals("Saturday", cs2113.getDay());
        assertNotEquals("Tuesday", cs3244.getDay());
        assertNotEquals("Sunday", ee4204.getDay());
    }

    @Test
    public void testGetTime() {
        assertEquals("4:00pm-6:00pm", cs2113.getTimeSlot());
        assertEquals("2:00pm-3:00pm", cs3244.getTimeSlot());
        assertEquals("10:00am-12:00pm", ee4204.getTimeSlot());

        assertNotEquals("10:00am-12:00pm", cs2113.getTimeSlot());
        assertNotEquals("4:00pm-6:00pm", cs3244.getTimeSlot());
        assertNotEquals("2:00pm-3:00pm", ee4204.getTimeSlot());
    }

    @Test
    public void testSetCode() {
        cs2113.setModuleCode("CS2113T");
        assertEquals("CS2113T", cs2113.getModuleCode());

        cs3244.setModuleCode("CS3244A");
        assertEquals("CS3244A", cs3244.getModuleCode());

        ee4204.setModuleCode("EE4204A");
        assertEquals("EE4204A", ee4204.getModuleCode());
    }

    @Test
    public void testSetCategory() {
        cs2113.setCategory("Lecture");
        assertEquals("Lecture", cs2113.getCategory());

        cs3244.setCategory("Exam");
        assertEquals("Exam", cs3244.getCategory());

        ee4204.setCategory("Laboratory");
        assertEquals("Laboratory", ee4204.getCategory());
    }

    @Test
    public void testSetDay() {
        cs2113.setDay("Tuesday");
        assertEquals("Tuesday", cs2113.getDay());

        cs3244.setDay("22-09-2022");
        assertEquals("22-09-2022", cs3244.getDay());

        ee4204.setDay("Friday");
        assertEquals("Friday", ee4204.getDay());
    }

    @Test
    public void testSetTime() {
        cs2113.setTimeSlot("10:00am-12:00pm");
        assertEquals("10:00am-12:00pm", cs2113.getTimeSlot());

        cs3244.setTimeSlot("4:00pm-6:00pm");
        assertEquals("4:00pm-6:00pm", cs3244.getTimeSlot());

        ee4204.setTimeSlot("2:00pm-3:00pm");
        assertEquals("2:00pm-3:00pm", ee4204.getTimeSlot());
    }

}
