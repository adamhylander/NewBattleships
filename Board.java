package battleships;

import java.util.*;

public class Board {
	LinkedHashMap<Coordinates, Pieces> map = new LinkedHashMap<Coordinates, Pieces>();
	LinkedList<Boats> boats = new LinkedList<Boats>();
	final int size = 10;
	final int twoBoat = 2;		//Tre stycken
	final int threeBoat = 3;	//Två stycken
	final int fourBoat = 4;		//Två stycken
	final int fiveBoat = 5;		//En stycken

	public Pieces lookup(Coordinates key) {
		return map.get(key);
	}
	
	public void makeBoard() {
		
		int size = 10;
		char K = 'A';
		String keys = "";
		
		for (int yAxis = 0; yAxis < size; yAxis++) {
			
			for (int xAxis = 0; xAxis < size; xAxis++) {
				keys = "" + K + xAxis;
				map.put(new Coordinates(keys), new Pieces ('~'));
			}
		K++;
		}
		
		
	
	}
	
	public void printBoard() {
				
			System.out.println("\n" + "  | 0 1 2 3 4 5 6 7 8 9");
			System.out.println("--+--------------------");
		
			char k = 'A';
			
			for(Coordinates keys : map.keySet()) {
					
				if(keys.toString().charAt(1) == '0') {
					System.out.print(k + " | ");
					k++;
				}	
				
				System.out.print(map.get(keys) + " ");
				
				if(keys.toString().charAt(1) == '9') {
					System.out.println("");
				}
			}

			System.out.println("\n");
		
	}
	
	public void addBoat(String coordinates, boolean xOry, int size) {
		char yCoord = coordinates.charAt(0);
		int xCoord = coordinates.charAt(1);
		
		Pieces boatPiece = new Pieces('#');
		
		LinkedList<Coordinates> testList = new LinkedList<Coordinates>(); 
		
		if (xOry == true) { 
			int boatLength = size + xCoord; 
				for(Coordinates keys : map.keySet()) {
				
					if (xCoord == boatLength) {
						break;
					}
					
					String checkKeys = keys.toString();
					
					if((checkKeys.charAt(0) == yCoord) && (keys.toString().charAt(1) == xCoord)) {
						Coordinates replaceCoords = new Coordinates(checkKeys);
						map.put(new Coordinates (checkKeys), boatPiece);
						xCoord++;
						
						testList.add(new Coordinates (checkKeys));
						
					}
				}
			
		}else { 
			int lengthCounter = 0;
			
			for(Coordinates keys : map.keySet()) {
			
				if (size == lengthCounter) {
					break;
				}
				
				String checkKeys = keys.toString();
				
				if((checkKeys.charAt(0) == yCoord) && (keys.toString().charAt(1) == xCoord)) {
					Coordinates replaceCoords = new Coordinates(checkKeys);
					map.put(replaceCoords, boatPiece);
					yCoord++;
					lengthCounter++;
				}
			}
		
	}
		
	}
	
	public boolean checkBoard(String coordinates, boolean xOry, int size) {
		
		char yCoord = coordinates.charAt(0);
		char yCoordStart = (char) (yCoord - 1);
		
		char xCoord = coordinates.charAt(1);
		char xCoordStart = (char) (xCoord - 1);
		
		if(coordinates.charAt(1) == '0') {
			xCoordStart = '0';
		}
		if(coordinates.charAt(0) == 'A') {
			yCoordStart = 'A';
		}
		
		if(xOry == true) {
			if(xCoord + size - 48 > 10) {
				return false;
			}
			
			for(Coordinates keys : map.keySet()) {
				String check = "" + yCoordStart + xCoordStart;
				
				if(check.equals(keys.toString()) || xCoordStart == 58) {
					if(lookup(keys).toString().contains("#")) {
						return false;
					}
					
					xCoordStart++;
					
					if(xCoordStart == size+xCoord+1) {
						yCoordStart++;
						for (int i = 0; i< size+2; i++) {
							if(xCoordStart == '0') {
								break;
							}
							xCoordStart--;
						}
					}
					
				}
				
				if(yCoordStart == (char) (yCoord + 2)) {
					break;
				}
				
			}
		}else { 
			if(size + yCoord - 65 > 10) {
				return false;
			}
			for(Coordinates keys : map.keySet()) {					
				String check = "" + yCoordStart + xCoordStart;

				if(check.equals(keys.toString()) || xCoordStart == 58) {
					if(lookup(keys).toString().contains("#")) {
						return false;
					}
					
					xCoordStart++;
					
					if(xCoordStart == (char) (xCoord + 2)) {
						yCoordStart++;
						for(int i = 0; i<3; i++) {
							if(xCoordStart == '0') {
								 break;
							}
							xCoordStart--;
						}
					}
					
				}
				
				if(yCoordStart == (char) (yCoord + size + 1)) {
					break;
				}
			}
		
		}
		
		return true;
	}
	
	public boolean position(String a) {
		if (a.equals("h")) {
			return true;
		}else {
			return false;
		}
	}
	
