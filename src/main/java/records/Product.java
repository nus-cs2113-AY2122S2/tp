package records;

import java.math.BigDecimal;
import java.time.format.DateTimeParseException;

public class Product extends Record {
    private final String productType;

    /**
     * Constructs a <code>Product</code> object.
     *
     * @param name Name of the product
     * @param price Price of the product
     * @param date Date of purchase of the product
     * @param productType Type of the product
     */
    public Product (String name, double price, String date, String productType) throws DateTimeParseException {
        super( name, price, date);
        this.productType = productType;
    }

    /** Returns product type of the record */
    public String getProductType() {
        return productType;
    }

    /**
     * @return String information of the product
     */
    @Override
    public String toString(){
        return "Product" + ": " + super.toString() + ", type = " + productType + "";
    }

    @Override
    public String saveRecords() {
        return "P " + super.saveRecords() + " | " + this.productType + System.lineSeparator();
    }
}