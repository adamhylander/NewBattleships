package battleships;

public class Coordinate {

	String text;

	// Constructor som sätter variabeln text till den String text som skickas in
	public Coordinate(String text) {
		this.text = text;
	}

	// Returnerar koordinat
	public String toString() {
		return text;
	}

	// Skapar hashcode av koordinat
	public int hashCode() {
		return text.hashCode();
	}

	// Undersöker om det som skickas in är en koordinat
	public boolean equals(Object obj) {
		if (!(obj instanceof Coordinate)) {
			return false;
		}
		Coordinate coordinates = (Coordinate) obj;
		if (this.text.equals(coordinates.text)) {
			return true;
		}
		return false;
	}

}