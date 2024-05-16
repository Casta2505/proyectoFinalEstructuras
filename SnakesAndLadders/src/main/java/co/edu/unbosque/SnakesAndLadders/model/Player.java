package co.edu.unbosque.SnakesAndLadders.model;

import java.io.Serializable;

public class Player implements Serializable {
	private static final long serialVersionUID = -5925284575093478673L;
	private String name;
	private int playerOrder;
	private String piece;
	private int boardPosition;

	public Player() {

	}

	public Player(String name, int boardPosition, int playerOrder) {
		this.name = name;
		this.boardPosition = boardPosition;
		this.playerOrder = playerOrder;
	}

	public int getOrder() {
		return playerOrder;
	}

	public void setOrder(int playerOrder) {
		this.playerOrder = playerOrder;
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
