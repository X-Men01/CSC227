public class Process {
    private int ProcessID , BurstTime , Memory ;
    private State state;
    public Process(int processId, int memoryRequired, int burstTime) {
	    this.ProcessID = processId;
	    this.Memory = memoryRequired;
	    this.BurstTime = burstTime;
	    
        this.state = State.Waiting;
	  }

}
