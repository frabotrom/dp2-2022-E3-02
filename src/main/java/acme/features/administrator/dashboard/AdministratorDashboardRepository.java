package acme.features.administrator.dashboard;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {

	@Query("SELECT count(i) FROM Item i WHERE i.type = 'COMPONENT'")
	Long totalComponents();

	@Query(value = "SELECT i.technology, i.retailPrice.currency ,avg(i.retailPrice.amount),stddev(i.retailPrice.amount),min(i.retailPrice.amount),max(i.retailPrice.amount) FROM Item i WHERE i.type = acme.entities.item.type.COMPONENT GROUP BY  i.retailPrice.currency,  i.technology", nativeQuery=true)
	List<Object[]> findMetricsComponents();

	@Query("SELECT count(i) FROM Item i WHERE i.type = 'TOOL'")
	Long totalTools();

	@Query(value = "SELECT i.retailPrice.currency ,avg(i.retailPrice.amount),stddev(i.retailPrice.amount),min(i.retailPrice.amount),max(i.retailPrice.amount) FROM Item i WHERE i.type = acme.entities.item.type.TOOL GROUP BY i.retailPrice.currency", nativeQuery=true)
	List<Object[]> findMetricsTools();

	@Query("SELECT p.status,count(p) FROM Patronage p GROUP BY p.status")
	List<Object[]> totalPatronages();
	
	@Query("SELECT p.status, avg(p.budget.amount),stddev(p.budget.amount),min(p.budget.amount),max(p.budget.amount) FROM Patronage p GROUP BY p.status")
	List<Object[]> findMetricsPatronagesBudget();

}