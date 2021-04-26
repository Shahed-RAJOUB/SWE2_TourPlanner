package org.openjfx.business_layer;

import lombok.RequiredArgsConstructor;
import org.openjfx.data_access_layer.TourDAO;
import org.openjfx.view_model.Tour;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ToursService{
    private final TourDAO tourDAO;

    public List<Tour> GetTours() {
        return tourDAO.GetTours();
    }

    public List<Tour> searchTours(String searchText) {
        var tours = tourDAO.GetTours();
        if(searchText == null || searchText.isEmpty()){
            return tours;
        }
        return tours.stream()
                .filter(t -> t.getTourName().toLowerCase().contains(searchText.toLowerCase()))
                .collect(java.util.stream.Collectors.toList());
    }
    public void insertTour(String tour) throws SQLException {
        tourDAO.insertNewTour(tour);
    }
}
