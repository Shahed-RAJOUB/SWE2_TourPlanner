package org.openjfx.data_access_layer;

import org.openjfx.view_model.Log;
import java.util.List;
import java.util.stream.Collectors;

public class LogDAO {
    private final DataAccess dataAccess;
    // welche daten system als target ist
    public LogDAO(DataAccess dataAccess){
        this.dataAccess = dataAccess;
    }
    public List<Log> GetLogs(){
        return dataAccess.GetLogs();
    }
    public List<Log> findMatchingLogs(String searchText){
        var logs = dataAccess.GetLogs();
        if(searchText == null || searchText.isEmpty()){
            return logs;
        }
        return logs.stream().filter(t -> t.getTourName().toLowerCase().contains(searchText.toLowerCase())).collect(Collectors.toList());
    }
}
