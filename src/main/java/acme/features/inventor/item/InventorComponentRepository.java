package acme.features.inventor.item;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Item;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorComponentRepository extends AbstractRepository{

	@Query("select i from Item i where i.type = 1 and i.inventor.id = :inventorId")
	Collection<Item> findComponentsByInventorId(int inventorId);

	@Query("select i from Item i where i.id = :id and i.type = 1")
	Item findComponentById(int id);
	
}
