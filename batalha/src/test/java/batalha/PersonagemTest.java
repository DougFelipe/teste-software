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

	// --- TESTE DE CONSTRUTOR --- //

	/**
	 * Testa os construtores vazio e parametrizado das classes `Assassino` e `Guerreiro`.
	 *
	 * <p>O teste verifica se os atributos definidos manualmente em um personagem
	 * criado com o construtor vazio são iguais aos atributos de um personagem
	 * criado com o construtor parametrizado.</p>
	 *
	 * @param classe a classe do personagem a ser testado ("Assassino" ou "Guerreiro").
	 * @param ataque o valor do atributo ataque do personagem.
	 * @param velocidade o valor do atributo velocidade do personagem.
	 * @param defesa o valor do atributo defesa do personagem.
	 * @param resistencia o valor do atributo resistência do personagem.
	 */
	@ParameterizedTest
	@CsvSource({
			"Assassino, 7, 7, 3, 3",
			"Guerreiro, 7, 3, 3, 7"
	})
	void checarConstrutoresVazioEParametrizado(String classe, int ataque, int velocidade, int defesa, int resistencia) {
		Personagem personagemVazio = classe.equals("Assassino") ? new Assassino() : new Guerreiro();
		personagemVazio.setAtaque(ataque);
		personagemVazio.setVelocidade(velocidade);
		personagemVazio.setDefesa(defesa);
		personagemVazio.setResistencia(resistencia);

		Personagem personagemParametrizado = classe.equals("Assassino")
				? new Assassino(ataque, defesa, velocidade, resistencia)
				: new Guerreiro(ataque, velocidade, defesa, resistencia);

		assertEquals(personagemVazio.getAtaque(), personagemParametrizado.getAtaque());
		assertEquals(personagemVazio.getDefesa(), personagemParametrizado.getDefesa());
		assertEquals(personagemVazio.getVelocidade(), personagemParametrizado.getVelocidade());
		assertEquals(personagemVazio.getResistencia(), personagemParametrizado.getResistencia());
	}

// --- TESTE DE CHECAR TOTAL --- //

	/**
	 * Testa o método `checarTotal` para verificar se o somatório dos atributos de um personagem
	 * corresponde às regras definidas (deve ser igual a 20).
	 *
	 * Se o somatório for diferente de 20, uma exceção `IllegalStateException` é esperada.
	 *
	 * @param ataque o valor do atributo ataque do personagem.
	 * @param defesa o valor do atributo defesa do personagem.
	 * @param resistencia o valor do atributo resistência do personagem.
	 * @param velocidade o valor do atributo velocidade do personagem.
	 * @param esperaExcecao indica se uma exceção é esperada para o caso de teste.
	 */
	@ParameterizedTest
	@CsvSource({
			"7, 6, 4, 3, false",  // Total = 20, não deve lançar exceção
			"7, 5, 4, 3, true",   // Total = 19, deve lançar exceção
			"9, 5, 4, 3, true"    // Total = 21, deve lançar exceção
	})
	void testeChecarTotal(int ataque, int defesa, int resistencia, int velocidade, boolean esperaExcecao) {
		Personagem p = new Personagem() {
			@Override
			void checarRegraDeClasse() {
				// Implementação vazia para fins de teste
			}
		};

		p.setAtaque(ataque);
		p.setDefesa(defesa);
		p.setResistencia(resistencia);
		p.setVelocidade(velocidade);

		if (esperaExcecao) {
			assertThrows(IllegalStateException.class, p::checarTotal);
		} else {
			assertDoesNotThrow(p::checarTotal);
		}
	}

