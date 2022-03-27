package seedu.ibook.model;

import seedu.ibook.model.item.Item;
import seedu.ibook.model.product.Product;

/**
 * A wrapper class for reversible version of {@code IBook#addItem(Product, Item)} method.
 */
public class ReversibleAddItemAction extends ReversibleIBookAction {

    private final Product product;
    private final Item item;

    /**
     * Class constructor.
     * @param product the product to which an item is added to.
     * @param item the item to be added.
     */
    public ReversibleAddItemAction(Product product, Item item) {
        this.product = product;
        this.item = item;
    }

    @Override
    public void performForwardAction(IBook iBook) {
        iBook.addItem(product, item);
    }

    @Override
    public void performBackwardAction(IBook iBook) {
        iBook.removeItem(product, item);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof ReversibleAddItemAction
                && product.equals(((ReversibleAddItemAction) other).product)
                && item.equals(((ReversibleAddItemAction) other).item));
    }
}
