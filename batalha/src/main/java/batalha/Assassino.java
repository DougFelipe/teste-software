package batalha;

public class Assassino extends Personagem {

	public Assassino(Integer ataque, Integer defesa, Integer velocidade, Integer resistencia) {
		super(ataque, defesa, velocidade, resistencia);
	}
	public Assassino(){

	}

	@Override
	final void checarRegraDeClasse() {
		// TODO Auto-generated method stub
		//atk e vel >>> res e def


		int velocidade = this.getVelocidade();
		int resistencia = this.getResistencia();
		int ataque = this.getAtaque();
		int defesa = this.getDefesa();

		if(		(ataque > velocidade && velocidade > defesa && defesa > resistencia) ||
				(ataque > velocidade && velocidade > resistencia && resistencia > defesa) ||
				(ataque == velocidade && velocidade > defesa && defesa > resistencia) ||
				(ataque == velocidade && velocidade > resistencia && resistencia > defesa) ||
				(velocidade > ataque && ataque > defesa && defesa > resistencia) ||
				(velocidade==ataque && ataque > defesa && defesa == resistencia )||
				(velocidade > ataque && ataque > resistencia && resistencia > defesa)
		){
			//é Assassino

		}else{
			throw new IllegalStateException("Os atributos não seguem a regra de classe para Assasino!");
		}

	}
}
