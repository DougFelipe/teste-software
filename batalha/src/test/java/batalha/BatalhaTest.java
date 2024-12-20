package batalha;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import org.mockito.Mockito;

import java.security.SecureRandom;


public class BatalhaTest {

    // ---------- INICIALIZANDO CLASSES VAZIAS ---------- //
    /**
     * Instâncias de Batalha e Personagens criadas para reutilização nos testes,
     * evitando a necessidade de recriação contínua.
     */
    private final Batalha batalha = new Batalha();
    private final Personagem assasino1 = new Assassino();
    private final Personagem assasino2 = new Assassino();
    private final Personagem guerreiro1 = new Guerreiro();
    private final Personagem guerreiro2 = new Guerreiro();

    private final Personagem p1 = new Guerreiro();
    private final Personagem p2 = new Assassino();

    // ---------- TESTE DE DANO CRÍTICO ---------- //
    /**
     * Testa o comportamento de golpes críticos, verificando se o método
     * {@link Batalha#temGolpeCritico()} retorna valores aleatórios esperados.
     */
    @Test
    void testeGolpeCritico() {
        Personagem guerreiro = new Guerreiro();
        // Teste com comportamento aleatório
        boolean checadorDeGolpeCritico = batalha.temGolpeCritico();
        // Neste caso, apenas validamos que o método executa sem falhas.
        assertFalse(checadorDeGolpeCritico);
    }

    // ---------- TESTES DE DANO BASE ---------- //
    /**
     * Testa o cálculo do dano base utilizando diferentes variações e valores de ataque.
     *
     * TABELA DE VARIAÇÕES:
     * 
     *     0 = 80%
     *     1 = 90%
     *     2 = 100%
     *     3 = 110%
     *     4 = 120%
     * 
     *
     * @param ataque o valor de ataque do personagem.
     * @param variacao o índice da variação de dano.
     * @param esperado o dano esperado para o cenário.
     */
    @ParameterizedTest
    @CsvSource({
            // Testes para Assassino com ataque 7
            "7, 0, 6",
            "7, 1, 6",
            "7, 2, 7",
            "7, 3, 8",
            "7, 4, 8",
            // Testes para Assassino com ataque 10
            "10, 0, 8",
            "10, 1, 9",
            "10, 2, 10",
            "10, 3, 11",
            "10, 4, 12"
    })
    void testandoDanoBase(int ataque, int variacao, int esperado) {
        Personagem assassino = new Assassino();
        assassino.setAtaque(ataque);

        int danoCalculado = batalha.calcularDanoBase(assassino, variacao);
        assertEquals(esperado, danoCalculado);
    }

    // ---------- TESTE DE EVASÃO CONTROLADA ---------- //
    /**
     * Testa o comportamento do método {@link Batalha#verificarEvasao(Personagem, Personagem, int)}
     * em diferentes cenários controlados de ataque e defesa.
     *
     * @param atk1 o ataque do personagem atacante.
     * @param res1 a resistência do personagem atacante.
     * @param vel1 a velocidade do personagem atacante.
     * @param def1 a defesa do personagem atacante.
     * @param atk2 o ataque do personagem defensor.
     * @param res2 a resistência do personagem defensor.
     * @param vel2 a velocidade do personagem defensor.
     * @param def2 a defesa do personagem defensor.
     * @param randomico o valor aleatório usado para simular evasão.
     * @param esperadoEvadiu o resultado esperado da evasão ({@code true} ou {@code false}).
     */
    @ParameterizedTest
    @CsvSource({
            // Caso 1: Evasão bem-sucedida
            "7, 7, 3, 3, 7, 7, 7, 3, 7, true",
            // Caso 2: Evasão bem-sucedida no limite
            "7, 7, 3, 3, 7, 7, 7, 3, 8, true",
            // Caso 3: Evasão mal-sucedida (randomico maior que a chance)
            "7, 7, 3, 3, 7, 7, 7, 3, 9, false",
            // Caso 4: Evasão mal-sucedida por empate de velocidade
            "7, 7, 7, 3, 7, 7, 7, 3, 1, false"
    })
    void testeDeEvasao(int atk1, int res1, int vel1, int def1,
                       int atk2, int res2, int vel2, int def2,
                       int randomico, boolean esperadoEvadiu) {

        // Configuração do atacante
        guerreiro1.setAtaque(atk1);
        guerreiro1.setResistencia(res1);
        guerreiro1.setVelocidade(vel1);
        guerreiro1.setDefesa(def1);

        // Configuração do defensor
        assasino2.setAtaque(atk2);
        assasino2.setResistencia(res2);
        assasino2.setVelocidade(vel2);
        assasino2.setDefesa(def2);

        // Verificação da evasão
        boolean evadiu = batalha.verificarEvasao(guerreiro1, assasino2, randomico);
        assertEquals(esperadoEvadiu, evadiu);
    }




// ---------- TESTE DE DANO FINAL + REMOVER HP ---------- //

