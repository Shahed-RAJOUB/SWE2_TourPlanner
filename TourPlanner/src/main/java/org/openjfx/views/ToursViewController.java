package org.openjfx.views;

/**
 * @author: Chahed Rajoub
 * @implNote Email : < if19b166@technikum-wien.at>
 */

import java.net.URL;
import java.util.ResourceBundle;


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
    void addTour(ActionEvent event) {
      /* if (event.getSource()==AddTour){
           viewModelTours.insertTour();
       }*/
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        viewModelTours.init();
    }
}
