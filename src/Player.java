import java.util.ArrayList;

class Player {
    private String name;
    private ArrayList<Item> inventory;
    private Room currentRoom;

    public Player(String name, Room startingRoom) {
        this.name = name;
        this.inventory = new ArrayList<>();
        this.currentRoom = startingRoom;
    }

    public void addItem(Item item) {
        inventory.add(item);
        System.out.println(">> " + item.getName() + " added to inventory.");
    }

    public boolean hasItem(String itemName) {
        for (Item i : inventory) {
            if (i.getName().equalsIgnoreCase(itemName)) {
                return true;
            }
        }
        return false;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }

    public String getName() {
        return name;
    }

    public void showInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory: (empty)");
            return;
        }
        System.out.print("Inventory: ");
        for (Item i : inventory) {
            System.out.print(i.getName() + " ");
        }
        System.out.println();
    }
}