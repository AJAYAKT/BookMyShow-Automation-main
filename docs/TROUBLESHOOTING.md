# BookMyShow Automation Framework - Troubleshooting Guide

## 🚨 Common Issues and Solutions

### 1. Element Not Found Errors

#### Issue: NoSuchElementException
```
org.openqa.selenium.NoSuchElementException: no such element: Unable to locate element
```

**Causes**:
- Dynamic CSS classes changed
- Element not loaded yet
- Incorrect XPath/CSS selector
- Element not visible

**Solutions**:
```java
// 1. Use explicit waits
WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
wait.until(ExpectedConditions.visibilityOf(element));

// 2. Use multiple fallback strategies
try {
    element = driver.findElement(By.xpath("//div[@class='original-class']"));
} catch (NoSuchElementException e) {
    element = driver.findElement(By.xpath("//div[contains(@class, 'partial-class')]"));
}

// 3. Use text-based locators
element = driver.findElement(By.xpath("//div[contains(text(), 'Kolkata')]"));

// 4. Increase wait times
wait.until(ExpectedConditions.elementToBeClickable(element));
```

#### Issue: Element Not Clickable
```
org.openqa.selenium.ElementNotInteractableException: element not interactable
```

**Solutions**:
```java
// 1. Scroll to element
JavascriptExecutor js = (JavascriptExecutor) driver;
js.executeScript("arguments[0].scrollIntoView(true);", element);

// 2. Wait for element to be clickable
wait.until(ExpectedConditions.elementToBeClickable(element));

// 3. Use JavaScript click
js.executeScript("arguments[0].click();", element);
```

### 2. Browser Driver Issues

#### Issue: ChromeDriver Version Mismatch
```
WARNING: Unable to find CDP implementation matching 139
```

**Solutions**:
```java
// 1. Use WebDriverManager (recommended)
WebDriverManager.chromedriver().setup();

// 2. Add Chrome options to suppress warnings
ChromeOptions options = new ChromeOptions();
options.addArguments("--disable-logging");
options.addArguments("--log-level=3");
options.addArguments("--disable-dev-shm-usage");
options.addArguments("--no-sandbox");
```

#### Issue: Browser Not Opening
```
WebDriverException: unknown error: cannot find Chrome binary
```

**Solutions**:
```bash
# 1. Check Chrome installation
where chrome
# or
which google-chrome

# 2. Specify Chrome path
ChromeOptions options = new ChromeOptions();
options.setBinary("/path/to/chrome");

# 3. Use headless mode
options.addArguments("--headless");
```

### 3. Maven Build Issues

#### Issue: Dependency Resolution Failed
```
Could not resolve dependencies for project
```

**Solutions**:
```bash
# 1. Clean Maven cache
mvn dependency:purge-local-repository

# 2. Force update dependencies
mvn clean install -U

# 3. Check internet connection and proxy settings
mvn clean install -X
```

#### Issue: Compilation Errors
```
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin
```

**Solutions**:
```bash
# 1. Check Java version
java -version
mvn -version

# 2. Set Java home
export JAVA_HOME=/path/to/java
export PATH=$JAVA_HOME/bin:$PATH

# 3. Clean and recompile
mvn clean compile
```

### 4. Test Execution Issues

#### Issue: Tests Not Running
```
No tests were found
```

**Solutions**:
```bash
# 1. Check TestNG configuration
mvn test -DsuiteXmlFile=testng.xml

# 2. Verify test class names
mvn test -Dtest=TestRunner

# 3. Check package structure
mvn test -Dtest=**/*Test*
```

#### Issue: Timeout Issues
```
org.openqa.selenium.TimeoutException: Expected condition failed
```

**Solutions**:
```java
// 1. Increase wait times
WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

// 2. Use different wait conditions
wait.until(ExpectedConditions.presenceOfElementLocated(locator));
wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
wait.until(ExpectedConditions.elementToBeClickable(locator));

// 3. Add explicit delays
Thread.sleep(2000);
```

### 5. Reporting Issues

#### Issue: Reports Not Generated
```
Reports directory not found
```

**Solutions**:
```bash
# 1. Create reports directory
mkdir -p reports

# 2. Check Maven surefire plugin configuration
mvn surefire-report:report

# 3. Verify Cucumber plugin configuration
mvn test -Dcucumber.options="--plugin html:reports/cucumber.html"
```

#### Issue: Screenshots Not Captured
```
ScreenshotUtil: Failed to capture screenshot
```

**Solutions**:
```java
// 1. Check screenshot directory
File screenshotDir = new File("screenshots/");
screenshotDir.mkdirs();

// 2. Verify WebDriver instance
if (driver instanceof TakesScreenshot) {
    TakesScreenshot ts = (TakesScreenshot) driver;
    byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
}

// 3. Add error handling
try {
    ScreenshotUtil.captureScreenshot(driver, "test_name");
} catch (Exception e) {
    System.err.println("Screenshot failed: " + e.getMessage());
}
```

## 🔍 Debugging Techniques

### 1. Enable Debug Logging
```bash
# Maven debug mode
mvn test -X

# Selenium debug mode
mvn test -Dselenium.debug=true

# Cucumber debug mode
mvn test -Dcucumber.options="--plugin pretty --plugin html:reports/debug.html"
```

### 2. Browser Debug Mode
```java
// Enable Chrome DevTools
ChromeOptions options = new ChromeOptions();
options.addArguments("--remote-debugging-port=9222");
options.addArguments("--user-data-dir=/tmp/chrome-debug");

// Enable Firefox debug mode
FirefoxOptions options = new FirefoxOptions();
options.addArguments("--devtools");
```

