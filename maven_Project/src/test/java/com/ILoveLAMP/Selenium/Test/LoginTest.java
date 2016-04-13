package com.ILoveLAMP.Selenium.Test;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LoginTest {
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
  public void testLogin() throws Exception {
    driver.get(baseUrl);
    driver.findElement(By.id("inputPassword")).clear();
    driver.findElement(By.id("inputPassword")).sendKeys("");
    driver.findElement(By.id("inputEmail")).clear();
    driver.findElement(By.id("inputEmail")).sendKeys("");
    driver.findElement(By.id("inputPassword")).clear();
    driver.findElement(By.id("inputPassword")).sendKeys("");
    driver.findElement(By.id("login")).click();
    assertEquals("Please enter user name and password to login", closeAlertAndGetItsText());
    driver.findElement(By.id("inputEmail")).clear();
    driver.findElement(By.id("inputEmail")).sendKeys("Yang");
    driver.findElement(By.id("login")).click();
    assertEquals("Please enter your password!", closeAlertAndGetItsText());
    driver.findElement(By.id("inputEmail")).clear();
    driver.findElement(By.id("inputEmail")).sendKeys("");
    driver.findElement(By.id("inputPassword")).clear();
    driver.findElement(By.id("inputPassword")).sendKeys("1234");
    driver.findElement(By.id("login")).click();
    assertEquals("Please enter your user name!", closeAlertAndGetItsText());
    driver.findElement(By.id("inputEmail")).clear();
    driver.findElement(By.id("inputEmail")).sendKeys("Yang");
    driver.findElement(By.id("inputPassword")).clear();
    driver.findElement(By.id("inputPassword")).sendKeys("1234");
    driver.findElement(By.id("login")).click();
    assertEquals("Welcome back Yang", closeAlertAndGetItsText());
    
	// Launch the Admin page
	driver.get(baseUrl + "Admin.html");
	
	String actualUrl = driver.getCurrentUrl();
	String expectedUrl = baseUrl + "Admin.html";
	assertEquals(actualUrl, expectedUrl);
	
	// Storing Title name in the String variable
	String title = driver.getTitle();
	assertEquals("Admin.html", title);
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
