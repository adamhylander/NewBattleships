package battleships;

import java.util.*;		

public class Boats {
	String name;
	LinkedList<Coordinates>  boatCoordinates = new LinkedList<Coordinates>();
	List<Boats> boats = new ArrayList<Boats>();
	

	public Boats(String name, LinkedList<Coordinates> boatCoordinates) {
		this.name = name;
		this.boatCoordinates = boatCoordinates;
	}
	
	public void addBoat(Boats b) {
		boats.add(b);
	}
	
	public List<Boats> boats() {
		return boats;
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
