package ru.job4j.concurrent;

/**
 * 3. Прерывание нити [#318314]
 */
public class ConsoleProgress implements Runnable {
    private final String[] loading = {".", "\\", "|", "/", "."};

    @Override
    public void run() {
        int count = 0;
        while (!Thread.currentThread().isInterrupted()) {
            if (count == loading.length) {
                count = 0;
            }
            System.out.print(String.format("\rLoading %s", loading[count]));
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
            count++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new ConsoleProgress());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}
