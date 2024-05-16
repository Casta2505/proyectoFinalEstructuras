package co.edu.unbosque.SnakesAndLadders.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class GameSave implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private int playerNum;
	private String difficulty;
	private String theme;
	private int diceNumber;
	@Column(columnDefinition = "LONGBLOB")
	private byte[] board;
	@Column(columnDefinition = "LONGBLOB")
	private byte[] playerTurn;
	@Column(columnDefinition = "LONGBLOB")
	private byte[] players;
	public int getPlayerNum() {
		return playerNum;
	}
	public void setPlayerNum(int playerNum) {
		this.playerNum = playerNum;
	}
	public String getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public int getDiceNumber() {
		return diceNumber;
	}
	public void setDiceNumber(int diceNumber) {
		this.diceNumber = diceNumber;
	}
	public byte[] getBoard() {
		return board;
	}
	public void setBoard(byte[] board) {
		this.board = board;
	}
	public byte[] getPlayerTurn() {
		return playerTurn;
	}
	public void setPlayerTurn(byte[] playerTurn) {
		this.playerTurn = playerTurn;
	}
	public byte[] getPlayers() {
		return players;
	}
	public void setPlayers(byte[] players) {
		this.players = players;
	}

}
