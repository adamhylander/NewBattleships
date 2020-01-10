package battleships;

import java.util.*;

//Class som skapar ett spelbräde och motståndarbräde för varje spelare.
public class Board {

	// Deklarerar listor till spelbrädena och variabler som kommer användas
	LinkedHashMap<Coordinate, Piece> map = new LinkedHashMap<Coordinate, Piece>();
	LinkedHashMap<Coordinate, Piece> enemyMap = new LinkedHashMap<Coordinate, Piece>();
	List<Boat> playerBoatList = new ArrayList<Boat>();
	int health = 0;
	final int boardSize = 10; // Maximala storlek på brädet, maximalt 10 rutor åt vardera håll (x och y-axel)
	Scanner scan = new Scanner(System.in);

	// Metod som skapar spelbräde
	public void makeBoard() {
		char K = 'A'; // Y-axeln börjar på bokstav A
		String keys = "";

		// För varje rad på y-axeln som är mindre än 10, lägger den till alla rutor med
		// nästa bokstav i x-led
		// Lägger in dessa i listorna map och enemyMap om den ska skapa flera spelbräden
		for (int yAxis = 0; yAxis < boardSize; yAxis++) {

			for (int xAxis = 0; xAxis < boardSize; xAxis++) {
				keys = "" + K + xAxis;
				map.put(new Coordinate(keys), new Piece('~'));
				enemyMap.put(new Coordinate(keys), new Piece('~'));
			}
			K++;
		}
	}

	// Metod som printar spelbrädet med tillhörande siffror och bokstäver, och
	// uppdateras allt
	// eftersom man lägger till båtar.
	public void printBoard() {

		System.out.println("\n" + "  | 0 1 2 3 4 5 6 7 8 9");
		System.out.println("--+--------------------");

		char k = 'A';

		for (Coordinate keys : map.keySet()) {

			if (keys.toString().charAt(1) == '0') {
				System.out.print(k + " | ");
				k++;
			}

			System.out.print(map.get(keys) + " ");

			if (keys.toString().charAt(1) == '9') {
				System.out.println("");
			}
		}

		System.out.println("\n");

	}

	// Metod för att placera ut båtar på brädet samt även lägga in koordinaterna för
	// varje båt i objektet Boats lista.
	public void addBoatToBoard(String Coordinate, boolean alignment, int size, String name) {
		char yCoord = Coordinate.charAt(0);
		int xCoord = Coordinate.charAt(1);
		LinkedList<Coordinate> boatCoordinate = new LinkedList<Coordinate>();
		Piece boatPiece = new Piece('#');

		if (alignment == true) {
			int boatLength = size + xCoord;
			for (Coordinate keys : map.keySet()) {

				if (xCoord == boatLength) {
					break;
				}

				String checkKeys = keys.toString();

				if ((checkKeys.charAt(0) == yCoord) && (keys.toString().charAt(1) == xCoord)) {
					map.put(new Coordinate(checkKeys), boatPiece);
					boatCoordinate.add(new Coordinate(checkKeys));
					health++;
					xCoord++;
				}
			}

		} else {
			int lengthCounter = 0;

			for (Coordinate keys : map.keySet()) {

				if (size == lengthCounter) {
					break;
				}

				String checkKeys = keys.toString();

				if ((checkKeys.charAt(0) == yCoord) && (keys.toString().charAt(1) == xCoord)) {
					map.put(new Coordinate(checkKeys), boatPiece);
					boatCoordinate.add(new Coordinate(checkKeys));
					health++;
					yCoord++;
					lengthCounter++;
				}
			}

		}

		playerBoatList.add(new Boat(name, size, boatCoordinate));
	}

