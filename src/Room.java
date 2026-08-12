class Room {
    private String name;
    private String description;
    private Puzzle puzzle;
    private Room nextRoom;
    private Item rewardItem;

    public Room(String name, String description, Puzzle puzzle, Item rewardItem) {
        this.name = name;
        this.description = description;
        this.puzzle = puzzle;
        this.rewardItem = rewardItem;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Puzzle getPuzzle() {
        return puzzle;
    }

    public Room getNextRoom() {
        return nextRoom;
    }

    public void setNextRoom(Room nextRoom) {
        this.nextRoom = nextRoom;
    }

    public Item getRewardItem() {
        return rewardItem;
    }
}