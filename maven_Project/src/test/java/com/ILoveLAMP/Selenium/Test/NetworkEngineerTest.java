package com.ILoveLAMP.Selenium.Test;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class NetworkEngineerTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080/maven_Project/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testNetworkEngineer() throws Exception {
    driver.get(baseUrl);
    driver.findElement(By.id("inputEmail")).clear();
    driver.findElement(By.id("inputEmail")).sendKeys("Colm");
    driver.findElement(By.id("inputPassword")).clear();
    driver.findElement(By.id("inputPassword")).sendKeys("1234");
    driver.findElement(By.id("login")).click();
    assertEquals("Welcome back Colm", closeAlertAndGetItsText());
    
    driver.findElement(By.id("semenu7")).click();
    driver.findElement(By.id("semenu7")).click();
    driver.findElement(By.xpath(".//*[@id='NoOfFailureAndTotalDurationStart']")).sendKeys("19/02/2011 19:35");
    driver.findElement(By.xpath(".//*[@id='NoOfFailureAndTotalDurationEnd']")).sendKeys("19/02/2016 20:36");
    long startTime = System.currentTimeMillis();
    driver.findElement(By.xpath(".//*[@id='submitNoOfFailureAndTotalDuration']")).click();  
    
    
    boolean status = driver.findElement(By.xpath(".//*[@id='myNoOfFailureAndTotalDurationTable']/tbody/tr[1]/td[1]")).isDisplayed();
    boolean withIN2Second = false;
    if(status){
    	
    	long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println(elapsedTime/1000+" seconds");
        if (elapsedTime < 2000){
        	withIN2Second = true;
        }
    }
    assertTrue(status);
    assertTrue(withIN2Second);
    
    
    //////////////////////////////////////////////////////////////////////////////////////
    
    
    
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
