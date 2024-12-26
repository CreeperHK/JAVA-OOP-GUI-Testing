/**
 * The Item class represents an abstract item.
 * It provides basic functionality for managing items, including name, regular price, and string representation.
 */
public abstract class Item {
    private String itemName;
    private double regularPrice;

    /**
     * Constructs an Item object with the specified name and regular price.
     *
     * @param itemName      the name of the item
     * @param regularPrice  the regular price of the item
     */
    public Item(String itemName, double regularPrice) {
        this.itemName = itemName;
        this.regularPrice = regularPrice;
    }

    /**
     * Returns the name of the item.
     *
     * @return the name of the item
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Returns the regular price of the item.
     *
     * @return the regular price of the item
     */
    public double getRegularPrice() {
        return regularPrice;
    }

    /**
     * Sets the name of the item.
     *
     * @param name the name of the item
     * @return true if the name is successfully set, false if the name is empty
     */
    public boolean setName(String name) {
        if (name.isEmpty()) {
            return false;
        }
        this.itemName = name;
        return true;
    }

    /**
     * Sets the regular price of the item.
     *
     * @param price the regular price of the item
     * @return true if the price is successfully set, false if the price is negative
     */
    public boolean setPrice(double price) {
        if (price < 0) {
            return false;
        }
        this.regularPrice = price;
        return true;
    }

    /**
     * Returns a string representation of the item.
     *
     * @return the string representation of the item in the format: "itemName ($regularPrice)"
     */
    @Override
    public String toString() {
        return itemName + " ($" + String.format("%.1f", regularPrice) + ")";
    }
}