package org.rajoub.config;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DatabaseConfigTest {

    @Test
    void getInstance() {
       Assertions.assertNotNull(DatabaseConfig.getInstance());
    }
}
