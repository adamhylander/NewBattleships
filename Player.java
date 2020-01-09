package battleships;

import java.util.*;

public class Player {
	int shots;
	int turn;
	int health;
	String nickname;
	List<Boat> playerBoats;
	LinkedHashMap<Coordinate, Piece> playerBoard;
	LinkedHashMap<Coordinate, Piece> enemyBoard;
	
	//Constructor som sätter de variabler som skickats in till lokala variabler
	public Player(int turn, int health, String nickname, int shots, LinkedHashMap<Coordinate, Piece> playerBoard, LinkedHashMap<Coordinate, Piece> enemyBoard, List<Boat> playerBoats) {
		this.turn = turn;
		this.health = health;
		this.nickname = nickname;
		this.shots = shots;
		this.playerBoats = playerBoats;
		this.playerBoard = playerBoard;
		this.enemyBoard = enemyBoard;
	}
	
	//Metoder nedan returnerar värdet för olika variabler och tilldelar värden till variabler
	
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
	
	public int getShots() {
		return shots;
	}
	
	public void setShots(int shots) {
		this.shots = shots;
	}
	
	public List<Boat> getBoats() {
		return playerBoats;
	}
	
	public void setBoats(List<Boat> b) {
		this.playerBoats = b;
	}
	
	public LinkedHashMap<Coordinate, Piece> getPlayerBoard() {
		return playerBoard;
	}
	
	public void setPlayerBoard (LinkedHashMap<Coordinate,Piece> playerBoard) {
		this.playerBoard = playerBoard;
	}
	
	public LinkedHashMap<Coordinate, Piece> getEnemyBoard() {
		return enemyBoard;
	}
	
	public void setEnemyBoard (LinkedHashMap<Coordinate,Piece> enemyBoard) {
		this.enemyBoard = enemyBoard;
	}
	
	public String toString() {
		return ("Name: " + this.nickname + ", Health: " + this.health + ", Turn: " + this.turn);
	}
}