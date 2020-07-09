package ru.job4j.wget;

import java.io.ByteArrayOutputStream;

/**
 * 4. Скачивание файла с ограничением. [#318309]
 */
public class Wget {
    private static final ByteArrayOutputStream OUT = new ByteArrayOutputStream();

    /**
     * Download file with speed limit.
     *
     * @param args 0: url.
     *             1: speed kb.
     */
    public static void main(String[] args) {
        DownloadFromURL.downloadFile(args[0], args[1], OUT);
    }
}
