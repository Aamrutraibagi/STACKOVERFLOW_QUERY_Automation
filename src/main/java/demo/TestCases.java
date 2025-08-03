package demo;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.bouncycastle.jcajce.provider.symmetric.AES.Wrap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;

import com.google.common.base.Verify;

import java.util.logging.Level;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCases {
    ChromeDriver driver;

    By searchBox = By.xpath("//*[@placeholder=\"Search…\"]");
    By ExpectedPythonList = By.xpath("//a[contains(text(),'python')]");
    By TagsButton = By.xpath("//span[contains(text(),'Tags')]");
    By JSFilter = By.xpath("(//a[contains(text(),'javascript')])[1]");
    By ExpectedJSList = By.xpath("//a[contains(text(),'javascript')]");
    By moreButton = By.cssSelector("span[data-text='More']");
    By scoreFilter = By.xpath("//a[contains(.,'Score')]");
    By listOfvotes = By.xpath("//span[text()=\"votes\"]/preceding-sibling::span");

    public TestCases() {
        System.out.println("Constructor: TestCases");

        WebDriverManager.chromedriver().timeout(30).setup();
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        // Set log level and type
        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);

        // Set path for log file
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "chromedriver.log");

        driver = new ChromeDriver(options);

        // Set browser to maximize and wait
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    public void endTest() {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    // TODO: Write Test Cases Here
    // TestCase01: Verify Stack Overflow Homepage URL
    public void testCase01() {
        System.out.println("Start Test case: testCase01");
        WrapperClass.navigateToUrl(driver, "https://stackoverflow.com/");
        String url = WrapperClass.getCurrentUrl(driver);
        if (url.contains("stackoverflow.com")) {
            System.out.println("URL is correct: " + url);
        } else {
            System.out.println("URL is incorrect: " + url);
        }
        System.out.println("end Test case: testCase01");
    }

    // TestCase02: Search for a Specific Topic and Verify Results
    public void testCase02() throws InterruptedException {
        System.out.println("Start Test case: testCase02");
        Thread.sleep(2000); // Wait for the page to load
        WrapperClass.enterText(driver, searchBox, "Python list comprehension");
        WrapperClass.pressEnter(driver, searchBox);
        List<WebElement> listofResult = driver.findElements(ExpectedPythonList);
        for (WebElement result : listofResult) {
            if (result.getText().contains("python")) {
                System.out.println("Found expected result: " + result.getText());
            } else {
                System.out.println("Did not find expected result: " + result.getText());
            }
        }
        System.out.println("end Test case: testCase02");
    }

    public void testCase03() throws InterruptedException {
        System.out.println("Start Test case: testCase03");
        WrapperClass.clickElement(driver, TagsButton);
        Thread.sleep(2000); // Wait for the page to load
        WrapperClass.clickElement(driver, JSFilter);
        Thread.sleep(2000); // Wait for the page to load
        List<WebElement> listofResult = driver.findElements(ExpectedJSList);
        for (WebElement result : listofResult) {
            if (result.getText().contains("javascript")) {
                System.out.println("Found expected result: " + result.getText());
            } else {
                System.out.println("Did not find expected result: " + result.getText());
            }
        }
        System.out.println("end Test case: testCase03");
    }

    // TestCase04: Verify Sorting by "Score" on Query Page
    // Expected Result: The displayed questions are sorted in descending order of
    // scores, with the most upvoted questions shown first.

    public void testCase04() throws InterruptedException {
        System.out.println("Start Test case: testCase04");
        WrapperClass.clickElement(driver, moreButton);
        Thread.sleep(2000); // Wait for the page to load
        WrapperClass.clickElement(driver, scoreFilter);
        Thread.sleep(2000); // Wait for the page to load
        List<WebElement> listOfVotes = driver.findElements(listOfvotes);
        List<Integer> listOfVotesArray = new ArrayList<>();
        for (WebElement Votes : listOfVotes) {
            listOfVotesArray.add(Votes.getText().isEmpty() ? 0 : Integer.parseInt(Votes.getText()));
        }
        ArrayList<Integer> sortedVotes = new ArrayList<>(listOfVotesArray);
        Collections.sort(sortedVotes, Collections.reverseOrder());

        if (listOfVotesArray.equals(sortedVotes)) {
            System.out.println("The questions are sorted in descending order of scores.");
        } else {
            System.out.println("The questions are NOT sorted in descending order of scores.");
        }

        System.out.println("end Test case: testCase04");
    }

}
