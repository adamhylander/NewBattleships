package battleships;

public class Piece {
	
	char text;
	
	//Constructor som sätter variabeln text till den String text som skickas in
	public Piece(char text) {
		this.text = text;
	}
	
	//Returnerar tecknet som skickats in som en char
	public char toChar() {
		return text;
	}
	
	//Returnerar tecknet som skickats in plus ett mellanrum mellan varje ruta
	public String toString() {
		String textToString = "";
		textToString = "" + text;
		return textToString;
	}
	
	//Returnerar hashcoden för den text som skickats in beroende på vad det är för tecken + ett mellanrum
	public int hashCode() {
		String textToString = "";
		textToString = "" + text;
		return textToString.hashCode();
	}
	
	//Undersöker om tecknet som skickats in är en "Piece"
	public boolean equals(Object obj) {
		if(!(obj instanceof Piece)) {
			return false;
		}
		Piece pieces = (Piece) obj;
		if(this.text == (pieces.text)) {
			return true;
		}
			return false;
	}

}