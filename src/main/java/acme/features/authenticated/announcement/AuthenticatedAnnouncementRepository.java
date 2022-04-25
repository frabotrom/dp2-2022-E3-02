package acme.features.authenticated.announcement;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.entities.Announcement;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedAnnouncementRepository extends AbstractRepository {
	
	@Query("select a from Announcement a where a.id = :id")
	Announcement findOneAnnouncementById(@Param("id") int id);
	
	@Query("select a from Announcement a where a.creationDate > :deadline")
	Collection<Announcement> findRecentAnnouncements(@Param("deadline") Date deadline);

}
