
package acme.features.patron.patronageReports;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.entities.Patronagereport;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface PatronPatronageReportRepository extends AbstractRepository {

	@Query("select pr from Patronagereport pr where pr.patronage.patron.id = :id")
	Collection<Patronagereport> findAllPatronageReportsByPatronId(@Param("id")int id);

	@Query("select pr from Patronagereport pr where pr.id = :id")
	Patronagereport findOnePatronageReportById(@Param("id")int id);

}
