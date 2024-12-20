package batalha;

/**
 * Classe abstrata Personagem representa a base para diferentes tipos de personagens
 * em um sistema de batalha. Define atributos, comportamentos e regras que
 * devem ser seguidas pelas subclasses específicas.
 *
 * <p>Os atributos de um personagem incluem ataque, defesa, velocidade, resistência
 * e vida. A classe também implementa verificações para assegurar que as subclasses
 * obedeçam a regras específicas de atributos e valores.</p>
 */
public abstract class Personagem {

	/** Atributo que representa o ataque do personagem. */
	private Integer ataque;

	/** Atributo que representa a defesa do personagem. */
	private Integer defesa;

	/** Atributo que representa a velocidade do personagem. */
	private Integer velocidade;

	/** Atributo que representa a resistência do personagem. */
	private Integer resistencia;

	/** Atributo que representa a vida do personagem. Inicializado com 100. */
	private Integer vida;

	/**
	 * Construtor padrão da classe Personagem.
	 * Inicializa a vida com o valor padrão de 100.
	 */
	Personagem() {
		this.vida = 100;
	}

	/**
	 * Construtor que permite inicializar os atributos de ataque, defesa, velocidade
	 * e resistência. A vida é inicializada com 100.
	 *
	 * <p>Ao criar uma instância, as seguintes validações são realizadas:</p>
	 * <ul>
	 *   <li>Verificação do somatório dos atributos ser igual a 20.</li>
	 *   <li>Verificação do valor mínimo de cada atributo ser maior ou igual a 3.</li>
	 *   <li>Verificação das regras específicas de classe (subclasses).</li>
	 * </ul>
	 *
	 * @param ataque valor do atributo de ataque do personagem.
	 * @param defesa valor do atributo de defesa do personagem.
	 * @param velocidade valor do atributo de velocidade do personagem.
	 * @param resistencia valor do atributo de resistência do personagem.
	 * @throws IllegalStateException se as validações falharem.
	 */
	public Personagem(Integer ataque, Integer defesa, Integer velocidade, Integer resistencia) {
		this.ataque = ataque;
		this.defesa = defesa;
		this.resistencia = resistencia;
		this.velocidade = velocidade;
		this.vida = 100;

		checarTotal();
		checarValorMinimo();
		checarRegraDeClasse();
	}

	/**
	 * Método abstrato que deve ser implementado pelas subclasses.
	 * Define regras específicas de validação para cada tipo de personagem.
	 *
	 * @throws IllegalStateException se os atributos não obedecerem às regras da classe.
	 */
	abstract void checarRegraDeClasse();

	/**
	 * Verifica se todos os atributos do personagem possuem o valor mínimo exigido.
	 *
	 * @throws IllegalStateException se algum atributo for menor que 3.
	 */
	final void checarValorMinimo() {
		checarValorMinimo(ataque);
		checarValorMinimo(defesa);
		checarValorMinimo(velocidade);
		checarValorMinimo(resistencia);
	}

	/**
	 * Verifica se o somatório dos atributos (ataque, defesa, velocidade e resistência)
	 * é igual a 20.
	 *
	 * @throws IllegalStateException se o somatório for diferente de 20.
	 */
	final void checarTotal() {
		if (this.ataque + this.defesa + this.velocidade + this.resistencia != 20) {
			throw new IllegalStateException("Somatório dos atributos deve ser igual a 20.");
		}
	}

	/**
	 * Verifica se os atributos do personagem atendem às condições para ser classificado como Assassino.
	 *
	 * @return {@code true} se os atributos seguirem a regra para Assassino, {@code false} caso contrário.
	 */
	final boolean checarEAssasino() {
		int velocidade = this.getVelocidade();
		int resistencia = this.getResistencia();
		int ataque = this.getAtaque();
		int defesa = this.getDefesa();

		return (ataque > velocidade && velocidade > defesa && defesa > resistencia) ||
				(ataque > velocidade && velocidade > resistencia && resistencia > defesa) ||
				(ataque == velocidade && velocidade > defesa && defesa > resistencia) ||
				(ataque == velocidade && velocidade > resistencia && resistencia > defesa) ||
				(ataque == velocidade && velocidade > resistencia && resistencia == defesa) ||
				(velocidade > ataque && ataque > defesa && defesa > resistencia) ||
				(velocidade > ataque && ataque > defesa && defesa == resistencia) ||
				(velocidade > ataque && ataque > resistencia && resistencia > defesa);
	}

	/**
	 * Verifica se os atributos do personagem atendem às condições para ser classificado como Guerreiro.
	 *
	 * @return {@code true} se os atributos seguirem a regra para Guerreiro, {@code false} caso contrário.
	 */
	final boolean checarEGuerreiro() {
		int velocidade = this.getVelocidade();
		int resistencia = this.getResistencia();
		int ataque = this.getAtaque();
		int defesa = this.getDefesa();

		return (resistencia > ataque && ataque > defesa && defesa > velocidade) ||
				(resistencia > ataque && ataque > velocidade && velocidade > defesa) ||
				(ataque > resistencia && resistencia > defesa && defesa > velocidade) ||
				(ataque > resistencia && resistencia > velocidade && velocidade > defesa) ||
				(ataque == resistencia && resistencia > velocidade && velocidade > defesa) ||
				(ataque == resistencia && resistencia > defesa && defesa > velocidade) ||
				(ataque == resistencia && resistencia > defesa && defesa == velocidade);
	}

	/**
	 * Verifica se um atributo individual possui o valor mínimo exigido.
	 *
	 * @param atributo valor do atributo a ser verificado.
	 * @throws IllegalStateException se o valor do atributo for menor que 3.
	 */
	final void checarValorMinimo(Integer atributo) {
		if (atributo < 3) {
			throw new IllegalStateException("O valor mínimo dos atributos deve ser maior ou igual a 3.");
		}
	}

	// Métodos getter e setter para os atributos.

	public Integer getAtaque() {
		return ataque;
	}

	void setAtaque(Integer ataque) {
		this.ataque = ataque;
	}

	public Integer getDefesa() {
		return defesa;
	}

	void setDefesa(Integer defesa) {
		this.defesa = defesa;
	}

	public Integer getVelocidade() {
		return velocidade;
	}

	void setVelocidade(Integer velocidade) {
		this.velocidade = velocidade;
	}

	public Integer getResistencia() {
		return resistencia;
	}

	void setResistencia(Integer resistencia) {
		this.resistencia = resistencia;
	}

	public Integer getVida() {
		return vida;
	}

	public void setVida(Integer vida) {
		this.vida = vida;
	}
}
