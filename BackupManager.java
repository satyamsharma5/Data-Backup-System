import java.io.*;
import java.util.zip.GZIPOutputStream;
import java.util.zip.GZIPInputStream;

public class BackupManager {
    public static void saveBackup(BackupData data, String filePath) throws Exception {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(data);
        }
        CompressionUtils.compressFile(filePath, filePath + ".gz");
        CSVHandler.exportToCSV(data, filePath.replace(".dat", ".csv"));
    }

    public static BackupData restoreBackup(String filePath) throws Exception {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (BackupData) ois.readObject();
        }
    }

    public static BackupData restoreCompressed(String filePath) throws Exception {
        try (ObjectInputStream ois = new ObjectInputStream(new GZIPInputStream(new FileInputStream(filePath)))) {
            return (BackupData) ois.readObject();
        }
    }
}