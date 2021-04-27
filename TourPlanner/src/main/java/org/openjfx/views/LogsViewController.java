package org.openjfx.views;

import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import org.openjfx.view_model.Log;
import org.openjfx.view_model.ViewModelLogs;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

@Controller
@RequiredArgsConstructor
@Getter
@Setter
public class LogsViewController implements Initializable {

    @FXML
    private ImageView urlImage;
    @FXML
    private Button Print;
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
    private DatePicker LogDate;

    @FXML
    private TextField LogDuration;

    @FXML
    private TextField LogDestination;

    @FXML
    private TextField LogTour;

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

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableLogs.setItems(viewModelLogs.getLogs());
        tableDate.setCellValueFactory(log -> new SimpleObjectProperty<>(log.getValue().getDate()));
        tableDuration.setCellValueFactory(log -> new SimpleObjectProperty<>(log.getValue().getDuration()));
        tableDistance.setCellValueFactory(log -> new SimpleObjectProperty<>(log.getValue().getDest()));
        BurnedCalories.setCellValueFactory(log -> new SimpleObjectProperty<>(log.getValue().getBurnedCalories()));
        urlImage.setImage(viewModelLogs.getImage());
    }

    public void addLog(ActionEvent event) throws SQLException {
        if(event.getSource()==AddLog){
            viewModelLogs.insertLog(LogDate.getValue().toString() , LogDuration.getText() , LogDestination.getText(), LogTour.getText());
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
            viewModelLogs.copyLog(getSelectedLog().getDate(),getSelectedLog().getDuration(),getSelectedLog().getDest(),getSelectedLog().getTourName());
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
            viewModelLogs.getPdf();
        }
    }
}
