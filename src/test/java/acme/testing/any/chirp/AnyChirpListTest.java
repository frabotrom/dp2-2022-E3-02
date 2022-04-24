package acme.testing.any.chirp;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyChirpListTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/any/chirp/list-recent.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTestComponents(final int recordIndex, final String creationDate, final String title, final String author,final String body, 
		 final String email) {

		
		super.clickOnMenu("Anonymous","Chirps Recent List");
		super.checkListingExists();
		super.sortListing(1, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, author);
		super.checkColumnHasValue(recordIndex, 2, creationDate);
		super.checkColumnHasValue(recordIndex, 3, body);
		super.checkColumnHasValue(recordIndex, 4, email); 
	}
	
}
