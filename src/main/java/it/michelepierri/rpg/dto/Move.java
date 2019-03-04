package it.michelepierri.rpg.dto;

import java.util.Objects;
import java.util.Set;

public class Move {

    private final Room room;
    private final Set<Item> collectedItems;

    public Move(Room room, Set<Item> collectedItems) {
        Objects.requireNonNull(room);
        Objects.requireNonNull(collectedItems);
        this.room = room;
        this.collectedItems = collectedItems;
    }

    public Room getRoom() {
        return room;
    }

    public Set<Item> getCollectedItems() {
        return collectedItems;
    }

    @Override
    public String toString() {
        return "Move{" + "room=" + room + ", collectedItems=" + collectedItems + '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(room, collectedItems);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof Move) {
            Move m = (Move) o;
            return Objects.equals(room, m.room)
                    && Objects.equals(collectedItems, m.collectedItems);
        }
        return false;
    }

}
