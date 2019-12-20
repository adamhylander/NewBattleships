package battleships;

import java.util.*;
public class Main {
	
	public static void main(String[] args) {
		Main demo = new Main();
		demo.menu();
	}
	
	public void menu() {
		System.out.println("     Battleships     ");
		System.out.println("=====================");
		
		Scanner scan = new Scanner(System.in);
		
		int a = 0;
		
		while (a != 4) {
			a = showMenu();
			switch(a) {
				case 1:	
					runGame();
				break;
				
				case 2:
					Board computerBoard = new Board();
					computerBoard.makeBoard();
					computerBoard.computerPlaceBoats();
					computerBoard.printBoard();
					
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
					System.out.println("Please type a number u dumbo");
				break;
			}
			
		}
	}
	
	public void runGame() {
		
		int playerOneHealth = 25;
		int playerTwoHealth = 25;
		
//		while(!speletslut) {
//			Player next = //ta reda på vems tur det är
//			for(Player p : players) {
//				next.runTurn(p);
//			}
//		}
		
		Board playerOneBoard = new Board();
		Board playerOneEnemyBoard = new Board();
		playerOneBoard.makeBoard();
		playerOneBoard.placeBoats();
		playerOneEnemyBoard.makeBoard();
		
		Board playerTwoBoard = new Board();
		Board playerTwoEnemyBoard = new Board();
		playerTwoBoard.makeBoard();
		playerTwoBoard.placeBoats();
		playerTwoEnemyBoard.makeBoard();
		
		
	}
	
	private static int showMenu() {
		System.out.println("1. Play Versus Friend");
		System.out.println("2. Play Versus Bot");
		System.out.println("3. Show Highscores");
		System.out.println("4. Rules");
		System.out.println("5. Quit");
		
		Scanner input = new Scanner(System.in);
		int choice = input.nextInt();
		
		if (choice == 5) {
			input.close();
		}
		
		return choice;

	}
	
}