import java.util.ArrayList;

public class RoundRobin extends Algorithm {

	public RoundRobin(ArrayList<Process> readyQueue, int q) {

		int quantum = q;

		/*
		 * if (readyQueue.isEmpty()) {
		 * System.out.println("\n\n\t\treadyQueue is Empty\n\n"); return; }
		 */

		do {

			if (!readyQueue.isEmpty())
				roundrobin(readyQueue, q);

			else {
				try {
					Main.Thread2.join(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		} while (!JobScheduler.jobQueue.isEmpty());

		// At each loop it will wait for 10ms for Thread2 in case ready queue load more
		// processes from the job queue

		loadResults();

		System.exit(0);
	}

	public static void roundrobin(ArrayList<Process> readyQueue, int q) {
		int quantum = q;

		while (!readyQueue.isEmpty()) {
			Process p = readyQueue.remove(0);

			// If the process doesn't finish ready queue will load it again in the back of
			// the queue for the next round

			if (p.getBurstTime() > quantum) {

				Time += quantum;

				GranttChart cG = new GranttChart("P" + p.getProcessID(), Time - quantum, Time);
				granttChart.add(cG);

				p.setWaitTime(p.getWaitTime() + (Time - quantum - p.getTerminationTime()));
				p.setBurstTime(p.getBurstTime() - quantum);
				p.setTerminationTime(Time);

				readyQueue.add(p);

			} else {

				Time += p.getBurstTime();
				p.setTurnAround(Time - p.getArrivalTime());

				p.setWaitTime(p.getWaitTime() + (Time - p.getBurstTime() - p.getTerminationTime()));

				p.setTerminationTime(Time);
				p.setState(States.Terminated);

				System.out.println("Process " + p.getProcessID() + " State: " + p.getState() + " Terminated at: "
						+ p.getTerminationTime());

				GranttChart cG = new GranttChart("P" + p.getProcessID(), Time - p.getBurstTime(), Time);
				granttChart.add(cG);

				finalQueue.add(p);

				// After terminating the process it will go out the ready queue and in this case
				// more memory will be available to be occupied
				Memory.releaseMemory(p.getMemory());
			}
		}
	}

}