### 3. Element Inspection
```java
// Print element information
System.out.println("Element text: " + element.getText());
System.out.println("Element location: " + element.getLocation());
System.out.println("Element size: " + element.getSize());
System.out.println("Element tag: " + element.getTagName());

// Highlight element
JavascriptExecutor js = (JavascriptExecutor) driver;
js.executeScript("arguments[0].style.border='3px solid red'", element);
```

### 4. Page Source Analysis
```java
// Print page source
System.out.println(driver.getPageSource());

// Save page source to file
Files.write(Paths.get("page_source.html"), driver.getPageSource().getBytes());

// Check specific elements
List<WebElement> elements = driver.findElements(By.xpath("//div[contains(@class, 'sc-')]"));
System.out.println("Found " + elements.size() + " elements with sc- class");
```

## 🛠️ Performance Issues

### 1. Slow Test Execution
**Causes**:
- Long wait times
- Inefficient locators
- Network latency
- Browser performance

**Solutions**:
```java
// 1. Optimize wait times
WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

// 2. Use efficient locators
// Bad: //div[@class='very-long-class-name']
// Good: //div[contains(@class, 'partial-name')]

// 3. Use headless mode
ChromeOptions options = new ChromeOptions();
options.addArguments("--headless");

// 4. Disable images and CSS
options.addArguments("--disable-images");
options.addArguments("--disable-css");
```

### 2. Memory Issues
**Solutions**:
```bash
# Increase JVM memory
export MAVEN_OPTS="-Xmx2048m -Xms1024m"

# Use memory-efficient browser options
ChromeOptions options = new ChromeOptions();
options.addArguments("--memory-pressure-off");
options.addArguments("--max_old_space_size=4096");
```

### 3. Parallel Execution Issues
**Solutions**:
```xml
<!-- Optimize TestNG parallel execution -->
<suite name="Suite" parallel="tests" thread-count="3">
  <test name="Test1">
    <classes>
      <class name="runners.TestRunner"/>
    </classes>
  </test>
</suite>
```

## 🔧 Environment-Specific Issues

### 1. Windows Issues
```bash
# Path issues
set PATH=%JAVA_HOME%\bin;%PATH%

# File permission issues
# Run as Administrator

# Chrome driver issues
# Use WebDriverManager
```

### 2. macOS Issues
```bash
# Permission issues
chmod +x chromedriver

# Security issues
# Allow Chrome in System Preferences > Security & Privacy
```

### 3. Linux Issues
```bash
# Install required packages
sudo apt-get update
sudo apt-get install openjdk-8-jdk maven

# Chrome installation
wget -q -O - https://dl.google.com/linux/linux_signing_key.pub | sudo apt-key add -
sudo sh -c 'echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google-chrome.list'
sudo apt-get update
sudo apt-get install google-chrome-stable
```

## 📊 Monitoring and Logging

### 1. Test Execution Logs
```bash
# View Maven logs
tail -f target/surefire-reports/*.txt

# View test output
cat test-output/testng-results.xml

# View Cucumber logs
cat reports/cucumber.json
```

### 2. Browser Logs
```java
// Enable browser logging
ChromeOptions options = new ChromeOptions();
options.addArguments("--enable-logging");
options.addArguments("--log-level=0");

// Access browser logs
Logs logs = driver.manage().logs();
for (LogEntry entry : logs.get(LogType.BROWSER)) {
    System.out.println(entry.getMessage());
}
```

### 3. Network Monitoring
```java
// Enable network logging
ChromeOptions options = new ChromeOptions();
options.addArguments("--enable-network-service-logging");

// Monitor network requests
Logs logs = driver.manage().logs();
for (LogEntry entry : logs.get(LogType.PERFORMANCE)) {
    System.out.println(entry.getMessage());
}
```

## 🚨 Emergency Recovery

### 1. Complete Reset
```bash
# Clean everything
mvn clean
rm -rf target/
rm -rf test-output/
rm -rf reports/
rm -rf screenshots/

# Rebuild
mvn clean install
mvn test
```

### 2. Dependency Issues
```bash
# Purge local repository
mvn dependency:purge-local-repository

# Force update
mvn clean install -U

# Check for conflicts
mvn dependency:tree
```

### 3. Browser Issues
```bash
# Kill all browser processes
taskkill /f /im chrome.exe
taskkill /f /im chromedriver.exe

# Clear browser cache
# Chrome: Settings > Privacy > Clear browsing data
```

## 📞 Getting Help

### 1. Self-Diagnosis
```bash
# Health check
mvn validate
mvn clean compile
mvn test -Dtest=TestRunner

# System check
java -version
mvn -version
chrome --version
```

### 2. Log Analysis
```bash
# Enable verbose logging
mvn test -X > test.log 2>&1

# Analyze logs
grep -i error test.log
grep -i exception test.log
grep -i timeout test.log
```

### 3. Community Support
- Check Stack Overflow for similar issues
- Review Selenium documentation
- Check Cucumber documentation
- Review TestNG documentation

### 4. Professional Support
- Contact development team
- Submit issue with detailed logs
- Provide reproduction steps
- Include environment details

## 📋 Troubleshooting Checklist

### Before Running Tests
- [ ] Java version is 8 or higher
- [ ] Maven is properly installed
- [ ] Browser is installed and updated
- [ ] Internet connection is stable
- [ ] Project compiles successfully

### During Test Execution
- [ ] Check console output for errors
- [ ] Monitor browser behavior
- [ ] Check network connectivity
- [ ] Verify element visibility
- [ ] Check wait times

### After Test Execution
- [ ] Review test reports
- [ ] Check screenshots for failures
- [ ] Analyze error logs
- [ ] Verify test data
- [ ] Check environment stability

---

**Troubleshooting Guide Version**: 1.0.0  
**Last Updated**: September 2025  
**Compatible with**: All supported environments
