package seedu.duke.assets;
import java.util.Comparator;

public abstract class SortItems implements Comparator<DateItem>  {
    public int compare(DateItem a, DateItem b)
    {

        return b.date.compareTo(a.date);
    }
}
