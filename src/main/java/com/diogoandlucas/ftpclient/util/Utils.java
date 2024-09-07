package com.diogoandlucas.ftpclient.util;

import java.util.List;

public class Utils {

    private final static List<String> BYTES_SUFFIXES = List.of("B", "KB", "MB", "GB", "TB");

    public static String formatBytes(double bytes) {

        int i = 0;

        while (bytes >= 1000) {
            bytes /= 1000;
            i++;
        }

        return String.format("%.2f %s", bytes, BYTES_SUFFIXES.get(i));
    }

    public static String formatTime(long millis) {

        long totalSeconds = millis / 1000;
        long days = totalSeconds / (24 * 60 * 60);
        long hours = (totalSeconds % (24 * 60 * 60)) / (60 * 60);
        long minutes = (totalSeconds % (60 * 60)) / 60;
        long seconds = totalSeconds % 60;

        StringBuilder result = new StringBuilder();

        if (days > 0)
            result.append(days).append("d ");

        if (hours > 0)
            result.append(hours).append("h ");

        if (minutes > 0)
            result.append(minutes).append("m ");

        result.append(seconds).append("s ");

        return result.toString();
    }

}
