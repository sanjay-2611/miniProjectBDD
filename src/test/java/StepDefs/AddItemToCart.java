package StepDefs;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class AddItemToCart {
	public static WebDriver driver;
	WebDriverWait wait;
	String item1;
	@BeforeAll
	public static void startup() {
				
		WebDriverManager.chromedriver().driverVersion("111.0.5563.65").setup();
		
		driver = new ChromeDriver();
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
	  Assert.assertEquals(wel.getText(), "Welcome kekran");
	}
	
	
	@When("Add Item to cart {string}")
	public void add_item_to_cart(String string)  {
		
		
		WebElement home= driver.findElement(By.xpath("//li/a[contains(text(),'Home')]"));
		  wait.until(ExpectedConditions.elementToBeClickable(home));
		  home.click();
		  driver.findElement(By.linkText(string)).click();
		  driver.findElement(By.xpath("//a[contains(text(),'Add to cart')]")).click();
		  wait.until(ExpectedConditions.alertIsPresent());
		  Alert alert=driver.switchTo().alert();		  
		  alert.accept();
	    
	}
	@Then("Item must added to the cart {string}")
	public void item_must_added_to_the_cart(String string1) {
		boolean count =false;
		driver.findElement(By.xpath("//a[contains(text(),'Cart')]")).click();
		  List<WebElement> cartlist=driver.findElements(By.xpath("//td[2]"));
		  for(WebElement cartslist: cartlist)
		  {
			  if(cartslist.getText().equalsIgnoreCase(string1)) {
			  Assert.assertEquals(cartslist.getText(), string1);
			  count=true;
			  }		  
		  }
		  
			  Assert.assertTrue(count);
		  
	}
	
	@When("List of items available in the cart")
	public void list_of_items_available_in_the_cart() {
		  List<WebElement> beforeDelete = driver.findElements(By.xpath("//tr[@class='success']"));
		  int before=beforeDelete.size();
		  Assert.assertTrue(before>0);
	}
	@Then("Delete an item from cart")
	public void delete_an_item_from_cart() {
		driver.findElement(By.xpath("(//td[4]//a)[1]")).click();
	}
	
	@When("Items should be available in the cart")
	public void items_should_be_available_in_the_cart() {
		List<WebElement> beforeDelete = driver.findElements(By.xpath("//tr[@class='success']"));
		  int before=beforeDelete.size();
		  Assert.assertTrue(before>0);
	}
	@Then("Place Order")
	public void place_order() {
		driver.findElement(By.xpath("//button[contains(text(),'Place Order')]")).click();
	}
	@Then("purchase items")
	public void purchase_items() {
		 driver.findElement(By.xpath("//input[@id='name']")).sendKeys("Jack Sparrow");
		  driver.findElement(By.xpath("//input[@id='country']")).sendKeys("Japan");
		  driver.findElement(By.xpath("//input[@id='city']")).sendKeys("Hiroshima");
		  driver.findElement(By.xpath("//input[@id='card']")).sendKeys("1234567890");
		  driver.findElement(By.xpath("//input[@id='month']")).sendKeys("February");
		  driver.findElement(By.xpath("//input[@id='year']")).sendKeys("2025");
		  driver.findElement(By.xpath("//button[text()='Purchase']")).click();
		  Assert.assertEquals(driver.findElement(By.xpath("(//h2)[3]")).getText(), "Thank you for your purchase!");
		  driver.findElement(By.xpath("//button[text()='OK']")).click();
	}
}
