package org.openjfx.business_layer;

import org.openjfx.data_access_layer.LogDAO;
import org.openjfx.data_access_layer.dbContent;
import org.openjfx.view_model.Log;

import java.util.List;

public class LogsManagerImpl implements LogsManager {
    LogDAO logDAO = new LogDAO(new dbContent());
    @Override
    public List<Log> GetLogs() {
        // from data object access
        return logDAO.GetLogs();
    }

    @Override
    public List<Log> searchLogs(String tour ) {
        return logDAO.findMatchingLogs(tour);
    }
}
