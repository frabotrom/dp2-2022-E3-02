package acme.features.patron.patronage;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.entities.Patronage;
import acme.entities.SystemConfiguration;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface PatronPatronageRepository extends AbstractRepository{
	
	@Query("select p from Patronage p where p.id = :id")
	Patronage findOnePatronageById(@Param("id")int id);
	
	@Query("select p from Patronage p where p.patron.id = :patronid")
	Collection<Patronage> findMyPatronages(@Param("patronid")Integer patronid);
	
	@Query("SELECT c FROM SystemConfiguration c")
	SystemConfiguration getSystemConfiguration();

}
