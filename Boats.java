package battleships;

import java.util.*;	

public class Boats {
	final static int destroyerSize = 2;			//Tre stycken
	final static int cruiserSize = 3;			//Två stycken
	final static int battleshipSize = 4;		//Två stycken
	final static int carrierSize = 5;			//En stycken
	String name;
	int size;
	LinkedList<Coordinates>  boatCoordinates;
	static List<Boats> boats = Arrays.asList(
//			(new Boats("Carrier", carrierSize, null)), 
//			(new Boats("Battleship 1", battleshipSize, null)), 
//			(new Boats("Battleship 2", battleshipSize, null)),
//			(new Boats("Cruiser 1", cruiserSize, null)),
//			(new Boats("Cruiser 2", cruiserSize, null)),
//			(new Boats("Destroyer 1", destroyerSize, null)),
//			(new Boats("Destroyer 2", destroyerSize, null)),
			(new Boats("Destroyer 3", destroyerSize, null)));

	public Boats(String name, int size, LinkedList<Coordinates> boatCoordinates) {
		this.name = name;
		this.size = size;
		this.boatCoordinates = boatCoordinates;
	}
	
	public String toString() {
		if(this.boatCoordinates == null) {
			return ("Name: " + this.name + ", Size: " + this.size);	
		}
		else {
			return this.boatCoordinates.toString();
		}
	}
		
	public String getName() {
		return name;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public LinkedList<Coordinates> getBoatCoordinates() {
		return boatCoordinates;
	}
	
	public static void printBoatList(List<Boats> boatList) {
		System.out.println("\n");
		
		System.out.println("Here are the boats in each player's arsenal:");
		for(Boats boats : boatList) {
			System.out.println(boats);
		}
		
		System.out.println("\n");
	}
}
