package co.edu.unbosque.SnakesAndLadders.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.SnakesAndLadders.model.Player;

public interface PlayerRepository extends CrudRepository<Player, Integer>{
	Optional<Player> findById(Integer id);
}
