import java.util.Scanner;

public class MatrixMultiplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input dimensions for Matrix A
        System.out.print("Enter number of rows in Matrix A: ");
        int rowsA = scanner.nextInt();
        System.out.print("Enter number of columns in Matrix A: ");
        int colsA = scanner.nextInt();

        // Input dimensions for Matrix B
        System.out.print("Enter number of rows in Matrix B: ");
        int rowsB = scanner.nextInt();
        System.out.print("Enter number of columns in Matrix B: ");
        int colsB = scanner.nextInt();

        // Check if multiplication is possible
        if (colsA != rowsB) {
            System.out.println("Matrix multiplication is not possible. Columns of A must match rows of B.");
            return;
        }

        // Input Matrix A
        int[][] A = new int[rowsA][colsA];
        System.out.println("Enter elements of Matrix A:");
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsA; j++) {
                A[i][j] = scanner.nextInt();
            }
        }

        // Input Matrix B
        int[][] B = new int[rowsB][colsB];
        System.out.println("Enter elements of Matrix B:");
        for (int i = 0; i < rowsB; i++) {
            for (int j = 0; j < colsB; j++) {
                B[i][j] = scanner.nextInt();
            }
        }

        // Resultant Matrix
        int[][] C = new int[rowsA][colsB];

        // Matrix Multiplication Logic
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                C[i][j] = 0;
                for (int k = 0; k < colsA; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        // Print Resultant Matrix
        System.out.println("Resultant Matrix (A × B):");
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                System.out.print(C[i][j] + " ");
            }
            System.out.println();
        }

        scanner.close();
    }
}
