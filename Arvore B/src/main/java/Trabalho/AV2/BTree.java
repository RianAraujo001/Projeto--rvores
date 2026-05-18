package Trabalho.AV2;

public class BTree {

    /**
     * Grau mínimo da árvore.
     *
     * Para ordem 4:
     * t = 2
     */
    private final int t;

    /**
     * Raiz da árvore.
     */
    private BTreeNode root;

    /**
     * Construtor da árvore.
     */
    public BTree(int t) {

        this.t = t;

        root = new BTreeNode(t, true);
    }

    /**
     * Inserção pública.
     */
    public void insert(int key) {

        BTreeNode currentRoot = root;

        /*
         * Caso raiz cheia.
         */
        if (currentRoot.keys.size() == 2 * t - 1) {

            BTreeNode newRoot =
                    new BTreeNode(t, false);

            root = newRoot;

            newRoot.children.add(currentRoot);

            splitChild(newRoot, 0);

            insertNonFull(newRoot, key);
        }

        else {

            insertNonFull(currentRoot, key);
        }
    }

    /**
     * Inserção em nó não cheio.
     */
    private void insertNonFull(
            BTreeNode node,
            int key
    ) {

        int index = node.keys.size() - 1;

        /*
         * Inserção em folha.
         */
        if (node.leaf) {

            node.keys.add(0);

            while (
                    index >= 0
                            &&
                            key < node.keys.get(index)
            ) {

                node.keys.set(
                        index + 1,
                        node.keys.get(index)
                );

                index--;
            }

            node.keys.set(index + 1, key);
        }

        /*
         * Inserção em nó interno.
         */
        else {

            while (
                    index >= 0
                            &&
                            key < node.keys.get(index)
            ) {

                index--;
            }

            index++;

            /*
             * Divide filho cheio.
             */
            if (
                    node.children.get(index)
                            .keys.size()
                            == 2 * t - 1
            ) {

                splitChild(node, index);

                if (key > node.keys.get(index)) {
                    index++;
                }
            }

            insertNonFull(
                    node.children.get(index),
                    key
            );
        }
    }

    /**
     * Divide um filho cheio.
     */
    private void splitChild(
            BTreeNode parent,
            int index
    ) {

        BTreeNode fullNode =
                parent.children.get(index);

        BTreeNode newNode =
                new BTreeNode(
                        t,
                        fullNode.leaf
                );

        /*
         * Move metade das chaves.
         */
        for (int i = 0; i < t - 1; i++) {

            newNode.keys.add(
                    fullNode.keys.remove(t)
            );
        }

        /*
         * Move filhos.
         */
        if (!fullNode.leaf) {

            for (int i = 0; i < t; i++) {

                newNode.children.add(
                        fullNode.children.remove(t)
                );
            }
        }

        /*
         * Insere novo filho.
         */
        parent.children.add(
                index + 1,
                newNode
        );

        /*
         * Sobe chave central.
         */
        parent.keys.add(
                index,
                fullNode.keys.remove(t - 1)
        );
    }

    /**
     * Remoção pública.
     */
    public void remove(int key) {

        remove(root, key);

        /*
         * Ajuste da raiz.
         */
        if (
                root.keys.isEmpty()
                        &&
                        !root.leaf
        ) {

            root = root.children.get(0);
        }
    }

    /**
     * Remoção recursiva.
     */
    private void remove(
            BTreeNode node,
            int key
    ) {

        int index = findKey(node, key);

        /*
         * Chave encontrada.
         */
        if (
                index < node.keys.size()
                        &&
                        node.keys.get(index) == key
        ) {

            /*
             * Caso folha.
             */
            if (node.leaf) {

                node.keys.remove(index);

                /*
                 * Verifica underflow.
                 */
                if (
                        node != root
                                &&
                                node.keys.size() < t - 1
                ) {

                    System.out.println(
                            "Remoção "
                                    + key
                                    + ": folha com underflow"
                    );
                }

                else {

                    System.out.println(
                            "Remoção "
                                    + key
                                    + ": folha sem underflow"
                    );
                }
            }

            /*
             * Caso nó interno.
             */
            else {

                System.out.println(
                        "Remoção "
                                + key
                                + ": nó interno"
                );

                removeInternalNode(
                        node,
                        key,
                        index
                );
            }
        }

        /*
         * Chave não encontrada no nó.
         */
        else {

            /*
             * Caso folha.
             */
            if (node.leaf) {

                System.out.println(
                        "Valor não encontrado."
                );

                return;
            }

            boolean lastChild =
                    index == node.keys.size();

            /*
             * Garante mínimo de chaves.
             */
            if (
                    node.children.get(index)
                            .keys.size() < t
            ) {

                fill(node, index);
            }

            /*
             * Continua remoção.
             */
            if (
                    lastChild
                            &&
                            index > node.keys.size()
            ) {

                remove(
                        node.children.get(index - 1),
                        key
                );
            }

            else {

                remove(
                        node.children.get(index),
                        key
                );
            }
        }
    }

