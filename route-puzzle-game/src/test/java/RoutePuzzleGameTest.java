
import static java.util.Collections.singleton;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Rule;
import org.junit.Test;

import it.michelepierri.rpg.dto.Item;
import it.michelepierri.rpg.dto.Room;
import it.michelepierri.rpg.game.RoutePuzzleGame;

public class RoutePuzzleGameTest {

	@Rule
	public final JUnitSoftAssertions softly = new JUnitSoftAssertions();

	@Test
	public void emptySolution() {
		Item item = mock(Item.class);
		Set<Item> itemsToCollect = singleton(item);
		Integer ind = 1;
		Room r = mock(Room.class);

		Map<Integer, Room> map = new HashMap<Integer, Room>();
		map.put(ind, r);
		RoutePuzzleGame player1 = new RoutePuzzleGame(map, ind, itemsToCollect);

		assertThat(player1.run()).isEmpty();
	}

	@Test(expected=NullPointerException.class)
	public void nullInput() {
		RoutePuzzleGame player1 = new RoutePuzzleGame(null, null, null);
		player1.run();
	}
//    
//    @Test
//    public void routeWithBackTracking() {
//        Item item1 = mock(Item.class);
//        Item item2 = mock(Item.class);
//        Room startingRoom = mockRoom();
//        Room neighbor1 = mockRoom(item1);
//        Room neighbor2 = mockRoom(item2);
//        connect(startingRoom, neighbor1, neighbor2);
//        connect(neighbor1, startingRoom);
//        connect(neighbor2, startingRoom);
//        Set<Item> itemsToCollect = setOf(item1, item2);
//
//        RoutePuzzleGame player1 = new RoutePuzzleGame(,null,null);
//        
//        List<Move> route = player1.findRoute(startingRoom, itemsToCollect);
//
//        assertThat(route)
//                .hasSize(4)
//                .containsSequence(
//                        new Move(startingRoom, emptySet()),
//                        new Move(neighbor1, singleton(item1))
//                ).containsSequence(
//                        new Move(startingRoom, emptySet()),
//                        new Move(neighbor2, singleton(item2))
//                );
//    }
//
//    private void connect(Room room, Room... neighbors) {
//        when(room.getNeighbors()).thenReturn(setOf(neighbors));
//    }
//
//    private Room mockRoom(Item... items) {
//        Room room = mock(Room.class);
//        when(room.getItems()).thenReturn(setOf(items));
//        return room;
//    }
//
//    @SuppressWarnings("unchecked")
//    private static <T> Set<T> setOf(T... elements) {
//        return Stream.of(elements).collect(toSet());
//    }

}
