/**
 * The DrinkItem class represents a drink item that extends the Item class.
 * It provides functionality for managing drink items, including sugar and milk options.
 */
public class DrinkItem extends Item {
    private boolean sugar;
    private boolean milk;
    private boolean canMilk;

    /**
     * Constructs a DrinkItem object with the specified name, regular price, sugar option, milk option, and milk availability.
     *
     * @param itemName      the name of the drink item
     * @param regularPrice  the regular price of the drink item
     * @param sugar         true if the drink item has sugar, false otherwise
     * @param milk          true if the drink item has milk, false otherwise
     * @param canMilk       true if milk can be added to the drink item, false otherwise
     */
    public DrinkItem(String itemName, double regularPrice, boolean sugar, boolean milk, boolean canMilk) {
        super(itemName, regularPrice);
        this.sugar = sugar;
        this.milk = milk;
        this.canMilk = canMilk;
    }

    /**
     * Returns whether the drink item has sugar.
     *
     * @return true if the drink item has sugar, false otherwise
     */
    public boolean hasSugar() {
        return sugar;
    }

    /**
     * Returns whether the drink item has milk.
     *
     * @return true if the drink item has milk, false otherwise
     */
    public boolean hasMilk() {
        return milk;
    }

    /**
     * Returns whether milk can be added to the drink item.
     *
     * @return true if milk can be added, false otherwise
     */
    public boolean canAddMilk() {
        return canMilk;
    }

    /**
     * Sets the presence of sugar in the drink item.
     *
     * @param sugar true if the drink item should have sugar, false otherwise
     */
    public void setSugar(boolean sugar) {
        this.sugar = sugar;
    }

    /**
     * Sets the presence of milk in the drink item.
     * Throws an IllegalStateException if the drink item does not allow milk to be added.
     *
     * @param milk true if the drink item should have milk, false otherwise
     * @throws IllegalStateException if milk cannot be added to the drink item
     */
    public void setMilk(boolean milk) {
        if (!canMilk) {
            throw new IllegalStateException("No milk can be added to the drink.");
        }
        this.milk = milk;
    }
}