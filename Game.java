package battleships;

import java.util.*;

public class Game {
	
	int amountOfPlayers;
	int turnCounter = 1; 
	int totalHealth = 0;
	int amountOfTurns = 0;
	Pieces miss = new Pieces('O');
	Pieces hit = new Pieces('X');
	Scanner scan = new Scanner(System.in);
	LinkedList<Player> players = new LinkedList<Player>();
	List<Player> defeatedPlayers = new ArrayList<Player>();
	Board boardMethods = new Board();
	
	//Metod för spel mellan människor
	public void PvP() {
		//Skapar x antal bräden och spelare beroende på användarens input
		while(amountOfPlayers < 2) {
			try {
				System.out.println("How many would like to play?");
				amountOfPlayers = scan.nextInt();	

			} 
			catch(InputMismatchException error) {
				System.out.println("Ange ett heltal");
				scan.nextLine();
			}
		}
		createPlayer(amountOfPlayers);
		Boats.printBoatList(Boats.boats);
		
		//Låter spelarna placera ut sina båtar själva eller välja att datorn ska placera ut dem slumpmässigt
		for(Player p : players) {
			System.out.println("Time for " + p.getNickname() + " to place his boats" + "\n");
			String answer = "";
			do {
				System.out.println("Randomize board? y/n");
				answer = scan.next();
			}while(!(answer.equals("y") || answer.equals("n")));
			if(answer.equals("y")) {
				computerPlaceBoats(p);
			}
			else {
				playerPlaceBoats(p);
			}
			totalHealth = p.getHealth();
		}
		printPlayerList();
		
		//Medan det finns fler än två spelare kvar, fortsätter spelet
		while(amountOfPlayers > 1) {
			PvPTurn();
		}
		
		//Lägger till vinnaren i defeatedPlayers så att vi kan enkelt printa all bräden i slutet av spelet.
		for(Player p : players) {
			defeatedPlayers.add(p);
		}
		
		//Skriver ut de spelare som förlorat och vem som vann
		gameOverPrint();
		for(Player p : players) {
			System.out.println("Congratulations " + p.getNickname() + "!");
			System.out.println("You Won! \n");
		}
	}
	
	//Metod för spel mot bottar
	public void PvE() {
		//Skapar en mänsklig spelare
		createPlayer(1);
		//Väljer antal bottar att spela mot och dessa får spelarnamn.
		while(amountOfPlayers < 1) {
			try {
				System.out.println("How many bots would you like to verse?");
				amountOfPlayers = scan.nextInt();	

			} 
			catch(InputMismatchException error) {
				System.out.println("Ange ett heltal");
				scan.nextLine();
			}
		}
		//Här skapar vi bottarna, anledningen till varför det är amountOfPlayers plus 1 är för enklare kunna använda generella metoder
		amountOfPlayers = amountOfPlayers + 1;
		for(int i = 2; i <= amountOfPlayers; i++) {
			String datorNamn = "Göran " + (i - 1);
			Player computerPlayer = new Player(i, 0, datorNamn, 0, null, null,null); 
			players.add(computerPlayer);
		}
		//Skriver ut de båtar som finns och låter spelaren placera dem eller låta datorn placera dem slumpmässigt
		Boats.printBoatList(Boats.boats);
		for(Player p : players) {
			if(p.getTurn() == 1) {
				String answer = "";
				do {
					System.out.println("Randomize board? y/n");
					answer = scan.next();
				}while(!(answer.equals("y") || answer.equals("n")));
				
				if(answer.equals("y")) {
					computerPlaceBoats(p);
				}
				else{
					playerPlaceBoats(p);
				}
			}
			else {
				computerPlaceBoats(p);
			}
			totalHealth = p.getHealth();
		}
		printPlayerList();
		//Medan det finns fler än en spelare kvar, fortsätter spelet
		while(amountOfPlayers > 1) {
			PvETurn();
		}
		for(Player p : players) {
			defeatedPlayers.add(p);
		}
		//Skriver ut vem som förlorat och vem som vann
		gameOverPrint();
		for(Player p : players) {
			System.out.println("Congratulations " + p.getNickname() + "!");
			System.out.println("You Won! \n");
		}
	}
	
	//Metod för att skapa spelare, låter spelarna välja spelarnamn
	public void createPlayer(int amountOfPlayers) {
		for(int i = 1; i <= amountOfPlayers; i++) {
			System.out.println("Vad ska spelare " + i + " heta?");
			String spelarnamn = scan.next();
			Player newPlayer = new Player(i, 0, spelarnamn, 0, null, null,null);
			players.add(newPlayer);
		}
	}
	
	//Metod som låter spelaren placera ut sina båtar
	public void playerPlaceBoats(Player p) {
		Board board = new Board();
		board.makeBoard();
		board.placeBoats();
		p.setBoats(board.playerBoatList);
		p.setPlayerBoard(board.map);
		p.setHealth(board.health);
		p.setEnemyBoard(board.enemyMap);
	}
	