// --- TESTE DE MINIMO --- //

	/**
	 * Testa o método `checarValorMinimo` para verificar se todos os atributos de um personagem
	 * atendem ao valor mínimo exigido (>= 3).
	 *
	 * Se algum atributo for menor que 3, uma exceção `IllegalStateException` é esperada.
	 *
	 * @param ataque o valor do atributo ataque do personagem.
	 * @param defesa o valor do atributo defesa do personagem.
	 * @param resistencia o valor do atributo resistência do personagem.
	 * @param velocidade o valor do atributo velocidade do personagem.
	 * @param deveLancarExcecao indica se uma exceção é esperada para o caso de teste.
	 */
	@ParameterizedTest
	@CsvSource({
			"7, 5, 2, 4, true",  // Resistência menor que 3, deve lançar exceção
			"7, 5, 3, 4, false", // Resistência igual a 3, não deve lançar exceção
			"7, 5, 4, 4, false"  // Resistência maior que 3, não deve lançar exceção
	})
	void testeChecarValorMinimo(int ataque, int defesa, int resistencia, int velocidade, boolean deveLancarExcecao) {
		Personagem p = new Personagem() {
			@Override
			void checarRegraDeClasse() {
				if (!checarEGuerreiro() && !checarEAssasino()) {
					throw new IllegalStateException("Os atributos não correspondem a nenhuma classe válida (Guerreiro ou Assassino).");
				}
			}
		};

		p.setAtaque(ataque);
		p.setDefesa(defesa);
		p.setResistencia(resistencia);
		p.setVelocidade(velocidade);

		if (deveLancarExcecao) {
			assertThrows(IllegalStateException.class, () -> p.checarValorMinimo(resistencia));
		} else {
			assertDoesNotThrow(() -> p.checarValorMinimo(resistencia));
		}
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


	/**
	 * Testa os limites dos atributos de um Guerreiro para verificar se seguem as regras da classe.
	 *
	 * @param ataque valor do atributo ataque do Guerreiro.
	 * @param resistencia valor do atributo resistência do Guerreiro.
	 * @param defesa valor do atributo defesa do Guerreiro.
	 * @param velocidade valor do atributo velocidade do Guerreiro.
	 */
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

	/**
	 * Testa os limites de um Assassino com atributos que não seguem as regras de sua classe.
	 *
	 * @param ataque valor do atributo ataque do Assassino.
	 * @param velocidade valor do atributo velocidade do Assassino.
	 * @param defesa valor do atributo defesa do Assassino.
	 * @param resistencia valor do atributo resistência do Assassino.
	 */
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

		assertThrows(IllegalStateException.class, assassino::checarRegraDeClasse,
				"Os atributos não devem ser válidos para a classe Assassino.");
	}

	/**
	 * Testa atributos de empate em Guerreiro para verificar se permanecem válidos conforme as regras da classe.
	 *
	 * @param ataque valor do atributo ataque do Guerreiro.
	 * @param resistencia valor do atributo resistência do Guerreiro.
	 * @param defesa valor do atributo defesa do Guerreiro.
	 * @param velocidade valor do atributo velocidade do Guerreiro.
	 */
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

	/**
	 * Testa atributos de empate em Assassino para verificar se permanecem válidos conforme as regras da classe.
	 *
	 * @param ataque valor do atributo ataque do Assassino.
	 * @param velocidade valor do atributo velocidade do Assassino.
	 * @param defesa valor do atributo defesa do Assassino.
	 * @param resistencia valor do atributo resistência do Assassino.
	 */
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

	/**
	 * Testa casos inválidos onde atributos secundários de Guerreiro ultrapassam os principais.
	 *
	 * @param ataque valor do atributo ataque do Guerreiro.
	 * @param resistencia valor do atributo resistência do Guerreiro.
	 * @param defesa valor do atributo defesa do Guerreiro.
	 * @param velocidade valor do atributo velocidade do Guerreiro.
	 */
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

	/**
	 * Testa casos inválidos onde atributos secundários de Assassino ultrapassam os principais.
	 *
	 * @param ataque valor do atributo ataque do Assassino.
	 * @param velocidade valor do atributo velocidade do Assassino.
	 * @param defesa valor do atributo defesa do Assassino.
	 * @param resistencia valor do atributo resistência do Assassino.
	 */
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
