import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ItemGUI {
    private OrderList orderList;
    private JFrame frame;
    private DefaultListModel<Item> listModel;
    private JList<Item> orderListJList;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ItemGUI().createAndShowGUI();
            }
        });
    }

    /**
     * Constructs an ItemGUI object.
     * Initializes the order list and list model.
     */
    public ItemGUI() {
        orderList = new OrderList();
        listModel = new DefaultListModel<>();
    }
    
    /**
     * Creates and displays the graphical user interface.
     * Sets up the frame, panels, labels, buttons, and event listeners.
     */
    private void createAndShowGUI() {
        frame = new JFrame("Café de Happy");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());
    
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    
        JLabel titleLabel = new JLabel("Order List");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(titleLabel, BorderLayout.NORTH);
    
        orderListJList = new JList<>(listModel);
        orderListJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(orderListJList);
        panel.add(scrollPane, BorderLayout.CENTER);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(frame, "Are you sure you want to close the program?",
                        "Confirm Close", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    frame.dispose();
                }
            }
        });
    
        JButton addButton = new JButton("Add Item");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAddItem(orderListJList);
            }
        });
    
        JButton removeButton = new JButton("Remove Item");
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeSelectedItem(orderListJList);
            }
        });
    
        JButton totalPriceButton = new JButton("Show Total Price");
        totalPriceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showTotalPrice();
            }
        });
    
        JButton doubleFoodButton = new JButton("Double Food");
        doubleFoodButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setDoubleFood();
            }
        });
    
        JButton tripleFoodButton = new JButton("Triple Food");
        tripleFoodButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setTripleFood();
            }
        });
    
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(totalPriceButton);
        buttonPanel.add(doubleFoodButton);
        buttonPanel.add(tripleFoodButton);
    
        panel.add(buttonPanel, BorderLayout.SOUTH);
    
        frame.add(panel);
        frame.setVisible(true);

        
    }

    /**
     * Sets the price of the selected food item to zero and the price of the selected drink item to zero or a specified amount.
     * Asks the user for additional customizations for the drink item.
     * Adds the modified food and drink items to the order list and updates the GUI.
     */
    private void setDoubleFood() {
        int numberOfFoodItems = 5;
        int numberOfDrinkItems = 4;
        String[] foodItemOptions = new String[numberOfFoodItems];
        String[] foodItemIds = new String[numberOfFoodItems];
        String[] drinkItemOptions = new String[numberOfDrinkItems];
        String[] drinkItemIds = new String[numberOfDrinkItems];
    
        for (int i = 1; i <= numberOfFoodItems; i++) {
            Item foodItem = ItemFactory.createItem(i);
            foodItemOptions[i - 1] = i + ". " + foodItem.getItemName() + " $" + foodItem.getRegularPrice();
            foodItemIds[i - 1] = String.valueOf(i);
        }
        for (int i = 1; i <= numberOfDrinkItems; i++) {
            int itemId = i + numberOfFoodItems;
            Item drinkItem = ItemFactory.createItem(itemId);
            drinkItemOptions[i - 1] = itemId + ". " + drinkItem.getItemName() + " $" + drinkItem.getRegularPrice();
            drinkItemIds[i - 1] = String.valueOf(itemId);
        }
    
        JComboBox<String> foodItemIdComboBox = new JComboBox<>(foodItemOptions);
        foodItemIdComboBox.setEditable(false);
        JComboBox<String> drinkItemIdComboBox = new JComboBox<>(drinkItemOptions);
        drinkItemIdComboBox.setEditable(false);
    
        Object[] message = {
                "Select Food Item ID:", foodItemIdComboBox,
                "Select Drink Item ID:", drinkItemIdComboBox
        };
    
        int result = JOptionPane.showOptionDialog(frame, message, "Select Item IDs",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
    
        if (result == JOptionPane.OK_OPTION) {
            String foodItemIdStr = (String) foodItemIds[foodItemIdComboBox.getSelectedIndex()];
            String drinkItemIdStr = (String) drinkItemIds[drinkItemIdComboBox.getSelectedIndex()];
    
            if (foodItemIdStr != null && !foodItemIdStr.isEmpty() && drinkItemIdStr != null && !drinkItemIdStr.isEmpty()) {
                try {
                    int foodItemId = Integer.parseInt(foodItemIdStr);
                    int drinkItemId = Integer.parseInt(drinkItemIdStr);
    
                    Item foodItem = ItemFactory.createItem(foodItemId);
                    Item drinkItem = ItemFactory.createItem(drinkItemId);
    
                    boolean sugar = false;
                    boolean milk = false;
                    boolean cold = false;
                    boolean vegan = false;

                    if (foodItemId != 0){
                        foodItem.setPrice(0.0);
                    }

                    if (drinkItemId == 6){
                        drinkItem.setPrice(0.0);
                    }
    
                    if (drinkItemId >= 7 && drinkItemId <= 9) {
                        if (drinkItemId == 7) {
                            int sugarOption = JOptionPane.showConfirmDialog(frame, "Do you want to add sugar to this drink?", "Add Sugar", JOptionPane.YES_NO_OPTION);
                            if (sugarOption == JOptionPane.YES_OPTION) {
                                sugar = true;
                            }
                            int milkOption = JOptionPane.showConfirmDialog(frame, "Do you want to add milk to this drink?", "Add Milk", JOptionPane.YES_NO_OPTION);
                            if (milkOption == JOptionPane.YES_OPTION) {
                                milk = true;
                            }
                            int coldOption = JOptionPane.showConfirmDialog(frame, "Do you want a cold version for this drink? (Additional $2.0)?", "Add Cold", JOptionPane.YES_NO_OPTION);
                            if (coldOption == JOptionPane.YES_OPTION) {
                                cold = true;
                                drinkItem.setPrice(2.0);
                            } else {
                                drinkItem.setPrice(0.0);
                            }
                        } else if (drinkItemId == 8) {
                            int sugarOption = JOptionPane.showConfirmDialog(frame, "Do you want to add sugar to this drink?", "Add Sugar", JOptionPane.YES_NO_OPTION);
                            if (sugarOption == JOptionPane.YES_OPTION) {
                                sugar = true;
                            }
                            int milkOption = JOptionPane.showConfirmDialog(frame, "Do you want to add milk to this drink?", "Add Milk", JOptionPane.YES_NO_OPTION);
                            if (milkOption == JOptionPane.YES_OPTION) {
                                milk = true;
                            }
                            int coldOption = JOptionPane.showConfirmDialog(frame, "Do you want a cold version for this drink? (Additional $3.0)?", "Add Cold", JOptionPane.YES_NO_OPTION);
                            if (coldOption == JOptionPane.YES_OPTION) {
                                cold = true;
                                drinkItem.setPrice(3.0);
                            } else {
                                drinkItem.setPrice(0.0);
                            }
                        } else {
                            int sugarOption = JOptionPane.showConfirmDialog(frame, "Do you want to add sugar to this drink?", "Add Sugar", JOptionPane.YES_NO_OPTION);
                            if (sugarOption == JOptionPane.YES_OPTION) {
                                sugar = true;
                            }
                            int coldOption = JOptionPane.showConfirmDialog(frame, "Do you want a cold version for this drink? (Additional $2.0)?", "Add Cold", JOptionPane.YES_NO_OPTION);
                            if (coldOption== JOptionPane.YES_OPTION) {
                                cold = true;
                                drinkItem.setPrice(2.0);
                            } else {
                                drinkItem.setPrice(0.0);}
                        }
                    }
                    
                    StringBuilder itemNameBuilder = new StringBuilder(drinkItem.getItemName());
                    if (sugar) { itemNameBuilder.append(" (+Sugar)"); }
                    if (milk) { itemNameBuilder.append(" (+Milk)"); }
                    if (cold) {
                        if (drinkItemId == 8) {
                            itemNameBuilder.append(" (+Cold +$3)");
                        } else {
                            itemNameBuilder.append(" (+Cold +$2)");
                        }
                    }
                    if (vegan) {itemNameBuilder.append(" (Vegan)"); }
                    drinkItem.setName(itemNameBuilder.toString());
                    
                    boolean addedFoodItem = orderList.addItem(foodItem);
                    boolean addedDrinkItem = orderList.addItem(drinkItem);
                    
                    if (addedFoodItem && addedDrinkItem) {
                        listModel.addElement(foodItem);
                        listModel.addElement(drinkItem);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Maximum item limit reached.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } finally{}
            }
        }
    }

    /**
     * Sets the price of the selected food item to zero and the price of the selected drink item to zero or a specified amount.
     * Asks the user for additional customizations for the drink item.
     * Adds the modified food and drink items to the order list and updates the GUI.
     */
    private void setTripleFood() {
        int numberOfFoodItems = 5;
        int numberOfDrinkItems = 4;
        String[] foodItemOptions = new String[numberOfFoodItems];
        String[] foodItemIds = new String[numberOfFoodItems];
        String[] drinkItemOptions = new String[numberOfDrinkItems];
        String[] drinkItemIds = new String[numberOfDrinkItems];

        for (int i = 1; i <= numberOfFoodItems; i++) {
            Item foodItem = ItemFactory.createItem(i);
            foodItemOptions[i - 1] = i + ". " + foodItem.getItemName() + " $" + foodItem.getRegularPrice();
            foodItemIds[i - 1] = String.valueOf(i);
        }
        for (int i = 1; i <= numberOfDrinkItems; i++) {
            int itemId = i + numberOfFoodItems;
            Item drinkItem = ItemFactory.createItem(itemId);
            drinkItemOptions[i - 1] = itemId + ". " + drinkItem.getItemName() + " $" + drinkItem.getRegularPrice();
            drinkItemIds[i - 1] = String.valueOf(itemId);
        }

        JComboBox<String> foodItemIdComboBox = new JComboBox<>(foodItemOptions);
        foodItemIdComboBox.setEditable(false);
        JComboBox<String> drinkItemIdComboBox = new JComboBox<>(drinkItemOptions);
        drinkItemIdComboBox.setEditable(false);
        JComboBox<String> thirdItemIdComboBox = new JComboBox<>(foodItemOptions);
        thirdItemIdComboBox.setEditable(false);

        Object[] message = {
            "Select Food Item ID:", foodItemIdComboBox,
            "Select Food Item ID:", thirdItemIdComboBox,
            "Select Drink Item ID:", drinkItemIdComboBox
        };

        int result = JOptionPane.showOptionDialog(frame, message, "Select Item IDs",
        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

        if (result == JOptionPane.OK_OPTION) {
            String foodItemIdStr = (String) foodItemIds[foodItemIdComboBox.getSelectedIndex()];
            String drinkItemIdStr = (String) drinkItemIds[drinkItemIdComboBox.getSelectedIndex()];
            String thirdItemIdStr = (String) foodItemIds[thirdItemIdComboBox.getSelectedIndex()];

            if (foodItemIdStr != null && !foodItemIdStr.isEmpty() &&
                drinkItemIdStr != null && !drinkItemIdStr.isEmpty() &&
                thirdItemIdStr != null && !thirdItemIdStr.isEmpty()) {
                    try {
                        int foodItemId = Integer.parseInt(foodItemIdStr);
                        int drinkItemId = Integer.parseInt(drinkItemIdStr);
                        int thirdItemId = Integer.parseInt(thirdItemIdStr);

                        Item foodItem = ItemFactory.createItem(foodItemId);
                        Item drinkItem = ItemFactory.createItem(drinkItemId);
                        Item thirdItem = ItemFactory.createItem(thirdItemId);

                        boolean sugar = false;
                        boolean milk = false;
                        boolean cold = false;
                        boolean vegan = false;

                        if (foodItemId != 0){
                            foodItem.setPrice(0.0);
                        }

                        if (thirdItemId != 0){
                            thirdItem.setPrice(0.0);
                        }
        
                        if (drinkItemId == 6){
                            drinkItem.setPrice(0.0);
                        }
                        
                        if (drinkItemId >= 7 && drinkItemId <= 9) {
                            if (drinkItemId == 7) {
                                int sugarOption = JOptionPane.showConfirmDialog(frame, "Do you want to add sugar to this drink?", "Add Sugar", JOptionPane.YES_NO_OPTION);
                                if (sugarOption == JOptionPane.YES_OPTION) {
                                    sugar = true;
                                }
                                int milkOption = JOptionPane.showConfirmDialog(frame, "Do you want to add milk to this drink?", "Add Milk", JOptionPane.YES_NO_OPTION);
                                if (milkOption == JOptionPane.YES_OPTION) {
                                    milk = true;
                                }
                                int coldOption = JOptionPane.showConfirmDialog(frame, "Do you want a cold version for this drink? (Additional $2.0)?", "Add Cold", JOptionPane.YES_NO_OPTION);
                                if (coldOption == JOptionPane.YES_OPTION) {
                                    cold = true;
                                    drinkItem.setPrice(2.0);
                                } else {
                                    drinkItem.setPrice(0.0);}
                            } else if (drinkItemId == 8) {
                                int sugarOption = JOptionPane.showConfirmDialog(frame, "Do you want to add sugar to this drink?", "Add Sugar", JOptionPane.YES_NO_OPTION);
                                if (sugarOption == JOptionPane.YES_OPTION) {
                                    sugar = true;
                                }
                                int milkOption = JOptionPane.showConfirmDialog(frame, "Do you want to add milk to this drink?", "Add Milk", JOptionPane.YES_NO_OPTION);
                                if (milkOption == JOptionPane.YES_OPTION) {
                                    milk = true;
                                }
                                int coldOption = JOptionPane.showConfirmDialog(frame, "Do you want a cold version for this drink? (Additional $3.0)?", "Add Cold", JOptionPane.YES_NO_OPTION);
                                if (coldOption == JOptionPane.YES_OPTION) {
                                    cold = true;
                                    drinkItem.setPrice(3.0);
                                } else {
                                    drinkItem.setPrice(0.0);}
                            } else {
                                int sugarOption = JOptionPane.showConfirmDialog(frame, "Do you want to add sugar to this drink?", "Add Sugar", JOptionPane.YES_NO_OPTION);
                                if (sugarOption == JOptionPane.YES_OPTION) {
                                    sugar = true;
                                }
                                int coldOption = JOptionPane.showConfirmDialog(frame, "Do you want a cold version for this drink? (Additional $2.0)?", "Add Cold", JOptionPane.YES_NO_OPTION);
                                if (coldOption== JOptionPane.YES_OPTION) {
                                    cold = true;
                                    drinkItem.setPrice(2.0);
                                } else {
                                    drinkItem.setPrice(0.0);}
                            }
                        }
                        
                        StringBuilder itemNameBuilder = new StringBuilder(drinkItem.getItemName());
                        if (sugar) { itemNameBuilder.append(" (+Sugar)"); }
                        if (milk) { itemNameBuilder.append(" (+Milk)"); }
                        if (cold) {
                            if (drinkItemId == 8) {
                                itemNameBuilder.append(" (+Cold +$3)");
                            } else {
                                itemNameBuilder.append(" (+Cold +$2)");
                            }
                        }
                        if (vegan) {itemNameBuilder.append(" (Vegan)"); }
                        drinkItem.setName(itemNameBuilder.toString());

                        boolean addedFoodItem = orderList.addItem(foodItem);
                        boolean addedThirdItem = orderList.addItem(thirdItem);
                        boolean addedDrinkItem = orderList.addItem(drinkItem);

                    if (addedFoodItem && addedDrinkItem && addedThirdItem) {
                        listModel.addElement(foodItem);
                        listModel.addElement(thirdItem);
                        listModel.addElement(drinkItem);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Maximum item limit reached.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    } finally {}
        
                }
            }
        }
    
    /**
     * Shows a dialog box to add a new item to the order list.
     * Updates the list model and the GUI with the new item.
     *
     * @param orderListJList the JList component displaying the order list
     */
    private void showAddItem(JList<Item> orderListJList) {
        int numberOfItems = 9;
        String[] itemOptions = new String[numberOfItems];
        String[] itemIds = new String[numberOfItems];
    
        for (int i = 1; i <= numberOfItems; i++) {
            Item item = ItemFactory.createItem(i);
            itemOptions[i - 1] = i + ". " + item.getItemName() + " $" + item.getRegularPrice();
            itemIds[i - 1] = String.valueOf(i);
        }
    
        JComboBox<String> itemIdComboBox = new JComboBox<>(itemOptions);
        itemIdComboBox.setEditable(false);
    
        int result = JOptionPane.showOptionDialog(frame, itemIdComboBox, "Select Item ID", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
        if (result == JOptionPane.OK_OPTION) {
            String itemIdStr = (String) itemIds[itemIdComboBox.getSelectedIndex()];
            if (itemIdStr != null && !itemIdStr.isEmpty()) {
                try {
                    int itemId = Integer.parseInt(itemIdStr);
                    if (itemId >= 1 && itemId <= 9) {
                        Item item = ItemFactory.createItem(itemId);
                        boolean sugar = false;
                        boolean milk = false;
                        boolean cold = false;
                        boolean vegan = false;
                        if (itemId >= 7 && itemId <= 9) {
                            if (itemId == 7) {
                                int sugar_option = JOptionPane.showConfirmDialog(frame, "Do you want to add sugar to this drink?", "Add Sugar", JOptionPane.YES_NO_OPTION);
                                if (sugar_option == JOptionPane.YES_OPTION) {
                                    sugar = true;
                                }
                                int milk_option = JOptionPane.showConfirmDialog(frame, "Do you want add milk this drink?", "Add Milk", JOptionPane.YES_NO_OPTION);
                                if (milk_option == JOptionPane.YES_OPTION) {
                                    milk = true;
                                }
                                int cold_option = JOptionPane.showConfirmDialog(frame, "Do you want a cold version for this drink? (Additional $2.0)?", "Add Cold", JOptionPane.YES_NO_OPTION);
                                if (cold_option == JOptionPane.YES_OPTION) {
                                    cold = true;
                                    item.setPrice(item.getRegularPrice() + 2.0);
                                }
                            } else if (itemId == 8) {
                                int suger_option = JOptionPane.showConfirmDialog(frame, "Do you want add suger this drink?", "Add Suger", JOptionPane.YES_NO_OPTION);
                                if (suger_option == JOptionPane.YES_OPTION) {
                                    sugar = true;
                                }
                                int milk_option = JOptionPane.showConfirmDialog(frame, "Do you want add milk this drink?", "Add Milk", JOptionPane.YES_NO_OPTION);
                                if (milk_option == JOptionPane.YES_OPTION) {
                                    milk = true;
                                }
                                int cold_option = JOptionPane.showConfirmDialog(frame, "Do you want a cold version for this drink? (Additional $3.0)?", "Add Cold", JOptionPane.YES_NO_OPTION);
                                if (cold_option == JOptionPane.YES_OPTION) {
                                    cold = true;
                                    item.setPrice(item.getRegularPrice() + 3.0);
                                }
                            } else {
                                int suger_option = JOptionPane.showConfirmDialog(frame, "Do you want add suger this drink?", "Add Suger", JOptionPane.YES_NO_OPTION);
                                if (suger_option == JOptionPane.YES_OPTION) {
                                    sugar = true;
                                }
                                int cold_option = JOptionPane.showConfirmDialog(frame, "Do you want a cold version for this drink? (Additional $2.0)?", "Add Cold", JOptionPane.YES_NO_OPTION);
                                if (cold_option == JOptionPane.YES_OPTION) {
                                    cold = true;
                                    item.setPrice(item.getRegularPrice() + 2.0);
                                }
                        }
                    }
                    if (itemId == 3 || itemId == 5){
                        vegan = true;
                    }
                    StringBuilder itemNameBuilder = new StringBuilder(item.getItemName());
                    if (sugar) {itemNameBuilder.append(" (+Sugar)");}
                    if (milk) {itemNameBuilder.append(" (+Milk)");}
                    if (cold) {if (itemId == 8) {itemNameBuilder.append(" (+Cold +$3)");} else {itemNameBuilder.append(" (+Cold +$2)");}}
                    if (vegan) {itemNameBuilder.append(" (Vegan)");}
                    item.setName(itemNameBuilder.toString());

                    boolean added = orderList.addItem(item);
                    if (added) {
                        listModel.addElement(item);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Maximum item limit reached.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid Item ID. Please enter a valid ID.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Invalid Item ID. Please enter a valid ID.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

    /**
     * Removes the selected item from the order list.
     * Updates the list model and the GUI.
     *
     * @param orderListJList the JList component displaying the order list
     */
    private void removeSelectedItem(JList<Item> orderListJList) {
        int selectedIndex = orderListJList.getSelectedIndex();
        if (selectedIndex >= 0) {
            listModel.remove(selectedIndex);
            orderList.removeItem(selectedIndex);
        } else {
            JOptionPane.showMessageDialog(frame, "No item selected.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Calculates and displays the total price of all items in the order list.
     */
    private void showTotalPrice() {
        double totalPrice = orderList.getTotalPrice();
        JOptionPane.showMessageDialog(frame, "The total price of the order is: $" + String.format("%.2f", totalPrice),
                "Total Price", JOptionPane.INFORMATION_MESSAGE);
    }
}