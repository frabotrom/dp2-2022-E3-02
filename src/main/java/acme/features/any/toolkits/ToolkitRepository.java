package acme.features.any.toolkits;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.entities.Toolkit;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ToolkitRepository extends AbstractRepository {

	@Query("select i from Toolkit i where i.id = :id")
	Toolkit findOneToolkitById(@Param("id") int id);

	@Query("select t from Toolkit t where t.draftMode=false")
	Collection<Toolkit> findAllToolkits();
	
}
