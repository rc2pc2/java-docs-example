
public class GameEngine {
    private final Player player;
    private final Inventory inventory;
    private final AchievementService achievementService; // <-- INJECTED DEPENDENCY

    /** ... */
    public GameEngine(Player player, Inventory inventory, AchievementService achievementService) {
        this.player = player;
        this.inventory = inventory;
        this.achievementService = achievementService; 
    }
    
    /** ... */
    public void startGame() {
        System.out.println("Il gioco è iniziato per " + player.getStatus());
        this.achievementService.unlockAchievement("FIRST_STEP");
    }

    /** ... */
    public void playerDefeatsEnemy() {
        System.out.println("Il giocatore ha sconfitto un nemico!");
        player.gainExperience(150);
        this.achievementService.unlockAchievement("MONSTER_SLAYER");
    }
    
    /** ... */
    public void playerFindsItem(Item item) {
        boolean added = inventory.addItem(item);
        if(added) {
            System.out.println(player.getStatus() + " ha trovato: " + item.getName());
            if (item.getName().equalsIgnoreCase("Gemma Rara")) {
                this.achievementService.unlockAchievement("TREASURE_HUNTER");
            }
        } else {
            System.out.println("Inventario pieno! Impossibile raccogliere " + item.getName());
        }
    }
    
    /** ... */
    public void simulateBattle(int incomingDamage) throws IllegalStateException {
        if (!player.isAlive()) {
            throw new IllegalStateException("Il giocatore è già sconfitto e non può combattere.");
        }
        player.takeDamage(incomingDamage);
        System.out.println("Il giocatore subisce " + incomingDamage + " danni.");
    }
    
    /** ... */
    public void endTurn() {
        System.out.println("--- Fine Turno ---");
        System.out.println(player.getStatus());
        System.out.println("Achievement sbloccati: " + achievementService.getTotalUnlockedCount());
        System.out.println("-----------------");
    }

    public static void main(String[] args) {
        Player mainPlayer = new Player("Aragorn");
        Inventory playerInventory = new Inventory(10);
        AchievementService achievements = new AchievementService();

        GameEngine engine = new GameEngine(mainPlayer, playerInventory, achievements);
        
        engine.startGame();
        engine.playerFindsItem(new Item("Spada di Legno", 5));
        engine.endTurn();
        
        engine.playerDefeatsEnemy();
        engine.endTurn();

        engine.playerFindsItem(new Item("Gemma Rara", 1));
        engine.endTurn();
    }
}