public class MatrixProduct {
   public static int[][] matrixProduct_DAC(int[][] A, int[][] B) {
      checkSize(A, B);
      int n = A.length;
      return matrixProduct_DAC(A, 0, 0, B, 0, 0, n);
   }

   private static int[][] matrixProduct_DAC(int[][] A, int startRowA, int startColA, int[][] B, int startRowB, int startColB, int n) {
      int[][] C = new int[n][n];
      int[][] c11, c12, c21, c22;
      if (n == 1) {
         C[0][0] = A[startRowA][startColA] * B[startRowB][startColB];
      }
      else {
         c11 = matrixAdd(matrixProduct_DAC(A, 0, 0, B, 0, 0, n / 2), matrixProduct_DAC(A, 0, n / 2, B, n / 2, 0, n / 2), n / 2);
         c12 = matrixAdd(matrixProduct_DAC(A, 0, 0, B, 0, n / 2, n / 2), matrixProduct_DAC(A, 0, n / 2, B, n / 2, n / 2, n / 2), n / 2);
         c21 = matrixAdd(matrixProduct_DAC(A, n / 2, 0, B, 0, 0, n / 2), matrixProduct_DAC(A, n / 2, n / 2, B, n / 2, 0, n / 2), n / 2);
         c22 = matrixAdd(matrixProduct_DAC(A, n / 2, 0, B, 0, n / 2, n / 2), matrixProduct_DAC(A, n / 2, n / 2, B, n / 2, n / 2, n / 2), n / 2);
         subToResultMatrix(C, c11, 0, n / 2, 0, n / 2); //(C[0][0] = c11;
         subToResultMatrix(C, c12, n / 2, n, 0, n / 2); // C[0][1] = c12;
         subToResultMatrix(C, c21, 0, n / 2, n / 2, n); //C[1][0] = c21;
         subToResultMatrix(C, c22, n / 2, n, n / 2, n); //C[1][1] = c22;
      }
      return C;
   }
   
   private static void subToResultMatrix(int[][] C, int[][] sub, int startRow, int endRow, int startCol, int endCol) {
      for (int i = startRow; i < endRow; i++) {
         for (int j = startCol; j < endCol; j++) {
            C[i][j] = sub[i - startRow][j - startCol];
         }
      }
   }


   private static int[][] matrixAdd(int[][] A, int[][] B, int n) {
      int[][] C = new int[n][n];
      for (int i = 0; i < n; i++) {
         for (int j = 0; j < n; j++) {
            C[i][j] = A[i][j] + B[i][j];
         }
      }
      return C;
   }

   public static int[][] matrixProduct_Strassen(int[][] A, int[][] B) {
      return null;
   }

   private static void checkSize(int[][] A, int[][] B) {
      if (A.length != A[0].length) {
         throw new IllegalArgumentException();
      }
      else if (B.length != B[0].length) {
         throw new IllegalArgumentException();
      }
      else if (A.length != B.length) {
         throw new IllegalArgumentException();
      }
      else if ((A.length & (A.length - 1)) != 0) {
         throw new IllegalArgumentException();
      }
   }
}
