# BookMyShow Automation Framework - API Reference

## 📚 Framework Components

### Base Classes

#### ConfigLoader
**Location**: `src/main/java/base/ConfigLoader.java`

**Purpose**: Loads configuration properties from config.properties file.

**Methods**:
```java
public static String get(String key)
```
- **Parameters**: `key` - Configuration property key
- **Returns**: String value of the property
- **Example**: `ConfigLoader.get("browser")` returns "chrome"

#### DriverSetup
**Location**: `src/main/java/base/DriverSetup.java`

**Purpose**: Manages WebDriver instances and browser configuration.

**Methods**:
```java
public static WebDriver getDriver()
```
- **Returns**: WebDriver instance
- **Description**: Returns singleton WebDriver instance with configured browser

```java
public static void quitDriver()
```
- **Description**: Closes WebDriver instance and cleans up resources

### Page Object Classes

#### LoginPage
**Location**: `src/main/java/pages/LoginPage.java`

**Purpose**: Handles login page interactions and city selection.

**Key Methods**:
```java
public void selectCity(String cityName)
```
- **Parameters**: `cityName` - Name of the city to select
- **Description**: Selects city with multiple fallback strategies

```java
public void goToLoginPage()
```
- **Description**: Navigates to login page

```java
public void enterMobileNumber(String number)
```
- **Parameters**: `number` - Mobile number to enter
- **Description**: Enters mobile number in login form

```java
public void clickContinue()
```
- **Description**: Clicks continue button

```java
public boolean verifyOTPBoxIsVisible()
```
- **Returns**: Boolean indicating if OTP box is visible
- **Description**: Verifies OTP input box visibility

```java
public String verifyErrorMessage()
```
- **Returns**: Error message text
- **Description**: Gets error message text

#### MoviePage
**Location**: `src/main/java/pages/MoviePage.java`

**Purpose**: Handles movie-related page interactions.

**Key Methods**:
```java
public void selectFirstRecommendedMovie()
```
- **Description**: Selects first movie from recommended movies section

```java
public boolean isMovieDetailsPageDisplayed()
```
- **Returns**: Boolean indicating if movie details page is displayed
- **Description**: Verifies movie details page visibility

```java
public boolean isMovieNameDisplayed()
```
- **Returns**: Boolean indicating if movie name is displayed
- **Description**: Verifies movie name visibility

```java
public boolean isMoviePosterVisible()
```
- **Returns**: Boolean indicating if movie poster is visible
- **Description**: Verifies movie poster visibility

```java
public boolean isBookingOptionAvailable()
```
- **Returns**: Boolean indicating if booking option is available
- **Description**: Verifies booking option availability

```java
public void clickMoviesTab()
```
- **Description**: Clicks on Movies tab

```java
public boolean isFiltersVisible()
```
- **Returns**: Boolean indicating if filters are visible
- **Description**: Verifies filters visibility

```java
public boolean isGenreFilterVisible()
```
- **Returns**: Boolean indicating if genre filter is visible
- **Description**: Verifies genre filter visibility

```java
public void clickExploreUpcomingMovies()
```
- **Description**: Clicks on Explore Upcoming Movies link

```java
public boolean isInCinemasNearYouVisible()
```
- **Returns**: Boolean indicating if "In Cinemas Near You" link is visible
- **Description**: Verifies upcoming movies link visibility

#### CitySearchPage
**Location**: `src/main/java/pages/CitySearchPage.java`

**Purpose**: Handles city search functionality.

**Key Methods**:
```java
public void searchCity(String cityName)
```
- **Parameters**: `cityName` - Name of the city to search
- **Description**: Searches for a city

```java
public void selectCityFromResults(String cityName)
```
- **Parameters**: `cityName` - Name of the city to select
- **Description**: Selects city from search results

#### GiftCardPage
**Location**: `src/main/java/pages/GiftCardPage.java`

**Purpose**: Handles gift card functionality.

**Key Methods**:
```java
public void checkGiftCardBalance()
```
- **Description**: Checks gift card balance

```java
public void enterGiftCardDetails(String cardNumber, String pin)
```
- **Parameters**: 
  - `cardNumber` - Gift card number
  - `pin` - Gift card PIN
- **Description**: Enters gift card details

### Utility Classes

#### WaitUtils
**Location**: `src/main/java/utils/WaitUtils.java`

