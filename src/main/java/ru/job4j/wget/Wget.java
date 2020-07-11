package ru.job4j.wget;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;

/**
 * 4. Скачивание файла с ограничением. [#318309]
 */
public class Wget {
    /**
     * Download file with speed limit.
     *
     * @param args 0: url.
     *             1: speed kb.
     */
    public static void main(@NotNull String[] args) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DownloadFromURL download = new DownloadFromURL();
        download.downloadFile(args[0], args[1], out);
    }
}
