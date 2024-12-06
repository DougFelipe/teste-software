package batalha;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class PersonagemTest {

	@Test
	void testeChecarTotalIgualA20_NaoLancaExcecao() {
		Personagem p = new Personagem() {
			@Override
			void checarRegraDeClasse() {
				// Deixado em branco de propósito
			}
		};

		p.setAtaque(7);
		p.setDefesa(6);
		p.setResistencia(4);
		p.setVelocidade(3);

		assertDoesNotThrow(() -> p.checarTotal());
	}

	@Test
	void testeChecarTotalIgualA19_LancaExcecao() {
		Personagem p = new Personagem() {
			@Override
			void checarRegraDeClasse() {
				// Deixado em branco de propósito
			}
		};

		p.setAtaque(7);
		p.setDefesa(5);
		p.setResistencia(4);
		p.setVelocidade(3);

		assertThrows(IllegalStateException.class, () -> p.checarTotal());
	}

	@Test
	void testeChecarTotalIgualA21_LancaExcecao() {
		Personagem p = new Personagem() {
			@Override
			void checarRegraDeClasse() {
				// Deixado em branco de propósito
			}
		};

		p.setAtaque(9);
		p.setDefesa(5);
		p.setResistencia(4);
		p.setVelocidade(3);

		assertThrows(IllegalStateException.class, () -> p.checarTotal());
	}

	@Test
	void testeChecarMinimoIgualA2_LancaExcecao() {
		Personagem p = new Personagem() {
			@Override
			void checarRegraDeClasse() {
				// Deixado em branco de propósito
			}
		};

		p.setAtaque(7);
		p.setDefesa(5);
		p.setResistencia(2);
		p.setVelocidade(4);

		assertThrows(IllegalStateException.class, () -> p.checarValorMinimo(p.getResistencia()));
	}

	@Test
	void testeChecarMinimoIgualA3_NaoLancaExcecao() {
		Personagem p = new Personagem() {
			@Override
			void checarRegraDeClasse() {
				// Deixado em branco de propósito
			}
		};

		p.setAtaque(7);
		p.setDefesa(5);
		p.setResistencia(3);
		p.setVelocidade(4);

		assertDoesNotThrow(() -> p.checarValorMinimo(p.getResistencia()));
	}
	@Test
	void testeChecarMinimoIgualA4_NaoLancaExcecao() {
		Personagem p = new Personagem() {
			@Override
			void checarRegraDeClasse() {
				if(!checarEGuerreiro() && !checarEAssasino()){
					throw new IllegalStateException("Os atributos não correspondem a nenhuma classe válida (Guerreiro ou Assassino).");
				}

			}
		};

		p.setAtaque(7);
		p.setDefesa(5);
		p.setResistencia(3);
		p.setVelocidade(4);

		assertDoesNotThrow(() -> p.checarValorMinimo(p.getVelocidade()));
	}



	@Test
	void testeChecarRegraDeClasseGuerreiroValido(){
		Personagem p = new Guerreiro() ;
		p.setAtaque(7);
		p.setResistencia(7);
		p.setDefesa(3);
		p.setVelocidade(3);

		//Testa se será guerreiro
		//é esperado que nao jogue uma exceção
		assertDoesNotThrow(()->{
			p.checarRegraDeClasse();
		});

	}

	@Test
	void testeChecarRegraDeClasseAssassinoValido(){
		Personagem p = new Assassino();
		p.setAtaque(7);
		p.setVelocidade(6);
		p.setDefesa(4);
		p.setResistencia(3);

		//Testa se é Assassino
		//Confere se ele não joga exceção, se não houver exceção passará no teste
		assertDoesNotThrow(()->{
			p.checarRegraDeClasse();
		});

	}

	@Test
	void testeChecarRegraDeClasseAssassinoLancaExcecao(){
		Personagem naoAssassino = new Assassino();

		naoAssassino.setAtaque(5);
		naoAssassino.setVelocidade(5);
		naoAssassino.setDefesa(5);
		naoAssassino.setResistencia(5);


		//testa se não é assassino
		//é esperado que ele jogue uma exceção, caso jogue ele passará no teste
		assertThrows(IllegalStateException.class, naoAssassino::checarRegraDeClasse);


	}

	@Test
	void testeChecarRegraDeClasseGuerreiroLancaExcecao(){
		Personagem p = new Guerreiro();

		p.setAtaque(5);
		p.setResistencia(7);
		p.setDefesa(5);
		p.setVelocidade(3);

		assertThrows(IllegalStateException.class, p::checarRegraDeClasse);
	}

}
