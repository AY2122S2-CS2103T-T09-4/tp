package seedu.ibook.model.product;

import static java.util.Objects.requireNonNull;
import static seedu.ibook.commons.util.AppUtil.checkArgument;

/**
 * Represents a Product's price in the ibook.
 * Guarantees: immutable; is valid as declared in {@link #isValidPrice(String)}
 */
public class Price {

    private static class WildPrice extends Price {
        private WildPrice() {};

        @Override
        public boolean equals(Object other) {
            if (other instanceof Price) {
                return true;
            } else {
                return false;
            }
        }

    }

    public static final WildPrice WILDPRICE = new WildPrice();
    public static final String MESSAGE_CONSTRAINTS =
            "Prices should only be of type double, and should not be negative";

    /*
     * The first character of the name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "\\d+(?:.\\d{1,2})?";

    public final Double price;

    private Price() {
        price = 0.00;
    }

    /**
     * Constructs a {@code Price}.
     *
     * @param price A valid price.
     */
    public Price(String price) {
        requireNonNull(price);
        checkArgument(isValidPrice(price), MESSAGE_CONSTRAINTS);
        this.price = Double.parseDouble(price);
    }

    /**
     * Returns true if a given string is a valid price.
     */
    public static boolean isValidPrice(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return String.format("$%.2f", price);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Price // instanceof handles nulls
                && price.equals(((Price) other).price)); // state check
    }

    @Override
    public int hashCode() {
        return price.hashCode();
    }

}