package mess;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Task_2 {
    
    public static void main(String[] args) throws IOException {
	int numberN;
	
	try (Scanner scanner = new Scanner(new File("input.txt"));) {
	    numberN = scanner.nextInt();
	    
	    writeToFile("output.txt", solveTask(numberN).toString());
	}
    }

    private static void writeToFile(String fileName, String dataForWritting) {
        try (PrintWriter printWriter = new PrintWriter (fileName)) {
            printWriter.println(dataForWritting);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    private static Integer solveTask(int number) {
	if (number <= 10_000) {
	    if (number > 0 && number != 0) {
		return calculateSumAllNumbersBetweenOneAnd(number);
	    } else {
		return Math.negateExact(calculateSumAllNumbersBetweenOneAnd(Math.abs(number))) + 1;
	    }
	} else {
	    return 0;
	}
    }
    
    
    public static int calculateSumAllNumbersBetweenOneAnd(int number) {
       
        return (1 + number) * number / 2;
    }
}