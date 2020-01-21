import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.SearchPage;

import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Auto1Test extends TestBase {

    /**
     * Preconditions: We assume that search criteria returns number of items greater than 0
     * <p>
     * Given user is on search page
     * When user applies filter by selected year of production > 2015
     * And user sorts price descending
     * Then Search results are displayed correctly
     * </p>
     * <p>
     * author: Eliza Korab
     * date: 2020.01.20
     */
    @Test
    public void verifySearchResultsAreFilteredAndOrderedCorrectly() {

        int numberOfResults;
        int year = 2015;
        int descendingPriceOrder = 2;

        //1. Navigate to Search page
        mainPage.goToSearch();

        //2. Filter by year and apply filter
        searchPage.setYearFilterAndApply(String.valueOf(year));

        //3. Sort by price descending
        searchPage.sortFilterClick();
        searchPage.selectPriceSortByType(descendingPriceOrder);
        searchPage.sortFilterClick();

        //4. Verify results are present
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-qa-selector=results-amount]")));

        numberOfResults = searchPage.searchResultList.size();
        assertThat(numberOfResults).isNotEqualTo(0);

        //5. Verify that all previously filtered results have proper year of production
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(numberOfResults).isEqualTo(SearchPage.specListYear.size());

        List<Integer> yearList = SearchPage.getYearListFromSpecList();
        int i = 0;
        while (i < yearList.size()) {
            assertThat(yearList.get(i)).isGreaterThanOrEqualTo(year);
            i++;
        }

        //6. Verify that all results are sorted by price descending
        softly.assertThat(numberOfResults).isEqualTo(SearchPage.specListPrice.size());
        softly.assertAll();

        assertThat(SearchPage.getFormattedPriceListFromSpecList()).isSortedAccordingTo(Comparator.reverseOrder());
    }
}
