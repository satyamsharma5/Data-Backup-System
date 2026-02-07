import java.io.*;
import java.util.*;

public class CSVHandler {
    public static void exportToCSV(BackupData data, String filePath) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {
            pw.println("Key,Value,Type");
            for (Map.Entry<String, Object> entry : data.getDataItems().entrySet()) {
                pw.println(entry.getKey() + "," + entry.getValue() + "," + entry.getValue().getClass().getSimpleName());
            }
        }
    }

    public static Map<String, Object> importFromCSV(String filePath) throws IOException {
        Map<String, Object> dataItems = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line; br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String key = parts[0];
                String value = parts[1];
                String type = parts[2];
                Object parsedValue = parseValue(value, type);
                dataItems.put(key, parsedValue);
            }
        }
        return dataItems;
    }

    // FIX: made public so Main.java can use it
    public static Object parseValue(String value, String type) {
        switch (type) {
            case "Integer": return Integer.parseInt(value);
            case "Double": return Double.parseDouble(value);
            case "Boolean": return Boolean.parseBoolean(value);
            default: return value;
        }
    }
}
