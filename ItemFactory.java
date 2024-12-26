/**
 * The ItemFactory class provides a factory method for creating different types of items.
 * It allows the creation of FoodItem, DrinkItem, and ComboItem objects based on the given item ID.
 */
public class ItemFactory {

    /**
     * Creates an item based on the provided item ID.
     *
     * @param itemId the ID of the item to create
     * @return an instance of the corresponding item based on the given ID
     * @throws IllegalArgumentException if the item ID is invalid or unrecognized
     */
    public static Item createItem(int itemId) {
        switch (itemId) {
            case 1:
                return new FoodItem("Chicken Macaroni", 43.0, false);
            case 2:
                return new FoodItem("Beef Noodle", 48.0, false);
            case 3:
                return new FoodItem("Vegetable Noodle", 38.0, true);
            case 4:
                return new FoodItem("Bun", 13.0, false);
            case 5:
                return new FoodItem("Vegan Bun", 15.0, true);
            case 6:
                return new DrinkItem("Chinese Tea", 12.0, false, false, false);
            case 7:
                return new ColdDrinkItem("Milk Tea", 13.0, true, true, true, 2.0, false);
            case 8:
                return new ColdDrinkItem("Coffee", 23.0, true, true, true, 3.0, false);
            case 9:
                return new ColdDrinkItem("Lemon Tea", 15.0, true, false, false, 2.0, false);
            case 10:
                return new ComboItem("Double Food", 2);
            case 11:
                return new ComboItem("Triple Food", 3);
            default:
                throw new IllegalArgumentException("Invalid item ID: " + itemId);
            
        }
    }
}