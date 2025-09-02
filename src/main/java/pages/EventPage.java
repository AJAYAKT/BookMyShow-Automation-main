package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.DriverSetup;
import utils.WaitUtils;

public class EventPage {
    private final WebDriver driver;

    public EventPage() {
        this.driver = DriverSetup.getDriver();
    }

    public void openEvents() {
        driver.get("https://in.bookmyshow.com/explore/events");
    }

    public WebElement getFirstEventCard() {
        return WaitUtils.waitForElementVisible(driver, By.cssSelector("[data-card-index='0'], .card-container, [data-event-card]"), 10);
    }
}


