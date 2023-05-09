package exercise;



class App {

    public static void main(String[] args) {
        // BEGIN
        SafetyList safetyList1 = new SafetyList();

        ListThread listThread1 = new ListThread(safetyList1);
        ListThread listThread2 = new ListThread(safetyList1);

        listThread2.start();
        listThread1.start();

        try {
            listThread2.join();
            listThread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Size: " + safetyList1.getSize());
        // END
    }
}

