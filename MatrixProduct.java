public class MatrixProduct {
   public static int[][] matrixProduct_DAC(int[][] A, int[][] B) {
      checkSize(A, B);
      int n = A.length;
      return matrixProduct_DAC(A, 0, 0, B, 0, 0, n);
   }

   private static int[][] matrixProduct_DAC(int[][] A, int startRowA, int startColA, int[][] B, int startRowB, int startColB, int n) {
      int[][] C = new int[n][n];
      int[][] c11, c12, c21, c22;
      int newSize = n / 2;
      if (n == 1) {
         C[0][0] = A[startRowA][startColA] * B[startRowB][startColB];
      }
      else {
         c11 = matrixAdd(matrixProduct_DAC(A, startRowA, startColA, B, startRowB, startColB, newSize), matrixProduct_DAC(A, startRowA, startColA + newSize, B, startRowB + newSize, startColB, newSize));
         c12 = matrixAdd(matrixProduct_DAC(A, startRowA, startColA, B, startRowB, startColB + newSize, newSize), matrixProduct_DAC(A, startRowA, startColA + newSize, B, startRowB + newSize, startColB + newSize, newSize));
         c21 = matrixAdd(matrixProduct_DAC(A, startRowA + newSize, startColA, B, startRowB, startColB, newSize), matrixProduct_DAC(A, startRowA + newSize, startColA + newSize, B, startRowB + newSize, startColB, newSize));
         c22 = matrixAdd(matrixProduct_DAC(A, startRowA + newSize, startColA, B, startRowB, startColB + newSize, newSize), matrixProduct_DAC(A, startRowA + newSize, startColA + newSize, B, startRowB + newSize, startColB + newSize, newSize));
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


   public static int[][] matrixAdd(int[][] A, int[][] B) {
      int[][] C = new int[A.length][A.length];
      for (int i = 0; i < A.length; i++) {
         for (int j = 0; j < A.length; j++) {
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
