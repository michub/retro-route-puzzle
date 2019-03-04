

import static java.util.Collections.singleton;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import it.michelepierri.rpg.dto.Item;
import it.michelepierri.rpg.dto.Move;
import it.michelepierri.rpg.dto.Room;
import it.michelepierri.rpg.game.PuzzleGameHelper;

@RunWith(MockitoJUnitRunner.class)
public class PuzzleGameHelperTest {


	@Test
	public void testFindNextMoves() {
		PuzzleGameHelper hlp = new PuzzleGameHelper();
		Set<Item> itemsToCollect = singleton(mock(Item.class));
		Deque<Room> roomToVisit = new ArrayDeque<>();
		Room room = mock(Room.class);
		roomToVisit.push(room);
        
		LinkedList<Move> result = hlp.findNextMoves(roomToVisit, itemsToCollect);
		assertEquals(0, result.size());
	}
	
	@Test(expected=NullPointerException.class)
    public void testNullHelper() {
		PuzzleGameHelper hlp = new PuzzleGameHelper();
		hlp.findNextMoves(null, null);
	}

	@Test(expected=NullPointerException.class)
	public void testNullJoin() {
		PuzzleGameHelper hlp = new PuzzleGameHelper();
		hlp.join(null, null);
	}
	
	@Test
	public void testJoin() {
		PuzzleGameHelper hlp = new PuzzleGameHelper();
		
		LinkedList<Move> left = new LinkedList<>();
		LinkedList<Move> right = new LinkedList<>();
		
		hlp.join(left, right);
		assertEquals(0, left.size());
	}


}
