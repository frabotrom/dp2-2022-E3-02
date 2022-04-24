package acme.features.any.chirp;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.entities.Chirp;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyChirpRepository extends AbstractRepository {
	
	@Query("select c from Chirp c where c.creationDate > :deadline")
	Collection<Chirp> findAllRecentChirps(@Param("deadline") Date deadline);

}