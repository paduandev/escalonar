package br.pro.paduan.base;

import java.util.ArrayList;

/**
 * Class written to simulate process scheduling on an operating system running
 * on a single-core processor machine.
 * 
 * @author Emerson dos Santos Paduan
 * @version 1.0
 */
public class ProcessScheduler {

    // type of ordering to be applied in the list of processes
    private static final int ARRIVAL = 1;
    private static final int EXECUTION = 2;
    private static final int PRIORITY = 3;
    private static final int NAME = 4;

    private ArrayList<Process> input; // input processes
    private ArrayList<Process> result;
    private ArrayList<Process> ready; // process ready for run
    private ArrayList<PCB> graph; // PCB to generate the graph
    private float averageWait, averageTurnaround;

    /**
     * New Process Scheduler without a process list
     * In this case, <b><I>setInput</I></b> method must be used to set the process
     * list
     */
    public ProcessScheduler() {

    }

    /**
     * New Process Scheduler with a process list
     * 
     * @param processList processes list to be scheduled
     */

    public ProcessScheduler(ArrayList<Process> processList) {
        if (!validateProcesses(processList))
            return;

        input = new ArrayList<>();
        for (Process p : processList) {
            input.add(new Process(p));
        }
    }

    /**
     * gets the average waiting time of processes after completion of scheduler
     * 
     * @return average
     */
    public float getAverageWait() {
        return averageWait;
    }

    /**
     * gets the average Turnaround time of processes after completion of escalation
     * 
     * @return average turnaround
     */
    public float getAverageTurnaround() {
        return averageTurnaround;
    }

    /**
     * Checks that the list of processes has only valid values
     * 
     * @param processList list of processes to be validated
     * @return true if the list is valid, or false if the list is null or contains
     *         invalid values
     */

    public static boolean validateProcesses(ArrayList<Process> processList) {
        if (processList == null || processList.isEmpty())
            return false;

        for (Process p : processList)
            if (p.getArrive() < 0 || p.getDuration() < 0 || p.getPriority() < 0)
                return false;

        return true;
    }

    /**
     * Provides the scheduler with the list of processes to be scheduled.
     * 
     * @param processList Processes List
     */

    public void setInput(ArrayList<Process> processList) {
        if (!validateProcesses(processList))
            return;

        input = new ArrayList<>();
        for (Process p : processList) {
            input.add(new Process(p));
        }

    }

    /**
     * Sorts a list of processes according to the option
     * This method must be stable
     * 
     * @param processList processes list
     * @param sortingType sorting type (by arrive time, execution time,
     *                    priority or by name - process id)
     * 
     */
    private void sortBy(ArrayList<Process> processList, int sortingType) {
        if (sortingType == ARRIVAL) {
            for (int i = 1; i <= processList.size() - 1; i++) {
                Process t = processList.get(i);
                int j = i - 1;
                while ((j >= 0) && (processList.get(j).getArrive() > t.getArrive())) {
                    processList.set(j + 1, processList.get(j));
                    j--;
                }
                processList.set(j + 1, t);
            }
        } else if (sortingType == EXECUTION) {
            for (int i = 1; i <= processList.size() - 1; i++) {
                Process t = processList.get(i);
                int j = i - 1;
                while ((j >= 0) && (processList.get(j).getDuration() > t.getDuration())) {
                    processList.set(j + 1, processList.get(j));
                    j--;
                }
                processList.set(j + 1, t);
            }
        } else if (sortingType == PRIORITY) // decreasing
        {
            for (int i = 1; i <= processList.size() - 1; i++) {
                Process t = processList.get(i);
                int j = i - 1;
                while ((j >= 0) && (processList.get(j).getPriority() < t.getPriority())) {
                    processList.set(j + 1, processList.get(j));
                    j--;
                }
                processList.set(j + 1, t);
            }
        } else {
            for (int i = 1; i <= processList.size() - 1; i++) {
                Process t = processList.get(i);
                int j = i - 1;
                while ((j >= 0) && (processList.get(j).getId().compareTo(t.getId()) > 0)) {
                    processList.set(j + 1, processList.get(j));
                    j--;
                }
                processList.set(j + 1, t);
            }
        }
    }

    /**
     * Algorithm First Came, First Served (FCFS)
     * Uses arrive time to schedule
     */

