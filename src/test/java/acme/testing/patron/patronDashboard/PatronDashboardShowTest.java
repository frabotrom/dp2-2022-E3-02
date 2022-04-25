package acme.testing.patron.patronDashboard;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class PatronDashboardShowTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/patron/patron-dashboard/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final String totalProposedPatronages, final String totalAcceptedPatronages, final String totalDeniedPatronages,
		final String averageBudgetProposed, final String deviationBudgetProposed, final String minimunBudgetProposed, final String maximunBudgetProposed,
		final String averageBudgetAccepted, final String deviationBudgetAccepted, final String minimunBudgetAccepted, final String maximunBudgetAccepted,
		final String averageBudgetDenied, final String deviationBudgetDenied, final String minimunBudgetDenied, final String maximunBudgetDenied) {
		
		super.signIn("User2", "HIDDEN-PASSWORD");
		
		super.clickOnMenu("Patron","My Dashboard");
		
		super.checkFormExists();
		super.checkInputBoxHasValue("totalProposedPatronages", totalProposedPatronages);
		super.checkInputBoxHasValue("totalAcceptedPatronages", totalAcceptedPatronages);
		super.checkInputBoxHasValue("totalDeniedPatronages", totalDeniedPatronages);
		
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
