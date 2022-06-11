package com.example.ordersdelivery.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class RouteTest {

    WebDriver driver;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest() {
        driver = new ChromeDriver();
    }

    @AfterEach
    void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void test() throws MalformedURLException {

        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

        driver.get("http://localhost:3000");
        String title = driver.getTitle();
        System.out.println(title);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement webElementAddRoute = driver.findElement(By.id("id_addroute"));
        System.out.println("Tag name = " + webElementAddRoute.getTagName());
        //webElement.click();

        WebElement webElementDesc = driver.findElement(By.id("id_desc_323"));
        webElementDesc.click();

        WebElement webElementOkModal = driver.findElement(By.id("id_ok_modal"));

        WebElement webElementFrom = driver.findElement(By.id("id_from"));
        webElementFrom.clear();
        webElementFrom.sendKeys("it works!!!");

        webElementOkModal.click();
    }
}
