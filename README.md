# 🌳 Estruturas de Árvores em Java

Projeto desenvolvido para a disciplina de **Estrutura de Dados I**, com foco na implementação prática de diferentes estruturas de árvores balanceadas utilizando Java.

O trabalho contempla a construção, manipulação e demonstração visual das seguintes estruturas:

* Árvore Rubro-Negra
* Árvore B
* Árvore B+

---

# 📚 Informações Acadêmicas

| Informação      | Detalhes                                       |
| --------------- | ---------------------------------------------- |
| **Disciplina**  | Estrutura de Dados I                           |
| **Professor**   | Wesley Soares de Souza                         |
| **Instituição** | Centro Universitário de Fernandópolis — UNIFEF |
| **Curso**       | Sistemas de Informação                         |
| **Semestre**    | 5º Semestre                                    |

---

# 👨‍💻 Integrantes

* Rian Antônio Magalhães de Araújo
* Gabriel da Silva Pereira

---

# 🎯 Objetivo do Projeto

O principal objetivo deste trabalho é aplicar os conceitos teóricos de árvores balanceadas por meio de implementações completas em Java, explorando operações fundamentais como:

* Inserção
* Remoção
* Balanceamento
* Busca eficiente
* Encadeamento de folhas
* Busca por intervalo

Além da implementação das estruturas, o projeto também apresenta demonstrações visuais desenvolvidas no Miro para facilitar a compreensão do funcionamento interno de cada árvore.

---

# 🌲 Estruturas Implementadas

## 🔴 Árvore Rubro-Negra

Implementação de uma árvore binária balanceada baseada em regras de coloração para garantir eficiência nas operações.

### Funcionalidades implementadas

* Inserção balanceada
* Remoção completa
* Rotação à esquerda
* Rotação à direita
* Correção de balanceamento (`DeleteFixup`)
* Impressão estruturada da árvore

### Conceitos aplicados

* Balanceamento automático
* Nós vermelhos e pretos
* Reorganização após remoções
* Complexidade O(log n)

---

## 🌲 Árvore B

Implementação de uma Árvore B de ordem 4, amplamente utilizada em sistemas de arquivos e bancos de dados.

### Funcionalidades implementadas

* Inserção em nós não cheios
* Split de nós
* Remoção em folha
* Remoção em nó interno
* Redistribuição de chaves
* Merge entre nós
* Ajuste automático da raiz

### Conceitos aplicados

* Múltiplas chaves por nó
* Redução da altura da árvore
* Balanceamento automático
* Redistribuição e fusão de nós

---

## 🌿 Árvore B+

Implementação simplificada de uma Árvore B+ com encadeamento entre folhas.

### Funcionalidades implementadas

* Inserção ordenada
* Divisão de folhas
* Ponteiros entre folhas (`next`)
* Consulta por intervalo (`Range Query`)
* Remoção de valores
* Impressão das folhas encadeadas

### Conceitos aplicados

* Encadeamento entre folhas
* Busca sequencial eficiente
* Range Query
* Estruturas utilizadas em bancos de dados

---

# 🛠 Tecnologias Utilizadas

* Java 17
* IntelliJ IDEA
* GitHub
* Miro

---

# 📂 Organização dos Arquivos

## 🔴 Árvore Rubro-Negra

```text
RedBlackTree.java
```

---

## 🌲 Árvore B

```text
BTree.java
BTreeNode.java
Main.java
```

---

## 🌿 Árvore B+

```text
BPlusTree.java
BPlusNode.java
Main.java
```

---

# ▶ Como Executar

## 1. Clonar o repositório

```bash
git clone LINK_DO_REPOSITORIO
```

---

## 2. Abrir o projeto

* Abrir o IntelliJ IDEA
* Selecionar a opção **Open**
* Escolher a pasta do projeto

---

## 3. Executar os arquivos

Executar:

```text
Main.java
```

ou:

```text
RedBlackTree.java
```

dependendo da estrutura desejada.

---

# 🧠 Resumo Teórico

## 🔴 Árvore Rubro-Negra

Estrutura binária balanceada que utiliza cores para manter a árvore organizada após inserções e remoções.

### Características

* Balanceamento automático
* Alta eficiência
* Complexidade O(log n)
* Muito utilizada em bibliotecas e coleções

---

## 🌲 Árvore B

Estrutura otimizada para armazenamento e acesso em disco.

### Características

* Nós com múltiplas chaves
* Menor altura
* Balanceamento automático
* Excelente desempenho em grandes volumes de dados

---

## 🌿 Árvore B+

Estrutura derivada da Árvore B com foco em consultas sequenciais eficientes.

### Características

* Dados armazenados nas folhas
* Folhas conectadas por ponteiros
* Alta eficiência para buscas por intervalo
* Muito utilizada em bancos de dados

---

# 🎨 Representação Visual

As demonstrações visuais das estruturas foram desenvolvidas utilizando o Miro, permitindo representar:

* Inserções
* Remoções
* Balanceamentos
* Splits
* Merge
* Encadeamento entre folhas
* Range Query

---

# 🔗 Link do Miro

Acesse o quadro visual do projeto:

[Miro - Estruturas de Árvores](https://miro.com/welcomeonboard/R0dScXAzeXlPaTNyVFVvK3Rramd6VjRqbDlxRHpRcjdoU2pSWUc4VnJ3a1FRblVqbldUaGRsbEFMN1dyendXRjVvVGFqTERpZW5pUHY0Z1p4eFdzNngrMndRbTROUkNYVFJBVlZxTVN0V0Z4ZFYyMktIeFVXR3hMUWI2THN2VDd3VHhHVHd5UWtSM1BidUtUYmxycDRnPT0hdjE=?share_link_id=40143585967&utm_source=chatgpt.com)

---

# 📌 Conclusão

O desenvolvimento deste trabalho permitiu aprofundar os conhecimentos sobre estruturas de árvores balanceadas e compreender, na prática, como funcionam operações fundamentais como inserção, remoção e reorganização de nós.

Além da implementação em Java, o projeto também contribuiu para o desenvolvimento da modelagem visual das estruturas de dados e para a análise do comportamento das árvores em diferentes cenários.
