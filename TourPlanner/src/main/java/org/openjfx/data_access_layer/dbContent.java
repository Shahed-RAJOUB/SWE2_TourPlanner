package org.openjfx.data_access_layer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import lombok.Setter;
import lombok.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
//    public boolean InsertLog(Logs log) throws SQLException {
//
//        configureConnection();
//        String query1 = "INSERT INTO LOGS ( tourtitle , tourdate , tourdist) VALUES (?, ?, ?)";
//        PreparedStatement statement = c.prepareStatement(query1);
//        statement.setString(1, log.getTourTitle() );
//        statement.setString(2, log.getTourDate().toString());
//        statement.setInt(3, log.getTourDist());
//        statement.executeUpdate();
//        c.commit();
//        closeConnection(statement);
//        return true;
//       }
//
//        private void closeConnection(PreparedStatement statement) throws SQLException  {
//            statement.close();
//            c.close();
//        }

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
                logs = new Logs(rs.getInt("id") , rs.getString("tourtitle") , rs.getString("tourdate") , rs.getInt("tourdist") );
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
}
