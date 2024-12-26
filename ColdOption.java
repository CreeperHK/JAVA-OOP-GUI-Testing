/**
 * The ColdOption interface represents an option for cold items.
 * Classes implementing this interface provide methods to get and set the coldness of an item.
 */
public interface ColdOption {

    /**
     * Returns whether the item is cold.
     *
     * @return true if the item is cold, false otherwise
     */
    public boolean getIsCold();

    /**
     * Sets the coldness of the item.
     *
     * @param cold true if the item is cold, false otherwise
     */
    public void setIsCold(boolean cold);
}