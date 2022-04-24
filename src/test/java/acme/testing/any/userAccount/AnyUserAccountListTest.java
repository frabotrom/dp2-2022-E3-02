package acme.testing.any.userAccount;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyUserAccountListTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/any/user-account/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String enabled, final String name, 
		final String surname,final String email ,final String password, final String username, 
		final String roles, final String rolesShow) {
		
		super.clickOnMenu("Any","User accounts list");
		super.checkListingExists();
		super.sortListing(1, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, username);
		super.checkColumnHasValue(recordIndex, 1, roles);


		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("identity.name", name);
		super.checkInputBoxHasValue("identity.surname", surname);
		super.checkInputBoxHasValue("identity.email", email);
		super.checkInputBoxHasValue("role", rolesShow);
		

	}

}
