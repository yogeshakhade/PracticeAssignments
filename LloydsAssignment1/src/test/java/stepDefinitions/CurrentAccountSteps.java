package stepDefinitions;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CurrentAccountSteps {
	WebDriver driver;
	FileInputStream file;
	Properties prop=new Properties();
	
	@Before
	public void before() {
		try {
			file=new FileInputStream("src/test/java/fileResources/currentaccountsteps.properties");
		} catch (Exception e) {
			System.out.println("file not found");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			prop.load(file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("file not loaded");
			e.printStackTrace();
		}
		
		WebDriverManager.chromedriver().setup();
	    driver= new ChromeDriver();
	    driver.manage().window().maximize();
	}
	
	@Given("Open browser and launch the URL")
	public void open_browser_and_launch_the_url() {		
        driver.get(prop.getProperty("url"));
	}
	@When("user click on current account from Products and Services dropdown")
	public void user_click_on_current_account_from_products_and_services_dropdown() {
		
		WebElement productsAndServicesTab = driver.findElement(By.linkText(prop.getProperty("productsAndServicesTab")));
		
		Actions actions = new Actions(driver);

		actions.moveToElement(productsAndServicesTab);
		
		WebElement currentAccount = driver.findElement(By.linkText(prop.getProperty("currentAccountTab")));

		actions.moveToElement(currentAccount);

		actions.click().build().perform();

	}
	@Then("current account information is visible to user")
	public void current_account_information_is_visible_to_user() {
		
		String title=driver.findElement(By.xpath(prop.getProperty("currentAccountsPageTitle"))).getText();
		//verifying title of page opened
		assertEquals(title,"Current accounts");
		String platinumFees=driver.findElement(By.xpath(prop.getProperty("platinumMonthlyPrice"))).getText();
		//verifying monthly fees of platinum current account
		assertEquals(platinumFees,"£21 monthly fee to maintain the account.");
		
		List<WebElement> li1=driver.findElements(By.xpath(prop.getProperty("platinumProductsRow1")));
		List<WebElement> li2=driver.findElements(By.xpath(prop.getProperty("platinumProductsRow2")));
		int numberOfProducts=li1.size()+li2.size();
		assertEquals(4,numberOfProducts);
		
	}
	
	@After
	public void after() {
		driver.close();
	}

}
