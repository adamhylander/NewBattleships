package battleships;

import java.util.*;
public class Main {
	
	static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		Main demo = new Main();
		demo.menu();
	}
	
	public void menu() {
		System.out.println("     Battleships     ");
		System.out.println("=====================");
		
		int a = 0;
		
		while (a != 4) {
			a = showMenu();
			switch(a) {
				case 1:	
					PvE();
				break;
				
				case 2:
					Board computerBoard = new Board();
					computerBoard.makeBoard();
					computerBoard.computerPlaceBoats();
					
				break;
			
				case 3:
				
				break;
				
				case 4:
					System.out.println("Rules:");
					System.out.println("1. Type coordinates like this A9");
				break;
			
				case 5:
					System.out.println("Thank you for playing");
					System.exit(0);
				break;
				
				default:
					System.out.println("Please type another number u dumbo");
				break;
			}
			
		}
	}
	
	public void PvE() {
		System.out.println("How many would like to play? Max is 4");
		int amountOfPlayers = scan.nextInt();
		
		while(amountOfPlayers < '1' && amountOfPlayers > '4') {
			System.out.println("How many would like to play?");
			amountOfPlayers = scan.nextInt();
		}
		
//		while(!speletslut) {
//			Player next = //ta reda på vems tur det är
//			for(Player p : players) {
//				next.runTurn(p);
//			}
//		}
		Board playerOneBoard = new Board();
		playerOneBoard.makeBoard();
		
		Board playerTwoBoard = new Board();
		playerTwoBoard.makeBoard();
		
		
	}
	
	private static int showMenu() {
		System.out.println("1. Play Versus Friend");
		System.out.println("2. Play Versus Bot");
		System.out.println("3. Show Highscores");
		System.out.println("4. Rules");
		System.out.println("5. Quit");
		
		int choice = scan.nextInt();
		
		return choice;

	}
	
}