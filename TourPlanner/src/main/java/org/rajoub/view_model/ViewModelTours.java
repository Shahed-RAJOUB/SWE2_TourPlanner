package org.rajoub.view_model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.rajoub.business_layer.ToursService;
import org.rajoub.model.Tour;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.SQLException;


@Component
@RequiredArgsConstructor
@Getter
@Setter

public class ViewModelTours {
    private final ToursService toursService;
    private final ViewModelLogs viewModelLogs;
    private ObservableList<Tour> observableTours = FXCollections.observableArrayList();
    FilteredList<Tour> searchedTours = new FilteredList<>(observableTours, s -> true);


    public ObservableList<Tour>  getTours() {
        observableTours.clear();
        observableTours.addAll(toursService.GetTours());
        return searchedTours;
    }
    public void insertTour(String tour,String from , String to) throws SQLException {
       toursService.insertTour(tour , from , to);
       getTours();
    }

    public void editTour(String tourName, String from, String to , String oldTour) throws SQLException {
        toursService.editTour(tourName,from,to , oldTour);
        getTours();
    }

    public void delete(String tour) throws SQLException {
        toursService.deleteTour(tour);
        getTours();
    }

    public void copyTour(String tour,String from , String to) throws SQLException {
        toursService.copyTour(tour , from , to);
        getTours();
    }

    public void setLogs(String tourName) {
        if(tourName!=null) {
            viewModelLogs.getSearchedLogs().setPredicate(l -> l.getTourName().toLowerCase().contains(tourName.toLowerCase()));
            viewModelLogs.getSelectedTour().setValue(tourName);
            viewModelLogs.getLogs();
        }
    }

    public void setRout(String from, String to) throws IOException {
        if(from != null) {
            viewModelLogs.getFrom().setValue(from);
            viewModelLogs.getTo().setValue(to);
            viewModelLogs.getImage();
        }
    }
}
