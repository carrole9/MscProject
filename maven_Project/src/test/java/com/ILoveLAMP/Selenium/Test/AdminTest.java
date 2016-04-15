package com.ILoveLAMP.Selenium.Test;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class AdminTest {
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
  public void testAdmin() throws Exception {
	// start from login page
    driver.get(baseUrl);   
    // login with correct username and password
    driver.findElement(By.id("inputEmail")).clear();
    driver.findElement(By.id("inputEmail")).sendKeys("Yang");
    driver.findElement(By.id("inputPassword")).clear();
    driver.findElement(By.id("inputPassword")).sendKeys("1234");
    driver.findElement(By.id("login")).click();
    assertEquals("Welcome back Yang", closeAlertAndGetItsText());
    
    // in the admin page check if all the elements present
    // Home button
    assertTrue(driver.findElement(By.xpath("html/body/div[1]/ul/li[1]/a")).isDisplayed());
    // import button
    assertTrue(driver.findElement(By.xpath(".//*[@id='semenu1']")).isDisplayed());
    // add user button
    assertTrue(driver.findElement(By.xpath(".//*[@id='semenu2']")).isDisplayed());
    // logout button
    assertTrue(driver.findElement(By.xpath(".//*[@id='logout']")).isDisplayed());

    
    // check if all element present in import data page
    driver.findElement(By.id("semenu1")).click();
    Thread.sleep(500);
    // file path selection
    assertTrue(driver.findElement(By.xpath(".//*[@id='AdminFile']")).isDisplayed());
//    // import data button
    assertTrue(driver.findElement(By.xpath(".//*[@id='ImportData']")).isDisplayed());
//    // view data button
    assertTrue(driver.findElement(By.xpath(".//*[@id='ViewData']")).isDisplayed());
    
    
    driver.findElement(By.id("ViewData")).click();
    driver.findElement(By.id("semenu2")).click();
    driver.findElement(By.id("view-users")).click();
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("Selenium");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("1234");
    new Select(driver.findElement(By.id("usertype"))).selectByVisibleText("Customer Service Rep");
    driver.findElement(By.id("add-user")).click();
    assertEquals("The Entered username has been taken! \nPlease try " +
			"different username.", closeAlertAndGetItsText());
    driver.findElement(By.id("view-users")).click();
    boolean status = driver.findElement(By.xpath(".//*[@id='menu2']/div[2]")).isDisplayed();
    assertTrue(status);    
    driver.findElement(By.id("logout")).click();
    assertEquals("Logging out", closeAlertAndGetItsText()); 
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
