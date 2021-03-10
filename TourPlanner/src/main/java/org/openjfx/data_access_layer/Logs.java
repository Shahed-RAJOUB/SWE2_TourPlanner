package org.openjfx.data_access_layer;

import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Logs {

    public Integer id;
    public String tourtitle;
    public String tourdate;
    public String tourdist;

    public Logs(int id, String tourtitle, String tourdate, String tourdist) {
        this.id = id;
        this.tourtitle = tourtitle;
        this.tourdate = tourdate;
        this.tourdist = tourdist;
    }
}
