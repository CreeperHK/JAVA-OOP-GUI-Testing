/**
 * Represents a cold drink item that extends the DrinkItem class and implements the ColdOption interface.
 * This class provides additional functionality for managing cold drink items.
 */
public class ColdDrinkItem extends DrinkItem implements ColdOption {

    private double coldExtraPrice;
    private boolean isCold;

    /**
     * Constructs a ColdDrinkItem object with the specified parameters.
     *
     * @param itemName       the name of the cold drink item
     * @param regularPrice   the regular price of the cold drink item
     * @param sugar          indicates if the cold drink item contains sugar
     * @param milk           indicates if the cold drink item contains milk
     * @param canMilk        indicates if the cold drink item can be served with milk
     * @param coldExtraPrice the additional price for making the cold drink item cold
     * @param isCold         indicates if the cold drink item is cold
     * @throws IllegalArgumentException if the coldExtraPrice is negative
     */
    public ColdDrinkItem(String itemName, double regularPrice, boolean sugar, boolean milk, boolean canMilk,
                         double coldExtraPrice, boolean isCold) {
        super(itemName, regularPrice, sugar, milk, canMilk);
        if (coldExtraPrice < 0) {
            throw new IllegalArgumentException("Cold extra price cannot be negative.");
        }
        this.coldExtraPrice = coldExtraPrice;
        this.isCold = isCold;
    }

    /**
     * Returns the additional price for making the cold drink item cold.
     *
     * @return the cold extra price
     */
    public double getColdExtraPrice() {
        return coldExtraPrice;
    }

    /**
     * Returns whether the cold drink item is cold.
     *
     * @return true if the cold drink item is cold, false otherwise
     */
    public boolean getIsCold() {
        return isCold;
    }

    /**
     * Sets the additional price for making the cold drink item cold.
     *
     * @param coldExtraPrice the cold extra price to set
     * @return true if the cold extra price is set successfully, false if the coldExtraPrice is negative
     */
    public boolean setColdExtraPrice(double coldExtraPrice) {
        if (coldExtraPrice >= 0) {
            this.coldExtraPrice = coldExtraPrice;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Sets whether the cold drink item is cold.
     *
     * @param cold true if the cold drink item is cold, false otherwise
     */
    public void setIsCold(boolean cold) {
        isCold = cold;
    }
}