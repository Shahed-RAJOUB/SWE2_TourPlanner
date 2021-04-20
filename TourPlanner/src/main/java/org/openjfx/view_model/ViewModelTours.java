package org.openjfx.view_model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.openjfx.business_layer.ToursService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter
@Setter
public class ViewModelTours {
    private final ToursService toursService;
    private ObservableList<Tour> observableTours = FXCollections.observableArrayList();

    public ObservableList<Tour> getTours() {
        observableTours = (ObservableList<Tour>) toursService.GetTours();
        return observableTours;
    }

    public void init(){
        observableTours.addAll(getTours());
    }
/*

    public void insertTour() {
        myData.configureConnection();
        myData.insertNewLog(txtTitle.getText() , date.getValue().toString(), intDistance.getText());
        GetTours();
    }

    public void GetTours() {
        myData.configureConnection();
        ObservableList<Log> list = myData.GetLogs();
        tableId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableTitle.setCellValueFactory(new PropertyValueFactory<>("tourName"));
        tableLogs.setItems(list);
    }*/
}
