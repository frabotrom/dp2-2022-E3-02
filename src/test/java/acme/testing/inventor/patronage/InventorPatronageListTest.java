package acme.testing.inventor.patronage;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorPatronageListTest extends TestHarness {
	
	@ParameterizedTest
	@CsvFileSource(resources= "/inventor/patronage/list-all.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String status, final String code, final String legalStuff, final String budget,
		final String creationDate, final String initialDate, final String finalDate, final String optionalLink, final String patronName,
		final String patronEmail, final String patronCompany, final String period) {
		
		super.signIn("User1", "HIDDEN-PASSWORD");
		
		super.clickOnMenu("Inventor", "My Patronages");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, status);
		super.checkColumnHasValue(recordIndex, 2, budget);
		super.checkColumnHasValue(recordIndex, 3, initialDate);
		super.checkColumnHasValue(recordIndex, 4, finalDate);
		super.checkColumnHasValue(recordIndex, 5, patronName);
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("status", status);
		super.checkInputBoxHasValue("legalStuff", legalStuff);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("creationDate", creationDate);
		super.checkInputBoxHasValue("initialDate", initialDate);
		super.checkInputBoxHasValue("finalDate", finalDate);
		super.checkInputBoxHasValue("optionalLink", optionalLink);
		super.checkInputBoxHasValue("period", period);
		super.checkInputBoxHasValue("patronName", patronName);
		super.checkInputBoxHasValue("patronEmail", patronEmail);
		super.checkInputBoxHasValue("patronCompany", patronCompany);

		super.signOut();
		
	}

}
