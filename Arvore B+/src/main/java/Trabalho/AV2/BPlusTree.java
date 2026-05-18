package Trabalho.AV2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BPlusTree {

    /**
     * Ordem da árvore.
     */
    private static final int ORDER = 4;

    /**
     * Máximo de chaves por folha.
     */
    private static final int MAX_KEYS = ORDER - 1;

    /**
     * Primeira folha da lista encadeada.
     */
    private BPlusNode firstLeaf;

    /**
     * Construtor da árvore.
     */
    public BPlusTree() {

        firstLeaf = new BPlusNode(true);
    }

    /**
     * =====================================================
     * INSERÇÃO
     * =====================================================
     */
    public void insert(int key) {

        /*
         * Localiza folha correta.
         */
        BPlusNode leaf = findLeaf(key);

        /*
         * Evita duplicatas.
         */
        if (leaf.keys.contains(key)) {
            return;
        }

        /*
         * Insere valor.
         */
        leaf.keys.add(key);

        /*
         * Mantém ordenado.
         */
        Collections.sort(leaf.keys);

        /*
         * Overflow da folha.
         */
        if (leaf.keys.size() > MAX_KEYS) {

            splitLeaf(leaf);
        }
    }

    /**
     * =====================================================
     * LOCALIZA FOLHA
     * =====================================================
     */
    private BPlusNode findLeaf(int key) {

        BPlusNode current = firstLeaf;

        /*
         * Percorre folhas encadeadas.
         */
        while (current.next != null) {

            /*
             * Se encontrou posição.
             */
            if (key <= current.keys.get(current.keys.size() - 1)) {
                return current;
            }

            current = current.next;
        }

        return current;
    }

    /**
     * =====================================================
     * SPLIT DA FOLHA
     * =====================================================
     */
    private void splitLeaf(BPlusNode leaf) {

        /*
         * Nova folha.
         */
        BPlusNode newLeaf =
                new BPlusNode(true);

        /*
         * Posição do meio.
         */
        int middle =
                leaf.keys.size() / 2;

        /*
         * Move metade das chaves.
         */
        while (leaf.keys.size() > middle) {

            newLeaf.keys.add(
                    leaf.keys.remove(middle)
            );
        }

        /*
         * Ajusta ponteiros next.
         */
        newLeaf.next = leaf.next;

        leaf.next = newLeaf;
    }

    /**
     * =====================================================
     * REMOÇÃO
     * =====================================================
     */
    public void remove(int key) {

        BPlusNode leaf =
                findLeaf(key);

        /*
         * Remove valor.
         */
        boolean removed =
                leaf.keys.remove(
                        Integer.valueOf(key)
                );

        if (removed) {

            System.out.println(
                    "Valor removido: " + key
            );
        }

        else {

            System.out.println(
                    "Valor não encontrado: " + key
            );
        }
    }

    /**
     * =====================================================
     * RANGE QUERY
     * =====================================================
     */
    public List<Integer> rangeQuery(
            int start,
            int end
    ) {

        List<Integer> result =
                new ArrayList<>();

        BPlusNode current = firstLeaf;

        /*
         * Percorre todas as folhas.
         */
        while (current != null) {

            for (Integer value : current.keys) {

                if (
                        value >= start
                                &&
                                value <= end
                ) {

                    result.add(value);
                }
            }

            current = current.next;
        }

        return result;
    }

    /**
     * =====================================================
     * IMPRESSÃO DAS FOLHAS
     * =====================================================
     */
    public void printLeaves() {

        BPlusNode current =
                firstLeaf;

        System.out.println(
                "\n========== FOLHAS ENCADEADAS =========="
        );

        /*
         * Percorre lista ligada.
         */
        while (current != null) {

            System.out.print(current.keys);

            if (current.next != null) {

                System.out.print(" -> ");
            }

            current = current.next;
        }

        System.out.println();
    }
}