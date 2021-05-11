package org.rajoub.views;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.rajoub.view_model.ViewModelMain;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;
@Controller
@RequiredArgsConstructor
@Getter
@Setter
@Log4j
public class MainController implements Initializable {

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

    private final ViewModelMain viewModelMain;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        log.info("to edit in tourViewController");
    }
}
