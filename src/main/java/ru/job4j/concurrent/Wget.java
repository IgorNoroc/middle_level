package ru.job4j.concurrent;

/**
 * 1.2. Режим ожидания. [#318311].
 */
public class Wget {
    public static void main(String[] args) {
        Thread thread = new Thread(
                () -> {
                    for (int i = 0; i < 100; i++) {
                        System.out.print(String.format("\rLoading %d%%", i));
                        try {
                            Thread.sleep(1000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        thread.start();
    }
}
