package it.michelepierri.rpg.dto;

import static java.util.stream.Collectors.toSet;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import it.michelepierri.rpg.exception.GameException;

public class JsonRoom implements Room {

    private final Integer id;
    private final String name;
    private final Integer north;
    private final Integer south;
    private final Integer west;
    private final Integer east;
    private final Set<Room> neighbors;
    private final Set<Item> items;

    @JsonCreator
    JsonRoom(
            @JsonProperty(value = "id") Integer id,
            @JsonProperty(value = "name") String name,
            @JsonProperty(value = "north") Integer north,
            @JsonProperty(value = "south") Integer south,
            @JsonProperty(value = "west") Integer west,
            @JsonProperty(value = "east") Integer east,
            @JsonProperty(value = "objects") List<JsonItem> objects
    ) {
        this.id = Objects.requireNonNull(id);
        this.name = Objects.requireNonNull(name);
        this.north = north;
        this.south = south;
        this.west = west;
        this.east = east;
        items = Objects.requireNonNull(objects).stream().collect(toSet());
        neighbors = new HashSet<>();
    }

    public void getNeighbors(Map<Integer, JsonRoom> roomMap) {
    	Stream.of(north, south, east, west)
    	.filter(Objects::nonNull)
    	.forEach(roomId -> {
    		JsonRoom room = roomMap.get(roomId);
    		if (room == null) {
    			throw new GameException("Room " + roomId + " not found");
    		}
    		neighbors.add(room);
    	});
    }
    
    @Override
    public String getName() {
        return name;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public Set<Item> getItems() {
        return items;
    }

    @Override
    public Set<Room> getNeighbors() {
        return neighbors;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof JsonRoom) {
            JsonRoom r = (JsonRoom) o;
            return Objects.equals(id, r.id);
        }
        return false;
    }

    @Override
    public String toString() {
        return name;
    }

}
