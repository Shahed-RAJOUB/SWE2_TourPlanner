package org.rajoub.model;

import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
public class Log {

    public Integer id;
    public String date;
    public Float dest;
    public Float duration;
    public Float burnedCalories;
    public String url;
    public String tourName;
    public Log(){}

    public Log(int id, String date, float duration, float destination, float calories, String tourName) {
        this.id = id;
        this.date = date;
        this.dest = destination;
        this.duration = duration;
        this.burnedCalories = calories;
        this.tourName = tourName;
    }
}
