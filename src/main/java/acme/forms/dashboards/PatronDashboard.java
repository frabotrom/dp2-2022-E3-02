package acme.forms.dashboards;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class PatronDashboard {
	
	// Attributes -------------------------------------------------------------
	
	// Totals -------------------------------------------------------------
	
	protected Integer					totalProposedPatronages;
	
	protected Integer					totalAcceptedPatronages;
	
	protected Integer					totalDeniedPatronages;
	
	
	// Proposed -------------------------------------------------------------
	
	protected Map<String, Double>		averageBudgetProposed;
	
	protected Map<String, Double>		deviationBudgetProposed;
	
	protected Map<String, Double>		minimunBudgetProposed;
	
	protected Map<String, Double>		maximunBudgetProposed;
	
	
	// Accepted -------------------------------------------------------------
	
	protected Map<String, Double>		averageBudgetAccepted;
		
	protected Map<String, Double>		deviationBudgetAccepted;
	
	protected Map<String, Double>		minimunBudgetAccepted;
	
	protected Map<String, Double>		maximunBudgetAccepted;
		
	
	// Denied -------------------------------------------------------------
		
	protected Map<String, Double>		averageBudgetDenied;
	
	protected Map<String, Double>		deviationBudgetDenied;

	protected Map<String, Double>		minimunBudgetDenied;

	protected Map<String, Double>		maximunBudgetDenied;

}
