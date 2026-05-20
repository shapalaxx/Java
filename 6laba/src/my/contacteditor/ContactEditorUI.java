/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.contacteditor;

import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.util.LinkedList;
import java.io.*;
import javax.swing.JFileChooser;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
/**
 *
 * @author ававы
 */
public class ContactEditorUI extends javax.swing.JFrame {
                                    
LinkedList<RecIntegral> LinkedInteg = new LinkedList<>();
private ServerSocket serverSocket;
private List<ClientHandler> clients = new ArrayList<>();
private ExecutorService serverThreadPool;
private volatile boolean isServerRunning = false;
private int currentTaskId = 0;

private static class TaskState {
    double sum = 0;
    int received = 0;
    int tableRow;
    int totalParts;
    TaskState(int tableRow, int totalParts) {
        this.tableRow = tableRow;
        this.totalParts = totalParts;
    }
}
private final Map<Integer, TaskState> taskMap = new ConcurrentHashMap<>();

    public ContactEditorUI() {
        initComponents();   
        serverThreadPool = Executors.newCachedThreadPool();
        startServer(55555);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButtonResult = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jButtonAdd = new javax.swing.JButton();
        jTextFieldUpperLimit = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldRange = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldLowerLimit = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jButtonAdd1 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButtonAdd3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 102, 102));
        setForeground(java.awt.Color.orange);

        jPanel1.setBackground(new java.awt.Color(102, 255, 255));

        jButtonResult.setBackground(new java.awt.Color(153, 153, 255));
        jButtonResult.setText("Resault");
        jButtonResult.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResultActionPerformed(evt);
            }
        });

        jButtonDelete.setBackground(new java.awt.Color(153, 153, 255));
        jButtonDelete.setText("Delete");
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });

        jButtonAdd.setBackground(new java.awt.Color(153, 153, 255));
        jButtonAdd.setText("Add");
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });

        jLabel3.setText("Range");

        jLabel1.setText("Lower limit");

        jLabel2.setText("Upper limit");

        jTable1.setBackground(new java.awt.Color(102, 255, 255));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Lower Limit", "Upper Limit", "Range", "Resault"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("e(x/x)");

        jButtonAdd1.setBackground(new java.awt.Color(153, 153, 255));
        jButtonAdd1.setText("Clear");
        jButtonAdd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdd1ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(153, 153, 153));
        jButton1.setText("Save TXT");
        jButton1.setToolTipText("");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(153, 153, 153));
        jButton2.setText("Load TXT");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(204, 204, 204));
        jButton3.setText("Load BIN");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(204, 204, 204));
        jButton4.setText("Save BIN");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButtonAdd3.setBackground(new java.awt.Color(153, 153, 255));
        jButtonAdd3.setText("Fill");
        jButtonAdd3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdd3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 669, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextFieldUpperLimit, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel1)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextFieldLowerLimit, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                                .addComponent(jTextFieldRange))
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(41, 41, 41)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jButtonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButtonAdd3, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jButtonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButtonAdd1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton2)
                                    .addComponent(jButton3))
                                .addGap(94, 94, 94))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButtonResult)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldLowerLimit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonAdd)
                            .addComponent(jButtonAdd1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addGap(2, 2, 2)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldUpperLimit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonDelete)
                            .addComponent(jButtonAdd3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldRange, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonResult))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton4)
                            .addComponent(jButton3))))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
       
        

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        try {
        double LowerLimit = Double.parseDouble(jTextFieldLowerLimit.getText());
        double UpperLimit = Double.parseDouble(jTextFieldUpperLimit.getText());
        double Range = Double.parseDouble(jTextFieldRange.getText());

        RecIntegral rec = new RecIntegral(LowerLimit, UpperLimit, Range);

        LinkedInteg.add(rec);

        DefaultTableModel tModel = (DefaultTableModel) jTable1.getModel();
        tModel.addRow(new Object[] {
            rec.getLowerLimit(),
            rec.getUpperLimit(),
            rec.getRange()
        });

        // очистка полей
        jTextFieldLowerLimit.setText("");
        jTextFieldUpperLimit.setText("");
        jTextFieldRange.setText("");

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Введите корректные числовые значения!");
    } catch (InvalidRangeException e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    }

    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
      DefaultTableModel tModel = (DefaultTableModel) jTable1.getModel();
        
        int rowNum = jTable1.getSelectedRow();
        
        if (rowNum == -1){
            JOptionPane.showMessageDialog(null, "Выберите строку");
        }else{
            tModel.removeRow(rowNum);
            LinkedInteg.remove(rowNum);
            //arrInteg.remove(rowNum);
        }
        
    }//GEN-LAST:event_jButtonDeleteActionPerformed
    private void startServer(int port) {

    if (isServerRunning) return;

    try {

        serverSocket = new ServerSocket(port);

        isServerRunning = true;

        JOptionPane.showMessageDialog(this,
                "Server started on port " + port);

        new Thread(() -> {

            while (isServerRunning) {

                try {

                    Socket clientSocket = serverSocket.accept();

                    ClientHandler handler =
        new ClientHandler(clientSocket,
                clients.size() + 1,
                this);

                    clients.add(handler);

                    serverThreadPool.submit(handler);

                    System.out.println(
                            "Client connected "
                                    + clients.size());

                } catch (IOException e) {

                    if (isServerRunning)
                        e.printStackTrace();
                }
            }

        }).start();

    } catch (IOException e) {

        JOptionPane.showMessageDialog(this,
                "Server start error");
    }
}
    private void jButtonResultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResultActionPerformed
        DefaultTableModel tModel = (DefaultTableModel) jTable1.getModel();
   int rowNum = jTable1.getSelectedRow();

    if (rowNum == -1) {
        JOptionPane.showMessageDialog(null, "Выберите строку");
        return;
    }

    try {
        double LowerLimit = Double.parseDouble(tModel.getValueAt(rowNum, 0).toString());
        double UpperLimit = Double.parseDouble(tModel.getValueAt(rowNum, 1).toString());
        double Range = Double.parseDouble(tModel.getValueAt(rowNum, 2).toString());

        String[] options = {"Локально", "По сети"};

        int choice = JOptionPane.showOptionDialog(
                this,
                "Выберите режим вычисления",
                "Режим",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]);

        // локальный расчет
        if (choice == 0) {

            int numThreads = 10;
    long totalSteps = (long) Math.ceil((UpperLimit - LowerLimit) / Range);
    long stepsPerThread = totalSteps / numThreads;

    IntegralThread[] threads = new IntegralThread[numThreads];

    for (int i = 0; i < numThreads; i++) {
        double from = LowerLimit + i * stepsPerThread * Range;
        double to = (i == numThreads - 1) ? UpperLimit : LowerLimit + (i + 1) * stepsPerThread * Range;
        threads[i] = new IntegralThread(from, to, Range);
        threads[i].start();
    }

    double total = 0;
    for (IntegralThread t : threads) {
        try { t.join(); total += t.getResult(); }
        catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    tModel.setValueAt(total, rowNum, 3);
    LinkedInteg.get(rowNum).setResult(total);
} 
      //сет расч
        else if (choice == 1) {

            if (clients.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No connected clients");
                return;
            }

            distributeToClients(LowerLimit, UpperLimit, Range, rowNum);

            JOptionPane.showMessageDialog(this, "Задача отправлена клиентам");

         
        }
    

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Ошибка: в таблице некорректные данные!");
        }

    }//GEN-LAST:event_jButtonResultActionPerformed
    private void saveToTextFile() {
    JFileChooser chooser = new JFileChooser();

    if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
        try (FileWriter writer = new FileWriter(chooser.getSelectedFile())) {

            for (RecIntegral rec : LinkedInteg) {
                writer.write(rec.getLowerLimit() + ";" +
                             rec.getUpperLimit() + ";" +
                             rec.getRange() + ";" +
                             rec.getResult() + "\n");
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Ошибка сохранения!");
        }
    }
}
    private void loadFromTextFile() {
    JFileChooser chooser = new JFileChooser();

    if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
        try (BufferedReader reader = new BufferedReader(
                new FileReader(chooser.getSelectedFile()))) {
            DefaultTableModel tModel = (DefaultTableModel) jTable1.getModel();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");

                double lower = Double.parseDouble(parts[0]);
                double upper = Double.parseDouble(parts[1]);
                double range = Double.parseDouble(parts[2]);
                double result = Double.parseDouble(parts[3]);

                RecIntegral rec = new RecIntegral(lower, upper, range);
                rec.setResult(result);

                LinkedInteg.add(rec);
                tModel.addRow(new Object[]{lower, upper, range, result});
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ошибка загрузки!");
        }
    }
}
    public synchronized void handleClientResult(int taskId, double result, int tableRow) {
TaskState state = taskMap.get(taskId);
    if (state == null) return;

    boolean done;
    double finalSum;
    int finalRow;

    synchronized (state) {
        state.sum += result;
        state.received++;
        done = (state.received == state.totalParts);
        finalSum = state.sum;
        finalRow = state.tableRow;
    }

    if (done) {
        taskMap.remove(taskId);
        final double displaySum = finalSum;
        final int displayRow = finalRow;

        javax.swing.SwingUtilities.invokeLater(() -> {
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            if (displayRow >= 0 && displayRow < model.getRowCount()) {
                model.setValueAt(displaySum, displayRow, 3);
                if (displayRow < LinkedInteg.size()) {
                    LinkedInteg.get(displayRow).setResult(displaySum);
                }
            }
            JOptionPane.showMessageDialog(ContactEditorUI.this,
                    "Сетевой расчёт завершён: " + displaySum);
        });
    }
}
    private void saveToBinaryFile() {
    JFileChooser chooser = new JFileChooser();

    if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(chooser.getSelectedFile()))) {

            out.writeObject(LinkedInteg);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Ошибка сохранения!");
        }
    }
}
    private void loadFromBinaryFile() {
    JFileChooser chooser = new JFileChooser();

    if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
        try (ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(chooser.getSelectedFile()))) {

            LinkedList<RecIntegral> list =
                    (LinkedList<RecIntegral>) in.readObject();

            DefaultTableModel tModel = (DefaultTableModel) jTable1.getModel();
          

            for (RecIntegral rec : list) {
                LinkedInteg.add(rec);
                tModel.addRow(new Object[]{
                    rec.getLowerLimit(),
                    rec.getUpperLimit(),
                    rec.getRange(),
                    rec.getResult()
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ошибка загрузки!");
        }
    }
}
    private void jButtonAdd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdd1ActionPerformed
        DefaultTableModel tModel = (DefaultTableModel) jTable1.getModel();
        tModel.setNumRows(0);
    }//GEN-LAST:event_jButtonAdd1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        saveToTextFile();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       loadFromTextFile();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        saveToBinaryFile();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        loadFromBinaryFile();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButtonAdd3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdd3ActionPerformed

    }//GEN-LAST:event_jButtonAdd3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ContactEditorUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ContactEditorUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ContactEditorUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ContactEditorUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ContactEditorUI().setVisible(true);
            }
        });
    }
    public double CalcIntegral(double LowerLimit, double UpperLimit, double Range){
        
        double start, h, sumS = 0;
        
        start = LowerLimit;
        
        do{
            h = Math.min(Range, (UpperLimit-start));
            sumS += h * (Math.exp(start)/start + Math.exp(start + h)/(start + h))/2;
            start += h;
        }while((start) < UpperLimit);
        
        return sumS;
    }
    private void distributeToClients(double a, double b, double step, int rowNum) {

    if (clients.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Нет подключенных клиентов");
        return;
    }

    // Считаем общее количество шагов
    long totalSteps = (long) Math.ceil((b - a) / step);
    int n = clients.size();

    int taskId = currentTaskId++;
    taskMap.put(taskId, new TaskState(rowNum, n));

    long stepsPerClient = totalSteps / n;

    for (int i = 0; i < n; i++) {

        // Границы считаем кратно step — не делим интервал геометрически
        double start = a + i * stepsPerClient * step;
        double end;

        if (i == n - 1) {
            end = b; // последний клиент добирает остаток
        } else {
            end = a + (i + 1) * stepsPerClient * step;
        }

        Message task = new Message(Message.Type.TASK);
        task.setTaskId(taskId);
        task.setStart(start);
        task.setEnd(end);
        task.setStep(step);
        task.setTableRow(rowNum);

        clients.get(i).sendTask(task);
    }
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonAdd1;
    private javax.swing.JButton jButtonAdd3;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonResult;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldLowerLimit;
    private javax.swing.JTextField jTextFieldRange;
    private javax.swing.JTextField jTextFieldUpperLimit;
    // End of variables declaration//GEN-END:variables
}
