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
import java.util.Random;

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
    private WebElement  radioSexoFem;
    @FindBy(id = "elementosForm:sexo:0")
    private WebElement  radioSexoMasc;

    @FindBy(id = "elementosForm:comidaFavorita:3")
    private WebElement  vegetarianoCheckbox;
    @FindBy(id = "elementosForm:comidaFavorita:0")
    private WebElement  carneCheckbox;
    @FindBy(id = "elementosForm:comidaFavorita:1")
    private WebElement frangoCheckbox;

    public DesafioCadastroPage preencherNome(String nome) {
        nomeTextField.sendKeys(nome);
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
        Random random = new Random();
        int opcao = random.nextInt(2);

        switch (opcao) {
            case 0:
                carneCheckbox.click();
                break;
            case 1:
                frangoCheckbox.click();
                break;
            default:
                System.out.println("Opção inválida");
                break;
        }

        return this;
    }

    public DesafioCadastroPage clicarCadastrar() {
        cadastrarButton.click();
        return this;
    }

    public DesafioCadastroPage selecionarEscolaridade() {
        WebElement escolaridade = driver.findElement(By.id("elementosForm:escolaridade"));
        Select selectEscolaridade = new Select(escolaridade);
        selectEscolaridade.selectByValue("1grauincomp");
        selectEscolaridade.selectByIndex(3);
        selectEscolaridade.selectByValue("doutorado");
        // selectEscolaridade.getFirstSelectedOption() // retornar o valor do primeiro selecionado
        // selectEscolaridade.getAllSelectedOptions() // lista com todos os selecionados

        return this;
    }

    public String verificaResultadoNome() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement labelNome = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='descNome']")));
        return labelNome.getText();
    }

    public String verificaResultadoSobreNome() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement labelNome = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='descSobrenome']")));
        return labelNome.getText();
    }

    public String verificaResultadoSexo() {
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

}
