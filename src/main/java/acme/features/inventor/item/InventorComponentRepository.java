package acme.features.inventor.item;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.entities.Item;
import acme.entities.Toolkit;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorComponentRepository extends AbstractRepository{

	@Query("select i from Item i where i.type = 1 and i.inventor.id = :inventorId")
	Collection<Item> findComponentsByInventorId(@Param("inventorId") int inventorId);

	@Query("select i from Item i where i.id = :id")
	Item findItemById(@Param("id") int id);
	
	@Query("select a.item from Amount a WHERE a.toolkit.id = :toolkitId")
	Collection<Item> findAllItemsByToolkitId(@Param("toolkitId") int toolkitId);
	
	@Query("select t from Toolkit t where t.id = :toolkitId")
	Toolkit findToolkitById(@Param("toolkitId") int toolkitId);
	
}
