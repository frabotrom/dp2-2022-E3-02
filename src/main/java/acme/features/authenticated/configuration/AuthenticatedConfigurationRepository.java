package acme.features.authenticated.configuration;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.SystemConfiguration;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedConfigurationRepository extends AbstractRepository {
	
	@Query("select c from SystemConfiguration c")
	SystemConfiguration getSystemConfiguration();
}
