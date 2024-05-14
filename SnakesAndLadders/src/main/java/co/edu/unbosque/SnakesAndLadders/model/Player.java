package co.edu.unbosque.SnakesAndLadders.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Player implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	private int playerOrder;
	private String piece;
	private int boardPosition;
	@ManyToOne
	private Game game;

	public Player() {
		
	}

	public Player(String name, int boardPosition, boolean turn, Game game, int playerOrder) {
		super();
		this.name = name;
		this.boardPosition = boardPosition;
		this.game = game;
		this.playerOrder = playerOrder;
	}

	public int getOrder() {
		return playerOrder;
	}

	public void setOrder(int playerOrder) {
		this.playerOrder = playerOrder;
	}

	public Integer getId() {
		return id;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPiece() {
		return piece;
	}

	public void setPiece(String piece) {
		this.piece = piece;
	}

	public int getBoardPosition() {
		return boardPosition;
	}

	public void setBoardPosition(int boardPosition) {
		this.boardPosition = boardPosition;
	}
}
