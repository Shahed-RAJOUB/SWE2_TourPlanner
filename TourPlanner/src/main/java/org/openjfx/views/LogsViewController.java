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
}
