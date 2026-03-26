# 🛒 Flipkart E-Commerce Automation – Selenium + TestNG + Maven

Automated test suite for Flipkart's product search, filtering, and cart workflow
using **Selenium WebDriver 4**, **TestNG**, **RetryAnalyzer**, and **Maven**.

---

## 📌 What This Test Does

| Step | Action |
|------|--------|
| 1 | Launch Chrome and open `https://www.flipkart.com` |
| 2 | Close the login popup (if displayed) |
| 3 | Search for **"Bluetooth Speakers"** |
| 4 | Apply **Brand → boAt** filter |
| 5 | Apply **Customer Rating → 4★ & above** filter |
| 6 | Sort by **Price: Low to High** |
| 7 | Click the first product (opens in new tab) |
| 8 | Switch to the new product tab |
| 9 | Click **"Add to Cart"** (if button is available) |
| 10 | Capture a **screenshot** saved to `screenshots/cart_result.png` |
| 11 | If test fails → **RetryAnalyzer** automatically re-runs the test |

---

## 🗂️ Project Structure

```
com.ecom.flipkart/
│
├── src/
│   ├── main/java/
│   │   ├── genericUtility/
│   │   │   ├── BaseClass.java                   ← WebDriver setup/teardown (@BeforeClass, @AfterClass)
│   │   │   ├── MultipleWindows.java              ← Switches between browser tabs
│   │   │   ├── Screenshot.java                   ← Captures and saves screenshots
│   │   │   └── RertyAnalyzerImplementation.java  ← RetryAnalyzer (auto-retries failed tests)
│   │   │
│   │   └── objectRepo/                           ← Page Object Model classes
│   │       ├── HomePage.java                     ← Search bar & searchProduct()
│   │       ├── LoginAlert.java                   ← Login popup close logic
│   │       ├── ProductPage.java                  ← Filters, sorting, first product click
│   │       └── CartPage.java                     ← Cart button & screenshot helpers
│   │
│   └── test/java/
│       └── testCaseRepo/
│           ├── TC_001.java    ← ✅ Main TestNG test (uses RetryAnalyzer)
│
├── screenshots/               ← Stores result screenshots (auto-created)
├── test-output/               ← TestNG HTML reports (auto-generated)
├── pom.xml                    ← Maven dependencies
└── README.md                  ← This file
```
## ✅ Prerequisites

Make sure the following are installed before running:

### 1. Java JDK 17 (as used in this project)
```bash
java -version
# Expected: java version "17.x.x"
```
Download: https://www.oracle.com/java/technologies/downloads/

### 2. Apache Maven 3.6+
```bash
mvn -version
# Expected: Apache Maven 3.x.x
```
Download: https://maven.apache.org/download.cgi

### 3. Google Chrome Browser (latest stable)
Download: https://www.google.com/chrome/

### 4. ChromeDriver
This project uses Selenium **4.x** which includes **Selenium Manager** —
ChromeDriver is downloaded automatically. No manual setup needed.

### 5. Eclipse IDE (optional, for GUI running)
Download: https://www.eclipse.org/downloads/

---

## 🚀 How to Run

### ▶️ Option 1 — Run via Maven (Command Line) ✅ Recommended

```bash
# Step 1: Navigate to project folder
cd com.ecom.flipkart

# Step 2: Install dependencies
mvn clean install -DskipTests

# Step 3: Run Specific Test class
mvn -Dtest=TC_001 test
```

TestNG executes `TC_001.java` automatically.
If a test fails, **RetryAnalyzer** re-runs it up to 3 times.
Results are saved to `test-output/index.html`.

---

### ▶️ Option 2 — Run via Eclipse IDE

1. **Import the project:**
   - Open Eclipse → `File` → `Import` → `Existing Maven Projects`
   - Browse to the `com.ecom.flipkart` folder → Click **Finish**

2. **Wait for Maven to download dependencies**
   (progress shown in the bottom status bar)

3. **Run the test:**
   - Expand `src/test/java/testCaseRepo/`
   - Right-click `TC_001.java` → `Run As` → **TestNG Test**

4. **View results:**
   - Console tab shows live pass/fail + retry output
   - Open `test-output/index.html` in a browser for the full HTML report

