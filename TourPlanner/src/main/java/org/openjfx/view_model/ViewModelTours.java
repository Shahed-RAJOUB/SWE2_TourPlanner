package org.openjfx.view_model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ViewModelTours {
/*    private ObservableList<Tour> observableTours = FXCollections.observableArrayList();

    public void SetTours(List<Tour> tours){
        var tourItems = tours.stream().map(t->new Tour(t.getTourName())).collect(Collectors.toList());
        observableTours.clear();
        observableTours.addAll(tourItems);
    }

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
