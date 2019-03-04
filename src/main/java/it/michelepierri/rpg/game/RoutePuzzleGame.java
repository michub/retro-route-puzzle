package it.michelepierri.rpg.game;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptySet;
import static java.util.Collections.singletonList;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import it.michelepierri.rpg.dto.Item;
import it.michelepierri.rpg.dto.Move;
import it.michelepierri.rpg.dto.Room;
import it.michelepierri.rpg.exception.GameException;

public class RoutePuzzleGame {

	Map<Integer, ? extends Room> roomMap;
	Integer startRoomId;
	Set<Item> itemsToCollect;
	
	private PuzzleGameHelper helper = new PuzzleGameHelper();
	
	public RoutePuzzleGame(Map<Integer, ? extends Room> map, Integer startRoomId, Set<Item> itemsToCollect) {
		this.roomMap = map;
		this.startRoomId = startRoomId;
		this.itemsToCollect = itemsToCollect;
	}

	public List<Move> run() {

        Room startRoom = roomMap.get(startRoomId);
        if(startRoom==null)
        	throw new GameException("Room " + startRoomId + " not exist");

        return findRoute(startRoom);
    }
	
    public Map<Integer, ? extends Room> getRoomMap() {
		return roomMap;
	}

	public void setRoomMap(Map<Integer, ? extends Room> roomMap) {
		this.roomMap = roomMap;
	}

	public Integer getStartRoomId() {
		return startRoomId;
	}

	public void setStartRoomId(Integer startRoomId) {
		this.startRoomId = startRoomId;
	}

	public Set<Item> getItemsToCollect() {
		return itemsToCollect;
	}

	public void setItemsToCollect(Set<Item> itemsToCollect) {
		this.itemsToCollect = itemsToCollect;
	}

    private List<Move> findRoute(Room startRoom) {
        Objects.requireNonNull(startRoom);

        if (itemsToCollect.isEmpty()) {
            return singletonList(new Move(startRoom, emptySet()));
        }

        HashSet<Item> remainingItems = new HashSet<>(itemsToCollect);
        LinkedList<Move> route = new LinkedList<>();
        LinkedList<Move> routeToNextItems;
        
        do {
            routeToNextItems = findRouteToNextItems(startRoom, remainingItems);
            helper.join(route, routeToNextItems);
            startRoom = route.isEmpty() ? startRoom : route.getLast().getRoom();
        } while (!remainingItems.isEmpty() && !routeToNextItems.isEmpty());

        return remainingItems.isEmpty() ? route : emptyList();
    }
    
	private LinkedList<Move> findRouteToNextItems(Room startRoom, Set<Item> itemsToCollect) {
        Deque<Room> roomsToVisit = new ArrayDeque<>();
        roomsToVisit.offerFirst(startRoom);
        return helper.findNextMoves(roomsToVisit, itemsToCollect);
    }

}
