package batalha;

/**
 * Classe Assassino representa um tipo específico de personagem em um sistema de batalha.
 * Herda da classe base Personagem e implementa regras específicas para a validação
 * dos atributos de classe.
 *
 * O Assassino é caracterizado por possuir maior ênfase nos atributos de
 * ataque e velocidade, enquanto resistência e defesa têm valores relativamente
 * menores. Esta classe assegura que tais características sejam respeitadas através
 * da validação das regras de classe.
 */
public class Assassino extends Personagem {

	/**
	 * Construtor da classe Assassino que permite inicializar os atributos de ataque,
	 * defesa, velocidade e resistência.
	 *
	 * @param ataque valor do atributo de ataque do Assassino.
	 * @param defesa valor do atributo de defesa do Assassino.
	 * @param velocidade valor do atributo de velocidade do Assassino.
	 * @param resistencia valor do atributo de resistência do Assassino.
	 */
	public Assassino(Integer ataque, Integer defesa, Integer velocidade, Integer resistencia) {
		super(ataque, defesa, velocidade, resistencia);
	}

	/**
	 * Construtor padrão da classe Assassino. Cria uma instância com valores
	 * padrão não inicializados para os atributos.
	 */
	public Assassino() {
		// Construtor vazio para permitir instância sem parâmetros.
	}

	/**
	 * Método que verifica se os atributos do personagem obedecem às regras da classe Assassino.
	 * A regra principal exige que os atributos de ataque e velocidade sejam
	 * maiores que os atributos de resistência e defesa.
	 *
	 * @throws IllegalStateException se os atributos do Assassino não seguirem a regra da classe.
	 */
	@Override
	final void checarRegraDeClasse() {
		// Obtém os valores dos atributos do personagem.
		int velocidade = this.getVelocidade();
		int resistencia = this.getResistencia();
		int ataque = this.getAtaque();
		int defesa = this.getDefesa();

		// Verifica se os atributos obedecem à regra de classe para Assassino.
		if (checarEAssasino()) {
			// É válido como Assassino.
		} else {
			throw new IllegalStateException("Os atributos não seguem a regra de classe para Assassino!");
		}
	}
}
