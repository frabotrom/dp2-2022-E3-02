
package acme.testing.authenticated.patron;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AuthenticatedPatronCreateTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/patron/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String username, final String password, final String name, final String surname, final String email, final String company, final String statement, final String info) {
		super.signUp(username, password, name, surname, email);
		super.signIn(username, password);

		super.clickOnMenu("Account", "Become a Patron");

		super.checkFormExists();

		super.fillInputBoxIn("company", company);
		super.fillInputBoxIn("statement", statement);
		super.fillInputBoxIn("info", info);

		super.clickOnSubmit("Become a Patron");

		super.checkCurrentPath("/master/welcome");

		super.clickOnMenu("Patron", "My Patronages");

		super.checkListingExists();

		super.checkListingEmpty();

		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/patron/becomepatron-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(30)
	public void negativeBecomePatronTest(final int recordIndex, final String username, final String password, final String name, final String surname, final String email, final String company, final String statement, final String info) {
		
		super.signUp(username, password, name, surname, email);
		super.signIn(username, password);
		
		super.clickOnMenu("Account", "Become a Patron");
		
		super.checkFormExists();
		
		super.fillInputBoxIn("company", company);
		super.fillInputBoxIn("statement", statement);
		super.fillInputBoxIn("info", info);
		
		super.clickOnSubmit("Become a Patron");
		
		super.checkErrorsExist();	
		
	}
	
	@Test
	@Order(40)
	public void negativeAlreadyPatronTest() {
		
		super.checkNotLinkExists("Account");	
		super.signIn("user6", "HIDDEN-PASSWORD");
		
		super.clickOnMenu("Account", "Become a Patron");
		
		super.checkErrorsExist();
		
	}

}