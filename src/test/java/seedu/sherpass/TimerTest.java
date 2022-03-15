package seedu.sherpass;

import org.junit.jupiter.api.Test;
import seedu.sherpass.util.Timer;
import seedu.sherpass.util.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TimerTest {
    private static Ui ui = new Ui();

    @Test
    public void testGetTimeLeft() {
        int testCase1 = 30;
        int testCase2 = 1000;
        Timer timer = new Timer(ui);
        timer.setDuration(testCase1);
        assertEquals(testCase1, timer.getTimeLeft());
        timer.setDuration(testCase2);
        assertEquals(testCase2, timer.getTimeLeft());
    }

    @Test
    public void testGetHasTimeLeft() {
        int testCase = 30;
        Timer timer = new Timer(ui);
        timer.setDuration(testCase);
        assertTrue(timer.getHasTimeLeft());
    }

}
