package it.michelepierri.rpg.input;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.michelepierri.rpg.dto.JsonRoom;
import it.michelepierri.rpg.dto.Room;
import it.michelepierri.rpg.dto.RoomsMap;

public class JsonMapLoader {

    private final ObjectMapper jackson;

    public JsonMapLoader() {
        jackson = new ObjectMapper();
    }

    public Map<Integer, ? extends Room> loadFrom(File f) throws IOException {
        RoomsMap jsonMap = jackson.readValue(f, RoomsMap.class);
        Map<Integer, JsonRoom> roomMap = mapRooms(jsonMap);
        loadNeighborRooms(roomMap);
        return roomMap;
    }

    private void loadNeighborRooms(Map<Integer, JsonRoom> roomMap) {
        roomMap.values().stream().forEach(r -> {
            r.getNeighbors(roomMap);
        });
    }

    private static Map<Integer, JsonRoom> mapRooms(RoomsMap jsonMap) {
        return jsonMap.getRooms().stream()
                .collect(toMap(
                        JsonRoom::getId,
                        identity()
                ));
    }

}
