public class MatrixProduct {
   public static int[][] matrixProduct_DAC(int[][] A, int[][] B) {
      checkSize(A, B);
      int n = A.length;
      return matrixProduct_DAC(A, 0, 0, B, 0, 0, n);
   }

   private static int[][] matrixProduct_DAC(int[][] A, int startRowA, int startColA, int[][] B, int startRowB, int startColB, int n) {
      int[][] C = new int[n][n];
     // int[][] c11, c12, c21, c22;
      int newSize = n / 2;
      if (n == 1) {
         C[0][0] = A[startRowA][startColA] * B[startRowB][startColB];
      }
      else {
         matrixAdd(matrixProduct_DAC(A, startRowA, startColA, B, startRowB, startColB, newSize), matrixProduct_DAC(A, startRowA, startColA + newSize, B, startRowB + newSize, startColB, newSize), C, 0, 0);
         matrixAdd(matrixProduct_DAC(A, startRowA, startColA, B, startRowB, startColB + newSize, newSize), matrixProduct_DAC(A, startRowA, startColA + newSize, B, startRowB + newSize, startColB + newSize, newSize), C, 0, newSize);
         matrixAdd(matrixProduct_DAC(A, startRowA + newSize, startColA, B, startRowB, startColB, newSize), matrixProduct_DAC(A, startRowA + newSize, startColA + newSize, B, startRowB + newSize, startColB, newSize), C, newSize, 0);
         matrixAdd(matrixProduct_DAC(A, startRowA + newSize, startColA, B, startRowB, startColB + newSize, newSize), matrixProduct_DAC(A, startRowA + newSize, startColA + newSize, B, startRowB + newSize, startColB + newSize, newSize), C, newSize, newSize);
       /*  subToResultMatrix(C, c11, 0, n / 2, 0, n / 2); //(C[0][0] = c11;
         subToResultMatrix(C, c12, n / 2, n, 0, n / 2); // C[0][1] = c12;
         subToResultMatrix(C, c21, 0, n / 2, n / 2, n); //C[1][0] = c21;
         subToResultMatrix(C, c22, n / 2, n, n / 2, n); //C[1][1] = c22; */
      }
      return C;
   }
   
/*   private static void subToResultMatrix(int[][] C, int[][] sub, int startRow, int endRow, int startCol, int endCol) {
      for (int i = startRow; i < endRow; i++) {
         for (int j = startCol; j < endCol; j++) {
            C[i][j] = sub[i - startRow][j - startCol];
         }
      }
   } */


   public static void matrixAdd(int[][] A, int[][] B, int[][] C, int rowC, int colC) {
      int n = A.length;   
      for (int i = 0; i < n; i++) {
         for (int j = 0; j < n; j++) {
            C[i + rowC][j + colC] = A[i][j] + B[i][j];
         }
      }
   }

   public static int[][] matrixProduct_Strassen(int[][] A, int[][] B) {
      checkSize(A, B);
      int n = A.length;
      return matrixProduct_Strassen(A, B, 0, 0, 0, 0, n);
   }

   private static int[][] matrixProduct_Strassen(int[][] A, int[][] B, int startRowA, int startColA, int startRowB, int startColB, int n) {
      int[][] C = new int[n][n];
      int[][] s1, s2, s3, s4, s5, s6, s7, s8, s9, s10;
      int newSize = n / 2;
      if (n == 1) {
         C[0][0] = A[startRowA][startColA] * B[startRowB][startColB];
      }
      else {
         s1 = matrixDiff(B, 1, 2, B, 2, 2); //s1 = B12 - B22
         //s2 = A11 + A12
         //s3 = A21 + A22
         //s4 = B21 + B11
         //s5 = A11 + A22
         //s6 = B11 + B22
         //s7 = A12 - A22
         //s8 = B21 + B22
         //s9 = A11 - A21
         //s10 = B11 + B12
      }
   }

   private static void matrixSum(int[][] A, startRowA, startColA, int[][] B, startRowB, startColB, n) {

   }

   private static void matrixDiff(int[][] A, startRowA, startColA, int[][] B, startRowB, startColB, n) {

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
