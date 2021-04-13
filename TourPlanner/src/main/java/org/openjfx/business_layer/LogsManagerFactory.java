package org.openjfx.business_layer;

public class LogsManagerFactory {
    private static LogsManager manager;

    // Singelton prenzip
    public static LogsManager getLogsManager() {
        if (manager == null) {
            manager = new LogsManagerImpl();
        }
        return manager;
    }
}
