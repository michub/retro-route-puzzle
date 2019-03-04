package it.michelepierri.rpg.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

public class RoomsMap {

    private final List<JsonRoom> rooms;

    @JsonCreator
    RoomsMap(@JsonProperty("rooms") List<JsonRoom> rooms) {
        this.rooms = Objects.requireNonNull(rooms);
    }

    public List<JsonRoom> getRooms() {
        return rooms;
    }

}
