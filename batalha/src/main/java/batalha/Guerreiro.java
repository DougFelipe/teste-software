package batalha;

public class Guerreiro extends Personagem {

	public Guerreiro(Integer ataque, Integer defesa, Integer velocidade, Integer resistencia) {
		super(ataque, defesa, velocidade, resistencia);
	}

	public Guerreiro(){

	}

	@Override
	final void checarRegraDeClasse() {
		// TODO Auto-generated method stub
		//atk e res >>> vel e def


		int velocidade = this.getVelocidade();
		int resistencia = this.getResistencia();
		int ataque = this.getAtaque();
		int defesa = this.getDefesa();

		if(		(resistencia > ataque && ataque > defesa && defesa > velocidade) ||
				(resistencia > ataque && ataque > velocidade && velocidade > defesa) ||
				(ataque > resistencia && resistencia > defesa && defesa > velocidade) ||
				(ataque > resistencia && resistencia > velocidade && velocidade > defesa) ||
				(ataque == resistencia && resistencia > velocidade && velocidade > defesa) ||
				(ataque == resistencia && resistencia > defesa && defesa > velocidade) ||
				(ataque == resistencia && resistencia > defesa && defesa == velocidade)){
			//é guerreiro

		}else{
			throw new IllegalStateException("Os atributos não seguem a regra de classe para Guerreiro!");
		}

	}

}