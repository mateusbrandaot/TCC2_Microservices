# Cálculo do Valor Presente Líquido (VPL)

O Valor Presente Líquido (VPL) é uma métrica financeira usada para determinar a viabilidade financeira de um projeto ou investimento. Ele calcula a diferença entre os fluxos de caixa presentes e o custo inicial do projeto.

## Premissas do Projeto

- **Duração do Projeto**: 4 meses.
- **Custo com AWS**: R$ 100 para o projeto inteiro.
- **Receita Estimada**: R$ 5 por cliente para 100 clientes durante os 4 meses do projeto, totalizando R$ 500.
- **Taxa de Desconto**: 8% ao ano (aproximadamente 2,61% ao mês).

## Passos para Calcular o VPL

1. **Calcule os fluxos de caixa presentes**: Para cada mês, calcule o valor presente das receitas esperadas usando a taxa de desconto. Use a fórmula:

    Receita Presente = Receita Mensal / (1 + i)^t
    
    Onde:
    - **Receita Mensal**: Receita esperada por mês (R$ 125).
    - **i**: Taxa de desconto mensal (aproximadamente 2,61%).
    - **t**: Mês correspondente (1 a 4).

2. **Some os fluxos de caixa presentes**: Some os valores presentes das receitas para os 4 meses.

3. **Subtraia o custo inicial**: Subtraia o custo inicial com AWS (R$ 100) do total de fluxos de caixa presentes.

## Exemplo de Cálculo

1. **Calcule os fluxos de caixa presentes**:

    - Mês 1: \(125 / 1.0261 ≅ R$ 121.82\)
    - Mês 2: \(125 / 1.0524 ≅ R$ 118.77\)
    - Mês 3: \(125 / 1.0790 ≅ R$ 115.84\)
    - Mês 4: \(125 / 1.1065 ≅ R$ 138.31\)

2. **Some os fluxos de caixa presentes**:

    \(R$ 121.82 + R$ 118.77 + R$ 115.84 + R$ 138.31 = R$ 494.74\)

3. **Subtraia o custo inicial**:

    \(R$ 494.74 - R$ 100 = R$ 394.74\)

O VPL calculado é de **R$ 394.74**, um resultado positivo que indica que o projeto é financeiramente viável com as premissas estabelecidas.

### Histórico de Versões

| Versão | Data       | Descrição            | Autor(es)              | Revisor(es) |
|--------|------------|----------------------|------------------------|-------------|
| `1.0`  | 07/05/2024 | Criação do Documento | João Paulo Coelho                 | -           |