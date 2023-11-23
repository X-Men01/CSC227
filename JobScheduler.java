import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class JobScheduler extends Thread {
	
    static ArrayList<Process> jobQueue = new ArrayList<>();
    static int nOfTotalJobs = 0;
    int lineNum = 0;
    
    int processId, burstTime, memoryRequired ;
    
    // Read process information from file in a separate thread
    public void run() {

        try {
            // Reading process
            Scanner scanner = new Scanner(new FileReader("C:\\Users\\yazee\\OneDrive\\Desktop\\New Text Document.txt" ) );
            try {
                while (scanner.hasNextLine()) {
                	String line = scanner.nextLine();
					lineNum++;
					
					if (lineNum % 2 == 0) {
						line = line.replaceAll("[^0-9]", " ");
						
						 String[] parts = line.split("  ");
						 processId = Integer.parseInt(parts[0]);
						 burstTime = Integer.parseInt(parts[1]);
						 memoryRequired = Integer.parseInt(parts[2]);
							synchronized (jobQueue) {
								jobQueue.add(new Process(processId, burstTime, memoryRequired));
								nOfTotalJobs++;
							}
							
					}

				}

			} catch (NumberFormatException e) {
                e.printStackTrace();
            }
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
