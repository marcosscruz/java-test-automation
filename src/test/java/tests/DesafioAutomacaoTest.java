package tests;

import entities.BaseTest;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.DesafioCadastroPage;

public class DesafioAutomacaoTest extends BaseTest {

    //definicao do DataProvider
    @DataProvider(name = "dadosCadastro")
    public static Object[][] criarDados() {
        return new Object[][]{
                {"Marcos", "Cruz", "Masculino"},
                //{"Carol", "Queiroz", "Feminino"},
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
                .clicarCadastrar()
                .concordaAlerta() // alerta so aparece quando preciona o botão de cadastrar
                .clicarCadastrar();
        Assert.assertEquals(cp.verificaTextoAlerta(),"Tem certeza que voce eh vegetariano?");
    }

    // Automatize a seleção de cada opção do dropdown de escolaridade e valide se a opção selecionada é a que foi definida no teste.
    @Test(dataProvider = "dadosCadastro")
    public void deveVerificarEscolaridade(String nome, String sobrenome, String sexo) {
        DesafioCadastroPage cp = new DesafioCadastroPage(getDriver());
        cp.preencherNome(nome)
                .preencherSobrenome(sobrenome)
                .selecionarSexo(sexo)
                .selecionarEscolaridade()
                .clicarCadastrar();

        Assert.assertEquals(cp.verificaResultadoEscolaridade(), "Escolaridade: " + "superior");
    }

    // Desenvolva testes que selecionem múltiplas opções de esportes e valide se as seleções estão corretas. Teste
    // também cenários específicos, como não selecionar nenhum esporte ou selecionar todas as opções.
    @Test(dataProvider = "dadosCadastro")
    public void deveVerificarEsportes(String nome, String sobrenome, String sexo){
        DesafioCadastroPage cp = new DesafioCadastroPage(getDriver());
        cp.preencherNome(nome)
                .preencherSobrenome(sobrenome)
                .selecionarSexo(sexo)
                .selecionarEsportes();

        WebElement selectElement = driver.findElement(By.id("elementosForm:esportes"));
        Select selectEsportes = new Select(selectElement);

        cp.verificarResultadoEsportes(selectEsportes, new String[]{"natacao", "futebol", "Karate"});
    }

    // Cenário específico: selecionou todos as opções
    @Test(dataProvider = "dadosCadastro")
    public void deveVerificarTodosEsportes(String nome, String sobrenome, String sexo){
        try {
            DesafioCadastroPage cp = new DesafioCadastroPage(getDriver());
            cp.preencherNome(nome)
                    .preencherSobrenome(sobrenome)
                    .selecionarSexo(sexo)
                    .selecinarTodosEsportes()
                    .clicarCadastrar()
                    .concordaAlerta()
                    .clicarCadastrar();

            Alert alert = driver.switchTo().alert();
            alert.accept();

            WebElement selectElement = driver.findElement(By.id("elementosForm:esportes"));
            Select selectEsportes = new Select(selectElement);

            cp.verificarResultadoEsportes(selectEsportes, new String[]{"natacao", "futebol", "Corrida", "Karate", "nada"});
        } catch (UnhandledAlertException e) {
            System.out.println("Alerta esperado: " + e.getAlert());
        }
    }

    // Automatize testes que cliquem no botão "Cadastrar" e verifiquem se o status do cadastro é atualizado
    // corretamente no elemento "Status".
    @Test(dataProvider = "dadosCadastro")
    public void deveVerificarStatusAtualizado(String nome, String sobrenome, String sexo) {
        DesafioCadastroPage cp = new DesafioCadastroPage(getDriver());
        cp.preencherNome(nome)
                .preencherSobrenome(sobrenome)
                .selecionarSexo(sexo)
                .selecionarComidaCarne()
                .selecionarEscolaridade();

        String statusCadastro = cp.verificaStatusCadastro();

        Assert.assertEquals(statusCadastro, "Cadastrado!"
                + "\nNome: " + nome
                + "\nSobrenome: " + sobrenome
                + "\nSexo: " + sexo
                + "\nComida: " + "Carne\n"
                + cp.verificaResultadoEscolaridade()
                + "\nEsportes:"
                + "\nSugestoes:"
        );
    }

}
