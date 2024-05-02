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
                {"Marcos", "Cruz", "Masculino"},
                {"Carol", "Queiroz","Feminino"},
        };
    }

    // Automatize um teste que insira valores nos campos "Nome" e "Sobrenome", e verifique se os textos inseridos são os esperados.
    // Crie testes que selecionem cada uma das opções de sexo ("Masculino" e "Feminino") usando botões de rádio. Inclua
    // uma validação para assegurar que a opção selecionada é a esperada.
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

    // Escreva testes para marcar cada uma das opções de comida favorita (Carne, Frango, Pizza, Vegetariano) e valide se
    // as caixas selecionadas estão corretas. Adicionalmente, teste a lógica de negócio que impede a seleção de opções
    // contraditórias (ex: Vegetariano e Carne).
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
