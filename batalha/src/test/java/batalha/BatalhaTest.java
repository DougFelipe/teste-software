package batalha;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BatalhaTest {

    //---------- INICIALIZANDO CLASSES VAZIAS ----------//

    // Essas classes são iniciadas como vazias para serem manipuladas
    // Durante as varias sessões de testes, excluindo a necessidade
    // De ficar criando ou inicializando toda hora

    Batalha batalha = new Batalha();
    Personagem assasino1 = new Assassino();
    Personagem assasino2 = new Assassino();
    Personagem guerreiro1 = new Guerreiro();
    Personagem guerreiro2 = new Guerreiro();




    //---------- TESTE DE DANO CRITICO ----------//
    @Test
    void testeGolpeCritico() {
        Personagem personagem1 = new Assassino();

        //Propositalmente este é um teste que tem comportamento aleatorio
        boolean checadorDeGolpeCritico = batalha.temGolpeCritico();
        assertFalse(checadorDeGolpeCritico);
    }


    //---------- TESTES DE DANO BASE ----------//

    //TABELA DE VARIAÇÕES
    //0 = 80%
    //1 = 90%
    //2 = 100%
    //3 = 110%
    //4 = 120%


    @Test
    void testandoDanoBaseDeATQ7eV0(){
        assasino1.setAtaque(7);
        //5,6
        int ataqueFinal = batalha.calcularDanoBase(assasino1,0);
        assertEquals(6, ataqueFinal);
    }

    @Test
    void testandoDanoBaseDeATQ7eV1(){
        assasino1.setAtaque(7);
        //6,3
        int ataqueFinal = batalha.calcularDanoBase(assasino1,1);
        assertEquals(6, ataqueFinal);
    }
    @Test
    void testandoDanoBaseDeATQ7eV2(){
        assasino1.setAtaque(7);
        //7,0
        int ataqueFinal = batalha.calcularDanoBase(assasino1,2);
        assertEquals(7, ataqueFinal);
    }
    @Test
    void testandoDanoBaseDeATQ7eV3(){
        assasino1.setAtaque(7);
        //7,7
        int ataqueFinal = batalha.calcularDanoBase(assasino1,3);
        assertEquals(8, ataqueFinal);
    }
    @Test
    void testandoDanoBaseDeATQ7eV4(){
        assasino1.setAtaque(7);
        // 8,4
        int ataqueFinal = batalha.calcularDanoBase(assasino1,4);
        assertEquals(8, ataqueFinal);
    }
    @Test
    void testandoDanoBaseDeATQ10eV0(){
        assasino2.setAtaque(10);

        int ataqueFinal = batalha.calcularDanoBase(assasino2,0);
        assertEquals(8, ataqueFinal);
    }
    @Test
    void testandoDanoBaseDeATQ10eV1(){
        assasino2.setAtaque(10);

        int ataqueFinal = batalha.calcularDanoBase(assasino2,1);
        assertEquals(9, ataqueFinal);
    }
    @Test
    void testandoDanoBaseDeATQ10eV2(){
        assasino2.setAtaque(10);

        int ataqueFinal = batalha.calcularDanoBase(assasino2,2);
        assertEquals(10, ataqueFinal);
    }

    @Test
    void testandoDanoBaseDeATQ10eV3(){
        assasino2.setAtaque(10);

        int ataqueFinal = batalha.calcularDanoBase(assasino2,3);
        assertEquals(11, ataqueFinal);
    }

    @Test
    void testandoDanoBaseDeATQ10eV4(){
        assasino2.setAtaque(10);

        int ataqueFinal = batalha.calcularDanoBase(assasino2,4);
        assertEquals(12, ataqueFinal);
    }


    //---------- TESTE DE EVASÃO CONTROLADA ----------//

    @Test
    void testeDeEvasaoBemSucedida(){

        // Atacante é o guerreiro
        guerreiro1.setAtaque(7);
        guerreiro1.setResistencia(7);
        guerreiro1.setVelocidade(3);
        guerreiro1.setDefesa(3);

        // Defensor é o assassino
        assasino2.setAtaque(7);
        assasino2.setVelocidade(7);
        assasino2.setDefesa(3);
        assasino2.setResistencia(3);

        //FORMULA: CHANCE DE EVASÃO(%) = MIN(50, (Y.VEL - X.VEL) * 2)
        // seguindo a formula temos = min(50, (7 -3) * 2)
        // ficando com min(50,8) que é igual a 8
        // entao usaremos 7 que é menor que 8

        boolean evadiu = batalha.verificarEvasao(guerreiro1, assasino2, 7);
        assertTrue(evadiu);

    }
    @Test
    void testeDeEvasaoBemSucedidaValorLimite(){

        // Atacante é o guerreiro
        guerreiro1.setAtaque(7);
        guerreiro1.setResistencia(7);
        guerreiro1.setVelocidade(3);
        guerreiro1.setDefesa(3);

        // Defensor é o assassino
        assasino2.setAtaque(7);
        assasino2.setVelocidade(7);
        assasino2.setDefesa(3);
        assasino2.setResistencia(3);

        //FORMULA: CHANCE DE EVASÃO(%) = MIN(50, (Y.VEL - X.VEL) * 2)
        // seguindo a formula temos = min(50, (7 -3) * 2)
        // ficando com min(50,8) que é igual a 8
        // entao usaremos 8 que é o valor limite para a evasão

        boolean evadiu = batalha.verificarEvasao(guerreiro1, assasino2, 8);
        assertTrue(evadiu);

    }
    @Test
    void testeDeEvasaoMalSucedidaRandomicoMaiorQueAChance(){

        // Atacante é o guerreiro
        guerreiro1.setAtaque(7);
        guerreiro1.setResistencia(7);
        guerreiro1.setVelocidade(3);
        guerreiro1.setDefesa(3);

        // Defensor é o assassino
        assasino2.setAtaque(7);
        assasino2.setVelocidade(7);
        assasino2.setDefesa(3);
        assasino2.setResistencia(3);

        //FORMULA: CHANCE DE EVASÃO(%) = MIN(50, (Y.VEL - X.VEL) * 2)
        // seguindo a formula temos = min(50, (7 -3) * 2)
        // ficando com min(50,8) que é igual a 8
        // entao usaremos 9 que é maior que 8

        boolean evadiu = batalha.verificarEvasao(guerreiro1, assasino2, 9);
        assertFalse(evadiu);

    }

    @Test
    void testeDeEvasaoMalSucedidaEMPATE(){
        // Usarei personagens de mesma configuração
        // Já que velocidades iguais resultam numa
        // Evasão mal sucedida.

        assasino1.setAtaque(7);
        assasino1.setVelocidade(7);
        assasino1.setDefesa(3);
        assasino1.setResistencia(3);

        assasino2.setAtaque(7);
        assasino2.setVelocidade(7);
        assasino2.setDefesa(3);
        assasino2.setResistencia(3);

        boolean evadiu = batalha.verificarEvasao(assasino1, assasino2, 1);

        assertFalse(evadiu);

    }


    //---------- TESTE DE DANO FINAL + REMOVER HP ----------//

    @Test
    void testeDanoInfrigido7DeDano3DeDefesaSemGolpeCritico(){
        assasino1.setAtaque(7);
        assasino2.setDefesa(3);
        // ataque base esperado 7,0
        int danoBase = batalha.calcularDanoBase(assasino1,2);
        // 7 - 3 = 4  sem golpe critico
        int danoInfrigido = batalha.calcularDanoFinal(danoBase, false, assasino2);

        assertEquals(4, danoInfrigido);



    }
    @Test
    void testeDanoInfrigido7DeDano3DeDefesaComGolpeCritico(){
        assasino1.setAtaque(7);
        assasino2.setDefesa(3);
        // ataque base esperado 7,0
        int danoBase = batalha.calcularDanoBase(assasino1,2);
        // (7 - 3) * 1,5 = 6
        int danoInfrigido = batalha.calcularDanoFinal(danoBase, true, assasino2);

        assertEquals(6, danoInfrigido);



    }

    @Test
    void testeDanoInfrigido7DeDano4DeDefesaSemGolpeCritico(){
        assasino1.setAtaque(7);
        assasino2.setDefesa(4);
        // ataque base esperado 7,0
        int danoBase = batalha.calcularDanoBase(assasino1,2);
        // 7 - 4 = 3  sem golpe critico
        int danoInfrigido = batalha.calcularDanoFinal(danoBase, false, assasino2);

        assertEquals(3, danoInfrigido);



    }

    @Test
    void testeDanoInfrigido7DeDano4DeDefesaComGolpeCritico(){
        assasino1.setAtaque(7);
        assasino2.setDefesa(4);
        // ataque base esperado 7,0
        int danoBase = batalha.calcularDanoBase(assasino1,2);
        // (7 - 4) * 1,5 = 6
        int danoInfrigido = batalha.calcularDanoFinal(danoBase, true, assasino2);

        assertEquals(4, danoInfrigido);



    }

    @Test
    void testeRemoverHP7DeDano3DeDefesaSemGolpeCritico(){

        assasino1.setAtaque(7);
        assasino2.setDefesa(3);
        assasino2.setVida(100);


        // ataque base esperado 7,0
        int danoBase = batalha.calcularDanoBase(assasino1,2);

        // 7 - 3 = 4  sem golpe critico
        int danoInfrigido = batalha.calcularDanoFinal(danoBase, false, assasino2);

        //removendo 4 de hp dos 100 iniciais
        batalha.removerHP(danoInfrigido,assasino2);
        assertEquals(96, assasino2.getVida());


    }

    @Test
    void testeRemoverHP7DeDano3DeDefesaComGolpeCritico(){

        assasino1.setAtaque(7);
        assasino2.setDefesa(3);
        assasino2.setVida(100);

        // ataque base esperado 7,0
        int danoBase = batalha.calcularDanoBase(assasino1,2);
        // (7 - 3) * 1,5 = 6
        int danoInfrigido = batalha.calcularDanoFinal(danoBase, true, assasino2);

        //removendo 6 de hp dos 100 iniciais
        batalha.removerHP(danoInfrigido,assasino2);
        assertEquals(94, assasino2.getVida());



    }



}
