package tests;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import pages.pages.Header;
import pages.pages.SearchResultPagePage;
import utils.CommonUtils;
import utils.Helper;

public class CheckHeaderAndSearchTest {

    private Helper helper           = new Helper();
    private CommonUtils commonUtils = new CommonUtils(helper.getDriver());

    private Header header                         = new Header(helper.getDriver());
    private SearchResultPagePage searchResultPage = new SearchResultPagePage(helper.getDriver());


    @Test (description = "Open page https://www.wiley.com/en-us")
    public void step1_openPage() {
        helper.openUrl("https://www.wiley.com/en-us");
        commonUtils.clickYesInModal();
    }

    @Test(description = "Check the header of 'WHO WE SERVE'")
    public void step2_checkWhoWeServeHeader() {
        commonUtils.moveToElement(header.getHeader("WHO WE SERVE"));

        header.checkSubHeadersCount("WHO WE SERVE", 12);

        header.checkHeaderIsPresent("WHO WE SERVE", "Students");
        header.checkHeaderIsPresent("WHO WE SERVE", "Instructors");
        header.checkHeaderIsPresent("WHO WE SERVE", "Book Authors");
        header.checkHeaderIsPresent("WHO WE SERVE", "Professionals");
        header.checkHeaderIsPresent("WHO WE SERVE", "Researchers");
        header.checkHeaderIsPresent("WHO WE SERVE", "Institutions");
        header.checkHeaderIsPresent("WHO WE SERVE", "Librarians");
        header.checkHeaderIsPresent("WHO WE SERVE", "Corporations");
        header.checkHeaderIsPresent("WHO WE SERVE", "Societies");
        header.checkHeaderIsPresent("WHO WE SERVE", "Journal Editors");
        header.checkHeaderIsPresent("WHO WE SERVE", "Bookstores");
        header.checkHeaderIsPresent("WHO WE SERVE", "Government");
    }

    @Test(description = "Check search functionality")
    public void step3_checkSearch() {
        header.fillSearchField("Java");
        header.checkSearchResultDisplayed();

        header.clickSearchButton();
        searchResultPage.checkProductItemCount(10);
        searchResultPage.checkProductTitleContainsText("Java");
        searchResultPage.checkAddOrViewButtonPresent();
    }

    @Test(description = "Check the header of 'SUBJECTS'")
    public void step4_checkSubjectHeader() {
        commonUtils.moveToElement(header.getHeader("SUBJECTS"));
        commonUtils.moveToElement(header.getSubHeader("SUBJECTS", "Education"));

        header.checkSubHeadersCount("SUBJECTS", "Education", 12);

        header.checkHeaderIsPresent("SUBJECTS", "Education", "Assessment, Evaluation Methods");
        header.checkHeaderIsPresent("SUBJECTS", "Education", "Classroom Management");
        header.checkHeaderIsPresent("SUBJECTS", "Education", "Conflict Resolution & Mediation");
        header.checkHeaderIsPresent("SUBJECTS", "Education", "Curriculum Tools");
        header.checkHeaderIsPresent("SUBJECTS", "Education", "Education & Public Policy");
        header.checkHeaderIsPresent("SUBJECTS", "Education", "Educational Research");
        header.checkHeaderIsPresent("SUBJECTS", "Education", "General Education");
        header.checkHeaderIsPresent("SUBJECTS", "Education", "Higher Education");
        header.checkHeaderIsPresent("SUBJECTS", "Education", "Information & Library Science");
        header.checkHeaderIsPresent("SUBJECTS", "Education", "Special Education");
        header.checkHeaderIsPresent("SUBJECTS", "Education", "Special Topics");
        header.checkHeaderIsPresent("SUBJECTS", "Education", "Vocational Technology");
    }

    @AfterTest
    public void close() {
        helper.close();
    }
}