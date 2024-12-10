package batalha;

public abstract class Personagem {
	private Integer ataque;

	private Integer defesa;

	private Integer velocidade;

	private Integer resistencia;

	private Integer vida;

	Personagem() {
		this.vida = 100;
	}

	public Personagem(Integer ataque, Integer defesa, Integer resistencia, Integer velocidade) {
		this.ataque = ataque;
		this.defesa = defesa;
		this.resistencia = resistencia;
		this.velocidade = velocidade;
		this.vida = 100;

		checarTotal();
		checarValorMinimo();
		checarRegraDeClasse();
	}

	abstract void checarRegraDeClasse();

	final void checarValorMinimo() {
		checarValorMinimo(ataque);
		checarValorMinimo(defesa);
		checarValorMinimo(velocidade);
		checarValorMinimo(resistencia);
	}
	final boolean checarEAssasino() {
		int velocidade = this.getVelocidade();
		int resistencia = this.getResistencia();
		int ataque = this.getAtaque();
		int defesa = this.getDefesa();

		if (
				(ataque > velocidade && velocidade > defesa && defesa > resistencia) ||
						(ataque > velocidade && velocidade > resistencia && resistencia > defesa) ||
						(ataque == velocidade && velocidade > defesa && defesa > resistencia) ||
						(ataque == velocidade && velocidade > resistencia && resistencia > defesa) ||
						(velocidade > ataque && ataque > defesa && defesa > resistencia) ||
						(velocidade > ataque && ataque > resistencia && resistencia > defesa)
		) {
			//É assasino
			return true;
		}
		return false;
	}

	final boolean checarEGuerreiro() {
		int velocidade = this.getVelocidade();
		int resistencia = this.getResistencia();
		int ataque = this.getAtaque();
		int defesa = this.getDefesa();

		if (
				(resistencia > ataque && ataque > defesa && defesa > velocidade) ||
						(resistencia > ataque && ataque > velocidade && velocidade > defesa) ||
						(ataque > resistencia && resistencia > defesa && defesa > velocidade) ||
						(ataque > resistencia && resistencia > velocidade && velocidade > defesa) ||
						(ataque == resistencia && resistencia > velocidade && velocidade > defesa) ||
						(ataque == resistencia && resistencia > defesa && defesa > velocidade) ||
						(ataque == resistencia && resistencia > defesa && defesa == velocidade)
		) {
			// É Guerreiro
			return true;
		}
		return false;
	}

	final void checarValorMinimo(Integer atributo) {
		// TODO

		if (atributo < 3 ) {
			throw new IllegalStateException("O valor minimo dos atributos deve ser maior ou igual a 3.");
		}

	}

	final void checarTotal() {
		if (this.ataque + this.defesa + this.velocidade + this.resistencia != 20) {
			throw new IllegalStateException("Somatório dos atributos deve ser igual a 20.");
		}
	}

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