    public void schedulerFCFS() {
        if (!validateProcesses(input))
            return;

        ready = new ArrayList<>();
        result = new ArrayList<>();
        graph = new ArrayList<>();
        Process running;

        int currentTime;

        sortBy(input, ARRIVAL);
        averageWait = 0;
        averageTurnaround = 0;

        currentTime = input.get(0).getArrive();

        ready.add(input.get(0));
        input.remove(0);

        while (!ready.isEmpty() || !input.isEmpty()) {
            // if new processes arrived, put them in the ready queue
            while (!input.isEmpty() && input.get(0).getArrive() <= currentTime) {
                if (input.get(0).getArrive() <= currentTime) {
                    ready.add(input.get(0));
                    input.remove(0);
                }
            }
            if (!ready.isEmpty()) {
                sortBy(ready, ARRIVAL);
                // take the next shortest process in the ready queue
                running = ready.get(0);
                ready.remove(0);

                graph.add(new PCB(running, currentTime));

                // calculates the time intervals for the current process
                running.setWait(currentTime - running.getArrive());
                currentTime += running.getDuration();
                running.setTurnaround(currentTime - running.getArrive());

                graph.get(graph.size() - 1).setInterruption(currentTime);
                result.add(running);

                averageWait += running.getWait();
                averageTurnaround += running.getTurnaround();
            } else
            // whether there is a "free" time slot, where there are no processes to run
            if (!input.isEmpty()) {
                // get the arrival time of the next process in the queue
                int time = input.get(0).getArrive();
                graph.add(new PCB(new Process("Px", currentTime, time - currentTime), currentTime));
                currentTime = time;
                graph.get(graph.size() - 1).setInterruption(currentTime);
            }
        }

        graph.get(graph.size() - 1).setInterruption(currentTime);
        averageWait /= result.size();
        averageTurnaround /= result.size();
    }

    /**
     * Scheduler Shortest Job First (SJF) Non-Preemptive
     *
     */
    public void schedulerSJFnP() {
        int currentTime;
        Process running;

        if (!validateProcesses(input))
            return;

        result = new ArrayList<>();
        ready = new ArrayList<>();
        graph = new ArrayList<>();

        sortBy(input, ARRIVAL);

        averageWait = 0;
        averageTurnaround = 0;
        currentTime = input.get(0).getArrive();
        ready.add(input.get(0));
        input.remove(0);

        while (!ready.isEmpty() || !input.isEmpty()) {
            // if new processes arrived, put them in the ready queue
            while (!input.isEmpty() && input.get(0).getArrive() <= currentTime) {
                if (input.get(0).getArrive() <= currentTime) {
                    ready.add(input.get(0));
                    input.remove(0);
                }
            }
            if (!ready.isEmpty()) {
                // take the next shortest process in the ready queue
                sortBy(ready, EXECUTION);
                running = ready.get(0);
                ready.remove(0);
                graph.add(new PCB(running, currentTime));

                // calculates the time intervals for the current process
                running.setWait(currentTime - running.getArrive());
                currentTime += running.getDuration();
                running.setTurnaround(currentTime - running.getArrive());

                result.add(running);

                graph.get(graph.size() - 1).setInterruption(currentTime);
                averageWait += running.getWait();
                averageTurnaround += running.getTurnaround();
            } else {
                // whether there is a "free" time slot, where there are no processes to run
                if (!input.isEmpty()) {
                    // get the arrival time of the next process in the queue
                    int time = input.get(0).getArrive();
                    graph.add(new PCB(new Process("Px", currentTime, time - currentTime), currentTime));
                    currentTime = time;
                    graph.get(graph.size() - 1).setInterruption(currentTime);
                }
            }
        }
        // calculates average wait and turnaround times
        averageWait /= result.size();
        averageTurnaround /= result.size();
    }

    /**
     * Scheduler Shortest Remaining Time (SRT) Preemptive
     */
    public void schedulerSRT() {
        int tempoAtual;
        Process running = null;

        if (!validateProcesses(input))
            return;

        result = new ArrayList<>();
        ready = new ArrayList<>();
        graph = new ArrayList<>();

        sortBy(input, ARRIVAL);

        averageWait = 0;
        averageTurnaround = 0;
        tempoAtual = input.get(0).getArrive();
        ready.add(input.get(0));
        input.remove(0);

        // While there are processes in the input or Ready queue or the last process has
        // not finished
        while (!ready.isEmpty() || !input.isEmpty() || running != null) {
            // if new processes arrived, put them in the ready queue
            while (!input.isEmpty() && input.get(0).getArrive() <= tempoAtual) {
                if (input.get(0).getArrive() <= tempoAtual) {
                    ready.add(input.get(0));
                    input.remove(0);
                    sortBy(ready, EXECUTION);
                }
            }
            // if there is a process in the ready queue OR any process running
            if (!ready.isEmpty() || running != null) {
                // Checks for priority to interrupt
                if (running != null) // if there is a process running
                {
                    // process in the queue has the shortest duration? yes = exchange
                    if (!ready.isEmpty())
                        if (running.getDuration() > ready.get(0).getDuration()) {
                            running.setInterruption(tempoAtual); // time where it was stopped
                            graph.get(graph.size() - 1).setInterruption(tempoAtual);
                            ready.add(running);
                            running = ready.get(0);
                            ready.remove(0);
                            sortBy(ready, EXECUTION);
                            // put it on the graph and adjust the values
                            graph.add(new PCB(running, tempoAtual));
                            // update the waiting time for the process
                            running.setWait(running.getWait() + tempoAtual - running.getInterruption());
                        }
                } else // get next process on ready queue
                {
                    running = ready.get(0);
                    ready.remove(0);
                    graph.add(new PCB(running, tempoAtual));
                    running.setWait(running.getWait() + tempoAtual - running.getInterruption());
                }

                tempoAtual++;
                running.DecDuration();

                // if the process has finished
                if (running.getDuration() == 0) {
                    running.setTurnaround(tempoAtual - running.getArrive());
                    result.add(running);
                    graph.get(graph.size() - 1).setInterruption(tempoAtual);
                    averageWait += running.getWait();
                    averageTurnaround += running.getTurnaround();
                    running = null;
                }
            } else {
                // whether there is a "free" time slot, where there are no processes to run
                if (!input.isEmpty()) {
                    // get the arrival time of the next process in the queue
                    int time = input.get(0).getArrive();
                    graph.add(new PCB(new Process("Px", tempoAtual, time - tempoAtual), tempoAtual));
                    tempoAtual = time;
                    graph.get(graph.size() - 1).setInterruption(tempoAtual);
                }
            }

        }
        // calculates average wait and turnaround times
        averageWait /= result.size();
        averageTurnaround /= result.size();
    }

