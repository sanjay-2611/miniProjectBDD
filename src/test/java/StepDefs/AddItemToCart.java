package StepDefs;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class AddItemToCart {
	static WebDriver driver;
	WebDriverWait wait;
	String item1;
	@BeforeAll
	public static void startup() {
				
		WebDriverManager.chromedriver().setup();
	    driver=new ChromeDriver();
	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
	    
	}

	@Given("Login Into App")
	public void login_into_app() {
		wait= new WebDriverWait(driver, Duration.ofSeconds(30));
	driver.get("https://www.demoblaze.com");
	driver.findElement(By.id("login2")).click();
	  driver.findElement(By.id("loginusername")).sendKeys("kekran");
	  driver.findElement(By.id("loginpassword")).sendKeys("kekran");
	  driver.findElement(By.xpath("//button[text()='Log in']")).click();
	  WebElement wel= driver.findElement(By.xpath("//li/a[text()='Welcome kekran']"));
//      wait.until(ExpectedConditions.attributeToBe(wel, "nameofuser", "Welcome kekran"));
	  Assert.assertEquals(wel.getText(), "Welcome kekran");
	}
	
	
	@When("Add Item to cart")
	public void add_item_to_cart(DataTable dataTable) {
		wait= new WebDriverWait(driver, Duration.ofSeconds(30));
		List<String> data= dataTable.asList();
		String item= data.get(1);
		item1=item;
		
		WebElement home= driver.findElement(By.xpath("//li/a[contains(text(),'Home')]"));
		  wait.until(ExpectedConditions.elementToBeClickable(home));
		  home.click();
		  driver.findElement(By.linkText(item)).click();
		  driver.findElement(By.xpath("//a[contains(text(),'Add to cart')]")).click();
		  wait.until(ExpectedConditions.alertIsPresent());
		  Alert alert=driver.switchTo().alert();		  
		  alert.accept();
	    
	}
	@Then("Item must added to the cart")
	public void item_must_added_to_the_cart() {
		boolean count =false;
		driver.findElement(By.xpath("//a[contains(text(),'Cart')]")).click();
		  List<WebElement> cartlist=driver.findElements(By.xpath("//td[2]"));
		  for(WebElement cartslist: cartlist)
		  {
			  if(cartslist.getText().equalsIgnoreCase(item1)) {
			  Assert.assertEquals(cartslist.getText(), item1);
			  count=true;
			  }		  
		  }
		  
			  Assert.assertTrue(count);
		  
	}
}
