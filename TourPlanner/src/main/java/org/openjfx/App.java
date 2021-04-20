package org.openjfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;


import java.io.IOException;

@ComponentScan
public class App extends Application {
    private static Scene scene;
    private static AnnotationConfigApplicationContext context;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("main"), 640, 480);
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
