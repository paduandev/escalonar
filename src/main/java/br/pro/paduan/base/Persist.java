package br.pro.paduan.base;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Save and Read process data from/to a File
 * 
 * @author Emerson dos Santos Paduan
 * @version 1.0
 * 
 *          File format: (each line)
 *          x;y;z
 * 
 *          x = positive integer number representing the arrive time
 *          y = positive integer number representing the duration (length) time
 *          z = positive integer number representing process priority
 * 
 */

public class Persist {

    /**
     * Load the data processes from a file to an array of Processes.
     * This method makes validation of the file and each process data
     * 
     * @return list of processes loaded (or null if error)
     */
    public static ArrayList<Process> load() {
        ArrayList<Process> processesList = null;
        String line;
        String processName;
        int arrive, duration, priority;

        try {
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Processos (*.prc)", "prc");
            javax.swing.JFileChooser fileChooser = new javax.swing.JFileChooser();
            fileChooser.setFileSelectionMode(javax.swing.JFileChooser.FILES_ONLY);
            fileChooser.addChoosableFileFilter(filter);
            fileChooser.setFileFilter(filter);
            fileChooser.setCurrentDirectory(new java.io.File(System.getProperty("user.dir")));

            if (fileChooser.showOpenDialog(fileChooser) == javax.swing.JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();

                java.io.FileInputStream fileInput = new java.io.FileInputStream("" + file.getPath());
                InputStreamReader inputStream = new InputStreamReader(fileInput);

                BufferedReader bufferReader = new BufferedReader(inputStream);

                processesList = new ArrayList<>();

                int processNumber = 1;
                while ((line = bufferReader.readLine()) != null) {

                    if (!line.isEmpty()) // ignores blank lines in the file
                    {
                        processName = "P" + processNumber;
                        StringTokenizer st = new StringTokenizer(line, ";");
                        try {
                            arrive = Integer.parseInt(st.nextToken());
                            duration = Integer.parseInt(st.nextToken());
                            priority = Integer.parseInt(st.nextToken());
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Wrong format for input file!", "Attention",
                                    JOptionPane.ERROR_MESSAGE);
                            inputStream.close();
                            fileInput.close();
                            bufferReader.close();
                            processesList = null;
                            return null;
                        }

                        processesList.add(new Process(processName, arrive, duration, priority));

                        processNumber++;
                    }
                }
                inputStream.close();
                fileInput.close();

                if (!ProcessScheduler.validateProcesses(processesList)) {
                    JOptionPane.showMessageDialog(null, "Input file with invalid values!", "Attention",
                            JOptionPane.ERROR_MESSAGE);
                    processesList = null;
                    return null;
                }

            }
        } catch (java.io.IOException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, "Error on input file: " + ex.toString());
        }

        return processesList;
    }

    /**
     * Save processes data to a file
     * 
     * @param lista processes to be saved
     */
    public static void save(ArrayList<Process> lista) {
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Processos (*.prc)", "prc");
        javax.swing.JFileChooser fileChooser = new javax.swing.JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(filter);
        fileChooser.setCurrentDirectory(new java.io.File(System.getProperty("user.dir")));

        try {
            if (fileChooser.showOpenDialog(fileChooser) == javax.swing.JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                if (!file.getName().endsWith("prc"))
                    file = new File(file.getPath() + ".prc");
                if (file.exists()) {
                    int opc = JOptionPane.showConfirmDialog(null, "Delete original file?", "ATTENTION",
                            JOptionPane.YES_NO_OPTION);
                    if (opc == JOptionPane.NO_OPTION)
                        return;

                }
                java.io.FileOutputStream fileOutput = new java.io.FileOutputStream("" + file.getPath());
                OutputStreamWriter outputStream = new OutputStreamWriter(fileOutput);

                for (Process p : lista) {
                    try {
                        outputStream.write("" + p.getArrive());
                        outputStream.write(";");
                        outputStream.write("" + p.getDuration());
                        outputStream.write(";");
                        outputStream.write("" + p.getPriority());
                        outputStream.write("\n");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error on save\n" + ex.getMessage(), "Attention",
                                JOptionPane.ERROR_MESSAGE);
                    }

                }
                outputStream.close();
                fileOutput.close();
            }
        } catch (java.io.IOException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, "Error on output file:\n" + ex.toString());
        }

    }
}
