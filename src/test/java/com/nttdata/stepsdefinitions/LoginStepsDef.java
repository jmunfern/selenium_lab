package com.nttdata.stepsdefinitions;

import com.nttdata.steps.InventorySteps;
import com.nttdata.steps.LoginSteps;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.nttdata.core.DriverManager.getDriver;
import static com.nttdata.core.DriverManager.screenShot;


public class LoginStepsDef {

    private WebDriver driver;


    private InventorySteps inventorySteps(WebDriver driver) {
        return new InventorySteps(driver);
    }

    @Dado("que me encuentro en la página de la tienda qlab")
    public void que_me_encuentro_en_la_página_de_login_de_qlab() {
        driver = getDriver();
        driver.get("https://qalab.bensg.com/store/pe/");
        driver.findElement(By.xpath("//span[contains(text(),'Iniciar')]")).click();
        screenShot();
    }

    @Cuando("inicio sesión con mi usuario: {string} y clave: {string}")
    public void inicio_sesión_con_las_credenciales_usuario_y_contraseña(String user, String clave) {
        driver.findElement(By.xpath("//input[@id='field-email']")).sendKeys(user);
        driver.findElement(By.xpath("//input[@id='field-password']")).sendKeys(clave);
        driver.findElement(By.xpath("//button[@id='submit-login']")).click();

        //LoginSteps loginSteps = new LoginSteps(driver);
        //loginSteps.typeUser(user);
        //loginSteps.typePassword(password);
        //loginSteps.login();
        screenShot();
    }


}
