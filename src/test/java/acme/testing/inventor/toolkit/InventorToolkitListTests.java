package acme.testing.inventor.toolkit;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorToolkitListTests extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/toolkit/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTestComponents(final int recordIndex, final String code, 
		final String title, final String description, final String asemblyNotes, final String info, final String draftMode) {

		super.signIn("User1", "HIDDEN-PASSWORD");

		super.clickOnMenu("Inventor","My Toolkits");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, title);
		super.checkColumnHasValue(recordIndex, 2, info);
		super.checkColumnHasValue(recordIndex, 3, draftMode);

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("asemblyNotes", asemblyNotes);
		super.checkInputBoxHasValue("info", info);
		super.checkInputBoxHasValue("draftMode", draftMode);
		
		super.signOut();


	}
	
}
