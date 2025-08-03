package demo;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WrapperClass {

    public static void waitForElement(ChromeDriver driver, By by, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public static void clickElement(ChromeDriver driver, By by) {
        WebElement element = driver.findElement(by);
        element.click();
    }

    public static void enterText(ChromeDriver driver, By by, String text) {
        WebElement element = driver.findElement(by);
        element.clear();
        element.sendKeys(text);
    }

    public static void clearText(ChromeDriver driver, By by) {
        WebElement element = driver.findElement(by);
        element.clear();
    }

    public static void pressEnter(ChromeDriver driver, By by) {
        WebElement element = driver.findElement(by);
        element.sendKeys(Keys.ENTER);
    }

    public static String getCurrentTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }

    public static String getCurrentTimestampWithMillis() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        return LocalDateTime.now().format(formatter);
    }

    public static void acceptAlert(ChromeDriver driver) {
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public static void dismissAlert(ChromeDriver driver) {
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
    }

    public static String getAlertText(ChromeDriver driver) {
        Alert alert = driver.switchTo().alert();
        return alert.getText();
    }

    public static Boolean VerifyText(ChromeDriver driver, By by, String expectedText) {
        WebElement element = driver.findElement(by);
        String actualText = element.getText();
        return actualText.contains(expectedText);
    }

    public static void switchToFrame(ChromeDriver driver, By by) {
        WebElement frame = driver.findElement(by);
        driver.switchTo().frame(frame);
    }

    public static void switchToDefaultContent(ChromeDriver driver) {
        driver.switchTo().defaultContent();
    }

    public static void scrollToElement(ChromeDriver driver, By by) {
        WebElement element = driver.findElement(by);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void scrollToTop(ChromeDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
    }

    public static void scrollToBottom(ChromeDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    public static void takeScreenshot(ChromeDriver driver, String filePath) {
        // Implement screenshot logic here
        // This is a placeholder as actual implementation requires additional libraries
        System.out.println("Taking screenshot and saving to: " + filePath);
    }

    public static void waitForPageLoad(ChromeDriver driver) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState")
                        .equals("complete"));
    }

    public static void selectDropdownByVisibleText(ChromeDriver driver, By by, String visibleText) {
        WebElement dropdown = driver.findElement(by);
        Select select = new Select(dropdown);
        select.selectByVisibleText(visibleText);
    }

    public static void dropdownByVisibleText(ChromeDriver driver, By by, String visibleText)
            throws InterruptedException {
        WebElement dropdown = driver.findElement(by);
        clickUsingJavaScript(driver, dropdown);
        Thread.sleep(1000); // Wait for dropdown options to be visible
        WebElement option = driver.findElement(
                By.xpath("(//div[@class='kRoyt MbhUzd']/following-sibling::span[text()='" + visibleText + "'])[2]"));
        if (option != null) {
            clickUsingJavaScript(driver, option);
        } else {
            System.out.println("Option with visible text '" + visibleText + "' not found in dropdown.");
        }
    }

    public static void clickUsingJavaScript(ChromeDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    public static void selectDateBeforeDaysFromToday(ChromeDriver driver, By by, int days) {
        LocalDateTime date = LocalDateTime.now().minusDays(days);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = date.format(formatter);
        WebElement dateField = driver.findElement(by);
        dateField.clear();
        dateField.sendKeys(formattedDate);
    }

    public static void navigateToUrl(ChromeDriver driver, String url) {
        driver.get(url);
    }

    public static String getPageTitle(ChromeDriver driver) {
        return driver.getTitle();
    }

    public static String getCurrentUrl(ChromeDriver driver) {
        return driver.getCurrentUrl();
    }

    public static String getPageSource(ChromeDriver driver) {
        return driver.getPageSource();
    }

    public static void refreshPage(ChromeDriver driver) {
        driver.navigate().refresh();
    }

    public static void back(ChromeDriver driver) {
        driver.navigate().back();
    }

    public static void forward(ChromeDriver driver) {
        driver.navigate().forward();
    }

    public static void maximizeWindow(ChromeDriver driver) {
        driver.manage().window().maximize();
    }

    public static void setImplicitWait(ChromeDriver driver, int seconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
    }

}
