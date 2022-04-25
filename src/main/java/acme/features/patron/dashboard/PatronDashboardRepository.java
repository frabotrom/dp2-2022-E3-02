package acme.features.patron.dashboard;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface PatronDashboardRepository extends AbstractRepository {
	
	// Totals -----------------------------------------------------------------------------------------------------
	
	@Query("select count(p) from Patronage p where p.patron.id = :id and p.status = 0")
	Integer totalProposedPatronages(@Param("id") int id);
	
	@Query("select count(p) from Patronage p where p.patron.id = :id and p.status = 1")
	Integer totalAcceptedPatronages(@Param("id") int id);
	
	@Query("select count(p) from Patronage p where p.patron.id = :id and p.status = 2")
	Integer totalDeniedPatronages(@Param("id") int id);
	
	// Proposed -----------------------------------------------------------------------------------------------------
	
	@Query("select p.budget.currency, avg(p.budget.amount) from Patronage p where p.patron.id = :id and p.status = 0 group by p.budget.currency")
	List<List<String>> averageBudgetProposed(@Param("id") int id);
	
	@Query("select p.budget.currency, stddev(p.budget.amount) from Patronage p where p.patron.id = :id and p.status = 0 group by p.budget.currency")
	List<List<String>> deviationBudgetProposed(@Param("id") int id);
	
	@Query("select p.budget.currency, min(p.budget.amount) from Patronage p where p.patron.id = :id and p.status = 0 group by p.budget.currency")
	List<List<String>> minimunBudgetProposed(@Param("id") int id);
	
	@Query("select p.budget.currency, max(p.budget.amount) from Patronage p where p.patron.id = :id and p.status = 0 group by p.budget.currency")
	List<List<String>> maximunBudgetProposed(@Param("id") int id);
	
	// Accepted -----------------------------------------------------------------------------------------------------
	
	@Query("select p.budget.currency, avg(p.budget.amount) from Patronage p where p.patron.id = :id and p.status = 1 group by p.budget.currency")
	List<List<String>> averageBudgetAccepted(@Param("id") int id);
		
	@Query("select p.budget.currency, stddev(p.budget.amount) from Patronage p where p.patron.id = :id and p.status = 1 group by p.budget.currency")
	List<List<String>> deviationBudgetAccepted(@Param("id") int id);
		
	@Query("select p.budget.currency, min(p.budget.amount) from Patronage p where p.patron.id = :id and p.status = 1 group by p.budget.currency")
	List<List<String>> minimunBudgetAccepted(@Param("id") int id);
		
	@Query("select p.budget.currency, max(p.budget.amount) from Patronage p where p.patron.id = :id and p.status = 1 group by p.budget.currency")
	List<List<String>> maximunBudgetAccepted(@Param("id") int id);
	
	// Denied -----------------------------------------------------------------------------------------------------
	
	@Query("select p.budget.currency, avg(p.budget.amount) from Patronage p where p.patron.id = :id and p.status = 2 group by p.budget.currency")
	List<List<String>> averageBudgetDenied(@Param("id") int id);
			
	@Query("select p.budget.currency, stddev(p.budget.amount) from Patronage p where p.patron.id = :id and p.status = 2 group by p.budget.currency")
	List<List<String>> deviationBudgetDenied(@Param("id") int id);
			
	@Query("select p.budget.currency, min(p.budget.amount) from Patronage p where p.patron.id = :id and p.status = 2 group by p.budget.currency")
	List<List<String>> minimunBudgetDenied(@Param("id") int id);
			
	@Query("select p.budget.currency, max(p.budget.amount) from Patronage p where p.patron.id = :id and p.status = 2 group by p.budget.currency")
	List<List<String>> maximunBudgetDenied(@Param("id") int id);

}
