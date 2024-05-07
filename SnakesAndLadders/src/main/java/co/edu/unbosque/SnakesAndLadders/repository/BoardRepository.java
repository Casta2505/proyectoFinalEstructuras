package co.edu.unbosque.SnakesAndLadders.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.SnakesAndLadders.model.Board;

public interface BoardRepository extends CrudRepository<Board, Integer> {

	public List<Board> findAll();
}
