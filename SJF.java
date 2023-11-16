import java.util.ArrayList;

public class SJF {
    static ArrayList<Process> finalQueue = new ArrayList<>();

    static ArrayList<GranttChart> granttChart = new ArrayList<>();

    static int Time = 0;

    public SJF(ArrayList<Process> readyQueue) {
        readyQueue.sort((p1, p2) -> p1.getBurstTime() - p2.getBurstTime());
        System.out.println("----------------------------------Running SJF----------------------------------");
        if (readyQueue.isEmpty()) {
            System.out.println("\n\n\t\treadyQueue is Empty\n\n");
            return;
        }
        while (!JobScheduler.jobQueue.isEmpty()) {

            if (!readyQueue.isEmpty()) {
                Process p = readyQueue.remove(0);

                Time += p.getBurstTime();
                p.setTurnAround(Time - p.getArrivalTime());
                p.setWaitTime((p.getTurnAround() - p.getBurstTime()) + p.getWaitTime());
                p.setTerminationTime(Time);

                p.setState(State.Terminated);

                Memory.releaseMemory(p.getMemory());

            }

            else {

                try {
                    Main.Thread2.join(1000);
                    readyQueue.sort((p1, p2) -> p1.getBurstTime() - p2.getBurstTime());
                } catch (InterruptedException e) {

                    e.printStackTrace();
                }

            }
        }

    }
    public static void sort(ArrayList<Process> readyQueue) {
		readyQueue.sort((p1, p2) -> p1.getBurstTime() - p2.getBurstTime());
	}

}
