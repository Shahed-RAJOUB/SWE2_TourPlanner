package org.rajoub.data_access_layer;

import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO_Base {

    private static DbContent dbContent;
    protected TourDAO tourDAO;
    protected LogDAO logDAO;

    @BeforeEach
    void setUp() {
        tourDAO = new TourDAO(dbContent);
        logDAO = new LogDAO(dbContent);
    }
    // to start these tests i should start Docker first using test containers
    // adding postgress image in Docker Container
    @Container
    private static final PostgreSQLContainer postgres = new PostgreSQLContainer("postgres:13.1-alpine")
            .withDatabaseName("test")
            .withUsername("test")
            .withPassword("test");

    @BeforeAll
    @SneakyThrows
    static void init() {
        String url = String.format("jdbc:postgresql://%s:%d/%s", postgres.getHost(), postgres.getFirstMappedPort(), postgres.getDatabaseName());
        Connection connection = DriverManager.getConnection(url, postgres.getUsername(), postgres.getPassword());
        connection.setAutoCommit(false);
        dbContent = new DbContent();
        dbContent.setC(connection);
        dbContent.getC().prepareCall(script).execute();
    }

    @AfterEach
    void tearDown() throws SQLException {
        dbContent.getC().prepareCall("DELETE FROM \"Tours\"").execute();
        dbContent.getC().prepareCall("DELETE FROM \"Logs\"").execute();
    }

    // My Schema script
    private static String script = "create table if not exists \"Tours\"\n" +
            "(\n" +
            "\tid serial not null\n" +
            "\t\tconstraint tours_pk\n" +
            "\t\t\tprimary key,\n" +
            "\t\"tourName\" varchar not null,\n" +
            "\t\"from\" varchar,\n" +
            "\t\"to\" varchar\n" +
            ");\n" +
            "\n" +
            "\n" +
            "create table if not exists \"Logs\"\n" +
            "(\n" +
            "\tid serial not null\n" +
            "\t\tconstraint logs_pk\n" +
            "\t\t\tprimary key,\n" +
            "\tdate varchar,\n" +
            "\tduration double precision,\n" +
            "\tdestination double precision,\n" +
            "\tcalories double precision,\n" +
            "\turl varchar,\n" +
            "\ttour_name varchar,\n" +
            "\tratings varchar\n" +
            ");\n" +
            "\n" +
            "\n";

}
