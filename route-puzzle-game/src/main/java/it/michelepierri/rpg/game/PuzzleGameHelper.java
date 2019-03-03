package it.michelepierri.rpg.game;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import it.michelepierri.rpg.dto.Item;
import it.michelepierri.rpg.dto.Move;
import it.michelepierri.rpg.dto.Room;

public class PuzzleGameHelper {

    public LinkedList<Move> findNextMoves(Deque<Room> roomsToVisit, Set<Item> itemsToCollect) {
        LinkedList<Move> route = new LinkedList<>();
        Set<Room> visitedRooms = new HashSet<>();
        do {
            Room room = roomsToVisit.pollFirst();
            visitedRooms.add(room);
            Set<Item> collectedItems = new HashSet<>(room.getItems());
            collectedItems.retainAll(itemsToCollect);
            route.addLast(new Move(room, collectedItems));
            if (itemsToCollect.removeAll(collectedItems)) {
                return route;
            } else {
                HashSet<Room> neighbors = new HashSet<>(room.getNeighbors());
                neighbors.removeAll(visitedRooms);
                if (neighbors.isEmpty()) {
                    route.removeLast();
                } else {
                	roomsToVisit.addAll(neighbors);
                }
            }
        } while (!roomsToVisit.isEmpty());

        return route;
    }
    

    public void join(LinkedList<Move> left, LinkedList<Move> right) {
        if (left.isEmpty()) {
            left.addAll(right);
        } else if (!right.isEmpty()) {
            if (left.getLast().getRoom().equals(right.getFirst().getRoom())) {
                right.removeFirst();
            }
            left.addAll(right);
        }
    }
    
}
