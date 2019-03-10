/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodogauss;

import java.text.DecimalFormat;

/**
 *
 * @author BS
 */
public class MetodoGauss {

        private static int p;

        public static void main(String[] args) {
            int TamanhoMatriz = 3;
            double[][] A = {
                    {2, 5, 3 },   // = R1
                    {5, 3, -10},   // = R2
                    {1, 1, 1 }   // = R3
            };

            // Valores apos a igualdade
            double[] igual = {20, -39, 5};
            // (R1, R2, R3}


            if (Math.abs(A[p][p]) >= 1e-5) { // Se o valor não for muito baixo (1^-10)
                double d;
                int cont = (TamanhoMatriz) - 1;

                double[] x = LinearS(A, igual);
                System.out.printf("Resposta: (");

                for (int i = 0; i < TamanhoMatriz; i++) {

                    //System.out.print(x[i] + ", "); //Resultado Absoluto

                    // D recebe o valor double
                    d = x[i];

                    DecimalFormat df = new DecimalFormat("#.##");
                    System.out.printf(df.format(d));

                    //Codigo para separar por virgula os valores
                    if (cont > 0){
                        System.out.printf(", ");
                        cont --;
                    }
                }
                System.out.printf(")");
            } else {
                System.out.println("Determinante é nulo");
            }
        }

    // Ultilizando o metodo de gauss ele elemina e seleciona um pivô
    public static double[] LinearS(double[][] A, double[] b) {
        int n = b.length;

        for (p = 0; p < n; p++) {

            // Encontra a coluna Pivô e substitui
            int max = p;

            for (int i = p + 1; i < n; i++) {
                if (Math.abs(A[i][p]) > Math.abs(A[max][p])) {
                    max = i;
                }
            }
            double[] temp = A[p]; A[p] = A[max]; A[max] = temp;
            double   t    = b[p]; b[p] = b[max]; b[max] = t;

            for (int i = p + 1; i < n; i++) {
                double matrizA = A[i][p] / A[p][p];
                b[i] -= matrizA * b[p];
                for (int j = p; j < n; j++) {
                    A[i][j] -= matrizA * A[p][j];
                }
            }
        }

        // Substituição de volta
        double[] x = new double[n];
        for (int i = n - 1; i >= 0; i--) {

            double sum = 0.0;
            for (int j = i + 1; j < n; j++) {
                sum += A[i][j] * x[j];
            }
            x[i] = (b[i] - sum) / A[i][i];
        }
        return x;
    }

}
