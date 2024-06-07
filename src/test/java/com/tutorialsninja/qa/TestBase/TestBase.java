package com.tutorialsninja.qa.TestBase;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.tutorialsNinja.qa.Utilities.Utils;

//import dev.failsafe.internal.util.Durations;

public class TestBase {
	public WebDriver driver;
	public Properties prop;
	public Properties dataprop;
	public FileInputStream ip;
	public FileInputStream ip1;
	
	
	
	
	public TestBase() throws Exception {
		prop = new Properties();
		ip = new FileInputStream (System.getProperty("user.dir") + "\\src\\test\\java\\com\\tutorialsninja\\qa\\Config\\config.properties");
		prop.load(ip);     
		
		dataprop = new Properties();
		ip1 = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\java\\com\\tutorialsninja\\qa\\TestData\\dataProp.properties");
		}
	
	
public WebDriver intializeBrowserAndOpenApplication(String browserName) {
	if (browserName.equals("Chrome")) {
		ChromeOptions options = new ChromeOptions();
		options.setPageLoadStrategy(PageLoadStrategy.EAGER);
		options.addArguments("--start-maximized");
		options.addArguments("--incognito");
		options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation", "disable-infobars"));
		driver = new ChromeDriver(options);
	} else if (browserName.equals("Firefox")) {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	} else if(browserName.equals("Edge")) {
		driver = new EdgeDriver();
		driver.manage().window().maximize();
	} else if(browserName.equals("Safari")) {
		driver = new SafariDriver();
		driver.manage().window().maximize();
	} else {
		System.out.println("Browser Not Matching");
	}
	//driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utils.Implicit_Wait));
	driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utils.Page_Load_Timeout));
	driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(Utils.Script_Timeout));	
	driver.get(prop.getProperty("url"));		
	return driver;
	}

}
