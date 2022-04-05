package arcs.data.validitychecker;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidMenuItemPriceChecker implements RawInputChecker {
    private static final String regPriceExp = "[0-9]+([,.][0-9]{1,2})?";

    final Pattern pattern = Pattern.compile(regPriceExp);

    @Override
    public boolean isValid(String price) {
        if (price == null) {
            return false;
        }
        Matcher matcher = pattern.matcher(price);
        if (matcher.matches()) {
            try {
                double priceAsFloat = Double.parseDouble(price);
                return !(priceAsFloat <= 0);
            } catch (NumberFormatException e) {
                System.out.println("Price is invalid");
            }
        } else {
            return false;
        }
        return false;
    }
}

