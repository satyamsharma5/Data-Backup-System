import java.io.File;

public class StatisticsManager {
    public static void showStatistics(String backupDir) {
        File dir = new File(backupDir);
        File[] files = dir.listFiles();
        if (files == null) return;

        int datCount = 0, gzCount = 0, csvCount = 0;
        long totalSize = 0;

        for (File f : files) {
            totalSize += f.length();
            if (f.getName().endsWith(".dat")) datCount++;
            else if (f.getName().endsWith(".gz")) gzCount++;
            else if (f.getName().endsWith(".csv")) csvCount++;
        }

        System.out.println("=== BACKUP STATISTICS ===");
        System.out.println("Total Backups: " + datCount);
        System.out.println(".dat files: " + datCount);
        System.out.println(".dat.gz files: " + gzCount);
        System.out.println(".csv files: " + csvCount);
        System.out.println("Total Size: " + (totalSize / 1024.0) + " KB");
    }
}