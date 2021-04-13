package org.openjfx.business_layer;

import org.openjfx.view_model.Log;

import java.util.List;

public interface LogsManager {
   public List<Log> GetLogs();
   public List<Log> searchLogs(String tour );
}
