package org.rajoub.views;

/**
 * @author: Chahed Rajoub
 * @implNote Email : < if19b166@technikum-wien.at>
 */

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


import javafx.scene.input.MouseEvent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lombok.extern.log4j.Log4j;
import org.rajoub.model.Tour;
import org.rajoub.view_model.ViewModelLogs;
import org.rajoub.view_model.ViewModelTours;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Getter
@Setter
@Log4j
public class ToursViewController implements Initializable {

    @FXML
    private ListView<Tour> toursList;

    @FXML
    private TextField TourName;

    @FXML
    private Button AddTour;
    @FXML
    private TextField TourFrom;

    @FXML
    private TextField TourTo;
    @FXML
    private TextField TourNewName;

    @FXML
    private Button EditTour;
    @FXML
    private TextField TourNewFrom;

    @FXML
    private TextField TourNewTo;
    @FXML
    private Button DeleteTour;

    @FXML
    private Button CopyTour;

    private final ViewModelTours viewModelTours;


    @FXML
    void addTour(ActionEvent event) throws SQLException {
      if(event.getSource()==AddTour){
           viewModelTours.insertTour(TourName.getText(),TourFrom.getText(),TourTo.getText());
           getTourName().setText(null);
           getTourFrom().setText(null);
           getTourTo().setText(null);
       }
    }
    @FXML
    void  deleteTour(ActionEvent event)throws SQLException {
        if(event.getSource()==DeleteTour){
          viewModelTours.delete(getSelectedTour().getTourName());
        }
    }
    @FXML
    void  copyTour(ActionEvent event)throws SQLException {
        if(event.getSource()==CopyTour){
            viewModelTours.copyTour(getSelectedTour().getTourName(),getSelectedTour().getFrom(),getSelectedTour().getTo());
        }
    }
    @FXML
    void  EditTour(ActionEvent event)throws SQLException {
        if(event.getSource()==EditTour){
            viewModelTours.editTour(TourNewName.getText(),TourNewFrom.getText(),TourNewTo.getText() , getSelectedTour().getTourName());
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        log.info("to edit in tourViewController");
        toursList.setItems(viewModelTours.getTours());

    }
    Tour getSelectedTour() {
        return toursList.getSelectionModel().getSelectedItem();
    }
    @FXML
    public void handleMouseClick(MouseEvent mouseEvent) throws IOException {
        if( getSelectedTour() != null){
            viewModelTours.setLogs(getSelectedTour().getTourName());
            viewModelTours.setRout(getSelectedTour().getFrom(),getSelectedTour().getTo() );
        }
    }
}
