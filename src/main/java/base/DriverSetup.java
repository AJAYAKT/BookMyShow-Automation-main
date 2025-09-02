package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverSetup {
	private static WebDriver driver;

	public static WebDriver getDriver() {
		if (driver == null) {
			String browser = ConfigLoader.get("browser").toLowerCase();

			switch (browser) {
			case "chrome":
				try {
					// Use WebDriverManager to handle driver version compatibility
					WebDriverManager.chromedriver().setup();
					ChromeOptions options = new ChromeOptions();
					options.addArguments("--disable-blink-features=AutomationControlled");
					options.addArguments("--disable-dev-shm-usage");
					options.addArguments("--no-sandbox");
					options.addArguments("--disable-gpu");
					options.addArguments("--remote-allow-origins=*");
					// Disable CDP logging to reduce warnings
					options.addArguments("--disable-logging");
					options.addArguments("--log-level=3");
					driver = new ChromeDriver(options);
				} catch (Exception e) {
					System.err.println("Error setting up Chrome driver: " + e.getMessage());
					// Fallback to basic Chrome setup
					ChromeOptions fallbackOptions = new ChromeOptions();
					fallbackOptions.addArguments("--disable-blink-features=AutomationControlled");
					driver = new ChromeDriver(fallbackOptions);
				}
				break;

			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				break;

			case "edge":
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
				break;

			default:
				throw new RuntimeException("Invalid browser: " + browser);
			}

			driver.manage().window().maximize();
		}
		return driver;
	}

	public static void quitDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}
}
