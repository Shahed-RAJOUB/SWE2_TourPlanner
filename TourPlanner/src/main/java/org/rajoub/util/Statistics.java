package org.rajoub.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.rajoub.business_layer.LogsService;
import org.rajoub.business_layer.ToursService;
import org.rajoub.model.Log;
import org.rajoub.model.Tour;
import org.springframework.stereotype.Component;


import java.nio.file.Paths;
import java.util.*;

@Builder
@Component
@RequiredArgsConstructor
public class Statistics {
    private final ToursService toursService;
    private final LogsService logsService;



    public void getChart() {

    }

    public void exportJS() {

        List<Tour> tours = new ArrayList<>();
        try {
            for(int i = 0 ; i < toursService.GetTours().size() ; i++) {
                List<Log> tourLogs = new ArrayList<>();
                for (int x = 0; x < logsService.GetLogs().size(); x++) {
                    if (logsService.GetLogs().get(x).tourName.equals(toursService.GetTours().get(i).tourName)) {
                        Log log = new Log(logsService.GetLogs().get(x).id ,  logsService.GetLogs().get(x).date ,logsService.GetLogs().get(x).duration , logsService.GetLogs().get(x).dest , logsService.GetLogs().get(x).burnedCalories , logsService.GetLogs().get(x).tourName);
                       tourLogs.add(log);
                    }
                }
                Tour tour = new Tour(toursService.GetTours().get(i).tourId, toursService.GetTours().get(i).tourName , toursService.GetTours().get(i).from , toursService.GetTours().get(i).to , tourLogs );
                tours.add(tour);
            }
            ObjectMapper objectMapper = new ObjectMapper();
            // convert map to JSON file
            objectMapper.writeValue(Paths.get("exportedData/data_"+RandomString()+".json").toFile(), tours);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void importJS() {
        try {
            // create object mapper instance
            ObjectMapper mapper = new ObjectMapper();

            // convert JSON array to list of tours
            List<Tour> tours = Arrays.asList(mapper.readValue(Paths.get("importedData/Data.json").toFile(), Tour[].class));

            // save tours for each
            for( int i = 0 ; i < tours.size(); i++){
                String newNameTour = tours.get(i).tourName+"imported"+RandomString();
                toursService.insertTour(newNameTour , tours.get(i).from , tours.get(i).to );
                for (int x = 0 ; x < tours.get(i).tourLogs.size() ; x++){
                    logsService.insertLog(tours.get(i).tourLogs.get(x).date , tours.get(i).tourLogs.get(x).duration , tours.get(i).tourLogs.get(x).dest , newNameTour);
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private String RandomString() {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int length = 3;
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }

        return sb.toString();
    }
}
