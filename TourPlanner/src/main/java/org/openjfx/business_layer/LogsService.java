package org.openjfx.business_layer;

import lombok.RequiredArgsConstructor;
import org.openjfx.data_access_layer.LogDAO;
import org.openjfx.view_model.Log;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class LogsService  {
    private final LogDAO logDAO;

    public List<Log> GetLogs() {
        return logDAO.GetLogs();
    }

    public void insertLog(String date , Float duration , Float destination , String tourname) throws SQLException {
        logDAO.insertNewLog(date , duration , destination, tourname);
    }

    public void deleteLog(int id) throws SQLException{
        logDAO.deleteLog(id);
    }
   public void copyLog(String date, Float duration, Float destination, String tourName) throws SQLException{
        logDAO.copyLog(date,duration,destination,tourName);
   }
   public void EditTLog(String date, Float duration, Float destination, String tourName , int id) throws SQLException{
        logDAO.EditTLog(date,duration,destination,tourName,id);
   }
}
