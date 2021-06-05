package org.rajoub.view_model;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.image.Image;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.rajoub.business_layer.LogsService;
import org.rajoub.model.Log;
import org.rajoub.util.MapquestApiService;
import org.rajoub.util.PdfGenerator;
import org.rajoub.util.Statistics;
import org.springframework.context.annotation.Lazy;
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
    private final Statistics statistics;
    private ObservableList<Log> observableLogs = FXCollections.observableArrayList();
    FilteredList<Log> searchedLogs = new FilteredList<>(observableLogs, s -> true);
    private  final StringProperty from = new SimpleStringProperty();
    private final StringProperty to = new SimpleStringProperty();
    private final ObjectProperty<Image> imagTour = new SimpleObjectProperty<>();
    private final ObjectProperty<String> SelectedTour = new SimpleObjectProperty<>();


    public ObservableList<Log> getLogs() {
        observableLogs.clear();
        observableLogs.addAll(logsService.GetLogs());
        return searchedLogs;
    }

    public void insertLog(String date, String dur, String dest, String tour) throws SQLException {
        float duration=Float.parseFloat(dur);
        float destination=Float.parseFloat(dest);
        logsService.insertLog(date,duration,destination,tour);
        getLogs();
    }

    public Image getImage() throws IOException {
        if(getFrom() != null) {
            mapquestApiService.GetImage(getFrom(), getTo());
            getImagTour().setValue(mapquestApiService.GetImageFromFile());
            return mapquestApiService.GetImageFromFile();

        }
        return null;
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

    public void getFullPdf() throws IOException {
        pdfGenerator.DownloadFullPdf();
    }


    public void getTourPdf() throws IOException {
        pdfGenerator.DownloadTourPdf(getSelectedTour().getValue());
    }

    public void exportJS() {
        statistics.exportJS();
    }

    public void importJS() {
        statistics.importJS();
        getLogs();
    }
}
