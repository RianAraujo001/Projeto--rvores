package Trabalho.AV2;

public class RedBlackTree {

    /**
     * Representação das cores.
     */
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    /**
     * Classe interna responsável por representar
     * os nós da Árvore Rubro-Negra.
     */
    class Node {

        int key;

        boolean color;

        Node left;
        Node right;
        Node parent;

        /**
         * Construtor do nó.
         */
        Node(int key) {

            this.key = key;

            this.color = RED;
        }
    }

    /**
     * Nó sentinela NIL.
     */
    private final Node NIL = new Node(0);

    /**
     * Raiz da árvore.
     */
    private Node root;

    /**
     * Construtor da árvore.
     */
    public RedBlackTree() {

        NIL.color = BLACK;

        NIL.left = NIL;
        NIL.right = NIL;
        NIL.parent = NIL;

        root = NIL;
    }

    /**
     * Realiza inserção de um novo valor.
     */
    public void insert(int key) {

        Node node = new Node(key);

        node.parent = NIL;
        node.left = NIL;
        node.right = NIL;

        Node parent = NIL;

        Node current = root;

        /*
         * Busca posição correta.
         */
        while (current != NIL) {

            parent = current;

            if (node.key < current.key) {
                current = current.left;
            }

            else {
                current = current.right;
            }
        }

        node.parent = parent;

        /*
         * Caso árvore vazia.
         */
        if (parent == NIL) {
            root = node;
        }

        /*
         * Inserção à esquerda.
         */
        else if (node.key < parent.key) {
            parent.left = node;
        }

        /*
         * Inserção à direita.
         */
        else {
            parent.right = node;
        }

        /*
         * Correção das propriedades.
         */
        fixInsert(node);
    }

    /**
     * Corrige violações após inserção.
     */
    private void fixInsert(Node node) {

        while (node.parent.color == RED) {

            /*
             * Pai está à direita.
             */
            if (node.parent == node.parent.parent.right) {

                Node uncle = node.parent.parent.left;

                /*
                 * CASO 1:
                 * Tio vermelho.
                 */
                if (uncle.color == RED) {

                    uncle.color = BLACK;

                    node.parent.color = BLACK;

                    node.parent.parent.color = RED;

                    node = node.parent.parent;
                }

                else {

                    /*
                     * CASO 2:
                     * Rotação à direita.
                     */
                    if (node == node.parent.left) {

                        node = node.parent;

                        rightRotate(node);
                    }

                    /*
                     * CASO 3:
                     * Rotação à esquerda.
                     */
                    node.parent.color = BLACK;

                    node.parent.parent.color = RED;

                    leftRotate(node.parent.parent);
                }
            }

            /*
             * Casos espelhados.
             */
            else {

                Node uncle = node.parent.parent.right;

                /*
                 * CASO 1 ESPELHADO.
                 */
                if (uncle.color == RED) {

                    uncle.color = BLACK;

                    node.parent.color = BLACK;

                    node.parent.parent.color = RED;

                    node = node.parent.parent;
                }

                else {

                    /*
                     * CASO 2 ESPELHADO.
                     */
                    if (node == node.parent.right) {

                        node = node.parent;

                        leftRotate(node);
                    }

                    /*
                     * CASO 3 ESPELHADO.
                     */
                    node.parent.color = BLACK;

                    node.parent.parent.color = RED;

                    rightRotate(node.parent.parent);
                }
            }

            if (node == root) {
                break;
            }
        }

        /*
         * Raiz sempre preta.
         */
        root.color = BLACK;
    }

    /**
     * Rotação à esquerda.
     */
    private void leftRotate(Node node) {

        Node rightChild = node.right;

        node.right = rightChild.left;

        if (rightChild.left != NIL) {
            rightChild.left.parent = node;
        }

        rightChild.parent = node.parent;

        if (node.parent == NIL) {
            root = rightChild;
        }

        else if (node == node.parent.left) {
            node.parent.left = rightChild;
        }

        else {
            node.parent.right = rightChild;
        }

        rightChild.left = node;

        node.parent = rightChild;
    }