	//Datorn placerar ut båtar
	public void computerPlaceBoats(Player p) {
		Board board = new Board();
		board.makeBoard();
		p.setEnemyBoard(board.enemyMap);
		board.computerPlaceBoats();
		p.setBoats(board.playerBoatList);
		p.setPlayerBoard(board.map);
		p.setHealth(board.health);
	}

	public void PvPTurn() {
		if(turnCounter == 1 && amountOfTurns > 0) {
				playerStats();
		}
		
		//Nästa spelares tur, skriver ut spelbräden
		for(Player p : players) {	
			if(p.getTurn() == turnCounter) {
				System.out.println(p.getNickname() + "'s turn! \n");
				printBoard();
				break;
			}
		}
		
		//Spelaren får fortsätta skjuta på alla andras enemyBoard tills den missar. Hoppar då ur whileloopen som blir false
		for(Player p : players) {
			if(p.getTurn() != turnCounter) {
				while (playerAim(p) == true);
			}
		}
		
		turnCounter++;
		
		//Om turnCounter är större än antal spelare, sätt den till 1 och lägg till 1 på antal rundor
		if(turnCounter > amountOfPlayers) {
			turnCounter = 1;
			amountOfTurns++;
		}
		
	}
	//Räknar hur många gånger varje spelare hur mycket av båtarna som är kvar samt varje spelares träffsäkerhet
	public void playerStats() {
		for(Player p : players) {
			int hitQuota = (100 - (100 * (amountOfPlayers - 1) * amountOfTurns)/p.getShots());
			float healthDenominator = (float)(totalHealth);
			float healthNominator = (float) (p.getHealth() * 100);
			int healthQuota = (int) (healthNominator/healthDenominator);
			System.out.println(p.getNickname() + "'s Accuracy: " + hitQuota + "%");
			System.out.println(p.getNickname() + "'s Health: " + healthQuota + "%\n");
			System.out.println();
		}
	}
	
	public void PvETurn() {
		if(amountOfTurns > 0) {
			playerStats();
		}
		
		//Nästa spelares tur, skriver ut spelbräden
		for(Player p : players) {	
			if(p.getTurn() == 1) {
				System.out.println(p.getNickname() + "'s turn! \n");
				printBoard();
				break;
			}
		}
		
		//Spelaren får fortsätta spela tills den missar
		for(Player p : players) {	
			if(p.getTurn() != 1) {
				while (playerAim(p) == true);
			}
		}
		
		turnCounter++;
		
		while(!(turnCounter > amountOfPlayers)) {
			for(Player p : players) { 	
				if(p.getTurn() != turnCounter) {
					while (computerAim(p) == true);
				}	
			}
			turnCounter++;
		}
		
		turnCounter = 1;
		amountOfTurns++;
		
	}
	
	//Låter spelaren skjuta på motståndarens bräde
	public boolean playerAim(Player p) {
		System.out.println("Where would you like to shoot " + p.getNickname() + "?");
		String playerAim = scan.next();
		System.out.println();
		//Om koordinaten inte finns på brädet får spelaren skriva ny koordinat
		while(boardMethods.onBoard(playerAim) == false) {
			System.out.println("Please fire on the board");
			playerAim = scan.next();
			System.out.println();
		}
		//Om spelaren redan skjutit på den angivna koordinaten får spelaren skriva ny koordinat
		Coordinates playerShot = new Coordinates(playerAim);
		while(p.getEnemyBoard().get(playerShot).equals(miss) || p.getEnemyBoard().get(playerShot).equals(hit)) {
			System.out.println("You have already fired there, please fire somewhere else");
			playerAim = scan.next();
			playerShot = new Coordinates(playerAim);
		}
		return shoot(p, playerShot, playerAim);
	}
	
	//Datorn skjuter på motståndarens bräde på slumpmässig koordinat
	//Om datorn redan skjutit på denna koordinat skjuter den på ny koordinat
	public boolean computerAim(Player p) {
		System.out.println("Firing at: " + p.getNickname());
		String botAim = boardMethods.getRandomCoordinate();
		System.out.println(botAim);
		Coordinates botShot = new Coordinates(botAim);
		while(p.getEnemyBoard().get(botShot).equals(miss) || p.getEnemyBoard().get(botShot).equals(hit)) {
			botAim = boardMethods.getRandomCoordinate();
			botShot = new Coordinates(botAim);
			System.out.println("Refiring at: " + p.getNickname());
			System.out.println(botAim);
		}
		return shoot(p, botShot, botAim);
	}
	
