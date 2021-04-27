package org.openjfx.view_model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.openjfx.business_layer.ToursService;
import org.springframework.stereotype.Component;

import java.sql.SQLException;


@Component
@RequiredArgsConstructor
@Getter
@Setter
public class ViewModelTours {
    private final ToursService toursService;
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
}
