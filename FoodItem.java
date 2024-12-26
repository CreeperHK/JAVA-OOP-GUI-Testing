/**
 * The FoodItem class represents a food item that extends the Item class.
 * It provides functionality for managing food items, including the vegan diet option.
 */
public class FoodItem extends Item {
    private boolean isVegan;

    /**
     * Constructs a FoodItem object with the specified name, regular price, and vegan diet option.
     *
     * @param itemName      the name of the food item
     * @param regularPrice  the regular price of the food item
     * @param isVegan       true if the food item is suitable for a vegan diet, false otherwise
     */
    public FoodItem(String itemName, double regularPrice, boolean isVegan) {
        super(itemName, regularPrice);
        this.isVegan = isVegan;
    }

    /**
     * Returns whether the food item is suitable for a vegan diet.
     *
     * @return true if the food item is vegan, false otherwise
     */
    public boolean isVeganDiet() {
        return isVegan;
    }

    /**
     * Sets the suitability of the food item for a vegan diet.
     *
     * @param isVegan true if the food item is suitable for a vegan diet, false otherwise
     */
    public void setVegan(boolean isVegan) {
        this.isVegan = isVegan;
    }
}