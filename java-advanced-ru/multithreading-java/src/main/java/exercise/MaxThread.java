package exercise;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// BEGIN
public class MaxThread extends Thread {

    private int[] numbers;
    private int maxNumber;
    public MaxThread(int [] numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {
        maxNumber = Arrays.stream(numbers)
                .max()
                .orElse(0);
    }

    public int getMaxNumber() {
        return maxNumber;
    }
}
// END
