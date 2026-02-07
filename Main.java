import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String backupDir = "backups";
        new java.io.File(backupDir).mkdirs();

        while (true) {
            System.out.println("=== DATA BACKUP SYSTEM ===");
            System.out.println("1. Create New Backup");
            System.out.println("2. Restore from Binary");
            System.out.println("3. Restore from Compressed");
            System.out.println("4. Import from CSV");
            System.out.println("5. List All Versions");
            System.out.println("6. Cleanup Old Backups");
            System.out.println("7. Backup Statistics");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt(); sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter backup description: ");
                    String desc = sc.nextLine();
                    Map<String, Object> items = new HashMap<>();
                    while (true) {
                        System.out.print("Enter key: ");
                        String key = sc.nextLine();
                        System.out.print("Enter value: ");
                        String val = sc.nextLine();
                        System.out.print("Enter type (String/Integer/Double/Boolean): ");
                        String type = sc.nextLine();
                        items.put(key, CSVHandler.parseValue(val, type));
                        System.out.print("Add more items? (y/n): ");
                        if (sc.nextLine().equalsIgnoreCase("n")) break;
                    }
                    BackupData data = new BackupData(desc, items);
                    String filePath = backupDir + "/backup_" + System.currentTimeMillis() + ".dat";
                    BackupManager.saveBackup(data, filePath);
                    VersionManager.addVersion(data);
                    System.out.println("Backup created successfully!");
                    break;

                case 2:
                    System.out.print("Enter binary file path: ");
                    String binPath = sc.nextLine();
                    BackupData restored = BackupManager.restoreBackup(binPath);
                    System.out.println("✅ RESTORED BACKUP:\n" + restored);
                    break;

                case 3:
                    System.out.print("Enter compressed file path: ");
                    String gzPath = sc.nextLine();
                    BackupData restoredGz = BackupManager.restoreCompressed(gzPath);
                    System.out.println("✅ RESTORED BACKUP:\n" + restoredGz);
                    break;

                case 4:
                    System.out.print("Enter CSV file path: ");
                    String csvPath = sc.nextLine();
                    Map<String, Object> imported = CSVHandler.importFromCSV(csvPath);
                    System.out.println("✅ Imported Data: " + imported);
                    break;

                case 5:
                    System.out.println("=== ALL BACKUP VERSIONS ===");
                    List<BackupData> versions = VersionManager.listVersions();
                    int i = 1;
                    for (BackupData v : versions) {
                        System.out.println("Version " + i++ + ":\n" + v + "\n");
                    }
                    System.out.println("Total Versions: " + versions.size());
                    break;

                case 6:
                    System.out.print("Enter days to keep backups: ");
                    int days = sc.nextInt(); sc.nextLine();
                    VersionManager.cleanupOldBackups(days);
                    System.out.println("✅ Cleanup completed!");
                    break;

                case 7:
                    StatisticsManager.showStatistics(backupDir);
                    break;

                case 8:
                    System.out.println("Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}