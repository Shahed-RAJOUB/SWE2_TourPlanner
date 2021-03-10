package org.openjfx.view_model;

import javafx.beans.property.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class view_model_primary {
    private IntegerProperty TourId = new SimpleIntegerProperty();
    private  StringProperty TourTitle = new SimpleStringProperty();
    private StringProperty TourDate;
    private  IntegerProperty TourDistance = new SimpleIntegerProperty();
    private BooleanProperty buttonDisabled = new SimpleBooleanProperty();

}
