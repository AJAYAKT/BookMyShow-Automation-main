# BookMyShow Automation Project

A comprehensive automation framework for testing BookMyShow website using Cucumber, TestNG, and Selenium WebDriver.

## 🚀 Features

- **BDD Testing**: Cucumber feature files for readable test scenarios
- **Page Object Model**: Maintainable and scalable test architecture
- **Multiple Browser Support**: Chrome, Firefox, and Edge
- **Robust Element Locators**: Multiple fallback strategies for dynamic websites
- **Comprehensive Reporting**: Cucumber HTML and TestNG reports
- **Screenshot Capture**: Automatic screenshots for failed tests
- **Data-Driven Testing**: Excel-based test data management

## 📋 Prerequisites

- **Java JDK 8 or higher** (Currently using Java 21)
- **Maven 3.6+**
- **Chrome Browser** (default browser)
- **Git** (for version control)

## 🛠️ Setup Instructions

### 1. Clone the Project
```bash
git clone <repository-url>
cd BookMyShow-Automation-main
```
 the 
### 2. Verify Java Installation
```bash
java -version
mvn -version
```

### 3. Install Dependencies
```bash
mvn clean install
```

## 🏃‍♂️ Running Tests

### Method 1: Maven Commands
```bash
# Run all tests
mvn test

# Run specific test suite
mvn test -DsuiteXmlFile=testng.xml

# Clean and run
mvn clean test
```

### Method 2: TestNG XML
- Right-click on `testng.xml` → Run As → TestNG Suite

### Method 3: TestRunner Class
- Right-click on `src/test/java/runners/TestRunner.java` → Run As → Java Application

## 📁 Project Structure

```
BookMyShow-Automation-main/
├── config/
│   └── config.properties          # Configuration settings
├── src/
│   ├── features/                  # Cucumber feature files
│   │   ├── movieSearch.feature   # Movie search scenarios
│   │   ├── citySearch.feature    # City selection scenarios
│   │   ├── giftCard.feature      # Gift card scenarios
│   │   └── login.feature         # Login scenarios
│   ├── main/java/
│   │   ├── base/                  # Base classes
│   │   ├── constants/             # Framework constants
│   │   ├── pages/                 # Page Object classes
│   │   └── utils/                 # Utility classes
│   └── test/java/
│       ├── hooks/                 # Cucumber hooks
│       ├── runners/               # Test runners
│       └── stepdefinitions/       # Step definitions
├── reports/                       # Test reports
├── screenshots/                   # Test screenshots
└── target/                        # Compiled classes
```

## ⚙️ Configuration

### Browser Settings (`config/config.properties`)
```properties
browser=chrome                    # chrome, firefox, edge
baseUrl=https://in.bookmyshow.com
implicitWait=15                  # Increased for stability
explicitWait=20                  # Increased for dynamic elements
```

### Test Execution
- **Default Feature**: `movieSearch.feature`
- **Tags**: Use `@CurrentMovies`, `@UIValidation`, `@UpcomingMovies`
- **Parallel Execution**: Configured for 5 threads in `testng.xml`

## 🔧 Troubleshooting

### Common Issues & Solutions

#### 1. Element Not Found Errors
**Problem**: `NoSuchElementException` for city selection
**Solution**: The framework now uses multiple fallback strategies:
- Original locator
- Text-based search
- Generic class-based search

#### 2. Chrome CDP Warnings
**Problem**: `Unable to find CDP implementation matching 139`
**Solution**: Updated Chrome options to handle CDP version issues

#### 3. Dynamic Class Changes
**Problem**: CSS classes change frequently
**Solution**: Using partial class matching and text-based locators

### Debug Mode
```bash
# Enable debug logging
mvn test -X

# Run with specific feature
mvn test -Dcucumber.options="--tags @UpcomingMovies"
```

## 📊 Test Reports

After test execution, reports are available in:
- **Cucumber HTML**: `reports/cucumber.html`
- **TestNG Reports**: `test-output/` directory
- **Screenshots**: `screenshots/` directory

## 🧪 Test Scenarios

### Movie Search Module
- ✅ Select first movie from recommended movies
- ✅ Validate movie details page
- ✅ UI validation on movie screen
- ✅ Navigate to upcoming movies

### City Selection
- ✅ Dynamic city search with fallback strategies
- ✅ Multiple locator approaches
- ✅ Robust error handling

## 🔄 Maintenance

### Updating Locators
1. **Check for Dynamic Changes**: Website updates may change HTML structure
2. **Use Text-Based Locators**: More stable than class-based locators
3. **Implement Fallback Strategies**: Multiple approaches for element identification

### Adding New Tests
1. Create feature file in `src/features/`
2. Add step definitions in `src/test/java/stepdefinitions/`
3. Update `TestRunner.java` if needed
4. Run tests to verify

## 🚨 Best Practices

1. **Wait Strategies**: Use explicit waits for dynamic elements
2. **Locator Strategy**: Prefer text-based over class-based locators
3. **Error Handling**: Implement comprehensive exception handling
4. **Screenshots**: Capture screenshots for debugging failures
5. **Logging**: Add detailed logging for troubleshooting

## 📞 Support

For issues or questions:
1. Check the troubleshooting section above
2. Review test reports and screenshots
3. Enable debug logging with `mvn test -X`
4. Check console output for detailed error messages

## 🔄 Recent Updates

- **Enhanced City Selection**: Multiple fallback strategies implemented
- **Improved Chrome Setup**: Better CDP version handling
- **Increased Wait Times**: More stable element detection
- **Robust Error Handling**: Better debugging capabilities

---

**Note**: This framework is designed to handle dynamic website changes. If tests continue to fail, the website structure may have changed significantly, requiring locator updates.