	// Undersöker om det ligger någon båt inom 1 ruta från där man vill placera en
	// ny båt. Den här metoden går igenom alla värden på brädet. Vi börjar med att kolla
	// den rutan som är överst till vänster, exempelvis om vi kolla en båt som läggs på C1,
	// börjar vi kolla B0. Sen beroende på om båten ska ligga vertikalt eller horisontellt
	// itererar vi lite annorlunda.
	public boolean checkBoard(String Coordinate, boolean alignment, int size) {

		char yCoordStart = (char) (Coordinate.charAt(0) - 1);
		char xCoordStart = (char) (Coordinate.charAt(1) - 1);

		if (Coordinate.charAt(1) == '0') {
			xCoordStart = '0';
		}
		if (Coordinate.charAt(0) == 'A') {
			yCoordStart = 'A';
		}

		if (alignment == true) {
			// Om båten placeras på ett sådant sätt att båten skulle ligga utanför brädet,
			// returnerar vi false
			if (Coordinate.charAt(1) + size - 48 > 10) {
				return false;
			}

			for (Coordinate keys : map.keySet()) {
				String check = "" + yCoordStart + xCoordStart;

				if (check.equals(keys.toString()) || xCoordStart == 58) {
					if (map.get(keys).toString().contains("#")) {

						return false;
					}

					xCoordStart++;

					if (xCoordStart == size + Coordinate.charAt(1) + 1) {
						yCoordStart++;
						for (int i = 0; i < size + 2; i++) {
							if (xCoordStart == '0') {
								break;
							}
							xCoordStart--;
						}
					}

				}

				if (yCoordStart == (char) (Coordinate.charAt(0) + 2)) {
					break;
				}

			}
		} else {
			// Om båten placeras på ett sådant sätt att båten skulle ligga utanför brädet,
			// returnerar vi false
			if (size + Coordinate.charAt(0) - 65 > 10) {
				return false;
			}
			for (Coordinate keys : map.keySet()) {
				String check = "" + yCoordStart + xCoordStart;

				if (check.equals(keys.toString()) || xCoordStart == 58) {
					if (map.get(keys).toString().contains("#") && xCoordStart != 58) {
						return false;
					}

					xCoordStart++;

					if (xCoordStart == (char) (Coordinate.charAt(1) + 2)) {
						yCoordStart++;
						for (int i = 0; i < 3; i++) {
							if (xCoordStart == '0') {
								break;
							}
							xCoordStart--;
						}
					}

				}

				if (yCoordStart == (char) (Coordinate.charAt(0) + size + 1) || yCoordStart == 'K') {
					break;
				}
			}

		}

		return true;
	}

	// Metod som undersöker om spelaren skjuter på brädet
	public boolean onBoard(String Coordinate) {
		if (Coordinate.charAt(0) < 'A' || Coordinate.charAt(0) > 'J') {
			return false;
		}
		if (Coordinate.charAt(1) < '0' || Coordinate.charAt(1) > '9') {
			return false;
		}
		if (String.valueOf(Coordinate).length() != 2) {
			return false;
		}
		return true;
	}

	public boolean position(String alignment) {
		if (alignment.equals("h")) {
			return true;
		} else {
			return false;
		}
	}

	// Metod som placerar ut båtarna
	public void placeBoats() {
		printBoard();
		for (Boat boats : Boat.boats) {
			System.out.println("Where would you like to place the " + boats.getName() + "?");
			String boatPosition = scan.nextLine();

			System.out.println("h/v?");
			String boatAlignment = scan.nextLine();
			while (!(boatAlignment.equals("h") || boatAlignment.equals("v"))) {
				System.out.println("h/v?");
				boatAlignment = scan.nextLine();
			}

			while (checkBoard(boatPosition, position(boatAlignment), boats.getSize()) != true
					|| onBoard(boatPosition) != true) {
				System.out.println("Where would you like to place " + boats.getName() + "?");
				boatPosition = scan.nextLine();

				System.out.println("h/v?");
				boatAlignment = scan.nextLine();
			}
			addBoatToBoard(boatPosition, position(boatAlignment), boats.getSize(), boats.getName());
			printBoard();
		}
	}

	// Metod som placerar ut båtarna på slumpmässiga koordinater
	public void computerPlaceBoats() {
		for (Boat boats : Boat.boats) {
			String boatPosition = getRandomCoordinate();
			boolean boatAlignment = getRandomAlignment();

			while ((checkBoard(boatPosition, boatAlignment, boats.getSize()) != true)) {
				boatPosition = getRandomCoordinate();
				boatAlignment = getRandomAlignment();
			}

			addBoatToBoard(boatPosition, boatAlignment, boats.getSize(), boats.getName());
		}
	}

	// Metod som returnerar en slumpmässig koordinat
	public String getRandomCoordinate() {
		double xmin = 0;
		double xmax = 9;
		int xCoord = (int) ((Math.random() * ((xmax - xmin) + 1)) + xmin);

		double ymin = 65;
		double ymax = 74;
		char yCoord = (char) ((Math.random() * ((ymax - ymin) + 1)) + ymin);

		String coordinate = "" + yCoord + xCoord;

		return coordinate;
	}

	// 50/50 boolean. Math.random() genererar en double mellan 0 och 1, beroende på
	// om den är över eller under 0.5 returnerar den true eller false.
	public boolean getRandomAlignment() {
		return Math.random() < 0.5;
	}
}