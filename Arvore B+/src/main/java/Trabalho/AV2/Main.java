package Trabalho.AV2;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        BPlusTree tree =
                new BPlusTree();

        /*
         * Inserção de 1 até 30.
         */
        for (int i = 1; i <= 30; i++) {

            tree.insert(i);
        }

        System.out.println(
                "================================================="
        );

        System.out.println(
                "            ÁRVORE B+ - ORDEM 4"
        );

        System.out.println(
                "================================================="
        );

        /*
         * Consulta inicial.
         */
        List<Integer> result1 =
                tree.rangeQuery(1, 30);

        System.out.println(
                "\nRangeQuery(1,30):"
        );

        System.out.println(result1);

        /*
         * Impressão das folhas.
         */
        tree.printLeaves();

        /*
         * Remoções obrigatórias.
         */
        int[] removals = {
                5, 10, 15, 20, 25
        };

        System.out.println(
                "\n========== REMOÇÕES =========="
        );

        for (int value : removals) {

            tree.remove(value);
        }

        /*
         * Consulta após remoções.
         */
        List<Integer> result2 =
                tree.rangeQuery(1, 30);

        System.out.println(
                "\nRangeQuery após remoções:"
        );

        System.out.println(result2);

        /*
         * Impressão das folhas novamente.
         */
        tree.printLeaves();

        System.out.println(
                "\n================================================="
        );

        System.out.println(
                "        DEMONSTRAÇÃO FINALIZADA"
        );

        System.out.println(
                "================================================="
        );
    }
}