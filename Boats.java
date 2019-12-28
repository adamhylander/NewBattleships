package battleships;

import java.util.*;	

public class Boats {
	String name;
	int size;
	LinkedList<Coordinates>  boatCoordinates = new LinkedList<Coordinates>();
	static LinkedList<Boats> boats = new LinkedList<Boats>();

	public Boats(String name, int size, LinkedList<Coordinates> boatCoordinates) {
		this.name = name;
		this.size = size;
		this.boatCoordinates = boatCoordinates;
	}
	
	public static void addBoat(Boats b) {
		boats.add(b);
	}
	
	
	public void clearList() {
		LinkedList<Coordinates> temporary = new LinkedList<Coordinates>();
			setBoatCoordinates(temporary);
	}
	
	public static LinkedList<Boats> boats() {
		return boats;
	}
	
	public String toString() {
		if(this.boatCoordinates == null) {
			return ("Name: " + this.name + ", Size: " + this.size);	
		}
		else {
			return ("Name: " + this.name + ", Size: " + this.size + ", Coordinates: " + this.boatCoordinates);
		}
	}
		
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
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
	
	public void setBoatCoordinates(LinkedList<Coordinates> boatCoordinates) {
		this.boatCoordinates = boatCoordinates;
	}
	
	public static void printBoatList(LinkedList<Boats> c) {
		System.out.println("\n");
		System.out.println("Here are the boats in each player's arsenal:");
		for(Boats boats : c) {
			System.out.println(boats);
		}
		System.out.println("\n");
	}
}
