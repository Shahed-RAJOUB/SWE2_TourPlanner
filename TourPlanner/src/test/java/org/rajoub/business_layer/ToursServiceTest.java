package org.rajoub.business_layer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.rajoub.data_access_layer.TourDAO;
import org.rajoub.model.Tour;

import java.util.List;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ToursServiceTest {
    @Mock
    private TourDAO tourDAO;
    private ToursService toursService;

    @BeforeEach
    void setup() {
        toursService = new ToursService(tourDAO );
    }
    @Test
    void getTours_returnTours(){
        when(tourDAO.GetTours()).thenReturn(List.of(Tour.builder().build() ));
        Assertions.assertEquals(toursService.GetTours() , List.of(Tour.builder().build()));
    }
}
