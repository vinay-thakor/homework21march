package homework;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class BookTest {

    private WebDriver driver;
    private String baseUrl = "https://demo.nopcommerce.com/";
    private JavascriptExecutor js;


    @Before

    public void openBrowser() {
        String baseUrl = "https://demo.nopcommerce.com/";
        System.setProperty("webdriver.chrome.driver", "divers/chromedriver.exe");
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(baseUrl);

    }

    @Test
    public void UserShouldNavigateBookPage() throws InterruptedException {

        driver.findElement(By.linkText("Books")).click();
        WebElement assertText = driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[contains(text(),'Books')]"));
        String expectedTex = "Books";
        Thread.sleep(3000);
        String actualTex = assertText.getText();
        Assert.assertEquals(expectedTex, actualTex);
    }

    @Test
    public void USerShouldVerifyBookByATZ() throws InterruptedException {
        driver.findElement(By.linkText("Books")).click();
        // WebElement assertText = driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[contains(text(),'Books')]"));
        driver.findElement(By.xpath("//select[@id='products-orderby']"));

        //Web element position
        WebElement positionDropDown = driver.findElement(By.cssSelector("select#products-orderby"));

        Select select = new Select(positionDropDown);
        select.selectByIndex(1);
        Thread.sleep(2000);


        //scroll down page
        js.executeScript("window.scrollBy(0,500);");
        Thread.sleep(2000);

        driver.findElement(By.xpath("//div[@class='item-grid']//div[1]//div[1]//div[2]//div[3]//div[2]//input[1]")).click();



        WebElement assertTex = driver.findElement(By.xpath("//p[@class='content']"));
        String expectedText ="The product has been added to your shopping cart";
        String actualTex = assertTex.getText();
        Assert.assertEquals(expectedText,actualTex);

    }

    @After
    public void closeBrowser() {
        //driver.quit();
    }
}
