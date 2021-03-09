package org.openjfx;

/**
 * @author: Chahed Rajoub
 * @implNote Email : < if19b166@technikum-wien.at>
 */

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;


import org.openjfx.data_access_layer.Logs;
import org.openjfx.data_access_layer.dbContent;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.openjfx.view_model.view_model_primary;


public class PrimaryController implements Initializable {



    @FXML
    private DatePicker date;

    @FXML
    private TextField txtTitle;

    @FXML
    private TextField intDistance;

    @FXML
    private Button TourInsert;

    @FXML
    private TableView<Logs> tableLogs;

    @FXML
    private TableColumn<Logs, Integer> tableId;

    @FXML
    private TableColumn<Logs, String> tableTitle;

    @FXML
    private TableColumn<Logs, LocalDate> tableDate;

    @FXML
    private TableColumn<Logs, Integer> tableDistance;

    @FXML
    void addTour(ActionEvent event) {
        //myData.configureConnection();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dbContent myData = new dbContent();
        myData.configureConnection();
        ObservableList<Logs> list = myData.getlogs();
        tableId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableTitle.setCellValueFactory(new PropertyValueFactory<>("tourtitle"));
        tableDate.setCellValueFactory(new PropertyValueFactory<>("tourdate"));
        tableDistance.setCellValueFactory(new PropertyValueFactory<>("tourdist"));
        tableLogs.setItems(list);

    }
}
