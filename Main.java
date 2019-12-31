package battleships;

import java.io.*;
import java.util.*;
public class Main {
	
	static Scanner scan = new Scanner(System.in);	
	LinkedList<String> highscoreList = new LinkedList<String>();
	LinkedList<Integer> scores = new LinkedList<Integer>();
	
	public static void main(String[] args) throws IOException {
		Main main = new Main();
		main.menu();
	}
	
	public void menu() throws IOException {
		
		InputStream highscores = new FileInputStream ("/Users/adam/eclipse-workspace/battleships/src/battleships/highscores.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(highscores));
	    while (reader.ready()) {
	    	String line = reader.readLine();
	    	if(!line.equals("")) {
	    		highscoreList.add(line);
	    	}
	    }
	    reader.close();
	    
		int a = 0;
		
		while (a != 5) {
			a = showMenu();
			switch(a) {
				case 1:	
					Game vsFriend = new Game();
					vsFriend.PvP();
					Player p = vsFriend.getWinner();
					String playerName = p.getNickname();
					int shots = p.getShots();
					int checkShots = checkHighscoreList(shots);
					if(checkShots > 0) {
						System.out.println("\n" + "Congratulations! You made the highscore list!");
						saving(shots, checkShots, playerName);
					}
					System.out.println();
				break;
				
				case 2:
					Game vsBot = new Game();
					vsBot.PvE();
				break;
			
				case 3:
					System.out.println();
					for(String s : highscoreList) {
				    	System.out.println(s);
				    }
					System.out.println();
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
		System.out.println("     Battleships!     ");
		System.out.println("======================");
		System.out.println("1. Play Versus Friends");
		System.out.println("2. Play Versus Bots   ");
		System.out.println("3. Show Highscores    ");
		System.out.println("4. Rules              ");
		System.out.println("5. Quit               ");
		
		int choice = scan.nextInt();
		
		return choice;

	}
	
	public int checkHighscoreList(int shots) {
		
		int scorePlace = 1;
		
		for(String s : highscoreList) {
	        String[] splitLine = s.split(":");
	        int recordShots = Integer.parseInt(splitLine[2].trim());
	        if(shots <= recordShots) {
	        	return scorePlace;
	        }
	        
	        scorePlace++;
		}
		return 0;
	}
	
	public void saving(int shots, int checkShots, String playerName) throws IOException {
		
		LinkedList<String> placeholder = new LinkedList<String>();
		int i = checkShots + 1;
		for(String s : highscoreList) {
			if(checkShots + 48 > s.charAt(0)) {
				placeholder.add(s);
				continue;
			}
			if(checkShots + 48 == s.charAt(0)) {
				placeholder.add(checkShots + ". Name: " + playerName + ", Missiles Fired: " + shots);
				continue;
			}
			String[] splitLine = s.split(":");
			placeholder.add(i + ". Name:" + splitLine[1] + ":" + splitLine[2]);
			i++;
		}
		
		highscoreList.clear();
		
		for(String s : placeholder) {
			highscoreList.add(s);
		}
		
		for(String s : highscoreList) {
			System.out.println(s);
			
		}
		
		OutputStream spara = new FileOutputStream ("/Users/adam/eclipse-workspace/battleships/src/battleships/highscores.txt", false);
		saveHighscore(spara);
	}
	
	public void saveHighscore(OutputStream os) throws IOException {
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(os);
		for(String s : highscoreList) {
			outputStreamWriter.append(s + "\n");
		}
		outputStreamWriter.close();
	}
	
}