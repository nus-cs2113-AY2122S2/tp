package storage;

import data.workouts.Workout;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FileManagerTest {

    @Test
    public void convertWorkoutToFileDataFormat_normalWorkoutInput_conversionSuccess() {
        Workout testSample1 = new Workout("push up", 1);
        Workout testSample2 = new Workout("russian twist", 1000);
        Workout testSample3 = new Workout("swimming", 20);

        String expectedOutput1 = "push up | 1";
        String expectedOutput2 = "russian twist | 1000";
        String expectedOutput3 = "swimming | 20";

        FileManager fm = new FileManager();

        assertEquals(expectedOutput1, fm.convertWorkoutToFileDataFormat(testSample1));
        assertEquals(expectedOutput2, fm.convertWorkoutToFileDataFormat(testSample2));
        assertEquals(expectedOutput3, fm.convertWorkoutToFileDataFormat(testSample3));
    }

    @Test
    public void convertWorkoutToFileDataFormat_nullWorkoutInput_exceptionThrown() {
        Workout testSample1 = null;

        FileManager fm = new FileManager();

        assertThrows(NullPointerException.class,
            () -> fm.convertWorkoutToFileDataFormat(testSample1));
    }
}