    /**
     * Remove chave de nó interno.
     */
    private void removeInternalNode(
            BTreeNode node,
            int key,
            int index
    ) {

        BTreeNode leftChild =
                node.children.get(index);

        BTreeNode rightChild =
                node.children.get(index + 1);

        /*
         * Usa predecessor.
         */
        if (leftChild.keys.size() >= t) {

            int predecessor =
                    getPredecessor(leftChild);

            System.out.println(
                    "Substituto escolhido: "
                            + predecessor
            );

            node.keys.set(index, predecessor);

            remove(leftChild, predecessor);
        }

        /*
         * Usa sucessor.
         */
        else if (rightChild.keys.size() >= t) {

            int successor =
                    getSuccessor(rightChild);

            System.out.println(
                    "Substituto escolhido: "
                            + successor
            );

            node.keys.set(index, successor);

            remove(rightChild, successor);
        }

        /*
         * Merge obrigatório.
         */
        else {

            System.out.println(
                    "Merge necessário para remoção"
            );

            merge(node, index);

            remove(leftChild, key);
        }
    }

    /**
     * Retorna predecessor.
     */
    private int getPredecessor(
            BTreeNode node
    ) {

        while (!node.leaf) {

            node =
                    node.children.get(
                            node.children.size() - 1
                    );
        }

        return node.keys.get(
                node.keys.size() - 1
        );
    }

    /**
     * Retorna sucessor.
     */
    private int getSuccessor(
            BTreeNode node
    ) {

        while (!node.leaf) {

            node = node.children.get(0);
        }

        return node.keys.get(0);
    }

    /**
     * Garante número mínimo de chaves.
     */
    private void fill(
            BTreeNode node,
            int index
    ) {

        /*
         * Redistribuição pela esquerda.
         */
        if (
                index != 0
                        &&
                        node.children.get(index - 1)
                                .keys.size() >= t
        ) {

            borrowFromPrevious(node, index);
        }

        /*
         * Redistribuição pela direita.
         */
        else if (
                index != node.keys.size()
                        &&
                        node.children.get(index + 1)
                                .keys.size() >= t
        ) {

            borrowFromNext(node, index);
        }

        /*
         * Merge.
         */
        else {

            if (index != node.keys.size()) {

                merge(node, index);
            }

            else {

                merge(node, index - 1);
            }
        }
    }

    /**
     * Redistribuição à esquerda.
     */
    private void borrowFromPrevious(
            BTreeNode node,
            int index
    ) {

        System.out.println(
                "Redistribuição à esquerda"
        );

        BTreeNode child =
                node.children.get(index);

        BTreeNode sibling =
                node.children.get(index - 1);

        /*
         * Move chave do pai.
         */
        child.keys.add(
                0,
                node.keys.get(index - 1)
        );

        /*
         * Move chave do irmão.
         */
        node.keys.set(
                index - 1,
                sibling.keys.remove(
                        sibling.keys.size() - 1
                )
        );

        /*
         * Move filho.
         */
        if (!sibling.leaf) {

            child.children.add(
                    0,
                    sibling.children.remove(
                            sibling.children.size() - 1
                    )
            );
        }
    }

    /**
     * Redistribuição à direita.
     */
    private void borrowFromNext(
            BTreeNode node,
            int index
    ) {

        System.out.println(
                "Redistribuição à direita"
        );

        BTreeNode child =
                node.children.get(index);

        BTreeNode sibling =
                node.children.get(index + 1);

        /*
         * Move chave do pai.
         */
        child.keys.add(
                node.keys.get(index)
        );

        /*
         * Move chave do irmão.
         */
        node.keys.set(
                index,
                sibling.keys.remove(0)
        );

        /*
         * Move filho.
         */
        if (!sibling.leaf) {

            child.children.add(
                    sibling.children.remove(0)
            );
        }
    }

    /**
     * Realiza merge entre nós.
     */
    private void merge(
            BTreeNode node,
            int index
    ) {

        System.out.println(
                "Merge realizado"
        );

        BTreeNode child =
                node.children.get(index);

        BTreeNode sibling =
                node.children.get(index + 1);

        /*
         * Move chave do pai.
         */
        child.keys.add(
                node.keys.remove(index)
        );

        /*
         * Move chaves do irmão.
         */
        child.keys.addAll(sibling.keys);

        /*
         * Move filhos do irmão.
         */
        if (!child.leaf) {

            child.children.addAll(
                    sibling.children
            );
        }

        /*
         * Remove irmão.
         */
        node.children.remove(index + 1);
    }

    /**
     * Localiza posição da chave.
     */
    private int findKey(
            BTreeNode node,
            int key
    ) {

        int index = 0;

        while (
                index < node.keys.size()
                        &&
                        node.keys.get(index) < key
        ) {

            index++;
        }

        return index;
    }

    /**
     * Impressão da árvore.
     */
    public void printTree() {

        root.print("");
    }
}