package ru.job4j.wget;

import java.io.BufferedInputStream;
import java.io.OutputStream;
import java.net.URL;

/**
 * 4. Скачивание файла с ограничением. [#318309]
 */
public class DownloadFromURL {
    /**
     * Download file from url with speed limit.
     *
     * @param url  source url.
     * @param speed speed limit.
     * @param out   destination file.
     */
    public void downloadFile(String url, String speed, OutputStream out) {
        try (BufferedInputStream reader = new BufferedInputStream(new URL(url).openStream())) {
            byte[] buffer = new byte[Integer.parseInt(speed) * 1000];
            int i = 0;
            while (i != -1) {
                long start = System.currentTimeMillis();
                i = reader.read(buffer, 0, Integer.parseInt(speed) * 1000);
                long finish = System.currentTimeMillis() - start;
                out.write(buffer);
                speedLimit(finish);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Checking speed.
     * If the speed per second is higher, the thread sleeps.
     *
     * @param time time.
     */
    private void speedLimit(long time) {
        if (time < 1000) {
            try {
                Thread.sleep(1000 - time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
