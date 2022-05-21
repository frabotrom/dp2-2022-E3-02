package acme.testing.administrator.announcement;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AdministratorAnnouncementCreateTest extends TestHarness{
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/announcement/create-announcement-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTestAnnouncement(final int key, final String title, final String body, final String critical, final String moreInfo) {
	
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Create Announcement");
		
			
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("body", body);
		super.fillInputBoxIn("critical", critical);
		super.fillInputBoxIn("moreInfo", moreInfo);
		super.fillInputBoxIn("confirmation", "true");
		super.clickOnSubmit("Create");
	
		super.clickOnMenu("Authenticated", "Recent Announcements");

		super.checkListingExists();
		super.checkNotListingEmpty();
		
		super.sortListing(0, "asc");
				
		super.checkColumnHasValue(key, 0, title);
		super.checkColumnHasValue(key, 1, body);
		super.checkColumnHasValue(key, 3, critical);

		
		super.signOut();
	}
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/announcement/create-announcement-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTestAnnouncement(final int key, final String title, final String body, final String critical, final String moreInfo) {
	
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Create Announcement");
		
			
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("body", body);
		super.fillInputBoxIn("critical", critical);
		super.fillInputBoxIn("moreInfo", moreInfo);
		super.fillInputBoxIn("confirmation", "true");
		super.clickOnSubmit("Create");
		super.checkErrorsExist();

		
		super.signOut();
	}
}