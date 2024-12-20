# **Documentação das Regras de Negócio**

## **1.1. Descrição das Partições Identificadas**

**Objetivo**: Identificar e descrever as regras que influenciam a lógica de negócio e que são usadas para particionar o domínio de entrada. Devem ser detalhadas as partições que levam a situações excepcionais, quando aplicável.

### **Exemplo Aplicado ao Projeto:**

**Regra de Negócio:** A distribuição de atributos entre Resistência, Ataque, Defesa e Velocidade deve respeitar as restrições da classe do personagem (Guerreiro ou Assassino).

**Partições Identificadas:**

1. **Classe do Personagem:**
    - **Guerreiro:** Resistência e Ataque são os atributos principais (mais altos ou empatados).
    - **Assassino:** Ataque e Velocidade são os atributos principais (mais altos ou empatados).

2. **Distribuição de Atributos:**
    - **Válida:** A soma total dos atributos é igual a 20, com cada atributo ≥ 3, e os principais atributos respeitam as restrições da classe.
    - **Inválida:** A soma total dos atributos é diferente de 20, algum atributo < 3, ou os principais atributos não respeitam as restrições da classe.

3. **Situações Excepcionais:**
    - **Excesso de Pontos:** Distribuição total excede 20 pontos.
    - **Falta de Pontos:** Distribuição total é inferior a 20 pontos.
    - **Atributos Fora do Limite:** Algum atributo < 3.

---

## **1.2. Análise dos Valores Limites Identificados**

**Objetivo**: Identificar os valores de fronteira relevantes para cada partição e determinar os valores que devem ser testados.

### **Exemplo Aplicado ao Projeto:**

**Valores Limites por Regra de Negócio:**

1. **Distribuição de Atributos:**
    - **Limite Inferior:** A soma de atributos é exatamente 20.
    - **Limite Superior:** A soma de atributos excede 20.
    - **Valores Mínimos por Atributo:** Cada atributo ≥ 3.
    - **Valores Máximos por Atributo:** Nenhum atributo pode exceder `20 - (soma dos outros 3)`.

2. **Restrições de Classe:**
    - **Guerreiro:**
        - Resistência >= Ataque.
        - Defesa e Velocidade ≤ Resistência e Ataque.
    - **Assassino:**
        - Ataque >= Velocidade.
        - Resistência e Defesa ≤ Ataque e Velocidade.

**Casos de Teste Baseados em Valores Limites:**

- Testar os valores mínimos e máximos válidos para cada atributo.
- Testar limites onde a distribuição de atributos passa a ser inválida (e.g., valores inferiores a 3, soma ≠ 20).
- Testar cenários onde as restrições de classe são violadas.

---

## **1.3. Tabela de Decisão**

**Objetivo**: Representar as condições e ações de cada regra de negócio para facilitar a geração de casos de teste.

### **Tabela de Decisão: Regra de Distribuição de Atributos**

| **Condições**                       | **Guerreiro (R >= A)** | **Assassino (A >= V)** | **Atributo < 3** | **Soma ≠ 20** | **Resultado**         |
|-------------------------------------|------------------------|------------------------|------------------|---------------|-----------------------|
| Resistência >= Ataque               | Sim                    | Não                    | Não              | Não           | Distribuição Válida   |
| Ataque >= Velocidade                | Não                    | Sim                    | Não              | Não           | Distribuição Válida   |
| Resistência >= Ataque               | Não                    | Não                    | Não              | Não           | Restrição Violada     |
| Ataque >= Velocidade                | Não                    | Não                    | Sim              | Não           | Restrição Violada     |
| Soma dos Atributos = 20             | Não                    | Não                    | Não              | Sim           | Restrição Violada     |

### **Legenda:**
- **R:** Resistência.
- **A:** Ataque.
- **V:** Velocidade.

### **Uso da Tabela para Casos de Teste:**

1. Criar casos de teste para todas as combinações válidas e inválidas de condições.
2. Validar que as ações correspondem às regras esperadas (e.g., distribuição válida ou violação de restrições).
