import java.util.ArrayList;

public class CPUScheduler extends Thread {
	
	static ArrayList<Process> readyQueue = new ArrayList<>();
	// Main memory size will be 8192
	static Memory memory = new Memory(8192);

	// Load jobs to ready queue in a separate thread
	public void run() {

		while (true) {

			if (!JobScheduler.jobQueue.isEmpty()) {
				Process process = JobScheduler.jobQueue.get(0);

				// If the process size exceeds 8192 we will never let it enter the ready queue
				// and we will take out from job queue and send it back to the disk
				if (process.getMemory() > 8192) {
					JobScheduler.jobQueue.remove(0);
					System.out.println(
							"Process number: " + process.getProcessID() + " cannot be scheduled due to memory size");
					continue;
				}
				
				// This while loop will not terminate until it finds a space for the process
				// (wait until one of the processes in the memory terminates)
				while (!Memory.allocateMemory(process.getMemory() )) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
                }
				
				// If it finds a space we will add it to the ready queue and switch it state to Ready
				JobScheduler.jobQueue.remove(0);
				readyQueue.add(process);	
				process.setState(States.Ready);

				process.setArrivalTime( Algorithm.getTime() ); 

				System.out.println("Process" + process.getProcessID() + " State: " + process.getState() + " Arrived at: "
						+ process.getArrivalTime());

			}
			// This block is used to let this method run concurrently rather than in parallel
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}

		}

	}
	

}