	public void placeBoats() {
		printBoard();
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Now it is time for Player 1 to place his boats");
		
		System.out.println("What would you like to the name the 5 boat?");
		String fiveName = scan.nextLine();
		
		System.out.println("Where would you like to place " + fiveName + "?");
		String five = scan.nextLine();
		
		System.out.println("h/v?");
		String fivePosition = scan.nextLine();
		
		while (checkBoard(five, position(fivePosition), fiveBoat) != true) {
			System.out.println("Where would you like to place " + fiveName + "?");
			five = scan.nextLine();
			
			System.out.println("h/v?");
			fivePosition = scan.nextLine();
		}
		
		addBoat(five, position(fivePosition), fiveBoat);
		
		printBoard();
		
		int counter = 0;
		
		while(counter != 2) {
			System.out.println("What would you like to the name the 4 boat?");
			String fourName = scan.nextLine();
			
			System.out.println("Where would you like to place " + fourName + "?");
			String four = scan.nextLine();
		
			System.out.println("h/v?");
			String fourPosition = scan.nextLine();
			
			while (checkBoard(four, position(fourPosition), fourBoat) != true) {
				System.out.println("Where would you like to place " + fourName + "?");
				four = scan.nextLine();
			
				System.out.println("h/v?");
				fourPosition = scan.nextLine();
			}
			
			addBoat(four, position(fourPosition), fourBoat);
			counter++;
			printBoard();
		}
		
		counter = 0;
		
		while(counter != 2) {
			System.out.println("What would you like to the name the 3 boat?");
			String threeName = scan.nextLine();
			
			System.out.println("Where would you like to place " + threeName + "?");
			String three = scan.nextLine();
		
			System.out.println("h/v?");
			String threePosition = scan.nextLine();
			
			while (checkBoard(three, position(threePosition), threeBoat) != true) {
				System.out.println("Where would you like to place " + threeName + "?");
				three = scan.nextLine();
			
				System.out.println("h/v?");
				threePosition = scan.nextLine();
			}
			
			addBoat(three, position(threePosition), threeBoat);
			counter++;
			printBoard();
		}
		
		counter = 0;
		
		while(counter != 3) {
			System.out.println("What would you like to the name the 2 boat?");
			String twoName = scan.nextLine();
			
			System.out.println("Where would you like to place " + twoName + "?");
			String two = scan.nextLine();
		
			System.out.println("h/v?");
			String twoPosition = scan.nextLine();
			
			while (checkBoard(two, position(twoPosition), twoBoat) != true) {
				System.out.println("Where would you like to place " + twoName + "?");
				two = scan.nextLine();
			
				System.out.println("h/v?");
				twoPosition = scan.nextLine();
			}
			
			addBoat(two, position(twoPosition), twoBoat);
			counter++;
			printBoard();
		}
		
		scan.close();
		
	}
	
	public void testPlacement() {
		Scanner scan = new Scanner(System.in);
		int sizeAndHealth = 5;
		String boatName = null;
		
		System.out.println("What would you like to name the boat?");
	}
	
	public void computerPlaceBoats() {
		String five =  getRandomCoordinate();
		boolean fivePosition = computerPosition();
		
		while(checkBoard(five, fivePosition, fiveBoat) != true) {
			five =  getRandomCoordinate();
			fivePosition = computerPosition();
		}
		
		addBoat(five, fivePosition, fiveBoat);
		
		int counter = 0;
		
		while (counter != 2) {
			String four =  getRandomCoordinate();
			boolean fourPosition = computerPosition();
			
			while(checkBoard(four, fourPosition, fourBoat) != true) {
				four =  getRandomCoordinate();
				fourPosition = computerPosition();
			}
			
			addBoat(four, fourPosition, fourBoat);
			counter++;
		}
		
		counter = 0;
		
		while (counter != 2) {
			String three =  getRandomCoordinate();
			boolean threePosition = computerPosition();
			
			while(checkBoard(three, threePosition, threeBoat) != true) {
				three =  getRandomCoordinate();
				threePosition = computerPosition();				
			}
			
			addBoat(three, threePosition, threeBoat);
			counter++;
			
		}
		
		counter = 0;
		
		while (counter != 3) {
			String two =  getRandomCoordinate();			
			boolean twoPosition = computerPosition();
			
			while(checkBoard(two, twoPosition, twoBoat) != true) {
				two =  getRandomCoordinate();
				twoPosition = computerPosition();
			}
			
			addBoat(two, twoPosition, twoBoat);
			counter++;
		}
		
	}
	
	public String getRandomCoordinate(){
		double xmin = 0;
		double xmax = 9;
		int xCoord = (int) ((Math.random()*((xmax-xmin)+1))+xmin);
		
		double ymin = 65;
		double ymax = 74;
		char yCoord = (char) ((Math.random()*((ymax-ymin)+1))+ymin);
		
		String coordinate = "" + yCoord + xCoord;
		
		return coordinate;
	}
	
	public boolean computerPosition() {
		return Math.random() < 0.5;
	}
}
	