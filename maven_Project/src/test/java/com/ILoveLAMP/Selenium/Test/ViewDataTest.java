package com.ILoveLAMP.Selenium.Test;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class ViewDataTest {
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
  public void testViewData() throws Exception {
	  
    driver.get(baseUrl);
    driver.findElement(By.id("inputEmail")).clear();
    driver.findElement(By.id("inputEmail")).sendKeys("Yang");
    driver.findElement(By.id("inputPassword")).clear();
    driver.findElement(By.id("inputPassword")).sendKeys("1234");
    driver.findElement(By.id("login")).click();
    assertEquals("Welcome back Yang", closeAlertAndGetItsText());
    
    driver.findElement(By.id("semenu1")).click();
    
    long startTime = System.currentTimeMillis();
    driver.findElement(By.id("ViewData")).click();
    boolean status = driver.findElement(By.xpath(".//*[@id='myData']")).isDisplayed();
    
    if(status){
    	long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println(elapsedTime);
    }
    
    assertTrue(status);
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