    /**
     * Testa o cálculo de dano final e a remoção de HP do defensor.
     *
     * @param ataque valor do atributo de ataque do atacante.
     * @param defesa valor do atributo de defesa do defensor.
     * @param variacaoDano variação aleatória no cálculo do dano base.
     * @param golpeCritico indica se o ataque possui golpe crítico.
     * @param esperadoDano valor esperado para o dano final calculado.
     * @param esperadoHP HP esperado para o defensor após o dano, -1 se não for aplicável.
     */
    @ParameterizedTest
    @CsvSource({
            "7, 3, 2, false, 4, -1", // Sem golpe crítico, dano infligido 4
            "7, 3, 2, true, 6, -1",  // Com golpe crítico, dano infligido 6
            "7, 4, 2, false, 3, -1", // Sem golpe crítico, dano infligido 3
            "7, 4, 2, true, 4, 96"   // Com golpe crítico, dano infligido 4, HP restante 96
    })
    void testeDanoFinalRemocaoHP(int ataque, int defesa, int variacaoDano, boolean golpeCritico, int esperadoDano, int esperadoHP) {
        assasino1.setAtaque(ataque);
        assasino2.setDefesa(defesa);

        if (esperadoHP != -1) {
            assasino2.setVida(100); // Apenas para casos que envolvem remoção de HP
        }

        int danoBase = batalha.calcularDanoBase(assasino1, variacaoDano);
        int danoFinal = batalha.calcularDanoFinal(danoBase, golpeCritico, assasino2);
        assertEquals(esperadoDano, danoFinal, "Dano final calculado incorretamente.");

        if (esperadoHP != -1) {
            batalha.removerHP(danoFinal, assasino2);
            assertEquals(esperadoHP, assasino2.getVida(), "HP restante do defensor calculado incorretamente.");
        }
    }



// ---------- TESTE SE TEM VENCEDOR ---------- //

    /**
     * Testa se a batalha detecta corretamente um vencedor.
     *
     * @param ataqueP1 ataque do personagem 1.
     * @param defesaP2 defesa do personagem 2.
     * @param vidaP2 HP inicial do personagem 2.
     * @param variacaoDano variação no cálculo do dano base.
     * @param esperadoVencedor indica se deve haver um vencedor.
     */

    @ParameterizedTest
    @CsvSource({
            "7, 3, 6, 2, true", // HP igual a 0
            "7, 3, 5, 2, true", // HP menor que 0
            "7, 3, 7, 2, false" // Sem vencedor
    })
    void checarVencedor(int ataqueP1, int defesaP2, int vidaP2, int variacaoDano, boolean esperadoVencedor) {
        assasino1.setAtaque(ataqueP1);
        assasino2.setDefesa(defesaP2);
        assasino2.setVida(vidaP2);

        int danoBase = batalha.calcularDanoBase(assasino1, variacaoDano);
        int danoInfrigido = batalha.calcularDanoFinal(danoBase, true, assasino2);
        batalha.removerHP(danoInfrigido, assasino2);

        boolean temVencedor = batalha.temVencedor(assasino1, assasino2);
        assertEquals(esperadoVencedor, temVencedor);
    }


    /**
     * Testa qual personagem venceu o combate.
     *
     * @param vidaP1 HP do personagem 1.
     * @param vidaP2 HP do personagem 2.
     * @param vencedorEsperado mensagem esperada indicando o vencedor.
     */
    @ParameterizedTest
    @CsvSource({
            "10, 0, O vencedor do confronto foi: Personagem 1", // P1 venceu
            "0, 10, O vencedor do confronto foi: Personagem 2"  // P2 venceu
    })
    void checarQuemVenceu(int vidaP1, int vidaP2, String vencedorEsperado) {
        p1.setVida(vidaP1);
        p2.setVida(vidaP2);

        String vencedor = batalha.quemVenceu(p1, p2);
        assertEquals(vencedorEsperado, vencedor);
    }


