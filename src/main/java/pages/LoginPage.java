package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	WebDriver driver;
	WebDriverWait wait;

	// ===== Constructor =====
	@SuppressWarnings("LeakingThisInConstructor")
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}

	// ===== Page Elements =====

	@FindBy(xpath = "//input[@placeholder='Search for your city']")
	private WebElement citySearchBox;

	@FindBy(xpath = "//div[contains(@class,'sc-fv93km-0')]//div[1]//div[1]")
	private WebElement firstSuggestedCity;

	@FindBy(xpath = "//div[text()='Sign in']")
	private WebElement loginIcon;

	@FindBy(id = "userMobileNumber")
	private WebElement mobileField;

	@FindBy(xpath = "//div[text()='Continue']")
	private WebElement continueBtn;

	@FindBy(xpath = "//div[normalize-space()='Continue' and (@aria-disabled='true' or contains(@class,'disabled') or contains(@class,'Disabled') or contains(@class,'isDisabled'))]")
	private WebElement disabledContinueBtn;
	@FindBy(xpath = "//div[normalize-space()='Continue' and not(@aria-disabled='true')]")
	private WebElement enabledContinueBtn;

	@FindBy(xpath = "//input[@type='tel']")
	List<WebElement> otpField;

	@FindBy(xpath = "//div[text()='Invalid mobile number']")
	private WebElement errMsg;

	@FindBy(xpath = "//div[@class='sc-1ydq0aj-6 gnsbYm']")
	private WebElement closePopupButton;
	@FindBy(xpath = "//div[@class='sc-1ydq0aj-0 bIaakI']")
	private WebElement popupBackBtn;

	// ===== Actions =====
	
	public void selectCity(String cityName) {
		// Wait for city search box to be visible and enter city name
		wait.until(ExpectedConditions.visibilityOf(citySearchBox)).sendKeys(cityName);
		
		// Wait for suggestions to load (prefer explicit wait over sleep)
		try {
			wait.until(ExpectedConditions.visibilityOf(firstSuggestedCity));
		} catch (Exception ignore) {
			// Fallback strategies below will handle selection
		}
		
		// Try multiple strategies to select the city
		WebElement cityToClick = null;
		
		// Strategy 1: Try the original locator
		try {
			if (firstSuggestedCity.isDisplayed()) {
				cityToClick = firstSuggestedCity;
			}
		} catch (Exception e) {
			System.out.println("Original locator failed, trying alternatives...");
		}
		
		// Strategy 2: Try to find city by text content
		if (cityToClick == null) {
			try {
				String textXPath = "//div[contains(@class, 'sc-')]//div[contains(text(), '" + cityName + "') or contains(text(), '" + cityName.toLowerCase() + "')]";
				WebElement textElement = driver.findElement(By.xpath(textXPath));
				if (textElement.isDisplayed()) {
					cityToClick = textElement;
				}
			} catch (Exception e) {
				System.out.println("Text-based locator failed, trying generic approach...");
			}
		}
		
		// Strategy 3: Generic approach - click first visible suggestion
		if (cityToClick == null) {
			try {
				String genericXPath = "//div[contains(@class, 'sc-')]//div[1]";
				List<WebElement> suggestions = driver.findElements(By.xpath(genericXPath));
				for (WebElement suggestion : suggestions) {
					if (suggestion.isDisplayed()) {
						cityToClick = suggestion;
						break;
					}
				}
			} catch (Exception e) {
				System.out.println("Generic approach failed");
			}
		}
		
		// If we found a city to click, click it
		if (cityToClick != null) {
			wait.until(ExpectedConditions.elementToBeClickable(cityToClick)).click();
			System.out.println("Successfully selected city: " + cityName);
		} else {
			throw new RuntimeException("Could not find any city suggestion to click for: " + cityName);
		}
	}

	public void goToLoginPage() {
		wait.until(ExpectedConditions.elementToBeClickable(loginIcon)).click();
	}
	public void clickContinue() {
		wait.until(ExpectedConditions.elementToBeClickable(continueBtn)).click();
	}

	public boolean verifyOTPBoxIsVisible() {
		return wait.until(ExpectedConditions.visibilityOf(otpField.get(0))).isDisplayed();
	}

	public void backToLogin() {
		wait.until(ExpectedConditions.visibilityOf(popupBackBtn)).click();
	}

	public String verifyErrorMessage() {
		return errMsg.getText();
	}

	public boolean isMobileNumberFieldVisible() {
		return wait.until(ExpectedConditions.visibilityOf(mobileField)).isDisplayed();
	}

	public boolean isContinueButtonVisible() {
		try {
			return continueBtn.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isContinueButtonDisabled() {
		return wait.until(ExpectedConditions.visibilityOf(disabledContinueBtn)).isDisplayed();
	}

	public boolean isContinueButtonEnabled() {
		return wait.until(ExpectedConditions.visibilityOf(enabledContinueBtn)).isDisplayed();
	}

	public void enterMobileNumber(String number) {
		WebElement field = wait.until(ExpectedConditions.visibilityOf(mobileField));
		try {
			field.clear();
		} catch (Exception e) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].value = '';", field);
		}
		field.sendKeys(number);
	}


	public void closeLoginPopup() {
		closePopupButton.click();
	}
}