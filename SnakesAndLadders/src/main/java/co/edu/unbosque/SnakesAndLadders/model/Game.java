package co.edu.unbosque.SnakesAndLadders.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import co.edu.unbosque.SnakesAndLadders.util.linkedlist.MyLinkedList;

public class Game implements Serializable{
	private Integer id;
	private static final long serialVersionUID = 6602633130631338389L;
	private int playerNum;
	private String difficulty;
	private String theme;
	private int diceNumber;
	private Board board;
	private Player playerTurn;
	private ArrayList<Player> players;

	public String getTheme() {
		return theme;
	}
	
	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public void setTheme(String theme) {
		this.theme = theme;
	}

	public int getPlayerNum() {
		return playerNum;
	}

	public void setPlayerNum(int playerNum) {
		this.playerNum = playerNum;
	}

	public Player getPlayerTurn() {
		return playerTurn;
	}

	public void setPlayerTurn(Player playerTurn) {
		this.playerTurn = playerTurn;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public int getDiceNumber() {
		return diceNumber;
	}

	public void setDiceNumber(int diceNumber) {
		this.diceNumber = diceNumber;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

}
