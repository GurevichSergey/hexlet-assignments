package exercise;

import java.util.Arrays;

// BEGIN
public class MinThread extends Thread {

    private int[] numbers;
    private int minNumber;
    public MinThread(int [] numbers) {
        this.numbers = numbers;
    }

    @Override
    public void  run() {
        getMinNumber();
    }

    public int getMinNumber() {
        return minNumber = Arrays.stream(numbers).min().orElse(1);
    }
}
// END
