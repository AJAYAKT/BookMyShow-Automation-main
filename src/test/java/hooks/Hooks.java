package hooks;

import org.openqa.selenium.WebDriver;

import base.DriverSetup;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.ScreenshotUtil;

public class Hooks {
	private static WebDriver driver;

	@Before
	public void setUp() {
		driver = DriverSetup.getDriver();
	}

	@After
	public void tearDown(Scenario scenario) {
		if (scenario.isFailed() && driver != null) {
			byte[] screenshot = ScreenshotUtil.captureScreenshot(driver, scenario.getName());
			if (screenshot != null) {
				scenario.attach(screenshot, "image/png", scenario.getName());
			}
		}
		if (driver != null) {
			DriverSetup.quitDriver();
		}
	}

	public static WebDriver getDriver() {
		return driver;
	}
}