package batalha;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


import org.junit.jupiter.api.Test;

class PersonagemTest {

	// --- TESTE DE CONSTRUTOR ---//

	@Test
	void checarConstrutorAssassinoVazioEParametrizado(){

		//Assassino inicializadp pelo construtor vazio
		Assassino ass1 = new Assassino();
		ass1.setAtaque(7);
		ass1.setVelocidade(7);
		ass1.setDefesa(3);
		ass1.setResistencia(3);

		//Assassino inicializado pelo construtor preenchido
		Assassino ass2 = new Assassino(7,3,7,3);

		//checa se os atributos correspondem entre si
		assertEquals(ass1.getAtaque(), ass2.getAtaque());
		assertEquals(ass1.getDefesa(), ass2.getDefesa());
		assertEquals(ass1.getVelocidade(), ass2.getVelocidade());
		assertEquals(ass1.getResistencia(), ass2.getResistencia());


	}
	@Test
	void checarConstrutorGuerreiroVazioEParametrizado(){

		//Guerreiro inicializadp pelo construtor vazio
		Guerreiro gue1 = new Guerreiro();
		gue1.setAtaque(7);
		gue1.setVelocidade(3);
		gue1.setDefesa(3);
		gue1.setResistencia(7);

		//Assassino inicializado pelo construtor preenchido
		Guerreiro gue2 = new Guerreiro(7,3,3,7);

		//checa se os atributos correspondem entre si
		assertEquals(gue1.getAtaque(), gue2.getAtaque());
		assertEquals(gue1.getDefesa(), gue2.getDefesa());
		assertEquals(gue1.getVelocidade(), gue2.getVelocidade());
		assertEquals(gue1.getResistencia(), gue2.getResistencia());

	}



	// --- TESTE DE CHECAR TOTAL ---//
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


	// --- TESTE DE MINIMO ---//

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


	// --- TESTE DE REGRA DE CLASSE ---//

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

	@Test
	void testeChecarRegraDeClasseGuerreiro2(){
		Personagem p = new Guerreiro();

		p.setAtaque(5);
		p.setResistencia(7);
		p.setDefesa(3);
		p.setVelocidade(5);

		assertFalse(p.checarEGuerreiro());
	}

	@Test
	void testeChecarRegraDeClasseGuerreiro3(){
		Personagem p = new Guerreiro();

		p.setAtaque(7);
		p.setResistencia(5);
		p.setDefesa(5);
		p.setVelocidade(3);

		assertFalse(p.checarEGuerreiro());
	}

	@Test
	void testeChecarRegraDeClasseGuerreiro4(){
		Personagem p = new Guerreiro();

		p.setAtaque(7);
		p.setResistencia(5);
		p.setVelocidade(5);
		p.setDefesa(3);

		assertFalse(p.checarEGuerreiro());
	}

	@Test
	void testeChecarRegraDeClasseGuerreiro5(){
		Personagem p = new Guerreiro();

		p.setAtaque(5);
		p.setResistencia(5);
		p.setDefesa(6);
		p.setVelocidade(4);

		assertFalse(p.checarEGuerreiro());
	}

	@Test
	void testeChecarRegraDeClasseGuerreiro6(){
		
		Personagem p = new Guerreiro();

		p.setAtaque(5);
		p.setResistencia(5);
		p.setVelocidade(6);
		p.setDefesa(4);

		assertFalse(p.checarEGuerreiro());
	}

	@Test
	void testeChecarRegraDeClasseAssassino2(){
		Personagem p = new Assassino();

		p.setAtaque(6);
		p.setVelocidade(5);
		p.setResistencia(6);
		p.setDefesa(3);

		assertFalse(p.checarEAssasino());
	}

	@Test
	void testeChecarRegraDeClasseAssassino3(){
		Personagem p = new Assassino();

		p.setAtaque(5);
		p.setVelocidade(5);
		p.setDefesa(6);
		p.setResistencia(4);

		assertFalse(p.checarEAssasino());

	}

	@Test
	void testeChecarRegraDeClasseAssassino4(){
		Personagem p = new Assassino();

		p.setAtaque(5);
		p.setVelocidade(5);
		p.setResistencia(6);
		p.setDefesa(4);

		assertFalse(p.checarEAssasino());
	}

	@Test
	void testeChecarRegraDeClasseAssassino5(){
		Personagem p = new Assassino();

		p.setVelocidade(6);
		p.setAtaque(5);
		p.setDefesa(6);
		p.setResistencia(3);

		assertFalse(p.checarEAssasino());

	}

	@Test
	void testeChecarRegraDeClasseAssassino6(){
		Personagem p = new Assassino();

		p.setVelocidade(6);
		p.setAtaque(5);
		p.setResistencia(6);
		p.setDefesa(3);

		assertFalse(p.checarEAssasino());
	}

