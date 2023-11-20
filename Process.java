public class Process {
    private int ProcessID , BurstTime , Memory ;

    private int arrivalTime, turnAround, waitTime, terminationTime;
    
    

    private State state;
    
    

    public Process(int processId, int burstTime, int memoryRequired)  {
	    this.ProcessID = processId;
	    this.Memory = memoryRequired;
	    this.BurstTime = burstTime;
	    
        this.state = State.Waiting;
	  }

      public int getMemory() {
        return Memory;
    }
    public int getBurstTime() {
        return BurstTime;
    }
    public int getProcessID() {
        return ProcessID;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getTurnAround() {
        return turnAround;
    }

    public void setTurnAround(int turnAround) {
        this.turnAround = turnAround;
    }

    public int getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }

    public int getTerminationTime() {
        return terminationTime;
    }

    public void setTerminationTime(int terminationTime) {
        this.terminationTime = terminationTime;
    }



}
