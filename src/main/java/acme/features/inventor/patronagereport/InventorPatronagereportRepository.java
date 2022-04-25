package acme.features.inventor.patronagereport;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.entities.Patronagereport;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorPatronagereportRepository extends AbstractRepository {

	@Query("select pr from Patronagereport pr where pr.patronage.id = :id")
	Collection<Patronagereport> findPatronageReportByPatronageId(@Param("id") int id);

	@Query("select pr from Patronagereport pr where pr.id = :id")
	Patronagereport findPatronageReportById(@Param("id") int id);
	
	@Query("select pr from Patronagereport pr where pr.patronage.inventor.id = :id")
	Collection<Patronagereport> findPatronageReportsByInventorId(@Param("id")int id);
}