	@ParameterizedTest(name = "Guerreiro próximo ao limite com Ataque {0} e Resistência {1}")
	@CsvSource({
			"6, 6, 4, 4", // Válido
			"7, 6, 4, 3", // Válido
			"5, 5, 6, 4"  // Inválido
	})
	void testeLimitesGuerreiro(int ataque, int resistencia, int defesa, int velocidade) {
		Personagem guerreiro = new Guerreiro();
		guerreiro.setAtaque(ataque);
		guerreiro.setResistencia(resistencia);
		guerreiro.setDefesa(defesa);
		guerreiro.setVelocidade(velocidade);

		if (guerreiro.checarEGuerreiro()) {
			assertDoesNotThrow(guerreiro::checarRegraDeClasse, "Os atributos devem ser válidos para um Guerreiro.");
		} else {
			assertThrows(IllegalStateException.class, guerreiro::checarRegraDeClasse, "Os atributos não devem ser válidos.");
		}
	}

	@ParameterizedTest(name = "Assassino inválido com Ataque {0}, Velocidade {1}, Defesa {2}, Resistência {3}")
	@CsvSource({
			"7, 7, 8, 3", // Defesa maior que Velocidade (Inválido)
			"7, 7, 3, 8", // Resistência maior que Velocidade (Inválido)
			"6, 5, 6, 4"  // Defesa igual a Ataque, mas maior que Velocidade (Inválido)
	})
	void testeLimitesAssassino(int ataque, int velocidade, int defesa, int resistencia) {
		Personagem assassino = new Assassino();
		assassino.setAtaque(ataque);
		assassino.setVelocidade(velocidade);
		assassino.setDefesa(defesa);
		assassino.setResistencia(resistencia);

		// Verifica que as regras da classe lançam exceção para cenários inválidos
		assertThrows(IllegalStateException.class, assassino::checarRegraDeClasse,
				"Os atributos não devem ser válidos para a classe Assassino.");
	}

	@ParameterizedTest(name = "Guerreiro com Ataque {0} e Resistência {1} válidos")
	@CsvSource({
			"7, 7, 3, 3",
			"6, 6, 4, 4",
			"8, 8, 2, 2"
	})
	void testeEmpatesGuerreiro(int ataque, int resistencia, int defesa, int velocidade) {
		Personagem guerreiro = new Guerreiro();
		guerreiro.setAtaque(ataque);
		guerreiro.setResistencia(resistencia);
		guerreiro.setDefesa(defesa);
		guerreiro.setVelocidade(velocidade);
		assertTrue(guerreiro.checarEGuerreiro(), "Os atributos devem ser válidos para um Guerreiro.");
	}

	@ParameterizedTest(name = "Assassino válido com Ataque={0}, Velocidade={1}, Defesa={2}, Resistência={3}")
	@CsvSource({
			"7, 7, 3, 3", // Ataque e Velocidade empatados, secundários menores
			"7, 6, 4, 3", // Ataque maior que Velocidade
			"5, 7, 4, 4", // Velocidade maior que Ataque
			"6, 6, 4, 4"  // Empate nos principais, secundários válidos
	})
	void testeEmpatesAssassino(int ataque, int velocidade, int defesa, int resistencia) {
		Personagem assassino = new Assassino();
		assassino.setAtaque(ataque);
		assassino.setVelocidade(velocidade);
		assassino.setDefesa(defesa);
		assassino.setResistencia(resistencia);

		assertDoesNotThrow(assassino::checarRegraDeClasse, "Os atributos devem ser válidos para um Assassino.");
	}


	@ParameterizedTest(name = "Guerreiro inválido com Defesa {2} ou Velocidade {3} maior que Ataque/Resistência")
	@CsvSource({
			"7, 7, 8, 3", // Defesa maior que Resistência
			"7, 7, 3, 8"  // Velocidade maior que Resistência
	})
	void testeGuerreiroSecundarios(int ataque, int resistencia, int defesa, int velocidade) {
		Personagem guerreiro = new Guerreiro();
		guerreiro.setAtaque(ataque);
		guerreiro.setResistencia(resistencia);
		guerreiro.setDefesa(defesa);
		guerreiro.setVelocidade(velocidade);

		assertThrows(IllegalStateException.class, guerreiro::checarRegraDeClasse, "Os atributos secundários não podem ultrapassar os principais para um Guerreiro.");
	}

	@ParameterizedTest(name = "Assassino inválido com Defesa {2} ou Resistência {3} maior que Ataque/Velocidade")
	@CsvSource({
			"7, 7, 8, 3", // Defesa maior que Velocidade
			"7, 7, 3, 8"  // Resistência maior que Velocidade
	})
	void testeAssassinoSecundarios(int ataque, int velocidade, int defesa, int resistencia) {
		Personagem assassino = new Assassino();
		assassino.setAtaque(ataque);
		assassino.setVelocidade(velocidade);
		assassino.setDefesa(defesa);
		assassino.setResistencia(resistencia);

		assertThrows(IllegalStateException.class, assassino::checarRegraDeClasse, "Os atributos secundários não podem ultrapassar os principais para um Assassino.");
	}


}
