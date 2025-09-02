# BookMyShow Automation Framework - Test Execution Guide

## 🚀 Quick Start

### Basic Test Execution
```bash
# Navigate to project directory
cd BookMyShow-Automation-main

# Run all tests
mvn test

# Clean and run
mvn clean test
```

## 📋 Test Execution Methods

### 1. Maven Command Line

#### Run All Tests
```bash
mvn test
```

#### Run Specific Test Suite
```bash
mvn test -DsuiteXmlFile=testng.xml
```

#### Run with Specific Profile
```bash
mvn test -Pchrome
mvn test -Pfirefox
mvn test -Pedge
```

#### Run with Debug Information
```bash
mvn test -X
```

### 2. TestNG XML Execution

#### Run via TestNG XML
```bash
# Right-click on testng.xml → Run As → TestNG Suite
# Or command line:
mvn test -DsuiteXmlFile=testng.xml
```

#### TestNG XML Configuration
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">
<suite name="BookMyShow Test Suite">
  <test thread-count="5" name="Movie Booking Tests">
    <classes>
      <class name="runners.TestRunner"/>
    </classes>
  </test>
</suite>
```

### 3. Cucumber TestRunner

#### Run via TestRunner Class
```bash
# Right-click on TestRunner.java → Run As → Java Application
# Or command line:
mvn test -Dtest=TestRunner
```

#### TestRunner Configuration
```java
@CucumberOptions(
    features = "src/features/movieSearch.feature",
    glue = {"stepdefinitions", "hooks"},
    plugin = {"pretty", "html:reports/cucumber.html", "json:reports/cucumber.json"},
    monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
```

## 🏷️ Tag-Based Execution

### Available Tags
- `@CurrentMovies` - Current movie scenarios
- `@UIValidation` - UI validation scenarios
- `@UpcomingMovies` - Upcoming movie scenarios
- `@Login` - Login scenarios
- `@CitySearch` - City search scenarios
- `@GiftCard` - Gift card scenarios

### Run Specific Tags
```bash
# Run only current movies
mvn test -Dcucumber.options="--tags @CurrentMovies"

# Run multiple tags
mvn test -Dcucumber.options="--tags @CurrentMovies or @UIValidation"

# Exclude specific tags
mvn test -Dcucumber.options="--tags not @Login"

# Run with AND condition
mvn test -Dcucumber.options="--tags @CurrentMovies and @UIValidation"
```

## 🌐 Browser-Specific Execution

### Chrome (Default)
```bash
mvn test -Dbrowser=chrome
```

### Firefox
```bash
mvn test -Dbrowser=firefox
```

### Edge
```bash
mvn test -Dbrowser=edge
```

### Cross-Browser Testing
```bash
# Run on multiple browsers
mvn test -Dbrowser=chrome
mvn test -Dbrowser=firefox
mvn test -Dbrowser=edge
```

## 📊 Parallel Execution

### Configure Parallel Execution
```xml
<!-- In testng.xml -->
<suite name="Suite" parallel="tests" thread-count="5">
  <test name="Test1">
    <classes>
      <class name="runners.TestRunner"/>
    </classes>
  </test>
</suite>
```

### Run Parallel Tests
```bash
mvn test -DsuiteXmlFile=testng.xml
```

## 📈 Test Reporting

### Cucumber Reports
```bash
# HTML Report
open reports/cucumber.html

# JSON Report
cat reports/cucumber.json
```

### TestNG Reports
```bash
# HTML Report
open test-output/index.html

# XML Report
cat test-output/testng-results.xml
```

### Screenshots
```bash
# View failure screenshots
ls screenshots/
```

## 🔧 Advanced Execution Options

### 1. Environment-Specific Execution
```bash
# Development environment
mvn test -Denv=dev

# Staging environment
mvn test -Denv=staging

# Production environment
mvn test -Denv=prod
```

### 2. Data-Driven Execution
```bash
# Run with specific test data
mvn test -Dtestdata=DataSource.xlsx

# Run with multiple data sets
mvn test -Dtestdata=multiple
```

### 3. Headless Execution
```bash
# Run in headless mode
mvn test -Dheadless=true
```

## 📋 Test Scenarios

### Movie Search Module
```gherkin
Feature: Movie Search Module
  The feature will validate movie search, recommended movies, and upcoming movies functionalities.

  Background:
    Given User is on home page and select city "Kolkata"

  @CurrentMovies
  Scenario: Select first movie from recommended movies and validate movie details
    When I select the first movie from the Recommended Movies section
    Then I should see the movie details page
    And the movie name should be displayed
    And the movie poster should be visible
    And the booking option should be available

  @UIValidation
  Scenario: Validate UI elements on movie screen
    When I click on the Movies tab
    And Verify "Filters" header is visible
    And Verify Genre filter is visible

  @UpcomingMovies
  Scenario: Navigate to upcoming movies and validate "In Cinemas Near You" link
    When I click on the Movies tab
    And I click on the "Explore Upcoming Movies" link
    Then I should see the "In Cinemas Near You" link displayed
```

### Login Module
```gherkin
Feature: Login Module
  The feature will validate login functionality and UI elements.

  @Login
  Scenario: Validate login with valid credentials
    Given User is on Login Page
    When User enters mobile number "7777777777"
    And Clicks on Continue
    Then Verify Otp page

  @Login
  Scenario: Validate login with invalid credentials
    Given User is on Login Page
    When User enters mobile number "1234567890"
    And Clicks on Continue
    Then Verify message "Invalid mobile number"
```

## 🔍 Debugging and Troubleshooting

### Enable Debug Mode
```bash
# Verbose logging
mvn test -X

# Debug specific test
mvn test -Dtest=TestRunner -X
```

### Check Logs
```bash
# View Maven logs
tail -f target/surefire-reports/*.txt

# View test output
cat test-output/testng-results.xml
```

### Screenshot Analysis
```bash
# List all screenshots
ls -la screenshots/

# View latest screenshot
open screenshots/$(ls -t screenshots/ | head -1)
```

## 📊 Performance Monitoring

### Execution Time
```bash
# Time the execution
time mvn test

# Profile execution
mvn test -Dmaven.test.failure.ignore=true
```

### Memory Usage
```bash
# Monitor memory usage
mvn test -Dmaven.test.failure.ignore=true -Xmx2048m
```

## 🔄 Continuous Integration

### Jenkins Integration
```bash
# Jenkins pipeline command
mvn clean test -DsuiteXmlFile=testng.xml

# Publish reports
mvn surefire-report:report
```

### Git Integration
```bash
# Run tests before commit
mvn test

# Run tests in CI/CD
mvn clean test -Dheadless=true
```

## 📋 Best Practices

### 1. Test Execution
- Always run `mvn clean test` for fresh execution
- Use specific tags for focused testing
- Monitor execution time and optimize as needed
- Review reports after each execution

### 2. Environment Management
- Use different configurations for different environments
- Maintain separate test data for each environment
- Use environment-specific properties files

### 3. Reporting
- Always check HTML reports for detailed results
- Save screenshots for failed tests
- Archive reports for historical analysis

## 🚨 Common Issues and Solutions

### Issue 1: Tests Not Running
```bash
# Solution: Check Maven configuration
mvn validate
mvn clean compile
```

### Issue 2: Browser Not Opening
```bash
# Solution: Check browser configuration
mvn test -Dbrowser=chrome -X
```

### Issue 3: Element Not Found
```bash
# Solution: Check locators and wait times
mvn test -DexplicitWait=30
```

### Issue 4: Timeout Issues
```bash
# Solution: Increase wait times
mvn test -DimplicitWait=20 -DexplicitWait=30
```

## 📞 Support

### Getting Help
1. Check this documentation
2. Review test reports
3. Check screenshots for failures
4. Enable debug mode for detailed logs

### Useful Commands
```bash
# Quick health check
mvn validate

# Full clean build
mvn clean install

# Run with specific options
mvn test -Dcucumber.options="--tags @CurrentMovies" -X
```

---

**Test Execution Guide Version**: 1.0.0  
**Last Updated**: September 2025  
**Compatible with**: Maven 3.6+, TestNG 7.10.2, Cucumber 7.15.0
