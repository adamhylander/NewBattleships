package battleships;

public class Pieces {
	char text;
	public Pieces(char text) {
		this.text = text;
	}
	
	public char toChar() {
		return text;
	}
	
	public String toString() {
		String textToString = "";
		textToString = "" + text;
		return textToString;
	}
	
	public int hashCode() {
		String textToString = "";
		textToString = "" + text;
		return textToString.hashCode();
	}
	
	public boolean equals(Object obj) {
		if(!(obj instanceof Pieces)) {
			return false;
		}
		Pieces objWord = (Pieces) obj;
		if(this.text == (objWord.text)) {
			return true;
		}
			return false;
	}

}

