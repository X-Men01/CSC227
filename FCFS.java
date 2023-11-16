import java.util.ArrayList;

public class FCFS {
    static ArrayList<Process>finalQueue = new ArrayList<>();

    static ArrayList<GranttChart>granttChart = new ArrayList<>();
    
    static int Time =0;


    // FCFS method will receive a queue as a parameter and will start scheduling the
    // processes following the FCFS algorithm
    public FCFS(ArrayList<Process> readyQueue) {

        if (readyQueue.isEmpty()) {
            System.out.println("\n\n\t\treadyQueue is Empty\n\n");
            return;
        }
        while (!JobScheduler.jobQueue.isEmpty()) {

            if (!readyQueue.isEmpty())
                fcfs(readyQueue);
            else {
                try {
                    Main.Thread2.join(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }

        // At each loop it will wait for 100ms for Thread2 in case ready queue load more
        // processes from the job queue

        loadResults();

        System.exit(0);
    }

    // This method will Apply FCFS on the queue sent in the parameter but the
    // difference here that it's not a whole FCFS algorithm it only read from ready
    // queue once without loading from job queue more processes
    private static void fcfs(ArrayList<Process> readyQueue) {

        while (!readyQueue.isEmpty()) {
            Process p = readyQueue.remove(0);

            Time += p.getBurstTime();
            p.setTurnAround(Time - p.getArrivalTime()); 
            p.setWaitTime( (p.getTurnAround() - p.getBurstTime()) + p.getWaitTime());
            p.setTerminationTime(Time);  

            GranttChart cG = new GranttChart("P" + p.getProcessID(), Time - p.getBurstTime(), Time);
            granttChart.add(cG);

            finalQueue.add(p);
            p.setState(State.Terminated);
            System.out
                    .println("Process " + p.getProcessID() + " State: " + p.getState() + " Terminated at: " + p.getTerminationTime());
            Memory.releaseMemory(p.getMemory());

        }

    }

    public static void loadResults(){
    	//Print the Grantt chart 
        GranttChart.Table(granttChart);
        
        double sumWait=0,sumTurn=0;
        
        
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("\tProcess Id\t\tMemory Required\t\tArrival Time\t\t  Burst Time\t\tWaiting Time\t\tTurnAround Time\t\tTermination Time");
        

        for (Process p : finalQueue) {
        	System.out.println("\t"+p.getProcessID()+"               \t\t"+p.getMemory()+"\t\t      "+p.getArrivalTime()+"         \t\t"+p.getBurstTime()+"\t\t         "+p.getWaitTime()+"       \t\t"+p.getTurnAround()+"             \t\t"+p.getTerminationTime());
        	
            sumWait+=p.getWaitTime();
            sumTurn+=p.getTurnAround();
        }
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------");


        double avgWait =  sumWait/finalQueue.size();
        double avgTurn =  sumTurn/finalQueue.size();

        System.out.println("AVG Waiting: "+avgWait+"\nAVG TrunAround: "+avgTurn);
    }

}
