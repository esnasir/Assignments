package com.nasir.cricket.engine;
import java.util.Random;
import java.util.Scanner;
import com.nasir.cricket.model.Team;
import com.nasir.cricket.model.Player;
import com.nasir.cricket.model.BallEvent;

public class MatchEngine {
	private final Team teamA;
	private final Team teamB;
	private final int totalOvers;
	private final Scanner sc;
	
	public MatchEngine(Team a, Team b, int overs) {
		this.teamA = a;
		this.teamB = b;
		this.totalOvers = overs;
		sc = new Scanner(System.in);
	}
	
	public Team toss() {
		Random rand = new Random();
		int toss = rand.nextInt(2);
		
		Team battingTeam, bowlingTeam;
		if(toss == 0) {
			battingTeam = teamA;
			bowlingTeam = teamB;
		}
		else {
			battingTeam = teamB;
			bowlingTeam = teamA;
		}
		System.out.println(battingTeam.getName() + " won the toss and will bat first!");
		return battingTeam;
	}
	
	public void playInnings(Team battingTeam, Team bowlingTeam, int target) {
		System.out.println("\n--- " + battingTeam.getName() + " Batting ---");
		Player striker = battingTeam.getStriker();
		Player nonStriker = battingTeam.getNonStriker();
		
		int ballsLimit = totalOvers * 6;
		int ballCount = 0;
		
		while ((ballCount < ballsLimit && battingTeam.getWickets() < 10) && (target == -1 || battingTeam.getRuns() < target)) {
			System.out.print("Ball " + (ballCount + 1) + ": ");
			int input = sc.nextInt();
			BallEvent event = mapInputToEvent(input);
			
			switch(event) {
				case DOT -> {
					System.out.println("Dot Ball.");
					ballCount++;
					battingTeam.addBall();
					striker.addBall();

					if(ballCount % 6 == 0) {
						battingTeam.switchStrike();
						striker = battingTeam.getStriker();
						nonStriker = battingTeam.getNonStriker();

					}
				}
				
				case RUN1, RUN2, RUN3, RUN4, RUN5, RUN6 -> {
					int runs = Integer.parseInt(event.name().substring(3));
					battingTeam.addRuns(runs);
					battingTeam.addBall();
					striker.addRunsAndBall(runs);
					ballCount++;
					String line = getCommentary(event, striker);
					if (!line.isEmpty()) System.out.println(line);
					
					if(runs % 2 != 0) {
						battingTeam.switchStrike();
						striker = battingTeam.getStriker();
						nonStriker = battingTeam.getNonStriker();

					}

					if(ballCount % 6 == 0) {
						battingTeam.switchStrike();
						striker = battingTeam.getStriker();
						nonStriker = battingTeam.getNonStriker();

					}
				}
				
				case WIDE, NO_BALL -> {
					battingTeam.addRuns(1);
					System.out.println(event.name().replace("_", " ") + "! +1 run");
				}
				
				case WICKET -> {
					battingTeam.addWicket();
					battingTeam.addBall();
					striker.addBall();
					striker.setOut();
					ballCount++;
					String line = getCommentary(event, striker);
					if (!line.isEmpty()) System.out.println(line);

					battingTeam.newBatsman();
					if(battingTeam.getStrikerIndex() < battingTeam.getPlayers().size()) {
						striker = battingTeam.getStriker();
					}
					if (ballCount % 6 == 0) {  // End-of-over check here
						battingTeam.switchStrike();
						striker = battingTeam.getStriker();
						nonStriker = battingTeam.getNonStriker();
					}
				}
			}
			
			displayDashboard(battingTeam, striker, nonStriker, ballCount);
		}
		
		System.out.println("\nEnd of innings: " + battingTeam.getName() + " scored " + battingTeam.getRuns() + "/" + battingTeam.getWickets());
		System.out.println("\nBatting Scorecard:");
		System.out.printf("%-20s %-10s %-10s %-10s%n", "Player", "Runs", "Balls", "Status");
		for (Player p : battingTeam.getPlayers()) {
			String status = p.isOut() ? "Out" : "Not Out";
			System.out.printf("%-20s %-10d %-10d %-10s%n", p.getName(), p.getRuns(), p.getBallsFaced(), status);
		}
		System.out.println("------------------------------------------------------------\n");

	}

	private void displayDashboard(Team battingTeam, Player striker, Player nonStriker, int balls) {
		int oversDone = balls / 6;
		int currentBall = balls % 6;

		System.out.println("\n------------------------------------------------------------");
		System.out.printf("🏏  %s  |  Score: %d/%d  |  Overs: %d.%d%n",
				battingTeam.getName(),
				battingTeam.getRuns(),
				battingTeam.getWickets(),
				oversDone,
				currentBall);
		System.out.println("------------------------------------------------------------");

		System.out.printf("%-20s %-10s %-10s%n", "Batsman", "Runs", "Balls");
		System.out.println("------------------------------------------------------------");
		System.out.printf("%-20s %-10d %-10d%n", striker.getName() + " *", striker.getRuns(), striker.getBallsFaced());
		System.out.printf("%-20s %-10d %-10d%n", nonStriker.getName(), nonStriker.getRuns(), nonStriker.getBallsFaced());
		System.out.println("------------------------------------------------------------");
	}


	private BallEvent mapInputToEvent(int input) {
		return switch(input) {
			case 0 -> BallEvent.DOT;
            case 1 -> BallEvent.RUN1;
            case 2 -> BallEvent.RUN2;
            case 3 -> BallEvent.RUN3;
            case 4 -> BallEvent.RUN4;
            case 5 -> BallEvent.RUN5;
            case 6 -> BallEvent.RUN6;
            case 7 -> BallEvent.WIDE;
            case 8 -> BallEvent.NO_BALL;
            case 9 -> BallEvent.WICKET;
            default -> BallEvent.DOT;
		};
	}

	private String getCommentary(BallEvent event, Player striker) {
		String name = striker.getName();
		Random rand = new Random();
		return switch(event) {
			case RUN4 -> {
				String[] lines = {
						name + " finds the gap! Four runs!",
						"Brilliant shot by " + name + " — that’s a boundary!",
						name + " times it beautifully, four!"
				};
				yield lines[rand.nextInt(lines.length)];
			}
			case RUN6 -> {
				String[] lines = {
						name + " sends it into the stands! What a six!",
						"Massive hit! That’s out of the park by " + name + "!",
						name + " launches that straight over long-on!"
				};
				yield lines[rand.nextInt(lines.length)];
			}
			case WICKET -> {
				String[] lines = {
						"Wicket! " + name + " departs after a fine knock.",
						"Oh no! " + name + " edges it, and he’s gone!",
						"That’s a huge wicket! " + name + " has to walk back."
				};
				yield lines[rand.nextInt(lines.length)];
			}
			default -> "";
		};
	}

}