	//Skickar in spelarens namn och koordinat som den skjuter på
	public boolean shoot(Player p, Coordinates shot, String aim) {
			
			List<Coordinates> newCoordinates = new ArrayList<Coordinates>();
			List<Boats> newBoats = new ArrayList<Boats>();
			
			//Undersöker om spelaren träffade en båt på vald koordinat
			boolean isHit = false;
			for (Boats boats : p.getBoats()) {
				for(Coordinates coordinates : boats.getBoatCoordinates()) {
					
					//Vid träff - blir ett kryss på vald koordinat, ett liv försvinner
					if(coordinates.equals(shot)) {
						isHit = true;
						System.out.println("Hit! \n");
						p.getPlayerBoard().put(shot, hit);
						p.getEnemyBoard().put(shot, hit);
						p.setHealth(p.getHealth() - 1);
						newCoordinates.add(shot);
						shotCounter();
					}
				}
				
				//För varje träffad koordinat, tas del av båt bort
				for(Coordinates coord : newCoordinates) {
					boats.getBoatCoordinates().remove(coord);
					boats.setSize(boats.getSize()-1);
				}
				
				//När en båts storlek är 0 berättar programmet att spelarens specifika båt sjunkit
				if(boats.getSize() == 0) {
					newBoats.add(boats);
					System.out.println("You sunk " + p.getNickname() + "'s " + boats.getName() + "\n");
				}
				
				if(isHit == true) {
					break;
				}
			}
			
			//Tar bort de båtar som sjunkit från listan så att mängden iterationer blir färre i framtiden
			for(Boats boats : newBoats) {
				p.getBoats().remove(boats);
			}
			
			//Om en spelares liv är 0 förlorar spelaren och den tas bort från spelet
			if(p.getHealth() == 0) {
				System.out.println(p.getNickname() + "'s Fleet has been destroyed! \n");
				players.remove(p);
				defeatedPlayers.add(p);
				amountOfPlayers--;
				return false;
			}
			
			//Om spelaren skjuter, men inte träffar, markeras missen med ett O, oavsett miss eller träff,
			//räknas antal skott mha shotCounter()
			if(isHit == false) {
				System.out.println("Miss! \n");
				p.getPlayerBoard().put(shot, miss);
				p.getEnemyBoard().put(shot, miss);
				shotCounter();
				return false;
			}
			
			return true;
			
	}
	
	//Räknar antal skott skjutna
	public void shotCounter() {
		for(Player p : players) {
			if(p.getTurn() == turnCounter) {
				p.setShots(p.getShots() + 1);
			}
		}
	}
	
	//Skriver ut spelbrädet för varje spelare beroende på vems tur
 	public void printBoard() {
		for(Player p : players) {
			//Här skrivs spelarens egna bräde ut 
			if(p.getTurn() == turnCounter) {
				System.out.println(p.getNickname() + "'s Board");
				System.out.println("=======================");
				System.out.println("  | 0 1 2 3 4 5 6 7 8 9");
				System.out.println("--+--------------------");
				char k = 'A';				
				for(Coordinates keys : p.getPlayerBoard().keySet()) {
					if(keys.toString().charAt(1) == '0') {
						System.out.print(k + " | ");
						k++;
					}	
					System.out.print(p.getPlayerBoard().get(keys) + " ");
					if(keys.toString().charAt(1) == '9') {
						System.out.println("");
					}
				}
				System.out.println("\n");
			}
		}
		for(Player p : players) {
			//Här skrivs enemy lines ut. Så man kan spåra vart man redan skjutit 
			if(p.getTurn() != turnCounter) {
				System.out.println(p.getNickname() + "'s lines");
				System.out.println("=======================");
				System.out.println("  | 0 1 2 3 4 5 6 7 8 9");
				System.out.println("--+--------------------");
				char k = 'A';
				for(Coordinates keys : p.getEnemyBoard().keySet()) {
					if(keys.toString().charAt(1) == '0') {
						System.out.print(k + " | ");
						k++;
					}	
					System.out.print(p.getEnemyBoard().get(keys) + " ");
					if(keys.toString().charAt(1) == '9') {
						System.out.println("");
					}
				}
				System.out.println("\n");
			}
		}
	}
	
 	//Skriver ut alla spelares bräden i slutet.
	public void gameOverPrint() {
		for(Player p : defeatedPlayers) {
			System.out.println(p.getNickname() + "'s Board");
			System.out.println("=======================");
			System.out.println("  | 0 1 2 3 4 5 6 7 8 9");
			System.out.println("--+--------------------");
			char k = 'A';				
			for(Coordinates keys : p.getPlayerBoard().keySet()) {
				if(keys.toString().charAt(1) == '0') {
					System.out.print(k + " | ");
					k++;
				}	
				System.out.print(p.getPlayerBoard().get(keys) + " ");
				if(keys.toString().charAt(1) == '9') {
					System.out.println("");
				}
			}
			System.out.println("\n");
		}
	}
	
	//Skriver ut namnet på alla spelare
	public void printPlayerList() {
		System.out.println("\n");
		
		System.out.println("Here are all captains!");
		for(Player players : players) {
			System.out.println(players);
		}
		
		System.out.println("\n"); 
	}
	
	//Returnerar den spelare som vann
	public Player getWinner() {
		for(Player p : players) {
			return p;
		}
		return null;
	}

}
