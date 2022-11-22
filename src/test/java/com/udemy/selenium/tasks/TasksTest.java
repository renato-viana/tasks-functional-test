package com.udemy.selenium.tasks;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class TasksTest {

    @Test
    public void deveSalvarTarefaComSucesso() {
        WebDriver driver = acessarAplicacao();

        try {
            //Clicar em Add Todo
            driver.findElement(By.id("addTodo")).click();

            //Escrever descrição
            driver.findElement(By.id("task")).sendKeys("Teste via selenium");

            //Escrever a data
            driver.findElement(By.id("dueDate")).sendKeys("10/12/2022");

            //Clicar em salvar
            driver.findElement(By.id("saveButton")).click();

            String message = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Success!", message);

        } finally {
            //Fechar o browser
            driver.quit();
        }
    }

    @Test
    public void naoDeveSalvarTarefaSemDescricao() {
        WebDriver driver = acessarAplicacao();

        try {
            //Clicar em Add Todo
            driver.findElement(By.id("addTodo")).click();

            //Escrever a data
            driver.findElement(By.id("dueDate")).sendKeys("10/12/2022");

            //Clicar em salvar
            driver.findElement(By.id("saveButton")).click();

            String message = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Fill the task description", message);

        } finally {
            driver.quit();
        }
    }

    @Test
    public void naoDeveSalvarTarefaSemData() {
        WebDriver driver = acessarAplicacao();

        try {
            //Clicar em Add Todo
            driver.findElement(By.id("addTodo")).click();

            //Escrever descrição
            driver.findElement(By.id("task")).sendKeys("Teste via selenium");

            //Clicar em salvar
            driver.findElement(By.id("saveButton")).click();

            String message = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Fill the due date", message);

        } finally {
            driver.quit();
        }
    }

    @Test
    public void naoDeveSalvarTarefaComDataPassada() {
        WebDriver driver = acessarAplicacao();

        try {
            //Clicar em Add Todo
            driver.findElement(By.id("addTodo")).click();

            //Escrever descrição
            driver.findElement(By.id("task")).sendKeys("Teste via selenium");

            //Escrever a data
            driver.findElement(By.id("dueDate")).sendKeys("10/10/2020");

            //Clicar em salvar
            driver.findElement(By.id("saveButton")).click();

            String message = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Due date must not be in past", message);

        } finally {
            driver.quit();
        }
    }

    private static WebDriver acessarAplicacao() {
        WebDriver driver = new ChromeDriver();
        driver.navigate().to("http://localhost:8001/tasks");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

}
