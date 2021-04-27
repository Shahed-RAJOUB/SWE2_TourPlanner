package org.openjfx.view_model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter

public class Tour {
    public int tourId;
    public String tourName;
    public String from;
    public String to;
    public List<Log> tourLogs;

    public Tour(int tourId , String tourName , String from , String to) {
        this.tourId = tourId;
        this.tourName = tourName;
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return this.tourName;
    }
}
