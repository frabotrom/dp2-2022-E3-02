package acme.features.inventor.item;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.entities.Item;
import acme.entities.SystemConfiguration;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;

@Repository
public interface InventorComponentRepository extends AbstractRepository{

	@Query("select i from Item i where i.type = 1 and i.inventor.id = :inventorId")
	Collection<Item> findComponentsByInventorId(@Param("inventorId") int inventorId);

	@Query("select i from Item i where i.id = :id and i.type = 1")
	Item findComponentById(@Param("id") int id);
	
	@Query("select inventor from Inventor inventor WHERE inventor.id=:id")
	Optional<Inventor> findInventorById(@Param("id") int id);
	
	@Query("select item from Item item where item.code=:code")
	Optional<Item> findItemByCode(@Param("code") String code);

	@Query("select sc from SystemConfiguration sc")
	SystemConfiguration findSystemConfiguration();
	
}
