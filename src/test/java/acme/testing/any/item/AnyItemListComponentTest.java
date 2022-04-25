package acme.testing.any.item;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyItemListComponentTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/any/item/component/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTestComponents(final int recordIndex, final String name, final String code, final String technology,final String description, 
		 final String retailPrice, final String link) {

		
		super.clickOnMenu("Anonymous","Components List");
		super.checkListingExists();
		super.sortListing(0, "asc");
		

		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, name);
		super.checkColumnHasValue(recordIndex, 2, retailPrice);
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("technology", technology);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("retailPrice", retailPrice);
		super.checkInputBoxHasValue("link", link);

 
	}
	
}