    /**
     * Scheduler Priority Non-Preemptive
     * Higher priority values indicate greater importance.
     */
    public void schedulerPriorityNP() {
        int tempoAtual;
        Process running;

        if (!validateProcesses(input))
            return;

        result = new ArrayList<>();
        ready = new ArrayList<>();
        graph = new ArrayList<>();

        sortBy(input, ARRIVAL);

        averageWait = 0;
        averageTurnaround = 0;
        tempoAtual = input.get(0).getArrive();
        ready.add(input.get(0));
        input.remove(0);

        while (!ready.isEmpty() || !input.isEmpty()) {
            // if new processes arrived, put them in the ready queue
            while (!input.isEmpty() && input.get(0).getArrive() <= tempoAtual) {
                if (input.get(0).getArrive() <= tempoAtual) {
                    ready.add(input.get(0));
                    input.remove(0);
                }
            }
            if (!ready.isEmpty()) {
                // get the next highest priority process in the ready queue
                sortBy(ready, PRIORITY);
                running = ready.get(0);
                ready.remove(0);

                graph.add(new PCB(running, tempoAtual));

                // calculates the time intervals for the current process
                running.setWait(tempoAtual - running.getArrive());
                tempoAtual += running.getDuration();
                running.setTurnaround(tempoAtual - running.getArrive());

                result.add(running);
                // updates the runtime finish on the graph
                graph.get(graph.size() - 1).setInterruption(tempoAtual);

                averageWait += running.getWait();
                averageTurnaround += running.getTurnaround();
            } else {
                // whether there is a "free" time slot, where there are no processes to run
                if (!input.isEmpty()) {
                    // get the arrival time of the next process in the queue
                    int time = input.get(0).getArrive();
                    graph.add(new PCB(new Process("Px", tempoAtual, time - tempoAtual), tempoAtual));
                    tempoAtual = time;
                    graph.get(graph.size() - 1).setInterruption(tempoAtual);
                }
            }
        }
        // calculates average wait and turnaround times
        averageWait /= result.size();
        averageTurnaround /= result.size();
    }

