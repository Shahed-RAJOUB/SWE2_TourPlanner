package org.openjfx.business_layer;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openjfx.data_access_layer.TourDAO;
import org.openjfx.view_model.Tour;

import java.util.List;

import static org.mockito.Mockito.when;


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
    void getTours_returnTours(){
        when(tourDAO.GetTours()).thenReturn(List.of(Tour.builder().build() ));
        Assertions.assertEquals(toursService.GetTours() , List.of(Tour.builder().build()));
    }
}
