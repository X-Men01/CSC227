import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class JobScheduler extends Thread {
    static ArrayList<Process> jobQueue = new ArrayList<>();

    static int nOfTotalJobs = 0;

    // Read process information from file in a separate thread
    int lineNum = 0 ;
    public void run() {

        try {
            // Reading process
            Scanner scanner = new Scanner(new FileReader("job.txt"));
            try {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
					lineNum++;
					
					if (lineNum % 2 == 0) {
						line = line.replaceAll("[^0-9]", " ");
						 String[] parts = line.split("  ");
						 int processId = Integer.parseInt(parts[0]);
						 int burstTime = Integer.parseInt(parts[1]);
						 int memoryRequired = Integer.parseInt(parts[2]);
                        
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
