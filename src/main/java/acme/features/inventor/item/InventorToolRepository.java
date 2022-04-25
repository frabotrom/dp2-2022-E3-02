package acme.features.inventor.item;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.entities.Item;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorToolRepository extends AbstractRepository{

	
	@Query("select i from Item i where i.id = :id and i.type = 0")
	Item findOneToolById(@Param("id") int id);

	@Query("select i from Item i where i.inventor.id = :inventorId and i.type = 0")
	Collection<Item> findManyToolsByInventorId(@Param("inventorId") int inventorId);
}
