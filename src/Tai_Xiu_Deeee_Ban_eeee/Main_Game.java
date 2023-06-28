package Tai_Xiu_Deeee_Ban_eeee;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main_Game {
	public static Player[] Players = new Player[4];
	
	public static void InitializePlayRoom(int numberPlayers) {
		List<String> emotions = new ArrayList<String>();
		emotions.add("No f*k'in way, that's unfair.");
		emotions.add("What a frick'in disappointment.");
		emotions.add("Oh sh!t, let me try again.");
		emotions.add("Looks like things ain't frick'in work out today.");
		
		
		for (int i = 0; i < numberPlayers; i++) {
			System.out.println("Enter the name of the " + (i+1) + "-th player: ");
			Scanner scanner = new Scanner(System.in);
			String name = scanner.nextLine();
			Players[i] = new Player(name);
		}
		
		for (int i=numberPlayers; i<4; i++) {
			Players[i] = new VirtualPlayer("Virtual Player",emotions.get(i));
		}
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner;
		String input;
		int numberPlayers;
		try {
			do {
				System.out.println("Enter number of players(0-4): ");
				scanner = new Scanner(System.in);
				input = scanner.nextLine();
				numberPlayers = Integer.parseInt(input);
			} while( 0>numberPlayers || numberPlayers>4 );
			
			InitializePlayRoom(numberPlayers);
			for (int i=0; i<4; i++) 
				System.out.println(Players[i].toString());
			
			
		} catch (NumberFormatException e) {
			System.out.println("The number of players must be an integer.");
		}
		
	}

}
