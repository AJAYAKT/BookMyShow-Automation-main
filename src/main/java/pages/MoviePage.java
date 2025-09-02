package pages;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.WaitUtils;

//import java.util.List;

public class MoviePage {
    WebDriver driver;
    
    // ===== Constructor =====

    @SuppressWarnings("LeakingThisInConstructor")
    public MoviePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
 // ===== Page Elements =====

    // --- Recommended movies ---
    @FindBy(xpath = "//h2[contains(.,'Recommended')]/following::div[1]//a")
    private java.util.List<WebElement> recommendedMovies;

    // --- Movie details page elements ---
    @FindBy(xpath = "//h1 | //h2 | //div[starts-with(@class,'Title') or contains(@class,'title')][self::div or self::h1 or self::h2]")
    private WebElement movieName;

    @FindBy(xpath = "//section[contains(@class,'sc-qswwm9-0 fuLSrE')]")
    private WebElement moviePoster;

    @FindBy(xpath = "//span[contains(text(),'Book tickets')]")
    private WebElement bookingOption;

    // --- Movies tab ---
    @FindBy(xpath = "//a[text()='Movies']")
    private WebElement moviesTab;

    // Filters header
    @FindBy(xpath = "//div[contains(@class,'sc-1y4pbdw-4')]")
    private WebElement filtersHeader;

    // Genre filter element
    @FindBy(xpath = "//div[contains(text(),'Genre')]")
    private WebElement genreFilter;

    // --- Explore Upcoming Movies link ---
    @FindBy(xpath = "//img[@alt='Coming Soon']")
    private WebElement exploreUpcomingMoviesImgLink;

    // --- "In Cinemas Near You" link ---
    @FindBy(xpath = "//img[@alt='Now Showing']")
    private WebElement inCinemasNearYouImgLink;
    

	// ===== Actions =====

    // --- Recommended Movies ---
    public void selectFirstRecommendedMovie() {
        if (recommendedMovies == null || recommendedMovies.isEmpty()) {
            throw new RuntimeException("No recommended movies found!");
        }
        Set<String> before = driver.getWindowHandles();
        for (WebElement card : recommendedMovies) {
            if (card.isDisplayed()) {
                WaitUtils.waitForClickability(driver, card).click();
                break;
            }
        }
        // Switch to new tab if one opened
        for (String handle : driver.getWindowHandles()) {
            if (!before.contains(handle)) {
                driver.switchTo().window(handle);
                break;
            }
        }
    }

    public boolean isMovieDetailsPageDisplayed() {
        // Wait for any heading/title element to be visible as a proxy for details page load
        return WaitUtils.waitForVisibility(driver, movieName, 30).isDisplayed();
    }

    public boolean isMovieNameDisplayed() {
        return WaitUtils.waitForVisibility(driver, movieName).isDisplayed();
    }

    public boolean isMoviePosterVisible() {
        return WaitUtils.waitForVisibility(driver, moviePoster).isDisplayed();
    }

    public boolean isBookingOptionAvailable() {
        return WaitUtils.waitForVisibility(driver, bookingOption).isDisplayed();
    }

    // --- Movies tab + UI validations ---
    public void clickMoviesTab() {
        WaitUtils.waitForClickability(driver, moviesTab).click();
    }

    public boolean isFiltersVisible() {
        return WaitUtils.waitForVisibility(driver, filtersHeader).isDisplayed();
    }

    public boolean isGenreFilterVisible() {
        return WaitUtils.waitForVisibility(driver, genreFilter).isDisplayed();
    }

    // --- Upcoming Movies ---
    public void clickExploreUpcomingMovies() {
        WaitUtils.waitForClickability(driver, exploreUpcomingMoviesImgLink).click();
    }

    public boolean isInCinemasNearYouVisible() {
        return WaitUtils.waitForVisibility(driver, inCinemasNearYouImgLink).isDisplayed();
    }
}
