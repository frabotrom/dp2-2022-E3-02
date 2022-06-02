package acme.forms;

import java.util.Map;

import org.springframework.data.util.Pair;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminDashboard {
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;
		
	// Attributes -------------------------------------------------------------
		
	// Global -------------------------------------------------------------
	
	protected Integer							totalComponents; 
		
	protected Integer							totalTools;
		
	protected Map<String, Integer>  			totalPatronages;		
		
	// Components  ---------------------------------------------------------
		
	protected Map<Pair<String,String>, Double>		averagePriceComponents;
		
	protected Map<Pair<String,String>, Double>		deviationPriceComponents;
		
	protected Map<Pair<String,String>, Double>		minimunPriceComponents;
		
	protected Map<Pair<String,String>, Double>		maximunPriceComponents;
		
	// Tools  ---------------------------------------------------------
		
	protected Map<String, Double>		averagePriceTools;
				
	protected Map<String, Double>		deviationPriceTools;
			
	protected Map<String, Double>		minimunPriceTools;
				
	protected Map<String, Double>		maximunPriceTools;
	
	
	//Patronages -------------------------------------------------------------
		
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
