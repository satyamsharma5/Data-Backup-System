import java.io.Serializable;
import java.util.*;

public class BackupData implements Serializable {
    private static final long serialVersionUID = 1L;
    private UUID backupId;
    private Date timestamp;
    private String description;
    private Map<String, Object> dataItems;

    public BackupData(String description, Map<String, Object> dataItems) {
        this.backupId = UUID.randomUUID();
        this.timestamp = new Date();
        this.description = description;
        this.dataItems = dataItems;
    }

    public UUID getBackupId() { return backupId; }
    public Date getTimestamp() { return timestamp; }
    public String getDescription() { return description; }
    public Map<String, Object> getDataItems() { return dataItems; }

    @Override
    public String toString() {
        return "Backup ID: " + backupId + "\nTime: " + timestamp +
               "\nDescription: " + description + "\nData Items: " + dataItems.size();
    }
}