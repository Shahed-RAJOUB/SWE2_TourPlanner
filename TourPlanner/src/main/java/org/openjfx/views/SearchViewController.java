package org.openjfx.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.openjfx.view_model.ViewModelSearch;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
@RequiredArgsConstructor
@Setter
@Getter
public class SearchViewController implements Initializable {
    @FXML
    public TextField search;

    @FXML
    public Button searchButton;

    private final ViewModelSearch viewModelSearch;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
/*        search.textProperty().bindBidirectional(viewModelSearch.searchStringProperty());
        searchButton.disableProperty().bind(viewModelSearch.searchDisabledBinding());*/
    }
    public void onSearch(ActionEvent actionEvent) {
        //business Logic to find
    }
}
