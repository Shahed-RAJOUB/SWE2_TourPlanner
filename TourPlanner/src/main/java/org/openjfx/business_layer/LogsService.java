package org.openjfx.business_layer;

import lombok.RequiredArgsConstructor;
import org.openjfx.data_access_layer.LogDAO;
import org.openjfx.view_model.Log;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LogsService  {
    private final LogDAO logDAO;

    public List<Log> GetLogs() {
        return logDAO.GetLogs();
    }

    public List<Log> searchLogs(String searchText) {
        var logs = logDAO.GetLogs();
        if(searchText == null || searchText.isEmpty()){
            return logs;
        }
        return logs.stream()
                .filter(t -> t.getTourName().toLowerCase().contains(searchText.toLowerCase()))
                .collect(java.util.stream.Collectors.toList());
    }
}
