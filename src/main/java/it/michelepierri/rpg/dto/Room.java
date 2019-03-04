package it.michelepierri.rpg.dto;

import java.util.Set;

public interface Room {

    Integer getId();
    
    String getName();
    
    Set<Item> getItems();

    Set<Room> getNeighbors();

}
