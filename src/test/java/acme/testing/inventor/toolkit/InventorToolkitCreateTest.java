package acme.testing.inventor.toolkit;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;
public class InventorToolkitCreateTest extends TestHarness {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/toolkit/create-toolkit-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positive(final int recordIndex, final String code, final String title, final String description, final String assemblyNotes, final String info, final String price) {
		super.signIn("User1", "HIDDEN-PASSWORD");
		
		super.clickOnMenu("Inventor", "My Toolkits");
		super.checkListingExists();

		super.clickOnButton("Create Toolkit");
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("asemblyNotes", assemblyNotes);
		super.fillInputBoxIn("info", info);
		super.clickOnSubmit("Create Toolkit");

		super.clickOnMenu("Inventor", "My Toolkits");
		super.checkListingExists();
		super.sortListing(0, "desc");
		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, title);	
		
		super.clickOnListingRecord(0);
		super.checkFormExists();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("asemblyNotes", assemblyNotes);
		super.checkInputBoxHasValue("info", info);

		super.clickOnButton("Tool/Components of the Toolkit");

		super.checkListingExists();
		super.checkListingEmpty();

		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/toolkit/create-toolkit-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negative(final int recordIndex, final String code, final String title, final String description, final String assemblyNotes, final String info, final String price) {
		super.signIn("User1", "HIDDEN-PASSWORD");
		
		super.clickOnMenu("Inventor", "My Toolkits");
		super.checkListingExists();

		super.clickOnButton("Create Toolkit");
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("asemblyNotes", assemblyNotes);
		super.fillInputBoxIn("info", info);
		super.clickOnSubmit("Create Toolkit");
		
		super.checkErrorsExist();

		super.signOut();
		
	}
	
}