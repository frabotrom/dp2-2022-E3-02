package acme.features.administrator.dashboard;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {
	
	// Global -----------------------------------------------------------------------------------------------------
	
	@Query("select count(i) from Item i where i.type = 1")
	Integer totalComponents();
	
	@Query("select count(i) from Item i where i.type = 0")
	Integer totalTools();
	
	@Query("select p.status, count(p) from Patronage p group by p.status")
	List<List<String>> totalPatronages();
	
	// Components -----------------------------------------------------------------------------------------------------
	
	@Query("select i.technology, i.retailPrice.currency, avg(i.retailPrice.amount) from Item i where i.type = 1 group by i.retailPrice.currency, i.technology")
	List<List<String>> averagePriceComponents();
	
	@Query("select i.technology, i.retailPrice.currency, stddev(i.retailPrice.amount) from Item i where i.type = 1 group by i.retailPrice.currency, i.technology")
	List<List<String>> deviationPriceComponents();
	
	@Query("select i.technology, i.retailPrice.currency, min(i.retailPrice.amount) from Item i where i.type = 1 group by i.retailPrice.currency, i.technology")
	List<List<String>> minimunPriceComponents();
	
	@Query("select i.technology, i.retailPrice.currency, max(i.retailPrice.amount) from Item i where i.type = 1 group by i.retailPrice.currency, i.technology")
	List<List<String>> maximunPriceComponents();
	
	// Tools -----------------------------------------------------------------------------------------------------
	
	@Query("select i.retailPrice.currency, avg(i.retailPrice.amount) from Item i where i.type = 0 group by i.retailPrice.currency")
	List<List<String>> averagePriceTools();
	
	@Query("select i.retailPrice.currency, stddev(i.retailPrice.amount) from Item i where i.type = 0 group by i.retailPrice.currency")
	List<List<String>> deviationPriceTools();
	
	@Query("select i.retailPrice.currency, min(i.retailPrice.amount) from Item i where i.type = 0 group by i.retailPrice.currency")
	List<List<String>> minimunPriceTools();
	
	@Query("select i.retailPrice.currency, max(i.retailPrice.amount) from Item i where i.type = 0 group by i.retailPrice.currency")
	List<List<String>> maximunPriceTools();

	// Total Patronages -----------------------------------------------------------------------------------------------------
	
	@Query("select count(p) from Patronage p where p.status = 0")
	Integer totalProposedPatronages();
	
	@Query("select count(p) from Patronage p where p.status = 1")
	Integer totalAcceptedPatronages();
	
	@Query("select count(p) from Patronage p where p.status = 2")
	Integer totalDeniedPatronages();
	
	// Proposed -----------------------------------------------------------------------------------------------------
	
	@Query("select p.budget.currency, avg(p.budget.amount) from Patronage p where p.status = 0 group by p.budget.currency")
	List<List<String>> averageBudgetProposed();
	
	@Query("select p.budget.currency, stddev(p.budget.amount) from Patronage p where p.status = 0 group by p.budget.currency")
	List<List<String>> deviationBudgetProposed();
	
	@Query("select p.budget.currency, min(p.budget.amount) from Patronage p where p.status = 0 group by p.budget.currency")
	List<List<String>> minimunBudgetProposed();
	
	@Query("select p.budget.currency, max(p.budget.amount) from Patronage p where p.status = 0 group by p.budget.currency")
	List<List<String>> maximunBudgetProposed();
	
	// Accepted -----------------------------------------------------------------------------------------------------
	
	@Query("select p.budget.currency, avg(p.budget.amount) from Patronage p where p.status = 1 group by p.budget.currency")
	List<List<String>> averageBudgetAccepted();
		
	@Query("select p.budget.currency, stddev(p.budget.amount) from Patronage p where p.status = 1 group by p.budget.currency")
	List<List<String>> deviationBudgetAccepted();
		
	@Query("select p.budget.currency, min(p.budget.amount) from Patronage p where p.status = 1 group by p.budget.currency")
	List<List<String>> minimunBudgetAccepted();
		
	@Query("select p.budget.currency, max(p.budget.amount) from Patronage p where p.status = 1 group by p.budget.currency")
	List<List<String>> maximunBudgetAccepted();
	
	// Denied -----------------------------------------------------------------------------------------------------
	
	@Query("select p.budget.currency, avg(p.budget.amount) from Patronage p where p.status = 2 group by p.budget.currency")
	List<List<String>> averageBudgetDenied();
			
	@Query("select p.budget.currency, stddev(p.budget.amount) from Patronage p where p.status = 2 group by p.budget.currency")
	List<List<String>> deviationBudgetDenied();
			
	@Query("select p.budget.currency, min(p.budget.amount) from Patronage p where p.status = 2 group by p.budget.currency")
	List<List<String>> minimunBudgetDenied();
			
	@Query("select p.budget.currency, max(p.budget.amount) from Patronage p where p.status = 2 group by p.budget.currency")
	List<List<String>> maximunBudgetDenied();

}