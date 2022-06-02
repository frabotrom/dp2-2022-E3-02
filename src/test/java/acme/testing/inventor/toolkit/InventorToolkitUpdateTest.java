package acme.testing.inventor.toolkit;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorToolkitUpdateTest extends TestHarness {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/toolkit/create-toolkit-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void create(final int recordIndex, final String code, final String title, final String description, final String asemblyNotes, final String info) {
		super.signIn("User1", "HIDDEN-PASSWORD");
		
		super.clickOnMenu("Inventor", "My Toolkits");
		super.checkListingExists();

		super.clickOnButton("Create Toolkit");
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("asemblyNotes", asemblyNotes);
		super.fillInputBoxIn("info", info);
		super.clickOnSubmit("Create Toolkit");
		
		super.signOut();
		
	}
	
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/toolkit/update-positive-toolkit.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positive(final int recordIndex, final String title, final String code, final String description, final String asemblyNotes, final String info) {
		super.signIn("User1", "HIDDEN-PASSWORD");
		
		super.clickOnMenu("Inventor", "My Toolkits");
		super.checkListingExists();
		super.sortListing(0, "desc");
		
		super.clickOnListingRecord(recordIndex);		
		super.checkFormExists();
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("asemblyNotes", asemblyNotes);
		super.fillInputBoxIn("info", info);
		super.clickOnSubmit("Update");

		super.checkListingExists();
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/toolkit/update-negative-toolkit.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negative(final int recordIndex, final String title, final String code, final String description, final String asemblyNotes, final String info) {
		super.signIn("User1", "HIDDEN-PASSWORD");
		
		super.clickOnMenu("Inventor", "My Toolkits");
		super.checkListingExists();
		super.sortListing(0, "desc");
		
		super.clickOnListingRecord(0);		
		super.checkFormExists();
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("asemblyNotes", asemblyNotes);
		super.fillInputBoxIn("info", info);
		super.clickOnSubmit("Update");

		super.checkErrorsExist();
		super.signOut();
		
	}

}