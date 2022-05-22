package acme.testing.inventor.item;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import acme.testing.TestHarness;

public class TestInventorPublishTool extends TestHarness{
	@Test
	@Order(10)
	public void positiveTest() {
		super.signIn("User1", "HIDDEN-PASSWORD");

		super.clickOnMenu("Inventor", "My Tools");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.clickOnListingRecord(2);
		super.checkFormExists();
		super.clickOnSubmit("Publish Tool");
		super.checkNotErrorsExist();

		super.signOut();
	}
	
	@Test
	@Order(10)
	public void negativeTest() {
		super.signIn("User1", "HIDDEN-PASSWORD");

		super.clickOnMenu("Inventor", "My Components");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.clickOnListingRecord(0);
		super.checkFormExists();
		super.checkNotButtonExists("Publish Component");

		super.signOut();
	}
}
