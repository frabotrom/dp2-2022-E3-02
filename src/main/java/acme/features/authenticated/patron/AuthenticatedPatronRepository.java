package acme.features.authenticated.patron;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedPatronRepository extends AbstractRepository{

	@Query("select ua from UserAccount ua where ua.id = :id")
	UserAccount findUserAccountById(@Param("id")int id);

}