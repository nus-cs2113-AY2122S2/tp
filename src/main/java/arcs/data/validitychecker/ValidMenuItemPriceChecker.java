package arcs.data.validitychecker;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidMenuItemPriceChecker implements RawInputChecker {

    /**
     * Expression allowed for Price of Menu Item.
     */
    private static final String regPriceExp = "[0-9]+([,.][0-9]{1,2})?";

    final Pattern pattern = Pattern.compile(regPriceExp);

    /**
     * Checks whether price inputted by user is valid.
     * @param price Price entered to be checked.
     * @return True if the price is a valid price.
     */
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

