package org.openjfx.view_model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Tour {
    public int tourId;
    public String tourName;
    public List<Log> tourLogs;

    public Tour(int tourId , String tourName) {
        this.tourId = tourId;
        this.tourName = tourName;
    }

    public Tour(String tourName) {
        this.tourName = tourName;
    }
}
