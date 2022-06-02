package acme.features.inventor.amount;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.entities.Amount;
import acme.entities.Item;
import acme.entities.Toolkit;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorAmountRepository extends AbstractRepository{

	@Query("SELECT i FROM Item i WHERE i.visible = true")
	List<Item> findVisibleItems();
	
	@Query("SELECT i FROM Item i WHERE i.visible = false AND i.inventor = :id")
	List<Item> findNonVisibleItemsByInventorId(@Param("id") int id);
	
	@Query("SELECT t FROM Toolkit t WHERE t.id = :id")
	Toolkit findToolkitById(@Param("id") int id);

	@Query("SELECT a FROM Amount a WHERE a.id = :id")
	Amount findAmountById(@Param("id") int id);

	@Query("SELECT a FROM Amount a WHERE a.toolkit.id = :id")
	Collection<Amount> findAmountsByToolkitId(@Param("id") int id);

	@Query("SELECT a.item FROM Amount a WHERE a.id = :id")
	Item findItemByAmountId(@Param("id") int id);

	@Query("SELECT i FROM Item i WHERE i.name = :itemName")
	Item findItemByName(@Param("itemName") String itemName);

	@Query("SELECT a.toolkit FROM Amount a WHERE a.id = :id")
	Toolkit findToolkitByAmountId(@Param("id") int id);
}