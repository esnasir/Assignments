package com.nasir.cricket.model;
/**
 * Represents a batsman in the match.
 * Tracks runs scored, balls faced and whether the player is out.
 */
public class Player {
	private final String name;
	private int runs;
	private int ballsFaced;
	private boolean isOut;

	/**
	 * Create a player with the given name.
	 *
	 * @param name player's name (e.g. "Virat Kohli")
	 */
	public Player(String name) {
		this.name = name;
		this.runs = 0;
		this.ballsFaced = 0;
		this.isOut = false;
	}

	// Basic getters
	public String getName() { return name; }
	public int getRuns() { return runs; }
	public int getBallsFaced() { return ballsFaced; }
	public boolean isOut() { return isOut; }


	// Mutators / convenience methods
	/**
	 * Add runs to this player's score. This method does NOT increment balls faced.
	 * Use {@link #addRunsAndBall(int)} when the run comes from a counted delivery.
	 *
	 * @param r runs to add (0..n)
	 */
	public void addRuns(int r) {
		if (r < 0) throw new IllegalArgumentException("Runs cannot be negative");
		this.runs += r;
	}

	/**
	 * Add runs and also increment balls faced (for a legal delivery where the batsman scored).
	 *
	 * @param r runs scored off the ball (1..6 typically)
	 */
	public void addRunsAndBall(int r) {
		addRuns(r);
		addBall();
	}

	/**
	 * Increment balls faced (for dot balls, wickets, or any legal delivery).
	 */
	public void addBall() {
		this.ballsFaced++;
	}

	/**
	 * Mark this player as out.
	 */
	public void setOut() {
		this.isOut = true;
	}

	/**
	 * Reset player's match statistics. Useful when reusing the same Player object across innings (if desired).
	 */
	public void reset() {
		this.runs = 0;
		this.ballsFaced = 0;
		this.isOut = false;
	}

	// Presentation helpers
	/**
	 * Returns a single-line formatted stats string for scoreboard printing.
	 * Example: "Virat Kohli        45 (32)"
	 *
	 * @return formatted stats line
	 */
	public String getStatsLine() {
		return String.format("%-20s %3d (%2d)", name, runs, ballsFaced);
	}

	/**
	 * Prints the formatted stats line to standard output.
	 */
	public void displayStats() {
		System.out.println(getStatsLine());
	}

	@Override
	public String toString() {
		return name + " " + runs + "(" + ballsFaced + ")" + (isOut ? " [out]" : "");
	}
}
