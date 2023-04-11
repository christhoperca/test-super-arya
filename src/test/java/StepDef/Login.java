package StepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;

public class Login {
    WebDriver driver;

    @Given("Open web url {string}")
    public void openWebUrl(String url) {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(url);
    }

    @And("Input username {string} and password {string}")
    public void inputUsernameAndPassword(String username, String password) {
        driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys(username);
        driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(password);
    }

    @When("Click login button")
    public void clickLoginButton() {
        driver.findElement(By.xpath("//input[@data-test='login-button']")).click();
    }

    @Then("Should success login and redirected to homepage")
    public void shouldSuccessLoginAndRedirectedToHomepage() {
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='inventory_container']")).isDisplayed());
        driver.close();
        driver.quit();
    }

    @Then("Should success login and redirected to product page")
    public void shouldSuccessLoginAndRedirectedToProductPage() {
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='inventory_container']")).isDisplayed());
    }

    @Then("Failed to login and show error message locked out user")
    public void failedToLoginAndShowErrorMessageLockedOutUser() {
        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(), 'Epic sadface: Sorry, this user has been locked out.')]")).isDisplayed());
        driver.close();
        driver.quit();
    }

    @Then("Failed to login and show error message username and password do not match")
    public void failedToLoginAndShowErrorMessageUsernameAndPasswordDoNotMatch() {
        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(), 'Epic sadface: Username and password do not match any user in this service')]")).isDisplayed());
        driver.close();
        driver.quit();
    }

    @When("Sort product to Name A to Z")
    public void sortProductToNameAToZ() {
        driver.findElement(By.xpath("//select[@class='product_sort_container']")).click();
        driver.findElement(By.xpath("//option[@value='az']")).click();
    }

    @Then("Filter Name A to Z activated")
    public void filterNameAToZActivated() {
        Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(), 'Name (A to Z)')]")).isDisplayed());
        driver.close();
        driver.quit();
    }

    @When("Sort product to Name Z to A")
    public void sortProductToNameZToA() {
        driver.findElement(By.xpath("//select[@class='product_sort_container']")).click();
        driver.findElement(By.xpath("//option[@value='za']")).click();
    }

    @Then("Filter Name Z to A activated")
    public void filterNameZToAActivated() {
        Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(), 'Name (Z to A)')]")).isDisplayed());
        driver.close();
        driver.quit();
    }

    @When("Sort product to Price Low to High")
    public void sortProductToPriceLowToHigh() {
        driver.findElement(By.xpath("//select[@class='product_sort_container']")).click();
        driver.findElement(By.xpath("//option[@value='lohi']")).click();
    }

    @Then("Filter Price Low to High activated")
    public void filterPriceLowToHighActivated() {
        Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(), 'Price (low to high)')]")).isDisplayed());
        driver.close();
        driver.quit();
    }

    @When("Sort product to Price High to Low")
    public void sortProductToPriceHighToLow() {
        driver.findElement(By.xpath("//select[@class='product_sort_container']")).click();
        driver.findElement(By.xpath("//option[@value='hilo']")).click();
    }

    @Then("Filter Price High to Low activated")
    public void filterPriceHighToLowActivated() {
        Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(), 'Price (high to low)')]")).isDisplayed());
        driver.close();
        driver.quit();
    }

    @Then("Highest Product Appeared")
    public void highestProductAppeared() {
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(), 'Sauce Labs Fleece Jacket')]")).isDisplayed());
    }

    @When("Click The Highest Product Detail")
    public void clickTheHighestProductDetail() {
        driver.findElement(By.xpath("//a[@id='item_5_title_link']")).click();
    }

    @Then("Product Name and Price are the Highest")
    public void productNameAndPriceAreTheHighest() {
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(), 'Sauce Labs Fleece Jacket')]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='inventory_details_price']")).isDisplayed());
    }

    @When("Buy the product")
    public void buyTheProduct() {
        driver.findElement(By.xpath("//button[contains(text(), 'Add to cart')]")).click();
        driver.findElement(By.xpath("//span[@class='shopping_cart_badge']")).click();
        driver.findElement(By.xpath("//button[@name='checkout']")).click();
    }

    @And("Fill Bio")
    public void fillBio() {
        driver.findElement(By.xpath("//input[@data-test='firstName']")).sendKeys("Arya");
        driver.findElement(By.xpath("//input[@data-test='lastName']")).sendKeys("Widjaya");
        driver.findElement(By.xpath("//input[@data-test='postalCode']")).sendKeys("15560");
        driver.findElement(By.xpath("//input[@data-test='continue']")).click();
    }

    @Then("Order Status Created")
    public void orderStatusCreated() throws IOException {
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='checkout_summary_container']")).isDisplayed());
        driver.findElement(By.xpath("//button[contains(text(), 'Finish')]")).click();
        driver.get("https://www.saucedemo.com/checkout-complete.html");
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("C:\\Users\\Public\\Pictures\\screenshot.png"));
        driver.close();
        driver.quit();
    }

}
