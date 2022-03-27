package seedu.ibook.model;

import seedu.ibook.model.item.Item;
import seedu.ibook.model.product.Product;

/**
 * A wrapper class for reversible version of {@code IBook#setItem(Product, Item, Item)} method.
 */
public class ReversibleSetItemAction extends ReversibleIBookAction {

    private final Product product;
    private final Item itemToUpdate;
    private final Item updatedItem;

    /**
     * Class constructor.
     * @param product the product of which an item is to be updated.
     * @param itemToUpdate item to be updated.
     * @param updatedItem the item to replace {@code itemToUpdate}.
     */
    public ReversibleSetItemAction(Product product, Item itemToUpdate, Item updatedItem) {
        this.product = product;
        this.itemToUpdate = itemToUpdate;
        this.updatedItem = updatedItem;
    }

    @Override
    public void performForwardAction(IBook iBook) {
        iBook.setItem(product, itemToUpdate, updatedItem);
    }

    @Override
    public void performBackwardAction(IBook iBook) {
        iBook.setItem(product, updatedItem, itemToUpdate);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof ReversibleSetItemAction
                && product.equals(((ReversibleSetItemAction) other).product)
                && itemToUpdate.equals(((ReversibleSetItemAction) other).itemToUpdate)
                && updatedItem.equals(((ReversibleSetItemAction) other).updatedItem));
    }
}
