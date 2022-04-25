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
public class AdministratorDashboardShowService2 implements AbstractShowService<Administrator, AdminDashboard>{
	
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
	    
	    final List<Object[]> avgComp = this.repository.findAveragePriceComponents();
	    final Map<Pair<String, String>, Double>    averagePriceComponents  = this.listTransformComponent(avgComp);
	    
	    final List<Object[]> devComp = this.repository.findDeviationPriceComponents();
	    final Map<Pair<String, String>, Double>    deviationPriceComponents  = this.listTransformComponent(devComp);
	    
	    final List<Object[]> minComp = this.repository.findMinimumPriceComponents();
	    final Map<Pair<String, String>, Double>    minimumPriceComponents  = this.listTransformComponent(minComp);
	    
	    final List<Object[]> maxComp = this.repository.findMaximumPriceComponents();
	    final Map<Pair<String, String>, Double>    maximumPriceComponents  = this.listTransformComponent(maxComp);
	    
	   //Tools(Agrupado por currency)
	    
	    final Long totalTools;
	    totalTools = this.repository.totalTools();
	    
	    final List<Object[]> avgTool = this.repository.findAverageToolsPrice();
	    final Map<String, Double> averagePriceTools = this.listTransformTool(avgTool);
	    
	    final List<Object[]> devTool = this.repository.findDeviationToolsPrice();
	    final Map<String, Double> deviationPriceTools = this.listTransformTool(devTool);
	    
	    final List<Object[]> minTool = this.repository.findMinimumToolsPrice();
	    final Map<String, Double> minimumPriceTools = this.listTransformTool(minTool);
	    
	    final List<Object[]> maxTool = this.repository.findMaximumToolsPrice();
	    final Map<String, Double> maximumPriceTools = this.listTransformTool(maxTool);
	    
	    // Patronages(Agrupado por status)
	    
	    final List<Object[]> totalPatronages;
	    totalPatronages = this.repository.totalPatronages();
	    
    	final Map<PatronageStatus, Long> mapTotalPatronages = new EnumMap<>(PatronageStatus.class);
	    for(final Object[] fila : totalPatronages) {
	    	final PatronageStatus status = (PatronageStatus) fila[0];
	    	final Long number = (Long) fila[1];
	    	mapTotalPatronages.put(status, number);
	    }
	    
	    final List<Object[]> avgPat = this.repository.findAveragePatronagesBudget();
	    final Map<PatronageStatus, Double> averagePatronagesBudget = this.listTransformPatronage(avgPat);
	    
	    final List<Object[]> devPat = this.repository.findDeviationPatronagesBudget();
	    final Map<PatronageStatus, Double> deviationPatronagesBudget = this.listTransformPatronage(devPat);
	    
	    final List<Object[]> minPat = this.repository.findMinimumPatronagesBudget();
	    final Map<PatronageStatus, Double> minimumPatronagesBudget = this.listTransformPatronage(minPat);
	    
	    final List<Object[]> maxPat = this.repository.findMaximumPatronagesBudget();
	    final Map<PatronageStatus, Double> maximumPatronagesBudget = this.listTransformPatronage(maxPat);
	    
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
	
	public Map<Pair<String, String>, Double> listTransformComponent(final List<Object[]> list) {

		final Map<Pair<String, String>, Double> res  = new HashMap<Pair<String, String>, Double>();

		for(final Object[] l: list) {
			final String currency = (String) l[1];
	    	final String tech =  (String) l[0];
	    	final Double cosa = (Double) l[2];	
	    	res.put(Pair.of(currency, tech), cosa);
		}

		return res;
	}
	
	public Map<String, Double> listTransformTool(final List<Object[]> list) {

		final Map<String, Double> res  = new HashMap<String, Double>();

		for(final Object[] l: list) {
			final String currency = (String) l[0];
	    	final Double cosa = (Double) l[1];	
	    	res.put(currency, cosa);
		}

		return res;
	}
	
	public Map<PatronageStatus, Double> listTransformPatronage(final List<Object[]> list) {

		final Map<PatronageStatus, Double> res  = new HashMap<PatronageStatus, Double>();

		for(final Object[] l: list) {
			final PatronageStatus currency = (PatronageStatus) l[0];
	    	final Double cosa = (Double) l[1];	
	    	res.put(currency, cosa);
		}

		return res;
	}
	
}	