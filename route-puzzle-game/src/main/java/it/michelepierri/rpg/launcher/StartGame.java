package it.michelepierri.rpg.launcher;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.michelepierri.rpg.dto.Item;
import it.michelepierri.rpg.dto.JsonItem;
import it.michelepierri.rpg.dto.Room;
import it.michelepierri.rpg.game.RoutePuzzleGame;
import it.michelepierri.rpg.input.CmdLineInterface;
import it.michelepierri.rpg.input.JsonMapLoader;

class StartGame {

	static Logger log = LoggerFactory.getLogger(RoutePuzzleGame.class);

	public static void main(String[] args) throws IOException {
        CmdLineInterface cli = CmdLineInterface.parseInput(args);
        if (cli == null) {
            System.exit(1);
        }

        JsonMapLoader mapLoader = new JsonMapLoader();
        Map<Integer, ? extends Room> mazeMap = mapLoader.loadFrom(cli.getMapFile());

        Integer startRoomId = cli.getRoomId();

        Set<Item> itemsToCollect = Arrays.asList(cli.getItems()).stream()
                .map(JsonItem::new)
                .collect(Collectors.toSet());

        RoutePuzzleGame game = new RoutePuzzleGame(mazeMap, startRoomId, itemsToCollect);
//        game.run().forEach(move -> log.info(move.toString()));
        game.run().forEach(move -> System.out.println(move.toString()));

	}
}
