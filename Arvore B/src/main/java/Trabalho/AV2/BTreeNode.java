package Trabalho.AV2;

import java.util.ArrayList;
import java.util.List;

public class BTreeNode {

    /**
     * Grau mínimo da árvore.
     */
    int t;

    /**
     * Lista de chaves do nó.
     */
    List<Integer> keys;

    /**
     * Lista de filhos.
     */
    List<BTreeNode> children;

    /**
     * Indica se o nó é folha.
     */
    boolean leaf;

    /**
     * Construtor do nó.
     */
    public BTreeNode(int t, boolean leaf) {

        this.t = t;

        this.leaf = leaf;

        keys = new ArrayList<>();

        children = new ArrayList<>();
    }

    /**
     * Impressão hierárquica da árvore.
     */
    public void print(String indent) {

        System.out.println(indent + keys);

        /*
         * Impressão recursiva dos filhos.
         */
        if (!leaf) {

            for (BTreeNode child : children) {

                child.print(indent + "   ");
            }
        }
    }
}