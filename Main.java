package battleships;

import java.util.*;
public class Main {
	
	static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		Main main = new Main();
		main.menu();
	}
	
	public void menu() {
		
		int a = 0;
		
		while (a != 5) {
			System.out.println("     Battleships     ");
			System.out.println("=====================");
			
			a = showMenu();
			switch(a) {
				case 1:	
					Game vsFriend = new Game();
					vsFriend.PvP();
				break;
				
				case 2:
					Game vsBot = new Game();
					vsBot.PvE();
				break;
			
				case 3:
				
				break;
				
				case 4:
					System.out.println("\n");
					System.out.println("Rules:");
					System.out.println("1. Type coordinates with the yAxis coordinate first followed \n"
									+  "by the xAxis coordinate. Should look like this 'A0'\n");
					System.out.println("2. Your boat will be placed, depending on whether \n"
									+  "you placed it horizontally or vertically, from the \n"
									+  "leftmost respectively the upmost coordinate\n");
					System.out.println("3. No scrolling upwards to see the other player's board");
					System.out.println("4. Bots will fire randomly");
					System.out.println("\n");
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
	
	private static int showMenu() {
		System.out.println("1. Play Versus Friends");
		System.out.println("2. Play Versus Bot");
		System.out.println("3. Show Highscores");
		System.out.println("4. Rules");
		System.out.println("5. Quit");
		
		int choice = scan.nextInt();
		
		return choice;

	}
	
}