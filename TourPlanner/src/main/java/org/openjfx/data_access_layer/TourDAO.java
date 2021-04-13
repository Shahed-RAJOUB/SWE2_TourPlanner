package org.openjfx.data_access_layer;

import org.openjfx.view_model.Tour;

import java.util.List;
import java.util.stream.Collectors;

public class TourDAO {
    private final DataAccess dataAccess;

    public TourDAO(DataAccess dataAccess){
        this.dataAccess = dataAccess;
    }
    public List<Tour> GetTours(){
        return dataAccess.GetTours();
    }
    public List<Tour> findMatchingTours(String searchText){
        var tours = dataAccess.GetTours();
        if(searchText == null || searchText.isEmpty()){
            return tours;
        }
        return tours.stream().filter(t -> t.getTourName().toLowerCase().contains(searchText.toLowerCase())).collect(Collectors.toList());
    }
}
