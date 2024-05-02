package co.edu.unbosque.SnakesAndLadders.repository;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.SnakesAndLadders.util.graph.Edge;

public interface EdgeRepository extends CrudRepository<Edge, Integer>{
	
}