**Purpose**: Provides utility methods for waiting and element interactions.

**Methods**:
```java
public static WebElement waitForVisibility(WebDriver driver, WebElement element)
```
- **Parameters**: 
  - `driver` - WebDriver instance
  - `element` - WebElement to wait for
- **Returns**: WebElement when visible
- **Description**: Waits for element to be visible

```java
public static WebElement waitForClickability(WebDriver driver, WebElement element)
```
- **Parameters**: 
  - `driver` - WebDriver instance
  - `element` - WebElement to wait for
- **Returns**: WebElement when clickable
- **Description**: Waits for element to be clickable

```java
public static WebElement waitForElementWithDynamicClass(WebDriver driver, String partialClass, String additionalSelector, int timeoutSeconds)
```
- **Parameters**: 
  - `driver` - WebDriver instance
  - `partialClass` - Partial CSS class name
  - `additionalSelector` - Additional XPath selector
  - `timeoutSeconds` - Timeout in seconds
- **Returns**: WebElement when found
- **Description**: Waits for element with dynamic class name

```java
public static WebElement waitForElementWithText(WebDriver driver, String text, int timeoutSeconds)
```
- **Parameters**: 
  - `driver` - WebDriver instance
  - `text` - Text to search for
  - `timeoutSeconds` - Timeout in seconds
- **Returns**: WebElement containing text
- **Description**: Waits for element containing specific text

#### ScreenshotUtil
**Location**: `src/main/java/utils/ScreenshotUtil.java`

**Purpose**: Handles screenshot capture functionality.

**Methods**:
```java
public static byte[] captureScreenshot(WebDriver driver, String scenarioName)
```
- **Parameters**: 
  - `driver` - WebDriver instance
  - `scenarioName` - Name of the test scenario
- **Returns**: Screenshot as byte array
- **Description**: Captures screenshot and saves to file

#### ExcelUtils
**Location**: `src/main/java/utils/ExcelUtils.java`

**Purpose**: Handles Excel file operations for test data.

**Methods**:
```java
public static String getCellData(String sheetName, int rowNum, int colNum)
```
- **Parameters**: 
  - `sheetName` - Name of the Excel sheet
  - `rowNum` - Row number
  - `colNum` - Column number
- **Returns**: Cell data as String
- **Description**: Reads data from Excel cell

```java
public static void setCellData(String sheetName, int rowNum, int colNum, String data)
```
- **Parameters**: 
  - `sheetName` - Name of the Excel sheet
  - `rowNum` - Row number
  - `colNum` - Column number
  - `data` - Data to write
- **Description**: Writes data to Excel cell

### Constants

#### FrameworkConstants
**Location**: `src/main/java/constants/FrameworkConstants.java`

**Purpose**: Contains framework-wide constants.

**Constants**:
```java
public static final String BASE_URL
```
- **Description**: Base URL of the application

```java
public static final String SCREENSHOT_PATH
```
- **Description**: Path for saving screenshots

```java
public static final String REPORT_PATH
```
- **Description**: Path for saving reports

```java
public static final String TESTDATA_PATH
```
- **Description**: Path to test data Excel file

## 🧪 Step Definitions

### LoginSteps
**Location**: `src/test/java/stepdefinitions/LoginSteps.java`

**Purpose**: Contains step definitions for login-related scenarios.

**Key Methods**:
```java
@Given("User is on home page and select city {string}")
public void User_is_on_home_page_and_select_city(String city)
```
- **Parameters**: `city` - City name to select
- **Description**: Navigates to home page and selects city

```java
@When("User is on Login Page")
public void user_is_on_login_page()
```
- **Description**: Navigates to login page

```java
@When("User enters mobile number {string}")
public void user_enters_mobile_number(String mobile)
```
- **Parameters**: `mobile` - Mobile number to enter
- **Description**: Enters mobile number in login form

```java
@And("Clicks on Continue")
public void clicks_on_continue()
```
- **Description**: Clicks continue button

```java
@Then("Verify Otp page")
public void Verify_Otp_page()
```
- **Description**: Verifies OTP page is displayed

```java
@Then("Verify message {string}")
public void Verify_message(String errMsg)
```
- **Parameters**: `errMsg` - Expected error message
- **Description**: Verifies error message

