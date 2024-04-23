package co.edu.unbosque.SnakesAndLadders.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.SnakesAndLadders.model.Game;

public interface GameRepository extends CrudRepository<Game, Integer>{
	public Optional<Game> findById(Integer id);
}
