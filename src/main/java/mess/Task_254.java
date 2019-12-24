package mess;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Task_254 {
    public static void main(String[] args) throws FileNotFoundException {
	
	try (Scanner scanner = new Scanner(new File("input.txt")); // read data from file
	     PrintWriter printWriter = new PrintWriter ("output.txt")) {
	    int earldomCounts = scanner.nextInt();
	    
	    int[] patronElectionsForEarldom = new int[earldomCounts];

	    for (int earldom = 0; earldom < earldomCounts; earldom++) {
		int approval = scanner.nextInt();
		patronElectionsForEarldom[earldom] = approval;
	    }

	    int confederationApprovalsCount = scanner.nextInt();
	    
	    Map<Integer,Integer> patronElectionsForConfederation = new HashMap<>();
	    
	    for (int approvalNumber = 0; approvalNumber < confederationApprovalsCount; approvalNumber++) {
		Integer alterablePatron = scanner.nextInt();
		Integer desiredPatron =  scanner.nextInt();
		
		patronElectionsForConfederation.put(alterablePatron, desiredPatron);
	    }
	    
	    for (int earldom = 0; earldom < earldomCounts; earldom++) {
		patronElectionsForEarldom[earldom] = patronElectionsForConfederation
			.getOrDefault(patronElectionsForEarldom[earldom], patronElectionsForEarldom[earldom]);
	    }
	    
	    //  write result to file
	    for (int patron : patronElectionsForEarldom) {
		printWriter.print(patron + " ");
	    }
	}
    }
   
}
