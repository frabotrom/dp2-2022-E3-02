package acme.testing.inventor.item;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import acme.testing.TestHarness;

public class TestInventorPublishComponent extends TestHarness{
	@Test
	@Order(10)
	public void positiveTest() {
		super.signIn("User1","HIDDEN-PASSWORD");
		super.clickOnMenu("Inventor","My Components");
		super.checkListingExists();
		super.sortListing(0, "asc");
				
		
		super.clickOnListingRecord(2);
		super.checkFormExists();
		super.checkInputBoxHasValue("visible", "Not Visible");
		super.clickOnButton("Publish Component");
		
		super.clickOnMenu("Inventor","My Components");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(2);
		super.checkFormExists();
		super.checkInputBoxHasValue("visible", "Visible");
		
		super.signOut();


	}
}