import java.util.Scanner;
import java.lang.IllegalArgumentException;
import java.io.File;
import java.io.FileNotFoundException;

public class MatrixWork {
   public static int[][] matrixProduct(int[][] A, int[][] B) {
      int[][] C = new int[A.length][B[0].length];
      if (A[0].length != B.length) {
         throw new IllegalArgumentException();
      } 
      else {   
         int sum = 0;
         for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B[0].length; j++) {
               for (int l = 0; l < A[0].length; l++) {
                   sum += A[i][l] * B[l][j];
               }
               C[i][j] = sum;
               sum = 0;
            }
         }          
      }
      return C;
   }
   public static void main(String[] args) throws FileNotFoundException{
      System.out.println("Enter your file name: ");
      Scanner in = new Scanner(System.in);
      String fileName = in.nextLine();
      in = new Scanner(new File(fileName));
      int[][] A, B, C;
      
      int rows = in.nextInt();
      int cols = in.nextInt();
      A = new int[rows][cols];
      for (int i = 0; i < rows; i++) {
         for (int j = 0; j < cols; j++) {
            A[i][j] = in.nextInt();
         }
      }
      
      rows = in.nextInt();
      cols = in.nextInt();
      B = new int[rows][cols];
      for (int i = 0; i < rows; i++) {
         for (int j = 0; j < cols; j++) {
            B[i][j] = in.nextInt();
         }
      }
      try { 
         C = MatrixProduct.matrixProduct_DAC(A, B);
         for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < C[0].length; j++) {
               System.out.print(C[i][j] + " ");
            }
            System.out.println();
         }	
      } catch(IllegalArgumentException e) {
         System.err.println("Matrix sizes are not compatible");
      }
   }
}
