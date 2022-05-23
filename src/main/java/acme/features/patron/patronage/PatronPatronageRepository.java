
package acme.features.patron.patronage;


import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.entities.Patronage;
import acme.entities.Patronagereport;
import acme.entities.SystemConfiguration;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;
import acme.roles.Patron;

@Repository
public interface PatronPatronageRepository extends AbstractRepository {

	@Query("SELECT p FROM Patronage p WHERE p.id = :id")
	Patronage findOnePatronageById(@Param("id")int id);
	
	@Query("SELECT p FROM Patronage p WHERE p.code LIKE :code")
	Patronage findOnePatronageByCode(@Param("code")String code);
	
	@Query("SELECT p FROM Patronage p WHERE p.patron.id = :patronid")
	Collection<Patronage> findMyPatronages(@Param("patronid")Integer patronid);
	
    @Query("select i from Inventor i")
    Collection<Inventor> findInventors();

    @Query("select inventor from Inventor inventor WHERE inventor.id=:id")
    Optional<Inventor> findInventorById(@Param("id")int id);
	
    @Query("select patron from Patron patron WHERE patron.id=:id")
    Optional<Patron> findPatronById(@Param("id")int id);
    
	@Query("select sc from SystemConfiguration sc")
	SystemConfiguration findSystemConfiguration();
	
	@Query("select c.systemCurrency from SystemConfiguration c")
	String systemCurrency();
	
	@Query("select pr from  Patronagereport pr where pr.patronage.id = :patronageId")
	Collection<Patronagereport> findManyPatronageReportsByPatronageId(@Param("patronageId")int patronageId);
}