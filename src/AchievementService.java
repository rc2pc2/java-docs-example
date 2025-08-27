import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AchievementService {
    private final Map<String, String> achievements; 
    private final Map<String, Boolean> unlockedAchievements;

    /** ... */
    public AchievementService() {
        this.achievements = new HashMap<>();
        this.unlockedAchievements = new HashMap<>();
        loadDefaultAchievements();
    }
    
    /** ... */
    private void loadDefaultAchievements() {
        achievements.put("FIRST_STEP", "Inizia la tua avventura.");
        achievements.put("MONSTER_SLAYER", "Sconfiggi il tuo primo nemico.");
        achievements.put("TREASURE_HUNTER", "Trova un oggetto raro.");
    }
    
    /** ... */
    public boolean unlockAchievement(String name) {
        if (!achievements.containsKey(name) || isUnlocked(name)) {
            return false;
        }
        unlockedAchievements.put(name, true);
        System.out.println("Achievement sbloccato: " + achievements.get(name));
        return true;
    }
    
    /** ... */
    public boolean isUnlocked(String name) {
        return unlockedAchievements.getOrDefault(name, false);
    }
    
    /** ... */
    public String getAchievementDescription(String name) {
        return achievements.get(name);
    }

    /** ... */
    public int getTotalUnlockedCount() {
        return unlockedAchievements.size();
    }
    
    /** ... */
    public Set<String> getAllAchievementNames() {
        return achievements.keySet();
    }
}