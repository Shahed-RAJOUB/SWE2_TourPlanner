package org.rajoub.business_layer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.rajoub.data_access_layer.TourDAO;
import org.rajoub.model.Tour;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ToursServiceTest {
    @Mock
    private TourDAO tourDAO;
    private ToursService toursService;

    @BeforeEach
    void setup() {
        toursService = new ToursService(tourDAO);
    }

    @Test
    void GetToursTest_getTourList() {
        List<Tour> tours = new ArrayList<>();
        Tour tour_1 = new Tour("tour1");
        Tour tour_2 = new Tour("tour2");
        Tour tour_3 = new Tour("tour3");
        tours.add(tour_1);
        tours.add(tour_2);
        tours.add(tour_3);
        Mockito.when(tourDAO.GetTours()).thenReturn(tours);

        Assertions.assertEquals(toursService.GetTours(), tours);
    }
    @Test
    void DeleteTourTest_deleted() throws SQLException {
        Tour expected = new Tour("tour");
        toursService.deleteTour(expected.getTourName());
        Mockito.verify(tourDAO).deleteTour(expected.getTourName());
    }
    @Test
    void CopyTourTest_Copied() throws SQLException {
        Tour expected = new Tour("tour" , "from" , "to");
        toursService.copyTour(expected.getTourName(), expected.getFrom() , expected.getTo());
        Mockito.verify(tourDAO).copyTour(expected.getTourName(), expected.getFrom() , expected.getTo());
    }

    @Test
    void InsertTourTest_inserted() throws SQLException {
        Tour expected = new Tour("tour" , "from" , "to");
        toursService.insertTour(expected.getTourName(), expected.getFrom() , expected.getTo());
        Mockito.verify(tourDAO).insertNewTour(expected.getTourName(), expected.getFrom() , expected.getTo());
    }
    @Test
    void EditTourTest_edited() throws SQLException {
        Tour expected = new Tour("tour" , "from" , "to");
        toursService.editTour(expected.getTourName(), expected.getFrom() , expected.getTo() , expected.getTourName());
        Mockito.verify(tourDAO).EditTour(expected.getTourName(), expected.getFrom() , expected.getTo() , expected.getTourName());
    }
}