### MovieSteps
**Location**: `src/test/java/stepdefinitions/MovieSteps.java`

**Purpose**: Contains step definitions for movie-related scenarios.

**Key Methods**:
```java
@When("I select the first movie from the Recommended Movies section")
public void i_select_the_first_movie_from_the_recommended_movies_section()
```
- **Description**: Selects first movie from recommended movies

```java
@Then("I should see the movie details page")
public void i_should_see_the_movie_details_page()
```
- **Description**: Verifies movie details page is displayed

```java
@Then("the movie name should be displayed")
public void the_movie_name_should_be_displayed()
```
- **Description**: Verifies movie name is displayed

```java
@Then("the movie poster should be visible")
public void the_movie_poster_should_be_visible()
```
- **Description**: Verifies movie poster is visible

```java
@Then("the booking option should be available")
public void the_booking_option_should_be_available()
```
- **Description**: Verifies booking option is available

```java
@When("I click on the Movies tab")
public void i_click_on_the_movies_tab()
```
- **Description**: Clicks on Movies tab

```java
@Then("Verify \"Filters\" header is visible")
public void verify_filters_header_is_visible()
```
- **Description**: Verifies filters header is visible

```java
@Then("Verify Genre filter is visible")
public void verify_genre_filter_is_visible()
```
- **Description**: Verifies genre filter is visible

```java
@And("I click on the {string} link")
public void i_click_on_the_explore_upcoming_movies_link(String linkText)
```
- **Parameters**: `linkText` - Link text to click
- **Description**: Clicks on specified link

```java
@Then("I should see the {string} link displayed")
public void i_should_see_the_in_cinemas_near_you_link_displayed(String linkText)
```
- **Parameters**: `linkText` - Link text to verify
- **Description**: Verifies link is displayed

### CitySearchSteps
**Location**: `src/test/java/stepdefinitions/CitySearchSteps.java`

**Purpose**: Contains step definitions for city search scenarios.

### GiftCardSteps
**Location**: `src/test/java/stepdefinitions/GiftCardSteps.java`

**Purpose**: Contains step definitions for gift card scenarios.

## 🔧 Configuration

### TestRunner
**Location**: `src/test/java/runners/TestRunner.java`

**Purpose**: Main test runner class for Cucumber tests.

**Configuration**:
```java
@CucumberOptions(
    features = "src/features/movieSearch.feature",
    glue = {"stepdefinitions", "hooks"},
    plugin = {"pretty", "html:reports/cucumber.html", "json:reports/cucumber.json"},
    monochrome = true
)
```

### Hooks
**Location**: `src/test/java/hooks/Hooks.java`

**Purpose**: Contains setup and teardown methods.

**Methods**:
```java
@Before
public void setUp()
```
- **Description**: Setup method executed before each scenario

```java
@After
public void tearDown()
```
- **Description**: Teardown method executed after each scenario

## 📊 Usage Examples

### Basic Page Object Usage
```java
// Initialize page object
LoginPage loginPage = new LoginPage(driver);

// Use page object methods
loginPage.selectCity("Kolkata");
loginPage.goToLoginPage();
loginPage.enterMobileNumber("7777777777");
loginPage.clickContinue();
```

### Utility Usage
```java
// Wait for element
WebElement element = WaitUtils.waitForVisibility(driver, someElement);

// Capture screenshot
byte[] screenshot = ScreenshotUtil.captureScreenshot(driver, "test_scenario");

// Read Excel data
String data = ExcelUtils.getCellData("Sheet1", 1, 1);
```

### Step Definition Usage
```java
// In feature file
Given User is on home page and select city "Kolkata"
When I click on the Movies tab
Then I should see the movie details page
```

## 🔄 Extension Points

### Adding New Page Objects
1. Create new class in `src/main/java/pages/`
2. Extend base functionality
3. Add page elements using `@FindBy`
4. Implement page actions

### Adding New Step Definitions
1. Create new class in `src/test/java/stepdefinitions/`
2. Add Cucumber annotations
3. Implement step methods
4. Use page objects for actions

### Adding New Utilities
1. Create new class in `src/main/java/utils/`
2. Implement static methods
3. Add proper error handling
4. Document usage

---

**API Reference Version**: 1.0.0  
**Last Updated**: September 2025  
**Compatible with**: Java 8+, Selenium 4.25.0, Cucumber 7.15.0
