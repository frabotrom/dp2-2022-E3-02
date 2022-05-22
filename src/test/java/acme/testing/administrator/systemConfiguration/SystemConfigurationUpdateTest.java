package acme.testing.administrator.systemConfiguration;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class SystemConfigurationUpdateTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/systemConfiguration/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final String strongSpamTerms, final String weakSpamTerms, final String systemCurrency, final String acceptedCurrencies, final String weakThreshold, final String strongThreshold) {
		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator","System Configuration");
		
		super.checkFormExists();
		super.fillInputBoxIn("strongSpamTerms", strongSpamTerms);
		super.fillInputBoxIn("weakSpamTerms", weakSpamTerms);
		super.fillInputBoxIn("systemCurrency", systemCurrency);
		super.fillInputBoxIn("acceptedCurrencies", acceptedCurrencies);
		super.fillInputBoxIn("weakThreshold", weakThreshold);
		super.fillInputBoxIn("strongThreshold", strongThreshold);
		super.clickOnSubmit("Update");
		
		super.clickOnMenu("Administrator","System Configuration");
		
		super.checkFormExists();
		super.checkInputBoxHasValue("strongSpamTerms", strongSpamTerms);
		super.checkInputBoxHasValue("weakSpamTerms", weakSpamTerms);
		super.checkInputBoxHasValue("systemCurrency", systemCurrency);
		super.checkInputBoxHasValue("acceptedCurrencies", acceptedCurrencies);
		super.checkInputBoxHasValue("weakThreshold", weakThreshold);
		super.checkInputBoxHasValue("strongThreshold", strongThreshold);
	
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/systemConfiguration/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final String strongSpamTerms, final String weakSpamTerms, final String systemCurrency, final String acceptedCurrencies, final String weakThreshold, final String strongThreshold) {
		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator","System Configuration");
		
		super.checkFormExists();
		super.fillInputBoxIn("strongSpamTerms", strongSpamTerms);
		super.fillInputBoxIn("weakSpamTerms", weakSpamTerms);
		super.fillInputBoxIn("systemCurrency", systemCurrency);
		super.fillInputBoxIn("acceptedCurrencies", acceptedCurrencies);
		super.fillInputBoxIn("weakThreshold", weakThreshold);
		super.fillInputBoxIn("strongThreshold", strongThreshold);
		super.clickOnSubmit("Update");
		
		super.checkErrorsExist();
	
		super.signOut();
	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		// HINT: the framework doesn't provide enough support to implement this test case,
		// HINT+ so it must be performed manually
	}

	
}
