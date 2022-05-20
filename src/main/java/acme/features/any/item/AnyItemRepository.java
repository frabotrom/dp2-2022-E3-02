package acme.features.any.item;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.entities.Item;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyItemRepository extends AbstractRepository {

	@Query("select i from Item i where i.id = :id")
	Item findOneItemById(@Param("id") int id);
	
	@Query("select c from Item c where c.type = 1 and c.visible = 1")
	Collection<Item> findAllVisibleComponents();
	
	@Query("select t from Item t where t.type = 0")
	Collection<Item> findAllTools();

	@Query("select t from Item t where t.type = 0 and t.visible = 1")
	Collection<Item> findAllVisibleTools();
	
	@Query("select a.item from Amount a WHERE a.toolkit.id = :toolkitId")
	Collection<Item> findAllComponentsByToolkitId(@Param("toolkitId") int toolkitId);
	
}
