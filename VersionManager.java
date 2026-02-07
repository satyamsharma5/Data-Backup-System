import java.util.*;

public class VersionManager {
    private static List<BackupData> versions = new ArrayList<>();

    public static void addVersion(BackupData data) {
        versions.add(data);
    }

    public static List<BackupData> listVersions() {
        return versions;
    }

    public static void cleanupOldBackups(int days) {
        Date cutoff = new Date(System.currentTimeMillis() - days * 86400000L);
        versions.removeIf(v -> v.getTimestamp().before(cutoff));
    }
}