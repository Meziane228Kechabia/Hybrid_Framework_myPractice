package com.tutorialsninja.qa.Testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.TestBase.TestBase;

public class SearchProductTets extends TestBase {
		public SearchProductTets() throws Exception{
			super();
		}
public WebDriver driver;
@BeforeMethod
public void searchSetUp() {
		 //driver = new ChromeDriver();
		 //driver.manage().window().maximize();
		 //driver.get("https://tutorialsninja.com/demo");
	driver = intializeBrowserAndOpenApplication(prop.getProperty("browser"));
	}
@Test	(priority =1)
public void verifySerchWithValidProduct() {
	driver.findElement(By.xpath("//*[@id=\"search\"]/input")).sendKeys("HP");
	driver.findElement(By.xpath("//*[@id=\"search\"]/span/button")).click();
	Assert.assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed());
	}
@Test	(priority =2)
public void verifySerchWithInvalidProduct() {
	driver.findElement(By.xpath("//*[@id=\"search\"]/input")).sendKeys("Dell");
	driver.findElement(By.xpath("//*[@id=\"search\"]/span/button")).click();
	String expectedWarnningMessage = "Products meeting the search criteria";
	Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"content\"]/h2")).getText().contains(expectedWarnningMessage));
	
}
@AfterMethod
public void tearDown() {
	driver.quit();
	}
}
