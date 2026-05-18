package Trabalho.AV2;

import java.util.ArrayList;
import java.util.List;

public class BPlusNode {

    /**
     * Indica se o nó é folha.
     */
    boolean leaf;

    /**
     * Lista de chaves do nó.
     */
    List<Integer> keys;

    /**
     * Lista de filhos.
     */
    List<BPlusNode> children;

    /**
     * Ponteiro para próxima folha.
     */
    BPlusNode next;

    /**
     * Ponteiro para o nó pai.
     *
     * IMPORTANTE:
     * Facilita split e reorganização da árvore.
     */
    BPlusNode parent;

    /**
     * Construtor do nó.
     */
    public BPlusNode(boolean leaf) {

        /*
         * Define tipo do nó.
         */
        this.leaf = leaf;

        /*
         * Inicializa lista de chaves.
         */
        keys = new ArrayList<>();

        /*
         * Inicializa lista de filhos.
         */
        children = new ArrayList<>();

        /*
         * Inicializa ponteiro next.
         */
        next = null;

        /*
         * Inicializa ponteiro parent.
         */
        parent = null;
    }

    /**
     * Verifica se o nó possui overflow.
     */
    public boolean isOverflow(int maxKeys) {

        return keys.size() > maxKeys;
    }

    /**
     * Verifica se o nó está vazio.
     */
    public boolean isEmpty() {

        return keys.isEmpty();
    }

    /**
     * Retorna representação textual do nó.
     */
    @Override
    public String toString() {

        return keys.toString();
    }
}