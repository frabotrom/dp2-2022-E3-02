package acme.features.administrator.dashboard;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {

	@Query("SELECT count(i) FROM Item i WHERE i.type = 'COMPONENT'")
	Long totalComponents();

	@Query("select i.technology, i.retailPrice.currency, avg(i.retailPrice.amount) from Item i where i.type = 'COMPONENT' group by i.retailPrice.currency, i.technology")
	List<Object[]> findAveragePriceComponents();
	
	@Query("select i.technology, i.retailPrice.currency, stddev(i.retailPrice.amount) from Item i where i.type = 'COMPONENT' group by i.retailPrice.currency, i.technology")
	List<Object[]> findDeviationPriceComponents();
	
	@Query("select i.technology, i.retailPrice.currency, min(i.retailPrice.amount) from Item i where i.type = 'COMPONENT' group by i.retailPrice.currency, i.technology")
	List<Object[]> findMinimumPriceComponents();
	
	@Query("select i.technology, i.retailPrice.currency, max(i.retailPrice.amount) from Item i where i.type = 'COMPONENT' group by i.retailPrice.currency, i.technology")
	List<Object[]> findMaximumPriceComponents();
	
	@Query(value = "SELECT i.technology, i.retailPrice.currency ,avg(i.retailPrice.amount),stddev(i.retailPrice.amount),min(i.retailPrice.amount),max(i.retailPrice.amount) FROM Item i WHERE i.type = 'COMPONENT' GROUP BY  i.retailPrice.currency,  i.technology", nativeQuery=true)
	List<Object[]> findMetricsComponents();

	@Query("SELECT count(i) FROM Item i WHERE i.type = 'TOOL'")
	Long totalTools();
	
	@Query("select i.retailPrice.currency, avg(i.retailPrice.amount) from Item i where i.type = 'TOOL' group by i.retailPrice.currency")
	List<Object[]> findAverageToolsPrice();
	
	@Query("select i.retailPrice.currency, stddev(i.retailPrice.amount) from Item i where i.type = 'TOOL' group by i.retailPrice.currency")
	List<Object[]> findDeviationToolsPrice();
	
	@Query("select i.retailPrice.currency, min(i.retailPrice.amount) from Item i where i.type = 'TOOL' group by i.retailPrice.currency")
	List<Object[]> findMinimumToolsPrice();
	
	@Query("select i.retailPrice.currency, max(i.retailPrice.amount) from Item i where i.type = 'TOOL' group by i.retailPrice.currency")
	List<Object[]> findMaximumToolsPrice();

	@Query(value = "SELECT i.retailPrice.currency ,avg(i.retailPrice.amount),stddev(i.retailPrice.amount),min(i.retailPrice.amount),max(i.retailPrice.amount) FROM Item i WHERE i.type = 'TOOL' GROUP BY i.retailPrice.currency", nativeQuery=true)
	List<Object[]> findMetricsTools();

	@Query("SELECT p.status,count(p) FROM Patronage p GROUP BY p.status")
	List<Object[]> totalPatronages();
	
	@Query("select p.status from Patronage p group by p.status")
	List<Object[]> findStatusPatronages();
	
	@Query("select p.status, avg(p.budget.amount) from Patronage p group by p.status")
	List<Object[]> findAveragePatronagesBudget();
	
	@Query("select p.status, stddev(p.budget.amount) from Patronage p group by p.status")
	List<Object[]> findDeviationPatronagesBudget();
	
	@Query("select p.status, min(p.budget.amount) from Patronage p group by p.status")
	List<Object[]> findMinimumPatronagesBudget();
	
	@Query("select p.status, max(p.budget.amount) from Patronage p group by p.status")
	List<Object[]> findMaximumPatronagesBudget();
	
	@Query("SELECT p.status, avg(p.budget.amount),stddev(p.budget.amount),min(p.budget.amount),max(p.budget.amount) FROM Patronage p GROUP BY p.status")
	List<Object[]> findMetricsPatronagesBudget();

}