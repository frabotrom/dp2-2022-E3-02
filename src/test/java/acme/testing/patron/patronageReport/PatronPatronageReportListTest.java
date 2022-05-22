
package acme.testing.patron.patronageReport;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class PatronPatronageReportListTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/patron/patronage-report/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTestPatronageReportsAsPatron(final int recordIndex, final String sequenceNumber, final String creationMoment, final String memorandum, final String link, final String patronageCode) {

		super.signIn("user6", "HIDDEN-PASSWORD");

		super.clickOnMenu("Patron", "My Patronage Reports");
		super.checkListingExists();
		super.checkListingExists();
		super.sortListing(2, "asc"); 

		super.checkColumnHasValue(recordIndex, 0, sequenceNumber);
		super.checkColumnHasValue(recordIndex, 1, creationMoment);
		super.checkColumnHasValue(recordIndex, 2, memorandum);
		super.checkColumnHasValue(recordIndex, 3, patronageCode);

		super.signOut();

	}
}
