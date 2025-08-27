import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Inventory {
    private final List<Item> items;
    private final int capacity;

    /** ... */
    public Inventory(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("La capacità deve essere positiva.");
        }
        this.capacity = capacity;
        this.items = new ArrayList<>();
    }

    /** ... */
    public boolean addItem(Item item) {
        if (items.size() >= capacity) {
            return false;
        }
        return items.add(item);
    }

    /** ... */
    public boolean removeItem(String itemName) {
        return items.removeIf(item -> item.getName().equalsIgnoreCase(itemName));
    }
    
    /** ... */
    public Optional<Item> findItem(String itemName) {
        return items.stream()
                .filter(item -> item.getName().equalsIgnoreCase(itemName))
                .findFirst();
    }
    
    /** ... */
    public int getItemCount() {
        return items.size();
    }
    
    /** ... */
    public List<Item> getAllItems() {
        return new ArrayList<>(items); // Ritorna una copia per proteggere la lista originale
    }
    
    /** ... */
    public boolean isFull() {
        return items.size() >= capacity;
    }
}