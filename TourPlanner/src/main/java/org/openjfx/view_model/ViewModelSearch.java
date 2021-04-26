package org.openjfx.view_model;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.openjfx.business_layer.LogsService;
import org.openjfx.business_layer.ToursService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter
@Setter
public class ViewModelSearch {
    private final ToursService toursService;
    private final LogsService logsService;

/*    public ViewModelSearch() {
        searchString.addListener((arg, oldVal, newVal)->isSearchDisabledBinding.invalidate());
    }*/

    public void searchLists( String search) {
        if(search!=null) {
            var tours = toursService.searchTours(search);
            var logs = logsService.searchLogs(search);
        }
    }
}
