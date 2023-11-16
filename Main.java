

import java.util.Scanner;
public class Main {
	static JobScheduler Thread1 =new JobScheduler();
	static CPUScheduler Thread2 =new CPUScheduler();





	public static void main (String[] args)   {


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
		System.out.println("2.MQ");
		System.out.println("3.Round-Robin");
		int n = input.nextInt();
		switch(n) {
			case 1:
                FCFS f = new FCFS(CPUScheduler.readyQueue);
				break;
			case 2:
				// Algorithms.MQ(FileLoadJob.readyQueue);
				break;
			case 3:
				// Algorithms.RoundRobin(FileLoadJob.readyQueue);;
				break;
		}


		System.exit(0);




	}
}












