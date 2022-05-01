package acme.testing.administrator.adminDashboard;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AdminDashboardShowTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/admin-dashboard/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10) 
	public void positiveTest(final String totalComponents, final String totalTools, final String totalPatronages, 
		final String averagePriceComponents, final String deviationPriceComponents, final String minimunPriceComponents, final String maximunPriceComponents, 
		final String averagePriceTools, final String deviationPriceTools, final String minimunPriceTools, final String maximunPriceTools, 
		final String averageBudgetProposed, final String deviationBudgetProposed, final String minimunBudgetProposed, final String maximunBudgetProposed, 
		final String averageBudgetAccepted, final String deviationBudgetAccepted, final String minimunBudgetAccepted, final String maximunBudgetAccepted, 
		final String averageBudgetDenied, final String deviationBudgetDenied, final String minimunBudgetDenied, final String maximunBudgetDenied) {
		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator","My Admin Dashboard");
		super.checkFormExists();
		super.checkInputBoxHasValue("totalComponents", totalComponents);
		super.checkInputBoxHasValue("totalTools", totalTools);
		super.checkInputBoxHasValue("totalPatronages", totalPatronages);
		
		super.checkInputBoxHasValue("averagePriceComponents", averagePriceComponents);
		super.checkInputBoxHasValue("deviationPriceComponents", deviationPriceComponents);
		super.checkInputBoxHasValue("minimunPriceComponents", minimunPriceComponents);
		super.checkInputBoxHasValue("maximunPriceComponents", maximunPriceComponents);
		
		super.checkInputBoxHasValue("averagePriceTools", averagePriceTools);
		super.checkInputBoxHasValue("deviationPriceTools", deviationPriceTools);
		super.checkInputBoxHasValue("minimunPriceTools", minimunPriceTools);
		super.checkInputBoxHasValue("maximunPriceTools", maximunPriceTools);
		
		
		// Los patronages totales no hacen falta ya que se comprueba antes
		//super.checkInputBoxHasValue("totalProposedPatronages", totalProposedPatronages);
		//super.checkInputBoxHasValue("totalAcceptedPatronages", totalAcceptedPatronages);
		//super.checkInputBoxHasValue("totalDeniedPatronages", totalDeniedPatronages);
		
		super.checkInputBoxHasValue("averageBudgetProposed", averageBudgetProposed);
		super.checkInputBoxHasValue("deviationBudgetProposed", deviationBudgetProposed);
		super.checkInputBoxHasValue("minimunBudgetProposed", minimunBudgetProposed);
		super.checkInputBoxHasValue("maximunBudgetProposed", maximunBudgetProposed);
		
		super.checkInputBoxHasValue("averageBudgetAccepted", averageBudgetAccepted);
		super.checkInputBoxHasValue("deviationBudgetAccepted", deviationBudgetAccepted);
		super.checkInputBoxHasValue("minimunBudgetAccepted", minimunBudgetAccepted);
		super.checkInputBoxHasValue("maximunBudgetAccepted", maximunBudgetAccepted);
		
		super.checkInputBoxHasValue("averageBudgetDenied", averageBudgetDenied);
		super.checkInputBoxHasValue("deviationBudgetDenied", deviationBudgetDenied);
		super.checkInputBoxHasValue("minimunBudgetDenied", minimunBudgetDenied);
		super.checkInputBoxHasValue("maximunBudgetDenied", maximunBudgetDenied);
		
		super.signOut();
	}
}