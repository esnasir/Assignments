package com.nasir.cricket.main;
import java.util.Scanner;
import com.nasir.cricket.model.Team;
import com.nasir.cricket.engine.MatchEngine;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String[] iplTeams = {"RCB", "CSK", "MI", "KKR", "RR", "DC", "PBKS", "SRH"};
		
		System.out.println("Available IPL Teams: ");
		for(int i = 0; i < iplTeams.length; i++) {
			System.out.println((i + 1) + ". " + iplTeams[i]);
		}
		
		System.out.print("Enter Team-1 number: ");
		int teamOneIndex = sc.nextInt() - 1;
		System.out.print("Enter Team-2 number: ");
		int teamTwoIndex = sc.nextInt() - 1;
		
		Team teamA = new Team(iplTeams[teamOneIndex]);
		Team teamB = new Team(iplTeams[teamTwoIndex]);
		
		System.out.print("Enter number of overs: ");
		int overs = sc.nextInt();
		
		MatchEngine engine = new MatchEngine(teamA, teamB, overs);
		
		Team firstBatting = engine.toss();
		Team secondBatting = firstBatting == teamA ? teamB : teamA;
		
		engine.playInnings(firstBatting, secondBatting, -1);
		
		int target = firstBatting.getRuns() + 1;
		engine.playInnings(secondBatting, firstBatting, target);

		System.out.println("\n============================================================");
		System.out.println("                      MATCH SUMMARY                         ");
		System.out.println("============================================================");

		int score1 = firstBatting.getRuns();
		int score2 = secondBatting.getRuns();

		System.out.printf("%-15s : %3d/%-2d  (Overs: %d.%d)%n",
				firstBatting.getName(),
				score1,
				firstBatting.getWickets(),
				firstBatting.getBallsPlayed() / 6,
				firstBatting.getBallsPlayed() % 6);

		System.out.printf("%-15s : %3d/%-2d  (Overs: %d.%d)%n",
				secondBatting.getName(),
				score2,
				secondBatting.getWickets(),
				secondBatting.getBallsPlayed() / 6,
				secondBatting.getBallsPlayed() % 6);

		System.out.println("------------------------------------------------------------");

		if (score2 > score1) {
			int wicketsLeft = 10 - secondBatting.getWickets();
			System.out.printf("🏆  %s WON by %d wicket(s)!%n", secondBatting.getName(), wicketsLeft);
		}
		else if (score2 < score1) {
			int runDiff = score1 - score2;
			System.out.printf("🏆  %s WON by %d run(s)!%n", firstBatting.getName(), runDiff);
		}
		else {
			System.out.println("🤝  The match ended in a TIE!");
		}

		System.out.println("============================================================\n");

	}
}