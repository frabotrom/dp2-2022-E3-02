package acme.testing.inventor.item;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class TestInventorListComponents extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources= "/inventor/item/components.csv", encoding = "utf-8", numLinesToSkip=1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String name, final String code, final String technology,final String description, 
		 final String retailPrice, final String link, final String visible, final String boo) {
		
		super.signIn("User1", "HIDDEN-PASSWORD");
		
		super.clickOnMenu("Inventor", "My Components");
		super.checkListingExists();
		super.sortListing(0, "asc");
		

		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, name);
		super.checkColumnHasValue(recordIndex, 2, retailPrice);
		super.checkColumnHasValue(recordIndex, 3, visible);

		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("technology", technology);	
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("retailPrice", retailPrice);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("visible", boo);
		
		super.signOut();

	}
	@Test
	public void hackingTest() {
		super.checkNotLinkExists("Inventor");
		super.navigate("/inventor/item/list-components");
		super.checkPanicExists();
		
		super.signIn("User6", "HIDDEN-PASSWORD");
		super.navigate("/inventor/item/list-components");
		super.checkPanicExists();
		
	}
}
