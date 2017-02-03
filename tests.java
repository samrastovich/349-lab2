import java.util.Scanner;
import java.lang.IllegalArgumentException;
import java.io.File;
import java.io.FileNotFoundException;

public class tests {
   public static void main(String[] args) {
      System.out.println("Enter your file name: ");
      Scanner in = new Scanner(System.in);
      String fileName = in.nextLine();
      try {
         in = new Scanner(new File(fileName));
      }
      catch (FileNotFoundException e) {
         System.out.println("File does not exist\n");
         return;
      }
      int[][] uppLeft, uppRight, botLeft, botRight, C;
      
      int rows = in.nextInt();
      int cols = in.nextInt();

      C = new int[rows * 2][cols * 2];
      uppLeft = new int[rows][cols];
      for (int i = 0; i < rows; i++) {
         for (int j = 0; j < cols; j++) {
            uppLeft[i][j] = in.nextInt();
         }
      }
      
      rows = in.nextInt();
      cols = in.nextInt();
      uppRight = new int[rows][cols];
      for (int i = 0; i < rows; i++) {
         for (int j = 0; j < cols; j++) {
            uppRight[i][j] = in.nextInt();
         }
      }
      rows = in.nextInt();
      cols = in.nextInt();
      botLeft = new int[rows][cols];
      for (int i = 0; i < rows; i++) {
         for (int j = 0; j < cols; j++) {
            botLeft[i][j] = in.nextInt();
         }
      }
      rows = in.nextInt();
      cols = in.nextInt();
      botRight = new int[rows][cols];
      for (int i = 0; i < rows; i++) {
         for (int j = 0; j < cols; j++) {
            botRight[i][j] = in.nextInt();
         }
      }
      System.out.println(botRight[0][0]);
      try { 
         C = MatrixProduct.combQuads(C, uppLeft, uppRight, botLeft, botRight, rows);
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