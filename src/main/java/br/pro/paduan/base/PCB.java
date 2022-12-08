package br.pro.paduan.base;

import java.awt.Color;

/**
 * PBD = Process Control Block
 * Used for Graph and Scheduler
 * 
 * Information about an execution block of a process on the Gantt chart
 * For this simulator it contain: process identifier, start and end of the
 * block, color
 * 
 * @author Emerson S. Paduan
 */
public class PCB {
    private final String id;
    private int start, interruption;
    private Color color;

    /**
     * Constructor
     * 
     * @param process process
     * @param time:   start time of execution
     */
    public PCB(Process process, int time) {
        this.id = process.getId();
        this.start = time;
        color = null;
    }

    /**
     * 
     * @return process id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @return start time of execution for this block
     */
    public int getStart() {
        return start;
    }

    /**
     * 
     * @return interruption time for this block
     */
    public int getInterruption() {
        return interruption;
    }

    /**
     * 
     * @param interruption interruption to assign
     */
    public void setInterruption(int interruption) {
        this.interruption = interruption;
    }

    /**
     * 
     * @return color of the block
     */
    public Color getColor() {
        return color;
    }

    /**
     * 
     * @param color color to assign
     */
    public void setColor(Color color) {
        this.color = color;
    }

}
