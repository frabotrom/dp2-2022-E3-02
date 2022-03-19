package acme.forms.dashboards;

import java.util.Map;

public class AdminDashboard {
	// Serialisation identifier -----------------------------------------------

		protected static final long	serialVersionUID	= 1L;
		
		// Attributes -------------------------------------------------------------
		
		// Totals -------------------------------------------------------------
		protected Integer					totalComponents;
		
		protected Integer					totalTools;
		
		protected Integer					totalProposedPatronages;
		
		protected Integer					totalAcceptedPatronages;
		
		protected Integer					totalDeniedPatronages;
		
		
		// Components Price ---------------------------------------------------------
		
		protected Map<String, Double>		averagePriceComponents;
		
		protected Map<String, Double>		deviationPriceComponents;
		
		protected Map<String, Double>		minimunPriceComponents;
		
		protected Map<String, Double>		maximunPriceComponents;
		
		// Tools Price ---------------------------------------------------------
		
		protected Map<String, Double>		averagePriceTools;
				
		protected Map<String, Double>		deviationPriceTools;
			
		protected Map<String, Double>		minimunPriceTools;
				
		protected Map<String, Double>		maximunPriceTools;
				
		// Proposed Patronages ----------------------------------------------------
		
		protected Map<String, Double>		averageBudgetProposed;
		
		protected Map<String, Double>		deviationBudgetProposed;
		
		protected Map<String, Double>		minimunBudgetProposed;
		
		protected Map<String, Double>		maximunBudgetProposed;
		
		
		// Accepted Patronages ------------------------------------------------------
		
		protected Map<String, Double>		averageBudgetAccepted;
			
		protected Map<String, Double>		deviationBudgetAccepted;
		
		protected Map<String, Double>		minimunBudgetAccepted;
		
		protected Map<String, Double>		maximunBudgetAccepted;
			
		
		// Denied Patronages ---------------------------------------------------------
			
		protected Map<String, Double>		averageBudgetDenied;
		
		protected Map<String, Double>		deviationBudgetDenied;

		protected Map<String, Double>		minimunBudgetDenied;

		protected Map<String, Double>		maximunBudgetDenied;
}
