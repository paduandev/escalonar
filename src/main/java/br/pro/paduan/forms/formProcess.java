
package br.pro.paduan.forms;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import br.pro.paduan.base.ProcessScheduler;
import br.pro.paduan.base.Process;

/**
 * Input or update process data
 * 
 * @author Emerson dos Santos Paduan
 * @version 1.0
 * @since 09/12/2010
 */

public class formProcess extends javax.swing.JDialog {
    ArrayList<Process> processList;

    /**
     * Create a new formProcess.
     */
    public formProcess(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        processList = null;
        initComponents();
    }

    /**
     * Overload the default JDialog constructor to pass the list of processes being
     * handled in the main interface as a parameter.
     * When the list is changed, it becomes the default list in the main interface,
     * provided it has been properly validated.
     * 
     * @param parent      : Principal form
     * @param modal       : true/false for indicate if is modal or not
     * @param processList : Process list from the principal interface
     */

    public formProcess(java.awt.Frame parent, boolean modal, ArrayList<Process> processList) {
        super(parent, modal);
        initComponents();
        this.processList = processList;
        // fills the table with the processes loaded in the main interface
        if (processList != null && !processList.isEmpty()) {
            for (Process p : processList) {
                tabelaInput.setValueAt(p.getId(), tabelaInput.getRowCount() - 1, 0);
                tabelaInput.setValueAt(p.getArrive(), tabelaInput.getRowCount() - 1, 1);
                tabelaInput.setValueAt(p.getDuration(), tabelaInput.getRowCount() - 1, 2);
                tabelaInput.setValueAt(p.getPriority(), tabelaInput.getRowCount() - 1, 3);
                ((DefaultTableModel) tabelaInput.getModel()).addRow(new Object[4]);
            }
            ((DefaultTableModel) tabelaInput.getModel()).removeRow(tabelaInput.getRowCount() - 1);
        }

    }

    /*
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaInput = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("br.pro.paduan.forms.Principal"); // NOI18N
        setTitle(bundle.getString("DIGITAÇÃO DOS PROCESSOS:")); // NOI18N
        setAlwaysOnTop(true);
        setResizable(false);
        setUndecorated(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12));
        java.util.ResourceBundle bundle1 = java.util.ResourceBundle.getBundle("br.pro.paduan.forms.Principal"); // NOI18N
        jLabel1.setText(bundle1.getString("DIGITAÇÃO DOS PROCESSOS:")); // NOI18N

        jScrollPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jScrollPane1.setName("tblProcessos"); // NOI18N

        tabelaInput.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {
                        { "P1", "", "", "" }
                },
                new String[] {
                        "Processo", "Chegada", "Duração", "Prioridade"
                }) {
            Class[] types = new Class[] {
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[] {
                    false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        tabelaInput.setCellSelectionEnabled(true);
        tabelaInput.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tabelaInput);

        jButton1.setText(bundle1.getString("INSERIR")); // NOI18N
        jButton1.setName("btnInserir"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText(bundle1.getString("REMOVER")); // NOI18N
        jButton2.setName("btnRemover"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText(bundle1.getString("OK")); // NOI18N
        jButton3.setName("btnOK"); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText(bundle1.getString("CANCELAR")); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(53, 53, 53)
                                                .addComponent(jLabel1))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 301,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(20, 20, 20)
                                                .addComponent(jButton3))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                false)
                                                        .addComponent(jButton4,
                                                                javax.swing.GroupLayout.Alignment.LEADING, 0, 0,
                                                                Short.MAX_VALUE)
                                                        .addComponent(jButton1,
                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jButton2,
                                                                javax.swing.GroupLayout.Alignment.LEADING))))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jLabel1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 254,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(64, 64, 64)
                                                .addComponent(jButton1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton4)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton3)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE));

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width - 406) / 2, (screenSize.height - 301) / 2, 406, 301);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton3ActionPerformed
        int c, d, p;

        // All processes have been deleted
        if (tabelaInput.getRowCount() == 0) {
            processList.removeAll(processList);
            this.dispose();
            return;
        }
        ArrayList<Process> lista = new ArrayList<>();
        // loads table data into a list of processes
        for (int i = 0; i < tabelaInput.getRowCount(); i++) {
            try {
                c = Integer.parseInt(tabelaInput.getValueAt(i, 1).toString());
                d = Integer.parseInt(tabelaInput.getValueAt(i, 2).toString());
                p = Integer.parseInt(tabelaInput.getValueAt(i, 3).toString());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                        java.util.ResourceBundle.getBundle("br.pro.paduan.forms.Principal")
                                .getString("DADOS DE ENTRADA INVÁLIDOS."),
                        java.util.ResourceBundle.getBundle("br.pro.paduan.forms.Principal").getString("ERRO"),
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            lista.add(new Process(
                    java.util.ResourceBundle.getBundle("br.pro.paduan.forms.Principal").getString("P") + (i + 1), c, d,
                    p));
        }
        // validate list fetched from interface before making list current
        if (ProcessScheduler.validateProcesses(lista)) {
            if (processList != null && !processList.isEmpty())
                processList.removeAll(processList);

            for (Process pc : lista)
                processList.add(pc);
            this.dispose();
        } else
            JOptionPane.showMessageDialog(this,
                    java.util.ResourceBundle.getBundle("br.pro.paduan.forms.Principal")
                            .getString("DADOS DE ENTRADA INVÁLIDOS."),
                    java.util.ResourceBundle.getBundle("br.pro.paduan.forms.Principal").getString("ERRO"),
                    JOptionPane.ERROR_MESSAGE);
    }// GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
        ((DefaultTableModel) tabelaInput.getModel()).addRow(new Object[4]);
        tabelaInput.setValueAt(
                java.util.ResourceBundle.getBundle("br.pro.paduan.forms.Principal").getString("P")
                        + (tabelaInput.getRowCount()),
                tabelaInput.getRowCount() - 1, 0);
    }// GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton4ActionPerformed
        this.dispose();
    }// GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton2ActionPerformed
        if (tabelaInput.getSelectedRow() != -1)
            ((DefaultTableModel) tabelaInput.getModel()).removeRow(tabelaInput.getSelectedRow());
    }// GEN-LAST:event_jButton2ActionPerformed

    /*
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                formProcess dialog = new formProcess(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaInput;
    // End of variables declaration//GEN-END:variables

}
