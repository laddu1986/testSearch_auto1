import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Auto1Test extends TestBase {

    /**
     * Preconditions: We assume that search criteria returns number of items greater than 0
     * <p>
     * Given user is on search page
     * When user applies filter by selected year of production > 2015
     * And user sorts price descending
     * Then Search results are displayed correctly
     * </p>
     * @author Eliza Korab
     * @date 2020.01.20
     */
    @Test
    public void verifySearchResultsAreFilteredAndOrderedCorrectly() throws InterruptedException {

        int numberOfResults;
        int year = 2015;
        int descendingPriceOrder = 2;

        //1. Navigate to Search page
        //driver.navigate().to("https://www.autohero.com/de/search/");
        mainPage.goToSearch();
        Thread.sleep(2000);

        //2. Filter by year and apply filter
        searchPage.setYearFilterAndApply(String.valueOf(year));
        //driver.findElement(By.cssSelector("[data-qa-selector=filter-year]")).click();
        //new Select(driver.findElement(By.name("yearRange.min"))).selectByVisibleText(String.valueOf(year));
        //OK

        //3. Sort by price descending
        searchPage.sortFilterClick();
        searchPage.selectPriceSortByType(descendingPriceOrder);
        Thread.sleep(1000);
        searchPage.sortFilterClick();
        //OK

        //4. Verify results are present
        //List<WebElement> searchResult = driver.findElements(cssSelector("[data-qa-selector-type=LIST]>div"));
        //Thread.sleep(2000); //TODO flicker
        WebDriverWait wait = new WebDriverWait(driver,10);
        WebElement resultsFound;
        resultsFound= wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-qa-selector=results-amount]")));

        numberOfResults =searchPage.searchResultList.size();
        //assertThat(numberOfResults).isNotEqualTo(0).as("Assert fail if...Filtration returns no results");
        //OK

        //5. Verify that all previously filtered results have proper year of production
        //List<WebElement> specListYear = driver.findElements(cssSelector("[data-qa-selector=spec-list]>li:nth-child(1)"));
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(numberOfResults).isEqualTo(searchPage.specListYear.size()).
                as("Softly validation fail: number of record is not equal to number of spec years");


        java.util.Iterator<WebElement> specListYearItem = searchPage.specListYear.iterator();
        while (specListYearItem.hasNext()) {
            WebElement row = specListYearItem.next();
            //System.out.println(row.getText());
            String textMonthYear = row.getText();
            String yearAsAString = textMonthYear.substring(textMonthYear.length() - 4, textMonthYear.length());
            int yearForRecord = Integer.parseInt(yearAsAString.trim());
            assertThat(yearForRecord).isGreaterThanOrEqualTo(year).
                    as("Assert fail if... Year on the list is not properly filtered");
        }
        //TODO: refactor while and get rid off row WebElement
        //OK

        //6. Verify that all results are sorted by price descending
        //List<WebElement> specListPrice = driver.findElements(cssSelector("[data-qa-selector=price]"));
        softly.assertThat(numberOfResults).isEqualTo(searchPage.specListPrice.size()).
                as("Softly Assert fail if...  Number of records found is not equal to number of spec prices");
        softly.assertAll();


        List<Integer> all_search_elements_prices = new ArrayList<Integer>();
        for (int i = 0; i < searchPage.specListPrice.size(); i++) {

            //loading text of each element in to array all_elements_text
            String priceForSearchResultItemString = searchPage.specListPrice.get(i).getText();
            priceForSearchResultItemString = priceForSearchResultItemString.substring(0, priceForSearchResultItemString.length() - 2);
            int priceForSearchResultItem = Integer.parseInt(priceForSearchResultItemString.replace(".", ""));
            all_search_elements_prices.add(priceForSearchResultItem);
        }

        assertThat(all_search_elements_prices).isSortedAccordingTo(Comparator.<Integer>reverseOrder()).
                as("Assert fail if... prices are not ordered descending");
        //assertThat(all_search_elements_prices).isSortedAccordingTo(Comparator.naturalOrder());

    }
}
