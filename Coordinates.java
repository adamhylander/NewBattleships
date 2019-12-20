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
		Coordinates objWord = (Coordinates) obj;
		if(this.text.equals(objWord.text)) {
			return true;
		}
			return false;
	}
	
}