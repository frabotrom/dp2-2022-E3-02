package acme.forms.dashboards;

import java.util.Map;

import org.springframework.data.util.Pair;

import acme.entities.PatronageStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminDashboard {
	// Serialisation identifier -----------------------------------------------

		protected static final long	serialVersionUID	= 1L;
		
		// Attributes -------------------------------------------------------------
		
		// Totals -------------------------------------------------------------
		protected Long					totalComponents; 
		
		protected Long					totalTools;
		
		protected Map<PatronageStatus, Long>  totalPatronages;		
		
		// Components Price ---------------------------------------------------------
		
		protected Map<Pair<String,String>, Double>		averagePriceComponents;
		
		protected Map<Pair<String,String>, Double>		deviationPriceComponents;
		
		protected Map<Pair<String,String>, Double>		minimunPriceComponents;
		
		protected Map<Pair<String,String>, Double>		maximunPriceComponents;
		
		// Tools Price ---------------------------------------------------------
		
		protected Map<String, Double>		averagePriceTools;
				
		protected Map<String, Double>		deviationPriceTools;
			
		protected Map<String, Double>		minimunPriceTools;
				
		protected Map<String, Double>		maximunPriceTools;
		
		//Patronages
		
		protected Map<PatronageStatus, Double>         averagePatronagesBudget;
	    
		protected Map<PatronageStatus, Double>         deviationPatronagesBudget;
	    
		protected Map<PatronageStatus, Double>         minimumPatronagesBudget;
	    
		protected Map<PatronageStatus, Double>         maximumPatronagesBudget;
}
