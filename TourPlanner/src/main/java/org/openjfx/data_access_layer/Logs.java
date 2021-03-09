package org.openjfx.data_access_layer;

import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Logs {

    private Integer id;
    private String tourtitle;
    private String tourdate;
    private Integer tourdist;

    public Logs(int id, String tourtitle, String tourdate, int tourdist) {
        this.id = id;
        this.tourtitle = tourtitle;
        this.tourdate = tourdate;
        this.tourdist = tourdist;
    }
}
