package org.openjfx.view_model;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class ViewModelSearch {
    private ViewModelMain viewModelMain;

    private final StringProperty searchString = new SimpleStringProperty("");
    private final BooleanBinding isSearchDisabledBinding = Bindings.createBooleanBinding( ()-> searchString.get().isEmpty());


    public ViewModelSearch() {
        searchString.addListener((arg, oldVal, newVal)->isSearchDisabledBinding.invalidate());
    }

    public Property<String> searchStringProperty() {
        return searchString;
    }

    public ObservableValue<Boolean> searchDisabledBinding() {
        return isSearchDisabledBinding;
    }
}
