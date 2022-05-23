package acme.testing.inventor.patronage;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorPatronageAcceptDenyTest extends TestHarness {
	
	@ParameterizedTest
	@CsvFileSource(resources= "/inventor/patronage/accept.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveAcceptTest(final int recordIndex, final String statusBefore, final String statusAfter) {
		
		super.signIn("User1", "HIDDEN-PASSWORD");
		
		super.clickOnMenu("Inventor", "My Patronages");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.clickOnListingRecord(recordIndex);
		super.checkInputBoxHasValue("status", statusBefore);
		super.clickOnSubmit("Accept Patronage");
		super.checkNotPanicExists();
		
		super.clickOnMenu("Inventor", "My Patronages");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(recordIndex);
		super.checkInputBoxHasValue("status", statusAfter);

		super.signOut();
		
	}
	
	
	@ParameterizedTest
	@CsvFileSource(resources= "/inventor/patronage/deny.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveDenyTest(final int recordIndex, final String statusBefore, final String statusAfter) {
		
		super.signIn("User1", "HIDDEN-PASSWORD");
		
		super.clickOnMenu("Inventor", "My Patronages");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.clickOnListingRecord(recordIndex);
		super.checkInputBoxHasValue("status", statusBefore);
		super.clickOnSubmit("Deny Patronage");
		super.checkNotPanicExists();
		
		super.clickOnMenu("Inventor", "My Patronages");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(recordIndex);
		super.checkInputBoxHasValue("status", statusAfter);

		super.signOut();
		
	}
	
	@Test
	@Order(10)
	public void negativeTest() {
		super.signIn("User1", "HIDDEN-PASSWORD");

		super.clickOnMenu("Inventor", "My Patronages");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.clickOnListingRecord(0);
		super.checkFormExists();
		super.checkNotButtonExists("Accept Patronage");
		super.checkNotButtonExists("Deny Patronage");

		super.signOut();
	}
}
