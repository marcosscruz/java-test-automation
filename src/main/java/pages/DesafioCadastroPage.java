package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class DesafioCadastroPage extends BasePage {


    public DesafioCadastroPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "elementosForm:nome")
    private WebElement nomeTextField;
    @FindBy(name = "elementosForm:sobrenome")
    private WebElement sobrenomeTextField;
    @FindBy(xpath = "/html/body/center/form/table/tbody/tr[10]/td/input")
    private WebElement cadastrarButton;
    @FindBy(id = "elementosForm:sexo:1")
    private WebElement  radioSexoFem ;
    @FindBy(id = "elementosForm:sexo:0")
    private WebElement  radioSexoMasc ;

    @FindBy(id = "elementosForm:comidaFavorita:3")
    private WebElement  vegetarianoCheckbox  ;
    @FindBy(id = "elementosForm:comidaFavorita:0")
    private WebElement  carneCheckbox  ;




    public DesafioCadastroPage preencherNome(String nome) {
        nomeTextField.sendKeys(nome); //enviar informações, passar algo para o browser, preencher um input sendKeys
        return this;
    }
    public DesafioCadastroPage preencherSobrenome(String sobrenome) {
        sobrenomeTextField.sendKeys(sobrenome);
        return this;
    }

    public DesafioCadastroPage selecionarSexo(String sexo) {

        if("Feminino".equalsIgnoreCase(sexo)){
            radioSexoFem.click();
        }else{
            radioSexoMasc.click();
        }

        return this;
    }


    public DesafioCadastroPage selecionarComidaVegetariana() {
       vegetarianoCheckbox.click();
        return this;
    }

    public DesafioCadastroPage selecionarComidaCarne() {
        carneCheckbox.click();
        return this;
    }

    public DesafioCadastroPage clicarCadastrar() {
        cadastrarButton.click(); //click comando para clicar
        return this;
    }

    public DesafioCadastroPage selecionarEscolaridade() {

        WebElement escolaridade = driver.findElement(By.id("elementosForm:escolaridade"));
        Select selectEscolaridade = new Select(escolaridade);
        selectEscolaridade.selectByVisibleText("Doutorado");
        selectEscolaridade.selectByIndex(3);
        selectEscolaridade.selectByValue("1grauincomp");

       // selectEscolaridade.getFirstSelectedOption()//retornar o valor do primeiro selecionado
      //  selectEscolaridade.getAllSelectedOptions()//lista com todos os selecionados

        return this;
    }


    public String verificaResultadoNome() {
        // Espera até que o elemento que contém o nome esteja visível.
        // Isso é útil quando há uma atualização dinâmica na página.
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement labelNome = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='descNome']")));
        return labelNome.getText();
    }

    public String verificaResultadoSobreNome() {
        // Espera até que o elemento que contém o nome esteja visível.
        // Isso é útil quando há uma atualização dinâmica na página.
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement labelNome = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='descSobrenome']")));
        return labelNome.getText();
    }

    public String verificaResultadoSexo() {
        // Espera até que o elemento que contém o nome esteja visível.
        // Isso é útil quando há uma atualização dinâmica na página.
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement labelNome = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='descSexo']")));
        return labelNome.getText();
    }


    public String verificaTextoAlerta(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        return alert.getText();
    }


    public DesafioCadastroPage concordaAlerta(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
        return this;
    }






/*
 evitar uso, não é uma boa pratica
    public String buscarValores(){
        return driver.getPageSource();
  }

 */


    /*Outros comandos

        nomeDoElemento.getText() /// capturar o texto de um elemento
        nomeDoElemento.clear(); //limpar o campo
        nomeDoElemento.isDisplayed() // verifica se algo esta visivel
        nomeDoElemento.isEnabled() // verifica interagir
        nomeDoElemento.isSelected() // verifica se o mouse está em um campo ou pra check e radio verifica se está marcado

    /*
     */



}
