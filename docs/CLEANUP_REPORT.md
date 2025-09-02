Cleanup Report

Kept
- Login module (`login.feature`, `LoginPage.java`, `LoginSteps.java`)
- Movie module (`movie.feature`, `MoviePage.java`, `MovieSteps.java`)
- Common utilities (`WaitUtils`, `ExcelUtils`, `ScreenshotUtil`)
- Test runner (`runners/TestRunner.java`)
- Build file (`pom.xml`) with Java 17 compiler and Surefire plugin

Added
- Event module scaffold: `EventPage.java`, `event.feature`, `EventSteps.java`
- Updated runner to scan `src/features`

Removed
- City module: `citySearch.feature`, `CitySearchPage.java`, `CitySearchSteps.java`
- GiftCard module: `giftCard.feature`, `GiftCardPage.java`, `GiftCardSteps.java`

Notes
- Ensure JAVA_HOME points to JDK 17 and run: `mvn clean test`.
- Config remains at `config/config.properties` for backward compatibility.

