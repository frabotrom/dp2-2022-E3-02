package acme.testing.patron.patronageReport;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class PatronPatronageReportShowTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/patron/patronage-report/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTestPatronageReportsAsPatron(final int recordIndex, final String sequenceNumber, final String creationMoment, 
		final String memorandum, final String optionalLink, final String patronageCode, final String patronageStatus, final String patronageLegal, final String patronageBudget,
		final String patronageCreationDate, final String patronageInitialDate, final String patronageFinalDate, final String patronageLink) {
		
		super.signIn("user6", "HIDDEN-PASSWORD");

		super.clickOnMenu("Patron", "My Patronage Reports");
		super.checkListingExists();
		super.sortListing(2, "asc"); 
		
	
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("sequenceNumber", sequenceNumber);
		super.checkInputBoxHasValue("creationMoment", creationMoment);
		super.checkInputBoxHasValue("memorandum", memorandum);
		super.checkInputBoxHasValue("optionalLink", optionalLink);
		
		super.checkInputBoxHasValue("patronage.code", patronageCode);	
		super.checkInputBoxHasValue("patronage.status", patronageStatus);
		super.checkInputBoxHasValue("patronage.legalStuff", patronageLegal);
		super.checkInputBoxHasValue("patronage.budget", patronageBudget);
		super.checkInputBoxHasValue("patronage.creationDate", patronageCreationDate);
		super.checkInputBoxHasValue("patronage.initialDate", patronageInitialDate);
		super.checkInputBoxHasValue("patronage.finalDate", patronageFinalDate);
		super.checkInputBoxHasValue("patronage.optionalLink", patronageLink);
		
		super.signOut();
	}

}
