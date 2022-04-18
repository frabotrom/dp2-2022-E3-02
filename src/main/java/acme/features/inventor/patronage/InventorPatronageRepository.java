package acme.features.inventor.patronage;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.entities.Patronage;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorPatronageRepository extends AbstractRepository {
	
	@Query("select p from Patronage p where p.id = :id")
	Patronage findOnePatronageById(@Param("id") int id);
	
	@Query("select p from Patronage p where p.inventor.id = :inventorId")
	Collection<Patronage> findManyPatronagesByInventorId(@Param("inventorId") int inventorId);

}
