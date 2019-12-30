package battleships;

import java.util.*;

public class Player {
	int turn;
	int health;
	String nickname;
	LinkedList<Boats> playerBoats;
	LinkedHashMap<Coordinates, Pieces> playerBoard;
	LinkedHashMap<Coordinates, Pieces> enemyBoard;
	
	public Player(int turn, int health, String nickname, LinkedHashMap<Coordinates, Pieces> playerBoard, LinkedHashMap<Coordinates, Pieces> enemyBoard, LinkedList<Boats> playerBoats) {
		this.turn = turn;
		this.health = health;
		this.nickname = nickname;
		this.playerBoats = playerBoats;
		this.playerBoard = playerBoard;
		this.enemyBoard = enemyBoard;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public int getHealth() {
		return health;
	}

	public int getTurn() {
		return turn;
	}
	
	public void setTurn(int turn) {
		this.turn = turn;
	}
	
	public LinkedList<Boats> getBoats() {
		return playerBoats;
	}
	
	public void setBoats(LinkedList<Boats> b) {
		this.playerBoats = b;
	}
	
	public LinkedHashMap<Coordinates, Pieces> getPlayerBoard() {
		return playerBoard;
	}
	
	public void setPlayerBoard (LinkedHashMap<Coordinates,Pieces> playerBoard) {
		this.playerBoard = playerBoard;
	}
	
	public LinkedHashMap<Coordinates, Pieces> getEnemyBoard() {
		return enemyBoard;
	}
	
	public void setEnemyBoard (LinkedHashMap<Coordinates,Pieces> enemyBoard) {
		this.enemyBoard = enemyBoard;
	}
	
	public String toString() {
		return ("Name: " + this.nickname + ", Health: " + this.health + ", Turn:" + this.turn);
	}
}