    /**
     * Rotação à direita.
     */
    private void rightRotate(Node node) {

        Node leftChild = node.left;

        node.left = leftChild.right;

        if (leftChild.right != NIL) {
            leftChild.right.parent = node;
        }

        leftChild.parent = node.parent;

        if (node.parent == NIL) {
            root = leftChild;
        }

        else if (node == node.parent.right) {
            node.parent.right = leftChild;
        }

        else {
            node.parent.left = leftChild;
        }

        leftChild.right = node;

        node.parent = leftChild;
    }

    /**
     * Busca um valor na árvore.
     */
    private Node search(Node node, int key) {

        while (node != NIL && key != node.key) {

            if (key < node.key) {
                node = node.left;
            }

            else {
                node = node.right;
            }
        }

        return node;
    }

    /**
     * Retorna o menor valor da subárvore.
     */
    private Node minimum(Node node) {

        while (node.left != NIL) {
            node = node.left;
        }

        return node;
    }

    /**
     * Substitui uma subárvore por outra.
     */
    private void transplant(Node source, Node target) {

        if (source.parent == NIL) {
            root = target;
        }

        else if (source == source.parent.left) {
            source.parent.left = target;
        }

        else {
            source.parent.right = target;
        }

        target.parent = source.parent;
    }

    /**
     * Remove um valor da árvore.
     */
    public void delete(int key) {

        Node removedNode = search(root, key);

        /*
         * Valor não encontrado.
         */
        if (removedNode == NIL) {

            System.out.println(
                    "Valor não encontrado."
            );

            return;
        }

        Node auxiliary = removedNode;

        boolean originalColor = auxiliary.color;

        Node fixNode;

        /*
         * CASO 1:
         * Apenas filho direito.
         */
        if (removedNode.left == NIL) {

            fixNode = removedNode.right;

            transplant(
                    removedNode,
                    removedNode.right
            );
        }

        /*
         * CASO 2:
         * Apenas filho esquerdo.
         */
        else if (removedNode.right == NIL) {

            fixNode = removedNode.left;

            transplant(
                    removedNode,
                    removedNode.left
            );
        }

        /*
         * CASO 3:
         * Dois filhos.
         */
        else {

            auxiliary = minimum(
                    removedNode.right
            );

            originalColor = auxiliary.color;

            fixNode = auxiliary.right;

            if (auxiliary.parent == removedNode) {

                fixNode.parent = auxiliary;
            }

            else {

                transplant(
                        auxiliary,
                        auxiliary.right
                );

                auxiliary.right =
                        removedNode.right;

                auxiliary.right.parent =
                        auxiliary;
            }

            transplant(
                    removedNode,
                    auxiliary
            );

            auxiliary.left =
                    removedNode.left;

            auxiliary.left.parent =
                    auxiliary;

            auxiliary.color =
                    removedNode.color;
        }

        /*
         * Necessário corrigir.
         */
        if (originalColor == BLACK) {
            deleteFixup(fixNode);
        }
    }

