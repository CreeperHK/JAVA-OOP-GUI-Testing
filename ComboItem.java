import java.util.ArrayList;

/**
 * The ComboItem class represents a combo item that extends the Item class.
 * It provides functionality for managing a combo item, including adding/removing food items and setting a free drink.
 */
public class ComboItem extends Item {
    private ArrayList<FoodItem> foods;
    private ColdDrinkItem freeDrink;
    private int maxFood;

    /**
     * Constructs a ComboItem object with the specified name and maximum number of food items.
     *
     * @param name     the name of the combo item
     * @param maxFood  the maximum number of food items allowed in the combo
     * @throws IllegalArgumentException if the maximum number of foods is not positive
     */
    public ComboItem(String name, int maxFood) {
        super(name, maxFood);
        if (maxFood <= 0) {
            throw new IllegalArgumentException("Maximum number of foods must be positive.");
        }
        this.maxFood = maxFood;
        foods = new ArrayList<>();
        freeDrink = null;
    }

    /**
     * Returns the food item at the specified index.
     *
     * @param index the index of the food item
     * @return the food item at the specified index, or null if the index is out of bounds
     */
    public FoodItem getFood(int index) {
        if (index >= 0 && index < foods.size()) {
            return foods.get(index);
        }
        return null;
    }

    /**
     * Returns the free drink associated with the combo item.
     *
     * @return the free drink, or null if no free drink is set
     */
    public ColdDrinkItem getFreeDrink() {
        return freeDrink;
    }

    /**
     * Returns the number of food items in the combo.
     *
     * @return the number of food items
     */
    public int getNumFoods() {
        return foods.size();
    }

    /**
     * Returns the maximum number of food items allowed in the combo.
     *
     * @return the maximum number of food items
     */
    public int getMaxFood() {
        return maxFood;
    }

    /**
     * Calculates and returns the total price of the combo item.
     * The price includes the regular prices of all food items and the additional price for a cold free drink (if available and set to cold).
     *
     * @return the total price of the combo item
     */
    public double getPrice() {
        double total = 0.0;
        for (FoodItem food : foods) {
            total += food.getRegularPrice();
        }
        if (freeDrink != null && freeDrink.getIsCold()) {
            total += freeDrink.getColdExtraPrice();
        }
        return total;
    }

    /**
     * Adds a food item to the combo.
     *
     * @param food the food item to add
     * @return true if the food item is successfully added, false if the maximum number of food items has been reached
     */
    public boolean addFood(FoodItem food) {
        if (foods.size() < maxFood) {
            foods.add(food);
            return true;
        }
        return false;
    }

    /**
     * Removes the food item at the specified index from the combo.
     *
     * @param index the index of the food item to remove
     * @return true if the food item is successfully removed, false if the index is out of bounds
     */
    public boolean removeFood(int index) {
        try {
            foods.remove(index);
            return true;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    /**
     * Sets the free drink for the combo.
     *
     * @param drink the cold drink item to set as the free drink
     * @return true
     */
    public boolean setFreeDrink(ColdDrinkItem drink) {
        freeDrink = drink;
        return true;
    }

    /**
     * This method is overridden from the Item class.
     * Setting the price of a ComboItem is not allowed and will always return false.
     *
     * @param price the price to set
     * @return false
     */
    @Override
    public boolean setPrice(double price) {
        return false;
    }
}