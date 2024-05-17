package co.edu.unbosque.SnakesAndLadders.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.SnakesAndLadders.model.GameSave;

public interface GameSaveRepository extends CrudRepository<GameSave, Integer>{
	public Optional<GameSave> findById(Integer id);
	public List<GameSave> findAll();
	public Optional<GameSave> findByBoard(byte[] board);
}
