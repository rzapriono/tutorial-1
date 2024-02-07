package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class CreateProductFunctionalTest {
    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setupTest() {
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }

    @Test
    void pageTitle_isCorrect (ChromeDriver driver) throws Exception {
        driver.get(baseUrl);

        WebElement hyperlink = driver.findElement(By.linkText("here!"));
        hyperlink.click();

        WebElement createButton = driver.findElement(By.id("createButton"));
        createButton.click();

        String pageTitle = driver.getTitle();
        assertEquals("Create New Product", pageTitle);
    }

    @Test
    void createProduct_isCorrect (ChromeDriver driver) throws Exception {
        driver.get(baseUrl);

        WebElement hyperlink = driver.findElement(By.linkText("here!"));
        hyperlink.click();driver.findElement(By.tagName("a")).click();

        WebElement fieldForName = driver.findElement(By.id("nameInput"));
        fieldForName.clear();
        fieldForName.sendKeys("McFlurry Matcha");

        WebElement fieldForQuantity = driver.findElement(By.id("quantityInput"));
        fieldForQuantity.clear();
        fieldForQuantity.sendKeys("50");

        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();

        String webSourceCode = driver.getPageSource();

        assertTrue(webSourceCode.contains("McFlurry Matcha"));
        assertTrue(webSourceCode.contains("50"));
    }
}
