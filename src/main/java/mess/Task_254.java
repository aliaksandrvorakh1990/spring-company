package mess;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Task_254 {
    public static void main(String[] args) throws FileNotFoundException {
	
	try (Scanner scanner = new Scanner(new File("input.txt")); // read data from file
	     PrintWriter printWriter = new PrintWriter ("output.txt")) {
	    int earldomCounts = scanner.nextInt();
	    
	    int[] patronElectionsForEarldom = new int[earldomCounts];
	    int[]  patronElectionsForconfederation = new int[earldomCounts];  // for compering Elections
	    // result between Earldoms and confederation

	    for (int earldom = 0; earldom < earldomCounts; earldom++) {
		int approval = scanner.nextInt();
		patronElectionsForEarldom[earldom] = approval;
		patronElectionsForconfederation[earldom] = approval;
	    }

	    int confederationApprovalsCount = scanner.nextInt();
	    
	    for (int approvalNumber = 0; approvalNumber < confederationApprovalsCount; approvalNumber++) {
		int alterablePatron = scanner.nextInt();
		int desiredPatron =  scanner.nextInt();
		
		for (int earldom = 0; earldom < earldomCounts; earldom++) {
		    if (patronElectionsForEarldom[earldom] == alterablePatron) {
			patronElectionsForconfederation[earldom] = desiredPatron;
		    }   
		}
	    }
	    
	    //  write result to file
	    for (int patron : patronElectionsForconfederation) {
		printWriter.print(patron + " ");
	    }
	}
    }
   
}
