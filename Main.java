System.out.println("Process" + p.getProcessID() + "( State: " + p.getState() + ", Terminated at: "
					+ p.getTerminationTime() +" )");
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
	static JobScheduler Thread1 = new JobScheduler();
	static CPUScheduler Thread2 = new CPUScheduler();

	public static void main(String[] args) {

		Thread1.start();
		Thread2.start();

		try {
			Thread1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Scanner input = new Scanner(System.in);

		System.out.println("Choose CPU scheduling algorithm (type num) : ");
		System.out.println("1.FCFS");
		System.out.println("2.SJF");
		System.out.println("3.Round-Robin with q = 3");
		System.out.println("4.Round-Robin with q = 5");
		int n = input.nextInt();

		switch (n) {
		case 1:
			FCFS f = new FCFS(CPUScheduler.readyQueue);
			break;
		case 2:
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			SJF s = new SJF(CPUScheduler.readyQueue);
			break;
		case 3:
			RoundRobin rr = new RoundRobin(CPUScheduler.readyQueue, 3);
			break;
		case 4:
			RoundRobin rr2 = new RoundRobin(CPUScheduler.readyQueue, 5);
		}
		
		System.exit(0);

	}
}
