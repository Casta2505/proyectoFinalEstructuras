package co.edu.unbosque.SnakesAndLadders.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.SnakesAndLadders.util.graph.Vertex;

public interface VertexRepository extends CrudRepository<Vertex, Integer> {
	Optional<Vertex> findByPosition(Integer position);
}
