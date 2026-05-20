package my.contacteditor;

import java.io.Serializable;

public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    public enum Type {
        READY,
        TASK,
        PARTIAL_RESULT,
        SHUTDOWN
    }

    private Type type;
    private double start;
    private double end;
    private double step;
    private double result;
    private int taskId;
    private int tableRow; // сохраняем строку таблицы на момент отправки

    public Message(Type type) {
        this.type = type;
    }

    public Type getType() { return type; }

    public double getStart() { return start; }
    public void setStart(double start) { this.start = start; }

    public double getEnd() { return end; }
    public void setEnd(double end) { this.end = end; }

    public double getStep() { return step; }
    public void setStep(double step) { this.step = step; }

    public double getResult() { return result; }
    public void setResult(double result) { this.result = result; }

    public int getTaskId() { return taskId; }
    public void setTaskId(int taskId) { this.taskId = taskId; }

    public int getTableRow() { return tableRow; }
    public void setTableRow(int tableRow) { this.tableRow = tableRow; }
}