
package acme.features.patron.patronage;


import java.util.Collection;
import java.util.List;

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

	@Query("SELECT p FROM Patronage p WHERE p.patron.id = :patronId")
	Collection<Patronage> findPatronagesByPatron(@Param("patronId")Integer patronId);
	
	@Query("SELECT p FROM Patronage p WHERE p.id = :id")
	Patronage findOnePatronageById(@Param("id")int id);

	@Query("SELECT c FROM SystemConfiguration c")
	SystemConfiguration getSystemConfiguration();
	
	@Query("SELECT patron from Patron patron where patron.id = :id")
	Patron findPatronById(@Param("id")int id);
	
	@Query("SELECT p FROM Patronage p WHERE p.code = :code")
	Patronage findOnePatronageByCode(@Param("code")String code);
	
	@Query("SELECT ac.acceptedCurrencies from SystemConfiguration ac")
	String findAcceptedCurrencies();
	
	@Query("SELECT inventor from Inventor inventor where inventor.userAccount.username = :username")
	Inventor findInventorByUsername(@Param("username")String username);
	
	@Query("SELECT inventor from Inventor inventor")
	List<Inventor> findAllInventors();
	
	@Query("SELECT patronage from Patronage patronage where patronage.id = :id")
	Patronage findPatronageById(@Param("id")int id);

	@Query("select pr from Patronagereport pr where pr.patronage.id = :patronageid")
	Collection<Patronagereport> findReportsByPatronageId(@Param("patronageid")int patronageid);
}