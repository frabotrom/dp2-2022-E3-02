package acme.features.inventor.patronageReport;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.entities.Patronagereport;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorPatronageReportRepository extends AbstractRepository {

	@Query("select pr from Patronagereport pr where pr.patronage.id = :id")
	Collection<Patronagereport> findPatronageReportByPatronageId(@Param("id") int id);

	@Query("select pr from Patronagereport pr where pr.id = :id")
	Patronagereport findPatronageReportById(@Param("id") int id);
}