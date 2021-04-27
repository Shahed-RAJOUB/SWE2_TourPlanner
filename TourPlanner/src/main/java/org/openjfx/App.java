package org.openjfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;


import java.io.IOException;

@ComponentScan
@Log4j
public class App extends Application {

    private static Scene scene;
    private static AnnotationConfigApplicationContext context;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("main"), 640, 480);
        /*PropertyConfigurator.configure(getClass().getResource("log4j.properties"));*/
        log.debug("Starting Debug Method");
        log.info("Starting Info Method");
        log.error("Starting Error Method");
        stage.setScene(scene);
        stage.show();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        fxmlLoader.setControllerFactory(App.context::getBean);
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        context = new AnnotationConfigApplicationContext();
        context.register(App.class);
        context.refresh();
        launch();
    }

}
