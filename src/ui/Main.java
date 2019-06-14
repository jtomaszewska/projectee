package ui;

import domain.base.ObjectPlus;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        readDatabase();

        Parent root = FXMLLoader.load(getClass().getResource("backlogWindow.fxml"));
        primaryStage.setTitle("Projectee");
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setScene(new Scene(root, bounds.getWidth(), bounds.getHeight()));

//        primaryStage.setWidth(bounds.getWidth());
//        primaryStage.setHeight(bounds.getHeight());

        primaryStage.show();
        primaryStage.setMaximized(true);
    }

    public void readDatabase() throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("objects.txt"));
        ObjectPlus.readExtents(objectInputStream);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
