package mess;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Task_195 {

    public static void main(String[] args) throws FileNotFoundException {
	int n;
	int a;
	int b;
	
	try (Scanner scanner = new Scanner(new File("input.txt"));) {
	    n = scanner.nextInt();
	    a = scanner.nextInt();
	    b = scanner.nextInt();
	    
	    writeToFile("output.txt", calculateThoriumSulfideAmount(n, a, b).toString());
	}
    }
    
    private static Integer calculateThoriumSulfideAmount(int n, int a, int b) {
	if ((n > 0 && n <= 100 ) && (a > 0 && a <= 100) && (b > 0 && b <= 100)) {
	    return 2 * n * a *b;
	}
	return 0;
    }

    private static void writeToFile(String fileName, String dataForWritting) {
        try (PrintWriter printWriter = new PrintWriter (fileName)) {
            printWriter.println(dataForWritting);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
