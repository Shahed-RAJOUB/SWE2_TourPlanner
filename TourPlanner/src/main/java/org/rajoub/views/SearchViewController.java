package org.rajoub.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.rajoub.view_model.ViewModelSearch;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
@RequiredArgsConstructor
@Setter
@Getter
@Log4j
public class SearchViewController implements Initializable {
    @FXML
    public TextField search;

    @FXML
    public Button searchButton;

    private final ViewModelSearch viewModelSearch;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        log.info("to edit in searchViewController");
    }
    public void onSearch(ActionEvent actionEvent) {
        if(actionEvent.getSource().equals(searchButton)){
            viewModelSearch.searchLists(search.getText());
            getSearch().setText(null);
        }
    }
}
