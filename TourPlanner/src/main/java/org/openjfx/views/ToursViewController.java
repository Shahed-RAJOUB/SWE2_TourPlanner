package org.openjfx.views;

/**
 * @author: Chahed Rajoub
 * @implNote Email : < if19b166@technikum-wien.at>
 */

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


import javafx.collections.ObservableList;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.openjfx.view_model.Tour;
import org.openjfx.view_model.ViewModelTours;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Getter
@Setter
public class ToursViewController implements Initializable {

    @FXML
    private ListView<Tour> toursList;

    @FXML
    private TextField TourName;

    @FXML
    private Button AddTour;

    private final ViewModelTours viewModelTours;

    @FXML
    void addTour(ActionEvent event) throws SQLException {
      if(event.getSource()==AddTour){
           viewModelTours.insertTour(TourName.getText());
           getTourName().setText(null);
       }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        toursList.setItems(viewModelTours.getTours());

    }
}
