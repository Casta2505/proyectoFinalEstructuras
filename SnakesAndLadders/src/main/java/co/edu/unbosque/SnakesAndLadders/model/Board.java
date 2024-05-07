package co.edu.unbosque.SnakesAndLadders.model;

import co.edu.unbosque.SnakesAndLadders.util.linkedlist.MyLinkedList;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Lob
	@Column(columnDefinition = "LONGBLOB")
	private byte[] graphData;
	private MyLinkedList<Components> snakes;
	private MyLinkedList<Components> ladders;
	private int height;
	private int width;

	public MyLinkedList<Components> getSnakes() {
		return snakes;
	}

	public void setSnakes(MyLinkedList<Components> snakes) {
		this.snakes = snakes;
	}

	public MyLinkedList<Components> getLadders() {
		return ladders;
	}

	public void setLadders(MyLinkedList<Components> ladders) {
		this.ladders = ladders;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public byte[] getGraphData() {
		return graphData;
	}

	public void setGraphData(byte[] graphData) {
		this.graphData = graphData;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

}