    /**
     * Scheduler Priority Preemptive
     * Higher priority values indicate greater importance.
     */
    public void schedulerPriorityP() {
        int currentTime;
        Process running = null;

        if (!validateProcesses(input))
            return;

        result = new ArrayList<>();
        ready = new ArrayList<>();
        graph = new ArrayList<>();

        sortBy(input, ARRIVAL);

        averageWait = 0;
        averageTurnaround = 0;
        currentTime = input.get(0).getArrive();
        ready.add(input.get(0));
        input.remove(0);

        // While there are processes in the input or Ready queue or the last process has
        // not finished
        while (!ready.isEmpty() || !input.isEmpty() || running != null) {
            // if new processes arrived, put them in the ready queue
            while (!input.isEmpty() && input.get(0).getArrive() <= currentTime) {
                if (input.get(0).getArrive() <= currentTime) {
                    ready.add(input.get(0));
                    input.remove(0);
                    sortBy(ready, PRIORITY);
                }
            }

            // if there is a process in the ready queue OR any process running
            if (!ready.isEmpty() || running != null) {
                // Checks for priority to interrupt
                if (running != null) // are there process running
                {
                    if (!ready.isEmpty())
                        if (running.getPriority() < ready.get(0).getPriority()) {
                            running.setInterruption(currentTime); // time where it was stopped
                            graph.get(graph.size() - 1).setInterruption(currentTime);
                            ready.add(running);
                            running = ready.get(0);
                            ready.remove(0);
                            sortBy(ready, PRIORITY);
                            graph.add(new PCB(running, currentTime));
                        }
                } else {
                    running = ready.get(0);
                    ready.remove(0);
                    graph.add(new PCB(running, currentTime));
                    running.setWait(running.getWait() + currentTime - running.getInterruption());
                }

                currentTime++;
                running.DecDuration();

                // process has finished
                if (running.getDuration() == 0) {
                    running.setTurnaround(currentTime - running.getArrive());
                    result.add(running);
                    graph.get(graph.size() - 1).setInterruption(currentTime);

                    averageWait += running.getWait();
                    averageTurnaround += running.getTurnaround();
                    running = null;
                }
            } else {
                // whether there is a "free" time slot, where there are no processes to run
                if (!input.isEmpty()) {
                    // get the arrival time of the next process in the queue
                    int tempo = input.get(0).getArrive();
                    graph.add(new PCB(new Process("Px", currentTime, tempo - currentTime), currentTime));
                    currentTime = tempo;
                    graph.get(graph.size() - 1).setInterruption(currentTime);
                }
            }
        }
        // calculates average wait and turnaround times
        averageWait /= result.size();
        averageTurnaround /= result.size();
    }

    /**
     * Scheduler Round Robin
     * 
     * @param quantum quantum for scheduler
     */
    public void schedulerRoundRobin(int quantum) {
        int currentTime;
        Process running = null;

        if (!validateProcesses(input) || quantum <= 0)
            return;

        result = new ArrayList<>();
        ready = new ArrayList<>();
        graph = new ArrayList<>();

        sortBy(input, ARRIVAL);

        averageWait = 0;
        averageTurnaround = 0;
        currentTime = input.get(0).getArrive();
        ready.add(input.get(0));
        input.remove(0);

        // While there are processes in the input or Ready queue or the last process has
        // not finished
        while (!ready.isEmpty() || !input.isEmpty() || running != null) {
            // if new processes arrived, put them in the ready queue
            while (!input.isEmpty() && input.get(0).getArrive() <= currentTime) {
                if (input.get(0).getArrive() <= currentTime) {
                    ready.add(input.get(0));
                    input.remove(0);
                }
            }

            // if there is a process in the ready queue OR any process running
            if (!ready.isEmpty() || running != null) {
                if (running != null) // are there running process
                {
                    if (!ready.isEmpty()) {
                        running.setInterruption(currentTime); // interruption time
                        graph.get(graph.size() - 1).setInterruption(currentTime);
                        ready.add(running);
                        running = ready.get(0);
                        ready.remove(0);

                        running.setWait(running.getWait() + currentTime - running.getInterruption());
                        graph.add(new PCB(running, currentTime));
                    }
                } else {
                    if (!ready.isEmpty()) {
                        running = ready.get(0);
                        ready.remove(0);

                        graph.add(new PCB(running, currentTime));
                        running.setWait(running.getWait() + currentTime - running.getInterruption());
                    }
                }

                if (running.getDuration() > quantum) {
                    currentTime += quantum;
                    running.DecDuration(quantum);
                } else {
                    currentTime += running.getDuration();
                    running.DecDuration(running.getDuration());
                }

                // process has finished
                if (running.getDuration() == 0) {
                    running.setTurnaround(currentTime - running.getArrive());
                    result.add(running);
                    // adjusts the last running time on the chart
                    graph.get(graph.size() - 1).setInterruption(currentTime);
                    averageWait += running.getWait();
                    averageTurnaround += running.getTurnaround();
                    running = null;
                }
            } else {
                // whether there is a "free" time slot, where there are no processes to run
                if (!input.isEmpty()) {
                    // get the arrival time of the next process in the queue
                    int tempo = input.get(0).getArrive();
                    graph.add(new PCB(new Process("Px", currentTime, tempo - currentTime), currentTime));
                    currentTime = tempo;
                    graph.get(graph.size() - 1).setInterruption(currentTime);
                }
            }

        }
        // calculates average wait and turnaround times
        averageWait /= result.size();
        averageTurnaround /= result.size();
    }

    /**
     * Return a list of process sorted by process name.
     * These processes store their wait and turnaround times during scheduling.
     * 
     * @return ArrayList de processos ou null
     */

    public ArrayList<Process> retEscala() {

        sortBy(result, NAME);
        return result;
    }

    /**
     * Return a list of <I>PCB</I>'s representing the result of escalation in the
     * Gantt chart
     * 
     * @return ArrayList<PCB> list of PCB or null
     */

    public ArrayList<PCB> retGraf() {
        return graph;
    }

}
