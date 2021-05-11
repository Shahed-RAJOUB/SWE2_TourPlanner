package org.rajoub.data_access_layer;

import lombok.Data;
import lombok.extern.log4j.Log4j;
import org.rajoub.config.DatabaseConfig;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;

@Data
@Component
@Log4j
public class DbContent {

    private Connection c;
    public DbContent() {
        try {
            DatabaseConfig data = DatabaseConfig.getInstance();
            c = DriverManager.getConnection(data.getUrl(), data.getUserName(), data.getPassword());
            c.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}
