package acme.testing.inventor.toolkit;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorToolkitPublishTest extends TestHarness {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/toolkit/publish-toolkit.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positive(final int recordIndex, final String title, final String code, final String description, final String asemblyNotes, final String info) {
		super.signIn("inventor1", "inventor1");
		
		super.clickOnMenu("Inventor", "My toolkits");
		super.checkListingExists();
		super.sortListing(0, "desc");
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.clickOnSubmit("Publish");
		super.checkNotErrorsExist();
		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/toolkit/publish-toolkit.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negative(final int recordIndex, final String title, final String code, final String description, final String asemblyNotes, final String info) {
		super.signIn("User1", "HIDDEN-PASSWORD");
		
		super.clickOnMenu("Inventor", "My Toolkits");
		super.checkListingExists();

		super.clickOnButton("Create new toolkit");
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("assemblyNotes", asemblyNotes);
		super.fillInputBoxIn("info", info);
		super.clickOnSubmit("Create Toolkit");

		super.clickOnMenu("Inventor", "My toolkits");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.clickOnListingRecord(0);
		super.checkFormExists();
		super.clickOnSubmit("Publish");

		super.checkFormExists();

		super.signOut();
		
	}

}
