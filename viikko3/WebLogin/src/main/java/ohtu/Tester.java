package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import java.util.Random;

public class Tester {

    private static WebDriver driver;

    public static void main(String[] args) {
        ChromeDriverManager.getInstance().setup();

        successfulLogin();
        loginIncorrectPassword();
        loginIncorrectUsername();
        registerNewUser();
        logoutAfterRegistration();
    }

    private static void sleep(int n) {
        try {
            Thread.sleep(n * 1000);
        } catch (Exception e) {
        }
    }

    private static void successfulLogin() {
        driver = new ChromeDriver();

        driver.get("http://localhost:4567");

        sleep(2);

        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));

        sleep(2);
        element.submit();

        sleep(3);

        driver.quit();
    }

    private static void loginIncorrectPassword() {
        driver = new ChromeDriver();

        driver.get("http://localhost:4567");

        sleep(2);

        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("login"));

        sleep(2);
        element.submit();

        sleep(3);

        driver.quit();
    }

    private static void loginIncorrectUsername() {
        driver = new ChromeDriver();

        driver.get("http://localhost:4567");

        sleep(2);

        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("minna");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));

        sleep(2);
        element.submit();

        sleep(3);

        driver.quit();
    }

    private static void registerNewUser() {
        driver = new ChromeDriver();

        driver.get("http://localhost:4567");

        sleep(2);

        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();

        sleep(2);

        Random rand = new Random();
        String username = "ioni" + rand.nextInt(1000000);
        element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys("ohtu2017");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("ohtu2017");
        element = driver.findElement(By.name("signup"));

        sleep(2);
        element.submit();

        sleep(3);

        driver.quit();
    }

    private static void logoutAfterRegistration() {
        driver = new ChromeDriver();

        driver.get("http://localhost:4567");

        sleep(2);

        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();

        sleep(2);

        Random rand = new Random();
        String username = "nylon" + rand.nextInt(1000000);
        element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys("ohtu2017");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("ohtu2017");
        element = driver.findElement(By.name("signup"));

        sleep(2);
        element.submit();

        sleep(3);

        element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();

        sleep(2);

        element = driver.findElement(By.linkText("logout"));
        element.click();

        sleep(3);

        driver.quit();
    }
}
