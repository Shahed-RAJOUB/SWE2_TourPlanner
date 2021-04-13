package org.openjfx.business_layer;

import org.openjfx.view_model.Tour;

import java.util.List;

public interface ToursManager {
    public List<Tour> GetTours();
    public List<Tour> searchTours(String tour );
}
