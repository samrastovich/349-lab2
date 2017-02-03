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


   private static void matrixAdd(int[][] A, int[][] B, int[][] C, int rowC, int colC) {
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
      int[][] s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, p1, p2, p3, p4, p5, p6, p7, c11, c12, c21, c22;
      int newSize = n / 2;
      if (n == 1) {
         C[0][0] = A[startRowA][startColA] * B[startRowB][startColB];
      }
      else {
         s1 = matrixDiff(B, startRowB, startColB + newSize, B, startRowB + newSize, startColB + newSize, newSize); //s1 = B12 - B22
         s2 = matrixSum(A, startRowA, startColA, A, startRowA, startColA + newSize, newSize); //s2 = A11 + A12
         s3 = matrixSum(A, startRowA + newSize, startColA, A, startRowA + newSize, startColA + newSize, newSize); //s3 = A21 + A22
         s4 = matrixSum(B, startRowB + newSize, startColB, B, startRowB, startColB, newSize); //s4 = B21 + B11
         s5 = matrixSum(A, startRowA, startColA, A, startRowA + newSize, startColA + newSize, newSize); //s5 = A11 + A22
         s6 = matrixSum(B, startRowB, startColB, B, startRowB + newSize, startColB + newSize, newSize); //s6 = B11 + B22
         s7 = matrixDiff(A, startRowA, startColA + newSize, A, startRowA + newSize, startColA + newSize, newSize); //s7 = A12 - A22
         s8 = matrixSum(B, startRowB + newSize, startColB, B, startRowB + newSize, startColB + newSize, newSize); //s8 = B21 + B22
         s9 = matrixDiff(A, startRowA, startColA, A, startRowA + newSize, startColA, newSize); //s9 = A11 - A21
         s10 = matrixSum(B, startRowB, startColB, B, startRowB, startColB + newSize, newSize); //s10 = B11 + B12

         p1 = matrixProduct_Strassen(A, s1, startRowA, startColA, 0, 0, newSize); //p1 = a11 * s1
         p2 = matrixProduct_Strassen(s2, B, 0, 0, startRowB + newSize, startColB + newSize, newSize); //p2 = s2 * b22
         p3 = matrixProduct_Strassen(s3, B, 0, 0, startRowB, startColB, newSize); //p3 = s3 * b11
         p4 = matrixProduct_Strassen(A, s4, startRowA + newSize, startColA + newSize, 0, 0, newSize); //p4 = a22 * s4
         p5 = matrixProduct_Strassen(s5, s6, 0, 0, 0, 0, newSize); //p5 = s5 * s6
         p6 = matrixProduct_Strassen(s7, s8, 0, 0, 0, 0, newSize); //p6 = s7 * s8
         p7 = matrixProduct_Strassen(s9, s10, 0, 0, 0, 0, newSize); //p7 = s9 * s10

         c11 = matrixSum(matrixDiff(matrixSum(p5, 0, 0, p4, 0, 0, newSize), 0, 0, p2, 0, 0, newSize), 0, 0, p6, 0, 0, newSize); //c11 = p5 + p4 - p2 + p6
         c12 = matrixSum(p1, 0, 0, p2, 0, 0, newSize); //c12 = p1 + p2
         c21 = matrixSum(p3, 0, 0, p4, 0, 0, newSize); //c21 = p3 + p4
         c22 = matrixDiff(matrixDiff(matrixSum(p5, 0, 0, p1, 0, 0, newSize), 0, 0, p3, 0, 0, newSize), 0, 0, p7, 0, 0, newSize); //c22 = p5 + p1 - p3 - p7

         combQuads(C, c11, c12, c21, c22, newSize);
      }
      return C;
   }

   public static int[][] combQuads(int[][] res, int[][] uppLeft, int[][] uppRight, int[][] botLeft, int[][] botRight, int quadSize) { //TESTED: PASSED
      for (int i = 0; i < quadSize; i++) {
         for (int j = 0; j < quadSize; j++) {
            res[i][j] = uppLeft[i][j];
         }
      }
      for (int i = 0; i < quadSize; i++) {
         for (int j = 0; j < quadSize; j++) {
            res[i][j + quadSize] = uppRight[i][j];
         }
      }
      for (int i = 0; i < quadSize; i++) {
         for (int j = 0; j < quadSize; j++) {
            res[i + quadSize][j] = botLeft[i][j];
         }
      }
      for (int i = 0; i < quadSize; i++) {
         for (int j = 0; j < quadSize; j++) {
            res[i + quadSize][j + quadSize] = botRight[i][j];
         }
      }
      return res;
   }

   private static int[][] matrixSum(int[][] A, int startRowA, int startColA, int[][] B, int startRowB, int startColB, int n) {
      int[][] result = new int[n][n];

      for (int i = 0; i < n; i++) {
         for (int j = 0; j < n; j++) {
            result[i][j] = A[startRowA + i][startColA + j] + B[startRowB + i][startColB + j];
         }
      }

      return result;
   }

   private static int[][] matrixDiff(int[][] A, int startRowA, int startColA, int[][] B, int startRowB, int startColB, int n) {
      int[][] result = new int[n][n];

      for (int i = 0; i < n; i++) {
         for (int j = 0; j < n; j++) {
            result[i][j] = A[startRowA + i][startColA + j] - B[startRowB + i][startColB + j];
         }
      }

      return result;
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
