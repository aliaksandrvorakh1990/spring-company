package mess;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Task_58 {
    public static void main(String[] args) throws IOException {
	doChechingOnSympaty("input.txt", "output.txt");
    }
        
    private static String chechOnSympaty(byte[][] matrix) {
	if (matrix.length == 1 || matrix[0].length == 1) {
	    return "YES";
	}
	
	for (int rowIndex = 0; rowIndex < (matrix.length - 1); rowIndex++) {
	   
	    for (int columnIndex = 0; columnIndex < matrix[0].length - 1; columnIndex++) {
		if (matrix[rowIndex][columnIndex] == matrix[rowIndex][columnIndex + 1]) {
		    if (matrix[rowIndex + 1][columnIndex] == matrix[rowIndex][columnIndex] 
			    && matrix[rowIndex + 1][columnIndex + 1] == matrix[rowIndex][columnIndex]) {
			return "NO";
		    }
		}
	    }	    
	}
	return "YES";
    }
    
    public static void doChechingOnSympaty(String inputfileName, String outputfileName) throws IOException {
	try (Scanner scanner = new Scanner(new File(inputfileName));
		PrintWriter printWriter = new PrintWriter (outputfileName);) {
	   int inputDataAmount = scanner.nextInt();
	 
	   int counterT = 0;
	   while (counterT < inputDataAmount) {
	       int rowsCount = scanner.nextInt();
	       int columnsCount = scanner.nextInt();
	     
	       scanner.nextLine();
	       
	       byte[][] matrix = new byte[rowsCount][columnsCount];
	       
	       for (int rowIndex = 0; rowIndex <rowsCount; rowIndex++) {
		   matrix[rowIndex] = scanner.nextLine().replaceAll(" ", "").getBytes();
	       }
	       
	       printWriter.println(chechOnSympaty(matrix));
	       
	       counterT++;
	   }   
	}
    }
    
}
