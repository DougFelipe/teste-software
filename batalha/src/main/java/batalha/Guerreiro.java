package batalha;

/**
 * Classe Guerreiro representa um tipo específico de personagem em um sistema de batalha.
 * Herda da classe base Personagem e implementa regras específicas para validar
 * os atributos de classe.
 *
 * O Guerreiro é caracterizado por atributos que priorizam ataque e resistência,
 * enquanto velocidade e defesa têm valores relativamente menores. Esta classe
 * verifica que essas características sejam respeitadas através da validação das regras
 * de classe.
 */
public class Guerreiro extends Personagem {

	/**
	 * Construtor da classe Guerreiro que inicializa os atributos de ataque, defesa,
	 * velocidade e resistência.
	 *
	 * @param ataque valor do atributo de ataque do Guerreiro.
	 * @param defesa valor do atributo de defesa do Guerreiro.
	 * @param velocidade valor do atributo de velocidade do Guerreiro.
	 * @param resistencia valor do atributo de resistência do Guerreiro.
	 */
	public Guerreiro(Integer ataque, Integer defesa, Integer velocidade, Integer resistencia) {
		super(ataque, defesa, velocidade, resistencia);
	}

	/**
	 * Construtor padrão da classe Guerreiro. Cria uma instância com valores
	 * padrão não inicializados para os atributos.
	 */
	public Guerreiro() {
		// Construtor vazio para permitir instância sem parâmetros.
	}

	/**
	 * Método que verifica se os atributos do personagem obedecem às regras da classe Guerreiro.
	 * A regra principal exige que os atributos de ataque e resistência sejam
	 * maiores que os atributos de velocidade e defesa.
	 *
	 * @throws IllegalStateException se os atributos do Guerreiro não seguirem a regra da classe.
	 */
	@Override
	final void checarRegraDeClasse() {
		// Obtém os valores dos atributos do personagem.
		int velocidade = this.getVelocidade();
		int resistencia = this.getResistencia();
		int ataque = this.getAtaque();
		int defesa = this.getDefesa();

		// Verifica se os atributos obedecem à regra de classe para Guerreiro.
		if (checarEGuerreiro()) {
			// É válido como Guerreiro.
		} else {
			throw new IllegalStateException("Os atributos não seguem a regra de classe para Guerreiro!");
		}
	}
}
