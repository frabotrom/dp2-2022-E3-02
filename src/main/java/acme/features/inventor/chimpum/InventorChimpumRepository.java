package acme.features.inventor.chimpum;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.entities.Chimpum;
import acme.entities.Item;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorChimpumRepository extends AbstractRepository {
	
	// No se mira que los items sean tools o visibles. En ese caso añadir i.type = 0 y i.visible = 1, respectivamente
	
	@Query("select i.chimpum from Item i where i.chimpum.id = :id")
	Chimpum findChimpumById(@Param("id") int id);
	
	@Query("select i.chimpum from Item i where i.inventor.id = :id")
	Collection<Chimpum> findChimpumsByInventorId(@Param("id")int id);
	
	@Query("select i from Item i where i.chimpum.id = :id")
	Item findItemByChimpumId(@Param("id") int id);
	
	@Query("select i from Item i where i.id = :id")
	Item findItemByItemId(@Param("id") int id);
	
	@Query("select i from Item i where i.inventor.id = :id")
	Collection<Item> findItemsByInventorId(@Param("id") int id);
	
	@Query("select i from Item i where i.id = :id and i.type = 0")
	Item findToolByItemId(@Param("id") int id);
}