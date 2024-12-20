package batalha;

import java.security.SecureRandom;

/**
 * Classe Batalha representa a lógica de um combate entre dois personagens.
 *
 * <p>Inclui métodos para determinar quem começa atacando, verificar evasões,
 * calcular danos, realizar ataques, e determinar o vencedor do confronto.</p>
 */
public class Batalha {

    /**
     * Determina qual personagem começa atacando.
     *
     * <p>O personagem com maior velocidade inicia. Caso ambos tenham a mesma velocidade,
     * a escolha é feita de forma aleatória.</p>
     *
     * @param personagem1 o primeiro personagem participante.
     * @param personagem2 o segundo personagem participante.
     * @return uma string indicando qual personagem começa atacando.
     */
    String determinarQuemComecaAtacando(Personagem personagem1, Personagem personagem2) {
        if (personagem1.getVelocidade().equals(personagem2.getVelocidade())) {
            SecureRandom secureRandom = new SecureRandom();
            int randomico = secureRandom.nextInt(2);
            return randomico == 0 ? "personagem 1 começa" : "personagem 2 começa";
        } else if (personagem1.getVelocidade() > personagem2.getVelocidade()) {
            return "personagem 1 começa";
        } else {
            return "personagem 2 começa";
        }
    }

    /**
     * Verifica se o defensor consegue se evadir de um ataque.
     *
     * <p>A chance de evasão é baseada na diferença de velocidade entre o atacante e o defensor,
     * com uma chance máxima de 50%. Caso a velocidade do defensor seja menor ou igual à do atacante,
     * a chance de evasão é 0%.</p>
     *
     * @param atacante o personagem que está atacando.
     * @param defensor o personagem que está defendendo.
     * @param randomico um número aleatório entre 1 e 100 para avaliar a evasão.
     * @return {@code true} se o defensor conseguiu evadir, {@code false} caso contrário.
     */
    boolean verificarEvasao(Personagem atacante, Personagem defensor, int randomico) {
        int chanceDeEvasao = Math.min(50, (defensor.getVelocidade() - atacante.getVelocidade()) * 2);
        return randomico <= chanceDeEvasao;
    }

    /**
     * Realiza o ataque de um personagem contra outro.
     *
     * <p>O método realiza várias etapas:
     * <ul>
     *     <li>Gera um número aleatório e verifica evasão.</li>
     *     <li>Calcula o dano base do ataque.</li>
     *     <li>Determina se o ataque é crítico.</li>
     *     <li>Calcula o dano final e aplica ao HP do defensor.</li>
     * </ul>
     * </p>
     *
     * @param quemAtaca o personagem que está realizando o ataque.
     * @param quemDefende o personagem que está se defendendo.
     */
    void atacar(Personagem quemAtaca, Personagem quemDefende) {
        SecureRandom randomEvasao = new SecureRandom();
        int randomico = randomEvasao.nextInt(100) + 1;
        boolean evadiu = verificarEvasao(quemAtaca, quemDefende, randomico);

        if (!evadiu) {
            SecureRandom randomDanoBase = new SecureRandom();
            int variacaoDeDano = randomDanoBase.nextInt(5);
            int danoBase = calcularDanoBase(quemAtaca, variacaoDeDano);

            boolean golpeCritico = temGolpeCritico();
            int danoFinal = calcularDanoFinal(danoBase, golpeCritico, quemDefende);
            removerHP(danoFinal, quemDefende);
        }
    }

    /**
     * Calcula o dano base de um ataque.
     *
     * @param quemAtaca o personagem que está atacando.
     * @param variacaoDeDano variação aleatória para modificar o dano base.
     * @return o dano base calculado.
     */
    int calcularDanoBase(Personagem quemAtaca, int variacaoDeDano) {
        double multiplicador = 0.8 + (variacaoDeDano * 0.1);
        return (int) Math.round(quemAtaca.getAtaque() * multiplicador);
    }

    /**
     * Determina se o ataque tem golpe crítico.
     *
     * <p>Golpes críticos têm uma chance de 10% de ocorrer.</p>
     *
     * @return {@code true} se o ataque for crítico, {@code false} caso contrário.
     */
    boolean temGolpeCritico() {
        SecureRandom secureRandom = new SecureRandom();
        int randomico = secureRandom.nextInt(100) + 1;
        return randomico <= 10;
    }

    /**
     * Calcula o dano final de um ataque, considerando a defesa do oponente e
     * possíveis modificadores de golpe crítico.
     *
     * @param danoBaseDoAtacante o dano base do atacante.
     * @param temDanoCritico {@code true} se o ataque for crítico.
     * @param quemDefende o personagem que está defendendo.
     * @return o dano final calculado.
     */
    int calcularDanoFinal(int danoBaseDoAtacante, boolean temDanoCritico, Personagem quemDefende) {
        int danoFinal = danoBaseDoAtacante - quemDefende.getDefesa();
        if (temDanoCritico) {
            danoFinal = danoFinal * 3 / 2;
        }
        return Math.max(danoFinal, 1);
    }

    /**
     * Remove o HP do defensor com base no dano sofrido.
     *
     * @param danoFinal o dano final infligido ao defensor.
     * @param quemDefende o personagem que está recebendo o dano.
     */
    void removerHP(int danoFinal, Personagem quemDefende) {
        int vidaRestante = Math.max(quemDefende.getVida() - danoFinal, 0);
        quemDefende.setVida(vidaRestante);
    }

    /**
     * Verifica se há um vencedor na batalha.
     *
     * @param P1 o primeiro personagem participante.
     * @param P2 o segundo personagem participante.
     * @return {@code true} se houver um vencedor, {@code false} caso contrário.
     */
    boolean temVencedor(Personagem P1, Personagem P2) {
        return P1.getVida() <= 0 || P2.getVida() <= 0;
    }

    /**
     * Retorna uma mensagem indicando qual personagem venceu a batalha.
     *
     * @param p1 o primeiro personagem.
     * @param p2 o segundo personagem.
     * @return uma string indicando o vencedor do confronto.
     */
    String quemVenceu(Personagem p1, Personagem p2) {
        return p2.getVida() <= 0 ? "O vencedor do confronto foi: Personagem 1"
                : "O vencedor do confronto foi: Personagem 2";
    }
}
