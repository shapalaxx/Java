package my.contacteditor;

public class IntegralThread extends Thread {
    private double lower, upper, range;
    private double result;

    public IntegralThread(double lower, double upper, double range) {
        this.lower = lower;
        this.upper = upper;
        this.range = range;
    }

    @Override
    public void run() {
        double start = lower, h, sum = 0;

        while (start < upper) {
            h = Math.min(range, upper - start);
            sum += h * (Math.exp(start)/start + Math.exp(start + h)/(start + h))/2;
            start += h;
        }

        result = sum;
    }

    public double getResult() {
        return result;
    }
}