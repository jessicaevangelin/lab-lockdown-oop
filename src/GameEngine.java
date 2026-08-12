import java.util.Scanner;

public class GameEngine {
    private Player player;
    private Scanner scanner;
    private boolean gameOver;
    private boolean playerWon;

    public GameEngine(Player player, Scanner scanner) {
        this.player = player;
        this.scanner = scanner;
        this.gameOver = false;
        this.playerWon = false;
}

    public void start() {
        System.out.println("=== LAB LOCKDOWN ===");
        System.out.println("Security breach detected. All exits sealed.");
        System.out.println("Solve each room's puzzle to proceed.\n");

        while (!gameOver) {
            Room current = player.getCurrentRoom();
            Puzzle puzzle = current.getPuzzle();

            System.out.println("\n--- " + current.getName() + " ---");
            System.out.println(current.getDescription());
            player.showInventory();

            // Check item requirement for CodeLockPuzzle or SequencePuzzle
            if (puzzle instanceof CodeLockPuzzle) {
                CodeLockPuzzle codeLock = (CodeLockPuzzle) puzzle;
                if (!player.hasItem(codeLock.getRequiredItemName())) {
                    System.out.println("This keypad needs: " + codeLock.getRequiredItemName()
                            + ". You don't have it yet — explore other rooms first if possible.");
                }
            } else if (puzzle instanceof SequencePuzzle) {
                SequencePuzzle seq = (SequencePuzzle) puzzle;
                if (!player.hasItem(seq.getRequiredItemName())) {
                    System.out.println("This panel needs: " + seq.getRequiredItemName()
                            + ". You don't have it yet — explore other rooms first if possible.");
                }
            }

            System.out.println("Hint: " + puzzle.getHint());
            System.out.println("Attempts remaining: " + puzzle.getAttemptsRemaining());
            System.out.print("Your answer: ");
            String input = scanner.nextLine();

            boolean correct = puzzle.attempt(input);

            if (correct) {
                System.out.println(">> Correct! Puzzle solved.");

                if (current.getRewardItem() != null) {
                    player.addItem(current.getRewardItem());
                }

                if (current.getNextRoom() == null) {
                    playerWon = true;
                    gameOver = true;
                } else {
                    player.setCurrentRoom(current.getNextRoom());
                }
            } else if (puzzle.attemptsExhausted()) {
                System.out.println(">> Incorrect. No attempts left.");
                gameOver = true;
            } else {
                System.out.println(">> Incorrect. Try again.");
            }
        }

        if (playerWon) {
            System.out.println("\n=== LOCKDOWN LIFTED ===");
            System.out.println(player.getName() + " escaped the lab. You win!");
        } else {
            System.out.println("\n=== LOCKDOWN ALARM TRIGGERED ===");
            System.out.println("Security has locked you in permanently. Game Over.");
        }

        scanner.close();
    }

    public static void main(String[] args) {
        // Room 5: Control Room (final)
        CodeLockPuzzle controlPuzzle = new CodeLockPuzzle(
                "The access card has a 4-digit code printed on the back.",
                3, "4821", "access card"
        );
        Room controlRoom = new Room(
                "Control Room",
                "The main override panel. Enter the code from your access card to unlock the exit.",
                controlPuzzle, null
        );

        // Room 4: Security Office
        RiddlePuzzle securityRiddle = new RiddlePuzzle(
                "I have keys but open no locks. I have space but no room. You can enter, but not go inside. What am I?",
                3, "keyboard"
        );
        Item accessCard = new Item("access card", "A card with a 4-digit code printed on the back.");
        Room securityOffice = new Room(
                "Security Office",
                "Monitors flicker across the wall. A locked drawer might hold something useful.",
                securityRiddle, accessCard
        );

        // Room 3: Chemical Storage
        SequencePuzzle chemSequence = new SequencePuzzle(
                "The toolkit's manual lists steps: Valve, Switch, Pump. Enter in that order, comma-separated.",
                3, new String[]{"Valve", "Switch", "Pump"}, "toolkit"
        );
        Room chemStorage = new Room(
                "Chemical Storage",
                "Pipes hiss softly. A control panel requires the correct activation sequence.",
                chemSequence, null
        );

        // Room 2: Server Room
        CodeLockPuzzle serverLock = new CodeLockPuzzle(
                "The keycard has a 3-digit code etched on its edge.",
                3, "507", "keycard"
        );
        Item toolkit = new Item("toolkit", "A small toolkit, useful for machinery.");
        Room serverRoom = new Room(
                "Server Room",
                "Rows of blinking servers. A keypad-locked panel blocks the way forward.",
                serverLock, toolkit
        );

        // Room 1: Entry Lab
        RiddlePuzzle entryRiddle = new RiddlePuzzle(
                "I unlock doors but I'm not a key. Swipe me and see. What am I?",
                3, "keycard"
        );
        Item keycard = new Item("keycard", "A card with a 3-digit code etched on the edge.");
        Room entryLab = new Room(
                "Entry Lab",
                "Emergency lights pulse red. A note on the wall hints at how to proceed.",
                entryRiddle, keycard
        );

        // Link rooms
        entryLab.setNextRoom(serverRoom);
        serverRoom.setNextRoom(chemStorage);
        chemStorage.setNextRoom(securityOffice);
        securityOffice.setNextRoom(controlRoom);
        controlRoom.setNextRoom(null);

        // Start game
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your researcher name: ");
        String playerName = scanner.nextLine();

        Player player = new Player(playerName, entryLab);
        GameEngine engine = new GameEngine(player, scanner);
        engine.start();
    }
}