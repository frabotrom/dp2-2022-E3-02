package acme.features.inventor.toolkits;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.entities.Toolkit;

@Repository
public interface InventorToolkitsRepository {
	
	@Query("select t from Toolkit t where t.id = :id")
	Toolkit findOneToolkitById(@Param("id") int id);
	
	@Query("select t from Toolkit t where t.inventor.id = :inventorId")
	Collection<Toolkit> findManyToolkitsByInventorId(@Param("inventorId") int inventorId);
	
}
