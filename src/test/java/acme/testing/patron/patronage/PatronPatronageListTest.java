package acme.testing.patron.patronage;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class PatronPatronageListTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/patron/patronage/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10) 
	public void positiveTest(final int recordIndex, final String status, final String code, final String legalStuff, final String budget,
		final String creationDate, final String initialDate, final String finalDate, final String optionalLink, final String name,
		final String email, final String company, final String period) {
		
		super.signIn("user6", "HIDDEN-PASSWORD");
		
		super.clickOnMenu("Patron","My Patronages");
		super.checkListingExists();
		super.sortListing(0, "asc"); 

		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, status);
		super.checkColumnHasValue(recordIndex, 2, budget);
		super.checkColumnHasValue(recordIndex, 3, creationDate);
		super.checkColumnHasValue(recordIndex, 4, initialDate);
		super.checkColumnHasValue(recordIndex, 5, finalDate);
		
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
		super.checkInputBoxHasValue("inventorName", name);
		super.checkInputBoxHasValue("inventorEmail", email);
		super.checkInputBoxHasValue("inventorCompany", company);
		
		super.signOut();
	}
}