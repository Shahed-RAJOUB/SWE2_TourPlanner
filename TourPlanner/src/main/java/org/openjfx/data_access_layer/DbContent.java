package org.openjfx.data_access_layer;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.openjfx.config.DatabaseConfig;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;

@Data
@Component
public class DbContent {
    private Connection c;
    public DbContent() {
        try {
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/TourLogs", DatabaseConfig.getInstance().getUserName(), DatabaseConfig.getInstance().getPassword());
            c.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}
