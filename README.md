# Projeto: Batalha por Turnos

## Autores
- Douglas Felipe
- Matheus Queiroz
- Pedro Arthur

---

## Introdução

Este projeto foi desenvolvido como parte do trabalho prático da disciplina de Teste de Software, ministrada pelo professor Eiji Adachi.  
O objetivo principal é implementar a lógica de um jogo de batalha por turnos, com foco em duas classes de personagens (`Guerreiro` e `Assassino`), e criar testes automatizados para verificar o funcionamento correto das funcionalidades implementadas.

O jogo segue uma mecânica baseada em atributos como **Ataque**, **Defesa**, **Velocidade** e **Resistência**, determinando as ações em combate, como chance de evasão, golpes críticos e cálculo de dano.

---

## Instruções de Compilação e Execução

1. Certifique-se de ter o **Java JDK 8 ou superior** e o **Maven** instalados em sua máquina.
2. Clone este repositório:  
   ```  
   git clone https://github.com/DougFelipe/teste-software  
   ```
3. Navegue até o diretório do projeto:  
   ```  
   cd batalha 
   ```
4. Compile o projeto utilizando o Maven:  
   ```  
   mvn clean compile  
   ```
5. Para executar o programa principal, utilize o seguinte comando:  
   ```  
   mvn exec:java -Dexec.mainClass="batalha.Batalha"  
   ```

---

## Como Executar os Testes

1. Certifique-se de que as dependências estão instaladas (veja a seção "Dependências").
2. Para rodar os testes automatizados, utilize o Maven:  
   ```  
   mvn test  
   ```
3. Os resultados dos testes serão exibidos no terminal.

---

## Como Gerar o Relatório de Cobertura

1. Plugin de cobertura configurado no arquivo `pom.xml` utilizando o **JaCoCo**).
2. Gere o relatório de cobertura com o seguinte comando:  
   ```  
   mvn jacoco:report  
   ```
3. Após a execução, o relatório estará disponível no caminho:  
   ```  
   target/site/jacoco/index.html  
   ```
4. Abra o arquivo `index.html` em seu navegador para visualizar os resultados.

---

## Dependências

O projeto utiliza as seguintes dependências:
- **JUnit 5**: Para testes automatizados.
    - Já configurado no arquivo `pom.xml`.
- **Maven**: Para gerenciamento de dependências e execução do projeto.

Para instalar as dependências, basta executar:  
```  
mvn install  
```

---

## Uso do Programa

O jogo implementa uma batalha por turnos entre duas classes de personagens: **Guerreiro** e **Assassino**.  
A seguir, estão exemplos das principais funcionalidades:

1. **Criação de Personagens**  
   ```  
   Guerreiro guerreiro = new Guerreiro(7, 3, 3, 7);  
   Assassino assassino = new Assassino(6, 3, 6, 5);  
   ```

2. **Batalha**  
   O combate segue as seguintes etapas:
    - Determinação de quem ataca primeiro, baseado na **Velocidade**.
    - Execução de ataques, com verificação de **evasão** e possibilidade de **golpe crítico**.
    - Cálculo de dano com base nos atributos dos personagens.

   ```  
   Batalha batalha = new Batalha();  
   batalha.iniciarBatalha(guerreiro, assassino);  
   ```

3. **Testes Automatizados**  
   O projeto inclui uma ampla cobertura de testes que verificam as seguintes funcionalidades:
    - Validação dos atributos dos personagens.
    - Regras específicas das classes (`Guerreiro` e `Assassino`).
    - Mecânica de combate, como cálculo de dano e chance de evasão.
    - Os relatórios do teste estão no diretório \batalha\target\site\jacoco\index.html

---

## Relatórios Automatizados   
- Disponíveis no diretório \batalha\target\site\jacoco\index.html
- Relatório MC/DC em um arquivo markdown no diretório \batalha\target\site\jacoco

## Estrutura do Projeto

```plaintext
├── batalha
│   ├── src
│   │   ├── main
│   │   │   └── java
│   │   │       └── batalha
│   │   │           ├── Assassino.java
│   │   │           ├── Batalha.java
│   │   │           ├── Guerreiro.java
│   │   │           └── Personagem.java
│   │   └── test
│   │       └── java
│   │           └── batalha
│   │               ├── BatalhaTest.java
│   │               └── PersonagemTest.java
├── pom.xml
├── README.md
└── .idea
```
