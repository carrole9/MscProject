package com.ILoveLAMP.Selenium.Test;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class SupportEngineerTest {
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
  public void testSupportEngineer() throws Exception {
    driver.get(baseUrl);
    driver.findElement(By.id("inputEmail")).clear();
    driver.findElement(By.id("inputEmail")).sendKeys("Shanu");
    driver.findElement(By.id("inputPassword")).clear();
    driver.findElement(By.id("inputPassword")).sendKeys("1234");
    driver.findElement(By.id("login")).click();
    assertEquals("Welcome back Shanu", closeAlertAndGetItsText());
    driver.findElement(By.linkText("Support Engineer")).click();
    driver.findElement(By.id("semenu1")).click();
    driver.findElement(By.id("mySTime")).clear();
    driver.findElement(By.id("mySTime")).sendKeys("2013-01-11 17:15:00");
    driver.findElement(By.id("myETime")).clear();
    driver.findElement(By.id("myETime")).sendKeys("2013-01-11 17:34:00");
    driver.findElement(By.id("suppSub1")).click();
    driver.findElement(By.id("semenu2")).click();
    driver.findElement(By.id("FmyModel")).clear();
    driver.findElement(By.id("FmyModel")).sendKeys("V");
    driver.findElement(By.id("FmyModel")).clear();
    driver.findElement(By.id("FmyModel")).sendKeys("VEA");
    driver.findElement(By.id("FmyModel")).clear();
    driver.findElement(By.id("FmyModel")).sendKeys("VEA3");
    driver.findElement(By.id("FstartTime")).clear();
    driver.findElement(By.id("FstartTime")).sendKeys("2013-01-11 17:15:00");
    driver.findElement(By.id("FendTime")).clear();
    driver.findElement(By.id("FendTime")).sendKeys("2013-01-11 17:34:00");
    driver.findElement(By.id("suppSub2")).click();
    driver.findElement(By.id("semenu3")).click();
    driver.findElement(By.id("FmyFailID")).clear();
    driver.findElement(By.id("FmyFailID")).sendKeys("1");
    driver.findElement(By.id("suppSub3")).click();
    driver.findElement(By.id("back")).click();
    driver.findElement(By.linkText("Customer Service Rep")).click();
    driver.findElement(By.id("semenu1")).click();
    driver.findElement(By.id("submitIMSI")).click();
    driver.findElement(By.id("semenu2")).click();
    driver.findElement(By.id("semenu3")).click();
    driver.findElement(By.id("submitCauseCode")).click();
    driver.findElement(By.linkText("Home")).click();
    driver.findElement(By.id("semenu1")).click();
    driver.findElement(By.linkText("Home")).click();
    driver.findElement(By.id("logout")).click();
    driver.findElement(By.id("inputPassword")).clear();
    driver.findElement(By.id("inputPassword")).sendKeys("1234");
    driver.findElement(By.id("inputEmail")).clear();
    driver.findElement(By.id("inputEmail")).sendKeys("Yang");
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
