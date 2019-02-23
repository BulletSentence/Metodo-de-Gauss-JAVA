/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodogauss;

/**
 *
 * @author BS
 */
public class MetodoGauss {
    private static final double ep = 1e-10;

        public static void main(String[] args) {
        int TamanhoMatriz = 3;
        double[][] A = {
            { 2, 1, -3 },   // = R1
            { -1, 3, 2 },   // = R2
            { 3, 1, -3 }   // = R3
        };
        
        double[] igual = { -1, 12, 0 }; 
        // (R1, R2, R3}
        
        double[] x = LinearS(A, igual);
        System.out.printf("Resposta: (");
        for (int i = 0; i < TamanhoMatriz; i++) {
            System.out.print(x[i] + ", ");
        }
        System.out.printf(")");
    }
    
    // Ultilizando o metodo de gauss ele elemina e seleciona um pivô
    public static double[] LinearS(double[][] A, double[] b) {
        int n = b.length;

        for (int p = 0; p < n; p++) {

            // Encontra a coluna Pivô e substitui
            int max = p;
            for (int i = p + 1; i < n; i++) {
                if (Math.abs(A[i][p]) > Math.abs(A[max][p])) {
                    max = i;
                }
            }
            double[] temp = A[p]; A[p] = A[max]; A[max] = temp;
            double   t    = b[p]; b[p] = b[max]; b[max] = t;

            // Vê se a matriz tem inversa
            if (Math.abs(A[p][p]) <= ep) {
                throw new ArithmeticException("Determinante é 0");
            }
           
            for (int i = p + 1; i < n; i++) {
                double alpha = A[i][p] / A[p][p];
                b[i] -= alpha * b[p];
                for (int j = p; j < n; j++) {
                    A[i][j] -= alpha * A[p][j];
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
