package org.openjfx.view_model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.openjfx.business_layer.LogsService;
import org.openjfx.util.MapquestApiService;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
@Getter
@Setter
public class ViewModelLogs {
    private final LogsService logsService;
    private final MapquestApiService mapquestApiService;
    private ObservableList<Log> observableLogs = FXCollections.observableArrayList();
    public ObservableList<Log> getLogs() {
        observableLogs.clear();
        observableLogs.addAll(logsService.GetLogs());
        return observableLogs;
    }

    public void insertLog(String date, String dur, String dest, String tour) throws SQLException {
        float duration=Float.parseFloat(dur);
        float destination=Float.parseFloat(dest);
        logsService.insertLog(date,duration,destination,tour);
        getLogs();
    }

    public Image getImage() throws IOException {
        mapquestApiService.GetImage();
       return   mapquestApiService.GetImageFromFile();
    }
}
