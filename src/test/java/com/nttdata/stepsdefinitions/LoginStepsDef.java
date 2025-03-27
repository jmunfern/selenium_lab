package com.nttdata.stepsdefinitions;

import com.nttdata.steps.InventorySteps;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static com.nttdata.core.DriverManager.getDriver;
import static com.nttdata.core.DriverManager.screenShot;
import static java.lang.Thread.sleep;


public class LoginStepsDef {

    private WebDriver driver;
    private ArrayList<String> Categorias = new ArrayList<>();

    private InventorySteps inventorySteps(WebDriver driver){
        return new InventorySteps(driver);
    }

    @Dado("que me encuentro en la página de la tienda qlab")
    public void paso_1() {
        driver = getDriver();
        driver.get("https://qalab.bensg.com/store/pe/");

        screenShot();
    }

    @Y("me logueo con mi usuario: {string} y clave {string}")
    public void paso_2(String user, String clave) {
        driver.findElement(By.xpath("//span[contains(text(),'Iniciar')]")).click();

        driver.findElement(By.xpath("//input[@id='field-email']")).sendKeys(user);
        driver.findElement(By.xpath("//input[@id='field-password']")).sendKeys(clave);
        driver.findElement(By.xpath("//button[@id='submit-login']")).click();
        screenShot();
    }

    @Cuando("navego hacia la categoría: {string} y subcategoría {string}")
    public void paso_3(String categoria, String sub_categoria) {
        List<WebElement> Elemcategorias = driver.findElements(By.xpath("//ul[@id='top-menu']/li/a"));

        for (WebElement i: Elemcategorias){
            String valor_categoria = i.getText();
            System.out.println(valor_categoria);
            Categorias.add(valor_categoria);
        }
        try {
            if (Categorias.contains(categoria)) {
                System.out.println("El valor '" + categoria + "' se encontró en la categoría.");
            } else {

                throw new IllegalArgumentException("El valor '" + categoria + "' no se encontró en la categoría.");
            }
        } catch (Exception e) {
            System.out.println("El valor '" + categoria + "' no se encontró en la categoría.");
        }

        driver.findElement(By.xpath("//ul[@id='top-menu']/li/a[contains(@href, 'clothes')]")).click();

        String xpath_subcategoria = String.format("//div[@class='block-categories']/descendant::a[contains(text(),'%s')]", sub_categoria);
        try {
            driver.findElement(By.xpath(xpath_subcategoria)).click();
        } catch (Exception e) {
            System.out.println("No existe la sub categoria:" + sub_categoria);
        }

        screenShot();
    }

    @Y("agrego 2 unidades del primer producto al carrito")
    public void paso_4() {
        driver.findElement(By.xpath("//a[picture]")).click();
        WebElement elm = driver.findElement(By.xpath("//input[@id='quantity_wanted']"));
        elm.clear();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50000));
        elm.sendKeys("2");
        driver.findElement(By.xpath("//button[contains(@class, 'add-to-cart')]")).click();
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        screenShot();

    }
    @Entonces("valido en el popup la confirmación del producto agregado")
    public void paso_5() {
        String dato_precio = driver.findElement(By.xpath("//p[@class='product-price']")).getText();
        String dato_cantidad = driver.findElement(By.xpath("//span[@class='product-quantity']/strong")).getText();
        String dato_total = driver.findElement(By.xpath("//p[@class='product-total']/span[last()]")).getText();

        String dato_precio_parse = dato_precio.replace("S/ ", "");
        double valor_precio = Double.parseDouble(dato_precio_parse);

        double valor_cantidad = Double.parseDouble(dato_cantidad);

        String dato_total_parse = dato_total.replace("S/ ", "");
        double valor_total = Double.parseDouble(dato_total_parse);

        boolean condicion = valor_precio * valor_cantidad == valor_total;

        if (condicion) {
            System.out.println("comprobado");
        } else {
            throw new IllegalArgumentException("fallo entre cantidad y precio respecto al total");
        }

        String dato_verificacion_producto_agregado = driver.findElement(By.xpath("//h4")).getText();
        System.out.println(dato_verificacion_producto_agregado);

        screenShot();

    }

    @Cuando("finalizo la compra")
    public void paso_6() {
        driver.findElement(By.xpath("//a[contains(text(), 'Finalizar')]")).click();
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        screenShot();

    }

    @Entonces("valido el titulo de la pagina del carrito")
    public void paso_7() {

        String titulo = driver.getTitle();
        System.out.println(titulo);
        screenShot();
    }

    @Entonces("vuelvo a validar el calculo de precios en el carrito")
    public void paso_8() {

        String dato_precio = driver.findElement(By.xpath("//span[@class='price']")).getText();
        String dato_cantidad = driver.findElement(By.xpath("//input[@name='product-quantity-spin']")).getAttribute("value");
        String dato_total = driver.findElement(By.xpath("//div[@class='cart-summary-line cart-total']/span[last()]")).getText();

        String dato_precio_parse = dato_precio.replace("S/ ", "");
        double valor_precio = Double.parseDouble(dato_precio_parse);

        double valor_cantidad = Double.parseDouble(dato_cantidad);

        String dato_total_parse = dato_total.replace("S/ ", "");
        double valor_total = Double.parseDouble(dato_total_parse);

        boolean condicion = valor_precio * valor_cantidad == valor_total;

        if (condicion) {
            System.out.println("comprobado final");
        } else {
            throw new IllegalArgumentException("fallo entre cantidad y precio respecto al total");
        }

        driver.findElement(By.xpath("//a[contains(text(), 'Finalizar')]")).click();
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        screenShot();
    }

    @Entonces("imprimo el mensaje de credenciales incorrectas")
    public void caso_1() {
        System.out.println("Hay un error en las credenciales");
        screenShot();
    }

    @Entonces("imprimo el mensaje de categoria no encontrada")
    public void caso_2() {
        System.out.println("La categoría no fue encontrada");
        screenShot();
    }

}
