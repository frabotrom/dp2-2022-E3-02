package acme.features.administrator.dashboard;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import acme.entities.PatronageStatus;
import acme.forms.dashboards.AdminDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, AdminDashboard>{
	
	@Autowired
	protected AdministratorDashboardRepository repository;
	
	@Override
	public boolean authorise(final Request<AdminDashboard> request) {
		assert request != null;
		return true;
	}

	@Override
	public AdminDashboard findOne(final Request<AdminDashboard> request) {
		assert request != null;

		final AdminDashboard result;
		
		//Components(Agrupado por currency y technology)
		
		final Long totalComponents;
		totalComponents = this.repository.totalComponents();
		
	    final Map<Pair<String, String>, Double>    averagePriceComponents  = new HashMap<>();
	    final Map<Pair<String, String>, Double>    deviationPriceComponents = new HashMap<>();
	    final Map<Pair<String, String>, Double>    minimumPriceComponents = new HashMap<>();
	    final Map<Pair<String, String>, Double>    maximumPriceComponents = new HashMap<>();
	
	    
		final List<Object[]> metricsComponents = this.repository.findMetricsComponents();
	    for(final Object[] metricC : metricsComponents) {
			final String currency = (String) metricC[1];
			final String technology = (String) metricC[0];
			final Double avg = (Double) metricC[2];
			final Double stdev = (Double) metricC[3];
			final Double min = (Double) metricC[4];
			final Double max = (Double) metricC[5];
			
			averagePriceComponents.put(Pair.of(currency, technology), avg);
			deviationPriceComponents.put(Pair.of(currency, technology), stdev);
			minimumPriceComponents.put(Pair.of(currency, technology), min);
			maximumPriceComponents.put(Pair.of(currency, technology), max);
		}
	    
	   //Tools(Agrupado por currency)
	    
	    final Long totalTools;
	    totalTools = this.repository.totalTools();
	    
	    final Map<String, Double>                  averagePriceTools = new HashMap<>();
	    final Map<String, Double>                  deviationPriceTools = new HashMap<>();
	    final Map<String, Double>                  minimumPriceTools = new HashMap<>();
	    final Map<String, Double>                  maximumPriceTools = new HashMap<>();
	    
	    final List<Object[]> metricsTools = this.repository.findMetricsTools();
	    
	    for(final Object[] metricT : metricsTools) {
			final String currency = (String) metricT[0];
			final Double avg = (Double) metricT[1];
			final Double stdev = (Double) metricT[2];
			final Double min = (Double) metricT[3];
			final Double max = (Double) metricT[4];
			
			averagePriceTools.put(currency, avg);
			deviationPriceTools.put(currency, stdev);
			minimumPriceTools.put(currency, min);
			maximumPriceTools.put(currency, max);
	    }
	    
	    // Patronages(Agrupado por status)
	    
	    final List<Object[]> totalPatronages;
	    totalPatronages = this.repository.totalPatronages();
	    
    	final Map<PatronageStatus, Long> mapTotalPatronages = new EnumMap<>(PatronageStatus.class);
	    for(final Object[] fila : totalPatronages) {
	    	final PatronageStatus status = (PatronageStatus) fila[0];
	    	final Long number = (Long) fila[1];
	    	mapTotalPatronages.put(status, number);
	    }
	    
	    final Map<PatronageStatus, Double>         averagePatronagesBudget= new EnumMap<>(PatronageStatus.class);
	    final Map<PatronageStatus, Double>         deviationPatronagesBudget= new EnumMap<>(PatronageStatus.class);
	    final Map<PatronageStatus, Double>         minimumPatronagesBudget= new EnumMap<>(PatronageStatus.class);
	    final Map<PatronageStatus, Double>         maximumPatronagesBudget= new EnumMap<>(PatronageStatus.class);


	    final List<Object[]> metricsPatronagesBudget = this.repository.findMetricsPatronagesBudget();
	    
	    for(final Object[] fila : metricsPatronagesBudget) {
			final PatronageStatus status = (PatronageStatus) fila[0];
			final Double avg = (Double) fila[1];
			final Double stdev = (Double) fila[2];
			final Double min = (Double) fila[3];
			final Double max = (Double) fila[4];
			
			averagePatronagesBudget.put(status, avg);
			deviationPatronagesBudget.put(status, stdev);
			minimumPatronagesBudget.put(status, min);
			maximumPatronagesBudget.put(status, max);
	    }
	    
		result = new AdminDashboard();
		result.setTotalComponents(totalComponents);
		result.setAveragePriceComponents(averagePriceComponents);
		result.setDeviationPriceComponents(deviationPriceComponents);
		result.setMinimunPriceComponents(minimumPriceComponents);
		result.setMaximunPriceComponents(maximumPriceComponents);
		result.setTotalTools(totalTools);
		result.setAveragePriceTools(averagePriceTools);
		result.setDeviationPriceTools(deviationPriceTools);
		result.setMinimunPriceTools(minimumPriceTools);
		result.setMaximunPriceTools(maximumPriceTools);
		result.setTotalPatronages(mapTotalPatronages);
		result.setAveragePatronagesBudget(averagePatronagesBudget);
		result.setDeviationPatronagesBudget(deviationPatronagesBudget);
		result.setMinimumPatronagesBudget(minimumPatronagesBudget);
		result.setMaximumPatronagesBudget(maximumPatronagesBudget);
		
		return result;
	}

	@Override
	public void unbind(final Request<AdminDashboard> request, final AdminDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, //
			"totalComponents", "averagePriceComponents", "deviationPriceComponents", "minimunPriceComponents", "maximunPriceComponents", //
			"totalTools", "averagePriceTools", "deviationPriceTools", "minimunPriceTools", "maximunPriceTools", //
			"totalPatronages", "averagePatronagesBudget", "deviationPatronagesBudget","minimumPatronagesBudget", "maximumPatronagesBudget");
		
		final Set<String> technologies = entity.getMinimunPriceComponents().keySet().stream().map(Pair::getFirst).collect(Collectors.toSet());
		model.setAttribute("technology", technologies);
		
		final Set<String> currencies = entity.getDeviationPriceTools().keySet();
		model.setAttribute("currency", currencies);
	}
}	