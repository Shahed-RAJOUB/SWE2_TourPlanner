package org.openjfx.business_layer;

import org.openjfx.data_access_layer.TourDAO;
import org.openjfx.data_access_layer.dbContent;
import org.openjfx.view_model.Tour;

import java.util.List;

public class ToursManagerImpl implements ToursManager{
    TourDAO tourDAO = new TourDAO(new dbContent());
    @Override
    public List<Tour> GetTours() {
        return tourDAO.GetTours();
    }

    @Override
    public List<Tour> searchTours(String tour) {
        return tourDAO.findMatchingTours(tour);
    }
}
