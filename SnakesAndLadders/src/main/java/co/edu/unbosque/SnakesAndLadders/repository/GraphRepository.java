package co.edu.unbosque.SnakesAndLadders.repository;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.SnakesAndLadders.util.graph.Graph;

public interface GraphRepository extends CrudRepository<Graph, Integer>{
	
}
