package batalha;

import java.security.SecureRandom;
import java.util.Random;

public class Batalha {

    String determinarQuemComecaAtacando(Personagem personagem1, Personagem personagem2) {
        //O personagem mais rapido começa
        //Se empatar, é feito um sorteio

        if (personagem1.getVelocidade().equals(personagem2.getVelocidade())) {
            //escolhe aleatoriamente
            SecureRandom secureRandom = new SecureRandom();
            int randomico = secureRandom.nextInt(2) + 1;
            if (randomico==1){
                //personagem 1 começa
                return "personagem 1 começa";
            }else{
                //personagem 2 começa
                return "personagem 2 começa";
            }


        } else if (personagem1.getVelocidade()>personagem2.getVelocidade()) {
            //Personagem 1 começa
            return "personagem 1 começa";

        }else{
            //Personagem 2 começa
            return "personagem 2 começa";

        }

    }

    boolean verificarEvasao(Personagem atacante, Personagem defensor, int randomico) {
        //        REGRAS PARA EVASÃO (EVITAR GOLPES)
        //
        //        SEJA X UM ATACANTE E Y UM DEFENSOR
        //
        //        -FORMULA: CHANCE DE EVASÃO(%) = MIN(50, (Y.VEL - X.VEL) * 2)
        //                -CHANCE MAXIMA DE EVASÃO: 50%
        //                -SE: Y.VEL =< X.VEL    ENTÃO A CHANCE DE EVASÃO É 0%
        //
        //                VERIFICAÇÃO DAS REGRAS:
        //        -CALCULAR CHANCE DE EVASÃO USANDO A FORMULA
        //        -GERAR UM NUMERO ALEATORIO ENTRE 1 E 100 ( SEJA ELE CHAMADO DE "EVASÃO")
        //        -SE "EVASÃO" =< CHANCE DE EVASÃO   ENTÃO O ATAQUE É EVITADO

        //Formula de evasão
        int chanceDeEvasao = Math.min(50,(defensor.getVelocidade()-atacante.getVelocidade())*2);
        //Numero Aleatorio

        //Hora de determinar a evasão
        if (atacante.getVelocidade().equals(defensor.getVelocidade())) {
            //Não haverá evasão
            return false;
        } else if (randomico<=chanceDeEvasao) {
            //O defensor irá se evadir
            return true;

        }else{
            //Não haverá evasão
            return false;
        }


    }

    void atacar(Personagem quemAtaca , Personagem quemDefende){

        // FASE 1 - GERAR RANDOMICO E CHECAR EVASAO
        // gera aleatorio e usa em VerificarEvasão
        SecureRandom randomEvasao = new SecureRandom();
        int randomico = randomEvasao.nextInt(100) + 1;
        boolean evadiu = verificarEvasao(quemAtaca, quemDefende, randomico);

        if (!evadiu) {

            // FASE 2 - GERAR RANDOMICO E CALCULAR DANO BASE
            // gera aleatorio e usa em VerificarEvasão
            SecureRandom randomDanoBase = new SecureRandom();
            int variacaoDeDano = randomDanoBase.nextInt(5);
            int danoBase = calcularDanoBase(quemAtaca, variacaoDeDano);

            // FASE 3 - CHECAR GOLPE CRITICO, CALCULAR DANO FINAL
            // E REMOVER HP DO DEFENSOR
            boolean golpeCritico = temGolpeCritico(); //boolean
            int danoFinal = calcularDanoFinal(danoBase, golpeCritico, quemDefende);
            removerHP(danoFinal, quemDefende);


//            //FASE 4 - CHECAR SE HOUVE VENCEDORES E DETERMINA-LO
//            temVencedor(quemAtaca, quemDefende);
//
//            if (temVencedor(quemAtaca, quemDefende)) {
//                //Imprime mensagem informando o vencedor
//                quemVenceu(quemAtaca, quemDefende);
//            }


        }else{
            //Defensor se evadiu do ataque
            //Sem mais ações na rodada
        }
    }


    //refatorado para simplificação
    int calcularDanoBase(Personagem quemAtaca, int variacaoDeDano) {
        double multiplicador = 0.8 + (variacaoDeDano * 0.1); // Variação de 0 a 0.4
        return (int) Math.round(quemAtaca.getAtaque() * multiplicador);
    }


    boolean temGolpeCritico() {

        SecureRandom secureRandom = new SecureRandom();
        //gera um numero de 1 a 100
        int randomico = secureRandom.nextInt(100)+1 ;

        //se for um numero <= 10 tem golpe critico
        if (randomico<=10){
            // Golpe Crítico
            return true;
        }else {
            // Sem Golpe Crítico
            return false;
        }

    }

     int calcularDanoFinal(int danoBaseDoAtacante, boolean temDanoCritico, Personagem quemDefende) {

        int danoFinal = danoBaseDoAtacante - quemDefende.getDefesa();

        if (temDanoCritico){
            // aplicando o multiplicador do golpe critico
            // usei 3/2 inves de 1.5 para manter a
            // assinatura de int ao inves de double

            danoFinal = danoFinal*3/2;

        }

        if (danoFinal <= 1) {
            danoFinal=1;
            return danoFinal;
        }else{
            return danoFinal;
        }

    }

     void removerHP(int danoFinal, Personagem quemDefende) {
        //calcula o HP pós ataque e salva no Defensor
        int vidaRestante = quemDefende.getVida() - danoFinal;
        quemDefende.setVida(vidaRestante);

    }

    boolean temVencedor(Personagem P1, Personagem P2 ) {
        if (P1.getVida() > 0  &&  P2.getVida() > 0) {
            // A batalha continua
            return false;
        } else if (P1.getVida() > 0  &&  P2.getVida() <= 0) {
            // A batalha acabou
            // Personagem 1 venceu
            return true;

        }else{
            // if(P1.getVida() <= 0  &&  P2.getVida() > 0)
            // A batalha acabou
            // Personagem 2 venceu
            return true;

        }


    }

     void quemVenceu(Personagem p1, Personagem p2) {
        if (p2.getVida()<=0){
            //PERSONAGEM 1 VENCEU
            System.out.println("O vencedor do confronto foi: "+ p1);

        }else{
            //PERSONAGEM 2 VENCEU
            System.out.println("O vencedor do confronto foi: "+ p2);
        }
    }


}
