package my.contacteditor;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IntegralClient {

    private static final int NUM_THREADS = 4;

    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    private ExecutorService threadPool;
    
    private double calcDirect(double start, double end, double step) {
    double cur = start, h, sum = 0;
    while (cur < end) {
        h = Math.min(step, end - cur);
        sum += h * (Math.tan(cur) + Math.tan(cur + h)) / 2;
        cur += h;
    }
    return sum;
}
    public IntegralClient(String host, int port) throws IOException {

        socket = new Socket(host, port);

        out = new ObjectOutputStream(socket.getOutputStream());
        out.flush();

        in = new ObjectInputStream(socket.getInputStream());

        threadPool = Executors.newFixedThreadPool(NUM_THREADS);

        System.out.println("Client connected to " + host + ":" + port);
    }

    public void start() {

        try {
            while (true) {

                Message msg = (Message) in.readObject();

                if (msg.getType() == Message.Type.TASK) {

                    // Сохраняем данные задачи — lambda не может захватить изменяемые переменные
                    final double taskStart = msg.getStart();
                    final double taskEnd   = msg.getEnd();
                    final double taskStep  = msg.getStep();
                    final int    taskId    = msg.getTaskId();
                    final int    tableRow  = msg.getTableRow();

                    threadPool.submit(() -> {

                        // Делим подотрезок ещё на NUM_THREADS нитей (как в предыдущей лабе)
                        double total = calcDirect(taskStart, taskEnd, taskStep);

                        Message resultMsg = new Message(Message.Type.PARTIAL_RESULT);
                        resultMsg.setTaskId(taskId);
                        resultMsg.setTableRow(tableRow); 
                        resultMsg.setResult(total);

                        try {
                            synchronized (out) {
                                out.writeObject(resultMsg);
                                out.flush();
                            }
                        } catch (IOException e) {
                            System.out.println("Failed to send result to server");
                        }
                    });

                } else if (msg.getType() == Message.Type.SHUTDOWN) {
                    System.out.println("Server shutdown received");
                    break;
                }
            }

        } catch (Exception e) {
            System.out.println("Client error: " + e.getMessage());
        } finally {
            stop();
        }
    }

    /**
     * Разбивает отрезок [start, end] на NUM_THREADS подотрезков
     * и считает интеграл в нескольких нитях (как в прошлой лабе).
     */
    private double calculateParallel(double start, double end, double step) {

        IntegralThread[] threads = new IntegralThread[NUM_THREADS];
        double interval = (end - start) / NUM_THREADS;

        for (int i = 0; i < NUM_THREADS; i++) {
            double from = start + i * interval;
            double to   = (i == NUM_THREADS - 1) ? end : from + interval;
            threads[i] = new IntegralThread(from, to, step);
            threads[i].start();
        }

        double total = 0;
        for (int i = 0; i < NUM_THREADS; i++) {
            try {
                threads[i].join();
                total += threads[i].getResult();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return total;
    }

    public void stop() {

        try {
            threadPool.shutdown();

            if (in != null) in.close();
            if (out != null) out.close();
            if (socket != null) socket.close();

            System.out.println("Client stopped");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        try {
            new IntegralClient("localhost", 55555).start();
        } catch (IOException e) {
            System.out.println("Cannot connect to server: " + e.getMessage());
        }
    }
}