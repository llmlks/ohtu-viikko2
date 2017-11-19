package ohtu;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Stepdefs {

    WebDriver driver;
    String baseUrl = "http://localhost:4567";

    @Before
    public void setUp() {
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
    }

    @Given("^login is selected$")
    public void login_selected() throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();
    }

    @Given("^command new user is selected$")
    public void new_user_selected() throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
    }

    @When("^correct username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void username_correct_and_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }

    @When("^correct username \"([^\"]*)\" and incorrect password \"([^\"]*)\" are given$")
    public void username_and_incorrect_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }

    @When("^nonexistent username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void nonexistent_username_and_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }

    @When("^a valid username \"([^\"]*)\" and password \"([^\"]*)\" and matching password confirmation are entered$")
    public void valid_username_password_and_password_confirmation_are_given(String username, String password) throws Throwable {
        createNewAccountWith(username, password, password);
    }

    @When("^a too short username \"([^\"]*)\" and valid password \"([^\"]*)\" and matching password confirmation are entered$")
    public void short_username_valid_password_and_valid_password_confirmation_are_given(String username, String password) throws Throwable {
        createNewAccountWith(username, password, password);
    }

    @When("^a valid username \"([^\"]*)\" and a too short password \"([^\"]*)\" and matching password confirmation are entered$")
    public void valid_username_short_password_and_valid_password_confirmation_are_given(String username, String password) throws Throwable {
        createNewAccountWith(username, password, password);
    }

    @When("^an existing username \"([^\"]*)\" and valid password \"([^\"]*)\" and matching password confirmation are entered$")
    public void taken_username_valid_password_and_valid_password_confirmation_are_given(String username, String password) throws Throwable {
        createNewAccountWith(username, password, password);
    }

    @When("^a valid username \"([^\"]*)\" and password \"([^\"]*)\" and non-matching password confirmation \"([^\"]*)\" are entered$")
    public void password_and_non_matching_password_confirmation_are_given(String username, String password, String confirmation) throws Throwable {
        createNewAccountWith(username, password, confirmation);
    }

    @Then("^user is logged in$")
    public void user_is_logged_in() throws Throwable {
        pageHasContent("Ohtu Application main page");
    }

    @Then("^user is not logged in and error message is given$")
    public void user_is_not_logged_in_and_error_message_is_given() throws Throwable {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }

    @Then("^a new user is created$")
    public void new_user_is_created() {
        pageHasContent("Welcome to Ohtu Application!");
    }

    @Then("^user is not created and error \"([^\"]*)\" is reported$")
    public void user_is_not_created_and_error_is_reported(String error)
            throws Throwable {
        pageHasContent(error);
        pageHasContent("Create username and give password");
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    /* helper methods */
    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }

    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();
    }

    private void createNewAccountWith(String username, String password, String confirmation) {

        assertTrue(driver.getPageSource()
                .contains("Create username and give password"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(confirmation);
        element = driver.findElement(By.name("signup"));
        element.submit();
    }
}
