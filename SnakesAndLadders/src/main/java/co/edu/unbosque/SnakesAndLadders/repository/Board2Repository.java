package co.edu.unbosque.SnakesAndLadders.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.SnakesAndLadders.model.Board2;

public interface Board2Repository extends CrudRepository<Board2, Integer> {

	public List<Board2> findAll();
}
