## Aplicação de MC/DC no `if (!evadiu)` na função `atacar` em Batalha

O fluxo de decisão do `if` depende de múltiplas condições indiretas:

### Dependências:
1. **`verificarEvasao(quemAtaca, quemDefende, randomico)`**:
    - Determina se o ataque foi evitado ou não.
    - Depende da fórmula de cálculo de evasão e do valor aleatório gerado.

2. **Cálculo do dano base (`calcularDanoBase`)**:
    - Envolve atributos do atacante e variação aleatória no dano.

3. **Verificação de golpe crítico (`temGolpeCritico`)**:
    - Pode multiplicar o dano final por um fator.

4. **Cálculo do dano final (`calcularDanoFinal`)**:
    - Ajusta o dano base considerando o golpe crítico e a defesa do defensor.

---

### 1. Aplicar MC/DC no `if (!evadiu)`
Este `if` possui uma única condição direta (`!evadiu`), mas seu comportamento depende de subcondições. Precisamos testar:

- **Evasão bem-sucedida (`evadiu = true`)**: O código dentro do `if` não deve ser executado.
- **Evasão malsucedida (`evadiu = false`)**: O código dentro do `if` deve ser executado.

Para atingir MC/DC, é necessário testar o impacto de cada subcondição que contribui para o resultado de `evadiu`.

---

### 2. Estruturar condições e decisões
#### Condições identificadas:
1. **`randomico <= chanceDeEvasao`**:
    - O número aleatório gerado está dentro da chance de evasão.
    - Influencia diretamente `evadiu`.

2. **`chanceDeEvasao > 0`**:
    - O defensor tem uma chance válida de evasão.
    - Depende da diferença de velocidades.

3. **`defensor.getVelocidade() > atacante.getVelocidade()`**:
    - Determina se há chance de evasão (velocidade do defensor maior que a do atacante).

---

### Tabela MC/DC para `if (!evadiu)`

| Caso de Teste | `defensor.getVelocidade() > atacante.getVelocidade()` | `chanceDeEvasao > 0` | `randomico <= chanceDeEvasao` | Resultado (`evadiu`) | Executa Código de Ataque |
|---------------|--------------------------------------------------------|-----------------------|------------------------------|-----------------------|--------------------------|
| 1             | `true`                                                 | `true`                | `true`                       | `true`                | `não`                    |
| 2             | `true`                                                 | `true`                | `false`                      | `false`               | `sim`                    |
| 3             | `true`                                                 | `false`               | `false`                      | `false`               | `sim`                    |
| 4             | `false`                                                | `false`               | `false`                      | `false`               | `sim`                    |

---

### Observações:
- Cada linha da tabela cobre uma combinação distinta de condições, garantindo que cada uma seja exercitada ao mudar de "verdadeira" para "falsa".
- Esta análise atende ao critério MC/DC, validando todas as ramificações do código e as condições que influenciam a decisão principal do `if`.
