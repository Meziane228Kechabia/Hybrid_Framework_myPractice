package com.tutorialsninja.qa.Testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsNinja.qa.Utilities.Utils;
import com.tutorialsninja.qa.TestBase.TestBase;

public class RegisterTest extends TestBase {
	public RegisterTest() throws Exception {
		super();
	}
	public WebDriver driver;
@BeforeMethod
public void registerSetUp() {
	//driver = new ChromeDriver();
	//driver.manage().window().maximize();
	//driver.get("https://tutorialsninja.com/demo");
	
	driver = intializeBrowserAndOpenApplication(prop.getProperty("browser"));
	
	driver.findElement(By.linkText("My Account")).click();
	driver.findElement(By.linkText("Register")).click();
	}

@Test (priority =1)
public void registerWithMandatoryDetails() {
	driver.findElement(By.id("input-firstname")).sendKeys(dataprop.getProperty("firstname"));
	driver.findElement(By.id("input-lastname")).sendKeys(dataprop.getProperty("lastname"));
	driver.findElement(By.id("input-email")).sendKeys(Utils.emailWithDateTimeStamp());
	driver.findElement(By.id("input-telephone")).sendKeys(dataprop.getProperty("telephone"));
	driver.findElement(By.id("input-password")).sendKeys(dataprop.getProperty("password"));
	driver.findElement(By.id("input-confirm")).sendKeys(dataprop.getProperty("confirmPassword"));
	driver.findElement(By.xpath("//input[@name='agree']")).click();
	driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
	Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"content\"]/h1")).isDisplayed());                                       
	}
@Test (priority =2)
public void registerWithALLDetails() {
	driver.findElement(By.id("input-firstname")).sendKeys(dataprop.getProperty("firstname"));
	driver.findElement(By.id("input-lastname")).sendKeys(dataprop.getProperty("lastname"));
	driver.findElement(By.id("input-email")).sendKeys(Utils.emailWithDateTimeStamp());
	driver.findElement(By.id("input-telephone")).sendKeys(dataprop.getProperty("telephone"));
	driver.findElement(By.id("input-password")).sendKeys(dataprop.getProperty("password"));
	driver.findElement(By.id("input-confirm")).sendKeys(dataprop.getProperty("confirmPassword"));	
	driver.findElement(By.xpath("//input[@type='radio' and @ name='newsletter' and @value='1']")).click();
	//driver.findElement(By.linkText("agree")).click();
	driver.findElement(By.xpath("//*[@id=\"account-register\"]/div[1]")).click();
	driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
	Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).isDisplayed());                                       
	}
@Test (priority =3)
public void registerWithExistingEmail() {
	driver.findElement(By.id("input-firstname")).sendKeys(dataprop.getProperty("firstname"));
	System.out.println("print the value of:" +"firstname");
	driver.findElement(By.id("input-lastname")).sendKeys(dataprop.getProperty("lastname"));
	driver.findElement(By.id("input-email")).sendKeys("seleniumpanda@gmail.com");
	driver.findElement(By.id("input-telephone")).sendKeys(dataprop.getProperty("telephone"));
	driver.findElement(By.id("input-password")).sendKeys(dataprop.getProperty("password"));
	driver.findElement(By.id("input-confirm")).sendKeys(dataprop.getProperty("confirmPassword"));	
	driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/div/input[1]")).click();
	driver.findElement(By.cssSelector("input.btn.btn-primary")).click();	
	String expectedMessage = dataprop.getProperty("existingEmailWarningMessage");
	Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"account-register\"]/div[1]")).getText().contains(expectedMessage));                                      
	}
@Test(priority =4)
public void registerWithoutDetails() {
	//driver.findElement(By.xpath("//input[@type='checkbox' and @name='agree']")).click();
	driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
// You must agree to the privacy policy
	String expectedPrivacyPolicyWarningMessage = dataprop.getProperty("PrivacyPolicyWarningMessage");
	Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"account-register\"]/div[1]")).getText().contains(expectedPrivacyPolicyWarningMessage));
// Assertion for the first name field
	String expectedWarningMessageFirstName = dataprop.getProperty("firstNameWarningMessage");
	Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"account\"]/div[2]/div/div")).getText().contains(expectedWarningMessageFirstName));
// Another way of assertion of the first name above	
	String actualFirstNamemessageWarning = driver.findElement(By.xpath("//*[@id=\"account\"]/div[2]/div/div")).getText();
	String expectedWarningMessageFirstName1 = dataprop.getProperty("firstNameWarningMessage");
	Assert.assertTrue(actualFirstNamemessageWarning.equals(expectedWarningMessageFirstName1));	
// Assertion for the last name field
	String lastNameWarningMessage = dataprop.getProperty("lastNameWarningMessage");
	Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"account\"]/div[3]/div/div")).getText().contains(lastNameWarningMessage));
// Assertion for the e-mail
	String expectedWarningEmail = dataprop.getProperty("emailWarningMessage");
	Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"account\"]/div[4]/div/div")).getText().contains(expectedWarningEmail));
// Assertion of the telephone  field
	String expectedWarningPhoneMessage = dataprop.getProperty("phoneWarningMessage");
	Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"account\"]/div[5]/div/div")).getText().contains(expectedWarningPhoneMessage));
// Assertion of the password
	String expectedWarningMessagePassword = dataprop.getProperty("paswordWarningMessage");
	Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"content\"]/form/fieldset[2]/div[1]/div/div")).getText().contains(expectedWarningMessagePassword));
// Assertion of the 	
	}
@AfterMethod
public void tearDown() {
	driver.quit();
	}	
}

	


