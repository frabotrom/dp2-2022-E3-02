package acme.features.patron.dashboard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.forms.dashboards.PatronDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;

@Service
public class PatronDashboardShowService implements AbstractShowService<Patron, PatronDashboard> {
	
	// Internal state ---------------------------------------------------------

		@Autowired
		protected PatronDashboardRepository repository;

	// AbstractShowService<Patron, PatronDashboard> interface ----------------


	@Override
	public boolean authorise(final Request<PatronDashboard> request) {
		assert request != null;
		
		return true;
	}
	
	@Override
	public PatronDashboard findOne(final Request<PatronDashboard> request) {
		assert request != null;
		
		PatronDashboard result;
		result = new PatronDashboard();
		
		final Integer id = request.getPrincipal().getActiveRoleId();
		
		// Totals -----------------------------------------------------------------------------------------------------
		
		final Integer totalProposedPatronages;
		final Integer totalAcceptedPatronages;
		final Integer totalDeniedPatronages;
		
		totalProposedPatronages = this.repository.totalProposedPatronages(id);
		totalAcceptedPatronages = this.repository.totalAcceptedPatronages(id);
		totalDeniedPatronages = this.repository.totalDeniedPatronages(id);
		
		result.setTotalProposedPatronages(totalProposedPatronages);
		result.setTotalAcceptedPatronages(totalAcceptedPatronages);
		result.setTotalDeniedPatronages(totalDeniedPatronages);
		
		// Proposed -----------------------------------------------------------------------------------------------------
		
		List<List<String>> averageBudgetProposedList;
		averageBudgetProposedList = this.repository.averageBudgetProposed(id);
		final Map<String, Double> averageBudgetProposed = this.listTransform(averageBudgetProposedList);
		
		List<List<String>> deviationBudgetProposedList;
		deviationBudgetProposedList = this.repository.deviationBudgetProposed(id);
		final Map<String, Double> deviationBudgetProposed = this.listTransform(deviationBudgetProposedList);
		
		List<List<String>> minimunBudgetProposedList;
		minimunBudgetProposedList = this.repository.minimunBudgetProposed(id);
		final Map<String, Double> minimunBudgetProposed = this.listTransform(minimunBudgetProposedList);
		
		List<List<String>> maximunBudgetProposedList;
		maximunBudgetProposedList = this.repository.maximunBudgetProposed(id);
		final Map<String, Double> maximunBudgetProposed = this.listTransform(maximunBudgetProposedList);
		
		result.setAverageBudgetProposed(averageBudgetProposed);
		result.setDeviationBudgetProposed(deviationBudgetProposed);
		result.setMinimunBudgetProposed(minimunBudgetProposed);
		result.setMaximunBudgetProposed(maximunBudgetProposed);
		
		// Accepted -----------------------------------------------------------------------------------------------------
		
		List<List<String>> averageBudgetAcceptedList;
		averageBudgetAcceptedList = this.repository.averageBudgetAccepted(id);
		final Map<String, Double> averageBudgetAccepted = this.listTransform(averageBudgetAcceptedList);
		
		List<List<String>> deviationBudgetAcceptedList;
		deviationBudgetAcceptedList = this.repository.deviationBudgetAccepted(id);
		final Map<String, Double> deviationBudgetAccepted = this.listTransform(deviationBudgetAcceptedList);
		
		List<List<String>> minimunBudgetAcceptedList;
		minimunBudgetAcceptedList = this.repository.minimunBudgetAccepted(id);
		final Map<String, Double> minimunBudgetAccepted = this.listTransform(minimunBudgetAcceptedList);
		
		List<List<String>> maximunBudgetAcceptedList;
		maximunBudgetAcceptedList = this.repository.maximunBudgetAccepted(id);
		final Map<String, Double> maximunBudgetAccepted = this.listTransform(maximunBudgetAcceptedList);
		
		result.setAverageBudgetAccepted(averageBudgetAccepted);
		result.setDeviationBudgetAccepted(deviationBudgetAccepted);
		result.setMinimunBudgetAccepted(minimunBudgetAccepted);
		result.setMaximunBudgetAccepted(maximunBudgetAccepted);
		
		// Denied -----------------------------------------------------------------------------------------------------
		
		List<List<String>> averageBudgetDeniedList;
		averageBudgetDeniedList = this.repository.averageBudgetDenied(id);
		final Map<String, Double> averageBudgetDenied = this.listTransform(averageBudgetDeniedList);
		
		List<List<String>> deviationBudgetDeniedList;
		deviationBudgetDeniedList = this.repository.deviationBudgetDenied(id);
		final Map<String, Double> deviationBudgetDenied = this.listTransform(deviationBudgetDeniedList);
		
		List<List<String>> minimunBudgetDeniedList;
		minimunBudgetDeniedList = this.repository.minimunBudgetDenied(id);
		final Map<String, Double> minimunBudgetDenied = this.listTransform(minimunBudgetDeniedList);
		
		List<List<String>> maximunBudgetDeniedList;
		maximunBudgetDeniedList = this.repository.maximunBudgetDenied(id);
		final Map<String, Double> maximunBudgetDenied = this.listTransform(maximunBudgetDeniedList);
		
		result.setAverageBudgetDenied(averageBudgetDenied);
		result.setDeviationBudgetDenied(deviationBudgetDenied);
		result.setMinimunBudgetDenied(minimunBudgetDenied);
		result.setMaximunBudgetDenied(maximunBudgetDenied);

		return result;
	}

	@Override
	public void unbind(final Request<PatronDashboard> request, final PatronDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "totalProposedPatronages", "totalAcceptedPatronages", "totalDeniedPatronages");
		
		model.setAttribute("averageBudgetProposed", entity.getAverageBudgetProposed().toString());
		model.setAttribute("deviationBudgetProposed", entity.getDeviationBudgetProposed().toString());
		model.setAttribute("minimunBudgetProposed", entity.getMinimunBudgetProposed().toString());
		model.setAttribute("maximunBudgetProposed", entity.getMaximunBudgetProposed().toString());
		
		model.setAttribute("averageBudgetAccepted", entity.getAverageBudgetAccepted().toString());
		model.setAttribute("deviationBudgetAccepted", entity.getDeviationBudgetAccepted().toString());
		model.setAttribute("minimunBudgetAccepted", entity.getMinimunBudgetAccepted().toString());
		model.setAttribute("maximunBudgetAccepted", entity.getMaximunBudgetAccepted().toString());
		
		model.setAttribute("averageBudgetDenied", entity.getAverageBudgetDenied().toString());
		model.setAttribute("deviationBudgetDenied", entity.getDeviationBudgetDenied().toString());
		model.setAttribute("minimunBudgetDenied", entity.getMinimunBudgetDenied().toString());
		model.setAttribute("maximunBudgetDenied", entity.getMaximunBudgetDenied().toString());
		
	}
	
	
	
	
	public Map<String, Double> listTransform(final List<List<String>> list) {
		
		final Map<String, Double> res  = new HashMap<String, Double>();
		
		for(final List<String> l: list) {
			res.put(l.get(0), Double.parseDouble(String.format("%.2f", Double.parseDouble(l.get(1)))));
		}
		
		return res;
	}

}
