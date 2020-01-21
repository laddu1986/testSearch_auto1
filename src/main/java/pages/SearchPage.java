package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;


public class SearchPage {

    @FindBy(css = "[data-qa-selector=filter-year]")
    WebElement yearFilter;
    @FindBy(name = "yearRange.min")
    WebElement yearWebElement;

    Select yearSelect() {
        return new Select(yearWebElement);
    }

    public void setYearFilterAndApply(String year) {
        yearFilter.click();
        yearSelect().selectByVisibleText(year);
    }

    @FindBy(name = "sort")
    WebElement sort;

    Select sortSelect() {
        return new Select(sort);
    }

    public void sortFilterClick() {
        sort.click();
    }

    public void selectPriceSortByType(int sortingType) {
        sortSelect().selectByIndex(sortingType);
    }

    @FindBy(css = "[data-qa-selector-type=LIST]>div")
    public List<WebElement> searchResultList;

    @FindBy(css = "[data-qa-selector=spec-list]>li:nth-child(1)")
    public static List<WebElement> specListYear;

    public static List<Integer> getYearListFromSpecList() {
        java.util.Iterator<WebElement> specListYearItem = specListYear.iterator();
        List<Integer> yearList = new ArrayList<>();
        while (specListYearItem.hasNext()) {
            WebElement row = specListYearItem.next();
            String textMonthYear = row.getText();
            String yearAsAString = textMonthYear.substring(textMonthYear.length() - 4);
            int yearForRecord = Integer.parseInt(yearAsAString.trim());
            yearList.add(yearForRecord);
        }
        return yearList;
    }

    @FindBy(css = "[data-qa-selector=price]")
    public static List<WebElement> specListPrice;

    public static List<Integer> getFormattedPriceListFromSpecList() {
        List<Integer> allSearchElementsPrices = new ArrayList<>();
        for (WebElement webElement : specListPrice) {

            String priceForSearchResultItemString = webElement.getText();
            priceForSearchResultItemString = priceForSearchResultItemString.substring(0, priceForSearchResultItemString.length() - 2);
            int priceForSearchResultItem = Integer.parseInt(priceForSearchResultItemString.replace(".", ""));
            allSearchElementsPrices.add(priceForSearchResultItem);
        }
        return allSearchElementsPrices;
    }
}
