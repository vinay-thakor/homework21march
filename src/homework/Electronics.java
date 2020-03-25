package homework;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class Electronics {

    private WebDriver driver;
    private JavascriptExecutor jse;

    @Before
    public void openBrowser() {
        String baseURL = "https://demo.nopcommerce.com/";
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        jse = (JavascriptExecutor) driver;
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(baseURL);
    }

    @Test
    public void userShouldNavigateToElectronicPage() {
        driver.findElement(By.linkText("Electronics")).click();
        WebElement assertText = driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[contains(text(),'Electronics')]"));
        String expectedText = "Electronics";
        String actualText = assertText.getText();
        Assert.assertEquals(actualText, expectedText);
    }

    @Test
    public void mouseHovertoElectronicTab() throws InterruptedException {
        Actions act = new Actions(driver);
        act.moveToElement(driver.findElement(By.xpath("//body/div[@class='master-wrapper-page']/div[@class='header-menu']/ul[@class='top-menu notmobile']/li[2]"))).perform();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//body/div[@class='master-wrapper-page']/div[@class='header-menu']/ul[@class='top-menu notmobile']/li[2]")).click();
    }

    @Test
    public void sortPriceLowToHigh() throws InterruptedException {

        WebElement electronics = driver.findElement(By.linkText("Electronics"));
        Actions actions = new Actions(driver);
        actions.moveToElement(electronics).perform();

        WebElement cameraNphoto = driver.findElement(By.linkText("Camera & photo"));
        actions.moveToElement(cameraNphoto).perform();
        cameraNphoto.click();

        //WebElement Position drop down menu
        WebElement sortByDropDownMenu = driver.findElement(By.id("products-orderby"));
        Select select = new Select(sortByDropDownMenu);
        select.selectByIndex(3);
        Thread.sleep(2000);
        jse.executeScript("window.scrollBy(0,500);");
        Thread.sleep(2000);

    }
}