    /**
     * Testa se existe um vencedor na batalha.
     *
     * @param vidaP1 HP do personagem 1.
     * @param vidaP2 HP do personagem 2.
     * @param esperadoResultado indica se há um vencedor.
     */
    @ParameterizedTest
    @CsvSource({
            "10, 0, true",  // P1 é vencedor
            "0, 10, true",  // P2 é vencedor
            "10, 10, false" // Sem vencedor
    })
    void checarTemVencedor(int vidaP1, int vidaP2, boolean esperadoResultado) {
        assasino1.setVida(vidaP1);
        assasino2.setVida(vidaP2);

        boolean temVencedor = batalha.temVencedor(assasino1, assasino2);
        assertEquals(esperadoResultado, temVencedor);
    }

    /**
     * Testa quem começa o combate com base na maior velocidade.
     *
     * @param velP1 velocidade do personagem 1.
     * @param velP2 velocidade do personagem 2.
     * @param esperado mensagem esperada indicando quem começa atacando.
     */
    @ParameterizedTest
    @CsvSource({
            "5, 3, personagem 1 começa",  // P1 maior velocidade
            "3, 5, personagem 2 começa"   // P2 maior velocidade
    })
    void testeDeterminarQuemComecaMaiorVelocidade(int velP1, int velP2, String esperado) {
        p1.setVelocidade(velP1);
        p2.setVelocidade(velP2);

        String quemComeca = batalha.determinarQuemComecaAtacando(p1, p2);
        assertEquals(esperado, quemComeca);
    }

    /**
     * Testa se o ataque foi esquivado pelo defensor.
     */
    @Test
    void ataqueEsquivado() {
        guerreiro2.setAtaque(7);
        guerreiro2.setResistencia(7);
        guerreiro2.setDefesa(3);
        guerreiro2.setVelocidade(3);

        assasino1.setAtaque(10);
        assasino1.setDefesa(3);
        assasino1.setVelocidade(25);
        assasino1.setResistencia(3);
        int vidaInicialAssassino = 100;
        assasino1.setVida(vidaInicialAssassino);

        batalha.atacar(guerreiro2, assasino1);
        assertEquals(vidaInicialAssassino, assasino1.getVida());
    }

    @ParameterizedTest
    @CsvSource({
            "10, 3, 3, 3, 10, 3, 3, 3, 0",  // Ambos personagens terminam com 0 HP
            "10, 3, 3, 3, 10, 3, 3, 2, 0",  // P1 termina com 0 HP
            "10, 3, 3, 3, 10, 3, 3, 3, 0"   // P2 termina com 0 HP
    })
    void testeFluxoCombateCompleto(int atkP1, int velP1, int defP1, int resP1,
                                   int atkP2, int velP2, int defP2, int resP2,
                                   int hpFinalP2) {
        Personagem p1 = new Guerreiro();
        Personagem p2 = new Assassino();
        p1.setAtaque(atkP1);
        p1.setVelocidade(velP1);
        p1.setDefesa(defP1);
        p1.setResistencia(resP1);
        p1.setVida(10);

        p2.setAtaque(atkP2);
        p2.setVelocidade(velP2);
        p2.setDefesa(defP2);
        p2.setResistencia(resP2);
        p2.setVida(10);

        while (!batalha.temVencedor(p1, p2)) {
            batalha.atacar(p1, p2);
            if (!batalha.temVencedor(p1, p2)) {
                batalha.atacar(p2, p1);
            }
        }

        if (batalha.temVencedor(p1, p2)) {
            assertEquals(hpFinalP2, p2.getVida(), "HP do defensor ao final do combate está incorreto.");
        }
    }

    @ParameterizedTest
    @CsvSource({
            "6, 4, 6, 4, 6, 6, 4, 5, 1", // Dano final esperado é 1
            "6, 4, 6, 4, 8, 6, 4, 6, 1"  // Dano final esperado é 1 (mínimo)
    })
    void ataqueComDanoFinal(int atkP1, int defP1, int velP1, int resP1,
                            int atkP2, int defP2, int velP2, int resP2,
                            int esperadoDanoFinal) {
        assasino1.setAtaque(atkP1);
        assasino1.setDefesa(defP1);
        assasino1.setVelocidade(velP1);
        assasino1.setResistencia(resP1);

        guerreiro2.setAtaque(atkP2);
        guerreiro2.setDefesa(defP2);
        guerreiro2.setVelocidade(velP2);
        guerreiro2.setResistencia(resP2);

        int danoBase = batalha.calcularDanoBase(assasino1, 2);
        int danoInfrigido = batalha.calcularDanoFinal(danoBase, false, guerreiro2);
        assertEquals(esperadoDanoFinal, danoInfrigido);
    }

    /**MC/DC Teste**/



}
