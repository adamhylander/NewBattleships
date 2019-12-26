package battleships;

import java.util.*;		

public class Boats {
	int size;
	int health;
	String name;
	LinkedList<Coordinates>  boatCoordinates = new LinkedList<Coordinates>();
	List<Boats> boats = new ArrayList<Boats>();
	

	public Boats(int size, int health, String name, LinkedList<Coordinates> boatCoordinates) {
		this.size = size;
		this.health = health;
		this.name = name;
		this.boatCoordinates = boatCoordinates;
	}
	
	public void addBoat(Boats b) {
		boats.add(b);
	}
	
	public List<Boats> boats() {
		return boats;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public LinkedList<Coordinates> getBoatCoordinates() {
		return boatCoordinates;
	}
	
	public void setBoatCoordinates(LinkedList<Coordinates> boatCoordinates) {
		this.boatCoordinates = boatCoordinates;
	}
}
