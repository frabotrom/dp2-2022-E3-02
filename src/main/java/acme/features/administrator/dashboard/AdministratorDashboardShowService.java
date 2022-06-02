package acme.features.administrator.dashboard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import acme.forms.dashboards.AdminDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, AdminDashboard>{
	
	// Internal state ---------------------------------------------------------

			@Autowired
			protected AdministratorDashboardRepository repository;

	// AbstractShowService<Patron, AdminDashboard> interface ----------------


		@Override
		public boolean authorise(final Request<AdminDashboard> request) {
			assert request != null;
			
			return true;
		}
		
		@Override
		public AdminDashboard findOne(final Request<AdminDashboard> request) {
			assert request != null;
			
			AdminDashboard result;
			result = new AdminDashboard();
						
			// Global -----------------------------------------------------------------------------------------------------
			
			final Integer totalComponents;
			final Integer totalTools;
			final List<List<String>> totalPatronagesList;
			
			totalComponents = this.repository.totalComponents();
			totalTools = this.repository.totalTools();
			totalPatronagesList = this.repository.totalPatronages();
			
			result.setTotalComponents(totalComponents);
			result.setTotalTools(totalTools);
			final Map<String, Integer> totalPatronages = this.listTransform2(totalPatronagesList);
			result.setTotalPatronages(totalPatronages);
			
			// Components -----------------------------------------------------------------------------------------------------
			
			List<List<String>> averagePriceComponentsList;
			averagePriceComponentsList = this.repository.averagePriceComponents();
			final Map<Pair<String,String>, Double> averagePriceComponents = this.listTransform3(averagePriceComponentsList);
			
			List<List<String>> deviationPriceComponentsList;
			deviationPriceComponentsList = this.repository.deviationPriceComponents();
			final Map<Pair<String,String>, Double> deviationPriceComponents = this.listTransform3(deviationPriceComponentsList);
			
			List<List<String>> minimunPriceComponentsList;
			minimunPriceComponentsList = this.repository.minimunPriceComponents();
			final Map<Pair<String,String>, Double> minimunPriceComponents = this.listTransform3(minimunPriceComponentsList);
			
			List<List<String>> maximunPriceComponentsList;
			maximunPriceComponentsList = this.repository.maximunPriceComponents();
			final Map<Pair<String,String>, Double> maximunPriceComponents = this.listTransform3(maximunPriceComponentsList);
			
			result.setAveragePriceComponents(averagePriceComponents);
			result.setDeviationPriceComponents(deviationPriceComponents);
			result.setMinimunPriceComponents(minimunPriceComponents);
			result.setMaximunPriceComponents(maximunPriceComponents);
			
			// Tools -----------------------------------------------------------------------------------------------------
			
			List<List<String>> averagePriceToolsList;
			averagePriceToolsList = this.repository.averagePriceTools();
			final Map<String, Double> averagePriceTools = this.listTransform(averagePriceToolsList);
			
			List<List<String>> deviationPriceToolsList;
			deviationPriceToolsList = this.repository.deviationPriceTools();
			final Map<String, Double> deviationPriceTools = this.listTransform(deviationPriceToolsList);
			
			List<List<String>> minimunPriceToolsList;
			minimunPriceToolsList = this.repository.minimunPriceTools();
			final Map<String, Double> minimunPriceTools = this.listTransform(minimunPriceToolsList);
			
			List<List<String>> maximunPriceToolsList;
			maximunPriceToolsList = this.repository.maximunPriceTools();
			final Map<String, Double> maximunPriceTools = this.listTransform(maximunPriceToolsList);
			
			result.setAveragePriceTools(averagePriceTools);
			result.setDeviationPriceTools(deviationPriceTools);
			result.setMinimunPriceTools(minimunPriceTools);
			result.setMaximunPriceTools(maximunPriceTools);
			
			// Total Patronages -----------------------------------------------------------------------------------------------------
			
			final Integer totalProposedPatronages;
			final Integer totalAcceptedPatronages;
			final Integer totalDeniedPatronages;
			
			totalProposedPatronages = this.repository.totalProposedPatronages();
			totalAcceptedPatronages = this.repository.totalAcceptedPatronages();
			totalDeniedPatronages = this.repository.totalDeniedPatronages();
			
			result.setTotalProposedPatronages(totalProposedPatronages);
			result.setTotalAcceptedPatronages(totalAcceptedPatronages);
			result.setTotalDeniedPatronages(totalDeniedPatronages);
			
			// Proposed -----------------------------------------------------------------------------------------------------
			
			List<List<String>> averageBudgetProposedList;
			averageBudgetProposedList = this.repository.averageBudgetProposed();
			final Map<String, Double> averageBudgetProposed = this.listTransform(averageBudgetProposedList);
			
			List<List<String>> deviationBudgetProposedList;
			deviationBudgetProposedList = this.repository.deviationBudgetProposed();
			final Map<String, Double> deviationBudgetProposed = this.listTransform(deviationBudgetProposedList);
			
			List<List<String>> minimunBudgetProposedList;
			minimunBudgetProposedList = this.repository.minimunBudgetProposed();
			final Map<String, Double> minimunBudgetProposed = this.listTransform(minimunBudgetProposedList);
			
			List<List<String>> maximunBudgetProposedList;
			maximunBudgetProposedList = this.repository.maximunBudgetProposed();
			final Map<String, Double> maximunBudgetProposed = this.listTransform(maximunBudgetProposedList);
			
			result.setAverageBudgetProposed(averageBudgetProposed);
			result.setDeviationBudgetProposed(deviationBudgetProposed);
			result.setMinimunBudgetProposed(minimunBudgetProposed);
			result.setMaximunBudgetProposed(maximunBudgetProposed);
			
			// Accepted -----------------------------------------------------------------------------------------------------
			
			List<List<String>> averageBudgetAcceptedList;
			averageBudgetAcceptedList = this.repository.averageBudgetAccepted();
			final Map<String, Double> averageBudgetAccepted = this.listTransform(averageBudgetAcceptedList);
			
			List<List<String>> deviationBudgetAcceptedList;
			deviationBudgetAcceptedList = this.repository.deviationBudgetAccepted();
			final Map<String, Double> deviationBudgetAccepted = this.listTransform(deviationBudgetAcceptedList);
			
			List<List<String>> minimunBudgetAcceptedList;
			minimunBudgetAcceptedList = this.repository.minimunBudgetAccepted();
			final Map<String, Double> minimunBudgetAccepted = this.listTransform(minimunBudgetAcceptedList);
			
			List<List<String>> maximunBudgetAcceptedList;
			maximunBudgetAcceptedList = this.repository.maximunBudgetAccepted();
			final Map<String, Double> maximunBudgetAccepted = this.listTransform(maximunBudgetAcceptedList);
			
			result.setAverageBudgetAccepted(averageBudgetAccepted);
			result.setDeviationBudgetAccepted(deviationBudgetAccepted);
			result.setMinimunBudgetAccepted(minimunBudgetAccepted);
			result.setMaximunBudgetAccepted(maximunBudgetAccepted);
			
			// Denied -----------------------------------------------------------------------------------------------------
			
			List<List<String>> averageBudgetDeniedList;
			averageBudgetDeniedList = this.repository.averageBudgetDenied();
			final Map<String, Double> averageBudgetDenied = this.listTransform(averageBudgetDeniedList);
			
			List<List<String>> deviationBudgetDeniedList;
			deviationBudgetDeniedList = this.repository.deviationBudgetDenied();
			final Map<String, Double> deviationBudgetDenied = this.listTransform(deviationBudgetDeniedList);
			
			List<List<String>> minimunBudgetDeniedList;
			minimunBudgetDeniedList = this.repository.minimunBudgetDenied();
			final Map<String, Double> minimunBudgetDenied = this.listTransform(minimunBudgetDeniedList);
			
			List<List<String>> maximunBudgetDeniedList;
			maximunBudgetDeniedList = this.repository.maximunBudgetDenied();
			final Map<String, Double> maximunBudgetDenied = this.listTransform(maximunBudgetDeniedList);
			
			result.setAverageBudgetDenied(averageBudgetDenied);
			result.setDeviationBudgetDenied(deviationBudgetDenied);
			result.setMinimunBudgetDenied(minimunBudgetDenied);
			result.setMaximunBudgetDenied(maximunBudgetDenied);
			
			// Chimpum -----------------------------------------------------------------------------------------------------
			final Integer totalArtefactsWithChimpums = this.repository.totalArtefactsWithChimpums();
			final Integer totalVisibleTools = this.repository.totalVisibleTools();
			final Double ratioArtefactsWithChimpum = (double) totalArtefactsWithChimpums/totalVisibleTools;
			
			List<List<String>> averageChimpumBudgetList;
			averageChimpumBudgetList = this.repository.averageChimpumBudget();
			final Map<String, Double> averageChimpumBudget = this.listTransform(averageChimpumBudgetList);
			
			List<List<String>> deviationChimpumBudgetList;
			deviationChimpumBudgetList = this.repository.deviationChimpumBudget();
			final Map<String, Double> deviationChimpumBudget = this.listTransform(deviationChimpumBudgetList);
			
			List<List<String>> minimunChimpumBudgetList;
			minimunChimpumBudgetList = this.repository.minimunChimpumBudget();
			final Map<String, Double> minimunChimpumBudget = this.listTransform(minimunChimpumBudgetList);
			
			List<List<String>> maximunChimpumBudgetList;
			maximunChimpumBudgetList = this.repository.maximunChimpumBudget();
			final Map<String, Double> maximunChimpumBudget = this.listTransform(maximunChimpumBudgetList);
			
			result.setRatioArtefactsWithChimpum(ratioArtefactsWithChimpum);
			result.setAverageChimpumBudget(averageChimpumBudget);
			result.setDeviationChimpumBudget(deviationChimpumBudget);
			result.setMinimunChimpumBudget(minimunChimpumBudget);
			result.setMaximunChimpumBudget(maximunChimpumBudget);

			return result;
		}

		@Override
		public void unbind(final Request<AdminDashboard> request, final AdminDashboard entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;

			request.unbind(entity, model, "totalComponents", "totalTools", "totalProposedPatronages", "totalAcceptedPatronages", "totalDeniedPatronages", "ratioArtefactsWithChimpum");
			
			model.setAttribute("totalPatronages", entity.getTotalPatronages().toString());
			
			model.setAttribute("averagePriceComponents", entity.getAveragePriceComponents().toString());
			model.setAttribute("deviationPriceComponents", entity.getDeviationPriceComponents().toString());
			model.setAttribute("minimunPriceComponents", entity.getMinimunPriceComponents().toString());
			model.setAttribute("maximunPriceComponents", entity.getMaximunPriceComponents().toString());
			
			model.setAttribute("averagePriceTools", entity.getAveragePriceTools().toString());
			model.setAttribute("deviationPriceTools", entity.getDeviationPriceTools().toString());
			model.setAttribute("minimunPriceTools", entity.getMinimunPriceTools().toString());
			model.setAttribute("maximunPriceTools", entity.getMaximunPriceTools().toString());
			
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
			
			model.setAttribute("averageChimpumBudget", entity.getAverageChimpumBudget().toString());
			model.setAttribute("deviationChimpumBudget", entity.getDeviationChimpumBudget().toString());
			model.setAttribute("minimunChimpumBudget", entity.getMinimunChimpumBudget().toString());
			model.setAttribute("maximunChimpumBudget", entity.getMaximunChimpumBudget().toString());
			
		}
		
		
		
		
		public Map<String, Double> listTransform(final List<List<String>> list) {
			
			final Map<String, Double> res  = new HashMap<String, Double>();
			
			for(final List<String> l: list) {
				res.put(l.get(0), Double.parseDouble(String.format("%.2f", Double.parseDouble(l.get(1)))));
			}
			
			return res;
		}
		
		public Map<String, Integer> listTransform2(final List<List<String>> list) {
			
			final Map<String, Integer> res  = new HashMap<String, Integer>();
			
			for(final List<String> l: list) {
				res.put(l.get(0), Integer.parseInt(l.get(1)));
			}
			
			return res;
		}
		
		public Map<Pair<String,String>, Double> listTransform3(final List<List<String>> list) {
			
			final Map<Pair<String,String>, Double> res  = new HashMap<Pair<String,String>, Double>();
			
			for(final List<String> l: list) {
				final Pair<String, String> pair = Pair.of(l.get(0), l.get(1));
				res.put(pair, Double.parseDouble(String.format("%.2f", Double.parseDouble(l.get(2)))));
			}
			
			return res;
		}

}
