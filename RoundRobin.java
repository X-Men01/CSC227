import java.util.ArrayList;

public class RoundRobin {
    static ArrayList<Process>finalQueue = new ArrayList<>();

    static ArrayList<GranttChart>granttChart = new ArrayList<>();

    static int Time =0;

    public RoundRobin(ArrayList<Process> readyQueue, int q) {

        int quantum = q;

        if (readyQueue.isEmpty()) {
            System.out.println("\n\n\t\treadyQueue is Empty\n\n");
            return;
        }

        while (!JobScheduler.jobQueue.isEmpty()) {

            // At each loop it will wait for 100ms for Thread2 in case ready queue load more
            // processes from the job queue
            try {
                Main.Thread2.join(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // if (!readyQueue.isEmpty())

                while (!readyQueue.isEmpty()) {
                    Process p = readyQueue.remove(0);

                    int RR_Time = p.getBurstTime();

                    // If the process doesn't finish ready queue will load it again in the back of
                    // the queue for the next round
                    if (RR_Time> quantum) {
                        Time += quantum;
                        RR_Time -= quantum;
                        GranttChart cG = new GranttChart("P" + p.getProcessID(), Time - quantum, Time);
                        granttChart.add(cG);

                        readyQueue.add(p);

                    } else {
                        Time += RR_Time;
                        p.setTurnAround( Time - p.getArrivalTime()); 
                        p.setWaitTime((p.getTerminationTime() - p.getProcessID())+p.getWaitTime()); 
                        p.setTerminationTime(Time);
                        p.setState(State.Terminated);
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

        loadResults();

        System.exit(0);
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
