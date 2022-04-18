package acme.testing.inventor.item;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class TestInventorListComponents extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources= "/inventor/item/components.csv", encoding = "utf-8", numLinesToSkip=1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String name, final String code, 
		final String technology, final String description, final String retailPrice, final String link) {
		
		super.signIn("User1", "HIDDEN-PASSWORD");
		
		super.clickOnMenu("Items", "My Components");
		super.checkListingExists();
		super.sortListing(1, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, name);
		super.checkColumnHasValue(recordIndex, 1, code);

		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("technology", technology);	
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("retailPrice", retailPrice);
		super.checkInputBoxHasValue("link", link);

	}
}
