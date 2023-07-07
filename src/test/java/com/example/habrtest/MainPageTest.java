package com.example.habrtest;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class MainPageTest {
    private WebDriver driver;


    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.habr.com/");

    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void searchFilter() {

        WebElement searchIcon = driver.findElement(By.cssSelector("a[href='/ru/search/']"));
        searchIcon.click();

        WebElement searchField = driver.findElement(By.xpath("//input[@class='tm-input-text-decorated__input']"));
        searchField.sendKeys("QA", Keys.ENTER);

        WebElement sortField = driver.findElement(By.xpath("//div[@class='tm-navigation-dropdown__button-text']"));
        assertTrue(driver.findElement(By.xpath("//div[@class='tm-navigation-dropdown__button-text']")).isDisplayed(), "фильтр по релевантности не найден");
    }

    @Test
    public void searchDesign() {

        WebElement searchButton = driver.findElement(By.cssSelector("a[href='/ru/flows/design/']"));
        searchButton.click();

        WebElement sectionDesign = driver.findElement(By.xpath("//button[@class='tm-navigation-filters-spoiler__button']"));
        assertTrue(driver.findElement(By.xpath("//button[@class='tm-navigation-filters-spoiler__button']")).isDisplayed(), "фильтр по все подряд не найден");
    }
}

