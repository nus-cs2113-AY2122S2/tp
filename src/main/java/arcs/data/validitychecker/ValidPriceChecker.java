package arcs.data.validitychecker;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidPriceChecker implements RawInputChecker {

    private static final String regPriceExp = "[0-9]+([,.][0-9]{1,2})?";

    final Pattern pattern = Pattern.compile(regPriceExp);

    @Override
    public boolean isValid(String price) {
        Matcher matcher = pattern.matcher(price);
        if (matcher.matches()) {
            return true;
        }
        else {
            return false;
        }
    }
}
