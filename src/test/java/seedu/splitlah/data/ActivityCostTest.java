package seedu.splitlah.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.splitlah.data.ActivityCost;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ActivityCostTest {

    private ActivityCost activityCost;

    @BeforeEach
    public void setUp() {
        activityCost = new ActivityCost(1, 10, 100);
    }

    // Getter and setter tests
    @Test
    public void getActivityId_returnsActivityId() {
        assertEquals(1, activityCost.getActivityId());
    }

    @Test
    public void getCostPaid_returnsCostPaid() {
        assertEquals(10, activityCost.getCostPaid());
    }

    @Test
    public void getCostOwed_returnsCostOwed()  {
        assertEquals(100, activityCost.getCostOwed());
    }
}
