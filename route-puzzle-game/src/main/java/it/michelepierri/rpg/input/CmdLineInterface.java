package it.michelepierri.rpg.input;

import java.io.File;
import java.util.Optional;
import java.util.stream.Stream;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.michelepierri.rpg.game.RoutePuzzleGame;

public class CmdLineInterface {

	static Logger log = LoggerFactory.getLogger(CmdLineInterface.class);

	private final File mapFile;
	private final Integer roomId;
	private final String[] items;

	private static final Option MAP = Option.builder("m").longOpt("map").argName("mapFile")
			.desc("File containing the rooms map in JSON format").hasArg().type(File.class).required().build();

	private static final Option ROOM = Option.builder("r").longOpt("room").argName("roomId")
			.desc("ID of the room to start searching from").hasArg().type(Number.class).required().build();

	private static final Option ITEMS = Option.builder("i").longOpt("items").argName("itemsToCollect")
			.desc("List of items to search for").numberOfArgs(Option.UNLIMITED_VALUES).build();

	private CmdLineInterface(Options options, String[] args) throws ParseException {
		Stream.of(MAP, ROOM, ITEMS).forEach(options::addOption);
		CommandLineParser parser = new DefaultParser();
		CommandLine cli = parser.parse(options, args);
		mapFile = (File) cli.getParsedOptionValue(MAP.getOpt());
		roomId = ((Number) cli.getParsedOptionValue(ROOM.getOpt())).intValue();
		items = Optional.ofNullable(cli.getOptionValues(ITEMS.getOpt())).orElse(new String[0]);
	}

	public static CmdLineInterface parseInput(String[] args) {
		Options options = new Options();
		try {
			return new CmdLineInterface(options, args);
		} catch (ParseException ex) {
			log.error("Can't parse arguments. %s%n", ex.getMessage());
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp(RoutePuzzleGame.class.getName(), options, true);
			return null;
		}
	}

	public File getMapFile() {
		return mapFile;
	}

	public Integer getRoomId() {
		return roomId;
	}

	public String[] getItems() {
		return items;
	}

}
