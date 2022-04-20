package acme.testing.administrator.systemConfiguration;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class SystemConfigurationShowTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/systemConfiguration/show.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final String strongSpamTerms, final String weakSpamTerms, final String acceptedCurrencies, final String weakThreshold, final String strongThreshold) {
		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator","System Configuration");
		
		super.checkFormExists();
		super.checkInputBoxHasValue("strongSpamTerms", strongSpamTerms);
		super.checkInputBoxHasValue("weakSpamTerms", weakSpamTerms);
		super.checkInputBoxHasValue("acceptedCurrencies", acceptedCurrencies);
		super.checkInputBoxHasValue("weakThreshold", weakThreshold);
		super.checkInputBoxHasValue("strongThreshold", strongThreshold+"0");
	
		
		super.signOut();
	}
	
}
