package org.openjfx.views;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lombok.Getter;
import lombok.Setter;
import org.openjfx.view_model.ViewModelMain;

import java.net.URL;
import java.util.ResourceBundle;

@Getter
@Setter
public class MainController implements Initializable {

    @FXML
    private SearchViewController searchViewController;

    @FXML
    private ToursViewController toursViewController;

    @FXML
    private LogsViewController logsViewController;

    @FXML
    private Menu menuFile;

    @FXML
    private MenuItem getReport;

    @FXML
    private Menu menuEdit;

    @FXML
    private MenuItem editTour;

    @FXML
    private MenuItem deleteTour;

    @FXML
    private MenuItem editLogs;

    @FXML
    private MenuItem deleteLogs;

    @FXML
    private Menu menuHelp;

    @FXML
    private MenuItem about;

    @FXML
    private MenuItem maintainer;

    private final ViewModelMain viewModelMain = new ViewModelMain();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      viewModelMain.setViewModelSearch(searchViewController.getViewModelSearch());
      viewModelMain.setViewModelTours(toursViewController.getViewModelTours());
      viewModelMain.setViewModelLogs(logsViewController.getViewModelLogs());
    }
}