---

---

## 📸 Screenshots

Screenshots are saved automatically to:
```
com.ecom.flipkart/screenshots/
  ├── cart_result.png   ← Saved when "Add to Cart" is clicked successfully
  └── result.png        ← Saved when product is unavailable / cannot be added
```

> The `screenshots/` folder is created automatically if it does not exist.

---

## 📊 Test Reports

After running, open the TestNG HTML report in any browser:

```
test-output/index.html             ← Full TestNG suite report (includes retry details)
test-output/emailable-report.html  ← Compact email-friendly summary report
```

In the report, **retried tests appear as skipped** entries and the final
attempt shows the true pass or fail outcome.

---

## 🔧 Dependencies (pom.xml)

| Library | Version | Purpose |
|---------|---------|---------|
| selenium-java | 4.x | Browser automation |
| testng | 7.x | Test framework, reporting & IRetryAnalyzer |
| poi | 5.x | Apache POI (Excel – for data-driven testing) |
| poi-ooxml | 5.x | POI OOXML support |

---

## 🏗️ Framework Design

This project follows the **Page Object Model (POM)** pattern:

```
TC_001.java  (Test Layer)
      │
      ├── @Test(retryAnalyzer = RertyAnalyzerImplementation.class)
      │
      ▼
objectRepo/  (Page Objects)
  HomePage → ProductPage → CartPage → LoginAlert
      │
      ▼
genericUtility/  (Utilities)
  BaseClass | Screenshot | MultipleWindows | RertyAnalyzerImplementation
      │
      ▼
Selenium WebDriver → Chrome → https://www.flipkart.com
```

- **BaseClass** manages browser lifecycle (`@BeforeClass` / `@AfterClass`)
- **@BeforeMethod** closes the login popup before every test method
- **PageFactory + @FindBy** used for all element locators
- **RertyAnalyzerImplementation** retries failed tests automatically
---

## 🔁 RetryAnalyzer – How It Works

The project uses a **RetryAnalyzer** to automatically re-run a failed test a set
number of times before finally marking it as failed.

### Class location:
```
src/main/java/genericUtility/RertyAnalyzerImplementation.java
```

### How it is applied in TC_001.java:
```java
@Test(retryAnalyzer = genericUtility.RertyAnalyzerImplementation.class)
public void LE_001() throws InterruptedException, IOException {
    // test steps here
}
```

### Retry behaviour summary:

| Scenario | Behaviour |
|----------|-----------|
| Test passes on 1st attempt | Runs once — marked ✅ PASS |
| Test fails on 1st attempt | Automatically retried up to 3 times |
| Test fails all retries | Marked ❌ FAIL after all attempts exhausted |
| Report visibility | Each retry attempt appears in TestNG report |

---

---

## ⚠️ Known Limitations & Notes

| Issue | Detail |
|-------|--------|
| Flipkart UI changes | DOM updates frequently; XPaths may need refreshing |
| Login popup XPath | `//span[@role='button']` — verify if popup doesn't close |
| `Thread.sleep()` | Used for stability; consider replacing with `WebDriverWait` |
| Class name typo | `RertyAnalyzerImplementation` — "Rerty" should be "Retry" |
| Headless mode | Not configured; Chrome opens as a visible browser window |

---

## 🐛 Troubleshooting

| Problem | Fix |
|---------|-----|
| `SessionNotCreatedException` | Update Chrome; Selenium Manager auto-handles the driver |
| `NoSuchElementException` | XPath is stale — use DevTools to get an updated locator |
| Test fails repeatedly despite retry | Check if the Flipkart element/XPath has changed |
| `screenshots/` folder missing | Folder is auto-created; check folder write permissions |
| Maven build fails | Run `mvn clean install` to re-download all dependencies |
| RetryAnalyzer not triggering | Confirm class properly implements `IRetryAnalyzer` from TestNG |

---

## 👤 Author
**Name:** Sivasurya G
**Project:** Flipkart E-Commerce Automation
**Framework:** Selenium WebDriver 4 + TestNG + Maven POM + RetryAnalyzer
**Language:** Java 17
