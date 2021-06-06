package org.rajoub.views;

import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;
import org.rajoub.model.Log;
import org.rajoub.view_model.ViewModelLogs;
import org.rajoub.view_model.ViewModelTours;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

@Controller
@RequiredArgsConstructor
@Getter
@Setter
@Log4j
public class LogsViewController implements Initializable {

    @FXML
    private ImageView urlImage;
    @FXML
    private Button Print;

    @FXML
    private CategoryAxis X;

    @FXML
    private NumberAxis Y;
    @FXML
    private Button Import;

    @FXML
    private Button Export;
    @FXML
    private Button TourRepo;
    @FXML
    private LineChart<?, ?> LineChart;

    @FXML
    private TableView<Log> tableLogs;

    @FXML
    private TableColumn<Log, String> tableDate;

    @FXML
    private TableColumn<Log, Float> tableDuration;

    @FXML
    private TableColumn<Log, Float> tableDistance;

    @FXML
    private TableColumn<Log, Float> BurnedCalories;

    @FXML
    private TableColumn<Log, String> Ratings;

    @FXML
    private DatePicker LogDate;

    @FXML
    private TextField LogDuration;

    @FXML
    private TextField LogDestination;

    @FXML
    private TextField LogTour;
    @FXML
    private TextField LogRating;

    @FXML
    private Button AddLog;
    @FXML
    private DatePicker LogNewDate;

    @FXML
    private TextField LogNewDuration;

    @FXML
    private TextField LogNewDestination;

    @FXML
    private TextField LogNewTour;

    @FXML
    private Button EditLog;

    @FXML
    private Button DeleteLog;

    @FXML
    private Button CopyLog;
    private final ViewModelLogs viewModelLogs;
    private final ViewModelTours viewModelTours;

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        log.info("to edit in logViewController");
        tableLogs.setItems(viewModelLogs.getLogs());
        visibleLogs();
    }
    public void visibleLogs() {
        tableDate.setCellValueFactory(log -> new SimpleObjectProperty<>(log.getValue().getDate()));
        tableDuration.setCellValueFactory(log -> new SimpleObjectProperty<>(log.getValue().getDuration()));
        tableDistance.setCellValueFactory(log -> new SimpleObjectProperty<>(log.getValue().getDest()));
        BurnedCalories.setCellValueFactory(log -> new SimpleObjectProperty<>(log.getValue().getBurnedCalories()));
        Ratings.setCellValueFactory(log -> new SimpleObjectProperty<>(log.getValue().getRatings()));
        urlImage.imageProperty().bind(viewModelLogs.getImagTour());
    }
    @FXML
    public void addLog(ActionEvent event) throws SQLException {
        if(event.getSource()==AddLog){
            viewModelLogs.insertLog(LogDate.getValue().toString() , LogDuration.getText() , LogDestination.getText(), LogTour.getText() , LogRating.getText());
           getLogDate().setValue(null);
           getLogDuration().setText(null);
           getLogDestination().setText(null);
           getLogTour().setText(null);
        }
    }
    @FXML
    void  deleteLog(ActionEvent event)throws SQLException {
        if(event.getSource()==DeleteLog){
            viewModelLogs.deleteLog(getSelectedLog().getId());
        }
    }
    @FXML
    void  copyLog(ActionEvent event)throws SQLException {
        if(event.getSource()==CopyLog){
            viewModelLogs.copyLog(getSelectedLog().getDate(),getSelectedLog().getDuration(),getSelectedLog().getDest(),getSelectedLog().getTourName(),getSelectedLog().getRatings());
        }
    }
    @FXML
    void  EditLog(ActionEvent event)throws SQLException {
        if(event.getSource()==EditLog){
            viewModelLogs.EditTLog(LogNewDate.getValue().toString() , LogNewDuration.getText() , LogNewDestination.getText() , LogNewTour.getText() , getSelectedLog().getId());
        }
    }
    Log getSelectedLog() {
        return tableLogs.getSelectionModel().getSelectedItem();
    }

    public void print(ActionEvent event) throws IOException {
        if(event.getSource()==Print){
            viewModelLogs.getFullPdf();
        }
    }

    public void TourReport(ActionEvent event) throws IOException {
        if(event.getSource()==TourRepo){
            viewModelLogs.getTourPdf();
        }
    }

    public void exportJS(ActionEvent event) throws IOException {
        if(event.getSource()==Export){
            viewModelLogs.exportJS();
        }
    }
    public void importJS(ActionEvent event) {
        if(event.getSource()==Import){
            viewModelLogs.importJS();
        }
    }
}
