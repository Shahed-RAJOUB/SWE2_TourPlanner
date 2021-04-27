package org.openjfx.view_model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.openjfx.business_layer.LogsService;
import org.openjfx.util.MapquestApiService;
import org.openjfx.util.PdfGenerator;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
@Getter
@Setter
public class ViewModelLogs {
    private final LogsService logsService;
    private final MapquestApiService mapquestApiService;
    private final PdfGenerator pdfGenerator;
    private ObservableList<Log> observableLogs = FXCollections.observableArrayList();
    public ObservableList<Log> getLogs() {
        observableLogs.clear();
        observableLogs.addAll(logsService.GetLogs());
        return observableLogs;
    }

    public void insertLog(String date, String dur, String dest, String tour) throws SQLException {
        float duration=Float.parseFloat(dur);
        float destination=Float.parseFloat(dest);
        logsService.insertLog(date,duration,destination,tour);
        getLogs();
    }

    public Image getImage() throws IOException {
        mapquestApiService.GetImage();
       return   mapquestApiService.GetImageFromFile();
    }
    public void deleteLog(int id) throws SQLException{
        logsService.deleteLog(id);
        getLogs();
    }
    public void copyLog(String date, Float duration, Float destination, String tourName) throws SQLException{
        logsService.copyLog(date,duration,destination,tourName);
        getLogs();
    }
    public void EditTLog(String date, String dur, String dest, String tourName , int id) throws SQLException{
        float duration=Float.parseFloat(dur);
        float destination=Float.parseFloat(dest);
        logsService.EditTLog(date,duration,destination,tourName,id);
        getLogs();
    }

    public void getPdf() throws IOException {
        pdfGenerator.DownloadPdf();
    }
}
