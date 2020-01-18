package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.By.cssSelector;

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
    @FindBy(css = "[data-qa-selector-value=offerPrice.amountMinorUnits.desc]")
    WebElement descendingSortWebElement;
    @FindBy(css = "[data-qa-selector=results-amount]")
    public WebElement searchResultCounter;

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
    public List<WebElement> specListYear;

    @FindBy(css = "[data-qa-selector=price]")
    public List<WebElement> specListPrice;
}
