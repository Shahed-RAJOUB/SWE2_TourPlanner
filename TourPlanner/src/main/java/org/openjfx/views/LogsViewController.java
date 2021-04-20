package org.openjfx.views;

import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.openjfx.view_model.Log;
import org.openjfx.view_model.ViewModelLogs;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
@RequiredArgsConstructor
@Getter
@Setter
public class LogsViewController implements Initializable {

    public TextField LogTour;
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
    private TextField LogDate;

    @FXML
    private TextField LogDuration;

    @FXML
    private TextField LogDistance;

    @FXML
    private Button AddLog;

    private final ViewModelLogs viewModelLogs;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        tableLogs.setItems(viewModelLogs.GetLogs());
/*        tableDateColumn.setCellValueFactory(log -> new SimpleObjectProperty<>(log.getValue().getDate()));
        durationTableColumn.setCellValueFactory(log -> new SimpleObjectProperty<>(log.getValue().getDurationFormatted()));
        distanceTableColumn.setCellValueFactory(log -> new SimpleObjectProperty<>(log.getValue().getDistanceFormatted()));
        descriptionTableColumn.setCellValueFactory(log -> new SimpleObjectProperty<>(log.getValue().getDescription()));*/
    }

    public void addLog(ActionEvent actionEvent) {
    }
}
