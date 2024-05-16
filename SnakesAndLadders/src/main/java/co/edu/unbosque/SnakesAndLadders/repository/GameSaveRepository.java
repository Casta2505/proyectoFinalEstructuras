package co.edu.unbosque.SnakesAndLadders.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.SnakesAndLadders.model.GameSave;

public interface GameSaveRepository extends CrudRepository<GameSave, Integer>{
	public Optional<GameSave> findById(Integer id);
}
