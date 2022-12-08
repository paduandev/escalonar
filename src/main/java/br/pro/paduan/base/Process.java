package br.pro.paduan.base;

/**
 * Class that represents a process running on the Operating System
 * 
 * @author Emerson dos Santos Paduan
 * @version 1.0
 * 
 */

public class Process {
    private final String id;
    private int duration, arrive, priority;
    private int wait, turnaround;
    private int interruption;

    /**
     * Constructor.
     * 
     * @param id       Process id
     * @param arrive   arrive time
     * @param duration duration time
     */
    public Process(String id, int arrive, int duration) {
        this.id = id;
        this.arrive = arrive;
        this.duration = duration;
        this.priority = 0;
        wait = turnaround = 0;
        interruption = arrive;
    }

    /**
     * Constructor.
     * 
     * @param id       Process id
     * @param arrive   arrive time
     * @param duration duration time
     * @param priority priority
     */
    public Process(String id, int arrive, int duration, int priority) {
        this.id = id;
        this.arrive = arrive;
        this.duration = duration;
        this.priority = priority;
        wait = turnaround = 0;
        interruption = arrive;
    }

    /**
     * Constructor.
     * 
     * @param process Process
     */
    public Process(Process process) {
        this.id = process.id;
        this.arrive = process.arrive;
        this.duration = process.duration;
        this.priority = process.priority;
        wait = turnaround = 0;
        interruption = process.arrive;
    }

    /**
     * Compare two Process
     * 
     * @param process process to be compared
     * @return true (if equals) / false (not equals)
     */
    public boolean equals(Process process) {
        if (process == null) {
            return false;
        }
        if (process.getId().equals(this.getId()) && process.getArrive() == this.getArrive()
                && process.getDuration() == this.getDuration() && process.getPriority() == this.getPriority())
            return true;
        else
            return false;
    }

    /**
     * @return Process id
     */
    public String getId() {
        return id;
    }

    /**
     * @return Process duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * decrements the duration of a process by one unit of time.
     */
    public void DecDuration() {
        duration--;
    }

    /**
     * decrements the duration of a process by "time" unit.
     * 
     * @param time
     */
    public void DecDuration(int time) {
        duration -= time;
    }

    /**
     * @return process arrive time
     */
    public int getArrive() {
        return arrive;
    }

    /**
     * @return process wait time
     */
    public int getWait() {
        return wait;
    }

    /**
     * @param wait process wait time
     */
    public void setWait(int wait) {
        this.wait = wait;
    }

    /**
     * @return turnaround
     */
    public int getTurnaround() {
        return turnaround;
    }

    /**
     * @param turnaround turnaround to be assigned
     */
    public void setTurnaround(int turnaround) {
        this.turnaround = turnaround;
    }

    /**
     * @return process priority
     */
    public int getPriority() {
        return priority;
    }

    /**
     * @return the interruption time
     */
    public int getInterruption() {
        return interruption;
    }

    /**
     * @param interruption the interruption to be arrived
     */
    public void setInterruption(int interruption) {
        this.interruption = interruption;
    }

}
