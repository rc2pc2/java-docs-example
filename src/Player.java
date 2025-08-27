public class Player {
    private String username;
    private int health;
    private int level;
    private long experience;

    /** ... */
    public Player(String username) {
        this.username = username;
        this.health = 100;
        this.level = 1;
        this.experience = 0;
    }

    /** ... */
    public void takeDamage(int damageAmount) {
        if (damageAmount < 0) {
            return; // Non si può subire danno negativo
        }
        this.health -= damageAmount;
        if (this.health < 0) {
            this.health = 0;
        }
    }

    /** ... */
    public void heal(int healAmount) {
        if (healAmount < 0) {
            return; // Non si può curare con un valore negativo
        }
        this.health += healAmount;
        if (this.health > 100) {
            this.health = 100;
        }
    }

    /** ... */
    public void gainExperience(long expPoints) {
        this.experience += expPoints;
        checkLevelUp();
    }
    
    /** ... */
    private void checkLevelUp() {
        long requiredExp = this.level * 1000L;
        if (this.experience >= requiredExp) {
            this.level++;
            this.experience -= requiredExp;
            System.out.println(this.username + " è salito al livello " + this.level + "!");
        }
    }

    /** ... */
    public boolean isAlive() {
        return this.health > 0;
    }

    /** ... */
    public String getStatus() {
        return String.format("Player: %s | Livello: %d | HP: %d/100 | EXP: %d",
                this.username, this.level, this.health, this.experience);
    }
}