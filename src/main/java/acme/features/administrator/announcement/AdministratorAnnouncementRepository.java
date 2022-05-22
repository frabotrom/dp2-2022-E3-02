package acme.features.administrator.announcement;


import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.SystemConfiguration;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorAnnouncementRepository extends AbstractRepository {

	@Query("SELECT c FROM SystemConfiguration c")
	SystemConfiguration getSystemConfiguration();

}