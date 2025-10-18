package com.nasir.cricket.model;
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Represents a cricket team with players, total runs, wickets, and balls faced.
 */
public class Team {
	private final String name;
	private int runs;
	private int wickets;
	private int ballsPlayed;
	private int strikerIndex;
	private int nonStrikerIndex;
	private final List<Player> players;

	/**
	 * Create a team with a given name and list of player names.
	 * This constructor is typically used when player names are loaded dynamically.
	 *
	 * @param name         the team name (e.g., "RCB")
	 */
	public Team(String name) {
		this.name = name;
		this.runs = 0;
		this.wickets = 0;
		this.ballsPlayed = 0;
		this.strikerIndex = 0;
		this.nonStrikerIndex = 1;
		this.players = loadPlayersFromFile(name);
	}

	// Basic Getters
	public String getName() { return name; }
	public int getRuns() { return runs; }
	public int getWickets() { return wickets; }
	public int getBallsPlayed() { return ballsPlayed; }
	public List<Player> getPlayers() { return players; }

	// Game Operations
	public void addRuns(int r) { runs += r; }
	public void addWicket() { wickets++; }
	public void addBall() { ballsPlayed++; }

	public Player getPlayer(int index) {
		return players.get(index);
	}

	/**
	 * Resets all team stats (useful before starting a new innings or match).
	 */
	public void resetStats() {
		runs = 0;
		wickets = 0;
		ballsPlayed = 0;
		for (Player p : players) {
			p.reset();
		}
	}

	public Player getStriker() {
		return players.get(strikerIndex);
	}

	public int getStrikerIndex() {
		return strikerIndex;
	}

	public Player getNonStriker() {
		return players.get(nonStrikerIndex);
	}

	public void switchStrike() {
		int temp = strikerIndex;
		strikerIndex = nonStrikerIndex;
		nonStrikerIndex = temp;
	}

	public void newBatsman() {
		strikerIndex = Math.max(strikerIndex, nonStrikerIndex) + 1;
	}

	// Helper to load players dynamically
	private List<Player> loadPlayersFromFile(String teamName) {
		List<Player> list = new ArrayList<>();
		Path filePath = Path.of("src/com/nasir/cricket/data", teamName + ".txt");

		try (BufferedReader reader = Files.newBufferedReader(filePath)) {
			String line;
			while ((line = reader.readLine()) != null) {
				list.add(new Player(line.trim()));
			}
		} catch (IOException e) {
			System.out.println("Error loading players for team: " + teamName);
			e.printStackTrace();
		}
		return list;
	}
}
