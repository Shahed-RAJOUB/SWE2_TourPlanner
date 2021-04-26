package org.openjfx.view_model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    public ObservableList<Tour>  getTours() {
        observableTours.clear();
         observableTours.addAll(toursService.GetTours());
         return observableTours;
    }
    public void insertTour(String tour) throws SQLException {
       toursService.insertTour(tour);
       getTours();
    }

}
