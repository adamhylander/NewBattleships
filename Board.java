package battleships;

import java.util.*;

public class Board {
	LinkedHashMap<Coordinates, Pieces> map = new LinkedHashMap<Coordinates, Pieces>();
	LinkedList<Boats> playerBoatList = new LinkedList<Boats>();
	int health = 0;
	final int boardSize = 10;
	Scanner scan = new Scanner(System.in);
	
	public void makeBoard() {
		char K = 'A';
		String keys = "";
		
		for (int yAxis = 0; yAxis < boardSize; yAxis++) {
			
			for (int xAxis = 0; xAxis < boardSize; xAxis++) {
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
	
	public void addBoatToBoard(String coordinates, boolean alignment, int size, String name) {
		char yCoord = coordinates.charAt(0);
		int xCoord = coordinates.charAt(1);
		LinkedList<Coordinates>  boatCoordinates = new LinkedList<Coordinates>();
		Pieces boatPiece = new Pieces('#');
		
		if (alignment == true) { 
			int boatLength = size + xCoord; 
				for(Coordinates keys : map.keySet()) {
				
					if (xCoord == boatLength) {
						break;
					}
					
					String checkKeys = keys.toString();
					
					if((checkKeys.charAt(0) == yCoord) && (keys.toString().charAt(1) == xCoord)) {
						map.put(new Coordinates (checkKeys), boatPiece);
						boatCoordinates.add(new Coordinates (checkKeys));
						health++;
						xCoord++;
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
					map.put(new Coordinates (checkKeys), boatPiece);
					boatCoordinates.add(new Coordinates (checkKeys)); 
					health++;
					yCoord++;
					lengthCounter++;
				}
			}
		
		}

		playerBoatList.add(new Boats(name, size, boatCoordinates));	
	}
	
	public boolean checkBoard(String coordinates, boolean alignment, int size) {
		
		char yCoordStart = (char) (coordinates.charAt(0) - 1);
		char xCoordStart = (char) (coordinates.charAt(1) - 1);
		
		if(coordinates.charAt(1) == '0') {
			xCoordStart = '0';
		}
		if(coordinates.charAt(0) == 'A') {
			yCoordStart = 'A';
		}
		
		if(alignment == true) {
			if(coordinates.charAt(1) + size - 48 > 10) {
				return false;
			}
			
			for(Coordinates keys : map.keySet()) {
				String check = "" + yCoordStart + xCoordStart;
				
				if(check.equals(keys.toString()) || xCoordStart == 58) {
					if(map.get(keys).toString().contains("#")) {

						return false;
					}
					
					xCoordStart++;
					
					if(xCoordStart == size+ coordinates.charAt(1) +1) {
						yCoordStart++;
						for (int i = 0; i< size+2; i++) {
							if(xCoordStart == '0') {
								break;
							}
							xCoordStart--;
						}
					}
					
				}
				
				if(yCoordStart == (char) (coordinates.charAt(0) + 2)) {
					break;
				}
				
			}
		}else { 
			if(size + coordinates.charAt(0) - 65 > 10) {
				return false;
			}
			for(Coordinates keys : map.keySet()) {					
				String check = "" + yCoordStart + xCoordStart;
				
				if(check.equals(keys.toString()) || xCoordStart == 58) {
					if(map.get(keys).toString().contains("#") && xCoordStart != 58) {
						return false;
					}
					
					xCoordStart++;
					
					if(xCoordStart == (char) (coordinates.charAt(1) + 2)) {
						yCoordStart++;
						for(int i = 0; i<3; i++) {
							if(xCoordStart == '0') {
								 break;
							}
							xCoordStart--;
						}
					}
					
				}
				
				if(yCoordStart == (char) (coordinates.charAt(0) + size + 1) || yCoordStart == 'K') {
					break;
				}
			}
		
		}
		
		return true;
	}
	
	public boolean onBoard(String coordinates) {
		if(coordinates.charAt(0) < 'A' || coordinates.charAt(0) > 'J') {
			return false;
		}
		if(coordinates.charAt(1) < '0' || coordinates.charAt(1) > '9') {
			return false;
		}
		if(String.valueOf(coordinates).length() != 2) {
			return false;
		}
		return true;
	}
	
	public boolean position(String alignment) {
		if (alignment.equals("h")) {
			return true;
		}else {
			return false;
		}
	}
	
	public void placeBoats() {
		printBoard();
		for(Boats boats : Boats.boats) {
			System.out.println("Where would you like to place the " + boats.getName() + "?");
			String boatPosition = scan.nextLine();
			
			System.out.println("h/v?");
			String boatAlignment = scan.nextLine();
			
			while (checkBoard(boatPosition, position(boatAlignment), boats.getSize()) != true || onBoard(boatPosition) != true) {
				System.out.println("Where would you like to place " + boats.getName() + "?");
				boatPosition = scan.nextLine();
				
				System.out.println("h/v?");
				boatAlignment = scan.nextLine();
			}
			addBoatToBoard(boatPosition, position(boatAlignment), boats.getSize(), boats.getName());	
			printBoard();
		}
	}
	
	public void computerPlaceBoats() {
		for(Boats boats : Boats.boats) {
			String boatPosition = getRandomCoordinate();
			boolean boatAlignment = getRandomAlignment();
			
			while((checkBoard(boatPosition, boatAlignment, boats.getSize()) != true)) {
				boatPosition = getRandomCoordinate();
				boatAlignment = getRandomAlignment();
			}
			
			addBoatToBoard(boatPosition, boatAlignment, boats.getSize(), boats.getName());
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
	
	public boolean getRandomAlignment() {
		return Math.random() < 0.5;
	}
}
	