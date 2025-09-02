package stepdefinitions;

import static org.testng.Assert.assertNotNull;

import org.openqa.selenium.WebElement;

import base.DriverSetup;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pages.EventPage;

public class EventSteps {
    private final EventPage eventPage = new EventPage();

    @Given("I open the Events page")
    public void i_open_the_events_page() {
        DriverSetup.getDriver();
        eventPage.openEvents();
    }

    @Then("I should see at least one event")
    public void i_should_see_at_least_one_event() {
        WebElement card = eventPage.getFirstEventCard();
        assertNotNull(card, "Expected at least one event card to be visible");
    }
}


