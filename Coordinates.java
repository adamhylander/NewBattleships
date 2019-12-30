package battleships;

public class Coordinates {
	String text;
	
	public Coordinates(String text) {
		this.text = text;
	}
	
	public String toString() {
		return text;
	}
	
	public int hashCode() {
		return text.hashCode();
	}
	
	public boolean equals(Object obj) {
		if(!(obj instanceof Coordinates)) {
			return false;
		}
		Coordinates coordinates = (Coordinates) obj;
		if(this.text.equals(coordinates.text)) {
			return true;
		}
			return false;
	}
	
}