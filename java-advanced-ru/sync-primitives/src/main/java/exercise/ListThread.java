package exercise;

// BEGIN
public class ListThread extends Thread {
    private SafetyList safetyList;
    private int y = 0;

    public ListThread(SafetyList safetyList) {
        this.safetyList = safetyList;
    }

    @Override
    public void run() {
        for (var x = 0; x < 1000; x++) {
            safetyList.add(y);
            y++;
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
// END
