package org.rajoub.model;

import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
public class Tour {
    public int tourId;
    public String tourName;
    public String from;
    public String to;
    public List<Log> tourLogs;
    public Tour(){}  // empty constructor
    public Tour(int tourId , String tourName , String from , String to) {
        this.tourId = tourId;
        this.tourName = tourName;
        this.from = from;
        this.to = to;
    }

    public Tour(String tour, String from, String to) {
        this.tourName = tour;
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return this.tourName;
    }
    public Tour(String tourName){
        this.tourName = tourName;
    }
}