    /**
     * Corrige violações após remoção.
     */
    private void deleteFixup(Node node) {

        while (
                node != root
                        &&
                        node.color == BLACK
        ) {

            /*
             * Nó está à esquerda.
             */
            if (node == node.parent.left) {

                Node brother =
                        node.parent.right;

                /*
                 * CASO 1:
                 * Irmão vermelho.
                 */
                if (brother.color == RED) {

                    System.out.println(
                            "DeleteFixup -> Caso 1"
                    );

                    brother.color = BLACK;

                    node.parent.color = RED;

                    leftRotate(node.parent);

                    brother = node.parent.right;
                }

                /*
                 * CASO 2:
                 * Irmão preto com filhos pretos.
                 */
                if (
                        brother.left.color == BLACK
                                &&
                                brother.right.color == BLACK
                ) {

                    System.out.println(
                            "DeleteFixup -> Caso 2"
                    );

                    brother.color = RED;

                    node = node.parent;
                }

                else {

                    /*
                     * CASO 3:
                     * Filho direito preto.
                     */
                    if (
                            brother.right.color
                                    == BLACK
                    ) {

                        System.out.println(
                                "DeleteFixup -> Caso 3"
                        );

                        brother.left.color =
                                BLACK;

                        brother.color = RED;

                        rightRotate(brother);

                        brother =
                                node.parent.right;
                    }

                    /*
                     * CASO 4:
                     * Filho direito vermelho.
                     */
                    System.out.println(
                            "DeleteFixup -> Caso 4"
                    );

                    brother.color =
                            node.parent.color;

                    node.parent.color =
                            BLACK;

                    brother.right.color =
                            BLACK;

                    leftRotate(node.parent);

                    node = root;
                }
            }

            /*
             * Casos espelhados.
             */
            else {

                Node brother =
                        node.parent.left;

                /*
                 * CASO 1 ESPELHADO.
                 */
                if (brother.color == RED) {

                    System.out.println(
                            "DeleteFixup -> Caso 1 Espelhado"
                    );

                    brother.color = BLACK;

                    node.parent.color = RED;

                    rightRotate(node.parent);

                    brother = node.parent.left;
                }

                /*
                 * CASO 2 ESPELHADO.
                 */
                if (
                        brother.right.color == BLACK
                                &&
                                brother.left.color == BLACK
                ) {

                    System.out.println(
                            "DeleteFixup -> Caso 2 Espelhado"
                    );

                    brother.color = RED;

                    node = node.parent;
                }

                else {

                    /*
                     * CASO 3 ESPELHADO.
                     */
                    if (
                            brother.left.color
                                    == BLACK
                    ) {

                        System.out.println(
                                "DeleteFixup -> Caso 3 Espelhado"
                        );

                        brother.right.color =
                                BLACK;

                        brother.color = RED;

                        leftRotate(brother);

                        brother =
                                node.parent.left;
                    }

                    /*
                     * CASO 4 ESPELHADO.
                     */
                    System.out.println(
                            "DeleteFixup -> Caso 4 Espelhado"
                    );

                    brother.color =
                            node.parent.color;

                    node.parent.color =
                            BLACK;

                    brother.left.color =
                            BLACK;

                    rightRotate(node.parent);

                    node = root;
                }
            }
        }

        /*
         * Garante nó preto.
         */
        node.color = BLACK;
    }

    /**
     * Impressão hierárquica da árvore.
     */
    public void printTree() {

        printTree(root, "", true);
    }

    /**
     * Método auxiliar de impressão.
     */
    private void printTree(
            Node node,
            String indent,
            boolean last
    ) {

        if (node != NIL) {

            System.out.print(indent);

            if (last) {

                System.out.print("R----");

                indent += "     ";
            }

            else {

                System.out.print("L----");

                indent += "|    ";
            }

            String color =
                    node.color == RED
                            ? "RED"
                            : "BLACK";

            System.out.println(
                    node.key + "(" + color + ")"
            );

            printTree(
                    node.left,
                    indent,
                    false
            );

            printTree(
                    node.right,
                    indent,
                    true
            );
        }
    }

    /**
     * Método principal para demonstração.
     */
    public static void main(String[] args) {

        RedBlackTree tree =
                new RedBlackTree();

        int[] values = {
                7, 3, 18, 10,
                22, 8, 11, 26
        };

        /*
         * Inserção dos valores.
         */
        for (int value : values) {

            tree.insert(value);
        }

        System.out.println(
                "========== ÁRVORE INICIAL =========="
        );

        tree.printTree();

        /*
         * Remoções obrigatórias.
         */
        System.out.println(
                "\n========== REMOVENDO 18 =========="
        );

        tree.delete(18);

        tree.printTree();

        System.out.println(
                "\n========== REMOVENDO 11 =========="
        );

        tree.delete(11);

        tree.printTree();

        System.out.println(
                "\n========== REMOVENDO 3 =========="
        );

        tree.delete(3);

        tree.printTree();
    }
}