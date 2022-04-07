package seedu.duke.assets;

import java.util.Comparator;

class ExpiryComparator implements Comparator<Medicine> {

    // override the compare() method
    public int compare(Medicine m1, Medicine m2) {
        return m1.getExpiry().compareTo(m2.getExpiry());
    }
}

