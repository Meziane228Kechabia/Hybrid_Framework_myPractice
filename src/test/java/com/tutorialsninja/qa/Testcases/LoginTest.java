package com.tutorialsninja.qa.Testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.TestBase.TestBase;

public class LoginTest extends TestBase {
	public LoginTest() throws Exception {
		super();
	}
	public WebDriver driver;	
@BeforeMethod
public void loginSetup() {
    driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("https://tutorialsninja.com/demo");
	driver.findElement(By.linkText("My Account")).click();
	driver.findElement(By.linkText("Login")).click();
	}
@Test(priority =1)
public void verifyLoginWithValidCredentials() {
	driver.findElement(By.id("input-email")).sendKeys("seleniumpanda@gmail.com");
	driver.findElement(By.id("input-password")).sendKeys("Selenium@123");
	driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
	Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());	
	}
@Test(priority =2)
public void verifyLoginWithInvalidCredentials() {
	driver.findElement(By.id("input-email")).sendKeys("seleniumpanda@gmaill.com");
	driver.findElement(By.id("input-password")).sendKeys("Selenium@123456");
	driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
	}
@Test(priority =3)
public void verifyLoginWithValidEmailInvalidPassword() {
	driver.findElement(By.id("input-email")).sendKeys("seleniumpanda@gmail.com");
	driver.findElement(By.id("input-password")).sendKeys("AzulFellawen");
	driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
	String expectedWarningmessage = "Warning: No match for E-Mail Address and/or Password.";
	Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"account-login\"]/div[1]")).getText().contains(expectedWarningmessage));
	}
@Test(priority =4)
public void verifyLoginWithNoCredentials() {
	driver.findElement(By.id("input-email")).sendKeys("");
	driver.findElement(By.id("input-password")).sendKeys("");
	driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
	String expectedWarningmessage = "Warning: No match for E-Mail Address and/or Password.";
	Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"account-login\"]/div[1]")).getText().contains(expectedWarningmessage));
	}
@Test(priority =5)
public void verifyLoginWithEmptyPassword() {
	driver.findElement(By.id("input-email")).sendKeys("seleniumpanda@gmail.com");
	driver.findElement(By.id("input-password")).sendKeys("");
	driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
	String expectedWarningmessage = "Warning: No match for E-Mail Address and/or Password.";
	Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"account-login\"]/div[1]")).getText().contains(expectedWarningmessage));
	}
@AfterMethod
public void tearDown() {
driver.quit();
	}
}