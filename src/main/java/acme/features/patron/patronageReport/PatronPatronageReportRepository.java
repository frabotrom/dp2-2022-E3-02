package acme.features.patron.patronageReport;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.entities.Patronagereport;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface PatronPatronageReportRepository extends AbstractRepository{
	
	@Query("select p from PatronageReport p where p.id = :id")
	Patronagereport findOnePatronageReportById(@Param("id") int id);
	
	@Query("select p from PatronageReport p where p.patron.id = :patronId")
	Collection<Patronagereport> findManyPatronageReportsByPatronId(@Param("patronId") int patronId);
}
