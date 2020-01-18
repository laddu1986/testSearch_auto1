package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage {

    @FindBy(css = "[data-qa-selector=search]")
    WebElement searchButton;

    public void goToSearch() {
        searchButton.click();
    }


}
