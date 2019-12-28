package battleships;

import java.util.*;
import java.io.*;

public class Player {
	int health = 25;
	String nickname;
	LinkedList<Boats> boats;
	LinkedList<Player> players = new LinkedList<Player>();
	Scanner scan = new Scanner(System.in);
	
	public Player(int health, String nickname, LinkedList<Boats> boats) {
		this.health = health;
		this.nickname = nickname;
		this.boats = boats;
	}
	
	public void createPlayer(int amountOfPlayers) {
		for(int i = 1; i <= amountOfPlayers; i++) {
			System.out.println("Vad ska spelare " + i + "heta?");
			String spelarnamn = scan.nextLine();
			
		}
	}
}
