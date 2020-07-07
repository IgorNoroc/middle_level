package ru.job4j.wget;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.net.URL;

/**
 * 4. Скачивание файла с ограничением. [#318309]
 */
public class Wget {
    private static final ByteArrayOutputStream OUT = new ByteArrayOutputStream();

    public static void main(String[] args) {
        try (BufferedInputStream reader = new BufferedInputStream(new URL(args[0]).openStream())) {
            byte[] buffer = new byte[Integer.parseInt(args[1]) * 1000];
            int i = 0;
            while (i != -1) {
                long start = System.currentTimeMillis();
                i = reader.read(buffer, 0, Integer.parseInt(args[1]) * 1000);
                long finish = System.currentTimeMillis() - start;
                OUT.write(buffer);
                if ((finish) < 1000) {
                    Thread.sleep(1000 - finish);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
