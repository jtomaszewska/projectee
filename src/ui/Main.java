package ui;

import domain.Task;
import domain.base.ObjectPlus;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Main extends Application {

    public static Stage stage;
    public static Task selectedTask;

    @Override
    public void start(Stage primaryStage) throws Exception{

        readDatabase();

        Parent root = FXMLLoader.load(BacklogWindowController.class.getResource("BacklogWindow.fxml"));
        primaryStage.setTitle("Projectee");
        primaryStage.setScene(new Scene(root, 1000, 500));
        primaryStage.show();
        primaryStage.setMaximized(false);
        stage = primaryStage;
    }

    public void readDatabase() throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("objects.txt"));
        ObjectPlus.readExtents(objectInputStream);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
