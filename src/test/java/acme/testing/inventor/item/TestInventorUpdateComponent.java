package acme.testing.inventor.item;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class TestInventorUpdateComponent extends TestHarness {
	@ParameterizedTest
	@CsvFileSource(resources= "/inventor/item/componentsUpdate.csv", encoding = "utf-8", numLinesToSkip=1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String name, final String technology,final String description, 
		 final String retailPrice, final String link, final String visible, final String boo) {
		super.signIn("User1","HIDDEN-PASSWORD");
		super.clickOnMenu("Inventor","My Components");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(5);
		
		super.checkFormExists();
		super.fillInputBoxIn("name", name);
		super.fillInputBoxIn("technology", technology);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("retailPrice", retailPrice);
		super.clickOnSubmit("Update Component");

		super.sortListing(0, "asc");
		super.clickOnListingRecord(5);
		super.checkFormExists();
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("technology", technology);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("retailPrice", retailPrice);
		super.checkInputBoxHasValue("visible", boo);
	
		super.signOut();

	}
	
	@ParameterizedTest
	@CsvFileSource(resources= "/inventor/item/componentsUpdateNegative.csv", encoding = "utf-8", numLinesToSkip=1)
	@Order(10)
	public void negativeTest(final int recordIndex, final String name, final String technology,final String description, 
		 final String retailPrice, final String link, final String visible) {
		super.signIn("User1","HIDDEN-PASSWORD");
		super.clickOnMenu("Inventor","My Components");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(5);
		
		super.checkFormExists();
		super.fillInputBoxIn("name", name);
		super.fillInputBoxIn("technology", technology);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("retailPrice", retailPrice);
		super.clickOnSubmit("Update Component");
		super.checkErrorsExist();
	
		super.signOut();

	}
}
