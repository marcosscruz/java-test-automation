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

import java.util.List;
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
    private WebElement radioSexoFem;
    @FindBy(id = "elementosForm:sexo:0")
    private WebElement radioSexoMasc;

    @FindBy(id = "elementosForm:escolaridade")
    private WebElement dropdownEscolaridade;

    @FindBy(id = "elementosForm:comidaFavorita:3")
    private WebElement vegetarianoCheckbox;
    @FindBy(id = "elementosForm:comidaFavorita:0")
    private WebElement carneCheckbox;
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
        }

        return this;
    }

    public DesafioCadastroPage clicarCadastrar() {
        cadastrarButton.click();
        return this;
    }

    // Automatize a seleção de cada opção do dropdown de escolaridade e valide se a opção selecionada é a que foi definida no teste.
    public DesafioCadastroPage selecionarEscolaridade() {
        WebElement escolaridadeSelecionada = driver.findElement(By.id("elementosForm:escolaridade"));
        Select selectEscolaridade = new Select(escolaridadeSelecionada);

        selectEscolaridade.selectByVisibleText("Superior");

        return this;
    }

    // Desenvolva testes que selecionem múltiplas opções de esportes e valide se as seleções estão corretas. Teste
    // também cenários específicos, como não selecionar nenhum esporte ou selecionar todas as opções.
    public DesafioCadastroPage selecionarEsportes() {
        WebElement esportesSelecionados = driver.findElement(By.id("elementosForm:esportes"));
        Select selectEsportes = new Select(esportesSelecionados);

        if(selectEsportes.isMultiple()){
            selectEsportes.selectByValue("natacao");
            selectEsportes.selectByValue("futebol");
            selectEsportes.selectByValue("Karate");
            //selectEsportes.selectByVisibleText("Futebol");
            //selectEsportes.selectByIndex(3);
        } else {
            System.out.println("Não suporta múltipla seleções");
        }

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

    public String verificaResultadoEscolaridade() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement labelEscolaridade = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='descEscolaridade']")));
        return labelEscolaridade.getText();
    }

    /*
        public String verificarResultadoEsportes(Select select, String[] esportesEsperados) {
        List<WebElement> esportesSelecionados = select.getAllSelectedOptions();

        if (esportesSelecionados.size() != esportesEsperados.length){
            return "ERROR";
        }

        for(WebElement opcao : esportesSelecionados){
            String valorSelecao = opcao.getAttribute("value");
            if(!verificarEsporteSelecionado(valorSelecao, esportesEsperados)){
                return "Eportes inesperados selecionados: " + valorSelecao;
            }
        }

        return "Tudo correto!";
    }
     */

    public void verificarResultadoEsportes(Select select, String[] esportesEsperados) {
        List<WebElement> esportesSelecionados = select.getAllSelectedOptions();

        Assert.assertEquals(esportesSelecionados.size(), esportesEsperados.length);

        for(WebElement opcao : esportesSelecionados){
            String valorSelecao = opcao.getAttribute("value");
            Assert.assertTrue(verificarEsporteSelecionado(valorSelecao, esportesEsperados));
        }
    }

    public boolean verificarEsporteSelecionado(String esporte, String[] esportesEsperados){
        for (String esporteEsperado : esportesEsperados){
            if(esporte.equals(esporteEsperado)){
                return true;
            }
        }
        return false;
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

    public String verificaStatusCadastro() {
        clicarCadastrar();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement statusElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='resultado']")));

        return statusElement.getText();
    }

}
