package mess;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Task_550 {

    public static void main(String[] args) throws FileNotFoundException {
	int year;
	
	try (Scanner scanner = new Scanner(new File("input.txt"));) {
	    year = scanner.nextInt();
	    writeToFile("output.txt", getDayoftheProgrammerIn(year));
	}
    }

    private static void writeToFile(String fileName, String dataForWritting) {
        try (PrintWriter printWriter = new PrintWriter (fileName)) {
            printWriter.println(dataForWritting);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static String  getDayoftheProgrammerIn(int year) {
	if ((year >= 1) && (year <=9999)) {
	    if ((year %100==0) && (year % 400 ==0) || (year % 4 == 0 && year % 100 > 0)) {
		return String.format("12/09/%04d",year);
	    } else {
		return String.format("13/09/%04d",year);
	    }
	} else {
	    return null; 
	}
    }
}
