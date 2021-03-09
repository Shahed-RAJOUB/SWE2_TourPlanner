package org.openjfx.view_model;

import javafx.beans.property.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class view_model_primary {
    private IntegerProperty TourId = new SimpleIntegerProperty();
    private  StringProperty TourTitle = new SimpleStringProperty();
    private LocalDate TourDate;
    private  IntegerProperty TourDistance = new SimpleIntegerProperty();
    private BooleanProperty buttonDisabled = new SimpleBooleanProperty();

}
