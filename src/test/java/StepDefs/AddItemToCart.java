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
	public static void startup() 
	{				
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));	    
	}

	@Given("User is on launch Page")
	public void user_is_on_launch_page() 
	{
		wait= new WebDriverWait(driver, Duration.ofSeconds(30));
	driver.get("https://www.demoblaze.com");	 
	}
	
	
	@When("User Login")
	public void user_login() throws InterruptedException 
	{		
		driver.findElement(By.id("login2")).click();
		Thread.sleep(1000);
		  driver.findElement(By.id("loginusername")).sendKeys("kekran");
		  Thread.sleep(1000);
		  driver.findElement(By.id("loginpassword")).sendKeys("kekran");
		  driver.findElement(By.xpath("//button[text()='Log in']")).click();	    
	}
	
	@Then("Should display Home Page")
	public void should_display_home_page() throws InterruptedException
	{
		Thread.sleep(3000);
		 WebElement wel= driver.findElement(By.xpath("//li/a[text()='Welcome kekran']"));
		  Assert.assertEquals(wel.getText(), "Welcome kekran");
	}
	
	@When("Add an item to cart {string}")
	public void add_an_item_to_cart(String string)
	{
		wait=new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement home= driver.findElement(By.xpath("//li/a[contains(text(),'Home')]"));
//		  wait.until(ExpectedConditions.elementToBeClickable(home));
		  home.click();
		  driver.findElement(By.linkText(string)).click();
		  driver.findElement(By.xpath("//a[contains(text(),'Add to cart')]")).click();
		  wait.until(ExpectedConditions.alertIsPresent());
		  Alert alert=driver.switchTo().alert();		  
		  alert.accept();
	}
	@Then("Items must be added to cart {string}")
	public void items_must_be_added_to_cart(String string) 
	{
		wait=new WebDriverWait(driver, Duration.ofSeconds(30));
		boolean count =false;
		driver.findElement(By.xpath("//a[contains(text(),'Cart')]")).click();
		 List<WebElement> cartlist=driver.findElements(By.xpath("//td[2]"));
//		wait.until(ExpectedConditions.visibilityOfAllElements(cartlist));
		 
		  for(WebElement cartslist: cartlist)
		  {
			  if(cartslist.getText().equalsIgnoreCase(string)) {
			  Assert.assertEquals(cartslist.getText(), string);
			  count=true;
			  }		  
		  }
		  
			  Assert.assertTrue(count);
		  
		
		
		
	}
	
	@When("List of items should be available in cart")
	public void list_of_items_should_be_available_in_cart() { 
		wait=new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.findElement(By.xpath("//a[contains(text(),'Cart')]")).click();
		List<WebElement> beforeDelete = driver.findElements(By.xpath("//tr[@class='success']"));
		wait.until(ExpectedConditions.visibilityOfAllElements(beforeDelete));
		  int before=beforeDelete.size();
		  Assert.assertTrue(before>0);
	}
	@Then("Delete an item from cart")
	public void delete_an_item() throws InterruptedException {
		driver.findElement(By.xpath("(//td[4]//a)[1]")).click();
		Thread.sleep(2000);
		
	}
	@Given("Items should be available in cart")
	public void items_should_be_available_in_cart() 
	{		wait=new WebDriverWait(driver, Duration.ofSeconds(30));
		List<WebElement> beforeDelete = driver.findElements(By.xpath("//tr[@class='success']"));
		wait.until(ExpectedConditions.visibilityOfAllElements(beforeDelete));
		  int before=beforeDelete.size();
		  Assert.assertTrue(before>0);
		  
		  
		 
	}
	@When("Place Order")
	public void Place_Order() throws InterruptedException
	{
		driver.findElement(By.xpath("//button[contains(text(),'Place Order')]")).click();
		
	}
	
	@Then("Purchase Items")
	public void Purchase_Items() throws InterruptedException
	{
		Thread.sleep(4000);
		  driver.findElement(By.xpath("//input[@id='name']")).sendKeys("Jack Sparrow");
		  driver.findElement(By.xpath("//input[@id='country']")).sendKeys("Japan");
		  driver.findElement(By.xpath("//input[@id='city']")).sendKeys("Hiroshima");
		  driver.findElement(By.xpath("//input[@id='card']")).sendKeys("1234567890");
		  driver.findElement(By.xpath("//input[@id='month']")).sendKeys("February");
		  driver.findElement(By.xpath("//input[@id='year']")).sendKeys("2025");
		  driver.findElement(By.xpath("//button[text()='Purchase']")).click();
		  Assert.assertEquals(driver.findElement(By.xpath("(//h2)[3]")).getText(), "Thank you for your purchase!");
		  Thread.sleep(2000);
		  driver.findElement(By.xpath("//button[text()='OK']")).click();
	}
	
	
	
}
