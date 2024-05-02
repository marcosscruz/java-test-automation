package entities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    public String url;
    public WebDriver driver;

    @BeforeMethod
    public void preCond(){
        url = System.getProperty("user.dir")+"/src/paginas/componentes.html";
        driver = new ChromeDriver();
        driver.get(url);
        //driver.manage().window().maximize();
    }

    @AfterMethod
    public void posCond(){
      driver.quit();
    }

    protected WebDriver getDriver() {
        return driver;
    }
}
