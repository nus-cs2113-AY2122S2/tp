package storage;

import data.workouts.Workout;
import data.plans.Plan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FileManagerTest {

    @BeforeEach
    public void setUp() {
        LogHandler.startLogHandler();
    }

    @Test
    public void convertWorkoutToFileDataFormat_normalWorkoutInput_conversionSuccess() {
        Workout testWorkoutSample1 = new Workout("push up", 1);
        Workout testWorkoutSample2 = new Workout("russian twist", 1000);
        Workout testWorkoutSample3 = new Workout("swimming", 20);

        String expectedOutput1 = "push up | 1";
        String expectedOutput2 = "russian twist | 1000";
        String expectedOutput3 = "swimming | 20";

        FileManager fm = new FileManager();

        assertEquals(expectedOutput1, fm.convertWorkoutToFileDataFormat(testWorkoutSample1));
        assertEquals(expectedOutput2, fm.convertWorkoutToFileDataFormat(testWorkoutSample2));
        assertEquals(expectedOutput3, fm.convertWorkoutToFileDataFormat(testWorkoutSample3));
    }

    @Test
    public void convertPlanToFileDataFormat_normalPlanInput_conversionSuccess() {
        Workout testWorkoutSample1 = new Workout("push up", 1);
        Workout testWorkoutSample2 = new Workout("russian twist", 1000);
        Workout testWorkoutSample3 = new Workout("swimming", 20);
        var listOfWorkouts1 = new ArrayList<Workout>();
        listOfWorkouts1.add(testWorkoutSample1);
        listOfWorkouts1.add(testWorkoutSample2);
        listOfWorkouts1.add(testWorkoutSample3);
        var listOfWorkouts2 = new ArrayList<Workout>();
        listOfWorkouts2.add(testWorkoutSample1);
        listOfWorkouts2.add(testWorkoutSample3);
        Plan testPlanSample1 = new Plan("coolMan", listOfWorkouts1);
        Plan testPlanSample2 = new Plan("hotMan", listOfWorkouts2);
        String expectedOutput1 = "coolMan | push up | 1,russian twist | 1000,swimming | 20";
        String expectedOutput2 = "hotMan | push up | 1,swimming | 20";
        FileManager fm = new FileManager();

        assertEquals(expectedOutput1, fm.convertPlanToFileDataFormat(testPlanSample1));
        assertEquals(expectedOutput2, fm.convertPlanToFileDataFormat(testPlanSample2));
    }

    @Test
    public void convertWorkoutToFileDataFormat_nullWorkoutInput_exceptionThrown() {
        Workout testWorkoutSample1 = null;

        FileManager fm = new FileManager();

        assertThrows(NullPointerException.class,
            () -> fm.convertWorkoutToFileDataFormat(testWorkoutSample1));
    }

    @Test
    public void convertPlanToFileDataFormat_nullPlanInput_exceptionThrown() {
        Plan testPlanSample1 = null;

        FileManager fm = new FileManager();

        assertThrows(NullPointerException.class,
            () -> fm.convertPlanToFileDataFormat(testPlanSample1));
    }
}
