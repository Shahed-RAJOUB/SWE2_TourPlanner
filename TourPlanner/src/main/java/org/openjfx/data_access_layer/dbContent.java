package org.openjfx.data_access_layer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import lombok.Setter;
import lombok.Data;

import java.sql.*;

@Data
@Getter
@Setter
public class dbContent {
    public static Connection c;

    public void configureConnection()  {
        try {
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/TourLogs" , "postgres" , "if19b166");
            c.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public ObservableList<Logs> getlogs() {

        ObservableList<Logs> logsList = FXCollections.observableArrayList();
        configureConnection();
        String query = "SELECT * FROM Logs";
        PreparedStatement statement;
        ResultSet rs;
        try{
            statement = c.prepareStatement(query);
            rs = statement.executeQuery();
            Logs logs;
            while(rs.next()){
                logs = new Logs(rs.getInt("id") , rs.getString("tourtitle") , rs.getString("tourdate") , rs.getString("tourdist") );
                logsList.add(logs);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return logsList;
    }

    /**
     *
     * @param title
     * @param date
     * @param dist
     * @throws SQLException
     */
    public void insertNewLog(String title , String date , String dist) throws SQLException {
        configureConnection();
        String query1 = "INSERT INTO LOGS ( tourtitle , tourdate , tourdist) VALUES (?, ?, ?)";
        PreparedStatement statement = c.prepareStatement(query1);
        statement.setString(1,title );
        statement.setString(2,date);
        statement.setString(3, dist);
        statement.executeUpdate();
        c.commit();
        statement.close();
    }
}
