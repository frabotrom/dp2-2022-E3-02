package acme.testing.authenticated.systemConfiguration;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class SystemConfigurationShowTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/systemConfiguration/show.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final String systemCurrency, final String acceptedCurrencies) {
		
		super.signIn("User1", "HIDDEN-PASSWORD");
		
		super.clickOnMenu("Authenticated","System Configuration");
		
		super.checkFormExists();
		super.checkInputBoxHasValue("systemCurrency", systemCurrency);
		super.checkInputBoxHasValue("acceptedCurrencies", acceptedCurrencies);
	
		
		super.signOut();
	}
	
}
