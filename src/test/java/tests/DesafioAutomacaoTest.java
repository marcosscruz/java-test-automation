package tests;


import entities.BaseTest;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.DesafioCadastroPage;

import java.time.Duration;

public class DesafioAutomacaoTest extends BaseTest {


    //definicao do DataProvider
    @DataProvider(name = "dadosCadastro")
    public static Object[][] criarDados() {
        return new Object[][]{
                {"Aline", "Zanin", "Feminino"},
                //    {"Paulo", "Silva","Masculino"},
        };
    }


    @Test(dataProvider = "dadosCadastro")//, dataProviderClass ="NomeClaseProviderExterno.class" )
    public void deveCadastrarPessoaExemplo(String nome, String sobrenome, String sexo) {
        DesafioCadastroPage cp = new DesafioCadastroPage(getDriver());
        cp.preencherNome(nome)
                .preencherSobrenome(sobrenome)
                .selecionarSexo(sexo)
                .selecionarEscolaridade()
                .clicarCadastrar();

        Assert.assertEquals(cp.verificaResultadoNome(), "Nome: " + nome);
        Assert.assertEquals(cp.verificaResultadoSobreNome(), "Sobrenome: " + sobrenome);
        Assert.assertEquals(cp.verificaResultadoSexo(), "Sexo: " + sexo);


    }


    @Test(dataProvider = "dadosCadastro")//, dataProviderClass ="NomeClaseProviderExterno.class" )
    public void deveVerificarComida(String nome, String sobrenome, String sexo) {
        DesafioCadastroPage cp = new DesafioCadastroPage(getDriver());
        cp.preencherNome(nome)
                .preencherSobrenome(sobrenome)
                .selecionarSexo(sexo)
                .selecionarEscolaridade()
                .selecionarComidaCarne()
                .selecionarComidaVegetariana()
                .clicarCadastrar();
        Assert.assertEquals(cp.verificaTextoAlerta(),"Tem certeza que voce eh vegetariano?");


    }


}
