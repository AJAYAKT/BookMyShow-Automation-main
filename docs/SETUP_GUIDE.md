# BookMyShow Automation Framework - Setup Guide

## 📋 Prerequisites

### System Requirements
- **Operating System**: Windows 10/11, macOS, or Linux
- **Java**: JDK 8 or higher (Currently using Java 21)
- **Maven**: 3.6 or higher
- **Browser**: Chrome (default), Firefox, or Edge
- **Memory**: Minimum 4GB RAM
- **Storage**: 2GB free space

### Software Installation

#### 1. Java Development Kit (JDK)
```bash
# Download and install JDK 8+ from Oracle or OpenJDK
# Verify installation
java -version
javac -version
```

#### 2. Apache Maven
```bash
# Download Maven from https://maven.apache.org/download.cgi
# Set environment variables
export MAVEN_HOME=/path/to/maven
export PATH=$MAVEN_HOME/bin:$PATH

# Verify installation
mvn -version
```

#### 3. Git (Optional but recommended)
```bash
# Download from https://git-scm.com/
# Verify installation
git --version
```

## 🚀 Installation Steps

### Step 1: Clone/Download Project
```bash
# Option 1: Clone from repository
git clone <repository-url>
cd BookMyShow-Automation-main

# Option 2: Download and extract ZIP file
# Extract to desired location
cd BookMyShow-Automation-main
```

### Step 2: Verify Project Structure
```bash
# Check if all required files are present
ls -la
# Should see: pom.xml, src/, config/, etc.
```

### Step 3: Install Dependencies
```bash
# Clean and install Maven dependencies
mvn clean install

# This will download all required dependencies including:
# - Selenium WebDriver
# - Cucumber
# - TestNG
# - WebDriverManager
# - Apache POI
```

### Step 4: Verify Installation
```bash
# Compile the project
mvn clean compile

# Run a quick test
mvn test
```

## ⚙️ Configuration

### 1. Browser Configuration
Edit `config/config.properties`:
```properties
# Browser settings
browser=chrome                    # Options: chrome, firefox, edge
baseUrl=https://in.bookmyshow.com
implicitWait=15                  # Implicit wait in seconds
explicitWait=20                  # Explicit wait in seconds
```

### 2. Test Data Configuration
Update `resources/testdata/DataSource.xlsx` with your test data:
- City names
- Movie titles
- Event details
- User credentials

### 3. TestNG Configuration
Modify `testng.xml` for parallel execution:
```xml
<suite name="Suite">
  <test thread-count="5" name="Test">
    <classes>
      <class name="runners.TestRunner"/>
    </classes>
  </test>
</suite>
```

## 🔧 Environment Setup

### Windows Setup
```cmd
# Set JAVA_HOME
set JAVA_HOME=C:\Program Files\Java\jdk-21
set PATH=%JAVA_HOME%\bin;%PATH%

# Set MAVEN_HOME
set MAVEN_HOME=C:\Program Files\Apache\maven
set PATH=%MAVEN_HOME%\bin;%PATH%

# Verify setup
java -version
mvn -version
```

### macOS/Linux Setup
```bash
# Add to ~/.bashrc or ~/.zshrc
export JAVA_HOME=/usr/lib/jvm/java-21-openjdk
export MAVEN_HOME=/opt/maven
export PATH=$JAVA_HOME/bin:$MAVEN_HOME/bin:$PATH

# Reload shell
source ~/.bashrc

# Verify setup
java -version
mvn -version
```

## 🧪 Test Execution Setup

### 1. IDE Setup (IntelliJ IDEA)
```bash
# Import project
File → Open → Select project directory

# Configure Maven
File → Settings → Build → Build Tools → Maven
Set Maven home directory and user settings file

# Install Cucumber plugin
File → Settings → Plugins → Search "Cucumber for Java"
```

### 2. IDE Setup (Eclipse)
```bash
# Import project
File → Import → Existing Maven Projects

# Install Cucumber plugin
Help → Eclipse Marketplace → Search "Cucumber"
```

### 3. Command Line Setup
```bash
# Navigate to project directory
cd BookMyShow-Automation-main

# Verify Maven can find the project
mvn validate
```

## 🔍 Verification Steps

### 1. Compile Test
```bash
mvn clean compile
# Should show: BUILD SUCCESS
```

### 2. Run Sample Test
```bash
mvn test
# Should execute all test scenarios
```

### 3. Check Reports
```bash
# Cucumber reports
open reports/cucumber.html

# TestNG reports
open test-output/index.html
```

## 🚨 Troubleshooting Setup Issues

### Common Issues

#### 1. Java Version Issues
```bash
# Error: Unsupported major.minor version
# Solution: Update to Java 8 or higher
java -version
```

#### 2. Maven Issues
```bash
# Error: Maven not found
# Solution: Add Maven to PATH
export PATH=$MAVEN_HOME/bin:$PATH
```

#### 3. Browser Driver Issues
```bash
# Error: ChromeDriver not found
# Solution: WebDriverManager handles this automatically
# If issues persist, manually download ChromeDriver
```

#### 4. Network Issues
```bash
# Error: Failed to download dependencies
# Solution: Check internet connection and proxy settings
mvn clean install -X
```

### Debug Mode
```bash
# Enable debug logging
mvn test -X

# Run specific test
mvn test -Dtest=TestRunner

# Run with specific profile
mvn test -Pchrome
```

## 📊 Performance Optimization

### 1. Parallel Execution
```xml
<!-- In testng.xml -->
<test thread-count="5" name="Test">
```

### 2. Memory Settings
```bash
# Increase JVM memory
export MAVEN_OPTS="-Xmx2048m -Xms1024m"
```

### 3. Browser Optimization
```java
// In DriverSetup.java
ChromeOptions options = new ChromeOptions();
options.addArguments("--disable-dev-shm-usage");
options.addArguments("--no-sandbox");
```

## 🔄 Maintenance

### Regular Updates
```bash
# Update dependencies
mvn versions:display-dependency-updates

# Update Maven wrapper
mvn wrapper:wrapper
```

### Backup Configuration
```bash
# Backup important files
cp config/config.properties config/config.properties.backup
cp testng.xml testng.xml.backup
```

## 📞 Support

### Getting Help
1. Check this documentation
2. Review error logs in `test-output/`
3. Check screenshots in `screenshots/`
4. Enable debug mode: `mvn test -X`

### Common Commands
```bash
# Clean and compile
mvn clean compile

# Run tests
mvn test

# Generate reports
mvn surefire-report:report

# Skip tests
mvn compile -DskipTests
```

---

**Setup Guide Version**: 1.0.0  
**Last Updated**: September 2025  
**Compatible with**: Java 8+, Maven 3.6+
