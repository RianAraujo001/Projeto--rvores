package Trabalho.AV2;

public class Main {

    public static void main(String[] args) {

        /*
         * Criação da Árvore B de ordem 4.
         *
         * IMPORTANTE:
         * Para ordem 4 utilizamos t = 2.
         */
        BTree tree = new BTree(2);

        /*
         * Valores obrigatórios do enunciado.
         */
        int[] values = {
                1, 3, 7, 10, 11,
                13, 14, 15, 18,
                16, 19, 24, 25,
                26, 21, 4, 5,
                20, 22, 2, 17,
                12, 6
        };

        /*
         * Inserção dos valores.
         */
        for (int value : values) {

            tree.insert(value);
        }

        System.out.println(
                "================================================="
        );

        System.out.println(
                "           ÁRVORE B - ORDEM 4"
        );

        System.out.println(
                "================================================="
        );

        System.out.println(
                "\n========== ÁRVORE INICIAL ==========\n"
        );

        tree.printTree();

        /*
         * Remoções obrigatórias.
         */
        int[] removals = {
                6, 13, 7, 4, 2, 16
        };

        /*
         * Executa remoções.
         */
        for (int value : removals) {

            System.out.println(
                    "\n========================================"
            );

            System.out.println(
                    "REMOVENDO VALOR: " + value
            );

            System.out.println(
                    "========================================"
            );

            tree.remove(value);

            System.out.println(
                    "\nÁrvore após remoção:\n"
            );

            tree.printTree();
        }

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