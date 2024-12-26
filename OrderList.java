import java.util.ArrayList;

/**
 * The OrderList class represents a list of items in an order.
 * It provides functionality for adding, retrieving, and removing items, as well as calculating the total price.
 */
public class OrderList {
    private ArrayList<Item> items;

    /**
     * Constructs an empty OrderList object.
     */
    public OrderList() {
        items = new ArrayList<>();
    }

    /**
     * Adds an item to the order list.
     *
     * @param item the item to be added
     * @return true if the item is successfully added, false otherwise
     */
    public boolean addItem(Item item) {
        return items.add(item);
    }

    /**
     * Retrieves an item from the order list based on the specified index.
     *
     * @param index the index of the item to retrieve
     * @return the item at the specified index, or null if the index is out of bounds
     */
    public Item getItem(int index) {
        try {
            return items.get(index);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    /**
     * Removes an item from the order list based on the specified index.
     *
     * @param index the index of the item to remove
     * @return true if the item is successfully removed, false if the index is out of bounds
     */
    public boolean removeItem(int index) {
        try {
            items.remove(index);
            return true;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    /**
     * Returns the length of the order list.
     *
     * @return the number of items in the order list
     */
    public int getLength() {
        return items.size();
    }

    /**
     * Calculates and returns the total price of the items in the order list.
     *
     * @return the total price of the items in the order list
     */
    public double getTotalPrice() {
        double totalPrice = 0.0;
        for (Item item : items) {
            totalPrice += item.getRegularPrice();
        }
        return totalPrice;
    }
}