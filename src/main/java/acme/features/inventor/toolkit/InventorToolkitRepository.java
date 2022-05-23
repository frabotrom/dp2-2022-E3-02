package acme.features.inventor.toolkit;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.entities.Amount;
import acme.entities.Toolkit;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;

@Repository
public interface InventorToolkitRepository extends AbstractRepository {

	@Query("select i from Toolkit i where i.id = :id")
	Toolkit findOneToolkitById(@Param("id") int id);

	@Query("select t from Toolkit t where t.inventor.id = :inventorId")
	Collection<Toolkit> findToolkitsByInventorId(@Param("inventorId") int inventorId);
	
	@Query("SELECT inventor.id FROM Inventor inventor")
	Collection<Integer> findAllInventorId();
	
	@Query("SELECT inventor FROM Inventor inventor WHERE inventor.id = :id")
	Inventor findInventorById(@Param("id") int id);
	
	@Query("SELECT t.inventor FROM Toolkit t WHERE t.id = :id")
	Inventor findInventorByToolkitId(@Param("id") int id);
	
	@Query("select toolkit.code from Toolkit toolkit")
	Collection<String> findAllToolkitCodes();
	
	@Query("SELECT a FROM Amount a WHERE a.toolkit.id = :id")
	List<Amount> findAmountsByToolkitId(@Param("id") int id);
	
	@Query("SELECT SUM(a.item.retailPrice * a.total) FROM Amount a WHERE a.toolkit.id = :id")
	Optional <Double> findToolkitPrice(@Param("id") int id);
	
	@Modifying
	@Query("DELETE FROM Amount a WHERE a.toolkit.id = :id")
	void deleteAmountByToolkitId(@Param("id") int id);
	
}
