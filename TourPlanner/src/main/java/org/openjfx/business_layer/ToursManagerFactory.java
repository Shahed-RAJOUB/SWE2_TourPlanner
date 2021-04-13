package org.openjfx.business_layer;

public class ToursManagerFactory {
    private static ToursManager manager;
    // Singelton prenzip
    public static ToursManager getToursManager() {
        if (manager == null) {
            manager = new ToursManagerImpl();
        }
        return manager;
    }
}
