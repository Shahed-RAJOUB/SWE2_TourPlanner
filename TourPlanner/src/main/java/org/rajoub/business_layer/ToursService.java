package org.rajoub.business_layer;

import lombok.RequiredArgsConstructor;
import org.rajoub.data_access_layer.TourDAO;
import org.rajoub.model.Tour;
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
    public void insertTour(String tour , String from , String to) throws SQLException {
        tourDAO.insertNewTour(tour,from,to);
    }
    public void copyTour(String tour , String from , String to) throws SQLException {
        tourDAO.copyTour(tour,from,to);
    }
    public void editTour(String tour , String from , String to , String old) throws SQLException {
        tourDAO.EditTour(tour,from,to , old);
    }
    public void deleteTour(String tour) throws SQLException {
        tourDAO.deleteTour(tour);
    }
}
