package br.pro.paduan.base;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

/**
 * Show a Gantt graph with the scheduler
 * 
 * @author Emerson dos Santos Paduan
 * @version 1.0
 */
public class Graph extends JPanel {

    final int barWidth = 20; // bar width
    Graphics graphics;
    private ArrayList<PCB> queue; // PCBs process queue
    private ArrayList<Color> colors = new ArrayList<>();
    public final static int RET3D = 1;
    public final static int RET_ROUND = 2;
    public final static int RET_ROUND_F = 3;
    private int graphType;

    /**
     * Constructor
     * 
     * @param pcbList   ArrayList: PCB list
     * @param graphType int: graph type
     */
    public Graph(ArrayList<PCB> pcbList, int graphType) {
        queue = pcbList;
        this.graphType = graphType;
        setupColors();
    }

    @Override
    public void paintComponent(Graphics graphic) {
        int i = 0;
        int positionText = 45; // position for time text
        int positionPId = 10; // position for process Id

        int positionInit = 0, positionEnd;
        int frames; // how many frames will have the graph
        int blockLength; // length of each block (formed by many frames)

        // calculates the total length of the graphic based on the size of the form
        int graphLength = this.getParent().getWidth() - 60;

        // adjusts the size from the above calculation (width)
        this.setSize(graphLength + 20, this.getHeight());
        this.graphics = graphic;
        super.paintComponent(graphic);
        graphic.setFont(new Font(graphic.getFont().getFontName(), Font.PLAIN, 10));

        // calculates the size of each frame, considering the time interval from the
        // start of the first process and the end of the last process;
        frames = graphLength / queue.get(queue.size() - 1).getInterruption() - queue.get(0).getStart();

        if (queue.size() > 0) {
            // writes the start time for the graph
            graphic.drawString(queue.get(0).getStart() + "", 0, positionText);
            String txt;

            for (i = 0; i < queue.size(); i++) {
                txt = queue.get(i).getId();
                // calculates the position where the current block ends
                positionEnd = (queue.get(i).getInterruption() - queue.get(i).getStart()) * frames + positionInit;
                // calculates the current block size
                blockLength = positionEnd - positionInit;
                // get a color and draw the block
                graphic.setColor(queue.get(i).getColor());
                // displays "white" frame if there is no process
                if (queue.get(i).getId().equals("Px"))
                    graphic.drawRect(positionInit, 15, blockLength, barWidth);
                else
                    switch (graphType) {
                        case RET3D:
                            graphic.fill3DRect(positionInit, 15, blockLength, barWidth, true);
                            break;
                        case RET_ROUND:
                            graphic.drawRoundRect(positionInit, 15, blockLength, barWidth, 10, 10);
                            break;
                        case RET_ROUND_F:
                            graphic.fillRoundRect(positionInit, 15, blockLength, barWidth, 10, 10);
                            break;
                    }

                // write the process name and the end time of the block
                graphic.setColor(Color.BLACK);
                // If it is a time interval without a process, do not write the name
                if (!txt.equals("Px"))
                    graphic.drawString(txt, positionInit + (blockLength) / 2, positionPId);
                graphic.drawString(Integer.toString(queue.get(i).getInterruption()), positionEnd - 3, positionText);
                // updates the starting position of the next block
                positionInit += blockLength;
            }
        }

    }

    /**
     * Generates a new color for each process on queue
     */
    private void setupColors() {
        Random rd = new Random();
        int p, j, block;
        Color color;

        for (p = 0; p < queue.size(); p++) {
            // checks if the process has already received a color and if so, assigns the
            // same color
            for (block = 0; block < p; block++) {
                if (queue.get(block).getId().equals(queue.get(p).getId())) {
                    queue.get(p).setColor(queue.get(block).getColor());
                    break;
                }
            }
            // if you found the process, you already got the color and you can go to the
            // next block
            if (block != p) {
                continue;
            }
            // Indicates that there is no process running
            if (queue.get(p).getId().equals("Px")) {
                queue.get(p).setColor(this.getBackground());
                continue;
            }

            do {
                // generates a new color
                color = new Color(rd.nextInt(255), rd.nextInt(255), rd.nextInt(255));
                // checks if the color has already been used
                for (j = 0; j < colors.size(); j++) {
                    if (colors.get(j).equals(color)) {
                        break;
                    }
                }
                // if the color has already been used, it must generate another one
            } while (j < colors.size());
            colors.add(color);
            queue.get(p).setColor(color);
        }
    }

    /**
     * Updates the queue of processes that have been scheduled
     * 
     * @param queue
     */
    public void reSet(ArrayList<PCB> queue) {
        this.queue = queue;
    }

    /**
     * Remove all processes from the queue
     */
    public void removeAllP() {
        queue.removeAll(queue);
    }
}
